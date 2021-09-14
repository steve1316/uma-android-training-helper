package com.steve1316.uma_android_training_helper.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.preference.PreferenceManager
import com.googlecode.tesseract.android.TessBaseAPI
import com.steve1316.uma_android_training_helper.MainActivity
import com.steve1316.uma_android_training_helper.bot.Game
import com.steve1316.uma_android_training_helper.ui.settings.SettingsFragment
import org.opencv.android.Utils
import org.opencv.core.*
import org.opencv.imgcodecs.Imgcodecs
import org.opencv.imgproc.Imgproc
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.lang.Integer.max
import java.text.DecimalFormat


/**
 * Utility functions for image processing via CV like OpenCV.
 */
class ImageUtils(context: Context, private val game: Game) {
	private val tag: String = "[${MainActivity.loggerTag}]ImageUtils"
	private var myContext = context
	
	private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
	private val debugMode: Boolean = sharedPreferences.getBoolean("debugMode", false)
	
	private val displayWidth: Int = MediaProjectionService.displayWidth
	private val displayHeight: Int = MediaProjectionService.displayHeight
	private val isDefault: Boolean = (displayWidth == 1080) // 1080p
	private val isLowerEnd: Boolean = (displayWidth == 720) // 720p
	private val isTablet: Boolean = (displayWidth == 1600 && displayHeight == 2560) || (displayWidth == 2560 && displayHeight == 1600) // Galaxy Tab S7 1600x2560 Portrait Mode
	
	// 720 pixels in width.
	private val lowerEndScales: MutableList<Double> = mutableListOf(0.60, 0.61, 0.62, 0.63, 0.64, 0.65, 0.67, 0.68, 0.69, 0.70)
	
	// Middle ground between 720 and 1080 pixels.
	private val middleEndScales: MutableList<Double> = mutableListOf(
		0.70, 0.71, 0.72, 0.73, 0.74, 0.75, 0.76, 0.77, 0.78, 0.79, 0.80, 0.81, 0.82, 0.83, 0.84, 0.85, 0.87, 0.88, 0.89, 0.90, 0.91, 0.92, 0.93, 0.94, 0.95, 0.96, 0.97, 0.98, 0.99
	)
	
	// 1600 pixels in width in Portrait Mode.
	private val tabletPortraitScales: MutableList<Double> = mutableListOf(1.28, 1.30, 1.32, 1.34)
	
	private val decimalFormat = DecimalFormat("#.###")
	
	private val matchMethod: Int = Imgproc.TM_CCOEFF_NORMED
	
	private val tesseractLanguages = arrayListOf("jpn")
	private val tessBaseAPI: TessBaseAPI
	
	private var firstTimeCheck: Boolean = true
	private var sourceBitmap: Bitmap? = null
	
	companion object {
		private var matchFilePath: String = ""
		private lateinit var matchLocation: Point
		
		/**
		 * Saves the file path to the saved match image file for debugging purposes.
		 *
		 * @param filePath File path to where to store the image containing the location of where the match was found.
		 */
		private fun updateMatchFilePath(filePath: String) {
			matchFilePath = filePath
		}
	}
	
	init {
		// Set the file path to the /files/temp/ folder.
		val matchFilePath: String = myContext.getExternalFilesDir(null)?.absolutePath + "/temp"
		updateMatchFilePath(matchFilePath)
		
		// Initialize Tesseract with the jpn.traineddata model.
		initTesseract()
		tessBaseAPI = TessBaseAPI()
		
		// Start up Tesseract.
		tessBaseAPI.init(myContext.getExternalFilesDir(null)?.absolutePath + "/tesseract/", "jpn")
		game.printToLog("[INFO] Training file loaded.\n", tag = tag)
	}
	
	/**
	 * Match between the source Bitmap from /files/temp/ and the template Bitmap from the assets folder.
	 *
	 * @param sourceBitmap Bitmap from the /files/temp/ folder.
	 * @param templateBitmap Bitmap from the assets folder.
	 * @param region Specify the region consisting of (x, y, width, height) of the source screenshot to template match. Defaults to (0, 0, 0, 0) which is equivalent to searching the full image.
	 * @return True if a match was found. False otherwise.
	 */
	private fun match(sourceBitmap: Bitmap, templateBitmap: Bitmap, region: IntArray = intArrayOf(0, 0, 0, 0)): Boolean {
		// If a custom region was specified, crop the source screenshot.
		val srcBitmap = if (!region.contentEquals(intArrayOf(0, 0, 0, 0))) {
			Bitmap.createBitmap(sourceBitmap, region[0], region[1], region[2], region[3])
		} else {
			sourceBitmap
		}
		
		// Scale images.
		val scales: MutableList<Double> = when {
			isLowerEnd -> {
				lowerEndScales.toMutableList()
			}
			!isLowerEnd && !isDefault && !isTablet -> {
				middleEndScales.toMutableList()
			}
			isTablet -> {
				tabletPortraitScales.toMutableList()
			}
			else -> {
				mutableListOf(1.0)
			}
		}
		
		while (scales.isNotEmpty()) {
			val newScale: Double = decimalFormat.format(scales.removeFirst()).toDouble()
			
			val tmp: Bitmap = if (newScale != 1.0) {
				Bitmap.createScaledBitmap(templateBitmap, (templateBitmap.width * newScale).toInt(), (templateBitmap.height * newScale).toInt(), true)
			} else {
				templateBitmap
			}
			
			// Create the Mats of both source and template images.
			val sourceMat = Mat()
			val templateMat = Mat()
			Utils.bitmapToMat(srcBitmap, sourceMat)
			Utils.bitmapToMat(tmp, templateMat)
			
			// Make the Mats grayscale for the source and the template.
			Imgproc.cvtColor(sourceMat, sourceMat, Imgproc.COLOR_BGR2GRAY)
			Imgproc.cvtColor(templateMat, templateMat, Imgproc.COLOR_BGR2GRAY)
			
			// Create the result matrix.
			val resultColumns: Int = sourceMat.cols() - templateMat.cols() + 1
			val resultRows: Int = sourceMat.rows() - templateMat.rows() + 1
			val resultMat = Mat(resultRows, resultColumns, CvType.CV_32FC1)
			
			// Now perform the matching and localize the result.
			Imgproc.matchTemplate(sourceMat, templateMat, resultMat, matchMethod)
			val mmr: Core.MinMaxLocResult = Core.minMaxLoc(resultMat)
			
			matchLocation = Point()
			var matchCheck = false
			
			// Format minVal or maxVal.
			val minVal: Double = decimalFormat.format(mmr.minVal).toDouble()
			val maxVal: Double = decimalFormat.format(mmr.maxVal).toDouble()
			
			// Depending on which matching method was used, the algorithms determine which location was the best.
			if ((matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) && mmr.minVal <= (1.0 - 0.8)) {
				matchLocation = mmr.minLoc
				matchCheck = true
				if (debugMode) {
					game.printToLog("[DEBUG] Match found with $minVal <= ${1.0 - 0.8} at Point $matchLocation using scale: $newScale.", tag = tag)
				}
			} else if ((matchMethod != Imgproc.TM_SQDIFF && matchMethod != Imgproc.TM_SQDIFF_NORMED) && mmr.maxVal >= 0.8) {
				matchLocation = mmr.maxLoc
				matchCheck = true
				if (debugMode) {
					game.printToLog("[DEBUG] Match found with $maxVal >= 0.8 at Point $matchLocation using scale: $newScale.", tag = tag)
				}
			} else {
				if (debugMode) {
					if ((matchMethod != Imgproc.TM_SQDIFF && matchMethod != Imgproc.TM_SQDIFF_NORMED)) {
						game.printToLog("[DEBUG] Match not found with $maxVal not >= 0.8 at Point ${mmr.maxLoc} using scale $newScale.", tag = tag)
					} else {
						game.printToLog("[DEBUG] Match not found with $minVal not <= ${1.0 - 0.8} at Point ${mmr.minLoc} using scale $newScale.", tag = tag)
					}
				}
			}
			
			if (matchCheck) {
				if (debugMode) {
					// Draw a rectangle around the supposed best matching location and then save the match into a file in /files/temp/ directory. This is for debugging purposes to see if this
					// algorithm found the match accurately or not.
					if (matchFilePath != "") {
						Imgproc.rectangle(sourceMat, matchLocation, Point(matchLocation.x + templateMat.cols(), matchLocation.y + templateMat.rows()), Scalar(0.0, 128.0, 0.0), 5)
						Imgcodecs.imwrite("$matchFilePath/match.png", sourceMat)
					}
				}
				
				// Center the coordinates so that any tap gesture would be directed at the center of that match location instead of the default
				// position of the top left corner of the match location.
				matchLocation.x += (templateMat.cols() / 2)
				matchLocation.y += (templateMat.rows() / 2)
				
				// If a custom region was specified, readjust the coordinates to reflect the fullscreen source screenshot.
				if (!region.contentEquals(intArrayOf(0, 0, 0, 0))) {
					matchLocation.x = sourceBitmap.width - (region[0] + matchLocation.x)
					matchLocation.y = sourceBitmap.height - (region[1] + matchLocation.y)
				}
				
				return true
			}
		}
		
		return false
	}
	
	/**
	 * Open the source and template image files and return Bitmaps for them.
	 *
	 * @param templateName File name of the template image.
	 * @return A Pair of source and template Bitmaps.
	 */
	private fun getBitmaps(templateName: String): Pair<Bitmap?, Bitmap?> {
		// Keep the same source Bitmap on repeated tries to reduce processing time.
		if (firstTimeCheck) {
			// Keep swiping a little bit up and down to trigger a new image for ImageReader to grab.
			while (sourceBitmap == null) {
				sourceBitmap = MediaProjectionService.takeScreenshotNow()
			}
			
			firstTimeCheck = false
		}
		
		var templateBitmap: Bitmap?
		
		// Get the Bitmap from the template image file inside the specified folder.
		myContext.assets?.open("images/$templateName.webp").use { inputStream ->
			// Get the Bitmap from the template image file and then start matching.
			templateBitmap = BitmapFactory.decodeStream(inputStream)
		}
		
		return if (templateBitmap != null) {
			Pair(sourceBitmap, templateBitmap)
		} else {
			game.printToLog("[ERROR] One or more of the Bitmaps are null.", tag = tag, isError = true)
			
			Pair(sourceBitmap, templateBitmap)
		}
	}
	
	/**
	 * Perform OCR text detection using Tesseract along with some image manipulation via thresholding to make the cropped screenshot black and white using OpenCV.
	 *
	 * @param increment Increments the threshold by this value. Defaults to 0.0
	 * @return The detected String in the cropped region.
	 */
	fun findText(increment: Double = 0.0): String {
		val (sourceBitmap, templateBitmap) = getBitmaps("shift")
		
		// Acquire the location of the energy text image.
		val (_, energyTemplateBitmap) = getBitmaps("energy")
		match(sourceBitmap!!, energyTemplateBitmap!!)
		
		// Use the match location acquired from finding the energy text image and acquire the (x, y) coordinates of the event title container right below the location of the energy text image.
		// Note that the cropped bitmap does not take into account the shift yet. That comes afterwards.
		val newX: Int
		val newY: Int
		var croppedBitmap: Bitmap = when {
			isLowerEnd -> {
				newX = max(0, matchLocation.x.toInt() - (85).toInt())
				newY = max(0, matchLocation.y.toInt() + (75).toInt())
				Bitmap.createBitmap(sourceBitmap, newX, newY, 450, 45)
			}
			isTablet -> {
				newX = max(0, matchLocation.x.toInt() - (250).toInt())
				newY = max(0, matchLocation.y.toInt() + (154).toInt())
				Bitmap.createBitmap(sourceBitmap, newX, newY, 900, 85)
			}
			else -> {
				newX = max(0, matchLocation.x.toInt() - 125)
				newY = max(0, matchLocation.y.toInt() + 116)
				Bitmap.createBitmap(sourceBitmap, newX, newY, 700, 65)
			}
		}
		
		// Now see if it is necessary to shift the cropped region over by 70 pixels or not to account for certain events.
		croppedBitmap = if (match(croppedBitmap, templateBitmap!!)) {
			Log.d(tag, "Applying shift!")
			
			when {
				isLowerEnd -> {
					Bitmap.createBitmap(croppedBitmap, 50, 0, croppedBitmap.width - 50, croppedBitmap.height)
				}
				isTablet -> {
					Bitmap.createBitmap(croppedBitmap, 100, 0, croppedBitmap.width - 100, croppedBitmap.height)
				}
				else -> {
					Bitmap.createBitmap(croppedBitmap, 70, 0, croppedBitmap.width - 70, croppedBitmap.height)
				}
			}
		} else {
			Log.d(tag, "Do not need to shift.")
			croppedBitmap
		}
		
		// Enlarge the region of the event title.
		croppedBitmap = Bitmap.createScaledBitmap(croppedBitmap, croppedBitmap.width * 2, croppedBitmap.height * 2, true)
		
		// Make the cropped screenshot grayscale.
		val cvImage = Mat()
		Utils.bitmapToMat(croppedBitmap, cvImage)
		Imgproc.cvtColor(cvImage, cvImage, Imgproc.COLOR_BGR2GRAY)
		
		// Save the cropped image before converting it to black and white in order to troubleshoot issues related to differing device sizes and cropping.
		Imgcodecs.imwrite("$matchFilePath/pre-RESULT.png", cvImage)
		
		// Thresh the grayscale cropped image to make black and white.
		val bwImage = Mat()
		val threshold = SettingsFragment.getIntSharedPreference(myContext, "threshold")
		Imgproc.threshold(cvImage, bwImage, threshold.toDouble() + increment, 255.0, Imgproc.THRESH_BINARY)
		Imgcodecs.imwrite("$matchFilePath/RESULT.png", bwImage)
		
		game.printToLog("[INFO] Saved result image successfully named RESULT.png to internal storage inside the /files/temp/ folder.", tag = tag)
		
		val resultBitmap = BitmapFactory.decodeFile("$matchFilePath/RESULT.png")
		tessBaseAPI.setImage(resultBitmap)
		
		// Set the Page Segmentation Mode to '--psm 7' or "Treat the image as a single text line" according to https://tesseract-ocr.github.io/tessdoc/ImproveQuality.html#page-segmentation-method
		tessBaseAPI.pageSegMode = TessBaseAPI.PageSegMode.PSM_SINGLE_LINE
		
		var result = "empty!"
		try {
			// Finally, detect text on the cropped region.
			result = tessBaseAPI.utF8Text
		} catch (e: Exception) {
			game.printToLog("[ERROR] Cannot perform OCR: ${e.stackTraceToString()}", tag = tag, isError = true)
		}
		
		tessBaseAPI.clear()
		
		return result
	}
	
	/**
	 * Initialize Tesseract for future OCR operations. Make sure to put your .traineddata inside the root of the /assets/ folder.
	 */
	private fun initTesseract() {
		val externalFilesDir: File? = myContext.getExternalFilesDir(null)
		val tempDirectory: String = externalFilesDir?.absolutePath + "/tesseract/tessdata/"
		val newTempDirectory = File(tempDirectory)
		
		// If the /files/temp/ folder does not exist, create it.
		if (!newTempDirectory.exists()) {
			val successfullyCreated: Boolean = newTempDirectory.mkdirs()
			
			// If the folder was not able to be created for some reason, log the error and stop the MediaProjection Service.
			if (!successfullyCreated) {
				game.printToLog("[ERROR] Failed to create the /files/tesseract/tessdata/ folder.", tag = tag, isError = true)
			} else {
				game.printToLog("[INFO] Successfully created /files/tesseract/tessdata/ folder.", tag = tag)
			}
		} else {
			game.printToLog("[INFO] /files/tesseract/tessdata/ folder already exists.", tag = tag)
		}
		
		// If the traineddata is not in the application folder, copy it there from assets.
		tesseractLanguages.forEach { lang ->
			val trainedDataPath = File(tempDirectory, "$lang.traineddata")
			if (!trainedDataPath.exists()) {
				try {
					game.printToLog("[INFO] Starting Tesseract initialization.", tag = tag)
					val input = myContext.assets.open("$lang.traineddata")
					
					val output = FileOutputStream("$tempDirectory/$lang.traineddata")
					
					val buffer = ByteArray(1024)
					var read: Int
					while (input.read(buffer).also { read = it } != -1) {
						output.write(buffer, 0, read)
					}
					
					input.close()
					output.flush()
					output.close()
					game.printToLog("[INFO] Finished Tesseract initialization.", tag = tag)
				} catch (e: IOException) {
					game.printToLog("[ERROR] IO EXCEPTION: ${e.stackTraceToString()}", tag = tag, isError = true)
				}
			}
		}
	}
}
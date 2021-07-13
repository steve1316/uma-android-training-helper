package com.steve1316.uma_android_training_helper.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
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


/**
 * Utility functions for image processing via CV like OpenCV.
 */
class ImageUtils(context: Context, private val game: Game) {
	private val TAG: String = "[${MainActivity.loggerTag}]ImageUtils"
	private var myContext = context
	
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
		game.printToLog("[INFO] Training file loaded.\n", tag = TAG)
	}
	
	/**
	 * Match between the source Bitmap from /files/temp/ and the template Bitmap from the assets folder.
	 *
	 * @param sourceBitmap Bitmap from the /files/temp/ folder.
	 * @param templateBitmap Bitmap from the assets folder.
	 * @param useCannyAlgorithm Check whether or not to use Canny edge detection algorithm. Defaults to false.
	 */
	private fun match(sourceBitmap: Bitmap, templateBitmap: Bitmap, useCannyAlgorithm: Boolean = false): Boolean {
		// Create the Mats of both source and template images.
		val sourceMat = Mat()
		val templateMat = Mat()
		Utils.bitmapToMat(sourceBitmap, sourceMat)
		Utils.bitmapToMat(templateBitmap, templateMat)
		
		// Make the Mats grayscale for the source and the template.
		Imgproc.cvtColor(sourceMat, sourceMat, Imgproc.COLOR_BGR2GRAY)
		Imgproc.cvtColor(templateMat, templateMat, Imgproc.COLOR_BGR2GRAY)
		
		if (useCannyAlgorithm) {
			// Blur the source and template.
			Imgproc.blur(sourceMat, sourceMat, Size(3.0, 3.0))
			Imgproc.blur(templateMat, templateMat, Size(3.0, 3.0))
			
			// Apply Canny edge detection algorithm in both source and template. Generally recommended for threshold2 to be 3 times threshold1.
			Imgproc.Canny(sourceMat, sourceMat, 100.0, 300.0)
			Imgproc.Canny(templateMat, templateMat, 100.0, 300.0)
		}
		
		// Create the result matrix.
		val resultColumns: Int = sourceMat.cols() - templateMat.cols() + 1
		val resultRows: Int = sourceMat.rows() - templateMat.rows() + 1
		val resultMat = Mat(resultRows, resultColumns, CvType.CV_32FC1)
		
		// Now perform the matching and localize the result.
		Imgproc.matchTemplate(sourceMat, templateMat, resultMat, matchMethod)
		val mmr: Core.MinMaxLocResult = Core.minMaxLoc(resultMat)
		
		matchLocation = Point()
		var matchCheck = false
		
		// Depending on which matching method was used, the algorithms determine which location was the best.
		if ((matchMethod == Imgproc.TM_SQDIFF || matchMethod == Imgproc.TM_SQDIFF_NORMED) && mmr.minVal <= 0.2) {
			matchLocation = mmr.minLoc
			matchCheck = true
		} else if ((matchMethod != Imgproc.TM_SQDIFF && matchMethod != Imgproc.TM_SQDIFF_NORMED) && mmr.maxVal >= 0.8) {
			matchLocation = mmr.maxLoc
			matchCheck = true
		}
		
		if (matchCheck) {
			// Draw a rectangle around the supposed best matching location and then save the match into a file in /files/temp/ directory. This is for
			// debugging purposes to see if this algorithm found the match accurately or not.
			if (matchFilePath != "") {
				Imgproc.rectangle(sourceMat, matchLocation, Point(matchLocation.x + templateMat.cols(), matchLocation.y + templateMat.rows()), Scalar(0.0, 128.0, 0.0), 5)
				Imgcodecs.imwrite("$matchFilePath/match.png", sourceMat)
			}
			
			// Center the coordinates so that any tap gesture would be directed at the center of that match location instead of the default
			// position of the top left corner of the match location.
			matchLocation.x += (templateMat.cols() / 2)
			matchLocation.y += (templateMat.rows() / 2)
			
			return true
		} else {
			return false
		}
	}
	
	/**
	 * Open the source and template image files and return Bitmaps for them.
	 *
	 * @param templateName File name of the template image.
	 * @param templateFolderName Name of the subfolder in /assets/ that the template image is in.
	 * @return A Pair of source and template Bitmaps.
	 */
	private fun getBitmaps(templateName: String, templateFolderName: String): Pair<Bitmap?, Bitmap?> {
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
		myContext.assets?.open("$templateFolderName/$templateName.webp").use { inputStream ->
			// Get the Bitmap from the template image file and then start matching.
			templateBitmap = BitmapFactory.decodeStream(inputStream)
		}
		
		return if (templateBitmap != null) {
			Pair(sourceBitmap, templateBitmap)
		} else {
			game.printToLog("[ERROR] One or more of the Bitmaps are null.", tag = TAG, isError = true)
			
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
		val (sourceBitmap, templateBitmap) = getBitmaps("shift", "images")
		
		// Acquire the location of the energy text image.
		val (_, energyTemplateBitmap) = getBitmaps("energy", "images")
		match(sourceBitmap!!, energyTemplateBitmap!!)
		
		// Acquire the (x, y) coordinates of the event title container right below the location of the energy text image.
		val newX: Int = max(0, matchLocation.x.toInt() - 125)
		val newY: Int = max(0, matchLocation.y.toInt() + 116)
		var croppedBitmap: Bitmap = Bitmap.createBitmap(sourceBitmap, newX, newY, 645, 65)
		
		// Now see if it is necessary to shift the cropped region over by 70 pixels or not to account for certain events.
		croppedBitmap = if (match(croppedBitmap, templateBitmap!!)) {
			Log.d(TAG, "Shifting the region over by 70 pixels!")
			Bitmap.createBitmap(sourceBitmap, newX + 70, newY, 645 - 70, 65)
		} else {
			Log.d(TAG, "Do not need to shift.")
			croppedBitmap
		}
		
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
		
		game.printToLog("[INFO] Saved result image successfully named RESULT.png to internal storage inside the /files/temp/ folder.", tag = TAG)
		
		val resultBitmap = BitmapFactory.decodeFile("$matchFilePath/RESULT.png")
		tessBaseAPI.setImage(resultBitmap)
		
		// Set the Page Segmentation Mode to '--psm 7' or "Treat the image as a single text line" according to https://tesseract-ocr.github.io/tessdoc/ImproveQuality.html#page-segmentation-method
		tessBaseAPI.pageSegMode = TessBaseAPI.PageSegMode.PSM_SINGLE_LINE
		
		var result = "empty!"
		try {
			// Finally, detect text on the cropped region.
			result = tessBaseAPI.utF8Text
		} catch (e: Exception) {
			game.printToLog("[ERROR] Cannot perform OCR: ${e.stackTraceToString()}", tag = TAG, isError = true)
		}
		
		tessBaseAPI.end()
		
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
				game.printToLog("[ERROR] Failed to create the /files/tesseract/tessdata/ folder.", tag = TAG, isError = true)
			} else {
				game.printToLog("[INFO] Successfully created /files/tesseract/tessdata/ folder.", tag = TAG)
			}
		} else {
			game.printToLog("[INFO] /files/tesseract/tessdata/ folder already exists.", tag = TAG)
		}
		
		// If the traineddata is not in the application folder, copy it there from assets.
		tesseractLanguages.forEach { lang ->
			val trainedDataPath = File(tempDirectory, "$lang.traineddata")
			if (!trainedDataPath.exists()) {
				try {
					game.printToLog("[INFO] Starting Tesseract initialization.", tag = TAG)
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
					game.printToLog("[INFO] Finished Tesseract initialization.", tag = TAG)
				} catch (e: IOException) {
					game.printToLog("[ERROR] IO EXCEPTION: ${e.stackTraceToString()}", tag = TAG, isError = true)
				}
			}
		}
	}
}
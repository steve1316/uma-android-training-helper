# Uma Musume Training Event Helper For Android using MediaProjection, AccessibilityService, OpenCV, and Tesseract
![GitHub commit activity](https://img.shields.io/github/commit-activity/m/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub last commit](https://img.shields.io/github/last-commit/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub issues](https://img.shields.io/github/issues/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub pull requests](https://img.shields.io/github/issues-pr/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub](https://img.shields.io/github/license/steve1316/uma-android-training-helper?logo=GitHub)

Inspiration from @amate for their work on [UmaUmaCruise](https://github.com/amate/UmaUmaCruise) and @gertasik for [GameTora's Training Event Helper](https://gametora.com/umamusume/training-event-helper).

This Android application written in Kotlin is designed to assist Uma Musume players with selecting the right training options in the same vein as amate's UmaCruise and GameTora's Training Event Helper.

It accomplishes this by taking a screenshot via the MediaProjection and OpenCV performs image processing. Finally, Tesseract will perform OCR text recognition on it and will determine if there is a similar string to it in the data and will display it as a Notification that you can view the training event's option rewards.

# Features
- [x] Perform OCR text detection on the fly for Uma Musume's training events.
- [ ] Add support for all characters. (IN-PROGRESS)
- [ ] Add support for all support cards. (IN-PROGRESS)

# Requirements
WIP

# Instructions
1. Download the .apk file from the ```Releases``` section on the right and install it on your Android device. If you want to build the .apk yourself, do the following:
   1. Download and extract the project repository.
   2. Go to ```https://opencv.org/releases/``` and download OpenCV 4.5.1 (make sure to download the Android version of OpenCV) and extract it.
   3. Create a new folder inside the root of the project repository named ```opencv``` and copy the extracted files in ```/OpenCV-android-sdk/sdk/``` from Step 2 into it.
   4. Open the project repository in ```Android Studio``` and you can now build and run on your Android Device or build your own .apk file.
   5. You can set ```universalApk``` to ```true``` in the ```build.gradle``` for the application to build a one-for-all .apk file or adjust the ```include 'arm64-v8a'``` to customize which ABI to build the .apk file for.
2. Once you have it running, fill out the required sections marked with * in the Settings page of the application.
3. Now go back to the Home page. The settings you have selected will be shown to you in the text box below the ```Start``` button.
4. Now tap on the ```Start``` button. If this is the first time, it will ask you to give the application ```Overlay``` permission.
5. Once it is enabled, tapping on the ```Start``` button again will display an overlay button that you can move around the screen.
6. Navigate yourself to the training event and press the overlay button to start. A Notification will notify you of whether the OCR was successful and will display its results.
7. Whenever you want to stop, just press ```STOP PROCESS``` on the Notification in your Notification Drawer.

# Technologies Used
1. [jpn.traineddata from UmaUmaCruise by @amate](https://github.com/amate/UmaUmaCruise)
2. [MediaProjection - Used to obtain full screenshots](https://developer.android.com/reference/android/media/projection/MediaProjection)
3. [AccessibilityService - Used to dispatch gestures like tapping and scrolling](https://developer.android.com/reference/android/accessibilityservice/AccessibilityService)
4. [OpenCV Android 4.5.1 - Used to template match](https://opencv.org/releases/)
5. [Tesseract4Android 2.1.1](https://github.com/adaptech-cz/Tesseract4Android)
6. [string-similarity 1.0.0](https://github.com/rrice/java-string-similarity)

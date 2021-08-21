# Uma Musume Training Event Helper For Android using OpenCV and Tesseract

![GitHub commit activity](https://img.shields.io/github/commit-activity/m/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub last commit](https://img.shields.io/github/last-commit/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub issues](https://img.shields.io/github/issues/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub pull requests](https://img.shields.io/github/issues-pr/steve1316/uma-android-training-helper?logo=GitHub) ![GitHub](https://img.shields.io/github/license/steve1316/uma-android-training-helper?logo=GitHub)

> Data last updated August 20, 2021 (Eishin Flash update)

Inspiration from @amate for their work on [UmaUmaCruise](https://github.com/amate/UmaUmaCruise) and @gertasik for [GameTora's Training Event Helper](https://gametora.com/umamusume/training-event-helper).

This Android application written in Kotlin is designed to assist Uma Musume Pretty Derby players with selecting the right training options in the same vein as amate's UmaCruise and GameTora's Training Event Helper by informing you of what rewards you get for each option on the screen in a seamless manner. In addition, it will also inform you of the English translations of any skills and statuses present in the rewards and what they do.

https://user-images.githubusercontent.com/18709555/121611207-96999d00-ca0c-11eb-9eb1-538131e52719.mp4

## Technical Process

Upon tapping the floating overlay button, the process begins.

-   First it takes a fullscreen screenshot using MediaProjection and saves it as `source.png` inside the /files/temp/ folder in Android internal storage.
-   Secondly, it converts the `source.png` to grayscale and crops the image to be the region of the Event Title. After that, it thresholds the cropped region turning it black and white and saves it as `RESULTS.png` inside the /files/temp/ folder in Android internal storage.
-   Lastly, Tesseract performs OCR on it using [@amate's jpn.traineddata](https://github.com/amate/UmaUmaCruise). It then takes that string and begins comparison with the strings stored in [CharacterData.kt](https://github.com/steve1316/uma-android-training-helper/blob/main/app/src/main/java/com/steve1316/uma_android_training_helper/data/CharacterData.kt) and [SupportData.kt](https://github.com/steve1316/uma-android-training-helper/blob/main/app/src/main/java/com/steve1316/uma_android_training_helper/data/SupportData.kt) to determine the highest similarity. If the confidence is higher than the set minimum, then it will have the Notification in your Notification Drawer updated to be the Event Rewards for each option. If not, then the Notification will be updated to indicate failure.

# Disclaimer

Due to the difficult nature of Kanji, mileage may vary when using this. I have added an option to adjust the Threshold value in the Settings to allow manual adjustment to improve OCR accuracy.

# Requirements

-   Android Device (Oreo 8.0+)
    -   Tablet needs to be a minimum width of 1600 pixels (like the Galaxy Tab S7 with its 2650x1600 pixel resolution).

# Features

-   [x] Perform OCR text detection on the fly with a floating overlay button for Uma Musume's training events and then display the results as a Notification.
-   [x] Provides Skill and Status translations during training events and what they do, available in the message log.
-   [x] Support for all Characters.
-   [x] Support for all Support Cards.
-   [x] Various settings to adjust such as enabling automatic retry upon OCR failure.

# Instructions

1. Download the .apk file from the `Releases` section on the right and install it on your Android device. If you want to build the .apk yourself, do the following:
    1. Download and extract the project repository.
    2. Go to `https://opencv.org/releases/` and download OpenCV 4.5.1 (make sure to download the Android version of OpenCV) and extract it.
    3. Create a new folder inside the root of the project repository named `opencv` and copy the extracted files in `/OpenCV-android-sdk/sdk/` from Step 2 into it.
    4. Open the project repository in `Android Studio` and you can now build and run on your Android Device or build your own .apk file.
    5. You can set `universalApk` to `true` in the `build.gradle` for the application to build a one-for-all .apk file or adjust the `include 'arm64-v8a'` to customize which ABI to build the .apk file for.
2. Once you have it running, fill out the required sections marked with \* in the Settings page of the application.
3. Now go back to the Home page. The settings you have selected will be shown to you in the text box below the `Start` button.
4. Now tap on the `Start` button. If this is the first time, it will ask you to give the application `Overlay` permission.
5. Once it is enabled, tapping on the `Start` button again will display an overlay button that you can move around the screen.
6. Navigate yourself to the training event and press the overlay button to start. A Notification will notify you of whether the OCR was successful and will display its results.
7. Whenever you want to stop, just press `STOP PROCESS` on the Notification in your Notification Drawer.

# Technologies Used

1. [jpn.traineddata from UmaUmaCruise by @amate](https://github.com/amate/UmaUmaCruise)
2. [MediaProjection - Used to obtain full screenshots](https://developer.android.com/reference/android/media/projection/MediaProjection)
3. [AccessibilityService - Used to dispatch gestures like tapping and scrolling](https://developer.android.com/reference/android/accessibilityservice/AccessibilityService)
4. [OpenCV Android 4.5.1 - Used to template match](https://opencv.org/releases/)
5. [Tesseract4Android 2.1.1 - For performing OCR on the screen](https://github.com/adaptech-cz/Tesseract4Android)
6. [string-similarity 1.0.0 - For comparing string similarities during text detection](https://github.com/rrice/java-string-similarity)
7. [AppUpdater 2.7 - For automatically checking and notifying the user for new app updates](https://github.com/javiersantos/AppUpdater)

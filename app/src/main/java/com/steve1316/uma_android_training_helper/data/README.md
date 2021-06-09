# Data Template Step-by-step

## Step 1
Visit GameTora's Training Event Helper at (https://gametora.com/umamusume/training-event-helper) and select the Character or Support Card that you want to work on. Then select an event and a popup will appear with the event results for each option:

![Example 1 from GameTora](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example1.png)

## Step 2
Then head on over to the respective Data class and under their category, copy and paste from the popup's text and format it like so:

![Formatted String Event of Example 1 from GameTora](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example1_formatted.png)

- Line Breaks are denoted by ```\n```.
- The 1st Option as the first element of the array, the 2nd Option as the second element, etc.

When you encounter an event like this:

![Example 2 from GameTora](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example2.png)

You format the event text like so:

![Formatted String Event of Example 2 from GameTora](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example2_formatted.png)

- The lines to break up each potential reward within one option is denoted by ```\n----------\n```.

## Step 3
Finally, update the ```array.xml``` inside the app/src/main/res/values/array.xml with a new ```<item>``` in the correct category to have it show up as selectable in the UI:

![Final Step](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example3.png)

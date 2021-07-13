# Data Template Step-by-step

## Some things to note
- Characters with multiple versions of themselves (Anime Collab, Wedding, etc.) share a lot of the same events and same rewards. They usually have 3-4 new lines for each different version.
- Support Cards of the same Character share the same basic events as their R counterparts. As such, it is most likely not necessary to copy the bottom events every single time for each different version.

## Step 1
Visit GameTora's Training Event Helper at (https://gametora.com/umamusume/training-event-helper) and select the Character or Support Card that you want to work on. Then select an event and a popup will appear with the event results for each option:

![Example 1 from GameTora](https://raw.githubusercontent.com/steve1316/uma-android-training-helper/main/app/src/main/assets/readme/example1.png)

## Step 2
Then head on over to the respective JSON file and under their category, copy and paste from the popup's text and format it like so:

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

# Skill Template Step-by-step

## Step 1
Make sure you have Python installed (I have Python 3.8.3). Run ```pip install -r requirements.txt``` in the root of the ```skill_web_scraper``` folder in order to install the BeautifulSoup4 package for web scraping.

## Step 2
Now run ```python main.py``` in the same folder. The two files that will be updated inside the folder is a ```skills.json```. Skills are now updated to the latest.

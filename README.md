This repo hosts the code for the blog series about saving and restoring fragment state in Android. The posts are WIP and will be published at [my blog](http://curioustechizen.blogspot.com/).

The sample just contains an `Activity` with two `Fragment`s inside it. The first `Fragment` shows a multiple choice list of Android flagship devices. The second `Fragment` simply contains a `TextView` with a message.

Our goal is to go through the following use cases and see how the `Fragment` life-cycle and the save/restore methods need to be understood in order to achieve all the use cases.

Use Case 1:
----
  1. Launch the app and hit "Populate"
  2. Select one or more items in the list
  3. Hit "Next" to go to the next screen.
  4. Press the Android Back button to return to the list screen

We expect that the items and choices from Step 2 are remembered.


Use Case 2:
----
  1. Launch the app and hit "Populate"
  2. Select one or more items in the list
  3. Rotate the device

We expect that the items and choices from Step 2 are remembered.


Use Case 3:
----
  1. Launch the app and hit "Populate"
  2. Select one or more items in the list
  3. Hit "Next" to go to the next screen.
  4. Rotate the device - multiple times
  5. Press the Android Back button to return to the list screen

We expect that the items and choices from Step 2 are remembered.

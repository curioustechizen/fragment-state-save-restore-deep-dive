This repo hosts the code for the blog series about saving and restoring fragment state in Android. The posts are WIP and will be published at [my blog](http://curioustechizen.blogspot.com/).


TL;DR
----
If you are too lazy to go through the source code and posts, here's an executive summary of the take-aways:
  - When a `Fragment` is not at the top of the stack, i.e., it is on the back stack, the `Activity` still gives it a chance to save state (for example when being rotated).
      + The `onSaveInstanceState` is called, followed by `onDestroy`.
      + Later, after rotation, `onCreate` is called.
      + However, none of the view-related methods are called. `onCreateView` and later methods are not called.
  - The above causes issues with state saving if you are not careful
      + You might try to save state for a view assuming it exists, however since `onCreateView` has not been called, you run the risk of `NullPointerException`
      + A simple null check doesn't get the job done since this results in no state being saved at all.


Recipe
----
Based on these learnings, the suggested recipe for saving/restoring state when it comes to `Fragment`s is:
  - Retrieve state in `onCreate`. Save the retrieved state in a member variable
  - In `onCreateView`, after inflating your layout, finding your views etc, then restore the state of the views using the state you retrieve in `onCreate`
  - In `onSaveInstanceState`, if the views are present, then ask the views for their saved state (by calling the view's `onSaveInstanceState` for example). Save this in the `ouState` parameter.
  - In `onSaveInstanceState`,if the views are not present, then save the state you retrieved in `onCreate` into the `outState` paremeter.

This way, you relay the state you had received in `onCreate` over to the system in `onSaveInstanceState`. So, even if your `Fragment` is on the back stack while it is rotated, it is still able to save state.


Sample Description
----
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

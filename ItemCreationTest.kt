

// run logger with :  adb logcat -s TEST
//run the test with : ./gradlew connectedAndroidTest
// clean and rebuild with  :./gradlew clean  and ./gradlew build


package com.touchsurgery.thesurgeonstodolist

import android.util.Log
import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.rule.ActivityTestRule
import com.touchsurgery.thesurgeonstodolist.activities.MainActivity
import com.touchsurgery.thesurgeonstodolist.R
import org.junit.Rule
import org.junit.Test
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.NoMatchingViewException

class ItemCreationTest {

    // Set up the activity rule to launch the MainActivity before running each test
    @get:Rule
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testTodoListOperations() {
        try {
            /////////// Scenario 1 - Creation of a new (to-do) task and verification of this function

            Log.d("TEST", "Scenario 1: Adding Item 1") // Scenario 1 - Creation of a new (to-do) task and verification of this function
            onView(withId(R.id.fab)).perform(click()) // Click at the floating + button
            onView(withId(R.id.todoText)).perform(typeText("Item 1"), closeSoftKeyboard()) // Type "Item 1" in the input field and close the keyboard

            onView(withId(R.id.seekBar)).perform(object : ViewAction { // Perform custom action on the SeekBar
                override fun getConstraints() = isAssignableFrom(android.widget.SeekBar::class.java) // Ensure the view is a SeekBar
                override fun getDescription() = "Set SeekBar progress to 1" // Description for the action
                override fun perform(uiController: UiController, view: View) {
                    (view as android.widget.SeekBar).progress = 1 // Set the SeekBar progress to 1
                }
            })
            onView(withId(R.id.submitTodo)).perform(click()) // Click the "Submit" button to save the new item

            Log.d("TEST", "Scenario 1: PASS") // Log the success of the first scenario
        } catch (e: Exception) {
            Log.e("TEST", "Scenario 1: FAIL - ${e.message}") // Log failure if an exception occurs
        }

        try {
            /////////// Scenario 2 - Add item 2 and verify ascending listing ( this is a simple assertion, we coculd make it smarter with random priority production adn checking)

            Log.d("TEST", "Scenario 2: Adding Item 2 and verify ascending list") // Scenario 2 - Add item 2 and verify ascending listing
            onView(withId(R.id.fab)).perform(click()) // Click FAB to add new item
            onView(withId(R.id.todoText)).perform(typeText("Item 2"), closeSoftKeyboard()) // Type "Item 2"

            // Set priority to 1 (higher priority)
            onView(withId(R.id.seekBar)).perform(object : ViewAction {
                override fun getConstraints() = isAssignableFrom(android.widget.SeekBar::class.java)
                override fun getDescription() = "Set SeekBar progress to 1"
                override fun perform(uiController: UiController, view: View) {
                    (view as android.widget.SeekBar).progress = 4
                }
            })
            onView(withId(R.id.submitTodo)).perform(click()) // Submit the item

            onView(withContentDescription("More options")).perform(click()) // Click on the "More options" menu
            onView(withText("Settings")).perform(click()) // Click on the "Settings" option
            onView(withText("Sort list by priority")).perform(click()) // Click on "Sort list by priority" option
            onView(isRoot()).perform(pressBack()) // Press the back button to return to the previous screen

            onView(withText("(1) Item 1")).check(matches(isDisplayed())) // Verify Item 1 appears with priority 1
            onView(withText("(4) Item 2")).check(matches(isDisplayed())) // Verify Item 2 appears with priority 4
            Log.d("TEST", "Scenario 2: PASS")

        } catch (e: Exception) {
            Log.e("TEST", "Scenario 2: FAIL - ${e.message}")
        }


        try {
            /////////// Scenario 3 - Task delete and veficication of this function

            Log.d("TEST", "Scenario 3: Deleting Item 1") // Scenario 3 - Task delete and veficication of this function
            onView(withText("(1) Item 1")).perform(click()) // Click on Item 1 in the list
            onView(withText("(1) Item 1")).check(doesNotExist()) // Verify that Item 1 no longer exists in the list
            Log.d("TEST", "Scenario 3: PASS") // Log the success of the third scenario
        } catch (e: Exception) {
            Log.e("TEST", "Scenario 3: FAIL - ${e.message}") // Log failure if an exception occurs
        }

        try {
            /////////// Scenario 4 - Modify Settings and verification

            Log.d("TEST", "Scenario 4: Open Settings and Sort by Name") // Scenario 4 - Modify Settings and verification
            onView(withContentDescription("More options")).perform(click()) // Click on the "More options" menu
            onView(withText("Settings")).perform(click()) // Click on the "Settings" option
            onView(withText("Sort list by name")).perform(click()) // Click on "Sort list by name" option
            onView(isRoot()).perform(pressBack()) // Press the back button to return to the previous screen
            Log.d("TEST", "Scenario 4: PASS") // Log the success of the fourth scenario
        } catch (e: Exception) {
            Log.e("TEST", "Scenario 4: FAIL - ${e.message}") // Log failure if an exception occurs
        }

        try {

            /////////// Scenario 5 - Delete all tasks - verification of this function

            Log.d("TEST", "Scenario 5: Delete All Items and Verify Items Are Deleted") // Scenario 5 - Delete all tasks - verification of this function

            // Attempt to delete specific items and verify they no longer exist in the list
            try {
                onView(withText("Item 1")).perform(click()).check(doesNotExist()) // Check if "Item 1" is deleted
            } catch (e: NoMatchingViewException) {} // Catch if "Item 1" was not found

            try {
                onView(withText("Item 2")).perform(click()).check(doesNotExist()) // Check if "Item 2" is deleted
            } catch (e: NoMatchingViewException) {} // Catch if "Item 2" was not found

            try {
                onView(withText("Treat patients")).perform(click()).check(doesNotExist()) // Check if "Treat patients" is deleted
            } catch (e: NoMatchingViewException) {} // Catch if "Treat patients" was not found

            try {
                onView(withText("Try those amazing surgery mobile apps")).perform(click()).check(doesNotExist()) // Check if other item is deleted
            } catch (e: NoMatchingViewException) {} // Catch if the item was not found

            // Check that all deleted items no longer exist in the list
            onView(withText("Item 1")).check(doesNotExist()) // Confirm Item 1 is deleted
            onView(withText("Item 2")).check(doesNotExist()) // Confirm Item 2 is deleted
            onView(withText("Treat patients")).check(doesNotExist()) // Confirm "Treat patients" is deleted
            onView(withText("Try those amazing surgery mobile apps")).check(doesNotExist()) // Confirm other item is deleted

            Log.d("TEST", "Scenario 5: PASS") // Log the success of the fifth scenario
        } catch (e: Exception) {
            Log.e("TEST", "Scenario 5: FAIL - ${e.message}") // Log failure if an exception occurs
        }
    }
}

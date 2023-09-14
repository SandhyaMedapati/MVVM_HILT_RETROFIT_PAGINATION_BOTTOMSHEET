package com.sandhya.projects.uitests

import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sandhya.projects.MainActivity
import com.sandhya.projects.R
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAction
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.google.android.material.tabs.TabLayout
import com.sandhya.projects.staff.ui.StaffFragment
import com.sandhya.projects.students.ui.StudentsFragment
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
class FragmentTest {

    @get:Rule
    val activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testStaffTabItemCount() {
        activityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)

        Thread.sleep(1000)
        activityScenarioRule.scenario.onActivity { activity ->
            val tabLayout: TabLayout = activity.findViewById(R.id.tabLayout)

            val tabTitleToFind = "STAFF"

            val tabIndex = (0 until tabLayout.tabCount).firstOrNull { index ->
                tabLayout.getTabAt(index)?.text?.toString()
                    ?.equals(tabTitleToFind, ignoreCase = true) == true
            }

            Log.e(
                "MainActivityTest", "testTabTitle: Success with $tabTitleToFind at index $tabIndex"
            )

            if (tabIndex != null) {
                tabLayout.getTabAt(tabIndex)?.select()

                Thread.sleep(1000)

                val fragmentManager: FragmentManager = activity.supportFragmentManager

                val fragment = fragmentManager.findFragmentByTag("f$tabIndex-$tabTitleToFind")

                if (fragment is StaffFragment) {
                    onView(withId(R.id.staffRecyclerView)).check(
                        RecyclerViewItemCountAssertion(
                            EXPECTED_STAFF_ITEM_COUNT
                        )
                    )
                } else {
                    throw IllegalArgumentException("The fragment is not a StaffFragment")
                }
            } else {
                throw IllegalArgumentException("Tab with title \"$tabTitleToFind\" not found.")
            }
        }
    }

    @Test
    fun testStudentsTabItemCount() {
        activityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)

        Thread.sleep(2000)
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.viewPager)).perform(ViewPagerActions.scrollRight())

        activityScenarioRule.scenario.onActivity { activity ->
            val tabLayout: TabLayout = activity.findViewById(R.id.tabLayout)

            val tabTitleToFind = "STUDENT"

            val tabIndex = (0 until tabLayout.tabCount).firstOrNull { index ->
                tabLayout.getTabAt(index)?.text?.toString()
                    ?.equals(tabTitleToFind, ignoreCase = true) == true
            }

            Log.e(
                "MainActivityTest", "testTabTitle: Success with $tabTitleToFind at index $tabIndex"
            )

            if (tabIndex != null) {
                tabLayout.getTabAt(tabIndex)?.select()

                Thread.sleep(1000)

                val fragmentManager: FragmentManager = activity.supportFragmentManager

                val fragment = fragmentManager.findFragmentByTag("f$tabIndex-$tabTitleToFind")

                if (fragment is StudentsFragment) {
                    onView(withId(R.id.studentsRecyclerView)).check(
                        RecyclerViewItemCountAssertion(
                            EXPECTED_STUDENTS_ITEM_COUNT
                        )
                    )
                } else {
                    throw IllegalArgumentException("The fragment is not a StudentsFragment")
                }
            } else {
                throw IllegalArgumentException("Tab with title \"$tabTitleToFind\" not found.")
            }
        }
    }

    @Test
    fun testCharactersTabItemCount() {
        activityScenarioRule.scenario.moveToState(Lifecycle.State.RESUMED)

        Thread.sleep(2000)
        onView(withId(R.id.viewPager)).perform(ViewActions.swipeLeft())
        onView(withId(R.id.viewPager)).perform(ViewPagerActions.scrollRight())

        activityScenarioRule.scenario.onActivity { activity ->
            val tabLayout: TabLayout = activity.findViewById(R.id.tabLayout)

            val tabTitleToFind = "CHARACTERS"

            val tabIndex = (0 until tabLayout.tabCount).firstOrNull { index ->
                tabLayout.getTabAt(index)?.text?.toString()
                    ?.equals(tabTitleToFind, ignoreCase = true) == true
            }

            Log.e(
                "MainActivityTest", "testTabTitle: Success with $tabTitleToFind at index $tabIndex"
            )

            if (tabIndex != null) {
                tabLayout.getTabAt(tabIndex)?.select()

                Thread.sleep(1000)

                val fragmentManager: FragmentManager = activity.supportFragmentManager

                val fragment = fragmentManager.findFragmentByTag("f$tabIndex-$tabTitleToFind")

                if (fragment is StudentsFragment) {
                    onView(withId(R.id.charactersRecyclerView)).check(
                        RecyclerViewItemCountAssertion(
                            EXPECTED_CHARACTERS_ITEM_COUNT
                        )
                    )
                } else {
                    throw IllegalArgumentException("The fragment is not a CharactersFragment")
                }
            } else {
                throw IllegalArgumentException("Tab with title \"$tabTitleToFind\" not found.")
            }
        }
    }

    companion object {
        private const val EXPECTED_STAFF_ITEM_COUNT = 10
        private const val EXPECTED_STUDENTS_ITEM_COUNT = 15
        private const val EXPECTED_CHARACTERS_ITEM_COUNT = 5
    }

    object ViewPagerActions {
        fun scrollRight(): ViewAction {
            return GeneralSwipeAction(
                Swipe.FAST, GeneralLocation.CENTER_LEFT, GeneralLocation.CENTER_RIGHT, Press.FINGER
            )
        }

        fun scrollLeft(): ViewAction {
            return GeneralSwipeAction(
                Swipe.FAST, GeneralLocation.CENTER_RIGHT, GeneralLocation.CENTER_LEFT, Press.FINGER
            )
        }
    }

    class RecyclerViewItemCountAssertion(private val expectedCount: Int) : ViewAssertion {
        override fun check(view: View?, noViewFoundException: NoMatchingViewException?) {
            if (noViewFoundException != null) {
                throw noViewFoundException
            }

            if (view is RecyclerView) {
                val adapter = view.adapter
                Assert.assertNotNull(adapter)
                Assert.assertEquals(expectedCount, adapter?.itemCount ?: 0)
            } else {
                throw IllegalArgumentException("The view is not a RecyclerView")
            }
        }
    }
}
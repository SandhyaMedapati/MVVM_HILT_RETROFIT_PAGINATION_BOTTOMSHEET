package com.sandhya.projects.uitests

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.android.material.tabs.TabLayout
import com.sandhya.projects.MainActivity
import com.sandhya.projects.R
import junit.framework.TestCase.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    private lateinit var activityScenario: ActivityScenario<MainActivity>
    private val expectedStaffTabTitle = "Staff"
    private val expectedStudentTabTitle = "Student"
    private val expectedCharactersTabTitle = "Characters"

    @Before
    fun setUp() {
        activityScenario = ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun testStaffTabTitle() {
        activityScenario.onActivity { activity ->
            val tabLayout = activity.findViewById<TabLayout>(R.id.tabLayout)
            Thread.sleep(1000)

            val tabTitle = tabLayout.getTabAt(0)?.text.toString()
            Thread.sleep(1000)
            assertEquals(expectedStaffTabTitle, tabTitle)
            if (tabTitle == expectedStaffTabTitle) {
                Log.e("MainActivityTest", "testTabTitle: Success with $tabTitle")
            } else {
                Log.e("MainActivityTest", "testTabTitle: Failed with $tabTitle")
            }
        }
    }

    @Test
    fun testStudentsTabTitle() {
        activityScenario.onActivity { activity ->
            val tabLayout = activity.findViewById<TabLayout>(R.id.tabLayout)
            Thread.sleep(1000)

            val tabTitle = tabLayout.getTabAt(1)?.text.toString()
            Thread.sleep(1000)
            assertEquals(expectedStudentTabTitle, tabTitle)
            if (tabTitle == expectedStudentTabTitle) {
                Log.e("MainActivityTest", "testTabTitle: Success with $tabTitle")
            } else {
                Log.e("MainActivityTest", "testTabTitle: Failed with $tabTitle")
            }
        }
    }

    @Test
    fun testCharactersTabTitle() {
        activityScenario.onActivity { activity ->
            val tabLayout = activity.findViewById<TabLayout>(R.id.tabLayout)
            Thread.sleep(1000)

            val tabTitle = tabLayout.getTabAt(2)?.text.toString()
            Thread.sleep(1000)
            assertEquals(expectedCharactersTabTitle, tabTitle)
            if (tabTitle == expectedCharactersTabTitle) {
                Log.e("MainActivityTest", "testTabTitle: Success with $tabTitle")
            } else {
                Log.e("MainActivityTest", "testTabTitle: Failed with $tabTitle")
            }
        }
    }
}

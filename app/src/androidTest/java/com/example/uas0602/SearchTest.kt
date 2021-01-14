package com.example.uas0602

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.example.uas0602.activity.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*



@RunWith(AndroidJUnit4::class)
class SearchTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testSearch() {

        // select league
        onView(withId(R.id.rvLiga))
            .perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))

        // select search
        onView(withId(R.id.action_search))
            .perform(click())

        // insert the query
        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("Liverpool"))
            .perform(pressImeActionButton())

        Thread.sleep(1000)

        onView(isRoot()).perform(pressBack())
    }
}
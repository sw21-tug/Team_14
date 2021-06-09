package com.example.tutorly

import android.content.Intent
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import androidx.test.rule.ActivityTestRule
import junit.framework.TestCase
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@SmallTest
class TutorProfileTest : TestCase() {

    @get:Rule
    var tutorProfileActivity : ActivityTestRule<TutorProfile> = ActivityTestRule<TutorProfile>(
        TutorProfile::class.java,
        true,
        false)

    @Before
    fun initIntent() {
        Intents.init()

        // change activity
        val intent = Intent()
        tutorProfileActivity.launchActivity(intent)
    }

    @After
    fun releaseIntent() {
        Intents.release()
    }

    @Test
    fun checkTitlesShown() {

        // check if titles visible
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorInfo))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtSubject))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkTitlesText() {

        // check if the text is right
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorInfo))
            .check(ViewAssertions.matches(ViewMatchers.withText("Tutor Information")))
        Espresso.onView(ViewMatchers.withId(R.id.txtSubject))
            .check(ViewAssertions.matches(ViewMatchers.withText("Subjects")))
    }

    @Test
    fun checkTutorInformationShown() {

        // check if tutor info visible
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorName))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorAge))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorGender))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorMail))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkTutorInformationText() {

        // check if right text gets displayed
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorName))
            .check(ViewAssertions.matches(ViewMatchers.withText("Name: ${TutorProfile.tutorName}")))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorAge))
            .check(ViewAssertions.matches(ViewMatchers.withText("Age: ${TutorProfile.tutorAge}")))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorGender))
            .check(ViewAssertions.matches(ViewMatchers.withText("Gender: ${TutorProfile.tutorGender}")))
        Espresso.onView(ViewMatchers.withId(R.id.txtTutorMail))
            .check(ViewAssertions.matches(ViewMatchers.withText("E-Mail: ${TutorProfile.tutorMail}")))
    }

    @Test
    fun checkImageViewShown() {

        // check if imageview is shown
        Espresso.onView(ViewMatchers.withId(R.id.tutorImage))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun checkListViewShown() {

        // check if listview exists
        Espresso.onView(ViewMatchers.withId(R.id.subjectslist))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


}
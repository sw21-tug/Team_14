package com.example.android.testing.espresso.BasicSample

import android.app.Activity
import android.content.Intent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.Intents.times
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.RootMatchers.withDecorView
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.tutorly.*
import org.hamcrest.Matchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.random.Random


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginRegisterTest {

    private lateinit var validEmailToBetyped: String
    private lateinit var invalidEmailToBetyped: String
    private lateinit var validPWToBetyped: String
    private lateinit var invalidPWToBetyped: String

    private lateinit var validEmailToBeregistered: String
    private lateinit var firstname: String
    private lateinit var lastname: String
    private lateinit var password: String
    private lateinit var confirm_pw: String
    private lateinit var confirm_pw_wrong: String


    @get:Rule

    var activityLogin: ActivityTestRule<LoginActivity> = ActivityTestRule<LoginActivity>(
            LoginActivity::class.java,
            true,
            false)

    var activityRegister: ActivityTestRule<RegisterActivity> = ActivityTestRule<RegisterActivity>(
            RegisterActivity::class.java,
            true,
            false)

    var loginActivityRule: ActivityScenarioRule<LoginActivity>
            = ActivityScenarioRule(LoginActivity::class.java)
    var registerActivityRule: ActivityScenarioRule<RegisterActivity>
            = ActivityScenarioRule(RegisterActivity::class.java)
    var ruleSwitchToMain =
        ActivityTestRule(MainActivity::class.java, true, false)
    var ruleSwitchToRegister =
        ActivityTestRule(RegisterActivity::class.java, true, false)
    var ruleSwitchToLogin =
        ActivityTestRule(LoginActivity::class.java, true, false)


    @Before
    fun init() {
        // init Intents
        Intents.init()
        // Specify a valid string.
        validEmailToBetyped = "1@2.4"
        invalidEmailToBetyped = "123@4"
        validPWToBetyped = "123456"
        invalidPWToBetyped = "123"

        validEmailToBeregistered = Random.nextInt(4, 1000000000).toString() +
         "@" + Random.nextInt(5, 1000000000).toString() + "." + Random.nextInt(6, 1000000000).toString()

        firstname = "John"
        lastname = "Doe"
        password = "123456"
        confirm_pw = "123456"
        confirm_pw_wrong = "1234567"
    }

    @After
    fun tearDown() {
        Intents.release()
    }

    @Test
    fun validLogin() {
        // Type text and then press the button.
        onView(withId(R.id.input_username_login))
            .perform(typeText(validEmailToBetyped), closeSoftKeyboard())
        onView(withId(R.id.input_password_login))
            .perform(typeText(validPWToBetyped), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        // Check that the intent was changed.
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name), times(2))
    }

    @Test
    fun invalidEmailLogin() {
        // Type text and then press the button.
        onView(withId(R.id.input_username_login))
            .perform(typeText(invalidEmailToBetyped), closeSoftKeyboard())
        onView(withId(R.id.input_password_login))
            .perform(typeText(validPWToBetyped), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        // Check that only once run
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun invalidPWLogin() {
        // Type text and then press the button.
        onView(withId(R.id.input_username_login))
            .perform(typeText(validEmailToBetyped), closeSoftKeyboard())
        onView(withId(R.id.input_password_login))
            .perform(typeText(invalidPWToBetyped), closeSoftKeyboard())
        onView(withId(R.id.btn_login)).perform(click())

        // Check that only once run
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun gotoRegisterclick() {

        activityRegister.launchActivity(Intent())
        onView(withId(R.id.txt_gotoRegister)).perform(click())

        // check
        ruleSwitchToRegister.launchActivity(Intent())
        intended(hasComponent(RegisterActivity::class.java.name), times(2))
    }

    @Test
    fun gotoRegistershown() {

        activityLogin.launchActivity(Intent())
        onView(withId(R.id.txt_gotoRegister)).check(matches(isDisplayed()))

    }

    @Test
    fun gotoRegistertext() {

        activityLogin.launchActivity(Intent())
        onView(withId(R.id.txt_gotoRegister)).check(matches(withText(R.string.register_account)))
    }

    @Test
    fun gotoLoginclick() {

        activityRegister.launchActivity(Intent())
        onView(withId(R.id.txt_gotoSignin)).perform(click())

        // check
        ruleSwitchToLogin.launchActivity(Intent())
        intended(hasComponent(LoginActivity::class.java.name), times(2))
    }

    @Test
    fun gotoLoginshown() {

        activityRegister.launchActivity(Intent())
        onView(withId(R.id.txt_gotoSignin)).check(matches(isDisplayed()))

    }

    @Test
    fun gotoLogintext() {

        activityRegister.launchActivity(Intent())
        onView(withId(R.id.txt_gotoSignin)).check(matches(withText(R.string.have_account)))
    }


    @Test
    fun validRegister() {
        onView(withId(R.id.txt_gotoRegister)).perform(click())
        // Type text and then press the button.
        onView(withId(R.id.username_reg))
            .perform(typeText(validEmailToBeregistered), closeSoftKeyboard())
        onView(withId(R.id.input_firstname))
            .perform(typeText(firstname), closeSoftKeyboard())
        onView(withId(R.id.input_lastname))
            .perform(typeText(lastname), closeSoftKeyboard())

        onView(withId(R.id.password_reg))
            .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.confirm_password_reg))
            .perform(typeText(confirm_pw), closeSoftKeyboard())

        onView(withId(R.id.btn_register)).perform(click())

        // Check that only once run
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name), times(2))
    }

    @Test
    fun invalidRegister() {
        onView(withId(R.id.txt_gotoRegister)).perform(click())
        // Type text and then press the button.
        onView(withId(R.id.username_reg))
            .perform(typeText(validEmailToBeregistered), closeSoftKeyboard())
        onView(withId(R.id.input_firstname))
            .perform(typeText(firstname), closeSoftKeyboard())
        onView(withId(R.id.input_lastname))
            .perform(typeText(lastname), closeSoftKeyboard())

        onView(withId(R.id.password_reg))
            .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.confirm_password_reg))
            .perform(typeText(confirm_pw_wrong), closeSoftKeyboard())

        onView(withId(R.id.btn_register)).perform(click())

        // Check that only once run
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
    }

    @Test
    fun invalidRegisterOnInvalidEmail() {
        onView(withId(R.id.txt_gotoRegister)).perform(click())
        // Type text and then press the button.
        onView(withId(R.id.username_reg))
            .perform(typeText(invalidEmailToBetyped), closeSoftKeyboard())
        onView(withId(R.id.input_firstname))
            .perform(typeText(firstname), closeSoftKeyboard())
        onView(withId(R.id.input_lastname))
            .perform(typeText(lastname), closeSoftKeyboard())

        onView(withId(R.id.password_reg))
            .perform(typeText(password), closeSoftKeyboard())
        onView(withId(R.id.confirm_password_reg))
            .perform(typeText(confirm_pw), closeSoftKeyboard())

        onView(withId(R.id.btn_register)).perform(click())

        // Check that only once run
        ruleSwitchToMain.launchActivity(Intent())
        intended(hasComponent(MainActivity::class.java.name))
    }





}
package com.quoders.apps.android.treepolis.ui.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.quoders.apps.android.treepolis.R;
import com.quoders.apps.android.treepolis.ui.signup.SignupActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by davidguerrerodiaz on 03/04/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class SingupTest {

    @Rule
    public ActivityTestRule<SignupActivity> mSignupActivityRule = new ActivityTestRule<>(SignupActivity.class);


    @Test
    public void testperformValidCompleteSignup() throws Exception {

        //  Fill up new user fields
        onView(withId(R.id.editTextCreateUserName)).perform(typeText("david_" + System.currentTimeMillis()));
        onView(withId(R.id.editTextCreateEmail)).perform(typeText("david.guerrero@quoders.com_" + + System.currentTimeMillis()));
        onView(withId(R.id.editTextCreatePassword)).perform(typeText("password"));
        onView(withId(R.id.editTextPasswordConfirm)).perform(typeText("password"));

        //  Click on Sign Up button
        onView(withId(R.id.editTextPasswordConfirm)).perform(click());

        //  Check progress dialog is showed
        onView(withText(R.string.signup_progress)).check(matches(isDisplayed()));

        //  Assert Home screen has been launched
        onView(withText(R.string.title_activity_home)).check(matches(isDisplayed()));
    }
}
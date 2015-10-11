package com.quoders.apps.android.treepolis.ui.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.quoders.apps.android.treepolis.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by davidguerrero on 11/08/15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class HomePresenterTest {

    @Rule
    public ActivityTestRule<HomeActivity> mHomeActivityRule = new ActivityTestRule<>(HomeActivity.class);


    @Test
    public void testOnCheckTreeClick() throws Exception {

        //  Test that after clicking the submit new tree button the checkin new tree activity is launched
        //onView(withId(R.id.buttonCheckTree)).perform(click());
        onView(withId(R.id.buttonSubmitTree)).check(matches(withText(("Submit"))));
    }


}
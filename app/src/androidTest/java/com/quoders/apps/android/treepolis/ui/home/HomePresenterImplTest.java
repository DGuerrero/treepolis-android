package com.quoders.apps.android.treepolis.ui.home;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.quoders.apps.android.treepolis.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by davidguerrero on 11/08/15.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest

public class HomePresenterImplTest {

    @Rule
    public ActivityTestRule<HomeActivity> mActivityRule = new ActivityTestRule<>(HomeActivity.class);

    @Test
    public void testOnCheckTree() throws Exception {
        onView(withId(R.id.buttonCheckTree)).perform(click());

    }
}
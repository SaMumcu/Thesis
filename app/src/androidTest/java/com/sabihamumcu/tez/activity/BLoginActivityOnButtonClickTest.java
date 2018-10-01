package com.sabihamumcu.tez.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.sabihamumcu.tez.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by sabis on 4/11/2018.
 */
public class BLoginActivityOnButtonClickTest {

    @Rule
    public ActivityTestRule<BLoginActivity> mBLoginActivityActivityTestRule
            = new ActivityTestRule<BLoginActivity>(BLoginActivity.class);

    BLoginActivity mBLoginActivity = null;

    Instrumentation.ActivityMonitor monitor = getInstrumentation().addMonitor(BRegisterActivity.class.getName(), null, false);

    @Before
    public void setUp() throws Exception {
        mBLoginActivity = mBLoginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunchOfSecondActvityOnButtonClick() {
        View view = mBLoginActivity.findViewById(R.id.btn_register);
        assertNotNull(view);
        onView(withId(R.id.btn_register)).perform(click());
        Activity secondActivity = getInstrumentation().waitForMonitorWithTimeout(monitor, 5000);
        assertNotNull(secondActivity);

    }

    @After
    public void tearDown() throws Exception {
        mBLoginActivity = null;
    }

}
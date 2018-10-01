package com.sabihamumcu.tez.test;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import com.sabihamumcu.tez.*;
import com.sabihamumcu.tez.R;
import com.sabihamumcu.tez.activity.BLoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by sabis on 4/11/2018.
 */
public class BLoginActivityTest {
    //activity launch test
    @Rule
    public ActivityTestRule<BLoginActivity> mBLoginActivityActivityTestRule
            = new ActivityTestRule<BLoginActivity>(BLoginActivity.class);

    BLoginActivity mBLoginActivity = null;

    @Before
    public void setUp() throws Exception {
        mBLoginActivity = mBLoginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch() {
        View view = mBLoginActivity.findViewById(R.id.topBar);
        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {
        mBLoginActivity = null;
    }

}
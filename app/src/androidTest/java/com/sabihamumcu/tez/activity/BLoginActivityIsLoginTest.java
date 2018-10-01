package com.sabihamumcu.tez.activity;

import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.sabihamumcu.tez.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by sabis on 4/11/2018.
 */
public class BLoginActivityIsLoginTest {

    @Rule
    public ActivityTestRule<BLoginActivity> mBLoginActivityActivityTestRule
            = new ActivityTestRule<BLoginActivity>(BLoginActivity.class);

    private String mail = "x.y@outlook.com";
    private String password = "12345678";
    BLoginActivity mBLoginActivity = null;

    @Before
    public void setUp() throws Exception {
        mBLoginActivity = mBLoginActivityActivityTestRule.getActivity();
    }

    @Test
    public void testUserInput() {
        //input
        Espresso.onView(withId(R.id.et_mail)).perform(typeText(mail));
        Espresso.onView(withId(R.id.et_password)).perform(typeText(password));
        Espresso.closeSoftKeyboard();
        Espresso.onView(withId(R.id.btn_login)).perform(click());
        Espresso.onView(withText(mBLoginActivity.getResources().getString(R.string.wrongLogInInputError))).inRoot(withDecorView(not((mBLoginActivity.getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {

    }

}
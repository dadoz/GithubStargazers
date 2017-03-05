package com.application.subitoit.githubstargazers;

import android.support.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RepoOwnerInputTest {
    @Rule
    public ActivityTestRule<MainActivity> mRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void ownerEmptyInputTest() {
        onView(withId(R.id.ownerTextInputLayoutId)).check(matches(null));//hasTextInputLayoutErrorText(mRule.getActivity().getString(R.string.app_name))));

    }

    @Test
    public void repoEmptyInputTest() {
        onView(withId(R.id.repoTextInputLayoutId))
                .perform(click())
                .check(matches(null));//hasTextInputLayoutErrorText(mRule.getActivity().getString(R.string.app_name))));
    }
}

package com.application.subitoit.githubstargazers;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.InstrumentationTestCase;

import com.application.subitoit.githubstargazers.managers.RetrofitManager;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.net.URL;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import retrofit2.Retrofit;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;


@RunWith(AndroidJUnit4.class)
public class StargazersRetrofitTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule;
    private MockWebServer server;

    public StargazersRetrofitTest() {
        mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);
    }

    @Before
    public void setUp() throws Exception {
        server = new MockWebServer();
        server.start();
        RetrofitManager.baseUrlEndpoint = server.url("/").url().toString();
//        injectInstrumentation(InstrumentationRegistry.getInstrumentation());
    }

    @Test
    public void testQuoteIsShown() throws Exception {
        String fileName = "quote_200_ok_response.json";
        server.enqueue(new MockResponse()
                .setResponseCode(200)
                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));

        Intent intent = new Intent();
        mActivityRule.launchActivity(intent);

        onView(withId(R.id.findButtonId)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
        onView(withText("I came from a ")).check(matches(isDisplayed()));
    }

//
//    @Test
//    public void testRetryButtonShowsWhenError() throws Exception {
//        String fileName = "quote_404_not_found.json";
//
//        server.enqueue(new MockResponse()
//                .setResponseCode(404)
//                .setBody(RestServiceTestHelper.getStringFromFile(getInstrumentation().getContext(), fileName)));
//
//        Intent intent = new Intent();
//        mActivityRule.launchActivity(intent);
//
//        onView(withId(R.id.findButtonId)).check(matches(isDisplayed()));
//        onView(withText("Quote Not found")).check(matches(isDisplayed()));
//    }

    @After
    public void tearDown() throws Exception {
        server.shutdown();
    }

    private static class RestServiceTestHelper {
        public static String getStringFromFile(Context context, String fileName) {
            return "blal";
        }
    }
}

package com.lazycouple.restapiclient.ui;

import android.app.FragmentManager;
import android.support.test.espresso.PerformException;
import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.util.HumanReadables;
import android.support.test.espresso.util.TreeIterables;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.lazycouple.restapiclient.MainActivity;
import com.lazycouple.restapiclient.R;
import com.lazycouple.restapiclient.consts.BasicConsts;

import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeoutException;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Created by noco on 2016-10-21.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class RestRequestFragmentTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private RestRequestFragment requestFragment;

    @Before
    public void setUp() throws Exception {

        MainActivity activity = activityTestRule.getActivity();
        FragmentManager fragmentManager = activity.getFragmentManager();
        requestFragment = (RestRequestFragment)fragmentManager.findFragmentById(R.id.content_frame);


    }

    @Test
    public void changeUrl() {


        onView(withId(R.id.et_input_url)).perform(ViewActions.clearText(), typeText(BasicConsts.GIT_USER_URL), closeSoftKeyboard());
//        onView(withId(R.id.bt_send)).perform(ViewActions.click());

        onView(isRoot()).perform(waitId(R.id.tv_response_body, 10000));

        onView(withId(R.id.tv_response_body)).check(matches(isDisplayed()));

    }

    /** Perform action of waiting for a specific view id. */
    public static ViewAction waitId(final int viewId, final long millis) {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> during " + millis + " millis.";
            }

            @Override
            public void perform(final UiController uiController, final View view) {
                uiController.loopMainThreadUntilIdle();
                final long startTime = System.currentTimeMillis();
                final long endTime = startTime + millis;
                final Matcher<View> viewMatcher = withId(viewId);

                do {
                    for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                        // found view with required ID
                        if (viewMatcher.matches(child)) {
                            return;
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50);
                }
                while (System.currentTimeMillis() < endTime);

                // timeout happens
                throw new PerformException.Builder()
                        .withActionDescription(this.getDescription())
                        .withViewDescription(HumanReadables.describe(view))
                        .withCause(new TimeoutException())
                        .build();
            }
        };
    }
}
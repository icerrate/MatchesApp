package com.icerrate.matches;

import android.content.Context;
import android.content.Intent;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import com.icerrate.matches.ui.MainActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author icerrate
 */
@RunWith(AndroidJUnit4.class)
public class MatchTest {

    private static final int WAIT_MILLIS = 3000;

    private final ActivityTestRule<MainActivity> myActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, false, false);

    @Before
    public void setup() {
        myActivityTestRule.launchActivity(
                new Intent(ApplicationProvider.getApplicationContext(), MainActivity.class)
        );
    }

    @Test
    public void testTabs() throws InterruptedException {
        //TODO: Should use Idling Resources
        Thread.sleep(WAIT_MILLIS);
        Context context = myActivityTestRule.getActivity().getApplicationContext();
        onView(withText(context.getString(R.string.tab_fixtures)))
                .check(matches(isDisplayed()));
        onView(withText(context.getString(R.string.tab_results)))
                .check(matches(isDisplayed()));
    }
}

package com.android.example.cookingrecipes.ui.activities;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import com.android.example.cookingrecipes.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeListActivityTest {
    @Rule
    public ActivityTestRule<RecipeListActivity> activityRule
            = new ActivityTestRule<>(RecipeListActivity.class);

    @Test
    public void onCreate() {
        onView(withId(R.id.item_list))
                .check(matches(hasChildCount(0)))
                .check(matches(isDisplayed()));
    }
}
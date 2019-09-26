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
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class RecipeDetailActivityTest {

    @Rule
    public ActivityTestRule<RecipeDetailActivity> activityRule
            = new ActivityTestRule<>(RecipeDetailActivity.class);

    @Test
    public void onCreate() {

        onView(withId(R.id.cv_recipe_info))
                .check(matches(hasDescendant(withId(R.id.tv_recipe_name))))
                .check(matches(hasDescendant(withId(R.id.tv_servings))))
                .check(matches(hasDescendant(withId(R.id.rv_ingredients))))
                .check(matches(isDisplayed()));

    }
}
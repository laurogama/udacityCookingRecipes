package com.android.example.cookingrecipes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.ui.fragments.RecipeDetailFragment;

public class RecipeDetailActivity extends AppCompatActivity {
    RecipeDetailFragment fragment;
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);

        if (findViewById(R.id.recipe_info_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putInt(RecipeDetailFragment.ARG_ITEM_ID,
                    getIntent().getIntExtra(RecipeDetailFragment.ARG_ITEM_ID, -1));
            fragment = new RecipeDetailFragment();
            fragment.setArguments(arguments);
        } else {
            fragment = (RecipeDetailFragment) getSupportFragmentManager()
                    .getFragment(savedInstanceState, RecipeDetailFragment.ARG_ITEM_ID);
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.recipe_info_container, fragment)
                .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, RecipeListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    private void showStep(int position) {
//        if (mTwoPane) {
//            Bundle arguments = new Bundle();
//            arguments.putParcelable(StepDetailFragment.STEP_KEY, mRecipe.getSteps().get(position));
//            RecipeStepDetailFragment fragment = new RecipeStepDetailFragment();
//            fragment.setArguments(arguments);
//            getSupportFragmentManager().beginTransaction()
//                    .replace(R.id.recipe_step_detail_container, fragment)
//                    .commit();
//        } else {
//            Intent intent = new Intent(this, RecipeStepDetailActivity.class);
//            intent.putExtra(RecipeStepDetailActivity.RECIPE_KEY, mRecipe);
//            intent.putExtra(RecipeStepDetailActivity.STEP_SELECTED_KEY, position);
//            startActivity(intent);
//        }
//    }
}

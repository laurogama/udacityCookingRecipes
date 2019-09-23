package com.android.example.cookingrecipes.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.ui.fragments.RecipeDetailFragment;
import com.android.example.cookingrecipes.ui.viewmodel.RecipeDetailViewModel;

public class RecipeDetailActivity extends AppCompatActivity {
    RecipeDetailFragment fragment;
    private RecipeDetailViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_info);
        mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
        mViewModel.setRecipe(getIntent().getIntExtra(RecipeDetailFragment.ARG_ITEM_ID, -1));
        mViewModel.setTwoPane(findViewById(R.id.step_detail_container) != null);
        if (savedInstanceState == null) {
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
}

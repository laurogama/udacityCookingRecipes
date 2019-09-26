package com.android.example.cookingrecipes.ui.activities;

import android.os.Bundle;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.ui.adapters.RecipeRecyclerViewAdapter;
import com.android.example.cookingrecipes.ui.viewmodel.RecipeListViewModel;

import java.util.List;

/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link RecipeDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class RecipeListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        recyclerView = findViewById(R.id.item_list);
        /**
         * Whether or not the activity is in two-pane mode, i.e. running on a tablet
         * device.
         */
        RecipeListViewModel mRecipeListViewModel = ViewModelProviders.of(this).get(RecipeListViewModel.class);
        mRecipeListViewModel.getRecipes().observe(this, this::onRecipesChanged);
    }

    private void onRecipesChanged(List<Recipe> recipes) {
        setupRecyclerView(recyclerView, recipes);
    }

    public int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int scalingFactor = 200;
        int noOfColumns = (int) (dpWidth / scalingFactor);
        if (noOfColumns < 2)
            noOfColumns = 2;
        return noOfColumns;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView, List<Recipe> recipes) {
        recyclerView.setLayoutManager(new GridLayoutManager(this, calculateNoOfColumns()));
        recyclerView.setAdapter(new RecipeRecyclerViewAdapter(recipes));
    }
}
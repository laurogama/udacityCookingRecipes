package com.android.example.cookingrecipes.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.ui.activities.RecipeDetailActivity;
import com.android.example.cookingrecipes.ui.activities.RecipeListActivity;
import com.android.example.cookingrecipes.ui.adapters.IngredientsAdapter;
import com.android.example.cookingrecipes.ui.adapters.StepsAdapter;
import com.android.example.cookingrecipes.ui.viewmodel.RecipeDetailViewModel;

import static com.android.example.cookingrecipes.ui.activities.RecipeListActivity.TWO_PANE;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment {
    /**
     * The fragment argument representing the recipe ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "recipe_detail_frag";

    private Recipe mRecipe;
    private RecipeDetailViewModel mViewModel;
    private RecyclerView mRecyclerViewIngredients;
    private RecyclerView mRecyclerViewSteps;
    private boolean mTwoPane;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public RecipeDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null && getArguments().containsKey(ARG_ITEM_ID)) {
            mViewModel = ViewModelProviders.of(this).get(RecipeDetailViewModel.class);
            mRecipe = mViewModel.getRecipe(getArguments().getInt(ARG_ITEM_ID, -1));
            mTwoPane = getArguments().getBoolean(TWO_PANE, false);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.recipe_detail, container, false);

        if (mRecipe != null) {
            ((TextView) rootView.findViewById(R.id.tv_recipe_name)).setText(mRecipe.getName());
            mRecyclerViewIngredients = rootView.findViewById(R.id.rv_ingredients);
            mRecyclerViewIngredients.setAdapter(new IngredientsAdapter(mRecipe.getIngredients()));

            mRecyclerViewSteps = rootView.findViewById(R.id.rv_steps);
            mRecyclerViewSteps.setAdapter(new StepsAdapter(mRecipe.getSteps(), mTwoPane));


        }
        return rootView;
    }
}

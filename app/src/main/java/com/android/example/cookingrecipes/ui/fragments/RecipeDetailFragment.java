package com.android.example.cookingrecipes.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.databinding.RecipeDetailBinding;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.repository.models.Step;
import com.android.example.cookingrecipes.ui.activities.RecipeDetailActivity;
import com.android.example.cookingrecipes.ui.activities.RecipeListActivity;
import com.android.example.cookingrecipes.ui.adapters.IngredientsAdapter;
import com.android.example.cookingrecipes.ui.adapters.StepsAdapter;
import com.android.example.cookingrecipes.ui.listeners.StepClickListener;
import com.android.example.cookingrecipes.ui.viewmodel.RecipeDetailViewModel;

/**
 * A fragment representing a single Recipe detail screen.
 * This fragment is either contained in a {@link RecipeListActivity}
 * in two-pane mode (on tablets) or a {@link RecipeDetailActivity}
 * on handsets.
 */
public class RecipeDetailFragment extends Fragment implements StepClickListener {
    /**
     * The fragment argument representing the recipe ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "recipe_detail_frag";

    private Recipe mRecipe;
    private RecipeDetailViewModel mViewModel;
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
            mTwoPane = getActivity().findViewById(R.id.step_detail_container) != null;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecipeDetailBinding binding = RecipeDetailBinding.inflate(inflater, container, false);
        binding.setRecipe(mRecipe);
        View rootView = binding.getRoot();

        RecyclerView mRecyclerViewIngredients = binding.rvIngredients;
        mRecyclerViewIngredients.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerViewIngredients.setHasFixedSize(true);
        mRecyclerViewIngredients.setAdapter(new IngredientsAdapter(mRecipe.getIngredients()));

        RecyclerView mRecyclerViewSteps = binding.rvSteps;
        mRecyclerViewSteps.setLayoutManager(new LinearLayoutManager(this.getContext()));
        mRecyclerViewSteps.setHasFixedSize(true);
        mRecyclerViewSteps.setAdapter(new StepsAdapter(mRecipe.getSteps(), mTwoPane, this));

        return rootView;
    }

    @Override
    public void OnItemClick(Step step) {
        mViewModel.onStepClicked(step);
        StepDetailFragment stepDetailFragment = new StepDetailFragment();
        if (mTwoPane) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.step_detail_container, stepDetailFragment).commit();
        } else {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.recipe_info_container, stepDetailFragment).addToBackStack(null).commit();
        }
    }
}

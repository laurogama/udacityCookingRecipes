package com.android.example.cookingrecipes.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.repository.models.Step;

public class RecipeDetailViewModel extends ViewModel {
    public Recipe mRecipe;

    public RecipeDetailViewModel() {
    }

    public Recipe getRecipe(Integer id) {
        mRecipe = Repository.getsInstance().getRecipe(id);
        return mRecipe;
    }

    public void onStepClicked(Step step) {
        Log.d(RecipeDetailViewModel.class.getSimpleName(), String.format("Step %d clicked", step.getId()));
        Repository.getsInstance().setSelectedStep(step);
    }

    public Step getStep() {
        return Repository.getsInstance().getSelectedStep();
    }
}

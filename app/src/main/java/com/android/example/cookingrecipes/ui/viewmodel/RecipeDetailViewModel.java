package com.android.example.cookingrecipes.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.repository.models.Step;

public class RecipeDetailViewModel extends ViewModel {
    private static final String TAG = RecipeDetailViewModel.class.getSimpleName();
    private Recipe mRecipe;
    private boolean mTwoPane;


    public RecipeDetailViewModel() {
    }

    public Recipe getRecipe() {
        return mRecipe;
    }

    public void setRecipe(Integer id) {
        mRecipe = Repository.getsInstance().getRecipe(id);
    }

    public void onStepClicked(Step step) {
        Log.d(RecipeDetailViewModel.class.getSimpleName(), String.format("Step %d clicked", step.getId()));
        Repository.getsInstance().setSelectedStep(step);
    }

    public Step getStep() {
        return Repository.getsInstance().getSelectedStep();
    }

    public boolean isTwopane() {
        return mTwoPane;
    }

    public void setTwoPane(boolean isTwoPane) {
        mTwoPane = isTwoPane;
    }

    public void nextStep(int id) {
        Log.d(TAG, "nextStep: " + id);
    }

    public void previousStep(int id) {
        Log.d(TAG, "previousStep: " + id);
    }
}

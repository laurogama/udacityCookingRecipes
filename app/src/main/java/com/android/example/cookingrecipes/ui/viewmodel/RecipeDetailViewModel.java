package com.android.example.cookingrecipes.ui.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.repository.models.Step;

public class RecipeDetailViewModel extends ViewModel {
    private static final String TAG = RecipeDetailViewModel.class.getSimpleName();
    private boolean mTwoPane;
    private int stepId;


    public RecipeDetailViewModel() {
    }

    public LiveData<Recipe> getRecipe() {
        return Repository.getsInstance().getRecipe();
    }

    public void setRecipe(Integer id) {
        Repository.getsInstance().setRecipe(id);
        stepId = 0;
    }

    public void onStepClicked(Step step) {
        Log.d(RecipeDetailViewModel.class.getSimpleName(), String.format("Step %d clicked",
                step.getId()));
        stepId = step.getId();
        Repository.getsInstance().setSelectedStep(step);
    }

    public LiveData<Step> getStep() {
        return Repository.getsInstance().getSelectedStep();
    }

    public boolean isTwopane() {
        return mTwoPane;
    }

    public void setTwoPane(boolean isTwoPane) {
        mTwoPane = isTwoPane;
    }

    public void nextStep() {
        Log.d(TAG, "nextStep:");
        int newStepId = stepId + 1;
        if (getRecipe().getValue() != null) {
            for (Step nextStep : getRecipe().getValue().getSteps()) {
                if (nextStep.getId().equals(newStepId)) {
                    stepId = newStepId;
                    Repository.getsInstance().setSelectedStep(nextStep);
                }
            }
        }
    }

    public void previousStep() {
        Log.d(TAG, "previousStep:");
        int newStepId = stepId - 1;
        if (getRecipe().getValue() != null) {
            for (Step nextStep : getRecipe().getValue().getSteps()) {
                if (nextStep.getId().equals(newStepId)) {
                    stepId = newStepId;
                    Repository.getsInstance().setSelectedStep(nextStep);
                }
            }
        }
    }
}

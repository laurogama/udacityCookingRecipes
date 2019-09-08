package com.android.example.cookingrecipes.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;

public class RecipeDetailViewModel extends ViewModel {
    private Recipe mRecipe;

    public RecipeDetailViewModel() {

    }

    public Recipe getRecipe(Integer id) {
        mRecipe = Repository.getsInstance().getRecipe(id);
        return mRecipe;
    }
}

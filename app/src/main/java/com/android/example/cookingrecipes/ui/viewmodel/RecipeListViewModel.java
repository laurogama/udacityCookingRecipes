package com.android.example.cookingrecipes.ui.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;

import java.util.List;

public class RecipeListViewModel extends ViewModel {
    private LiveData<List<Recipe>> recipes;

    public RecipeListViewModel() {
        recipes = Repository.getsInstance().getRecipes();
    }

    public LiveData<List<Recipe>> getRecipes() {
        return recipes;
    }
}

package com.android.example.cookingrecipes.repository;

import com.android.example.cookingrecipes.repository.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

    String RECIPE_BASE_URL = "https://go.udacity.com/";

    @GET("android-baking-app-json/")
    Call<List<Recipe>> getRecipes();
}

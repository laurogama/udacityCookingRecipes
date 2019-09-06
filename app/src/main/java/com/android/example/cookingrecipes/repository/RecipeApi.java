package com.android.example.cookingrecipes.repository;

import com.android.example.cookingrecipes.repository.models.Recipe;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RecipeApi {

//    String RECIPE_BASE_URL = "http://go.udacity.com/";
    String RECIPE_BASE_URL = "https://d17h27t6h515a5.cloudfront.net";

//    @GET("android-baking-app-json/")
    @GET("topher/2017/May/59121517_baking/baking.json")
    Call<List<Recipe>> getRecipes();
}

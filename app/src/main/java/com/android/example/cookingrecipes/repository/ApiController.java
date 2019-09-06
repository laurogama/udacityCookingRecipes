package com.android.example.cookingrecipes.repository;

import com.android.example.cookingrecipes.repository.models.Recipe;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.android.example.cookingrecipes.repository.RecipeApi.RECIPE_BASE_URL;

public class ApiController {

    public void loadRecipes(Callback<List<Recipe>> callback) {
        Call<List<Recipe>> call = load().getRecipes();
        call.enqueue(callback);
    }

    private RecipeApi load() {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RECIPE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(RecipeApi.class);
    }
}

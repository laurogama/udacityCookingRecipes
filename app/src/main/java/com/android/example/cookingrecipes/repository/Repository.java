package com.android.example.cookingrecipes.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.repository.models.Step;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository {
    private static Repository sInstance;
    private MutableLiveData<List<Recipe>> mRecipes;
    private MutableLiveData<Step> mStep;
    private ApiController mApiController;
    private MutableLiveData<Recipe> mCurrentRecipe = new MutableLiveData<>();

    private Repository() {
        mApiController = new ApiController();
        mRecipes = new MutableLiveData<>();
        mStep = new MutableLiveData<>();
    }

    public static Repository getsInstance() {
        if (sInstance == null) {
            sInstance = new Repository();
        }
        return sInstance;
    }

    public MutableLiveData<List<Recipe>> getRecipes() {
        mApiController.loadRecipes(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                if (response.isSuccessful()) {
                    mRecipes.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {
                t.printStackTrace();
                Log.d(Repository.class.getSimpleName(), call.request().url().toString());
            }
        });
        return mRecipes;
    }

    private Recipe findRecipe(Integer id) {
        if (mRecipes.getValue() != null) {
            return mRecipes.getValue().stream().parallel()
                    .filter(rec -> rec.getId().equals(id))
                    .findFirst().orElse(null);
        }
        return null;
    }

    public MutableLiveData<Step> getSelectedStep() {
        return mStep;
    }

    public void setSelectedStep(Step step) {
        mStep.postValue(step);
    }

    public LiveData<Recipe> getRecipe() {
        return mCurrentRecipe;
    }

    public void setRecipe(Integer id) {
        mCurrentRecipe.setValue(findRecipe(id));
    }

}

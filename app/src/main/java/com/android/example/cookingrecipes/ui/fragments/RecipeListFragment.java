package com.android.example.cookingrecipes.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.ui.listeners.RecipeClickListener;

public class RecipeListFragment extends Fragment implements RecipeClickListener {
    protected RecyclerView recipesRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void OnItemClick(Recipe recipe) {

    }
}

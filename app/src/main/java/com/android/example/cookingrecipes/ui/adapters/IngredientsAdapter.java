package com.android.example.cookingrecipes.ui.adapters;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.repository.models.Ingredients;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter {
    private List<Ingredients> mIngredients;

    public IngredientsAdapter(List<Ingredients> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

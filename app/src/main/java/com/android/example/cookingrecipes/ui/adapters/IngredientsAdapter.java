package com.android.example.cookingrecipes.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.databinding.IngredientListItemBinding;
import com.android.example.cookingrecipes.repository.models.Ingredient;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientViewHolder> {
    private List<Ingredient> mIngredients;

    public IngredientsAdapter(List<Ingredient> ingredients) {
        mIngredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        IngredientListItemBinding ingredientBinding =
                IngredientListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new IngredientViewHolder(ingredientBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        Ingredient ingredient = mIngredients.get(position);
        holder.listItemBinding.setIngredient(ingredient);
    }

    @Override
    public int getItemCount() {
        return mIngredients != null ? mIngredients.size() : 0;
    }

    class IngredientViewHolder extends RecyclerView.ViewHolder {
        private IngredientListItemBinding listItemBinding;

        IngredientViewHolder(IngredientListItemBinding ingredientBinding) {
            super(ingredientBinding.getRoot());
            this.listItemBinding = ingredientBinding;
        }
    }
}

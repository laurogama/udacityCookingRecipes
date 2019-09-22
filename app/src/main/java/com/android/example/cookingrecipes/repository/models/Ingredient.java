package com.android.example.cookingrecipes.repository.models;

import com.google.gson.annotations.SerializedName;

public class Ingredient {
    String measure;
    Float quantity;
    @SerializedName("ingredient")
    String ingredientName;

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }
}

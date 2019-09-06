package com.android.example.cookingrecipes.repository.models;

import androidx.room.PrimaryKey;

public class Steps {
    @PrimaryKey
    Integer id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;
}

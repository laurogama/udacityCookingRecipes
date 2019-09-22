package com.android.example.cookingrecipes.repository.models;

import androidx.room.PrimaryKey;

public class Step {
    @PrimaryKey
    Integer id;
    String shortDescription;
    String description;
    String videoURL;
    String thumbnailURL;

    public Integer getId() {
        return id;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }
}

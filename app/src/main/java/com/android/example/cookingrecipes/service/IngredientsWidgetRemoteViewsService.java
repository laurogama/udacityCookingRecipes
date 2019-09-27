package com.android.example.cookingrecipes.service;

import android.content.Intent;
import android.widget.RemoteViewsService;

import com.android.example.cookingrecipes.provider.IngredientsListWidgetRemoteViewsFactory;

public class IngredientsWidgetRemoteViewsService extends RemoteViewsService {
    @Override
    public RemoteViewsFactory onGetViewFactory(Intent intent) {
        return new IngredientsListWidgetRemoteViewsFactory(this.getApplicationContext(), intent);
    }
}

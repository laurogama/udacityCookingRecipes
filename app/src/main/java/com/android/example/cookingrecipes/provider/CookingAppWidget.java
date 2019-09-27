package com.android.example.cookingrecipes.provider;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.service.IngredientsWidgetRemoteViewsService;
import com.android.example.cookingrecipes.ui.activities.RecipeDetailActivity;

import java.util.Objects;

/**
 * Implementation of App Widget functionality.
 */
public class CookingAppWidget extends AppWidgetProvider {

    void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                         int appWidgetId) {

        RemoteViews views = prepareListView(context);
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    private RemoteViews prepareListView(Context context) {
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.cooking_app_widget);
        Intent intent = new Intent(context, IngredientsWidgetRemoteViewsService.class);
        views.setRemoteAdapter(R.id.rv_widget_ingredients, intent);

        Intent appIntent = new Intent(context, RecipeDetailActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, appIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        views.setPendingIntentTemplate(R.id.rv_widget_ingredients, pendingIntent);

        return views;
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        for (int appWidgetId : appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);

        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent != null && Objects.equals("android.appwidget.action.APPWIDGET_UPDATE",
                intent.getAction())) {
            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
            ComponentName componentName = new ComponentName(context.getApplicationContext(),
                    CookingAppWidget.class);
            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
            RemoteViews remoteViews = prepareListView(context);
            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
            appWidgetManager.notifyAppWidgetViewDataChanged(
                    appWidgetManager.getAppWidgetIds(componentName),
                    R.id.rv_widget_ingredients);
            onUpdate(context, appWidgetManager, appWidgetIds);
        }
        super.onReceive(context, intent);
    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}


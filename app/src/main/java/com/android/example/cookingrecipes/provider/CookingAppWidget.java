package com.android.example.cookingrecipes.provider;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.service.IngredientsWidgetRemoteViewsService;

/**
 * Implementation of App Widget functionality.
 */
public class CookingAppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        // There may be multiple widgets active, so update all of them
        Recipe recipe = Repository.getsInstance().getRecipe().getValue();
        if (recipe != null) {
            for (int appWidgetId : appWidgetIds) {
                RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.cooking_app_widget);

                remoteViews.setTextViewText(R.id.recipeTitle, recipe.getName());
                remoteViews.setRemoteAdapter(R.id.rv_widget_ingredients,
                        new Intent(context, IngredientsWidgetRemoteViewsService.class));
                appWidgetManager.updateAppWidget(appWidgetId, remoteViews);
            }
        }
//        super.onUpdate(context, appWidgetManager, appWidgetIds);
    }

//    @Override
//    public void onReceive(Context context, Intent intent) {
//        if (intent != null && Objects.equals("android.appwidget.action.APPWIDGET_UPDATE",
//                intent.getAction())) {
//            AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
//            ComponentName componentName = new ComponentName(context.getApplicationContext(),
//                    CookingAppWidget.class);
//            int[] appWidgetIds = appWidgetManager.getAppWidgetIds(componentName);
//            appWidgetManager.notifyAppWidgetViewDataChanged(appWidgetIds,
//                    R.id.rv_widget_ingredients);
//
////            Recipe recipe = Repository.getsInstance().getRecipe().getValue();
////            RemoteViews remoteViews = prepareListView(context, recipe);
////            appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
//        }
//        super.onReceive(context, intent);
//    }
}


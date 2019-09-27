package com.android.example.cookingrecipes.provider;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.models.Ingredient;

import java.util.ArrayList;
import java.util.Locale;

public class IngredientsListWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private ArrayList<Ingredient> listIngredients = new ArrayList<>();
    private int appWidgetId;

    public IngredientsListWidgetRemoteViewsFactory(Context mContext, Intent intent) {
        this.mContext = mContext;
        appWidgetId = intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,
                AppWidgetManager.INVALID_APPWIDGET_ID);
    }


    @Override
    public void onCreate() {

    }


    @Override
    public void onDataSetChanged() {

//        final long identityToken = Binder.clearCallingIdentity();
//        Uri uri = Contract.PATH_TODOS_URI;
//        mCursor = mContext.getContentResolver().query(uri,
//                null,
//                null,
//                null,
//                Contract._ID + " DESC");

//        Binder.restoreCallingIdentity(identityToken);
    }

    @Override
    public void onDestroy() {
        listIngredients = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return listIngredients.size();
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                listIngredients.isEmpty()) {
            return null;
        }
        try {
            Ingredient ingredient = listIngredients.get(position);
            RemoteViews rv = new RemoteViews(mContext.getPackageName(),
                    R.layout.ingredient_list_item);
            rv.setTextViewText(R.id.tv_ingredient_name,
                    String.format(Locale.ENGLISH, "%.2fx%s %s", ingredient.getQuantity(),
                            ingredient.getMeasure(), ingredient.getIngredientName()));
            return rv;
        } catch (IndexOutOfBoundsException ex) {
            return null;
        }

    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }
}

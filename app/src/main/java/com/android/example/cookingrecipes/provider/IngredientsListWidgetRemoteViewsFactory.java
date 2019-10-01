package com.android.example.cookingrecipes.provider;

import android.content.Context;
import android.os.Binder;
import android.widget.AdapterView;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Ingredient;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;

public class IngredientsListWidgetRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {
    private Context mContext;
    private ArrayList<Ingredient> mListIngredients = new ArrayList<>();

    public IngredientsListWidgetRemoteViewsFactory(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public void onCreate() {

    }


    @Override
    public void onDataSetChanged() {
        final long identityToken = Binder.clearCallingIdentity();
        mListIngredients = (ArrayList<Ingredient>) Objects.requireNonNull(Repository.getsInstance()
                .getRecipe().getValue()).getIngredients();
        Binder.restoreCallingIdentity(identityToken);

    }

    @Override
    public void onDestroy() {
        mListIngredients = null;
    }

    @Override
    public int getCount() {
        return mListIngredients != null ? mListIngredients.size() : 0;
    }

    @Override
    public RemoteViews getViewAt(int position) {
        if (position == AdapterView.INVALID_POSITION ||
                mListIngredients.isEmpty()) {
            return null;
        }
        try {
            Ingredient ingredient = mListIngredients.get(position);
            RemoteViews rv = new RemoteViews(mContext.getPackageName(),
                    R.layout.widget_ingredient_list_item);
            rv.setTextViewText(R.id.tv_widget_item,
                    String.format(Locale.ENGLISH, "%.2fx%s %s", ingredient.getQuantity(),
                            ingredient.getMeasure(), ingredient.getIngredientName()));
            return rv;
        } catch (IndexOutOfBoundsException ex) {
            ex.printStackTrace();
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

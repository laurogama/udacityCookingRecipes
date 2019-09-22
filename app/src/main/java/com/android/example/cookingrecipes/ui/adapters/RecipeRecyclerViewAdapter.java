package com.android.example.cookingrecipes.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.models.Recipe;
import com.android.example.cookingrecipes.ui.activities.RecipeDetailActivity;
import com.android.example.cookingrecipes.ui.activities.RecipeListActivity;
import com.android.example.cookingrecipes.ui.fragments.RecipeDetailFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeRecyclerViewAdapter
        extends RecyclerView.Adapter<RecipeRecyclerViewAdapter.ViewHolder> {

    private final RecipeListActivity mParentActivity;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mTwoPane) {
                Bundle arguments = new Bundle();

                arguments.putInt(RecipeDetailFragment.ARG_ITEM_ID, (Integer) view.getTag());
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, (Integer) view.getTag());
                context.startActivity(intent);
            }
        }
    };
    private List<Recipe> mValues;

    public RecipeRecyclerViewAdapter(RecipeListActivity parent, List<Recipe> recipes,
                                     boolean twoPane) {
        mParentActivity = parent;
        mTwoPane = twoPane;
        mValues = recipes;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mRecipeName.setText(mValues.get(position).getName());
        int imageWidthPixels = 50;
        int imageHeightPixels = 100;
        if (!mValues.get(position).getImage().equals("")) {
            Picasso.get().load(mValues.get(position).getImage()).resize(imageWidthPixels,
                    imageHeightPixels).error(R.drawable.recipe).placeholder(R.drawable.recipe).into(holder.mRecipePicture);
        }
        holder.itemView.setTag(mValues.get(position).getId());
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mRecipeName;
        final ImageView mRecipePicture;

        ViewHolder(View view) {
            super(view);
            mRecipeName = view.findViewById(R.id.tv_recipe_name);
            mRecipePicture = view.findViewById(R.id.iv_recipe);
        }
    }
}

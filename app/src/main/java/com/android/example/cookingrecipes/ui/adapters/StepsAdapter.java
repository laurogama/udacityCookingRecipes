package com.android.example.cookingrecipes.ui.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.R;
import com.android.example.cookingrecipes.repository.models.Steps;
import com.android.example.cookingrecipes.ui.activities.RecipeDetailActivity;
import com.android.example.cookingrecipes.ui.fragments.StepDetailFragment;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            if (mTwoPane) {
                Bundle arguments = new Bundle();

                arguments.putInt(StepDetailFragment.ARG_ITEM_ID, (Integer) view.getTag());
                StepDetailFragment fragment = new StepDetailFragment();
                fragment.setArguments(arguments);

            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, RecipeDetailActivity.class);
                intent.putExtra(StepDetailFragment.ARG_ITEM_ID, (Integer) view.getTag());
                context.startActivity(intent);
            }
        }
    };
    private List<Steps> mSteps;

    public StepsAdapter(List<Steps> steps, boolean twoPane) {

        mSteps = steps;
        mTwoPane = twoPane;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_step, parent, false);
        return new StepsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        holder

    }

    @Override
    public int getItemCount() {
        return mSteps != null ? mSteps.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

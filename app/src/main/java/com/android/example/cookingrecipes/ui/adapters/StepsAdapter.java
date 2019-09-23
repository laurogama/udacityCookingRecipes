package com.android.example.cookingrecipes.ui.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.cookingrecipes.databinding.StepListItemBinding;
import com.android.example.cookingrecipes.repository.models.Step;
import com.android.example.cookingrecipes.ui.listeners.StepClickListener;

import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.ViewHolder> {

    private StepClickListener mClickListener;
    private List<Step> mSteps;

    public StepsAdapter(List<Step> steps, StepClickListener clickListener) {
        mSteps = steps;
        mClickListener = clickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        StepListItemBinding binding = StepListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new StepsAdapter.ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Step step = mSteps.get(position);
        holder.binding.setStep(step);
        holder.binding.getRoot().setOnClickListener(v -> {
            if (mClickListener != null) {
                mClickListener.OnItemClick(step);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mSteps != null ? mSteps.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private StepListItemBinding binding;

        ViewHolder(@NonNull StepListItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}

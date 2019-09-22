package com.android.example.cookingrecipes.ui.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.android.example.cookingrecipes.databinding.StepDetailBinding;
import com.android.example.cookingrecipes.repository.models.Step;
import com.android.example.cookingrecipes.ui.viewmodel.StepDetailViewModel;

public class StepDetailFragment extends Fragment {

    public static final String STEP_KEY = "step_id";
    private StepDetailViewModel mViewModel;
    private StepDetailBinding binding;
    private Step mStep;

    public StepDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StepDetailViewModel.class);
        Log.d(StepDetailFragment.class.getSimpleName(), "onCreate");
    }

    private void onStepChanged(Step step) {
        Log.d(StepDetailFragment.class.getSimpleName(), "onStepChanged");
        mStep = step;
        binding.setStep(mStep);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        Log.d(StepDetailFragment.class.getSimpleName(), "onCreateView");
        binding = StepDetailBinding.inflate(inflater, container, false);
        Log.d(StepDetailFragment.class.getSimpleName(), String.format("Step:%s", mViewModel.getStep().getDescription()));
        binding.setStep(mViewModel.getStep());
        Log.d(StepDetailFragment.class.getSimpleName(), String.format("Activity:%s", getActivity().getClass().getSimpleName()));
        return binding.getRoot();
    }
}

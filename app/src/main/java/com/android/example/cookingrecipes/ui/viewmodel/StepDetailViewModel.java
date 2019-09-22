package com.android.example.cookingrecipes.ui.viewmodel;

import androidx.lifecycle.ViewModel;

import com.android.example.cookingrecipes.repository.Repository;
import com.android.example.cookingrecipes.repository.models.Step;

public class StepDetailViewModel extends ViewModel {

    public Step getStep() {
        return Repository.getsInstance().getSelectedStep();
    }
}

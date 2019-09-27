package com.android.example.cookingrecipes.ui.fragments;

import android.net.Uri;
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
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class StepDetailFragment extends Fragment {

    public static final String STEP_KEY = "step_id";
    private StepDetailViewModel mViewModel;
    private StepDetailBinding binding;
    private Step mStep;
    private SimpleExoPlayer mExoPlayer;

    public StepDetailFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(StepDetailViewModel.class);
        Log.d(StepDetailFragment.class.getSimpleName(), "onCreate");

    }

    /**
     * Initialize ExoPlayer.
     */
    private void initializePlayer(Step step) {
        Uri mediaUri = Uri.parse(step.getVideoURL());

        if (mExoPlayer == null) {
            mExoPlayer = ExoPlayerFactory.newSimpleInstance(this.getContext());
            // Create an instance of the ExoPlayer.
            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this.getContext(),
                    Util.getUserAgent(this.getContext(), this.getActivity().getApplication().getPackageName()));
            // This is the MediaSource representing the media to be played.
            MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(mediaUri);
            // Prepare the player with the source.
            mExoPlayer.prepare(videoSource);
        }
    }

    /**
     * Release ExoPlayer.
     */
    private void releasePlayer() {
        mExoPlayer.stop();
        mExoPlayer.release();
        mExoPlayer = null;
    }

    /**
     * Release the player when the activity is destroyed.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        releasePlayer();
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
        binding = StepDetailBinding.inflate(inflater, container, false);
        binding.setStep(mViewModel.getStep());
        initializePlayer(mViewModel.getStep());
        binding.playerView.setPlayer(mExoPlayer);
        return binding.getRoot();
    }
}

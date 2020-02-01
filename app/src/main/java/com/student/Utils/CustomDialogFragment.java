package com.student.Utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.load.engine.Resource;
import com.student.models.ProfileData;
import com.student.rentals.R;
import com.student.rentals.ui.fragments.view_fragment.ViewItemViewModel;

import timber.log.Timber;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 1/28/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
public class CustomDialogFragment extends DialogFragment {
    private Resources resources = getActivity().getResources();
    private ViewItemViewModel viewModel;

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        Timber.d(resources.getString(R.string.on_attach));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if (savedInstanceState != null) {

        }


        return inflater.inflate(R.layout.item_property_popup_profile, container, false);
    }


    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ViewItemViewModel.class);
    }



    @Override
    public void onStart() {
        super.onStart();
        Timber.d(resources.getString(R.string.on_start));
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d(resources.getString(R.string.on_stop));
    }


    @Override
    public void onResume() {
        super.onResume();
        Timber.d(resources.getString(R.string.on_resume));
        viewModel.getProfileData(1).observe(getViewLifecycleOwner(), new Observer<ProfileData>() {
            @Override
            public void onChanged(ProfileData profileData) {
                Timber.d(profileData.toString());
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onViewStateRestored(@Nullable final Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
    }
}

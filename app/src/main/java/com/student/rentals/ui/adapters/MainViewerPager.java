package com.student.rentals.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.student.rentals.ui.fragments.list_apartments.ApparmentsFragment;
import com.student.rentals.ui.fragments.list_fragment.ListItemsFragment;
import com.student.rentals.ui.fragments.list_fragment.NotifiactionsFragment;
import com.student.rentals.ui.fragments.profile_fragment.ProfileFragment;
import com.student.rentals.ui.fragments.uploads_fragments.UploadsFragment;

/**
 * Copyright (c) $today.year.
 * This file was created by eli on 3/12/20
 * You are not allowed to copy it or use it in another project
 * Without permission from creator
 **/
public class MainViewerPager extends FragmentPagerAdapter {
    private String[] titles = {"Apartments","Brokers","Profile"};
    private Fragment[] childFragments;

    public MainViewerPager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        childFragments = new Fragment[] {

                new ApparmentsFragment(),
                new ListItemsFragment(),
                new ProfileFragment()
        };
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return childFragments[position];
    }

    @Override
    public int getCount() {
        return childFragments.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position) {
        return titles[position];
    }
}

package com.student.rentals.ui.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.student.rentals.ui.fragments.list_fragment.NotifiactionsFragment;
import com.student.rentals.ui.fragments.view.UploadsFragment;

public class ProfilePagerAdapter extends FragmentPagerAdapter {
    private String[] titles = {"Notifications","Uploads"};
    private Fragment[] childFragments;

    public ProfilePagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        childFragments = new Fragment[] {

                new NotifiactionsFragment(),
                new UploadsFragment(),
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

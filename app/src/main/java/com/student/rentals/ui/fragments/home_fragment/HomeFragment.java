package com.student.rentals.ui.fragments.home_fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.student.models.RoomData;
import com.student.rentals.R;
import com.student.rentals.ui.adapters.HouseListAdapter;
import com.student.rentals.ui.activities.ViewPropertyActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private View view;
    @BindView(R.id.house_list)
    RecyclerView house_list;
    private ArrayList<String> houses = new ArrayList<>();
    private HomeViewModel viewModel;
    private HouseListAdapter adapter;

    public HomeFragment() {
    }

    @Override
    public void onAttach(@NonNull final Context context) {
        super.onAttach(context);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        adapter = new HouseListAdapter(getActivity());
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        viewModel.getRoomsList().observe(getViewLifecycleOwner(), new Observer<List<RoomData>>() {
            @Override
            public void onChanged(List<RoomData> roomData) {
                Log.d(TAG, roomData.toString());
            }
        });
        createHouses();

    }

    private void createList() {
        house_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.addItems(houses);
        house_list.setAdapter(adapter);
    }

    private void createHouses() {
        for (int i = 0; i < 10; i++) {
            houses.add(i, "House: " + i);
        }
        createList();
    }

    private void openActivity(View view, String string) {
        startActivity(new Intent(getContext(), ViewPropertyActivity.class));
        return;
    }
}

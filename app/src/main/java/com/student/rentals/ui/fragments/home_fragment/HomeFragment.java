package com.student.rentals.ui.fragments.home_fragment;

import android.os.Bundle;
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

import com.student.models.ApartmentData;
import com.student.models.pApartmentData;
import com.student.rentals.R;
import com.student.rentals.ui.adapters.HouseListAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private View view;
    @BindView(R.id.house_list)
    RecyclerView house_list;
    private HomeViewModel viewModel;
    private HouseListAdapter adapter;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.home_fragment, container, false);
        adapter = new HouseListAdapter(getActivity());
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        viewModel.getRoomsList().observe(getViewLifecycleOwner(), new Observer<pApartmentData>() {
            @Override
            public void onChanged(pApartmentData pApartmentData) {
                createList(pApartmentData.getData());
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    private void createList(List<ApartmentData> apartmentData) {
        house_list.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter.addItems(apartmentData);
        house_list.setAdapter(adapter);
    }

}

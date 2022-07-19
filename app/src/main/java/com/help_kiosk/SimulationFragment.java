package com.help_kiosk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.gms.tasks.Task;

public class SimulationFragment extends Fragment {
    private SimulationViewModel simulationViewModel;
    private Task<Uri> pat
    private ImageView imageView;
    private ImageButton bt_left;
    private ImageButton bt_right;
    private String selectedBtnName;
    private int count;


    public SimulationFragment() {
    }

    public static SimulationFragment newInstance(String param1, String param2) {
        SimulationFragment fragment = new SimulationFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_simulation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.way_img);
        bt_left = view.findViewById(R.id.way_btn_left);
        bt_right = view.findViewById(R.id.way_btn_right);
        selectedBtnName = WayFragmentArgs.fromBundle(getArguments()).getSelectedBtnName().toString();
        count = 1;

        simulationViewModel.isUriLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoaded) {
                if (isLoaded){

                }
            }
        });

    }
}
package com.help_kiosk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;

public class WayFragment extends Fragment {
    private WayViewModel wayViewModel;
    private ImageView imageView;
    private String selectedBtnName ;
    private Button bt_left;
    private Button bt_right;

    private Task<Uri> pathReference;

    public WayFragment() {
    }

    public static WayFragment newInstance(String param1, String param2) {
        WayFragment fragment = new WayFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        wayViewModel = new ViewModelProvider(this).get(WayViewModel.class);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_way, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = view.findViewById(R.id.way_img);
        selectedBtnName = WayFragmentArgs.fromBundle(getArguments()).getSelectedBtnName().toString();

        wayViewModel.getPathListReference(selectedBtnName);

        wayViewModel.isUriLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoaded) {
                if(isLoaded){
                    pathReference = wayViewModel.getPathReference();

                    Glide.with(requireContext())
                            .load(pathReference.getResult())
                            .into(imageView);
                }
            }
        });

    }
}
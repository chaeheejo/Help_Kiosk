package com.help_kiosk;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.Task;

public class WayFragment extends Fragment {
    private WayViewModel wayViewModel;
    private ImageView imageView;
    private String selectedBtnName ;
    private ImageButton bt_left;
    private ImageButton bt_right;
    private int count;

    private Task<Uri> pathUri;

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
        return inflater.inflate(R.layout.fragment_way, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bt_left = view.findViewById(R.id.way_btn_left);
        bt_right = view.findViewById(R.id.way_btn_right);
        selectedBtnName = WayFragmentArgs.fromBundle(getArguments()).getSelectedBtnName().toString();
        count=1;

        getPhoto(view, count);

        bt_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count-1>1){
                    getPhoto(view, count);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "첫 화면입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (count-1<wayViewModel.getSize()){
                    getPhoto(view, count);
                }
                else{
                    Toast.makeText(getActivity().getApplicationContext(), "마지막 화면입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void getPhoto(View view, int count){
        if (count==1){
            wayViewModel.getUriList(selectedBtnName);

            wayViewModel.isUriListLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
                @Override
                public void onChanged(Boolean isLoaded) {
                    if(isLoaded){
                        loadPhoto(view, count);
                    }
                }
            });
        }
        else{
            loadPhoto(view, count);
        }
    }

    public void loadPhoto(View view, int count){
        imageView = view.findViewById(R.id.way_img);
        wayViewModel.getDownloadUri(count);

        wayViewModel.isUriLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Task<Uri> pathUri = wayViewModel.getUri();

                Glide.with(requireContext())
                        .load(pathUri.getResult())
                        .into(imageView);
            }
        });
    }
}
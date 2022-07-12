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
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.google.android.gms.tasks.Task;

public class McdonaldsFragment extends Fragment {
    private WayViewModel wayViewModel;
    private ImageView imageView2;

    private Task<Uri> pathReference;

    public McdonaldsFragment() {
        // Required empty public constructor
    }

    public static McdonaldsFragment newInstance(String param1, String param2) {
        McdonaldsFragment fragment = new McdonaldsFragment();
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
        return inflater.inflate(R.layout.fragment_mcdonalds, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView2 = view.findViewById(R.id.imageView2);

        wayViewModel.getPhoto();
//        pathReference = wayViewModel.getUri();

        wayViewModel.uriLoaded().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoaded) {
                if(isLoaded){
                    pathReference=wayViewModel.getUri();
                    Glide.with(requireContext())
                            .load(pathReference.getResult())
                            .into(imageView2);
                }
            }
        });

    }
}
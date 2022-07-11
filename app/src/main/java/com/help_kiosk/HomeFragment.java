package com.help_kiosk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    private Button bt_mcdonalds;
    private Button bt_bugerking;
    private Button bt_cgv;
    private Button bt_gongcha;
    private Button bt_way;
    private Button bt_simulation;

    public HomeFragment() {

    }

    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @NonNull Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        bt_mcdonalds = view.findViewById(R.id.home_bt_mcdonalds);
        bt_bugerking = view.findViewById(R.id.home_bt_bugerking);

        bt_mcdonalds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_mcdonaldsFragment);
            }
        });

        bt_bugerking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_bugerkingFragment);
            }
        });
    }
}
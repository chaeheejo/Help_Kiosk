package com.help_kiosk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;

public class HomeFragment extends Fragment {

    private Button bt_way;
    private Button bt_simulation;
    private RadioGroup radioGroup;
    private String selectedButton ="";

    public HomeFragment() {

    }

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
        radioGroup = view.findViewById(R.id.home_radio_group);
        bt_way = view.findViewById(R.id.home_bt_way);
        bt_simulation = view.findViewById(R.id.home_bt_simulation);

        bt_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                
                switch (selectedButton){
                    case "mcdonalds":
                        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_wayFragment);
                        break;
                    case "bugerking":
                        break;
                    case "cgv":
                        break;
                    case "gongcha":
                        break;
                }
            }
        });

        bt_simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selectedButton){
                    case "mcdonalds":
                        NavHostFragment.findNavController(HomeFragment.this).navigate(R.id.action_homeFragment_to_simulationFragment);
                        break;
                    case "bugerking":
                        break;
                    case "cgv":
                        break;
                    case "gongcha":
                        break;
                }
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.home_rbt_mcdonalds:
                        selectedButton = "mcdonalds";
                        break;
                    case R.id.home_rbt_bugerking:
                        selectedButton = "bugerking";
                        break;
                    case R.id.home_rbt_cgv:
                        selectedButton = "cgv";
                        break;
                    case R.id.home_rbt_gongcha:
                        selectedButton = "gongcha";
                        break;
                }
            }
        });
    }
}
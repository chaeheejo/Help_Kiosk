package com.help_kiosk;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Toast;

public class HomeFragment extends Fragment {

    private WayViewModel wayViewModel;
    private Button bt_way;
    private Button bt_simulation;
    private RadioGroup radioGroup;
    private String selectedBtnName;

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
        wayViewModel = new ViewModelProvider(this).get(WayViewModel.class);
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
        selectedBtnName ="";

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.home_rbt_mcdonalds:
                        selectedBtnName = "mcdonalds";
                        break;
                    case R.id.home_rbt_bugerking:
                        selectedBtnName = "bugerking";
                        break;
                    case R.id.home_rbt_cgv:
                        selectedBtnName = "cgv";
                        break;
                    case R.id.home_rbt_gongcha:
                        selectedBtnName = "gongcha";
                        break;
                }
            }
        });



        bt_way.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedBtnName.isEmpty()){
                    HomeFragmentDirections.ActionHomeFragmentToWayFragment action = HomeFragmentDirections.actionHomeFragmentToWayFragment(selectedBtnName);
                    Navigation.findNavController(v).navigate(action);
                }
                else{
                    Log.d("home", "click ");
                    Toast.makeText(getActivity().getApplicationContext(), "상점을 먼저 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        bt_simulation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!selectedBtnName.isEmpty()){
                    HomeFragmentDirections.ActionHomeFragmentToWayFragment action = HomeFragmentDirections.actionHomeFragmentToWayFragment(selectedBtnName);
                    Navigation.findNavController(v).navigate(action);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "상점을 먼저 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

}
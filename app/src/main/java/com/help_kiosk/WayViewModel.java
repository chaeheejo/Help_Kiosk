package com.help_kiosk;

import androidx.lifecycle.ViewModel;


public class WayViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();

    public void getPhoto(){
        userRepository.getPhoto(result -> {
            if(result instanceof Result.Success){

            }
        });
    }
}

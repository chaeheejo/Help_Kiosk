package com.help_kiosk;

import androidx.lifecycle.ViewModel;

import com.google.firebase.storage.StorageReference;


public class WayViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    StorageReference pathReference;

    public StorageReference getPhoto(){
        userRepository.getPhoto(result -> {
            if(result instanceof Result.Success){
                pathReference = ((Result.Success<StorageReference>)result).getData();
            }
        });
        return pathReference;
    }
}

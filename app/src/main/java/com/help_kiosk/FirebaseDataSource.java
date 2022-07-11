package com.help_kiosk;

import android.widget.Toast;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseDataSource {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    public void getPhoto(DataSourceCallback<Result> callback){
        StorageReference pathReference = storageRef.child("KakaoTalk_20220706_214041555.jpg");
        if(pathReference == null){
            callback.onComplete(new Result.Error(new Exception("Failed")));
        }else{
            callback.onComplete(new Result.Success<String>("Success"));
        }
    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

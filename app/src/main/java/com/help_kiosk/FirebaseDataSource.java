package com.help_kiosk;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseDataSource {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    public void getPhoto(DataSourceCallback<Result> callback){
        StorageReference pathReference = storageRef.child("KakaoTalk_20220706_214041555.jpg");
        storageRef.child("KakaoTalk_20220706_214041555.jpg").getDownloadUrl()
                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        callback.onComplete(new Result.Success<StorageReference>(pathReference));
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
        if(pathReference == null){
            callback.onComplete(new Result.Error(new Exception("Failed")));
        }else{
            callback.onComplete(new Result.Success<StorageReference>(pathReference));
        }
    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

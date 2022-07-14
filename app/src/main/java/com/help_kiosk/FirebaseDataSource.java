package com.help_kiosk;

import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FirebaseDataSource {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    public void getPhoto(DataSourceCallback<Result> callback){


        StorageReference pathReference = storageRef.child("mcdonalds/1.jpg");

        pathReference.getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if(task.isSuccessful()){
                            callback.onComplete(new Result.Success<Task>(task));
                        }
                    }
                });

    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

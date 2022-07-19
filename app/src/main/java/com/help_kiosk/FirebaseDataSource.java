package com.help_kiosk;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.LinkedList;
import java.util.List;

public class FirebaseDataSource {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();

    public void getUriList(String selectedBtnName, DataSourceCallback<Result> callback){
        StorageReference listRef = storageRef.child(selectedBtnName);

        listRef.listAll()
                .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                    @Override
                    public void onSuccess(ListResult listResult) {
                        callback.onComplete(new Result.Success<ListResult>(listResult));
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.e("firebase", e.getMessage());
                    }
                });
    }

    public void getDownloadUri(String path, DataSourceCallback<Result> callback){
        storageRef.child(path).getDownloadUrl()
            .addOnCompleteListener(new OnCompleteListener<Uri>() {
                @Override
                public void onComplete(@NonNull Task<Uri> task) {
                    callback.onComplete(new Result.Success<Task>(task));
                }
            });
    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

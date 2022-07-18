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

import java.util.Arrays;
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
                    sortUriList(listResult);
                    callback.onComplete(new Result.Success<ListResult>(listResult));
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("firebase", e.getMessage());
                }
            });
    }

    public void sortUriList(ListResult listResult){
        ListResult newListResult;
        for (StorageReference item : listResult.getItems()){
            String[] itemPath = item.getName().split("[.]");
            Log.d("fire", "sortUri item0: "+itemPath[0]);
            String path = itemPath[0];
            Log.d("fire", "sortUriList: "+listResult.getItems().size());
        }
    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

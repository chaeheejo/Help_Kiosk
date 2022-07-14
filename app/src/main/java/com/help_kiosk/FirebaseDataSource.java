package com.help_kiosk;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
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

    public void getPhoto(String selectedPathName, DataSourceCallback<Result> callback){
        List<Task<Uri>> toReturn = new LinkedList<>();

        StorageReference listReference = storageRef.child(selectedPathName);
        listReference.listAll().addOnSuccessListener(new OnSuccessListener<ListResult>() {
            @Override
            public void onSuccess(ListResult listResult) {
                for(StorageReference item : listResult.getItems()){
                    Task<Uri> tmpUri = item.getDownloadUrl();
                    toReturn.add(tmpUri);
                }
            }
        });



        pathReference.getDownloadUrl()
                .addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull List<Task<Uri>> task) {
                        if(task.isSuccessful()){
                            callback.onComplete(new Result.Success<List<Task<Uri>>(toReturn));
                        }
                    }
                });

    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

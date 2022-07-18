package com.help_kiosk;

import android.app.Activity;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.ListResult;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executor;


public class WayViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<Boolean> uriListLoaded = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> uriLoaded = new MutableLiveData<>(false);
    private int size;
    private ListResult pathListUri;

    private Task<Uri> pathUri = new Task<Uri>() {
        @Override
        public boolean isComplete() {
            return false;
        }

        @Override
        public boolean isSuccessful() {
            return false;
        }

        @Override
        public boolean isCanceled() {
            return false;
        }

        @Nullable
        @Override
        public Uri getResult() {
            return null;
        }

        @Nullable
        @Override
        public <X extends Throwable> Uri getResult(@NonNull Class<X> aClass) throws X {
            return null;
        }

        @Nullable
        @Override
        public Exception getException() {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnSuccessListener(@NonNull OnSuccessListener<? super Uri> onSuccessListener) {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnSuccessListener(@NonNull Executor executor, @NonNull OnSuccessListener<? super Uri> onSuccessListener) {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnSuccessListener(@NonNull Activity activity, @NonNull OnSuccessListener<? super Uri> onSuccessListener) {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnFailureListener(@NonNull OnFailureListener onFailureListener) {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnFailureListener(@NonNull Executor executor, @NonNull OnFailureListener onFailureListener) {
            return null;
        }

        @NonNull
        @Override
        public Task<Uri> addOnFailureListener(@NonNull Activity activity, @NonNull OnFailureListener onFailureListener) {
            return null;
        }
    };


    public void getUriList(String path){
        userRepository.getUriList(path, result -> {
            if(result instanceof Result.Success){
                pathListUri = ((Result.Success<ListResult>)result).getData();
                size = pathListUri.getItems().size();
                uriListLoaded.setValue(true);
            }
        });
    }

    public void getDownloadUri(int count){
        Log.d("way", "start getdownloadurl "+pathListUri.toString());
        StorageReference storageRef = pathListUri.getItems().get(count-1);

        storageRef.getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()){
                    pathUri = task;
                    uriLoaded.setValue(true);
                }
                else{
                    Log.d("way", "failed getdownloadurl ");
                }
            }
        });
    }

    public LiveData<Boolean> isUriLoaded(){return uriLoaded;}
    public LiveData<Boolean> isUriListLoaded(){return uriListLoaded;}
    public int getSize(){return size;}
    public Task<Uri> getUri(){return pathUri;}

}

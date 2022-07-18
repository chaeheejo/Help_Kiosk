package com.help_kiosk;

import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class FirebaseDataSource {
    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private StorageReference storageRef = storage.getReference();
    private List<StorageReference> sortedListResult = new List<StorageReference>() {
        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(@Nullable Object o) {
            return false;
        }

        @NonNull
        @Override
        public Iterator<StorageReference> iterator() {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(@NonNull T[] a) {
            return null;
        }

        @Override
        public boolean add(StorageReference storageReference) {
            return false;
        }

        @Override
        public boolean remove(@Nullable Object o) {
            return false;
        }

        @Override
        public boolean containsAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(@NonNull Collection<? extends StorageReference> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends StorageReference> c) {
            return false;
        }

        @Override
        public boolean removeAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(@NonNull Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public StorageReference get(int index) {
            return null;
        }

        @Override
        public StorageReference set(int index, StorageReference element) {
            return null;
        }

        @Override
        public void add(int index, StorageReference element) {

        }

        @Override
        public StorageReference remove(int index) {
            return null;
        }

        @Override
        public int indexOf(@Nullable Object o) {
            return 0;
        }

        @Override
        public int lastIndexOf(@Nullable Object o) {
            return 0;
        }

        @NonNull
        @Override
        public ListIterator<StorageReference> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<StorageReference> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<StorageReference> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public void getUriList(String selectedBtnName, DataSourceCallback<Result> callback){
        StorageReference listRef = storageRef.child(selectedBtnName);

        listRef.listAll()
            .addOnSuccessListener(new OnSuccessListener<ListResult>() {
                @Override
                public void onSuccess(ListResult listResult) {
                    sortUriList(listResult, callback);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.e("firebase", e.getMessage());
                }
            });
    }

    public void sortUriList(ListResult listResult, DataSourceCallback<Result> callback){
        List<StorageReference> listStorage = listResult.getItems();

        for (int i=1; i<=listStorage.size();i++){
            for (int j=1;j<=listStorage.size();j++){
                StorageReference tmpStorage = listStorage.get(j-1);
                String[] itemName = tmpStorage.getName().split("[.]");
                if (itemName[0].equals(String.valueOf(i))){
                    sortedListResult.add(tmpStorage);
                    Log.d("fire sort", "sortUriList: "+sortedListResult.toString());
                    Log.d("fire sort", "sortUriList: "+sortedListResult.size());
                    break;
                }
            }
        }
        Log.d("fire", "sortUriList: "+sortedListResult.toString());
        Log.d("fire", "sortUriList: "+sortedListResult.size());

        callback.onComplete(new Result.Success<List<StorageReference>>(sortedListResult));
    }

    public interface DataSourceCallback<Result>{
        void onComplete(Result result);
    }
}

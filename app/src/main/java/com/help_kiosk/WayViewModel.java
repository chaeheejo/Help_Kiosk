package com.help_kiosk;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.Task;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class WayViewModel extends ViewModel {
    private UserRepository userRepository = UserRepository.getInstance();
    private MutableLiveData<Boolean> uriLoaded = new MutableLiveData<>(false);

    private List<Task<Uri>> pathListReference = new List<Task<Uri>>() {

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
        public Iterator<Task<Uri>> iterator() {
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
        public boolean add(Task<Uri> uriTask) {
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
        public boolean addAll(@NonNull Collection<? extends Task<Uri>> c) {
            return false;
        }

        @Override
        public boolean addAll(int index, @NonNull Collection<? extends Task<Uri>> c) {
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
        public Task<Uri> get(int index) {
            return null;
        }

        @Override
        public Task<Uri> set(int index, Task<Uri> element) {
            return null;
        }

        @Override
        public void add(int index, Task<Uri> element) {

        }

        @Override
        public Task<Uri> remove(int index) {
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
        public ListIterator<Task<Uri>> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Task<Uri>> listIterator(int index) {
            return null;
        }

        @NonNull
        @Override
        public List<Task<Uri>> subList(int fromIndex, int toIndex) {
            return null;
        }
    };

    public void getPathListReference(String selectedBtnName){
        userRepository.getPathListReference(selectedBtnName,result -> {
            if(result instanceof Result.Success){
                pathListReference = ((Result.Success<List<Task<Uri>>>)result).getData();
                uriLoaded.setValue(true);
            }
        });
    }

    public Task<Uri> getPathReference(){


        //List인 reference를 pageNo에 따라 하나씩 반환
        return pathListReference;
    }

    public LiveData<Boolean> isUriLoaded(){return uriLoaded;}

}

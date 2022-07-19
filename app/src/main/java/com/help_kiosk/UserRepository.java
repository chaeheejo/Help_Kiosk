package com.help_kiosk;

public class UserRepository {
    private static volatile UserRepository INSTANCE = new UserRepository();
    public static UserRepository getInstance(){
        return INSTANCE;
    }
    private FirebaseDataSource firebaseDataSource;

    public void getUriList(String selectedBtnName, UserRepositoryCallback<Result> callback){
        firebaseDataSource.getUriList(selectedBtnName, callback::onComplete);
    }

    public void getDownloadUri(String path, UserRepositoryCallback<Result> callback){
        firebaseDataSource.getDownloadUri(path, callback::onComplete);
    }

    public void setDataSource(FirebaseDataSource ds){this.firebaseDataSource = ds;}
    public interface UserRepositoryCallback<Result>{
        void onComplete(Result result);
    }
}

package com.help_kiosk;

public class UserRepository {
    private static volatile UserRepository INSTANCE = new UserRepository();
    public static UserRepository getInstance(){
        return INSTANCE;
    }
    private FirebaseDataSource firebaseDataSource;

    public void getPhoto(UserRepositoryCallback<Result> callback){
        firebaseDataSource.getPhoto(callback::onComplete);
    }

    public interface UserRepositoryCallback<T>{
        void onComplete(Result result);
    }
}

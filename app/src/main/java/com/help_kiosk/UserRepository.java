package com.help_kiosk;

public class UserRepository {
    private static volatile UserRepository INSTANCE = new UserRepository();
    public static UserRepository getInstance(){
        return INSTANCE;
    }
    private FirebaseDataSource firebaseDataSource;

    public void getPhoto(String selectedBtnName, UserRepositoryCallback<Result> callback){
        firebaseDataSource.getPhoto(selectedBtnName, callback::onComplete);
    }

    public void setDataSource(FirebaseDataSource ds){this.firebaseDataSource = ds;}
    public interface UserRepositoryCallback<Result>{
        void onComplete(Result result);
    }
}

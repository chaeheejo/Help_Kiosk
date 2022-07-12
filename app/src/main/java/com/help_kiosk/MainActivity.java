package com.help_kiosk;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseDataSource ds = new FirebaseDataSource();
        UserRepository.getInstance().setDataSource(ds);
    }
}
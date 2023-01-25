package com.example.demo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PracticeActivity extends AppCompatActivity {

    private Button button_log;
    private Button button_toast;
    public static String TAG = "demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        setTitle("Practice Activity");
        button_log = findViewById(R.id.button_log);

        button_log.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               Log.d(TAG, "Practice!Practice!Practice!");

           }
        });

        button_toast = findViewById(R.id.button_Toast);

        button_toast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Now push to Github and Submit!",Toast.LENGTH_LONG).show();

            }
        });


    }
}

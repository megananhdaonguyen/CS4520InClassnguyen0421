package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class SelectAvatar extends AppCompatActivity {
    private ImageButton button_avatar1;
    private ImageButton button_avatar2;
    private ImageButton button_avatar3;
    private ImageButton button_avatar4;
    private ImageButton button_avatar5;
    private ImageButton button_avatar6;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_avatar);
        setTitle("Select Avatar");

        button_avatar1 = findViewById(R.id.avatar1);
        button_avatar2 = findViewById(R.id.avatar2);
        button_avatar3 = findViewById(R.id.avatar3);
        button_avatar4 = findViewById(R.id.avatar4);
        button_avatar5 = findViewById(R.id.avatar5);
        button_avatar6 = findViewById(R.id.avatar6);

//        Intent toClass02 = new Intent(SelectAvatar.this, InClass02.class);

        button_avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar1);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });

        button_avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar2);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });

        button_avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar3);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });

        button_avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar4);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });

        button_avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar5);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });

        button_avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, R.drawable.avatar6);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });
    }
}

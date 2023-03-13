package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button buttonPractice;
    private Button buttonInClass01;

    private Button buttonInClass02;
    private Button buttonInClass03;
    private Button buttonInClass04;
    private Button buttonInClass05;
    private Button buttonInClass06;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPractice = findViewById(R.id.button_Practice);
        buttonInClass01 = findViewById(R.id.button_InClass01);
        buttonInClass02 = findViewById(R.id.button_InClass02);
        buttonInClass03 = findViewById(R.id.button_InClass03);
        buttonInClass04 = findViewById(R.id.button_InClass04);
        buttonInClass05 = findViewById(R.id.button_InClass05);
        buttonInClass06 = findViewById(R.id.button_InClass06);

        buttonPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPracticeActivity = new Intent(MainActivity.this,
                        PracticeActivity.class);

                startActivity(toPracticeActivity);
            }
        });

        buttonInClass01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass01 = new Intent(MainActivity.this,
                        InClass01.class);

                startActivity(toInClass01);
            }
        });

        buttonInClass02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass02 = new Intent(MainActivity.this,
                        InClass02.class);

                startActivity(toInClass02);
            }
        });

        buttonInClass03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass03 = new Intent(MainActivity.this,
                        InClass03.class);

                startActivity(toInClass03);
            }
        });

        buttonInClass04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass04 = new Intent(MainActivity.this,
                        InClass04.class);

                startActivity(toInClass04);
            }
        });

        buttonInClass05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass05 = new Intent(MainActivity.this,
                        InClass05.class);

                startActivity(toInClass05);
            }
        });

        buttonInClass06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toInClass06 = new Intent(MainActivity.this,
                        InClass06.class);

                startActivity(toInClass06);
            }
        });
    }
}
package com.example.demo;

//Megan Nguyen
//Assignment 1

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InClass01 extends AppCompatActivity {
    private Button calculate;
    private EditText weight_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EditText edit = (EditText) findViewById(R.id.weight_input);
        //TextView tview = (TextView) findViewById(R.id.textview1);
        Editable result = edit.getText();
        //tview.setText(result);

    }
}

package com.example.demo;

//Megan Nguyen
//Assignment 1

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class InClass01 extends AppCompatActivity {
    private Button button_CalcBMI;
    private EditText weight_Input;
    private EditText feet_Input;
    private EditText inches_Input;
    private TextView text_Result;
    private TextView condition_Output;

    public static String TAG = "In Class";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculate_bmi);
        setTitle("Calculate BMI");

        text_Result = findViewById(R.id.text_result);
        condition_Output = findViewById(R.id.condition_output);

        weight_Input = findViewById(R.id.weight_input);
        feet_Input = findViewById(R.id.feet_input);
        inches_Input = findViewById(R.id.inches_input);

        button_CalcBMI = findViewById(R.id.button_calcBMI);
        button_CalcBMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float weight = 0;
                float feet = 0;
                float inches = 0;

                String valueWeight = String.valueOf(weight_Input.getText());
                String valueFeet = String.valueOf(feet_Input.getText());
                String valueInches = String.valueOf(inches_Input.getText());

                if (valueWeight.isEmpty() || valueFeet.isEmpty() || valueInches.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Text Box Empty",Toast.LENGTH_LONG).show();
                } else if (Float.parseFloat(valueWeight) < 0 || Float.parseFloat(valueFeet) < 0|| Float.parseFloat(valueInches) < 0) {
                    Toast.makeText(getApplicationContext(), "Cannot Input Negative Numbers",Toast.LENGTH_LONG).show();
                } else {
                    weight = Float.parseFloat(valueWeight);
                    feet = Float.parseFloat(valueFeet);
                    inches = Float.parseFloat(valueInches);
                }

                float total_inches = (feet * 12) + inches;

                float bmi = (weight / (total_inches * total_inches)) * 703;
                String condition = "";

                if (bmi < 18.5) {
                    condition = "Underweight";
                } else if (bmi <= 24.9) {
                    condition = "Normal weight";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    condition = "Overweight";
                } else {
                    condition = "Obese";
                }

                text_Result.setText(String.format("Your BMI: %s", bmi));
                condition_Output.setText(String.format("%s", condition));
            }
        });

    }
}

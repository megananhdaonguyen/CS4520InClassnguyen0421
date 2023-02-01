package com.example.demo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DisplayActivity extends AppCompatActivity {
    private ImageView display_Avatar;
    private TextView display_Name;
    private TextView display_Email;
    private TextView display_Use;
    private TextView display_Mood;
    private ImageView display_Mood_Image;
    private Button button_Back;
    private int avatar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_activity);
        setTitle("Display Activity");

        display_Avatar = findViewById(R.id.display_avatar);
        display_Name = findViewById(R.id.display_name);
        display_Email = findViewById(R.id.display_email);
        display_Use = findViewById(R.id.display_use);
        display_Mood = findViewById(R.id.display_mood);
        display_Mood_Image = findViewById(R.id.display_mood_image);
        button_Back = findViewById(R.id.button_back);

        if(getIntent()!=null && getIntent().getExtras() != null) {
            User user = (User) getIntent().getSerializableExtra(InClass02.User_Key);
            display_Avatar.setImageResource(user.avatar);
            avatar = user.avatar;
            display_Name.setText(String.format("Name: %s", user.name));
            display_Email.setText(String.format("Email: %s", user.email));
            display_Use.setText(String.format("I use %s!", user.use));
            display_Mood.setText(String.format("I am %s!", user.mood));
            display_Mood_Image.setImageResource(user.moodImage);
        }

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toClass02 = new Intent();
                toClass02.putExtra(InClass02.User_Key, avatar);
                setResult(RESULT_OK, toClass02);
                finish();
            }
        });
    }
}

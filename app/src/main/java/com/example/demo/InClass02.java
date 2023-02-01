package com.example.demo;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.io.Serializable;

public class InClass02 extends AppCompatActivity {

    private EditText name_Input;
    private EditText email_Input;
    private ImageButton button_ChangeAvatar;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private Button button_Submit;
    private ImageView mood_Image;
    private String use;
    private String mood = "Awesome";
    private int moodImage = R.drawable.awesome;
    private int newAvatar = R.drawable.avatar_icon;
    private boolean imageChanged = false;
    final static String User_Key = "key";

    ActivityResultLauncher<Intent> startActivityForResult
            = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>(){
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode()==RESULT_OK) {
                button_ChangeAvatar = findViewById(R.id.button_change_avatar);
                newAvatar = result.getData().getIntExtra(User_Key, R.drawable.avatar_icon);
                button_ChangeAvatar.setImageResource(newAvatar);
                imageChanged = true;
            }
        }
    });

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_profile);
        setTitle("Edit Profile Activity");

        name_Input = findViewById(R.id.edit_name);
        email_Input = findViewById(R.id.edit_email);
        radioGroup = findViewById(R.id.radioGroup);
        button_ChangeAvatar = findViewById(R.id.button_change_avatar);
        seekBar = findViewById(R.id.mood_seekbar);
        button_Submit = findViewById(R.id.submit_button);
        mood_Image = findViewById(R.id.mood);

        mood_Image.setImageResource(R.drawable.awesome);

        Intent selectAvatar = new Intent(InClass02.this, SelectAvatar.class);
        Intent displayActivity = new Intent(InClass02.this, DisplayActivity.class);

        button_ChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult.launch(selectAvatar);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup radioGroup, int i) {
               if (i == R.id.radioButton_android) {
                   use = "Android";
               } else if (i == R.id.radioButton_ios) {
                   use = "iOS";
               }
           }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seek) {
                if (pval == 0) {
                    moodImage = R.drawable.angry;
                    mood_Image.setImageResource(moodImage);
                    mood = "Angry";
                }
                if (pval == 1) {
                    moodImage = R.drawable.sad;
                    mood_Image.setImageResource(moodImage);
                    mood = "Sad";
                }
                if (pval == 2) {
                    moodImage = R.drawable.happy;
                    mood_Image.setImageResource(moodImage);
                    mood = "Happy";
                }
                if (pval == 3) {
                    moodImage = R.drawable.awesome;
                    mood_Image.setImageResource(moodImage);
                    mood = "Awesome";
                }
            }
        });

        button_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = String.valueOf(name_Input.getText());
                String emailValue = String.valueOf(email_Input.getText());
                if (nameValue.isEmpty() || emailValue.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Text Box Empty",Toast.LENGTH_LONG).show();
                }
                if (use == null) {
                    Toast.makeText(getApplicationContext(), "No System Selected",Toast.LENGTH_LONG).show();
                }
                if (!imageChanged) {
                    Toast.makeText(getApplicationContext(), "No Avatar Selected",Toast.LENGTH_LONG).show();
                }
                if (!imageChanged) {
                    Toast.makeText(getApplicationContext(), "No Avatar Selected",Toast.LENGTH_LONG).show();
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
                    emailValue = "";
                    Toast.makeText(getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                User user = new User(nameValue, emailValue, newAvatar, use, mood, moodImage);
                displayActivity.putExtra(User_Key, user);
                startActivityForResult.launch(displayActivity);
            }
        });
    }
}

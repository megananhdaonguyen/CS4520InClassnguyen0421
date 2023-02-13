package com.example.demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Toast;

public class EditProfileFragment extends Fragment {
    private IfromFragmentToActivity fromEditToActivity;

    private User user;
    private ImageButton button_ChangeAvatar;
    private EditText name_Input;
    private EditText email_Input;
    private RadioGroup radioGroup;
    private SeekBar seekBar;
    private Button button_Submit;
    private ImageView mood_Image;
    private String use;
    private String mood = "Awesome";
    private int moodImage = R.drawable.awesome;
    private boolean isImageChanged = false;
    private int new_Display_Avatar = R.drawable.avatar_icon;

    public EditProfileFragment() {
        // Required empty public constructor
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        user = new User();

        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);
        view.setBackgroundColor(Color.WHITE);
        button_ChangeAvatar = view.findViewById(R.id.button_change_avatar);
        name_Input = view.findViewById(R.id.edit_name);
        email_Input = view.findViewById(R.id.edit_email);
        radioGroup = view.findViewById(R.id.radioGroup);
        button_ChangeAvatar = view.findViewById(R.id.button_change_avatar);
        seekBar = view.findViewById(R.id.mood_seekbar);
        button_Submit = view.findViewById(R.id.submit_button);
        mood_Image = view.findViewById(R.id.mood);

        mood_Image.setImageResource(R.drawable.awesome);

        button_ChangeAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isImageChanged = true;
                fromEditToActivity.selectAvatarClickedInEditProfile();
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
                    Toast.makeText(getActivity().getApplicationContext(), "Text Box Empty", Toast.LENGTH_LONG).show();
                }
                else if (!Patterns.EMAIL_ADDRESS.matcher(emailValue).matches()) {
                    Toast.makeText(getActivity().getApplicationContext(), "Invalid Email", Toast.LENGTH_SHORT).show();
                }
                else if (!isImageChanged) {
                    Toast.makeText(getActivity().getApplicationContext(), "No Avatar Selected", Toast.LENGTH_LONG).show();
                }
                else if (use == null) {
                    Toast.makeText(getActivity().getApplicationContext(), "No System Selected", Toast.LENGTH_LONG).show();
                }
                else {

                    user.setName(nameValue);
                    user.setEmail(emailValue);
                    user.setAvatar(new_Display_Avatar);
                    user.setUse(use);
                    user.setMood(mood);
                    user.setMoodImage(moodImage);

                    //send profile object to Activity
                    fromEditToActivity.submitButtonClickedInEditProfile(user);
                }
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(getArguments() != null){
            Bundle receivedData = getArguments();
            if(receivedData.containsKey("drawableID")){
                new_Display_Avatar = receivedData.getInt("drawableID");
                button_ChangeAvatar.setImageResource(new_Display_Avatar);
            }
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IfromFragmentToActivity){
            fromEditToActivity = (IfromFragmentToActivity) context;
        }else {
            throw new RuntimeException(context.toString()+ "must implement IfromFragmentToActivity");
        }

    }
}
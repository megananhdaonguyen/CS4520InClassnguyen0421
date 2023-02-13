package com.example.demo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayActivityFragment extends Fragment{
    private User user;
    private ImageView display_Avatar;
    private TextView display_Name;
    private TextView display_Email;
    private TextView display_Use;
    private TextView display_Mood;
    private ImageView display_Mood_Image;
    private Button button_Back;

    public DisplayActivityFragment() {
    }

    public static DisplayActivityFragment newInstance(User user) {
        DisplayActivityFragment fragment = new DisplayActivityFragment();
        Bundle args = new Bundle();
        args.putParcelable("profile", user);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            user = getArguments().getParcelable("profile");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display_activity, container, false);
        view.setBackgroundColor(Color.WHITE);
        display_Avatar = view.findViewById(R.id.display_avatar);
        display_Name = view.findViewById(R.id.display_name);
        display_Email = view.findViewById(R.id.display_email);
        display_Use = view.findViewById(R.id.display_use);
        display_Mood = view.findViewById(R.id.display_mood);
        display_Mood_Image = view.findViewById(R.id.display_mood_image);
        button_Back = view.findViewById(R.id.button_back);

        display_Avatar.setImageResource(user.getAvatar());
        display_Name.setText(String.format("Name: %s", user.getName()));
        display_Email.setText(String.format("Email: %s", user.getEmail()));
        display_Use.setText(String.format("I use %s!", user.getUse()));
        display_Mood.setText(String.format("I am %s!", user.getMood()));
        display_Mood_Image.setImageResource(user.getMoodImage());

        button_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }

}
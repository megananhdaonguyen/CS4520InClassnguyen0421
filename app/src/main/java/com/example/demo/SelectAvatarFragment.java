package com.example.demo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SelectAvatarFragment extends Fragment {
    private ImageButton button_avatar1;
    private ImageButton button_avatar2;
    private ImageButton button_avatar3;
    private ImageButton button_avatar4;
    private ImageButton button_avatar5;
    private ImageButton button_avatar6;

    private IfromFragmentToActivity fromSelectAvatarToActivity;

    public static SelectAvatarFragment newInstance() {
        SelectAvatarFragment fragment = new SelectAvatarFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        view.setBackgroundColor(Color.WHITE);
        button_avatar1 = view.findViewById(R.id.avatar1);
        button_avatar2 = view.findViewById(R.id.avatar2);
        button_avatar3 = view.findViewById(R.id.avatar3);
        button_avatar4 = view.findViewById(R.id.avatar4);
        button_avatar5 = view.findViewById(R.id.avatar5);
        button_avatar6 = view.findViewById(R.id.avatar6);


        button_avatar1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar1);
            }
        });
        button_avatar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar2);
            }
        });
        button_avatar3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar3);
            }
        });
        button_avatar4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar4);
            }
        });
        button_avatar5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar5);
            }
        });
        button_avatar6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fromSelectAvatarToActivity.avatarClickedInSelectAvatar(R.drawable.avatar6);
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
//        Initializing the interface......
        try {
            fromSelectAvatarToActivity = (IfromFragmentToActivity) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException("Error in retrieving data. Please try again");
        }
    }
}
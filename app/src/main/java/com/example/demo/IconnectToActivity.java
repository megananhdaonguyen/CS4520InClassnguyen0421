package com.example.demo;

import com.example.demo.ChatRecord;
import com.example.demo.User8;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public interface IconnectToActivity {
    void populateMainFragment(FirebaseUser mUser);
    void populateRegisterFragment();
    void registerDone(FirebaseUser mUser, User8 user);
    void logoutPressed();
    void newMessageButtonPressedFromMainFragment(ArrayList<User8> users);

    void onFriendSelectedFromSelectFriendFragment(User8 user);

    void onChatSelectedFromRecentChats(ChatRecord record);
}
package com.example.demo;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

public class InClass03 extends AppCompatActivity implements IfromFragmentToActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        setTitle("Edit Profile Fragment");

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, new EditProfileFragment(), "edit")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void selectAvatarClickedInEditProfile() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, SelectAvatarFragment.newInstance(), "selectAvatar")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void submitButtonClickedInEditProfile(User user) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.containerFragment, DisplayActivityFragment.newInstance(user), "display")
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void avatarClickedInSelectAvatar(int drawableID) {
        getSupportFragmentManager().popBackStack();
        EditProfileFragment editProfileFragment = (EditProfileFragment) getSupportFragmentManager()
                .findFragmentByTag("edit");

        Bundle bundle = new Bundle();
        bundle.putInt("drawableID", drawableID);
        editProfileFragment.setArguments(bundle);
    }

    @Override
    public void onBackPressed() {
        if(getSupportFragmentManager().getBackStackEntryCount()>1){
            super.onBackPressed();
        }
    }
}

package com.example.demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.IconnectToActivity;
import com.example.demo.User8;
import com.example.demo.R;

import java.util.ArrayList;


public class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {

    private ArrayList<User8> friends;
    private IconnectToActivity mListener;

    public FriendsAdapter(ArrayList<User8> friends, Context context) {
        this.friends = friends;
        if(context instanceof IconnectToActivity){
            this.mListener = (IconnectToActivity) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IeditButtonAction");
        }
    }

    public ArrayList<User8> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<User8> friends) {
        this.friends = friends;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textViewChatFriend;
        private final Button buttonChatFriend;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewChatFriend = itemView.findViewById(R.id.textView_row_friends);
            this.buttonChatFriend = itemView.findViewById(R.id.button_chat_row_friends);
        }

        public TextView getTextViewChatFriend() {
            return textViewChatFriend;
        }

        public Button getButtonChatFriend() {
            return buttonChatFriend;
        }
    }

    @NonNull
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_friends, parent, false);
        return new ViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsAdapter.ViewHolder holder, int position) {
        User8 curFriend = this.getFriends().get(position);
        holder.getTextViewChatFriend().setText(curFriend.getFirstname()+" "+curFriend.getLastname());
        holder.getButtonChatFriend().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mListener.onFriendSelectedFromSelectFriendFragment(curFriend);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.getFriends().size();
    }


}
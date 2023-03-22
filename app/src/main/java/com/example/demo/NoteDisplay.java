package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.OkHttpClient;


public class NoteDisplay extends AppCompatActivity implements AddFragment.IaddButtonAction {

    private RecyclerView recyclerView;
    private NoteAdapter noteAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private Notes notes;
    private HttpUrl meURL, getallURL, postURL, logoutURL;
    private String token;
    private OkHttpClient client;
    private TextView textView_name, textView_email;
    private static class TextKey {
        final static String text = "email";
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.display_notes);
        textView_email = findViewById(R.id.textView_email);
        textView_name = findViewById(R.id.textView_name);
        recyclerView = findViewById(R.id.recyclerReview);
        recyclerViewLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        client = new OkHttpClient();

        if (getIntent() != null && getIntent().getExtras() != null) {
            token = getIntent().getStringExtra("Token");
        }

        meURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/me")
                .newBuilder()
                .addQueryParameter("x-access-token", token)
                .build();

        logoutURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/logout")
                .newBuilder()
                .addQueryParameter("x-access-token", token)
                .build();

        getallURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/getall")
                .newBuilder()
                .addQueryParameter("x-access-token", token)
                .build();

        postURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/post")
                .newBuilder()
                .addQueryParameter("x-access-token", token)
                .build();

        System.out.println("token:" + token);
        Request requestProfile = new Request.Builder()
                .url(meURL)
                .build();

        client.newCall(requestProfile).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(NoteDisplay.this, "Error1", Toast.LENGTH_LONG).show();
                        }
                    });
                }
                if (response.isSuccessful()) {
                    Gson gsonData = new Gson();
                    MyDetails profile = gsonData.fromJson(response.body().charStream(), MyDetails.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            textView_name.setText(profile.getName());
                            textView_email.setText(profile.getEmail());
                        }
                    });
                }
            }
        });

        Request request = new Request.Builder()
                .url(getallURL)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(NoteDisplay.this, "Error2", Toast.LENGTH_LONG).show();
//                            noteAdapter = new NoteAdapter();
//                            recyclerView.setAdapter(noteAdapter);
                        }
                    });
                }
                if (response.isSuccessful()) {
                    Gson gsonData = new Gson();
                    notes = gsonData.fromJson(response.body().charStream(), Notes.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            noteAdapter = new NoteAdapter(notes.getNoteArrayList(), token, NoteDisplay.this);
                            recyclerView.setAdapter(noteAdapter);
                            noteAdapter.notifyDataSetChanged();
                        }
                    });
                }
            }
        });


        //      Populating The First Add Fragment....
        getSupportFragmentManager().beginTransaction()
                .add(R.id.containerAddEdit, AddFragment.newInstance(), "addFragment")
                .commit();
    }
    @Override
    public void addButtonClicked(String noteText) {
        //notes.add(note);

        RequestBody formBody = new FormBody.Builder()
                .add(NoteDisplay.TextKey.text, noteText)
                .build();
        Request requestAdd = new Request.Builder()
                .url(postURL)
                .post(formBody)
                .build();

        this.client.newCall(requestAdd).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gsonData = new Gson();
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    AddNote newNote = gsonData.fromJson(body, AddNote.class);

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notes.getNoteArrayList().add(newNote.getNote());
                        }
                    });

                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

}

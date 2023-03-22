package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.demo.NoteDisplay;
import com.example.demo.SignIn;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class InClass07 extends AppCompatActivity {

    private TextView textView_register, textView_login, textView_haveAccount;
    private EditText editText_name, editText_email, editText_password, editText_loginEmail,
            editText_loginPass;
    private Button button_register, button_login;
    private String regName, regEmail, regPassword, logEmail, logPassword;
    private OkHttpClient client;
    private HttpUrl registerURL, loginURL;
    private String token;

    private static class RegKeys {
        final static String name = "name";
        final static String email = "email";
        final static String password = "password";
    }

    private static class LogKeys {
        final static String email = "email";
        final static String password = "password";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_in_class07);

        setTitle("Sign In Page");

        textView_register = findViewById(R.id.textView_register);
        textView_login = findViewById(R.id.textView_login);
        textView_haveAccount = findViewById(R.id.textView_haveAccount);
        editText_name = findViewById(R.id.editText_name);
        editText_email = findViewById(R.id.editText_email);
        editText_password = findViewById(R.id.editText_password);
        editText_loginEmail = findViewById(R.id.editText_loginEmail);
        editText_loginPass = findViewById(R.id.editText_loginPass);
        button_register = findViewById(R.id.button_register);
        button_login = findViewById(R.id.button_login);
        client = new OkHttpClient();

        registerURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/register");

        loginURL = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/auth/login")
                .newBuilder()
                .build();

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                regName = String.valueOf(editText_name.getText());
                regEmail = String.valueOf(editText_email.getText());
                regPassword = String.valueOf(editText_password.getText());

                if (regName.isEmpty() || regEmail.isEmpty() || regPassword.isEmpty()) {
                    Toast.makeText(InClass07.this, "Invalid inputs",
                            Toast.LENGTH_LONG).show();
                }

                registerUsers(regName, regEmail, regPassword);

            }
        });

        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logEmail = String.valueOf(editText_email.getText());
                logPassword = String.valueOf(editText_password.getText());

                if (logEmail.isEmpty() || logPassword.isEmpty()) {
                    Toast.makeText(InClass07.this, "Invalid inputs",
                            Toast.LENGTH_LONG).show();
                }

                loginUsers(logEmail, logPassword);
                sendToNotesDisplay();
            }
        });
    }

    private void registerUsers(String addName, String addEmail, String addPassword) {
        RequestBody formBody = new FormBody.Builder()
                .add("name", addName)
                .add("email", addEmail)
                .add("password", addPassword)
                .build();
        Request request = new Request.Builder()
                .url(registerURL)
                .post(formBody)
                .build();

        Log.d("demo", "'requestBody: : " + request);

        this.client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if (response.isSuccessful()) {

                    Gson gsonData = new Gson();
                    ResponseBody responseBody = response.body();
                    String body = responseBody.string();
                    Log.d("demo", "onResponse: " + body);
                    //SignIn signIn = gsonData.fromJson(body, SignIn.class);
                    try {
                        JSONObject json = new JSONObject(body);
                        token = json.getString("token");
                    } catch(JSONException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            sendToNotesDisplay();
                        }
                    });
                    System.out.println("hi");

                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    private void loginUsers(String addEmail, String addPassword) {
        RequestBody formBody = new FormBody.Builder()
                .add(LogKeys.email, addEmail)
                .add(LogKeys.password, addPassword)
                .build();
        Request requestAdd = new Request.Builder()
                .url(loginURL)
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
                    SignIn signIn = gsonData.fromJson(body, SignIn.class);
                    token = signIn.getToken();

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });

                } else {
                    throw new IOException("Unexpected code " + response);
                }
            }
        });
    }

    private void sendToNotesDisplay() {
        Intent toNoteDisplay = new Intent(InClass07.this,
                NoteDisplay.class);
        toNoteDisplay.putExtra("Token", token);
        startActivity(toNoteDisplay);
    }
}



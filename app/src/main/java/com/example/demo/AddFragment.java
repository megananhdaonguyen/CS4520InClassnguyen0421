package com.example.demo;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class AddFragment extends Fragment {

    private static final String ARG_NOTE = "noteobejct";

    private Note mNote;
    private EditText editTextNote;
    private Button buttonAdd;
    private OkHttpClient client;

    private IaddButtonAction mListener;
    private HttpUrl url;


    public AddFragment() {
        // Required empty public constructor
    }

    public static AddFragment newInstance() {
        AddFragment fragment = new AddFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null) {
            mNote = (Note) args.getSerializable(ARG_NOTE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_add, container, false);
        editTextNote = rootView.findViewById(R.id.editTextNote);
        buttonAdd = rootView.findViewById(R.id.buttonAdd);

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String noteText = String.valueOf(editTextNote.getText());

//                if (noteText.isEmpty()) {
//                    Toast.makeText(AddFragment.this, "Invalid input", Toast.LENGTH_LONG).show();
//                }

                mListener.addButtonClicked(noteText);
            }
        });

        return rootView;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof IaddButtonAction){
            mListener = (IaddButtonAction) context;
        }else{
            throw new RuntimeException(context.toString()+ "must implement IaddButtonAction");
        }
    }

    public interface IaddButtonAction{
        void addButtonClicked(String noteText);
    }
}


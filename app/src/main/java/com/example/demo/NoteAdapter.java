package com.example.demo;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder> {
    private static final String TAG = "demo";
    private HttpUrl url;
    private OkHttpClient client = new OkHttpClient();
    private String token;
    private static class IDKey {
        final static String id = "id";
    }

    private ArrayList<Note> notes;

    public NoteAdapter() {
    }

    public NoteAdapter(ArrayList<Note> notes, String token, Context context) {
        this.notes = notes;
        this.token = token;

    }

    public ArrayList<Note> getNotes() {
        return notes;
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textViewNotes;
        private final Button buttonDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.textViewNotes = itemView.findViewById(R.id.textView_note);
            this.buttonDelete = itemView.findViewById(R.id.buttonDelete);
        }

        public TextView getTextViewNotes() {
            return textViewNotes;
        }

        public Button getButtonDelete() {
            return buttonDelete;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemRecyclerView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_row,parent, false);

        return new ViewHolder(itemRecyclerView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Note curNote = this.getNotes().get(position);

        holder.getTextViewNotes().setText(curNote.getText());

        holder.getButtonDelete().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                url = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com:3000/api/note/delete").newBuilder()
                        .addQueryParameter("x-access-token", token)
                        .build();

                RequestBody formBody = new FormBody.Builder()
                        .add(NoteAdapter.IDKey.id, curNote.get_id())
                        .build();
                Request requestAdd = new Request.Builder()
                        .url(url)
                        .post(formBody)
                        .build();

                NoteAdapter.this.client.newCall(requestAdd).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {

                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        if (response.isSuccessful()) {
                            Gson gsonData = new Gson();
                            ResponseBody responseBody = response.body();
                            String body = responseBody.string();
                            DeleteNote deletedNote = gsonData.fromJson(body, DeleteNote.class);

                            notes.remove(curNote);

                        } else {
                            throw new IOException("Unexpected code " + response);
                        }
                    }
                });

                notes.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.getNotes().size();
    }

}

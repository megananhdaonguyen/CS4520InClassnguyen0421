package com.example.demo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

// Megan Nguyen
// InClass05
public class InClass05 extends AppCompatActivity {

    private EditText imageSearch;
    private Button buttonGo;
    private ImageView displayImage;
    private ImageButton buttonPrevious;
    private ImageButton buttonNext;
    private ProgressBar loadingImage;
    private TextView loadingProgress;
    private int currentImage;
    private HttpUrl url;
    private OkHttpClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);
        setTitle("Image Search");

        imageSearch = findViewById(R.id.editText_search_keyword);
        buttonGo = findViewById(R.id.button_go);
        displayImage = findViewById(R.id.imageView_display);
        buttonPrevious = findViewById(R.id.imageButton_previous);
        buttonNext = findViewById(R.id.imageButton_next);
        loadingImage = findViewById(R.id.progressBar_loading_image);
        loadingProgress = findViewById(R.id.textView_loading);

        loadingImage.setVisibility(View.INVISIBLE);
        loadingProgress.setVisibility(View.INVISIBLE);

        buttonGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String keyword = String.valueOf(imageSearch.getText());
                OkHttpClient client = new OkHttpClient();
                url = HttpUrl.parse("http://ec2-54-164-201-39.compute-1.amazonaws.com/apis/images/retrieve").newBuilder()
                        .addQueryParameter("keyword", keyword)
                        .build();
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(@NonNull Call call, @NonNull IOException e) {
                    }

                    @Override
                    public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (!response.isSuccessful()) {
                                    Toast.makeText(InClass05.this, "INVALID KEYWORD, must be one of: " +
                                                    "boston, san_francisco, northeastern, spring,\n" +
                                                    "summer, wonders",
                                            Toast.LENGTH_LONG).show();
                                }
                                if (response.isSuccessful()) {
                                    ResponseBody responseBody = response.body();
                                    String responseBodyString = null;
                                    try {
                                        responseBodyString = responseBody.string();
                                    } catch (IOException e) {
                                        Toast.makeText(InClass05.this, "No Images Found",
                                                Toast.LENGTH_LONG).show();
                                        throw new RuntimeException(e);
                                    }
                                    List<String> images = new ArrayList<String>(Arrays.asList(responseBodyString.split("\n")));

                                    Glide.with(InClass05.this)
                                            .load(images.get(currentImage))
                                            .listener(new RequestListener<Drawable>() {
                                                @Override
                                                public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                    return false;
                                                }

                                                @Override
                                                public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                    loadingImage.setVisibility(View.GONE);
                                                    loadingProgress.setVisibility(View.GONE);
                                                    return false;
                                                }
                                            })
                                            .into(displayImage);

                                    buttonNext.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            loadingProgress.setVisibility(View.VISIBLE);
                                            loadingImage.setVisibility(View.VISIBLE);
                                            if (images.size() > 1) {
                                                if (currentImage + 1 == images.size()) {
                                                    currentImage = 0;
                                                } else {
                                                    currentImage++;
                                                }
                                                Glide.with(InClass05.this)
                                                        .load(images.get(currentImage))
                                                        .listener(new RequestListener<Drawable>() {
                                                            @Override
                                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                                return false;
                                                            }

                                                            @Override
                                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                                loadingProgress.setVisibility(View.GONE);
                                                                loadingImage.setVisibility(View.GONE);
                                                                return false;
                                                            }
                                                        })
                                                        .into(displayImage);
                                            }
                                        }
                                    });

                                    buttonPrevious.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            loadingImage.setVisibility(View.VISIBLE);
                                            loadingProgress.setVisibility(View.VISIBLE);
                                            if (images.size() > 1) {
                                                if (currentImage == 0) {
                                                    currentImage = images.size() - 1;
                                                } else {
                                                    currentImage = currentImage - 1;
                                                }
                                                Glide.with(InClass05.this)
                                                        .load(images.get(currentImage))
                                                        .listener(new RequestListener<Drawable>() {
                                                            @Override
                                                            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                                                                return false;
                                                            }

                                                            @Override
                                                            public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                                                                loadingImage.setVisibility(View.GONE);
                                                                loadingProgress.setVisibility(View.GONE);
                                                                return false;
                                                            }
                                                        })
                                                        .into(displayImage);
                                            }
                                        }
                                    });
                                }
                            }
                        });
                    }
                });
            }
        });
    }
}
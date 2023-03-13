package com.example.demo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

// Megan Nguyen
// InClass06
public class InClass06 extends AppCompatActivity {
    private final OkHttpClient client = new OkHttpClient();
    private ScrollView scrollView;
    private ListView listView_countries;
    private ListView listView_categories;
    private ArrayAdapter<News> newsAdapter;
    private ArrayAdapter<String> countriesAdapter;
    private ArrayAdapter<String> categoriesAdapter;
    private HttpUrl url;
    private NewsList newsList;
    private String country = "none";
    private String category = "none";
    private Boolean isCountry = false;
    private Boolean isCategory = false;
    private Button button_submit;
    private TextView displayNews;
    private TextView displayCategory;
    private TextView displayCountry;
    private TextView displayFoundNews;
    private final String[] arrayCountries = {"ma", "ae", "br", "au", "ca"};
    private final String[] arrayCategories = {"business", "entertainment", "general", "health", "science", "sports", "technology"};
    private final String APIKEY = "142721ee61534e3182f837c0533f587f";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news);
        setTitle("Find News");

        scrollView = findViewById(R.id.scrollView);
        listView_countries = findViewById(R.id.listView_country);
        listView_categories = findViewById(R.id.listView_category);
        button_submit = findViewById(R.id.button_submit);
        displayNews = findViewById(R.id.textView_news);
        displayFoundNews = findViewById(R.id.textView_display_news);
        displayCategory = findViewById(R.id.textView_category);
        displayCountry = findViewById(R.id.textView_country);

        countriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayCountries);
        listView_countries.setAdapter(countriesAdapter);
        listView_countries.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                country = arrayCountries[i];
                isCountry = true;
                displayCountry.setText("Country: " + country);
            }
        });

        categoriesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, arrayCategories);
        listView_categories.setAdapter(categoriesAdapter);
        listView_categories.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                category = arrayCategories[i];
                isCategory = true;
                displayCategory.setText("Category: " + category);
            }
        });



        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayNews.setText(String.format("News: %s,  %s", category, country));
                getNews();
            }
        });

    }

    private void getNews(){

        if (isCountry && isCategory) {
            url = HttpUrl.parse("https://newsapi.org/v2/top-headlines").newBuilder()
                    .addQueryParameter("country", country)
                    .addQueryParameter("category", category)
                    .addQueryParameter("apiKey", APIKEY)
                    .build();
        } else if (isCountry) {
            url = HttpUrl.parse("https://newsapi.org/v2/top-headlines").newBuilder()
                    .addQueryParameter("country", country)
                    .addQueryParameter("apiKey", APIKEY)
                    .build();
        } else if (isCategory) {
            url = HttpUrl.parse("https://newsapi.org/v2/top-headlines").newBuilder()
                    .addQueryParameter("category", category)
                    .addQueryParameter("apiKey", APIKEY)
                    .build();
        } else {
            url = HttpUrl.parse("https://newsapi.org/v2/top-headlines").newBuilder()
                    .addQueryParameter("apiKey", APIKEY)
                    .build();
        }

        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    Gson gsonData = new Gson();
                    newsList =  gsonData.fromJson(response.body().charStream(), NewsList.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            displayFoundNews.setText(newsList.toString());
                        }
                    });

                } else{
                    Toast.makeText(InClass06.this, "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
    }


}

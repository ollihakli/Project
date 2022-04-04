package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Movie> movieList;
    private RecyclerView recyclerView;

    Button searchButton;
    Button listButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        movieList = new ArrayList<>();

        searchButton = (Button) findViewById(R.id.searchButtonID);
        listButton = (Button) findViewById(R.id.listButtonID);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);

    }
    public void searchMovies (View v) {
        movieList = XmlReader.readXml(movieList);
        setAdapter();


    }

    private void setAdapter() {
        RecyclerAdapter adapter = new RecyclerAdapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
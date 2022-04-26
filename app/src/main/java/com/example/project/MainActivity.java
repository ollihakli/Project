package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Context context = null;

    private ArrayList<Movie> movieList;
    private RecyclerView recyclerView;
    private RecyclerAdapter.RecyclerViewClickListener listener;

    Button searchButton;
    Button listButton;


    MovieManager mm = MovieManager.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        context = MainActivity.this;
        mm.setContext(context);

        System.out.println(getFilesDir());
        movieList = new ArrayList<>();

        searchButton = (Button) findViewById(R.id.searchButtonID);
        listButton = (Button) findViewById(R.id.listButtonID);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerViewID);

    }
    public void searchMovies (View v) {
        movieList = XmlReader.readXml(movieList);
        setAdapter(movieList);
    }
    public void listMovies(View v) {
        openBestMoviesActivity();
        //MovieManager.getInstance().readEntries();
    }
    private void openBestMoviesActivity() {
        Intent intent = new Intent(MainActivity.this, BestMoviesActivity.class);
        startActivity(intent);
    }

    private void setAdapter(ArrayList<Movie> list) {
        setOnClickListener();
        RecyclerAdapter adapter = new RecyclerAdapter(list, listener);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new RecyclerAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), EntryActivity.class);
                intent.putExtra("moviename", movieList.get(position).getMovieName());
                startActivity(intent);
            }
        };
    }

}
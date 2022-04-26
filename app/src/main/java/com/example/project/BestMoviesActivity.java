package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class BestMoviesActivity extends AppCompatActivity {
    TextView txt;
    Button backButton;
    ArrayList<Entry> bestMovies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.best_movies_activity);

        bestMovies = new ArrayList<>();

        txt = (TextView) findViewById(R.id.bestMoviesTextViewid);
        backButton = (Button) findViewById(R.id.backButtonid);
        String s="";
        bestMovies = MovieManager.getInstance().readEntries();
        for(int i = 0; i < bestMovies.size(); i++) {
            System.out.println(bestMovies.get(i));
        }
        System.out.println(s);
        txt.setText(s);

    }
    public void goBack(View v){
        openMainActivity();
    }
    public void openMainActivity(){
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent);
    }

}

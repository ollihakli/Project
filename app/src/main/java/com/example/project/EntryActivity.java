package com.example.project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EntryActivity extends AppCompatActivity {

    Button submitButton;
    EditText comment;
    RatingBar ratingBar;
    float rateValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.entry_activity);

        TextView movieNameTxt = findViewById(R.id.movieNameTextViewid);
        submitButton = (Button) findViewById(R.id.searchButtonID);
        comment = (EditText) findViewById(R.id.editTextCommentid);
        ratingBar = (RatingBar) findViewById(R.id.ratingBarid);

        String moviename = "movie name not set";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            moviename = extras.getString("moviename");
        }
        movieNameTxt.setText(moviename);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                rateValue = ratingBar.getRating();
                Entry entry = new Entry();
                entry.setNumberOfStars(rateValue);
            }
        });
    }
}

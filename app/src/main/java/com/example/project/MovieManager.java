package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class MovieManager {

    private ArrayList<Entry> entryList;

    private static MovieManager instance = null;
    private Context context;

    private MovieManager() {
    }

    public static MovieManager getInstance() {
        if (instance == null) {
            instance = new MovieManager();
        }
        return instance;
    }

    public void setContext(Context c) {
        context = c;
    }

    public void saveEntries(Entry e) { //Saves user entries to CSV-file

        try {
            OutputStreamWriter ous = new OutputStreamWriter(context.openFileOutput("entries.csv", Context.MODE_APPEND));
            ous.write(e.getMovie() + ";" + e.getComment() + ";" + e.getNumberOfStars() + "\n");
            ous.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    @SuppressLint("NewApi")
    public ArrayList<Entry> readEntries() { //Reads saved entries from CSV-file and return list of entries
        try {
            String movie;
            float rating;
            String comment;
            String row = "";
            entryList = new ArrayList<>();

            InputStream ins = context.openFileInput("entries.csv");

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            while ((row = br.readLine()) != null) {
                String parts[] = row.split(";");
                movie = parts[0];
                comment = parts[1];
                rating = Float.parseFloat(parts[2]);

                Entry entry = new Entry(movie, rating, comment);
                entryList.add(entry);
            }

            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(entryList, Comparator.comparing(Entry::getNumberOfStars)); //Sorts list by number of stars
        Collections.reverse(entryList);
        return entryList;
    }
}

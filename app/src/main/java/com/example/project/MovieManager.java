package com.example.project;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

class MovieManager {

    private ArrayList<Entry> entryList;

    private static MovieManager instance = null;
    private Entry entry;
    private Context context;

    private MovieManager() {
    }

    public static MovieManager getInstance() {
        if (instance == null) {
            instance = new MovieManager();
        }
        return instance;
    }

    /*public void addEntry(Entry e){
        entryList.add(e);
    }*/

    public void setContext(Context c) {
        context = c;
    }

    public void saveEntries(Entry e) {

        try {

            OutputStreamWriter ous = new OutputStreamWriter(context.openFileOutput("testi.csv", Context.MODE_APPEND));
            //float f;
            //String com = getEntry().getComment();
            //f = getEntry().getNumberOfStars();
            //System.out.println(com+f);
            /*ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput("testi.txt", Context.MODE_APPEND));// FileOutputStream("objects.ser"));
            System.out.println("kirjoitettu");
            out.writeObject(e); // This can be a data structure
            out.close();*/
            ous.write(e.getMovie() + ";" + e.getComment() + ";" + e.getNumberOfStars() + "\n");

            //ous.write(com +";"+ Float.toString(f));
            ous.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public Entry getEntry() {
        return entry;
    }

    @SuppressLint("NewApi")
    public ArrayList<Entry> readEntries() {
        String txt = "";
        try {
            String movie;
            float rating;
            String comment;
            String row = "";
            entryList = new ArrayList<>();

           /* ObjectInputStream in = new ObjectInputStream(context.openFileInput("testi.txt", Context.MODE_APPEND));
            while((s=in.readLine()) != null) {
                Entry entry = (Entry) in.readObject(); // This can be e.g. Starship
                bestMovies.add(entry);
                System.out.println(entry.getMovie());
            }
            in.close();*/

            InputStream ins = context.openFileInput("testi.csv");

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            while ((row = br.readLine()) != null) {
                //System.out.println(row);
                //txt = txt + "\n" + row;
                String parts[] = row.split(";");
                movie = parts[0];
                comment = parts[1];
                rating = Float.parseFloat(parts[2]);

                Entry entry = new Entry(movie, rating, comment);
                entryList.add(entry);
                Collections.sort(entryList, Comparator.comparing(Entry::getNumberOfStars));

            }

            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entryList;
    }
}

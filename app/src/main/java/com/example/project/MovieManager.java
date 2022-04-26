package com.example.project;

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
import java.util.Collection;
import java.util.Collections;

class MovieManager {

    private ArrayList<Entry> bestMovies;

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

    public void setContext(Context c) {
        context = c;
    }

    public void saveEntries(Entry e) {

        try {

            OutputStreamWriter ous = new OutputStreamWriter(context.openFileOutput("testi.csv", Context.MODE_PRIVATE));
            //float f;
            //String com = getEntry().getComment();
            //f = getEntry().getNumberOfStars();
            //System.out.println(com+f);
            /*ObjectOutputStream out = new ObjectOutputStream(context.openFileOutput("testi.txt", Context.MODE_APPEND));// FileOutputStream("objects.ser"));
            System.out.println("kirjoitettu");
            out.writeObject(e); // This can be a data structure
            out.close();*/
            ous.write(e.getMovie() + e.getComment() + e.getNumberOfStars());

            //ous.write(com +";"+ Float.toString(f));
            ous.close();

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }


    }

    public Entry getEntry() {
        return entry;
    }

    public String readEntries() {
        String txt = "";
        try {
            String row = "";


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
                System.out.println(row);
                txt = txt + "\n" + row;

            }

            ins.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return txt;
    }
}

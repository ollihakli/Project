package com.example.project;

import java.io.Serializable;

public class Entry implements Serializable {

    private String movie;
    private float numberOfStars;
    private String comment = "";

    public Entry(String mov, float f, String com) {
        movie = mov;
        numberOfStars = f;
        comment = com;
    }

    @Override
    public String toString() {
        return '\'' + movie + '\'' + " Stars " + numberOfStars;
    }

    public String getMovie() {
        return movie;
    }

    public float getNumberOfStars() {
        return numberOfStars;
    }

    public String getComment() {
        return comment;
    }
}

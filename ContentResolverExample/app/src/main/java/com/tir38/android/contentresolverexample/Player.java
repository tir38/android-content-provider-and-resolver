package com.tir38.android.contentresolverexample;

// I manually copied this from Content Provider app.
// Normally I wouldn't know what the model object looks like on the Provider side
// and I would have to write my own class / fields.

public class Player {
    private String mName;
    private int mScore;

    public Player(String name, int score) {
        mName = name;
        mScore = score;
    }

    public String getName() {
        return mName;
    }

    public int getScore() {
        return mScore;
    }
}
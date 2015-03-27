package com.tir38.android.contentproviderexample;

import java.util.ArrayList;
import java.util.List;

/**
 * Store model data about PingPong
 */
public class DataStore {

    private static DataStore sDataStore;
    private List<Player> mPlayers;

    public static DataStore get() {
        if (sDataStore == null) {
            sDataStore = new DataStore();
        }

        return sDataStore;
    }

    private DataStore() {
        mPlayers = new ArrayList<>();
        mPlayers.add(new Player("kevin", 1));
        mPlayers.add(new Player("galvin", 3));
        mPlayers.add(new Player("PAUL", 12));
        mPlayers.add(new Player("chris", 6));
        mPlayers.add(new Player("andrew", 8));
        mPlayers.add(new Player("ceedon", 7));
    }

    public List<Player> getPlayers() {
        return mPlayers;
    }

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
}

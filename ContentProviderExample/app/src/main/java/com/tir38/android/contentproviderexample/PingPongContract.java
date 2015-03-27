package com.tir38.android.contentproviderexample;


import android.net.Uri;

/**
 * Contracts to access the PingPong Content Provider
 */
public class PingPongContract {

    /**
     * Authority string for this provider. This is our android-internal name.
     * We want to make sure it is unique.
     */
    public static final String AUTHORITY = "com.bignerdranch.pingpong.provider";

    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY);

    /**
     * The player column.
     * <p>TYPE: TEXT</p>
     */
    public static final String PLAYER = "player";

    /**
     * The score column.
     * <p>TYPE: INTEGER</p>
     */
    public static final String SCORE = "score";

}

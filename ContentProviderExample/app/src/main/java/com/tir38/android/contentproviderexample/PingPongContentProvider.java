package com.tir38.android.contentproviderexample;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.net.Uri;
import android.util.Log;

import java.util.List;

import static com.tir38.android.contentproviderexample.DataStore.get;

public class PingPongContentProvider extends ContentProvider {

    private static final String TAG = PingPongContentProvider.class.getSimpleName();

    private String[] colNames = {PingPongContract.PLAYER, PingPongContract.SCORE};

    @Override
    public boolean onCreate() {
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "somebody's accessing my Content Provider! " + uri.toString());
        if (uri.equals(PingPongContract.CONTENT_URI)) {

            // no matter what is queried for, return all players, all columns
            MatrixCursor matrixCursor = new MatrixCursor(colNames);

            List<DataStore.Player> players = get().getPlayers();
            Log.d(TAG, "cursor build, adding players: " + players.size());
            for (DataStore.Player player : players) {
                Log.d(TAG, "building player: " + player.getName());
                Object[] row = convertPlayerToRow(player);
                matrixCursor.addRow(row);
            }
            return matrixCursor;
        }

        return null; // contract doesn't match so don't do anything
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    private Object[] convertPlayerToRow(DataStore.Player player) {
        Object[] row = {player.getName(), player.getScore()};
        return row;
    }
}

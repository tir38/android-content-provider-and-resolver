package com.tir38.android.contentresolverexample;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.UserDictionary;
import android.support.v7.app.ActionBarActivity;
import android.widget.TextView;


/**
 * Activity that does some ContentResolving of the UserDictionary's ContentProvider
 */
public class ContentResolverActivity extends ActionBarActivity {

    private static final String TAG = ContentResolverActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView entriesTextView = (TextView) findViewById(R.id.entries);
        TextView playersTextView = (TextView) findViewById(R.id.players);

        getAllDictionaryEntries();

        addAnEntry();

        // lets see if our new entry is there...
        String allDictionaryEntries = getAllDictionaryEntries();
        entriesTextView.setText(allDictionaryEntries);

        // now lets try to access the content provider on the PingPong app
        String pingPongPlayers = getPingPongPlayers();
        playersTextView.setText(pingPongPlayers);
    }

    private void addAnEntry() {
        // Look! We use the same thing when sending stuff to SQLiteOpenHelper as we do when sending stuff to a ContentProvider
        ContentValues newEntry = new ContentValues();

        /*
         * Sets the values of each column and inserts the word. The arguments to the "put"
         * method are "column name" and "value"
         */
        newEntry.put(UserDictionary.Words.APP_ID, "example.user");
        newEntry.put(UserDictionary.Words.LOCALE, "en_US");
        newEntry.put(UserDictionary.Words.WORD, "abracadabra");
        newEntry.put(UserDictionary.Words.FREQUENCY, "1");

        getContentResolver().insert(
                UserDictionary.Words.CONTENT_URI,   // the user dictionary content URI
                newEntry                          // the values to insert
        );
    }

    private String getAllDictionaryEntries() {

        String entries = new String(); // we are going to store all entries as one long string; this is ugly don't repeat this pattern.

        Uri contentUri = UserDictionary.Words.CONTENT_URI;

        // Queries the user dictionary and returns results
        Cursor cursor = getContentResolver().query(
                contentUri,
                null,
                null,
                null,
                null);
        //by providing no projection and no selection we get all entries, all columns

        int wordColIndex = cursor.getColumnIndex(UserDictionary.Words.WORD);
        int freqColIndex = cursor.getColumnIndex(UserDictionary.Words.FREQUENCY);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            String word = cursor.getString(wordColIndex);
            int frequency = cursor.getInt(freqColIndex);
            entries  = entries + word + " : " + frequency + "\n";
            cursor.moveToNext();
        }

        // you don't tug on superman's cape, you don't spit into the wind, and you don't leave your cursor open
        cursor.close();

        return entries;
    }

    private String getPingPongPlayers() {
        String players = new String();

        Uri contentUri = Uri.parse("content://com.bignerdranch.pingpong.provider");

        // Queries the user dictionary and returns results
        Cursor cursor = getContentResolver().query(
                contentUri,
                null,
                null,
                null,
                null);
        //by providing no projection and no selection we get all entries, all columns

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            // normally we would have to get the column indexes from the PingPongContract class;
            String name = cursor.getString(0);
            int score = cursor.getInt(1);
            players = players + name + " : " + score + "\n";
            cursor.moveToNext();
        }

        cursor.close();

        return players;
    }
}

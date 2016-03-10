package com.example.asderfers.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DataBase extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_WORDS = "words";
    private static final String KEY_ID="id";
    private static final String KEY_WORD="word";
    private static final String KEY_MEAN="meaning";
    private static final String KEY_RATIO="ratio";
    private static final String KEY_IMAGE="image";
    private static final String DATABASE_NAME = "contactsManager";
    public DataBase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_WORDS = "CREATE TABLE " + TABLE_WORDS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_WORD + " TEXT,"
                + KEY_MEAN + " TEXT" + KEY_RATIO +" INTEGER"+ KEY_IMAGE +" BLOB"+ ")";
        db.execSQL(CREATE_WORDS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_WORDS);

        onCreate(db);
    }
    void addWord(words word) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_WORD, word.getWord());
        values.put(KEY_MEAN, word.getMean());
        values.put(KEY_RATIO, word.getRatio());
        values.put(KEY_IMAGE, word.getImageData());


        // Inserting Row
        db.insert(TABLE_WORDS, null, values);
        db.close(); // Closing database connection
    }

    words getWord(int id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor =db.query(TABLE_WORDS,new String[]{KEY_ID,KEY_WORD,KEY_MEAN,KEY_RATIO,KEY_IMAGE},KEY_ID +"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
         cursor.moveToFirst();

            words word =new words(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getBlob(4));
            return word;

    }
    public List<words> getAllWords()
    {
        List<words> wordsList =new ArrayList<words>();
        String selectQuery ="SELECT  * FROM "+TABLE_WORDS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                words word =new words(Integer.parseInt(cursor.getString(0)),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getBlob(4));
           wordsList.add(word);
            } while (cursor.moveToNext());
        }
return wordsList;
    }




}

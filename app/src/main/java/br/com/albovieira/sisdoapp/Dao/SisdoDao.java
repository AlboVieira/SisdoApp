package br.com.albovieira.sisdoapp.Dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by albov on 29/08/2015.
 */
public class SisdoDao {

    private DatabaseHelper helper;
    private SQLiteDatabase db;

    public SisdoDao(Context context){
        helper = new DatabaseHelper(context);
    }

    protected SQLiteDatabase getDb(){
        if (db == null){
            db = helper.getWritableDatabase();
        }
        return db;
    }

    public void close(){
        helper.close();
    }
}

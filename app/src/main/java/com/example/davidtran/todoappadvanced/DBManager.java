package com.example.davidtran.todoappadvanced;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by davidtran on 6/7/17.
 */

public class DBManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TaskDB.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE Task" + " (" +
                    "ID INTEGER PRIMARY KEY," +

                    "Title TEXT" +
                    "Description TEXT" +
                    "DueDate DATE" +
                    "String Detail;\n" +
                    "String PriorityLevel;\n" +
                    "Status;"


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;

    public DBManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
    public void saveTasktoDB(Task task){

    }
}

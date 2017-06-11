package com.example.davidtran.todoappadvanced;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


/**
 * Created by davidtran on 6/7/17.
 */


public class DBManager extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "TaskDB.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS Tasks" + " (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "Title TEXT," +
                    "DueDate Text," +
                    "DueTime Text,"+
                    "Detail Text," +
                    "PriorityLevel INTEGER," +
                    "Status NUMBERIC)" ;



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS Tasks";

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
    public boolean saveTasktoDB(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        try {
            contentValues.put("Title",task.getItemTitle());
            contentValues.put("DueDate", task.getDueDate());
            contentValues.put("DueTime", task.getDueTime());
            contentValues.put("Detail",task.getDetail());
            contentValues.put("PriorityLevel", task.getPriorityLevel());
            contentValues.put("Status",task.getStatus());

            db.insert("Tasks",null,contentValues);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public boolean deleteTaskfromDB(int _ID){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();

        db.delete("Tasks","ID =" + _ID,null);
        return true;
    }

   public ArrayList<Task> FilterTasks(int id){
       int index = 0;
       ArrayList<Task> taskList = new ArrayList<Task>();

       SQLiteDatabase db = this.getReadableDatabase();
       Cursor res = db.rawQuery("Select * from Tasks where ID = "+id,null);
       res.moveToFirst();

       while(res.isAfterLast()==false){
           boolean status = res.getInt(6) >0;
           taskList.add(new Task(res.getInt(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getInt(5),status));
       }
       return taskList;
   }
    public ArrayList<Task> getTaskList(){
        int index = 0;
        ArrayList<Task> taskList = new ArrayList<Task>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("Select * from Tasks",null);
        res.moveToFirst();

        while(res.isAfterLast()==false){

            int _ID = res.getInt(0);
            String Name = res.getString(1);
            String Date = res.getString(2);
            String Time = res.getString(3);
            String Detail = res.getString(4);
            int Priority = res.getInt(5);
            boolean status = res.getInt(6) >0;
            taskList.add(new Task(_ID,Name,Date,Time,Detail,Priority,status));
            Log.i("My log: number of task:",""+index++);
            res.moveToNext();
        }
        Log.i("My log: Task list:","");
        for (Task t:taskList
             ) {
            Log.i("My log:","name:"+t.getItemTitle());
        }

        return taskList;
    }



}


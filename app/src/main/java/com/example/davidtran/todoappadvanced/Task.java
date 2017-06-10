package com.example.davidtran.todoappadvanced;

import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by davidtran on 5/25/17.
 */

public class Task extends AppCompatActivity{


    private String ItemTitle;
    private String DueDate;
    private String DueTime;
    private String Detail;
    private int PriorityLevel;
    private boolean Status;

    public String getDueTime() {
        return DueTime;
    }
    public String getItemTitle() {
        return ItemTitle;
    }

    public String getDueDate() {
        return DueDate;
    }

    public String getDetail() {
        return Detail;
    }

    public int getPriorityLevel() {
        return PriorityLevel;
    }

    public boolean getStatus() {
        return Status;
    }

    public Task(){
          ItemTitle = "";
          DueDate = "none";
          DueTime = "none";
          Detail = "";
          PriorityLevel=2;      // 0 is low, 1 is medium, 2 is high
          Status = true;
    }

    public Task(String ItemTitle, String DueDate,String DueTime,String Detail,int PriorityLevel,boolean Status) {

        this.ItemTitle = ItemTitle;
        this.DueDate = DueDate;
        this.DueTime = DueTime;
        this.Detail = Detail;
        this.PriorityLevel = PriorityLevel;
        this.Status = Status;


    }



}

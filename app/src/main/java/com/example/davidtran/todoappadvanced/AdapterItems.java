package com.example.davidtran.todoappadvanced;

import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by davidtran on 5/25/17.
 */

public class AdapterItems extends AppCompatActivity{

    private String ItemTitle;
    private String Description;
    private Date DueDate;
    private String Detail;
    private String PriorityLevel;
    private boolean Status;

    public String getItemTitle() {
        return ItemTitle;
    }

    public String getDescription() {
        return Description;
    }

    public Date getDueDate() {
        return DueDate;
    }

    public String getDetail() {
        return Detail;
    }

    public String getPriorityLevel() {
        return PriorityLevel;
    }

    public boolean isStatus() {
        return Status;
    }



    public AdapterItems(){
        ItemTitle = "";
        Description = "";
       // DueDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
          Detail = "";
          PriorityLevel="";
          Status = true;
    }


    public AdapterItems(String ItemTitle,String PriorityLevel) {

        this.ItemTitle = ItemTitle;
        this.PriorityLevel = PriorityLevel;


    }

}

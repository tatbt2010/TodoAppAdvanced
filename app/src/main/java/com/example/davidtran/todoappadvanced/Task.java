package com.example.davidtran.todoappadvanced;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by davidtran on 5/25/17.
 */

public class Task extends AppCompatActivity implements Parcelable {

    private int _ID;



    private String ItemTitle;
    private String DueDate;
    private String DueTime;
    private String Detail;
    private int PriorityLevel;
    private boolean Status;

    public void set_ID(int _ID) { this._ID = _ID; }

    public void setItemTitle(String itemTitle) {
        ItemTitle = itemTitle;
    }

    public void setDueDate(String dueDate) {
        DueDate = dueDate;
    }

    public void setDueTime(String dueTime) {
        DueTime = dueTime;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public void setPriorityLevel(int priorityLevel) {
        PriorityLevel = priorityLevel;
    }

    public void setStatus(boolean status) {
        Status = status;
    }


    public int get_ID() { return _ID; }
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
    public Task(int _ID,String ItemTitle, String DueDate,String DueTime,String Detail,int PriorityLevel,boolean Status) {

        this._ID =_ID;
        this.ItemTitle = ItemTitle;
        this.DueDate = DueDate;
        this.DueTime = DueTime;
        this.Detail = Detail;
        this.PriorityLevel = PriorityLevel;
        this.Status = Status;


    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this._ID);
        dest.writeString(this.ItemTitle);
        dest.writeString(this.DueDate);
        dest.writeString(this.DueTime);
        dest.writeString(this.Detail);
        dest.writeInt(this.PriorityLevel);
        dest.writeByte(this.Status ? (byte) 1 : (byte) 0);
    }

    protected Task(Parcel in) {
        this._ID = in.readInt();
        this.ItemTitle = in.readString();
        this.DueDate = in.readString();
        this.DueTime = in.readString();
        this.Detail = in.readString();
        this.PriorityLevel = in.readInt();
        this.Status = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Task> CREATOR = new Parcelable.Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel source) {
            return new Task(source);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };
}

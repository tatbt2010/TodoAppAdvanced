package com.example.davidtran.todoappadvanced;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by davidtran on 6/6/17.
 */



public class EditItemFragment extends Fragment {

    Button setDateBtn;
    Button setTimeBtn;
    ListView listView;
    EditText txtDetail;
    EditText txtTaskName;
    TextView tvDate;
    TextView tvTime;
    Spinner spPriority;
    CheckBox cbisFinish;

    DBManager dbManager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.layout_edititem, container, false);
        setDateBtn = (Button) view.findViewById(R.id.setDateBtn);
        setTimeBtn = (Button) view.findViewById(R.id.setTimeBtn);
        //listView = (ListView) view.findViewById(R.id.ListItem);
        txtTaskName = (EditText) view.findViewById(R.id.txtTaskName);
        tvDate = (TextView) view.findViewById(R.id.tv_dateset);
        tvTime = (TextView) view.findViewById(R.id.tv_timeset);
        txtDetail = (EditText) view.findViewById(R.id.txtTaskDetail);
        spPriority = (Spinner) view.findViewById(R.id.sp_Priority);
        cbisFinish = (CheckBox) view.findViewById(R.id.cb_Finish);
        dbManager = new DBManager(getActivity());
        

        setHasOptionsMenu(true);

        setDateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(ft, "dialog");

            }
        });
        setTimeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(ft, "dialog");
            }
        });
        return view;
    }


   @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.Search).setVisible(false); // You can change the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
        menu.findItem(R.id.AddItem).setVisible(false);
        menu.findItem(R.id.SaveItem).setVisible(true);
        menu.findItem(R.id.CancelEdit).setVisible(true);
   }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.SaveItem: saveTask();
                                backListView();
                                return  true;

            case R.id.CancelEdit:
                                backListView();
                                return  true;
            default:return super.onOptionsItemSelected(item);

        }

    }
    private boolean saveTask(){
        String Name = txtTaskName.getText().toString();
        String Date = tvDate.getText().toString();
        String Time = tvTime.getText().toString();
        String Detail = txtDetail.getText().toString();
        Boolean isFinish =  cbisFinish.isChecked();
        int Priority = (int) spPriority.getSelectedItemId();
        Log.i("My log: Task info to save:","\nName:" + Name + "\n" +
                "Date:" + Date + "\n" + "Time:" + Time + "\nDetail:" + Detail + "\nPriority:" + Priority + "\nisFinish" + isFinish +
                "\n--------------------------------------");
        Task task = new Task(Name,Date,Time,Detail,Priority,isFinish);
        dbManager.saveTasktoDB(task);
        return true;

    }
    private void backListView(){
        Fragment fragment = new ListItemFragment();

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
}
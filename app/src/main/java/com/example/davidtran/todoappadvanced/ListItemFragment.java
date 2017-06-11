package com.example.davidtran.todoappadvanced;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by davidtran on 6/7/17.
 */

public class ListItemFragment extends Fragment{

    ArrayList<Task> arrayList;
    ListView listView;
    DBManager dbManager;
    View view;
    ArrayList<Task> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_listitem, container, false);
        listView = (ListView) view.findViewById(R.id.ListItem);
        this.view = view;
        arrayList = new ArrayList<Task>();
        dbManager = new DBManager(getActivity());
        getListTask();
        setupListViewListener();
        return view;
    }
    private boolean getListTask(){
        MyCustomAdapter myCustomAdapter;
        taskList = dbManager.getTaskList();
        myCustomAdapter = new MyCustomAdapter(taskList);
        listView.setAdapter(myCustomAdapter);
        return true;
    }
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<Task> listnewsDataAdapter;

        public MyCustomAdapter(ArrayList<Task> listnewsDataAdapter){
            this.listnewsDataAdapter = listnewsDataAdapter;

        }
        @Override
        public int getCount() {
            return listnewsDataAdapter.size();
        }

        @Override
        public Object getItem(int position) {
            return taskList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater mInflater = getActivity().getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_item,null);

            final Task s = listnewsDataAdapter.get(position);
            String Title = s.getItemTitle();
            int Priority = s.getPriorityLevel();
            String sPriority = "";
            switch (Priority){
                case 0: sPriority = "Low"; break;
                case 1: sPriority = "Medium"; break;
                case 2: sPriority = "High"; break;
                default: sPriority = "";
            }

            TextView txtitemTitle = (TextView) myView.findViewById(R.id.itemTitle);
            txtitemTitle.setText(Title);

            TextView txtPriority = (TextView) myView.findViewById(R.id.Priority);

            txtPriority.setText(sPriority);


            return myView;
        }

    }
    private void setupListViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task task = (Task) parent.getItemAtPosition(position);
                openTaskDetail(task,position);
                Toast.makeText(getActivity(),"item:"+id + "short clicked",Toast.LENGTH_SHORT).show();

            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(),"item:"+id + "long clicked",Toast.LENGTH_SHORT).show();
               /* arrayList.remove(position);
                listView.invalidateViews();
                // writeItems();     // write database
                */
                return true;
            }
        });
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.Search).setVisible(true); // You can change the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
        menu.findItem(R.id.AddItem).setVisible(true);
        menu.findItem(R.id.SaveItem).setVisible(false);
        menu.findItem(R.id.CancelEdit).setVisible(false);
    }
    private void openTaskDetail(final Task task, final int pos) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        String status ="";
        status = task.getStatus() == true?"Yes":"No";

        alertDialogBuilder.setTitle(task.getTitle());
        alertDialogBuilder.setMessage("Finished:"+ status +"\n\nDue Date: " + task.getDueDate() + "\n\nDue Time: " + task.getDueTime()+ "\n\nDETAIL:\n " + task.getDetail());
        // set Close message
        alertDialogBuilder.setPositiveButton("Close",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dialog.cancel();
            }
        });
        // set Task finished
        alertDialogBuilder.setNegativeButton("Mark as finished",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                dbManager.deleteTaskfromDB(task.get_ID());
                task.setStatus(true);
                dbManager.saveTasktoDB(task);
            }
        });
       // Set to Edit Task
        alertDialogBuilder.setNeutralButton("Edit",new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int id) {
                Bundle bundle=new Bundle();
                bundle.putParcelable("TaskToEdit",taskList.get(pos));
                //set Fragmentclass Arguments
                EditItemFragment fragment= new EditItemFragment();
                fragment.setArguments(bundle);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, fragment).commit();

            }
        });


        final AlertDialog alertDialog = alertDialogBuilder.create();
        if(task.getStatus()) {
            alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    //alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.GRAY);
                    alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setEnabled(false);
                }
            });
        }
        // show alert
        alertDialog.show();
    }


}

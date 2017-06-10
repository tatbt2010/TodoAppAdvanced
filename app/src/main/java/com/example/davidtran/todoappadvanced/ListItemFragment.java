package com.example.davidtran.todoappadvanced;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by davidtran on 6/7/17.
 */

public class ListItemFragment extends Fragment{

    ArrayList<Task> arrayList;
    ListView listView;
    DBManager dbManager;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_listitem, container, false);
        listView = (ListView) view.findViewById(R.id.ListItem);
        this.view = view;
        arrayList = new ArrayList<Task>();
        dbManager = new DBManager(getActivity());
        getListTask();
        return view;
    }
    private boolean getListTask(){
        MyCustomAdapter myCustomAdapter;
        ArrayList<Task> taskList = dbManager.getTaskList();
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
            return null;
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
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        menu.findItem(R.id.Search).setVisible(true); // You can change the state of the menu item here if you call getActivity().supportInvalidateOptionsMenu(); somewhere in your code
        menu.findItem(R.id.AddItem).setVisible(true);
        menu.findItem(R.id.SaveItem).setVisible(false);
        menu.findItem(R.id.CancelEdit).setVisible(false);
    }

}

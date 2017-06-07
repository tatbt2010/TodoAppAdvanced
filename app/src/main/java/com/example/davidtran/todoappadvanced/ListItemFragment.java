package com.example.davidtran.todoappadvanced;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by davidtran on 6/7/17.
 */

public class ListItemFragment extends Fragment{

    ArrayList<AdapterItems> arrayList;
    ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_listitem, container, false);

        listView = (ListView) view.findViewById(R.id.ListItem);
        arrayList = new ArrayList<AdapterItems>();
        MyCustomAdapter myCustomAdapter;

        arrayList.add(new AdapterItems("code nua code mai","High"));
        arrayList.add(new AdapterItems("code nua code mai 1","Normal"));
        arrayList.add(new AdapterItems("code nua code mai 2","Low"));

        for (AdapterItems a:arrayList) {
            Log.i("My log:",""+a.getItemTitle()+","+a.getPriorityLevel());
        }
        myCustomAdapter = new MyCustomAdapter(arrayList);
        listView.setAdapter(myCustomAdapter);

        return view;
    }
    private class MyCustomAdapter extends BaseAdapter {
        public ArrayList<AdapterItems> listnewsDataAdapter;



        public MyCustomAdapter(ArrayList<AdapterItems> listnewsDataAdapter){
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

            final AdapterItems s = listnewsDataAdapter.get(position);

            TextView txtitemTitle = (TextView) myView.findViewById(R.id.itemTitle);
            txtitemTitle.setText(s.getItemTitle());

            TextView txtPriority = (TextView) myView.findViewById(R.id.Priority);
            txtPriority.setText(s.getPriorityLevel());


            return myView;
        }
    }
}

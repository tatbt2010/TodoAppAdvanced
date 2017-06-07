package com.example.davidtran.todoappadvanced;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Task> arrayList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showListItem();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
        MenuItem searchItem = menu.findItem(R.id.Search);

        try {

            SearchView searchView = (SearchView) searchItem.getActionView();
            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(getApplicationContext(),query,Toast.LENGTH_LONG).show();
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
        catch (Exception e){
            Log.i("My log:",e.toString());

        }


        // SearchView searchView = (SearchView) menu.findItem(R.id.Search).getActionView();

        // return super.onCreateOptionsMenu(menu);
        return true;
    }



    private void setupListViewListener(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(MainActivity.this, EditItemFragment.class);
                i.putExtra("itemdata", arrayList.get(position).getItemTitle());
                i.putExtra("posItem",position);
                startActivityForResult(i, 2);
            }
        });
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                arrayList.remove(position);
                listView.invalidateViews();
               // writeItems();     // write database
                return true;
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Search:
                Toast.makeText(getApplicationContext(),"Open Search",Toast.LENGTH_SHORT).show();
                return  true;

            case R.id.AddItem:
                Toast.makeText(getApplicationContext(),"Add more item",Toast.LENGTH_SHORT).show();
                addItemListener();
                return  true;
            default:return super.onOptionsItemSelected(item);

        }

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
            LayoutInflater mInflater = getLayoutInflater();
            View myView = mInflater.inflate(R.layout.layout_item,null);

            final Task s = listnewsDataAdapter.get(position);

            TextView txtitemTitle = (TextView) myView.findViewById(R.id.itemTitle);
            txtitemTitle.setText(s.getItemTitle());

            TextView txtPriority = (TextView) myView.findViewById(R.id.Priority);
            txtPriority.setText(s.getPriorityLevel());


            return myView;
        }
    }
    private void showListItem(){
        Fragment fragment = new ListItemFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
    private void addItemListener(){
        Fragment fragment = new EditItemFragment();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, fragment).commit();
    }
}

package com.example.davidtran.todoappadvanced;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

/**
 * Created by davidtran on 6/6/17.
 */

public class NewItemActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.newitemmenu,menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.SaveItem:
                Toast.makeText(getApplicationContext(),"Save",Toast.LENGTH_SHORT).show();
                return  true;

            case R.id.Cancel:
                Toast.makeText(getApplicationContext(),"Cancel",Toast.LENGTH_SHORT).show();
                return  true;
            default:return super.onOptionsItemSelected(item);

        }

    }
}

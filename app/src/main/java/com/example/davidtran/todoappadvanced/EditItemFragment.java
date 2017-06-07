package com.example.davidtran.todoappadvanced;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by davidtran on 6/6/17.
 */



public class EditItemFragment extends Fragment {

    Button setDateBtn;
    Button setTimeBtn;
    ListView listView;
    EditText txtDetail;

   /* @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        //super.onCreateOptionsMenu(menu, inflater);
       // MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu,menu);
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.layout_edititem, container, false);
        setDateBtn = (Button) view.findViewById(R.id.setDateBtn);
        setTimeBtn = (Button) view.findViewById(R.id.setTimeBtn);
        listView = (ListView) view.findViewById(R.id.ListItem);
        txtDetail = (EditText) view.findViewById(R.id.txtTaskDetail);

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




}
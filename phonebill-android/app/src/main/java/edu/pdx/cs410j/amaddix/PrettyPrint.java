package edu.pdx.cs410j.amaddix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class PrettyPrint extends AppCompatActivity {

    private ArrayAdapter<String> content;

    /**
     * ONCREATE - when pretty print activity is launched, retrieve phone bill info sent from the
     * main activity and display with listview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pretty_print);
        ListView listview = findViewById(R.id.billContents);

        ArrayList<String> list = (ArrayList<String>) getIntent().getStringArrayListExtra("print");
        if(list==null) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
        content = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(content);
    }

}
package edu.pdx.cs410j.amaddix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private ArrayAdapter<String> content;

    /**
     * ONCREATE- when activity search is launched, retrieve phone call info sent from the
     * main activity and display with listview
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ListView listview = findViewById(R.id.searchResult);

        ArrayList<String> list = (ArrayList<String>) getIntent().getStringArrayListExtra("search");
        if (list == null) {
            setResult(RESULT_CANCELED, null);
            finish();
        }
        content = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        listview.setAdapter(content);
    }
}

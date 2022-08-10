package edu.pdx.cs410j.amaddix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddPhoneCall extends AppCompatActivity {

    //final private EditText customer = (EditText) findViewById(R.id.customerName);
    public static final String EXTRA_NAME = "customerName";
    public static final String EXTRA_CALLER = "caller";
    public static final String EXTRA_CALLEE = "callee";
    public static final String EXTRA_START = "start";
    public static final String EXTRA_END = "end";
    //private static final String EXTRA_CALLER = "caller";
   // private static final String EXTRA_CALLEE = "callee";
  //  private static final String EXTRA_START = "start";
  //  private static final String EXTRA_END = "end";



    private static final String EXTRA_CALL = "extraCall";

    private String customerName =null;
    private String caller =null;
    private String callee=null;
    private String start=null;
    private String end=null;

    /**
     * ONCREATE - launches addPhoneCall activity
     * @param savedInstanceState - bundle type
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_call);
    }

    /**
     * CREATECALL - gets information read by user containing a customerName, caller, callee, startTime,
     * and EndTime and sends it back to the main activity to save into the phoneBillMap
     * @param view -view type
     */
    public void CreateCall(View view) {
        EditText customerName = findViewById(R.id.customerName);
        EditText caller = findViewById(R.id.caller);
        EditText callee = findViewById(R.id.callee);
        EditText start = findViewById(R.id.start);
        EditText end = findViewById(R.id.end);

        this.customerName = customerName.getText().toString();
        this.caller = caller.getText().toString();
        this.callee = callee.getText().toString();
        this.start = start.getText().toString();
        this.end = end.getText().toString();
        Intent data = new Intent();

        if(this.customerName.matches("") || this.caller.matches("") || this.callee.matches("") || this.start.matches("") || this.end.matches("")){
            Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
        }
        else {
            data.putExtra(EXTRA_NAME, this.customerName);
            data.putExtra(EXTRA_CALLER, this.caller);
            data.putExtra(EXTRA_CALLEE, this.callee);
            data.putExtra(EXTRA_START, this.start);
            data.putExtra(EXTRA_END, this.end);


            setResult(RESULT_OK, data);
            finish();
        }
    }
}
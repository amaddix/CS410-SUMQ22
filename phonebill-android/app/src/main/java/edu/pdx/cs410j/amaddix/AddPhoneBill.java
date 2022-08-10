package edu.pdx.cs410j.amaddix;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.Serializable;

public class AddPhoneBill extends AppCompatActivity {

    public static final String EXTRA_NAME = "customerName";
    private String customerName;

    /**
     * ONCREATE- launches addPhoneBill activity
     * @param savedInstanceState - bundle type
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_phone_bill);
    }


    /**
     * CREATEBILL - gets information read by user containing a customerName and sends it back to
     * the main activity to save into the phoneBillMap
     * @param view - view type
     */
    public void CreateBill(View view) {
        EditText customerName = findViewById(R.id.customerName);
        this.customerName = customerName.getText().toString();
        Intent data = new Intent();
        if(this.customerName.matches("")){
            Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
        }
        else {
            data.putExtra(EXTRA_NAME, this.customerName);
            setResult(RESULT_OK, data);
            finish();
        }
    }
}
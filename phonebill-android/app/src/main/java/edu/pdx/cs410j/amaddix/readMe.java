package edu.pdx.cs410j.amaddix;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class readMe extends AppCompatActivity {

    /**
     * ONCREATE -   launches readme activity, with description of program
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_me);

        String info = "\t \t - Ashley Maddix - CS410Java Summer Q - \n\t\t\t\t\t\t\t\t\t\t\t\tPhoneBill Project 5\n\n\n\n" +
        "This project handles all the prior functionalities of all prior projects this term, which include :\n\n\n" +
    "\t\t -The ability to read files from a file 'billList.txt' (*in order to not lose the pre-existing " +
    "data in that text file, do so before adding any phonebills or phonecalls*)\n\n" +
    "\t\t -The ability to add multiple phonebills \n\n" +
    "\t\t -The ability to add multiple phone calls to a pre-existing phonebill \n\n" +
    "\t\t -Display a phonebill matching a given name in a readable format\n\n" +
    "\t\t -Search for phonecalls between two given times, assuming a the given name passed matches the" +
    "name of a pre-existing phonebill\n\n"+
    "\t\t - When adding a phonebill or phone call the program will save that information to a file 'billList.txt'";

        TextView tview = findViewById(R.id.textView3);
        tview.setText(info);
    }
}
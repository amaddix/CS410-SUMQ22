package edu.pdx.cs410j.amaddix;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;


public class MainActivity extends AppCompatActivity {

    private static final int GET_NAME = 42;
    private static final int GET_CALL = 43;
    private static final String EXTRA_PRINT = "print";
    private static final String EXTRA_SEARCH = "search";
    private final Map<String, PhoneBill> phoneBillMap = new HashMap<String, PhoneBill>();
    private ArrayAdapter<String> contents;

    /**
     * ON CREATE- on program launch
     * @param savedInstanceState - bundle type
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * READFILE - when button to read files is clicked, function to read from file is called
     * @param view - view type
     */

    public void readFile(View view) {
        try {
            readFile();
            Toast.makeText(MainActivity.this, "Successfully entered phonebill contents!", Toast.LENGTH_LONG).show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * READFILE - activity of readFile button, reads from existing file
     * @throws IOException if file doesn't exist of unable to parse
     */

    private void readFile() throws IOException {

        File dataDirectory = this.getDataDir();
        File billFile = new File(dataDirectory, "BillList.txt");
        FileReader fr = new FileReader(billFile);
        BufferedReader br = new BufferedReader(fr);
        StringBuffer input = new StringBuffer();
        String line;
        String namePattern = "^[a-zA-Z0-9 ]+$";
        String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9]) ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";
        String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";
        PhoneBill tbill=null;
        Boolean firstNumber = false;
        Boolean firstDate = false;
        String caller=null;
        String callee=null;
        String start=null;
        String end=null;
        while((line = br.readLine()) != null){
            if(line.matches(namePattern)&& (line.matches(phonePattern))==false) {
                tbill = new PhoneBill(line);
                this.phoneBillMap.put(line, tbill);
            }
            else if(line.matches(phonePattern) && firstNumber == false){
                caller = line;
                firstNumber = true;
            }
            else if(line.matches(phonePattern) && firstNumber == true){
                callee = line;
            }
            else if(line.matches(datePattern) && firstDate == false){
                start=line;
                firstDate=true;
            }
            else if(line.matches(datePattern) && firstDate == true){
                end=line;
            }

            if(tbill != null && caller != null && callee != null && start != null && end != null){
                PhoneCall tcall = new PhoneCall(tbill.getCustomer(), caller, callee, start, end);
                tbill.addPhoneCall(tcall);
                this.phoneBillMap.put(tbill.getCustomer(), tbill);
                caller=null;
                callee=null;
                start=null;
                end=null;
                firstNumber=false;
                firstDate=false;

            }
        }
        if(tbill == null){
            Toast.makeText(this, "FILE COULD NOT BE READ", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * PRETTY PRINT - when button to display bill is clicked, function checks if bill with given name
     * exists. If it does exist it calls activity to display phonebill info on new page
     * @param view - view type
     */

    public void PrettyPrint(View view) {
        Intent intent = new Intent(this, PrettyPrint.class);
        EditText customerName = findViewById(R.id.customerName);

        if(customerName.getText().toString().matches("")){
            Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
        }

        else if(customerName != null && this.phoneBillMap != null){
            PhoneBill tbill = phoneBillMap.get(customerName.getText().toString());
            if(tbill == null){
                Toast.makeText(this, "NO MATCHING BILL OF NAME : " + customerName.getText().toString(), Toast.LENGTH_LONG).show();
                }
            else {
                ArrayList<String> list = new ArrayList<String>();
                String info = "Customer Name :\t" + tbill.getCustomer() + "\n\n\n";
                if (tbill.getCallNum() == 0) {
                    list.add(info);
                } else {
                    for (int i = 0; i < tbill.getCallNum(); i++) {
                        int shourtomin = 0;
                        int ehourtomin = 0;
                        String stime = tbill.getStartTime(i);
                        String stArray[] = stime.split(" ");
                        //System.out.println("what what " + stime + stArray[0]);
                        if (stArray[1].matches("([0-9]|[012][0-9]):[0-6][0-9]$")) {
                            String sttime[] = stArray[1].split(":");
                            shourtomin = Integer.parseInt(sttime[0]);
                            shourtomin = shourtomin * 60; //convert hours to min
                            shourtomin = shourtomin + Integer.parseInt(sttime[1]); // add orginal min on
                        }
                        String etime = tbill.getEndTime(i);
                        String endArray[] = etime.split(" ");
                        if (endArray[1].matches("([0-9]|[012][0-9]):[0-6][0-9]$")) {
                            String ettime[] = endArray[1].split(":| ");
                            ehourtomin = Integer.parseInt(ettime[0]);
                            ehourtomin = ehourtomin * 60; //convert hours to min
                            ehourtomin = ehourtomin + Integer.parseInt(ettime[1]); // add orginal min on
                        }
                        int totalTime = 0;
                        if (stArray[2].matches(endArray[2])) {
                            totalTime = ehourtomin - shourtomin;
                        } else {
                            totalTime = (720 - shourtomin) + ehourtomin;
                        }

                        info = info + "\nCaller : \t\t" + tbill.getCaller(i) + "\nCallee : \t\t" +
                                tbill.getCallee(i) + "\nFrom :\t\t" + tbill.getStartTime(i) + "\nto :\t\t" +
                                tbill.getEndTime(i) + "\nFor a duration of :\t\t" + totalTime + " minutes\n\n";
                    }
                    list.add(info);
                }


                intent.putExtra(EXTRA_PRINT, list);
                //  startActivity(intent);

                startActivity(intent);
            }
        }
    }

    /**
     * SEARCHCALLS - when search button is clicked and has received requested information, it
     * confirms phonebill with the given name exists. If it does exist, get string containing all
     * phonecalls between the two given times. Launch activity and display info.
     * @param view - view type
     * @throws ParseException throws if parse exception is found
     */
    public void SearchCalls(View view) throws ParseException {
        String namePattern = "^[a-zA-Z0-9 ]+$";
        String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9]) ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";
        String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";

        Intent intent = new Intent(this, Search.class);
        EditText customerName = findViewById(R.id.search_name);
        EditText stimeEditor = findViewById(R.id.search_stime);
        EditText etimeEditor = findViewById(R.id.search_etime);
        if(customerName.getText().toString().matches("") || stimeEditor.getText().toString().matches("") || etimeEditor.getText().toString().matches("")){
            Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
        }
        else {
            String start = stimeEditor.getText().toString();
            String end = etimeEditor.getText().toString();

            if (start.matches(datePattern) == false) {
                Toast.makeText(this, "DATE FORMAT INCORRECT -\n 'MM/dd/yyyy hh:mm am/pm'", Toast.LENGTH_LONG).show();
            } else if (end.matches(datePattern) == false) {
                Toast.makeText(this, "DATE FORMAT INCORRECT -\n 'MM/dd/yyyy hh:mm am/pm'", Toast.LENGTH_LONG).show();
            } else {

                SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                SimpleDateFormat format2 = new SimpleDateFormat("M/d/yy h:mm a");
                Date compareStart = format1.parse(start);
                Date compareEnd = format1.parse(end);
                Date phoneStart;
                Date phoneEnd;

                if (customerName != null && this.phoneBillMap != null) {
                    PhoneBill tbill = phoneBillMap.get(customerName.getText().toString());
                    if (tbill == null) {
                        Toast.makeText(this, "NO MATCHING BILL OF NAME : " + customerName.getText().toString(), Toast.LENGTH_LONG).show();
                    } else {
                        ArrayList<String> list = new ArrayList<String>();
                        String info = "";
                        String tname = tbill.getCustomer();
                        if (tbill.getCallNum() == 0) {
                            Toast.makeText(this, "NO PHONECALLS UNDER THE NAME " + tname, Toast.LENGTH_LONG).show();
                            //list.add(tname);
                        } else {
                            for (int i = 0; i < tbill.getCallNum(); i++) {
                                phoneStart = format2.parse(tbill.getStartTime(i));
                                phoneEnd = format2.parse(tbill.getEndTime(i));

                                if ((compareStart.compareTo(phoneStart) <= 0) && (compareEnd.compareTo(phoneEnd) >= 0)) {

                                    int shourtomin = 0;
                                    int ehourtomin = 0;
                                    String stime = tbill.getStartTime(i);
                                    String stArray[] = stime.split(" ");
                                    //System.out.println("what what " + stime + stArray[0]);
                                    if (stArray[1].matches("([0-9]|[012][0-9]):[0-6][0-9]$")) {
                                        String sttime[] = stArray[1].split(":");
                                        shourtomin = Integer.parseInt(sttime[0]);
                                        shourtomin = shourtomin * 60; //convert hours to min
                                        shourtomin = shourtomin + Integer.parseInt(sttime[1]); // add orginal min on
                                    }
                                    String etime = tbill.getEndTime(i);
                                    String endArray[] = etime.split(" ");
                                    if (endArray[1].matches("([0-9]|[012][0-9]):[0-6][0-9]$")) {
                                        String ettime[] = endArray[1].split(":| ");
                                        ehourtomin = Integer.parseInt(ettime[0]);
                                        ehourtomin = ehourtomin * 60; //convert hours to min
                                        ehourtomin = ehourtomin + Integer.parseInt(ettime[1]); // add orginal min on
                                    }
                                    int totalTime = 0;
                                    if (stArray[2].matches(endArray[2])) {
                                        totalTime = ehourtomin - shourtomin;
                                    } else {
                                        totalTime = (720 - shourtomin) + ehourtomin;
                                    }

                                    info = info + "\nCaller : \t\t" + tbill.getCaller(i) + "\nCallee : \t\t" +
                                            tbill.getCallee(i) + "\nFrom :\t\t" + tbill.getStartTime(i) + "\nto :\t\t" +
                                            tbill.getEndTime(i) + "\nFor a duration of :\t\t" + totalTime + " minutes\n\n";
                                }}
                            list.add(info);
                        }
                        intent.putExtra(EXTRA_SEARCH, list);
                        startActivity(intent);
                    }}}}
    }
    /**
     * ADDPHONECALL - when clicked, call startActivityForResult to get infomation from the
     * addPhoneCall activity
     * @param view - view type
     */
    public void AddPhoneCall(View view) {
        Intent intent = new Intent(this, AddPhoneCall.class);
        startActivityForResult(intent, GET_CALL);
    }

    /**
     * ADDPHONEBILL - when clicked, call startActivityForResult to get infomation from the
     * addPhoneBill activity
     * @param view -view type
     */
    public void AddPhoneBill(View view) {
        Intent intent = new Intent(this, AddPhoneBill.class);
        startActivityForResult(intent, GET_NAME);
    }

    /**
     * ONACTIVITYSTART - helps get information from the activity pages : addPhoneBill + addPhoneCall
     * @param requestCode - to determine which request to handel
     * @param resultCode - if activity was returned without error
     * @param data - data sent from activity
     */

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == GET_NAME && resultCode == RESULT_OK && data != null){
            String tname = data.getStringExtra(AddPhoneBill.EXTRA_NAME);
            if(tname == null){
                Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
            }
            else if(tname != null){

                PhoneBill tbill = null;
                if( phoneBillMap.get(tname) != null){
                    Toast.makeText(this, "THERE ALREADY EXISTS A PHONEBILL WITH THE CUSTOMER NAME "+ tname, Toast.LENGTH_LONG).show();
                }
                else {
                    tbill = new PhoneBill(tname);
                    this.phoneBillMap.put(tname, tbill);
                    try {
                        writeFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        if(requestCode == GET_CALL && resultCode == RESULT_OK && data != null) {
            String tname = data.getStringExtra(AddPhoneCall.EXTRA_NAME);
            String tcaller = data.getStringExtra(AddPhoneCall.EXTRA_CALLER);
            String tcallee = data.getStringExtra(AddPhoneCall.EXTRA_CALLEE);
            String tstart = data.getStringExtra(AddPhoneCall.EXTRA_START);
            String tend = data.getStringExtra(AddPhoneCall.EXTRA_END);


            String namePattern = "^[a-zA-Z0-9 ]+$";
            String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9]) ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";
            String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";

            if(tname.matches("") || tcaller.matches("") || tcallee.matches("") || tstart.matches("") || tend.matches("")){
                Toast.makeText(this, "ONE OR MORE REQUIRED FIELDS WERE LEFT EMPTY", Toast.LENGTH_LONG).show();
            }
            else if (tcaller.matches(phonePattern) == false) {
                Toast.makeText(this, "PHONE NUMBER FORMAT INCORRECT - \n acceptable: '3333333333' OR '333-333-3333' OR '333 333 3333'", Toast.LENGTH_LONG).show();
            } else if (tcallee.matches(phonePattern) == false) {
                Toast.makeText(this, "PHONE NUMBER FORMAT INCORRECT - \n acceptable: '3333333333' OR '333-333-3333' OR '333 333 3333'", Toast.LENGTH_LONG).show();
            } else if (tstart.matches(datePattern) == false) {
                Toast.makeText(this, "DATE FORMAT INCORRECT - \n 'MM/dd/yyyy hh:mm am/pm'", Toast.LENGTH_LONG).show();
            } else if (tend.matches(datePattern) == false) {
                Toast.makeText(this, "DATE FORMAT INCORRECT - \n 'MM/dd/yyyy hh:mm am/pm'", Toast.LENGTH_LONG).show();
            } else {

                PhoneCall tcall = new PhoneCall(tname, tcaller, tcallee, tstart, tend);

                if (tname != null) {
                    PhoneBill tbill = phoneBillMap.get(tname);
                    if (tbill == null) {
                        Toast.makeText(this, "NO MATCHING BILL OF NAME : " + tname, Toast.LENGTH_LONG).show();
                    } else {
                        tbill.addPhoneCall(tcall);
                        try {
                            writeFile();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }}}}}
    }

    /**
     * WRITEFILE - called whenever phonebillmap gets updated. Creates/Overwrites contents of file
     * 'billList.txt' with simple (un-manipulated) strings with info of the contents from all phonebills
     * @throws IOException if io exception is found
     */

    private void writeFile() throws IOException {
        File dataDirectory = this.getDataDir();
        File billFile = new File(dataDirectory, "BillList.txt");
        String info = "";
        try (PrintWriter pw = new PrintWriter(new FileWriter(billFile, false))) {
            for(PhoneBill temp : phoneBillMap.values()){
                info= info + temp.getCustomer() + "\n";
                for(int i=0; i<temp.getCallNum();i++){
                    info= info+ temp.getCaller(i) + "\n";
                    info= info+ temp.getCallee(i) + "\n";
                    info= info+ temp.getStartDate(i) + "\n";
                    info= info+ temp.getEndDate(i) + "\n";
                }
            }
            pw.println(info);
            Toast.makeText(this, "Successfully updated file info", Toast.LENGTH_LONG).show();


        } catch (IOException e) {
          //  e.printStackTrace();
            Toast.makeText(this, "FILE COULD NOT BE UPDATED - \n" + e, Toast.LENGTH_LONG).show();

        }
    }

    /**
     * README - launches activity to help give instructions about program
     * @param view - view type
     */
        public void ReadMe(View view) {
            Intent intent = new Intent(this, readMe.class);
            startActivity(intent);
    }

}
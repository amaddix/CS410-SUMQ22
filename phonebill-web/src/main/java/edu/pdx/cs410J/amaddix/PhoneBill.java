package edu.pdx.cs410J.amaddix;

        import edu.pdx.cs410J.AbstractPhoneBill;
        import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;
        import java.util.Locale;

        import java.util.ArrayList;
        import java.util.Collection;

/**
 * Phone bill - saves customer name, a call list, and the numbers of calls in the list
 */

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    //variables for a phone bill
    private final String customer;
    private PhoneCall[] call;
    private int callNum;

    //constructor with customer name, create empty array for call list

    /**
     * PHONEBILL
     * base constructor
     */

    public PhoneBill(){
        this.customer=null;
        this.call= new PhoneCall[100];
        this.callNum=0;

    }

    /**
     * PHONE BILL
     * constructor initializing with only customer
     * @param customer -string with customer name
     */

    public PhoneBill(String customer) {
        this.customer = customer;
        //creating an array of phonecalls with 30 total slots for calls
        this.call= new PhoneCall[100];
        this.callNum=0;
    }

    /**
     * constructor initializing with all variables in a phone bill
     * @param customer -string customer name
     * @param caller - string phone number
     * @param callee - string phone number
     * @param tstart -string start time of call
     * @param tend -string endtime of call
     */
    //customer name, single call log (call list =1)
    public PhoneBill (String customer, String caller, String callee, String tstart, String tend){
        this.customer=customer;
        if(this.callNum == 0) {
            this.call = new PhoneCall[100];
        }
        this.callNum = this.callNum +1;

        this.call[callNum-1]=new PhoneCall(customer,caller, callee, tstart, tend);
        // this.callNum = 1;

    }

    /**
     * PHONEBILL CONSTRUCTOR
     * @param tcall -phonecall type
     */
    public PhoneBill(PhoneCall tcall){
        this.customer = tcall.getCustomer();
        if(this.call == null){
            this.call= new PhoneCall[15];
            this.callNum = 0;
        }
        if(tcall != null) {
            this.call[callNum] = new PhoneCall(tcall);
            this.callNum = this.callNum + 1;
        }
    }

    /**
     * PHONEBILL CONSTRUCTOR
     * @param tbill - phonebill type
     */

    public PhoneBill(PhoneBill tbill){
        String tcaller = tbill.getCaller(callNum);
        String tcallee = tbill.getCallee(callNum);
        String tstime = tbill.getStartTime(callNum);
        String tetime = tbill.getEndTime(callNum);
        this.customer = tbill.getCustomer();
        if( this.callNum == 0){
            this.call = new PhoneCall[15];
        }
        this.callNum = this.callNum +1;

        this.call[callNum-1] = new PhoneCall(this.customer,tcaller,tcallee, tstime, tetime);

    }
/*
  public PhoneBill(PhoneBill bill){
    this.customer = bill.getCustomer();

    String tcaller= bill.getCaller(0);
    String tcallee = bill.getCallee(0);
    String start = bill.getStartTime(0);
    String end = bill.getEndTime(0);

    this.call[0]=new PhoneCall(tcaller, tcallee, start, end);

  }
*/
    /**
     * GETCALLER
     * @param item int
     * @return the caller string
     */

    public String getCaller(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getCaller();
    }

    /**
     * GETCALLEE
     * @param item int
     * @return the callee string
     */
    public String getCallee(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getCallee();
    }

    /**
     * RETURNSDATE
     * @param i - int
     * @return start time
     */
    public String returnSdate(int i){
        if(this.call[i] == null){
            return null;
        }
        return this.call[i].returnSdate();
    }

    /**
     * RETURNEDATE
     * @param i -int
     * @return - end date
     */
    public String returnEdate(int i){
        if(this.call[i] == null){
            return null;
        }
        return this.call[i].returnEdate();
    }

    /**
     * GET START TIME
     * @param item int
     * @return starttime string
     */
    public String getStartTime(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getBeginTimeString();
    }

    /**
     * GETENDTIME
     * @param item int
     * @return endtime string
     */
    public String getEndTime(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getEndTimeString();
    }

    /**
     * GETCUSTOMER
     * @return customer name string
     */

    @Override
    public String getCustomer() {
        return this.customer;

    }

    /**
     * GETCALL NUMBER
     * @return int w number of calls a bill has
     */
    public int getCallNum(){
        return this.callNum;
    }

    /**
     * DISPLAY
     * @return int - to show programs working correctly
     */

    public String display() {
        String tname = this.getCustomer();
        int index = 0;
        if (this.getCaller(index) != null) {
           // System.out.println("Customer  :   " + tname);
            String info = tname + "\n";
            while(index < this.callNum) {

                String tcaller = this.getCaller(index);
                String tcallee = this.getCallee(index);
                String tStart = this.returnSdate(index);
                String tend = this.returnEdate(index);
                ///
                //System.out.println("Caller : " + tcaller + "    Callee :  " + tcallee);
                //System.out.println(tStart + " - " + tend);
                info = info + tcaller+"\n"+tcallee+"\n"+tStart+"\n"+tend+"\n";
                index= index+1;

            }

            System.out.println(info);

            return info;
        }
        return null;
    }
    /**
     * ADDPHONECALL
     * adds phone call the phone bill list
     * @param temp_call - phonecall type
     */

    @Override
    public void addPhoneCall(PhoneCall temp_call) {
        //called from main- read in phone number from user.
        this.call[this.callNum]=new PhoneCall(temp_call);
        this.callNum=this.callNum+1;

        this.sorted();
        //this.display();
        //throw new UnsupportedOperationException("This method is not implemented yet");
    }

    /**
     * SORTED
     * sorts phone call in a phone call list
     */


    public void sorted() {
        String patternDash = "^([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})$";
        String patternSpace = "^([0-9]{3} [0-9]{3} [0-9]{4})$";
        for (int i = 0; i < this.callNum; i++) {
            for (int j = i + 1; j < this.callNum; j++) {

                // System.out.println("in here123 " + j " and " + this.call.length);


                Double tj=0d;
                Double ti=0d;

                if(this.call[i].getCaller().matches(patternDash)) {
                    String tstring = this.call[i].getCaller().replace("-", "");
                    ti = Double.parseDouble(tstring);
                }
                else if(this.call[i].getCaller().matches(patternSpace)) {
                    String tstring = this.call[i].getCaller().replace(" ", "");
                    ti = Double.parseDouble(tstring);
                }
                else{
                    ti = Double.parseDouble(this.call[j].getCaller());
                }
                ////////////

                if(this.call[j].getCaller().matches(patternDash)) {
                    String tstring = this.call[j].getCaller().replace("-", "");
                    tj = Double.parseDouble(tstring);
                }

                else if(this.call[j].getCaller().matches(patternSpace)) {
                    String tstring = this.call[j].getCaller().replace(" ", "");
                    tj = Double.parseDouble(tstring);
                }
                else{
                    tj = Double.parseDouble(this.call[j].getCaller());
                }

                PhoneCall tmp = null;
                if (ti > tj) {
                    tmp = this.call[i];
                    this.call[i] = this.call[j];
                    this.call[j] = tmp;
                }/*
                else if(ti == tj){
                    System.out.println("in here1");
                    String datei = this.call[i].getBeginTimeString();
                    String datej = this.call[j].getBeginTimeString();

                    SimpleDateFormat format = new SimpleDateFormat("M/d/yy, h:mm a");

                    try{
                        Date tdate1 = format.parse(datei);
                        Date tdate2 = format.parse(datej);

                        if(tdate1.compareTo(tdate2) >= 0){
                            System.out.println("in here1");
                            tmp = this.call[i];
                            this.call[i] = this.call[j];
                            this.call[j] = tmp;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }*/
            }
        }
    }


    /*
      public void sortPhoneCalls(){
        PhoneCall tarray = new PhoneCall[callNum]
        if(callNum > 1){
          //for loop to iterate array
          for(int i =0; i<callNum ; i++){
            boolean flag = false;
            for(int j=i; j<callNum; j++){
              if(this.call[i]>)
            }
          }
            //second loop for each index to compare value
            //add greater value call

          //save tarray as new phone call array
        }
      }
    */

    /**
     * GETPHONECALLS
     * @return null
     */
    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        return null;
    }
}







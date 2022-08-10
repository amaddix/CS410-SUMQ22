package edu.pdx.cs410j.amaddix;
import java.io.Serializable;
import java.util.Collection;
import edu.pdx.cs410J.AbstractPhoneBill;

        import java.util.ArrayList;
        import java.util.Collection;

/**
 * PHONEBILL CLASS - saves customer name, a call list, and the numbers of calls in the list
 */

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

    //variables for a phone bill
    private final String customer;
    private PhoneCall[] call;
    private int callNum;

    //constructor with customer name, create empty array for call list

    /**
     * PHONE BILL
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
     * PHONEBILL
     * constructor initializing with all variables in a phone bill
     * @param customer -string
     * @param caller - string
     * @param callee - string
     * @param tstart -string
     * @param tend -string
     */

    public PhoneBill (String customer, String caller, String callee, String tstart, String tend){
        this.customer=customer;
        if(this.callNum == 0) {
            this.call = new PhoneCall[100];
        }
        this.callNum = this.callNum +1;

        this.call[callNum-1]=new PhoneCall(customer,caller, callee, tstart, tend);


    }

    /**
     * PHONEBILL - initializing bill with a given phone call
     * @param tcall - phonecall type holding new call info
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
     * PHONE BILL - initializing bill with given bill information
     * @param tbill - phone bill type with value of new bill
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

    /**
     * GETCALLER
     * @param item int index of call
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
     * @param item int index of call
     * @return the callee string
     */
    public String getCallee(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getCallee();
    }

    /**
     * GETSTARTDATE
     * @param item index of call
     * @return string with true start date
     */

    public String getStartDate(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getStartDate();
    }

    /**
     * GETENDDATE
     * @param item index of call
     * @return string with true end date
     */
    public String getEndDate(int item){
        if(this.call[item] == null){
            return null;
        }
        return this.call[item].getEndDate();
    }

    /**
     * GETSTARTTIME
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
     * GETCALLNUM
     * @return int w number of calls a bill has
     */
    public int getCallNum(){
        return this.callNum;
    }

    /**
     * DISPLAY - display information in a phone bill
     * @return int - to show programs working correctly
     */

    public int display() {
        String tname = this.getCustomer();
        int index = 0;
        if (this.getCaller(index) != null) {
            System.out.println("Customer  :   " + tname);
            while(index < this.callNum) {

                String tcaller = this.getCaller(index);
                String tcallee = this.getCallee(index);
                String tStart = this.getStartTime(index);
                String tend = this.getEndTime(index);
                ///
                System.out.println("Caller : " + tcaller + "    Callee :  " + tcallee);
                System.out.println(tStart + " - " + tend);
                index= index+1;
            }
            return 1;
        }
        return 0;
    }
    /**
     * ADDPHONECALL - adds new phonecall to the phonebills call[]
     * adds phone call the phone bill list
     * @param temp_call - phonecall type with new call to add to array
     */

    @Override
    public void addPhoneCall(PhoneCall temp_call) {
        //called from main- read in phone number from user.
        this.call[this.callNum]=new PhoneCall(temp_call);
        this.callNum=this.callNum+1;

        this.sorted();
    }

    /**
     * SORTED - sorts all the phonecalls in a call list upon adding any new contents to call[]
     */
    public void sorted() {
        String patternDash = "^([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})$";
        String patternSpace = "^([0-9]{3} [0-9]{3} [0-9]{4})$";
        for (int i = 0; i < this.callNum; i++) {
            for (int j = i + 1; j < this.callNum; j++) {
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
                }
            }
        }
    }

    /**
     * GETPHONECALLS  - returns a collection of phonecalls
     * @return collection of phone calls
     */
    @Override
    public Collection<PhoneCall> getPhoneCalls() {
        throw new UnsupportedOperationException("This method is not implemented yet");
    }
}








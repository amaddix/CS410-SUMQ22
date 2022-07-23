package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AbstractPhoneBill;

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
   * base constructor
   */

  public PhoneBill(){
    this.customer=null;
    this.call= new PhoneCall[100];
    this.callNum=0;

  }

  /**
   * constructor initializing with only customer
   * @param customer -string
   */

  public PhoneBill(String customer) {
    this.customer = customer;
    //creating an array of phonecalls with 30 total slots for calls
    this.call= new PhoneCall[100];
    this.callNum=0;
  }

  /**
   * constructor initializing with all variables in a phone bill
   * @param customer -string
   * @param caller - string
   * @param callee - string
   * @param tstart -string
   * @param tend -string
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
   * @return customer name string
   */

  @Override
  public String getCustomer() {
    return this.customer;

  }

  /**
   * @return int w number of calls a bill has
   */
  public int getCallNum(){
    return this.callNum;
  }

  /**
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
   * adds phone call the phone bill list
   * @param temp_call
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
        }
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
  @Override
  public Collection<PhoneCall> getPhoneCalls() {
    //Collection<PhoneCall> col = new ArrayList<>();

    ///for(int i=0; i<callNum; i++){
    //col.add(this.call[i]);
    //}
    //return col;
    throw new UnsupportedOperationException("This method is not implemented yet");
  }
}







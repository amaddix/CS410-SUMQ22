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
    this.call= new PhoneCall[10];
    this.callNum=0;

  }

  /**
   * constructor initializing with only customer
   * @param customer
   */

  public PhoneBill(String customer) {
    this.customer = customer;
    //creating an array of phonecalls with 30 total slots for calls
    this.call= new PhoneCall[15];
    this.callNum=0;
  }

  /**
   * constructor initializing with all variables in a phone bill
   * @param customer
   * @param caller
   * @param callee
   * @param tstart
   * @param tend
   */
  //customer name, single call log (call list =1)
  public PhoneBill (String customer, String caller, String callee, String tstart, String tend){
    this.customer=customer;
    this.callNum = 1;
    this.call= new PhoneCall[15];
    this.call[callNum-1]=new PhoneCall(caller, callee, tstart, tend);
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
   * @param item
   * @return the caller string
   */

  public String getCaller(int item){
    if(this.call[item] == null){
      return null;
    }
    return this.call[item].getCaller();
  }

  /**
   * @param item
   * @return the callee string
   */
  public String getCallee(int item){
    if(this.call[item] == null){
      return null;
    }
    return this.call[item].getCallee();
  }

  /**
   * @param item
   * @return starttime string
   */
  public String getStartTime(int item){
    if(this.call[item] == null){
      return null;
    }
    return this.call[item].getBeginTimeString();
  }

  /**
   * @param item
   * @return endtime string
   */
  public String getEndTime(int item){
    if(this.call[item] == null){
      return null;
    }
    return this.call[item].getEndTimeString();
  }

  /**
   * @return customer name
   */

  @Override
  public String getCustomer() {
    return this.customer;

  }

  public int getCallNum(){
    return this.callNum;
  }

  public int display() {
    String tname = this.getCustomer();
    int index = 0;
    if (this.getCaller(index) != null) {
      System.out.println("Customer  :   " + tname);
      while(index <= this.callNum) {
        String tcaller = this.getCaller(index);
        String tcallee = this.getCallee(index);
        String tStart = this.getStartTime(index);
        String tend = this.getEndTime(index);
        ///
        System.out.println("Caller : " + tcaller + "    Callee :  " + tcallee);
        System.out.println(tStart + " - " + tend);
        return 1;
      }
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
    this.call[callNum]=new PhoneCall(temp_call);
      callNum=callNum+1;
    //throw new UnsupportedOperationException("This method is not implemented yet");
  }

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







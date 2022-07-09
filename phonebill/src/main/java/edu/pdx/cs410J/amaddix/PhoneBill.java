package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AbstractPhoneBill;

import java.util.ArrayList;
import java.util.Collection;

public class PhoneBill extends AbstractPhoneBill<PhoneCall> {

  //variables for a phone bill
  private final String customer;
  private PhoneCall[] call;
  private int callNum;

  //constructor with customer name, create empty array for call list

  public PhoneBill(){
    this.customer=null;
    this.call= new PhoneCall[10];
    this.callNum=0;

  }

  public PhoneBill(String customer) {
    this.customer = customer;
    //creating an array of phonecalls with 30 total slots for calls
    this.call= new PhoneCall[10];
    this.callNum=0;
  }

  //customer name, single call log (call list =1)
  public PhoneBill (String customer, String caller, String callee){
    this.customer=customer;
    this.callNum = 1;
    this.call= new PhoneCall[2];
    this.call[callNum-1]=new PhoneCall(caller, callee);
  }

  public String getCaller(int item){
    return this.call[item].getCaller();
  }

  public String getCallee(int item){
    return this.call[item].getCallee();
  }

  @Override
  public String getCustomer() {
    return this.customer;

  }

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







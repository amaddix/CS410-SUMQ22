package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AbstractPhoneCall;

public class PhoneCall extends AbstractPhoneCall {
  //variables of a phone call
  private String caller;
  private String callee;
  private String startTime;
  private String endTime;


  //base constructor
  public PhoneCall(){
    this.caller=null;
    this.callee=null;
    this.startTime=null;
    this.endTime=null;
  }

  /**
   * constructor initializing :
   * @param tcaller
   * @param tcallee
   * @param tstartTime
   * @param tendTime
   */

  //constructor with caller and callee
  public PhoneCall(String tcaller, String tcallee, String tstartTime, String tendTime){
    this.caller=tcaller;
    this.callee=tcallee;
    this.startTime=tstartTime;
    this.endTime=tendTime;
  }

  /**
   * constructor copying a phone call
   * @param tempcall
   */

  //constructor with 2 string num
  public PhoneCall(PhoneCall tempcall){
    this.caller= tempcall.caller;
    this.callee=tempcall.callee;
    this.startTime=tempcall.startTime;
    this.endTime= tempcall.endTime;
  }

  //constructor with a string name and a call
  /*
  public PhoneCall(String tname, String tcaller,String tcallee,String tstartTime, String tendTime){
    this.name=tname;
    this.caller=tcaller;
    this.callee=tcallee;
    this.startTime=tstartTime;
    this.endTime=tendTime;
  }

   */


  /**
   * @return caller
   */
  @Override
  public String getCaller() {
    return this.caller;
  //  throw new UnsupportedOperationException("This method is not implemented yet");
  }

  /**
   * @return callee
   */
  @Override
  public String getCallee() {
    return this.callee;
    //return "This method is not implemented yet";
  }

  /**
   * @return start time
   */
  @Override
  public String getBeginTimeString() {
    return this.startTime;
  }

  /**
   * @return endTime
   */

  @Override
  public String getEndTimeString() {
    return this.endTime;
   // throw new UnsupportedOperationException("This method is not implemented yet");
  }
}







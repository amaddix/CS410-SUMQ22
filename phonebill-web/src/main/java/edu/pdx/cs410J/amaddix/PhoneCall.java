package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AbstractPhoneCall;

import java.awt.desktop.ScreenSleepEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.util.Locale.*;
import static java.util.Locale.ENGLISH;

public class PhoneCall extends AbstractPhoneCall {
    //variables of a phone call

    private String customer;
    private String caller;
    private String callee;
    private String startTime;
    private String endTime;


    //base constructor
    public PhoneCall(){
        this.customer = null;
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
    public PhoneCall(String tcustomer, String tcaller, String tcallee, String tstartTime, String tendTime){
        this.customer = tcustomer;
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
        this.customer=tempcall.customer;
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


    public String getCustomer(){
        return this.customer;
    }

    /**
     *  * GET CALLER
     * @return caller- String with phonecall end time
     */
    @Override
    public String getCaller() {
        return this.caller;
        //  throw new UnsupportedOperationException("This method is not implemented yet");
    }

    /**
     * GET CALLEE -
     *
     * @return callee - String with phonecall end time
     */
    @Override
    public String getCallee() {
        return this.callee;
        //return "This method is not implemented yet";
    }

    /**
     * GET start TIME STRING -
     * @return time - string with phonecall end time
     */
    @Override
    public String getBeginTimeString() {
        try {
            if(this.startTime != null) {
                if(this.startTime.matches("^([1-9]|1[0-2])/([1-9]|[12][0-9]|3[01])/([0-9][0-9]), ([1-9]|[12][0-9]):([1-9]|[1-6][0-9]) ([A][M]|[P][M])$")){
                    return this.startTime;
                }
                String tname= this.startTime;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
                Date ttime = sdf.parse(tname);
                //Date ttime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ENGLISH).parse(this.startTime);
                //System.out.println(ttime);

                String time = sdf.getDateTimeInstance(sdf.SHORT, sdf.SHORT).format(ttime);
                //System.out.println(time);
                return time;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "no time";
    }

    /**
     * GET END TIME STRING -
     * @return endTime - string with phonecall end time
     */

    @Override
    public String getEndTimeString() {
        try {
            if(this.endTime != null) {
                if(this.endTime.matches("^([1-9]|1[0-2])/([1-9]|[12][0-9]|3[01])/22, ([1-9]|[12][0-9]):([1-9]|[1-6][0-9]) ([A][M]|[P][M])$")){
                    return this.endTime;
                }


                String tname= this.endTime;
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm a");

                //if(this.endTime.matches(^[0-9]))
                Date ttime = sdf.parse(tname);
                //Date ttime = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT, Locale.ENGLISH).parse(this.startTime);
                //System.out.println(ttime);

                String time = sdf.getDateTimeInstance(sdf.SHORT, sdf.SHORT).format(ttime);
                //System.out.println(time);
                return time;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return "no time";
        // return this.endTime;
        // throw new UnsupportedOperationException("This method is not implemented yet");
    }

}







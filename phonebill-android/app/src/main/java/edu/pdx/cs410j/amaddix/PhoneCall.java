package edu.pdx.cs410j.amaddix;
import edu.pdx.cs410J.AbstractPhoneCall;

//import java.awt.desktop.ScreenSleepEvent;
 //       import java.text.DateFormat;
        import java.text.ParseException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class PhoneCall extends AbstractPhoneCall {
    //variables of a phone call
    private String customer;
    private String caller;
    private String callee;
    private String startTime;
    private String endTime;

    /**
     * PHONECALL BASE CONSTRUCTOR - initalizing to null
     */

    //base constructor
    public PhoneCall(){
        this.customer = null;
        this.caller=null;
        this.callee=null;
        this.startTime=null;
        this.endTime=null;
    }

    /**
     * PHONECALL
     * constructor initializing :
     * @param tcustomer  -string customer name
     * @param tcaller - string caller number
     * @param tcallee - string callee number
     * @param tstartTime - string start time
     * @param tendTime - string end time
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
     * PHONE CALL
     * constructor copying a phone call
     * @param tempcall - phonecall type
     */

    //constructor with 2 string num
    public PhoneCall(PhoneCall tempcall){
        this.customer=tempcall.customer;
        this.caller= tempcall.caller;
        this.callee=tempcall.callee;
        this.startTime=tempcall.startTime;
        this.endTime= tempcall.endTime;
    }

    /**
     * GETCUSTOMER
     * @return - string customer name
     */
    public String getCustomer(){
        return this.customer;
    }

    /**
     * GET CALLER
     * @return caller- String with phonecall end time
     */
    @Override
    public String getCaller() {
        return this.caller;
    }

    /**
     * GET CALLEE -
     * @return callee - String with phonecall end time
     */
    @Override
    public String getCallee() {
        return this.callee;
    }

    public String getStartDate(){
        return this.startTime;
    }
    public String getEndDate(){
        return this.endTime;
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
                String time = sdf.getDateTimeInstance(sdf.SHORT, sdf.SHORT).format(ttime);
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
                Date ttime = sdf.parse(tname);
                String time = sdf.getDateTimeInstance(sdf.SHORT, sdf.SHORT).format(ttime);
                return time;

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "no time";
    }

}
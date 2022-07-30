package edu.pdx.cs410J.amaddix;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.checkerframework.checker.units.qual.A;

import java.io.IOException;
import java.io.PrintStream;
import java.io.StringWriter;
import java.util.Map;

/**
 * The main class that parses the command line and communicates with the
 * Phone Bill server using REST.
 */
public class Project4 {

    public static final String MISSING_ARGS = "Missing command line arguments";

    public static void main(String... args) {

        Boolean print = false;
        Boolean search = false;
        Boolean readme = false;
        Boolean host = false;
        Boolean port = false;
        String hostName = null;
        String portString = null;
        String customer = null;
        String caller = null;
        String callee = null;
        String startdate = null;
        String starttime = null;
        String startampm = null;
        String enddate = null;
        String endtime = null;
        String endampm = null;
        String start = null;
        String end = null;
        String namePattern = "^[a-zA-Z0-9 ]+$";
        String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9]) ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";


        for (String arg : args) {
            if (arg.startsWith("-README") && !readme) {
                readme = true;

                System.out.println("Ashley Maddix\n" +
                        "CS410-Summer 2022 - JavaP\n" +
                        "\n" +
                        "This Project focuses on using the REST api server and client to save and retrieve a list of phonebills,\n" +
                        "all containing a list of phone calls. A -host and -port argument are both required by a host name and port number,\n" +
                        "exept in the case that a -README arg is passed. In order to apply any functionality, (-print optional) a customer name is\n" +
                        "required, which would search the list of bills for one matching that customer name. (-search optional) If a name with two dates\n" +
                        "then if a bill matching that name is found, it would search for all calls from that bill between the passed times." +
                        "If all args expected are read, then we add that call either to a preexisting bill or create a new one.");



                //readmeMessage(readme);


            } else if (arg.startsWith("-search") && !search) {
                search = true;

            } else if (arg.startsWith("-print") && !print) {
                print = true;

            } else if (arg.startsWith("-host") && !host) {
                host = true;

            } else if (host && hostName == null) {
                hostName = arg;

            } else if (arg.startsWith("-port") && !port) {
                port = true;

            } else if (port && portString == null) {
                portString = arg;

            }

            else if (arg.matches(namePattern) && customer ==null) {
                customer = arg;
            }
            else if (isValidPhoneNumber(arg) && caller ==null) {
                caller = arg;
            }
            else if (isValidPhoneNumber(arg) && callee==null) {
                callee = arg;
            }
            else if(arg.matches(datePattern) && start==null){
                start = arg;
            }
            else if(arg.matches(datePattern) && end==null){
                end = arg;
            }
            else if (arg.matches("^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9])$") && startdate == null) {
                startdate = arg;
            } else if (arg.matches("^([0-9]|[012][0-9]):[0-6][0-9]$") && starttime == null) {
                starttime = arg;
            } else if (arg.matches("^([a][m]|[p][m])$") && startampm == null) {
                startampm = arg;
            }else if (arg.matches("^(0[0-9]|1[0-2])/([012][0-9]|3[01])/(20[0-9][0-9])$") && enddate == null) {
                enddate = arg;
            } else if (arg.matches("^([0-9]|[012][0-9]):[0-6][0-9]$") && endtime == null) {
                endtime = arg;
            }  else if (arg.matches("^([a][m]|[p][m])$") && endampm == null) {
                endampm = arg;
            }else {
                usage("Extraneous command line argument: " + arg);
            }
        }

        if(starttime !=null && startdate != null && startampm !=null){
            start = startdate + " " + starttime + " " + startampm;

            if(start.matches(datePattern) == false){
                start =null;
            }
        }
        if(endtime !=null && enddate != null && endampm !=null){
            end = enddate + " " + endtime + " " + endampm;
            if(end.matches(datePattern)){

            }else{
                end =null;
            }
        }

        if (hostName == null) {
            usage( MISSING_ARGS );

        } else if ( portString == null) {
            usage( "Missing port" );
        }

        int tport;
        try {
            tport = Integer.parseInt( portString );
            
        } catch (NumberFormatException ex) {
            usage("Port \"" + portString + "\" must be an integer");
            return;
        }

        if(search ==true){
            if(customer == null){
                System.err.println("error trying to search with no name");
                return;
            } else if (start == null || end == null) {
                System.err.println("error trying to search with no dates");
                return;
            }
        } else if (print == true && customer == null){
            System.err.println("error trying to print with no name");
            return;
            }

        PhoneBillRestClient client = new PhoneBillRestClient(hostName, tport);

        Boolean addCall = false;
        //if we have customer name
        if(customer!=null) {
            if (start != null && end != null) {
                if (caller != null && callee != null) {
                    addCall = true;
                } else {
                    search = true;
                }
            } else {
                print = true;
            }
        }


        try {
            //check if theres a bill w customer name/ return info if so
            PhoneBill temp = null;
            //if we got all args, we add call
            if(addCall){
                 client.addPhoneCall(customer, caller, callee, startdate, starttime, startampm, enddate, endtime, endampm);
                 System.out.println("\t -SUCCESSFULLY ADDED CALL- \t");
                 PhoneBill tnew = new PhoneBill(customer, caller, callee, start, end);
                 tnew.display();
            }
                //if we have just name and start/end time - search for calls between that time (w matching bill name)
            if(search) {
                client.getSearch(customer, start, end);
                }
                //if we got only name, we search for bill w matching name
                else if(print) {
                    int found = client.getBill(customer);
                }

        } catch (IOException | ParserException ex){
            PrintStream err = System.err;
            System.err.println("** While contacting server: " + ex);
            return;
        }
    }

    /**
     * Takes a string that should be a customer phone number, and verifies that it matches that format.
     * @param phoneNumber
     * @return true if string matches phone number, false otherwise
     */
    @VisibleForTesting
    static boolean isValidPhoneNumber(String phoneNumber) {
        String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";
        if (phoneNumber.matches(phonePattern)) {
            return true;
        } else{
            return false;
        }
    }

    static public int readmeMessage(Boolean readme){

      if(readme){
                System.out.println("Ashley Maddix\n" +
                        "CS410-Summer 2022 - JavaP\n" +
                        "\n" +
                        "This Project focuses on using the REST api server and client to save and retrieve a list of phonebills,\n" +
                        "all containing a list of phone calls. A -host and -port argument are both required by a host name and port number,\n" +
                        "exept in the case that a -README arg is passed. In order to apply any functionality, (-print optional) a customer name is\n" +
                        "required, which would search the list of bills for one matching that customer name. (-search optional) If a name with two dates\n" +
                        "then if a bill matching that name is found, it would search for all calls from that bill between the passed times." +
                        "If all args expected are read, then we add that call either to a preexisting bill or create a new one.");


          return 1;
}
  return 0;

}
    ///////////////////////////////////

    /**
     * Makes sure that the give response has the expected HTTP status code
     * @param code The expected status code
     * @param response The response from the server
     */
    /*
    private static void checkResponseCode( int code, HttpRequestHelper.Response response )
    {
        if (response.getHttpStatusCode() != code) {
            error(String.format("Expected HTTP code %d, got code %d.\n\n%s", code,
                                response.getHttpStatusCode(), response.getContent()));
        }
    }
*/
    /**
     * ERROR -  ouputs error messages
     * @param message - string with error message
     */
    /*
    private static void error( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
    }
*/
    /**
     * Prints usage information for this program and exits
     * @param message An error message to print
     */
    private static void usage( String message )
    {
        PrintStream err = System.err;
        err.println("** " + message);
        err.println();
        err.println("usage: java Project4 host port [name] [caller] [callee] [starttime] [ endtime]");
        err.println("  host         Host of web server");
        err.println("  port         Port of web server");
        err.println("  name         customer name");
        err.println("  caller   caller phone number");
        err.println("  callee   callee phone number");
        err.println("  starttime  call start time");
        err.println("  endtime  call end time");
        err.println();
        err.println("This Program saves a list of phonebills with a list of phonecalls");
        err.println("to the server.");
        err.println("If only customer name provided - Bill matching that name");
        err.println("is printed.");
        err.println("If customer name, and two dates are provided - search for all calls");
        err.println("between those dates");
        err.println("if all args are read - phone call is saved into pre-excisting Bill or a new one");
        err.println();
    }
}

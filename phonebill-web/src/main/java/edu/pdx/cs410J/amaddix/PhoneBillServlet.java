package edu.pdx.cs410J.amaddix;

import com.google.common.annotations.VisibleForTesting;
import org.codehaus.groovy.runtime.IteratorClosureAdapter;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * This servlet ultimately provides a REST API for working with an
 * <code>PhoneBill</code>.  However, in its current state, it is an example
 * of how to use HTTP and Java servlets to store simple dictionary of words
 * and their definitions.
 */
public class PhoneBillServlet extends HttpServlet
{
    static final String CUSTOMER_PARAMETER = "customer";
    static final String CALLER_PARAMETER = "caller";
    static final String CALLEE_PARAMETER = "callee";
    static final String START_PARAMETER = "start";
    static final String END_PARAMETER = "end";



    private final Map<String, PhoneBill> phoneBillMap = new HashMap<String, PhoneBill>();
    private PhoneBill tbill = null;
    private PhoneBill tcall =null;

    /**
     * Handles an HTTP GET request from a client by writing the definition of the
     * word specified in the "CUSTOMER" HTTP parameter to the HTTP response.  If the
     * "CUSTOMER" parameter is not specified, all of the entries in the dictionary
     * are written to the HTTP response.
     */
    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws IOException {

        response.setContentType("text/plain");

        String customer = getParameter(CUSTOMER_PARAMETER, request);
        String start = getParameter(START_PARAMETER, request);
        String end = getParameter(END_PARAMETER, request);


        if (customer != null) {
            tbill = phoneBillMap.get(customer);
            // if(tbill != null) {
            if (start != null && end != null) {
                try {
                    getSearch(customer, start, end, response);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            } else {
                    getBill(customer, tbill, response);
            }
        }
    }

    public void getSearch(String customer, String start, String end,  HttpServletResponse response) throws IOException, ParseException {
        PrintWriter pw = response.getWriter();
        SimpleDateFormat format1 = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
        SimpleDateFormat format2 = new SimpleDateFormat("M/d/yy, h:mm a");
        Date tstarttime = format1.parse(start);
        Date tendtime = format1.parse(end);
        PhoneBill bill1 = phoneBillMap.get(customer);
        PhoneBill tempBill = null;

        if (bill1 != null) {
            for(int i=0; i<bill1.getCallNum(); i++){
                Date tempStart = format2.parse(bill1.getStartTime(i));
                Date tempEnd = format2.parse(bill1.getEndTime(i));

                System.out.println((tempStart.compareTo(tstarttime)));
                if((tempStart.compareTo(tstarttime) >= 0 )  && (tempEnd.compareTo(tendtime) <=0) ) {
                    PhoneCall tcall = new PhoneCall(customer, bill1.getCaller(i), bill1.getCallee(i), bill1.getStartTime(i), bill1.getEndTime(i));

                    if(tempBill == null){
                        tempBill = new PhoneBill(tcall);
                    }
                    else{
                        tempBill.addPhoneCall(tcall);
                    }
                }
                }

            PrettyPrinter dumper = new PrettyPrinter(pw);
            dumper.dump(tempBill);
            pw.println(tempBill.display());

            pw.flush();
            response.setStatus(HttpServletResponse.SC_OK);

            }
        }

    public void getBill(String name, PhoneBill bill1, HttpServletResponse response) throws IOException {

        if (bill1 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        } else {
            PrintWriter pw = response.getWriter();
            PrettyPrinter dumper = new PrettyPrinter(pw);
            dumper.dump(bill1);
            pw.println(bill1.display());
            pw.flush();
            response.setStatus(HttpServletResponse.SC_OK);
        }
    }


////////////////////////////////////////////////
    /**
     * Handles an HTTP POST request by storing the phonebill request parameters.  It writes the dictionary
     * entry to the HTTP response.
     */
    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws IOException {
        response.setContentType("text/plain");


        String customer = getParameter(CUSTOMER_PARAMETER, request);
        if (customer == null) {
            missingRequiredParameter(response, "customer");
            return;
        }
        String tcaller = getParameter(CALLER_PARAMETER, request);
        if (tcaller == null) {
            missingRequiredParameter(response, "tcaller");
            return;
        }
        String tcallee = getParameter(CALLEE_PARAMETER, request);
        if (tcallee == null) {
            missingRequiredParameter(response, "tcallee");
            return;
        }

        String start = getParameter(START_PARAMETER, request);

        if (start == null) {
              missingRequiredParameter(response, "startTime");
              return;
        }

        String end = getParameter(END_PARAMETER, request);
        if (end == null) {
            missingRequiredParameter(response, "startTime");
            return;
        }

        PhoneCall tcall = new PhoneCall(customer, tcaller, tcallee, start, end);

        PhoneBill bill1 = this.phoneBillMap.get(customer);

        if (bill1 == null) {
            bill1 = new PhoneBill(tcall);
            this.phoneBillMap.put(customer, bill1);
        } else {
            bill1.addPhoneCall(tcall);
            this.phoneBillMap.put(customer, bill1);
        }

        PrintWriter pw = response.getWriter();
        pw.println(Messages.definedCall(customer, tcaller, tcallee, start, end));
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);
    }

    /////////////////////////////////////////////////
    /**
     * Handles an HTTP DELETE request by removing all dictionary entries.  This
     * behavior is exposed for testing purposes only.  It's probably not
     * something that you'd want a real application to expose.
     */
    /*
    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        this.dictionary.clear();

        PrintWriter pw = response.getWriter();
        pw.println(Messages.allDictionaryEntriesDeleted());
        pw.flush();

        response.setStatus(HttpServletResponse.SC_OK);

    } */

    /**
     * Writes an error message about a missing parameter to the HTTP response.
     *
     * The text of the error message is created by {@link Messages#missingRequiredParameter(String)}
     */
    private void missingRequiredParameter( HttpServletResponse response, String parameterName )
        throws IOException
    {
        String message = Messages.missingRequiredParameter(parameterName);
        response.sendError(HttpServletResponse.SC_PRECONDITION_FAILED, message);
    }

    /**
     * Writes the definition of the given word to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */

    /*
    private void writeDefinition(String customer, HttpServletResponse response) throws IOException {
        PhoneBill tempBill = this.phoneBillMap.get(customer);

        if (tempBill == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);

        } else {
            PrintWriter pw = response.getWriter();

           // PhoneBill newBill= new PhoneBill(tempBill);
           // Map<String, PhoneBill> newBill = Map.of(customer, tempBill);
            PrettyPrinter dumper = new PrettyPrinter(pw);
            dumper.dump(tempBill);

            response.setStatus(HttpServletResponse.SC_OK);
        }
    }

*/

    /**
     * Writes all of the dictionary entries to the HTTP response.
     *
     * The text of the message is formatted with {@link TextDumper}
     */
/*
    private void writeAllDictionaryEntries(HttpServletResponse response ) throws IOException
    {
        for(PhoneBill billinfo : phoneBillMap.values()) {
            PrintWriter pw = response.getWriter();
            PrettyPrinter dumper = new PrettyPrinter(pw);
            //TextDumper dumper = new TextDumper(pw);
            dumper.dump(billinfo);
        }

        response.setStatus( HttpServletResponse.SC_OK );
    }

 */
////////

    /**
     * Returns the value of the HTTP request parameter with the given name.
     *
     * @return <code>null</code> if the value of the parameter is
     *         <code>null</code> or is the empty string
     */
    private String getParameter(String name, HttpServletRequest request) {
      String value = request.getParameter(name);
      if (value == null || "".equals(value)) {
        return null;

      } else {
        return value;
      }
    }
/*
    @VisibleForTesting
    String getDefinition(String word) {
        return this.dictionary.get(word);
    }
*/
}

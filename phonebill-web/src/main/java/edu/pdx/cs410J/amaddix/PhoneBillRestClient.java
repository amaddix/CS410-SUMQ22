package edu.pdx.cs410J.amaddix;

import com.google.common.annotations.VisibleForTesting;
import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;

import java.io.IOException;
import java.io.StringReader;
import java.util.Map;
import java.io.*;
import java.util.Scanner;

import static edu.pdx.cs410J.web.HttpRequestHelper.Response;
import static edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import static java.net.HttpURLConnection.HTTP_OK;

/**
 * A helper class for accessing the rest client.  Note that this class provides
 * an example of how to make gets and posts to a URL.  You'll need to change it
 * to do something other than just send dictionary entries.
 */
public class PhoneBillRestClient {

  private static final String WEB_APP = "phonebill";
  private static final String SERVLET = "calls";
  //private final String url;

  private final HttpRequestHelper http;


  /**
   * PHONEBILLRESTCLIENT CONSTRUCTOR
   * Creates a client to the Phone Bil REST service running on the given host and port
   * @param hostName The name of the host
   * @param port     The port
   */
  public PhoneBillRestClient(String hostName, int port) {
    this(new HttpRequestHelper(String.format("http://%s:%d/%s/%s", hostName, port, WEB_APP, SERVLET)));
  }

  /**
   * PHONEBILLRESTCLIENT CONSTRUCTOR
   * Creates a client to the Phone Bil REST service running on the given host and port
   * @param http -HttpRequestHelper
   */
  @VisibleForTesting
  PhoneBillRestClient(HttpRequestHelper http) {
    this.http = http;
  }

  /**
   * GETBILL - if client request phone bill displayed based on the customer name given
   * @param customer - string customer name
   * @return int to show result
   * @throws IOException if io exception is found
   * @throws ParserException if parser exception is found
   */
  public int getBill(String customer) throws IOException, ParserException {

    Response response = http.get(Map.of("customer", customer));
    //throwExceptionIfNotOkayHttpStatus(response);
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      System.err.println("No Bill found with matching customer name");
      return 0;
    }
    String content = response.getContent();
    System.out.println("\t -FOUND BILL MATCHING CUSTOMER : " + customer + "\n" + content);
    return 1;

  }

  /**
   * GETSTRING - if client requests phone call information between two times given,
   * @param customer - string customer name
   * @param start - string start time
   * @param end - string end time
   * @return - int to show result
   * @throws IOException if ioexception found
   * @throws ParserException if parser exception found
   */
  public int getSearch(String customer, String start, String end) throws IOException, ParserException {

    Response response = http.get(Map.of("customer", customer, "start", start, "end", end));
    //throwExceptionIfNotOkayHttpStatus(response);
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      System.err.println("Issue finding phonecalls between" + start + " - " + end + " for customer :" + customer);
      return 0;
    }
    String content = response.getContent();
    if (content != null) {
      System.out.println("\t -FOUND BILL MATCHING CUSTOMER : " + customer + "\n"
              + "\t -PHONE CALLS BETWEEN " + start + " - " + end + "\n" + content);
      return 1;
    }
    return 0;
  }

  /**
   * ADDPHONECALL - if client would like to add phone bill, if given all  required info save phonecall to phone bill
   * @param customer - string customer name
   * @param caller - string phone number
   * @param callee - string phone  number
   * @param startdate - string start date of call
   * @param starttime -string start time of call
   * @param startampm -string start am or pm
   * @param enddate -string start date of call
   * @param endtime -string start time of call
   * @param endampm -string start am or pm
   * @return - int to show result
   * @throws IOException - if ioexception is found
   */
  public int addPhoneCall(String customer, String caller, String callee, String startdate, String starttime, String startampm, String enddate, String endtime, String endampm) throws IOException {
    String start = startdate + " " + starttime + " " + startampm;
    String end = enddate + " " + endtime + " " + endampm;
    Response response = http.post(Map.of("customer", customer, "caller", caller, "callee", callee, "start", start, "end", end));
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      System.err.println("Issue adding new phone call");
      return 0;
    }
    //throwExceptionIfNotOkayHttpStatus(response);
    return 1;
  }
}
/*
  private void throwExceptionIfNotOkayHttpStatus(Response response) {
    int code = response.getHttpStatusCode();
    if (code != HTTP_OK) {
      String message = response.getContent();
      throw new RestException(code, message);
    }

}
/*
  ////////////////////////////////////////////////
  public Map<String, String> getAllDictionaryEntries() throws IOException, ParserException {
    Response response = http.get(Map.of());
    //for(PhoneBill billinfo : responce.getContent()) {
    TextParser parser = new TextParser(new StringReader(response.getContent()));
   // return parser.parse();
    return null;
  }

  public String getDefinition(String word) throws IOException, ParserException {
    Response response = http.get(Map.of("customer", word));
    throwExceptionIfNotOkayHttpStatus(response);
    String content = response.getContent();

    TextParser parser = new TextParser(new StringReader(content));
   // return parser.parse().get(word);
    return null;
  }

    public void addDictionaryEntry(String word, String definition) throws IOException {
      Response response = http.post(Map.of("word", word, "definition", definition));
      throwExceptionIfNotOkayHttpStatus(response);
    }

  public void removeAllDictionaryEntries() throws IOException {
      Response response = http.delete(Map.of());
      throwExceptionIfNotOkayHttpStatus(response);
    }
*/

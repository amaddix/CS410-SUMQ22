package edu.pdx.cs410J.amaddix;

import java.io.*;
import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the {@link PhoneCall} class.
 *
 * You'll need to update these unit tests as you build out your program.
 */
public class PhoneCallTest {

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */

  @Test
  void testPhoneBillCorrect() {
    String tname = "Ashley";
    String tcaller = "9584758564";
    String tcallee = "8577689786";
    String tstart = "04/28/2022 01:28 am";
    String tend = "04/28/2022 01:45 am";
    PhoneBill tbill = new PhoneBill(tname, tcaller, tcallee, tstart, tend);
    assertThat(tname, equalTo(tbill.getCustomer()));

  }

  @Test
  void testingCorrectCall() {
    //con w string string
    //con w phone call
    String tcaller = "3748574747";
    String tcallee = "9483737475";
    String tstart = "04/28/2022 01:28 am";
    String tend = "04/28/2022 01:45 am";
    PhoneCall tcall = new PhoneCall("Ashley", tcaller, tcallee, tstart, tend);
    PhoneCall tcall2 = new PhoneCall(tcall);

    assertThat(tcaller, equalTo(tcall.getCaller()));
    assertThat(tcallee, equalTo(tcall.getCallee()));
    assertThat(tcaller, equalTo(tcall2.getCaller()));
    assertThat(tcallee, equalTo(tcall2.getCallee()));


    //get caller
  }


  @Test
  void getBeginTime() {
    //getBeginTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall("ashley", "2344323423", "2342342343", "7/10/2022 01:22 pm", "7/10/2022 01:48 pm");
    assertThat("7/10/22, 1:22 PM", equalTo(call.getBeginTimeString()));
    // assertThrows(UnsupportedOperationException.class, call::getBeginTimeString);
  }

  @Test
  void getEndTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall("ashley", "2344323423", "2342342343", "7/10/2022 01:22 am", "7/10/2022 01:48 am");
    assertThat("7/10/22, 1:48 AM", equalTo(call.getEndTimeString()));

    // assertThrows(UnsupportedOperationException.class, call::getEndTimeString);
  }

  /**
   * This unit test will need to be modified (likely deleted) as you implement
   * your project.
   */
  /*
  @Test
  void initiallyAllPhoneCallsHaveTheSameCallee() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getCallee(), containsString("not implemented"));
  }
  */
  @Test
  void addingCall() {
    String tname = "Ashley";
    String tcaller = "5867375767";
    String tcallee = "7477579494";
    String tstart = "04/28/2022 01:28 am";
    String tend = "04/28/2022 01:45 am";
    PhoneCall tcall = new PhoneCall(tname, tcaller, tcallee, tstart, tend);
    PhoneBill tbill = new PhoneBill(tname);

    //tbill.addPhoneCall(tcall);
    //assertThat(tbill.getCaller(0),equalTo(tcaller));
    //assertThat(tbill.getCallee(0),equalTo(tcallee));

  }

  @Test
  void prettyprinter() {
    String tname = "Ashley";
    String tcaller = "5867375767";
    String tcallee = "7477579494";
    String tstart = "04/28/2022 01:28 am";
    String tend = "04/28/2022 01:45 am";
    PhoneCall tcall = new PhoneCall(tname, tcaller, tcallee, tstart, tend);
    PhoneBill tbill = new PhoneBill(tcall);

try {
  File tfile = new File("temptextfile.txt");
  if (tfile.isFile() == false || tfile.length() == 0) {
    PrettyPrinter pp = new PrettyPrinter((new FileWriter("temptextfile.txt")));
    pp.dump(tbill);

    Reader readerType = new FileReader("temptextfile.txt");
    TextParser parser = new TextParser(readerType);
    PhoneBill filebill = parser.parse();
    assertThat(tbill.getCustomer(), equalTo(filebill.getCustomer()));
    assertThat(tbill.getCaller(0), equalTo(filebill.getCaller(0)));
    assertThat(tbill.getCallee(0), equalTo(filebill.getCallee(0)));
    assertThat(tbill.getStartTime(0), equalTo(filebill.getStartTime(0)));
    assertThat(tbill.getEndTime(0), equalTo(filebill.getEndTime(0)));


  }
}catch (ParserException ex) {
      // insert code to run when exception occurs
      System.out.println("error w parser :" + ex);
    } catch (java.io.FileNotFoundException ex) {
      //} catch (java.text.FileNotFoundException ex) {
      // insert code to run when exception occurs
      System.out.println(ex);
      //PrintWriter writer = new PrintWriter(filename, "UTF-8");
      //Reader readerType = new FileReader(filename);
      //TextParser parser = new TextParser(readerType);

    } catch (IOException e) {
      e.printStackTrace();
    }
    }


  @Test
  public void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTime(), is(nullValue()));
  }
}
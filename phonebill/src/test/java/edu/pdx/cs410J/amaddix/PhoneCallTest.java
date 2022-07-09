package edu.pdx.cs410J.amaddix;

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
  void testPhoneBillCorrect(){
    String tname="Ashley";
    String tcaller= "9584758564";
    String tcallee ="8577689786";
    PhoneBill tbill= new PhoneBill(tname, tcaller, tcallee);

    assertThat(tname, equalTo(tbill.getCustomer()));

  }

  @Test
  void testingCorrectCall(){
    //con w string string
    //con w phone call
    String tcaller= "3748574747";
    String tcallee="9483737475";
    PhoneCall tcall= new PhoneCall(tcaller,tcallee);
    PhoneCall tcall2 = new PhoneCall(tcall);

    assertThat(tcaller, equalTo(tcall.getCaller()));
    assertThat(tcallee, equalTo(tcall.getCallee()));
    assertThat(tcaller, equalTo(tcall2.getCaller()));
    assertThat(tcallee, equalTo(tcall2.getCallee()));


    //get caller
  }


  @Test
  void getBeginTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall();
    assertThrows(UnsupportedOperationException.class, call::getBeginTimeString);
  }

  @Test
  void getEndTimeStringNeedsToBeImplemented() {
    PhoneCall call = new PhoneCall();
    assertThrows(UnsupportedOperationException.class, call::getEndTimeString);
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
    String tcaller="5867375767";
    String tcallee="7477579494";
    PhoneCall tcall = new PhoneCall(tcaller, tcallee);
    PhoneBill tbill = new PhoneBill(tname);

    tbill.addPhoneCall(tcall);
    assertThat(tbill.getCaller(0),equalTo(tcaller));
    assertThat(tbill.getCallee(0),equalTo(tcallee));

  }


  @Test
  void forProject1ItIsOkayIfGetBeginTimeReturnsNull() {
    PhoneCall call = new PhoneCall();
    assertThat(call.getBeginTime(), is(nullValue()));
  }
  
}

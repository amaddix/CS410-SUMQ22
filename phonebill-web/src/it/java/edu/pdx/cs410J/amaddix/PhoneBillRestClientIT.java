package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper.RestException;
import org.junit.jupiter.api.MethodOrderer.MethodName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Integration test that tests the REST calls made by {@link PhoneBillRestClient}
 */
@TestMethodOrder(MethodName.class)
class PhoneBillRestClientIT {
  private static final String HOSTNAME = "localhost";
  private static final String PORT = System.getProperty("http.port", "8080");

  private PhoneBillRestClient newPhoneBillRestClient() {
    int port = Integer.parseInt(PORT);
    return new PhoneBillRestClient(HOSTNAME, port);
  }

@Test
  void testcorrect() throws IOException, ParserException {

      PhoneBillRestClient client = newPhoneBillRestClient();
        String customer = "ash";
        String caller = "3748574747";
        String callee = "9483737475";
        String startdate = "04/28/2022";
        String starttime = "01:28";
        String startampm = "am";
        String enddate = "04/28/2022";
        String endtime = "01:45";
        String endampm = "am";
        client.addPhoneCall(customer, caller, callee, startdate, starttime, startampm, enddate, endtime, endtime);

        assertThat(client.getBill(customer), equalTo(1));
        assertThat(client.getSearch(customer, "04/25/2022 10:10 am", "04/30/2022 10:10 am"), equalTo(1));

}


  }


  /*
    @Test
  void test0RemoveAllDictionaryEntries() throws IOException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    client.removeAllDictionaryEntries();
  }

  @Test
  void test1EmptyServerContainsNoDictionaryEntries() throws IOException, ParserException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    Map<String, PhoneBill> billList = client.getAllDictionaryEntries();
    assertThat(dictionary.size(), equalTo(0));
  }

  @Test
  void test2DefineOneWord() throws IOException, ParserException {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String testWord = "TEST WORD";
    String testDefinition = "TEST DEFINITION";
    client.addDictionaryEntry(testWord, testDefinition);

    String definition = client.getDefinition(testWord);
    assertThat(definition, equalTo(testDefinition));
  }

  @Test
  void test4EmptyWordThrowsException() {
    PhoneBillRestClient client = newPhoneBillRestClient();
    String emptyString = "";

    RestException ex =
      assertThrows(RestException.class, () -> client.addDictionaryEntry(emptyString, emptyString));
    assertThat(ex.getHttpStatusCode(), equalTo(HttpURLConnection.HTTP_PRECON_FAILED));
    assertThat(ex.getMessage(), equalTo(Messages.missingRequiredParameter("word")));
  }
*/


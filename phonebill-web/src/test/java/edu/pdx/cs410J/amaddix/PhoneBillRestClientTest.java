package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.web.HttpRequestHelper;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;
import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PhoneBillRestClientTest {
/*
  @Test
  void getAllDictionaryEntriesPerformsHttpGetWithNoParameters() throws ParserException, IOException {
    Map<String, String> dictionary = Map.of("One", "1", "Two", "2");

    HttpRequestHelper http = mock(HttpRequestHelper.class);
    when(http.get(eq(Map.of()))).thenReturn(dictionaryAsText(dictionary));

    PhoneBillRestClient client = new PhoneBillRestClient(http);

   // assertThat(client.getAllDictionaryEntries(), equalTo(dictionary));
  }
*/
    /*
    @Test
    void Test1() {
        PhoneBill tbill = new PhoneBill("name", "2222222222", "3333333333", "10/10/2022 10:10 am", "10/10/2022 10:10 am");
        Map<String, PhoneBill> map = Map.of("name",tbill);

        HttpRequestHelper http = mock(HttpRequestHelper.class);
        http.post("name", "2222222222", "3333333333", "10/10/2022 10:10 am", "10/10/2022 10:10 am");
        when(http.get(eq(Map.of("name")))).thenReturn(dictionaryAsText(dictionary));

        PhoneBillRestClient client = new PhoneBillRestClient(http);

        // assertThat(client.getAllDictionaryEntries(), equalTo(dictionary));
    }
*/
  @Test
  void TestCorrectargs() throws IOException, ParserException{
      PhoneBill tbill = new PhoneBill("ashley", "7676767654", "10/10/2022", "10/08/2022 10:10 am", "10/12/2022 10:10 am");
      //Map<String, PhoneBill> tempmap = Map.of("ashey", tbill);
     // HttpRequestHelper http = mock(HttpRequestHelper.class);
      //when(http.get(eq(Map.of()))).thenReturn()
    try {
        PhoneBillRestClient client = new PhoneBillRestClient("localhost", 8080);
        //http.addPhoneCall("ashley", "5676787897", "7676767654", "10/10/2022", "10:10", "am", "10/10/2022", "11:11", "am");
        client.addPhoneCall("ashley", "5676787897", "7676767654", "10/10/2022", "10:10", "am", "10/10/2022", "11:11", "am");
        //client.getBill("ashley");
        //client.getSearch("ashley", "10/08/2022 10:10 am", "10/12/2022 10:10 am");
        assertThat(client.getBill("ashley"), equalTo(0));
        assertThat(client.getSearch("ashley", "10/08/2022 10:10 am", "10/12/2022 10:10 am"), equalTo(1));



        //client.getSearch("ashley", "10/08/2022 10:10 am", "10/12/2022 10:10 am");
        //assertThat((http.getBill("ashley")), equalTo(1));
        //assertThat((http.getSearch("ashley", "10/08/2022 10:10 am", "10/12/2022 10:10 am")), equalTo(1));
    }catch(IOException | ParserException e){
      System.err.println(e);
    }
  }

  private HttpRequestHelper.Response dictionaryAsText(Map<String, String> dictionary) {
    StringWriter writer = new StringWriter();
    //new TextDumper(writer).dump(dictionary);
     // for(int i=0; i<)

    return new HttpRequestHelper.Response(writer.toString());
  }
}

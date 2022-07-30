package edu.pdx.cs410J.amaddix;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.ParseException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.*;

/**
 * A unit test for the {@link PhoneBillServlet}.  It uses mockito to
 * provide mock http requests and responses.
 */
class PhoneBillServletTest {

    @Test
    void testingserver()throws ServletException, IOException{

        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);
        PrintWriter pw = mock(PrintWriter.class);

        when(response.getWriter()).thenReturn(pw);

        servlet.doGet(request, response);

        // Nothing is written to the response's PrintWriter
        verify(pw, never()).println(anyString());
        //verify(response).setStatus(HttpServletResponse.SC_OK);
    }

    @Test
    void incorrect1() throws ServletException, IOException {
        PhoneBill tbill = new PhoneBill("name", "5555555555", "3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("customer")).thenReturn("name");
        when(request.getParameter("caller")).thenReturn(null);
        when(request.getParameter("callee")).thenReturn("3333333333");
        when(request.getParameter("start")).thenReturn("10/10/2022 10:10 am");
        when(request.getParameter("end")).thenReturn("10/10/2022 11:11 am");
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
        // HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request.getParameter("caller")).thenReturn("name");
    }

    @Test
    void incorrect2() throws ServletException, IOException {
        PhoneBill tbill = new PhoneBill("name", "5555555555", "3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("customer")).thenReturn("name");
        when(request.getParameter("caller")).thenReturn("3333333333");
        when(request.getParameter("callee")).thenReturn(null);
        when(request.getParameter("start")).thenReturn("10/10/2022 10:10 am");
        when(request.getParameter("end")).thenReturn("10/10/2022 11:11 am");
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
        // HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request.getParameter("caller")).thenReturn("name");
    }

    @Test
    void incorrect3() throws ServletException, IOException {
        PhoneBill tbill = new PhoneBill("name", "5555555555", "3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("customer")).thenReturn("name");
        when(request.getParameter("caller")).thenReturn("3333333333");
        when(request.getParameter("callee")).thenReturn("5555555555");
        when(request.getParameter("start")).thenReturn(null);
        when(request.getParameter("end")).thenReturn("10/10/2022 11:11 am");
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
        // HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request.getParameter("caller")).thenReturn("name");
    }


    @Test
    void incorrect4() throws ServletException, IOException {
        PhoneBill tbill = new PhoneBill("name", "5555555555", "3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("customer")).thenReturn("name");
        when(request.getParameter("caller")).thenReturn("3333333333");
        when(request.getParameter("callee")).thenReturn("5555555555");
        when(request.getParameter("start")).thenReturn("10/10/2022 11:11 am");
        when(request.getParameter("end")).thenReturn(null);
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
        // HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request.getParameter("caller")).thenReturn("name");
    }

    @Test
    void incorrect6() throws ServletException, IOException {
        PhoneBill tbill = new PhoneBill("name", "5555555555", "3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("customer")).thenReturn(null);
        when(request.getParameter("caller")).thenReturn("3333333333");
        when(request.getParameter("callee")).thenReturn("5555555555");
        when(request.getParameter("start")).thenReturn("10/10/2022 11:11 am");
        when(request.getParameter("end")).thenReturn(null);
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
        //verify(response).setStatus((HttpServletResponse.SC_PRECONDITION_FAILED));
        //verify(response).setStatus(HttpServletResponse.SC_OK);



        //String terror = stringWriter.parse(response);

        //byte[] responseArray = responseWrapper.getContentAsByteArray();
        //String responseStr = new String(responseArray, responseWrapper.getCharacterEncoding());
        //assertThat(responseStr, containsString("customer"));


       // ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
        //verify(response).setStatus(statusCode.capture());
        //assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));
        // HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request.getParameter("caller")).thenReturn("name");
    }


    @Test
    void correct() throws ServletException, IOException{
        PhoneBill tbill = new PhoneBill("name","5555555555","3333333333", "10/10/2022 10:10 am", "10/10/2022 11:11 am");
        PhoneBillServlet servlet = new PhoneBillServlet();

        HttpServletRequest request = mock(HttpServletRequest.class);
        HttpServletResponse response = mock(HttpServletResponse.class);

        //when(request.request.get(Map.of("customer", customer));
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("caller")).thenReturn("5555555555");
        when(request.getParameter("callee")).thenReturn("3333333333");
        when(request.getParameter("start")).thenReturn("10/10/2022 10:10 am");
        when(request.getParameter("end")).thenReturn("10/10/2022 11:11 am");
        // Use a StringWriter to gather the text from multiple calls to println()
        StringWriter stringWriter = new StringWriter();
        PrintWriter pw = new PrintWriter(stringWriter, true);
        when(response.getWriter()).thenReturn(pw);
        servlet.doPost(request, response);
       // HttpServletRequest request1 = mock(HttpServletRequest.class);
        when(request.getParameter("name")).thenReturn("name");


        HttpServletRequest request1 = mock(HttpServletRequest.class);
        //when(request1.getParameter("name")).thenReturn("name");
        servlet.getBill("name", tbill, response);

        HttpServletRequest request2 = mock(HttpServletRequest.class);
       // when(request2.getParameter("name")).thenReturn("name");
       // when(request2.getParameter("start")).thenReturn("10/09/2022 10:10 am");
       // when(request2.getParameter("end")).thenReturn("10/11/2022 11:11 am");

        try{
            servlet.getSearch("name", "10/09/2022 10:10 am", "10/11/2022 11:11 am", response);
        } catch (ParseException e) {
            e.printStackTrace();
        }


        verify(response).setStatus(HttpServletResponse.SC_OK);
       // HttpServletRequest request2 = mock(HttpServletRequest.class);
        /*
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("start")).thenReturn("10/09/2022 10:10 am");
        when(request.getParameter("end")).thenReturn("10/11/2022 11:11 am");
        servlet.doGet(request,response);

         */
        //content = response.getContent();
        //assertThat(content, containsString("name"));
        //assertThat(servlet.doGet(request,response), response.notEqual(null));


        //when(response.getBill("name"))


       // response.post(Map.of("customer", customer, "caller", caller, "callee", callee, "start", start, "end", end));




        //assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition)));

        // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
        //ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
        //verify(response).setStatus(statusCode.capture());

       // assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

        //assertThat(servlet.getDefinition(word), equalTo(definition));
    }
/*
  @Test
  void initiallyServletContainsNoDictionaryEntries() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);
    PrintWriter pw = mock(PrintWriter.class);

    when(response.getWriter()).thenReturn(pw);

    servlet.doGet(request, response);

    // Nothing is written to the response's PrintWriter
    verify(pw, never()).println(anyString());
    verify(response).setStatus(HttpServletResponse.SC_OK);
  }

  @Test
  void addOneWordToDictionary() throws ServletException, IOException {
    PhoneBillServlet servlet = new PhoneBillServlet();

    String word = "TEST WORD";
    String definition = "TEST DEFINITION";

    HttpServletRequest request = mock(HttpServletRequest.class);
    when(request.getParameter("word")).thenReturn(word);
    when(request.getParameter("definition")).thenReturn(definition);

    HttpServletResponse response = mock(HttpServletResponse.class);

    // Use a StringWriter to gather the text from multiple calls to println()
    StringWriter stringWriter = new StringWriter();
    PrintWriter pw = new PrintWriter(stringWriter, true);

    when(response.getWriter()).thenReturn(pw);

    servlet.doPost(request, response);

    //assertThat(stringWriter.toString(), containsString(Messages.definedWordAs(word, definition)));

    // Use an ArgumentCaptor when you want to make multiple assertions against the value passed to the mock
    ArgumentCaptor<Integer> statusCode = ArgumentCaptor.forClass(Integer.class);
    verify(response).setStatus(statusCode.capture());

    assertThat(statusCode.getValue(), equalTo(HttpServletResponse.SC_OK));

    //assertThat(servlet.getDefinition(word), equalTo(definition));
  }
*/
}

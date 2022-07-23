package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import edu.pdx.cs410J.PhoneBillParser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

public class TextParser implements PhoneBillParser<PhoneBill> {
  private final Reader reader;

  /**
   * constructor initializing:
   *
   * @param reader
   */
  public TextParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * reads contents of file and saves as a phone bill
   *
   * @return phone bill created from text file
   * @throws ParserException
   */
  @Override
  public PhoneBill parse() throws ParserException {
    try (
            BufferedReader br = new BufferedReader(this.reader)
    ) {

      String st = null;
      String callee = null;
      String caller = null;
      String startTime = null;
      String endTime = null;
      String customer = null;
      String text = br.readLine();
      //text = br.readLine();



      // System.out.println("what happening : " + text);

      if (text == null) {
        throw new ParserException("missing file start");
      }

      text = br.readLine();

      boolean flag = false;
      int callIndex = 0;
      int i = 1;
      PhoneCall callList[] = new PhoneCall[15];
      String namePattern = "^[a-zA-Z0-9]+$";
      String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";
      String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/2022 ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";
      // String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/2022 ([0-9]|[012][0-9]):[0-6][0-9] $";
      PhoneBill tbill = null;
      while ((st = br.readLine()) != null ) {
        //  System.out.println("what happening : " + st);

        if (i == 5) {
          String temp[] = st.split("\t \t|\t - \t");
          if (temp[1].matches("^([1-9]|1[0-2])/([012][0-9]|3[01])/22, ([1-9]|[012][0-9]):[1-6][0-9] ([A][M]|[P][M])$")) {
            startTime = temp[1];
            i = 2;
          }
          else{
            System.err.println("Invalid time - file cannot be parsed");
            return null;
          }
          if (temp[2].matches("^([1-9]|1[0-2])/([012][0-9]|3[01])/22, ([1-9]|[012][0-9]):[1-6][0-9] ([A][M]|[P][M])$")) {
            endTime = temp[2];
            i = 2;
          }
          else{
            System.err.println("Invalid time - file cannot be parsed");
            return null;
          }
          if (callIndex == 0){
            callIndex = callIndex+1;
            //System.out.println("what happening AM I 0:  " +callIndex);
            tbill= new PhoneBill(customer, caller, callee, startTime, endTime);
          }
          else{
            PhoneCall tcall = new PhoneCall(customer, caller, callee, startTime, endTime);
            tbill.addPhoneCall(tcall);
            //return tbill;
          }
        } else {
          //splitting strings / cutting off text before :\t - to get just the variable
          //all strings read (at customer - down) should
          String temp[] = st.split(":\t");
          if(temp.length <= 1){
            System.err.println("file isnt parsable");
            return null;
          }
          if (temp[1].matches(namePattern) && i == 1) {
            customer = temp[1];
            i = i + 1;
            flag = true;
          }
          else if (temp[1].matches(phonePattern) && i == 2) {
            caller = temp[1];
            i = i + 1;
          }
          //search for phone number pattern/ save as callee if i ==2
          else if (temp[1].matches(phonePattern) && i == 3) {
            callee = temp[1];
            i = i + 1;
          } else if (temp[1].matches("^[0-9]+ minutes") && i == 4) {
            i = i + 1;
          }
          else{
            System.err.println("file isnt parsable");
            return null;
          }
        }

      }
        return tbill;


    } catch (IOException e) {
      throw new ParserException("While parsing phone bill text", e);
    }
  }
}

//code here ...
//create function
//System.out.println("i should have a file!");













        /*

        st=br.readLine();
       // System.out.println("what is happening " + st);
       // String pattern= ;
        //if(st.matches("^(Call list of customer     :\t)[a-zA-Z0-9]+$")){
       // if(i==1){
          String temp[] = st.split("\t");
          System.out.println(temp[1]);

          //confirm w name pattern
          customer = temp[1];
          st=br.readLine();
        }
        //else{
         // i++;
         // System.err.println("no customer name");
        //}
        //loops through one phone call, continues for list of calls in phone bill
       //if(st.matches("^(\t \t Incoming call from        :\t)([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$")){
        if(st = br.readLine())!=null) {
        String temp[] = st.split("\t");
          caller = temp[1];
          System.out.println("caller " + caller);

          i++;
        //  if(st.matches("^(Incoming call from        :\t)^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$") && i ==1){

            //if((st = br.readLine())!=null && st.matches("(\t \t Call received by        :\t)([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$")){
            //if(st.matches("^(Incoming call from        :\t)[a-zA-Z0-9]$") && i ==1){
          if(st = br.readLine())!=null)   {
          String temp1[] = st.split("\t");
              callee = temp1[1];
            System.out.println("callee number = " + callee);
            i++;
            //test to find instance of Total call time / dont really do anything
              //read following string/ split at " " then uncomment following
            if((st = br.readLine())!=null){
              i++;
              if((st = br.readLine())!=null){
                String temp2[] =st.split("\t - \t");
                if(temp2[0].matches("^([0-9]|1[0-2])/([12][0-9]|3[01])/22, ([0-9]|[12][0-9]):[1-6][0-9] ([A][M]|[P][M])$")){
                  startTime = temp2[0];
                  System.out.println("Start time = " + startTime);
                }

                if(temp2[1].matches("^([0-9]|1[0-2])/([12][0-9]|3[01])/22, ([0-9]|[12][0-9]):[1-6][0-9] ([A][M]|[P][M])$")){
                  endTime = temp2[1];
                  System.out.println("end time = " + endTime);

                }
              }
            }
          }
          i=1;
          PhoneCall tcall = new PhoneCall(caller, callee, startTime, endTime);

          if(callIndex == 0){
            tbill = new PhoneBill(customer, caller, callee, startTime, endTime);
          }
          else{
            tbill.addPhoneCall(tcall);
            System.out.println("printing added call");
            tbill.display();
          }
          callIndex++;*/




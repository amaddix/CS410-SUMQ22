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
   * @param reader
   */
  public TextParser(Reader reader) {
    this.reader = reader;
  }

  /**
   * reads contents of file and saves as a phone bill
   * @return phone bill created from text file
   * @throws ParserException
   */
  @Override
  public PhoneBill parse() throws ParserException {
    try (
      BufferedReader br = new BufferedReader(this.reader)
    ) {

      String st=null;
      String callee=null;
      String caller=null;
      String startTime=null;
      String endTime=null;
      String customer = br.readLine();

      if (customer == null) {
        throw new ParserException("Missing customer");
      }

      int callIndex=0;
      int i=1;
      PhoneCall callList[] = new PhoneCall[15];
      //String namePattern = "^[a-zA-Z0-9]+$";
      String phonePattern = "^[0-9]{10}$";
      String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/2022 [012][0-9]:[0-6][0-9]$";
      PhoneBill tbill = null;
      while((st=br.readLine())!=null){
        //loops through one phone call, continues for list of calls in phone bill
        if(st.matches(phonePattern) && i ==1){
          caller = st;
          i++;
          if((callee = br.readLine())!=null && callee.matches(phonePattern) && i==2){
            System.out.println("callee number = " + callee);
            i++;
            if((startTime = br.readLine())!=null && startTime.matches(datePattern) && i==3){
              i++;
              if((endTime = br.readLine())!=null &&endTime.matches(datePattern) && i==4){ }
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
          callIndex++;

        }

        /*
        if(i == 0){
          caller = st;
          argAmount++;
          i++;
        }
        else if(i == 1){
        callee = st;
          argAmount++;
          i++;
        }
        else if(i==2){
          startTime = st + br.readLine();
         // timeStart= st;

          // w i=2 + i=3
          i++;
          argAmount++;
          i++;

        }

        else if(i==4){
          endTime= st + br.readLine();
          // w i=4 + i=5
          i++;
          argAmount++;
          i++;

        } */

      }

      return new PhoneBill(customer, caller, callee, startTime, endTime);

    } catch (IOException e) {
      throw new ParserException("While parsing phone bill text", e);
    }
  }

  //code here ...
  //create function
  //System.out.println("i should have a file!");
}

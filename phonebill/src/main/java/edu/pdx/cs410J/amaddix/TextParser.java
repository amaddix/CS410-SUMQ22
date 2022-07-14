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

      int argAmount=0;
      int i=0;
      while((st=br.readLine())!=null){
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

        }

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

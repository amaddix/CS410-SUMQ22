package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperParserTest {
 /*   @Test
    void test1(){
        StringWriter sw = new StringWriter();

        PhoneBill tbill= new PhoneBill("ash", "5555555555", "4444444444", "04/28/2022 01:28 am","04/28/2022 01:45 am");
        TextDumper dumper = new TextDumper(sw);
        dumper.dump(tbill);
        //StringReader sr = new StringReader(tbill);

        TextParser parser = new TextParser(new StringReader(sw));
        PhoneBill read = parser.parse();
        assertThat(read, equalTo(tbill));
    }
*/
/*
  @Test
  void emptyMapCanBeDumpedAndParsed() throws ParserException {
    Map<String, String> map = Collections.emptyMap();
    Map<String, String> read = dumpAndParse(map);
   // assertThat(read, equalTo(map));
  }
*/
  /*
  @Test
  private PhoneBill dumpAndParse(PhoneBill map) throws ParserException {
    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(map);

    String text = sw.toString();

    TextParser parser = new TextParser(new StringReader(text));
   return parser.parse();
    //return null;
  }

  @Test
  void dumpedTextCanBeParsed() throws ParserException {
    PhoneBill tbill = new PhoneBill("name", "2222222222", "3333333333", "10/10/2022 10:10 am", "10/10/2022 10:10 am");
    PhoneBill read = dumpAndParse(tbill);
    assertThat(read, equalTo(tbill));
  } */
}

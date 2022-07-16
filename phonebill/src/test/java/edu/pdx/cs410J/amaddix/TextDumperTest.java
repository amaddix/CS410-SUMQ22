package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class TextDumperTest {

  @Test
  void appointmentBookOwnerIsDumpedInTextFormat() {
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(bill);

    String text = sw.toString();
    assertThat(text, containsString(customer));
  }

  @Test
  void testBillDump() {
    //String customer = "Test Phone Bill";
    String stArray[] = new String[7];
    stArray[0] = "Ashley";
    stArray[1] = "9584758564";
    stArray[2] = "8577689786";
    stArray[3] = "04/28/2022";
    stArray[4] = "01:28";
    stArray[5] = "04/28/2022";
    stArray[6] = " 01:45";

    String start = "04/28/2022 01:28";
    String end = "04/28/2022 01:45";
    PhoneBill bill = new PhoneBill(stArray[0], stArray[1], stArray[2], start, end);
    assertThat(bill.display(), CoreMatchers.equalTo(1));

    StringWriter sw = new StringWriter();
    TextDumper dumper = new TextDumper(sw);
    dumper.dump(bill);

    String text = sw.toString();
    assertThat(text, containsString(stArray[0]));
  }

  @Test
  void canParseTextWrittenByTextDumper(@TempDir File tempDir) throws IOException, ParserException {
    String customer = "Test Phone Bill";
    PhoneBill bill = new PhoneBill(customer);

    File textFile = new File(tempDir, "apptbook.txt");
    TextDumper dumper = new TextDumper(new FileWriter(textFile));
    dumper.dump(bill);

    TextParser parser = new TextParser(new FileReader(textFile));
    PhoneBill read = parser.parse();
    assertThat(read.getCustomer(), equalTo(customer));
  }
}

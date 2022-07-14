package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.ParserException;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * A unit test for code in the <code>Project1</code> class.  This is different
 * from <code>Project1IT</code> which is an integration test (and can capture data
 * written to {@link System#out} and the like.
 */
class Project1Test {

  /*
  @Test
  boolean testingMain(){
    String[] commandLineArgs = {"Ashley", "9584758564", "857asda"};
    Project1 tproj= new Project1();
    if(tproj.main(commandLineArgs), containsString("Second number missing")){
      return true;
    }

  }

   */
  @Test
  void testprojconstructor(){
    Project2 tproj = new Project2();
    PhoneBill tbill = new PhoneBill();
    assertThat(null, equalTo(tbill.getCustomer()));

  }
  /*
  @Test
  void testFiles(){
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
    Project1 tproj = new Project1(stArray[0], stArray[1], stArray[2], start, end);
    try {
      File tfile = new File(filename);
      if (tfile.exists()) {
        System.out.println("we have a file!");
      }
      else {
        tfile.createNewFile();
      }

      PhoneBill bill = tproj.fileManager(filename);


    }*/

  @Test
  void testfileManager() throws FileNotFoundException, UnsupportedEncodingException, ParserException {
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
    Project2 tproj = new Project2(stArray[0], stArray[1], stArray[2], start, end);
    PhoneBill tbill = tproj.fileManager(2, "newTextFile.txt");
    assertThat(tbill.getCustomer(), equalTo(stArray[0]));
  }

  @Test
  void testfileManager2() throws FileNotFoundException, UnsupportedEncodingException, ParserException {
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
    Project2 tproj = new Project2(stArray[0], stArray[1], stArray[2], start, end);
    PhoneBill tbill = tproj.fileManager(1, null);
   assertThat(tbill.getCustomer(), equalTo(null));
    //assertThat(result.getTextWrittenToStandardError(), containsString("Start time invalid"));
  }

  @Test
  void testDisplay() {
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
    Project2 tproj = new Project2(stArray[0], stArray[1], stArray[2], start, end);
    assertThat(tproj.display(), equalTo(1));

  }
  @Test
  void testingProject2Correct() {
    String stArray[] = new String[7];
    stArray[0]="Ashley";
    stArray[1]= "9584758564";
    stArray[2] ="8577689786";
    stArray[3] = "04/28/2022";
    stArray[4] = "01:28";
    stArray[5] = "04/28/2022" ;
    stArray[6]= " 01:45";

    String start = "04/28/2022 01:28";
    String end = "04/28/2022 01:45";
    Project2 tproj= new Project2(stArray[0], stArray[1], stArray[2], start, end);

    //assertThat(tproj.validName(tname) , equalsTo(true));
    assertThat(tproj.isValidPhoneNumber(stArray[1]) , equalTo(true));
    assertThat(tproj.isValidPhoneNumber(stArray[2]) , equalTo(true));
    assertThat(tproj.checkArgs(0, stArray) , equalTo(1));
    //assertThat(tproj.display(), containsString("Customer  :   " + tname + "Caller : " + tcaller + "    Callee :  " + tcallee));
  }

  @Test
  void testingProject2Incorrect(){
    String tname="Ash%^^&%&^%&^%123";
    String tcaller= "9sdfs324frd";
    String tcallee ="857768978645354353";
    String tstart = "04/28/2022 01:28";
    String tend = "04/28/2022 01:45";
    Project2 tproj= new Project2(tname, tcaller, tcallee, tstart, tend);

    //assertThat(tproj.validName(tname) , equalsTo(false));
    assertThat(tproj.isValidPhoneNumber(tcaller) , equalTo(false));
    assertThat(tproj.isValidPhoneNumber(tcallee) , equalTo(false));
  }


  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project2.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }
}

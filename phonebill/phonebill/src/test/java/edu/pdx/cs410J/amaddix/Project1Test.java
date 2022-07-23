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
    Project3 tproj = new Project3();
    PhoneBill tbill = new PhoneBill();
    assertThat(null, equalTo(tbill.getCustomer()));

  }

  @Test
  void testbillconstructor4(){
    PhoneBill tbill = new PhoneBill("name", "543-656-3234", "567-345-5676", "03/12/2022 10:55 pm", "03/12/2022 11:00 pm");
    PhoneBill temp = new PhoneBill(tbill);
    assertThat(tbill.getCustomer(), equalTo(temp.getCustomer()));
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
  void testfileManager() throws IOException, ParserException {
    String stArray[] = new String[7];
    stArray[0] = "Ashley";
    stArray[1] = "9584758564";
    stArray[2] = "8577689786";
    stArray[3] = "04/28/2022";
    stArray[4] = "01:28";
    stArray[5] = "04/28/2022";
    stArray[6] = "01:45";

    String start = "04/28/2022 01:28 am";
    String end = "04/28/2022 01:45 am";
    Project3 tproj = new Project3(stArray[0], stArray[1], stArray[2], start, end);
    File tfile = new File("newTextFile.txt");
    if (!tfile.exists()) {
      tfile.createNewFile();
    }
    String var= "newTextFile.txt";
    //PhoneBill tbill = tproj.fileManager(var);
    //assertThat(tbill.getCustomer(), equalTo(stArray[0]));
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
    stArray[6] = "01:45";

    String start = "04/28/2022 01:28 pm";
    String end = "04/28/2022 01:45 pm";
    PhoneCall tcall = new PhoneCall(stArray[0], stArray[1], stArray[2], start, end);
    Project3 tproj = new Project3(stArray[0], stArray[1], stArray[2], start, end);
    PhoneBill tbill= tproj.fileManager ("textfile.txt", tcall);
   assertThat(tcall.getCustomer(), equalTo("Ashley"));
    //assertThat(result.getTextWrittenToStandardError(), containsString("Start time invalid"));
  }



  @Test
  void testDisplay() {
    String stArray[] = new String[9];
    stArray[0] = "Ashley";
    stArray[1] = "9584758564";
    stArray[2] = "8577689786";
    stArray[3] = "01:28";
    stArray[4] = "pm";
    stArray[5] = "04/28/2022" ;
    stArray[6]= "01:45";
    stArray[7]= "pm";

    String start = "04/28/2022 01:28 pm";
    String end = "04/28/2022 01:45 pm";
    Project3 tproj = new Project3(stArray[0], stArray[1], stArray[2], start, end);
    assertThat(tproj.display(), equalTo(1));

  }
  @Test
  void testingProject3Correct() {
    String stArray[] = new String[9];

    stArray[0]= "9584758564";
    stArray[1] ="8577689786";
    stArray[2] = "04/28/2022";
    stArray[3] = "01:28";
    stArray[4] = "pm";
    stArray[5] = "04/28/2022" ;
    stArray[6]= "01:45";
    stArray[7]= "pm";

    String start = "04/28/2022 01:28 pm";
    String end = "04/28/2022 01:45 pm";
    Project3 tproj= new Project3("unknown", stArray[1], stArray[2], start, end);

    //assertThat(tproj.validName(tname) , equalsTo(true));
    assertThat(tproj.isValidPhoneNumber(stArray[1]) , equalTo(true));
    assertThat(tproj.isValidPhoneNumber(stArray[2]) , equalTo(false));
    //PhoneBill tbill = tproj.checkArgs(0, stArray);
    //assertThat(tbill.getCaller(0), equalTo("9584758564"));
    assertThat(tproj.checkArgs(0, stArray) , equalTo(0));
   // assertThat(tproj.checkArgs(0, stArray) , equalTo(0));

    //assertThat(tproj.display(), containsString("Customer  :   " + tname + "Caller : " + tcaller + "    Callee :  " + tcallee));
  }

  @Test
  void testingProject3Incorrect(){
    String tname="Ash%^^&%&^%&^%123";
    String tcaller= "9sdfs324frd";
    String tcallee ="857768978645354353";
    String tstart = "04/28/2022 01:28 pm";
    String tend = "04/28/2022 01:45 pm";
    Project3 tproj= new Project3(tname, tcaller, tcallee, tstart, tend);

    //assertThat(tproj.validName(tname) , equalsTo(false));
    assertThat(tproj.isValidPhoneNumber(tcaller) , equalTo(false));
    assertThat(tproj.isValidPhoneNumber(tcallee) , equalTo(false));
  }


  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project3.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }
}



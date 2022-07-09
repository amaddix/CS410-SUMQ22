package edu.pdx.cs410J.amaddix;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

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
    Project1 tproj = new Project1();
    PhoneBill tbill = new PhoneBill();
    assertThat(null, equalTo(tbill.getCustomer()));

  }

  @Test
  void testingProject1Correct() {
    String tname="Ashley";
    String tcaller= "9584758564";
    String tcallee ="8577689786";
    Project1 tproj= new Project1(tname, tcaller, tcallee);

    //assertThat(tproj.validName(tname) , equalsTo(true));
    assertThat(tproj.isValidPhoneNumber(tcaller) , equalTo(true));
    assertThat(tproj.isValidPhoneNumber(tcallee) , equalTo(true));
    //assertThat(tproj.display(), containsString("Customer  :   " + tname + "Caller : " + tcaller + "    Callee :  " + tcallee));
  }

  @Test
  void testingProject1Incorrect(){
    String tname="Ash%^^&%&^%&^%123";
    String tcaller= "9sdfs324frd";
    String tcallee ="857768978645354353";
    Project1 tproj= new Project1(tname, tcaller, tcallee);

    //assertThat(tproj.validName(tname) , equalsTo(false));
    assertThat(tproj.isValidPhoneNumber(tcaller) , equalTo(false));
    assertThat(tproj.isValidPhoneNumber(tcallee) , equalTo(false));
  }


  @Test
  void readmeCanBeReadAsResource() throws IOException {
    try (
      InputStream readme = Project1.class.getResourceAsStream("README.txt")
    ) {
      assertThat(readme, not(nullValue()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(readme));
      String line = reader.readLine();
      assertThat(line, containsString("This is a README file!"));
    }
  }
}

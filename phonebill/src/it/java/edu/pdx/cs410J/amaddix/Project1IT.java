package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.InvokeMainTestCase;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Tests the functionality in the {@link Project3} main class.
 */
class Project3IT extends InvokeMainTestCase {

    /**
     * Invokes the main method of {@link Project3} with the given arguments.
     */
    private MainMethodResult invokeMain(String... args) {
        return invokeMain( Project3.class, args );
    }

  /**
   * Tests that invoking the main method with no arguments issues an error
   */

  @Test
    void testNoCommandLineArguments() {
        MainMethodResult result = invokeMain();
        assertThat(result.getTextWrittenToStandardError(), containsString("Missing command line arguments"));

    }

    @Test
    void testWithWrongCaller() {
        MainMethodResult result = invokeMain("Ashley", "44535777", "979787678", "07/13/2022", "10:55", "07/13/2022", "11:30");
        assertThat(result.getTextWrittenToStandardError(), containsString("Caller Phone number Invalid"));

    }

    @Test
    void testWithWrongCallee() {
        MainMethodResult result = invokeMain("Ashley", "3444535777", "979787678", "07/13/2022", "10:55", "07/13/2022", "11:30");
        assertThat(result.getTextWrittenToStandardError(), containsString("Callee Phone number Invalid"));

    }

    @Test
    void testWithWrongStart() {
        MainMethodResult result = invokeMain("Ashley", "4453577734", "9797876784", "07/asadas2022", "10:5sds5", "07/13/2022", "11:30");
        assertThat(result.getTextWrittenToStandardError(), containsString("No start and/or end call time for phone call- will be saved as '0'"));

    }

    @Test
    void testCorrect(){
        String stArray[] = new String[9];
        stArray[0] = "Ashley";
        stArray[1] = "9584758564";
        stArray[2] = "8577689786";
        stArray[3] = "04/28/2022";
        stArray[4] = "01:28";
        stArray[5] = "am";
        stArray[6] = "04/28/2022";
        stArray[7] = "01:45";
        stArray[8] = "am";

        String start = "04/28/2022 1:28 am";
        String end = "04/28/2022 1:45 am";
        Project3 tproj = new Project3();
        //Project1 tproj = new Project1(stArray[0], stArray[1], stArray[2], start, end);

        MainMethodResult result = invokeMain(stArray);
        assertThat(tproj.checkArgs(0, stArray), equalTo(2));
      //  assertThat(tproj.checkArgs(0,stArray), equalTo(2));
    }



/*
    @Test
    void testWithNoTime() {
        MainMethodResult result = invokeMain("Ashley", "4453577734", "9797876784");
        assertThat(result.getTextWrittenToStandardError(), containsString("End time invalid"));

    }*/
/*
    @Test
    void testWithNoNUm() {
        MainMethodResult result = invokeMain("Ashley");
        assertThat(result.getTextWrittenToStandardError(), containsString("No Caller or Callee numbers - No Phone call recored"));

    }*/


}
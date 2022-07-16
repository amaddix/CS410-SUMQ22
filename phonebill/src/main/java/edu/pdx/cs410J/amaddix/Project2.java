package edu.pdx.cs410J.amaddix;

import com.google.common.annotations.VisibleForTesting;

import java.io.*;
import java.util.Scanner;

import edu.pdx.cs410J.ParserException;
/**
 * The main class for the CS410J Phone Bill Project
 * Saves a list of different customer bills - each followed by a call list
 * containing the caller+ callee numbers, and the start time and end time of phone call
 *
 * FUNCTIONS -
 * constructor(NULL)
 * constructor(string name, string caller, string callee, string starttime, string endtime)
 * isValidPhoneNumber (string number)
 * checkArgs(string commandLineArgs[])
 * main(String commandLineArgs[])
 * display()
 * fileManager(String filename)
 */
public class Project2 {

  private PhoneBill customerBill;
  private PhoneBill billList[];

  /**
   * Project constructor to initalize bill list to null
   */
  public Project2() {
    this.customerBill = new PhoneBill();
    this.billList = null;
  }

  /**
   * Initialize constructor with string arg for each variable in bill
   *
   * @param tname
   * @param tcaller
   * @param tcallee
   * @param tstart
   * @param tend
   */

  public Project2(String tname, String tcaller, String tcallee, String tstart, String tend) {
    this.customerBill = new PhoneBill(tname, tcaller, tcallee, tstart, tend);
    this.billList = null;
  }


  /**
   * Takes a string that should be a customer phone number, and verifies that it matches that format.
   *
   * @param phoneNumber
   * @return true if string matches phone number, false otherwise
   */

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    String phonePattern = "^([0-9]{10})|([0-9]{3}-[0-9]{3}-[0-9]{4})|([0-9]{10})|([0-9]{3} [0-9]{3} [0-9]{4})$";
    if (phoneNumber.matches(phonePattern)) {
      return true;
    } else
      return false;
  }

  /**
   * Reads in list of arguments that would make up a phone bill, and filter through to identify information
   *
   * @param initialIndex
   * @param targs
   * @return 0 if a phoneBill was not made, 1 if it was made without call time, 2 if it was made with all arguments
   */

  static public int checkArgs(int initialIndex, String targs[]) {
    String namePattern = "^[a-zA-Z0-9 ]+$";
    String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/2022 [012][0-9]:[0-6][0-9]$";
    //String tcustomer = null;
    boolean flag = true;

    //READING IN ARGS FROM FILE- SAVING AS INITIAL PHONE BILL-
    //if no args were read in from command line
    System.out.println("length " + targs.length);
    System.out.println("index " + initialIndex);
    //String customerName

    if (targs.length == 0) {
      System.err.println("Missing command line arguments");

    }
/*
    int i = initialIndex;
    while (i < targs.length){
      if ((targs[i]).matches(namePattern)) {
        tcustomer = tcustomer + (targs[i]);
      } else {
        flag = true;
      }
    i++;
  }

    int argLength = targs.length - initialIndex;
    if(argLength == 1){
      //only customer
      if (tcustomer.matches(namePattern)) {
        PhoneBill tbill = new PhoneBill(tcustomer);
        return tbill;
      }
      else{
        System.err.println("Customer name invalid  " + tcustomer);
      }

    }
    if(argLength == 2){
      //only numbers
      if (isValidPhoneNumber(targs[initialIndex + 1])) {
        if (isValidPhoneNumber(targs[initialIndex + 2])) {
          PhoneBill tbill = new PhoneBill(null,targs[initialIndex+1], targs[initialIndex+2], null, null);
          return tbill;
        } else {
          System.err.println("Callee Phone number Invalid");
         // return 0;
        }
      }
      System.err.println("Caller Phone number Invalid");
    }

    if(argLength == 3) {

      //name + numbers
      if (tcustomer.matches(namePattern)) {
       // PhoneBill tbill = new PhoneBill(tcustomer);
        if (isValidPhoneNumber(targs[initialIndex + 1])) {
          if (isValidPhoneNumber(targs[initialIndex + 2])) {
            PhoneBill tbill = new PhoneBill(null, targs[initialIndex + 1], targs[initialIndex + 2], null, null);
            return tbill;
          } else {
            System.err.println("Callee Phone number Invalid");
            // 0;
          }
        }
        System.err.println("Caller Phone number Invalid");
      } else {
        System.err.println("Customer name invalid");
      }
    }
    if(argLength == 6){
      //numbers + times
      if (isValidPhoneNumber(targs[initialIndex + 1])) {
        if (isValidPhoneNumber(targs[initialIndex + 2])) {
          String tstart = targs[initialIndex + 3] + " " + targs[initialIndex + 4];
          if (tstart.matches(datePattern)) {
            String tend = targs[initialIndex + 5] + " " + targs[initialIndex + 6];
            if (tend.matches(datePattern)) {
              // return 2;
              PhoneBill tbill = new PhoneBill(tcustomer, targs[initialIndex + 1], targs[initialIndex + 2], tstart, tend);
              return tbill;
            } else {
              System.err.println("End time invalid");
              System.out.println("End time invalid");
            }

          }
        }else {
          System.err.println("Callee Phone number Invalid");
          // return 0;
        }
      }
      System.err.println("Caller Phone number Invalid");
    }

    if( argLength == 7) {
      //name, numbers, times
      //name + numbers
      if (tcustomer.matches(namePattern)) {
        //PhoneBill tbill = new PhoneBill(tcustomer);
        if (isValidPhoneNumber(targs[initialIndex + 1])) {
          if (isValidPhoneNumber(targs[initialIndex + 2])) {
            String tstart = targs[initialIndex + 3] + " " + targs[initialIndex + 4];
            if (tstart.matches(datePattern)) {
              String tend = targs[initialIndex + 5] + " " + targs[initialIndex + 6];
              if (tend.matches(datePattern)) {
                // return 2;
                PhoneBill tbill = new PhoneBill(tcustomer, targs[initialIndex + 1], targs[initialIndex + 2], tstart, tend);
                return tbill;
              } else {
                System.err.println("End time invalid");
                System.out.println("End time invalid");
              }

            }
          } else {
            System.err.println("Callee Phone number Invalid");
            // return 0;
          }
        }
        System.err.println("Caller Phone number Invalid");
      } else {
        System.err.println("Customer name invalid");
      }
    }
      return null;
    }

*/
    // boolean flag = false;

    if (targs.length > initialIndex) {
      if (targs[initialIndex].matches(namePattern)) ;
      flag = true;
    } else {
      System.err.println("no customer name");
      flag = false;
    }

    if (targs.length >= initialIndex + 3) {
      if (isValidPhoneNumber(targs[initialIndex + 1])) {
        if (isValidPhoneNumber(targs[initialIndex + 2])) {
          //System.out.println("Caller : " + targs[1] + "   Callee: " + targs[2]);
        } else {
          System.err.println("Callee Phone number Invalid");
          return 0;
        }
        //if args 4 and 5 are read in, we confirm they match the pattern of a date.
        if (targs.length >= initialIndex + 7) {
          String tstart = targs[initialIndex + 3] + " " + targs[initialIndex + 4];

          if (tstart.matches(datePattern)) {
            String tend = targs[initialIndex + 5] + " " + targs[initialIndex + 6];
            if (tend.matches(datePattern)) {
              if (flag == true) {
                return 2;
              } else {
                return 1;
              }
            } else {
              System.err.println("End time invalid");
              System.out.println("End time invalid");

              return 0;
            }
          } else {
            System.err.println("Start time invalid");
            return 0;
          }
        }
        //if no date/time read in, save as null
        else {
          System.err.println("No start and/or end call time for phone call- will be saved as '0'");
          return 1;
        }
      } else {
        System.err.println("Caller Phone number Invalid");
        return 0;
      }
    } else {
      System.err.println("No Caller or Callee numbers - No Phone call recored");
      return 0;
    }

  }


  /**
   * displays the information of a phone bill within a phone
   */

  public int display() {
    /*
    String tname = this.customerBill.getCustomer();
    if((this.customerBill.getCaller(0) ) != null) {
      String tcaller = this.customerBill.getCaller(0);
      String tcallee = this.customerBill.getCallee(0);
      String tStart = this.customerBill.getStartTime(0);
      String tend = this.customerBill.getEndTime(0);

      ///
      System.out.println("Customer  :   " + tname);
      System.out.println("Caller : " + tcaller + "    Callee :  " + tcallee);
      System.out.println(tStart + " - " + tend);
      return 1;
    }

    System.out.println("Customer  :   " + tname);
    return 0;*/
    return this.customerBill.display();

  }

  /**
   * allows option to save a phone bill from a text file, or write a phone bill to a text file
   *
   * @param filename
   * @return
   * @throws ParserException
   * @throws java.io.FileNotFoundException
   * @throws UnsupportedEncodingException
   */

  public PhoneBill fileManager(String filename) throws ParserException, java.io.FileNotFoundException, UnsupportedEncodingException {
    try {

      if (filename != null) {
        //System.err.println("error- making new");
        System.out.println("bill -> file contents");
        TextDumper dumper = new TextDumper(new FileWriter(filename));
        // PhoneBill tbill = this.customerBill;
        dumper.dump(this.customerBill);
        //return tbill;

        System.out.println("found file name  " + filename);
        Reader readerType = new FileReader(filename);
        TextParser parser = new TextParser(readerType);
        PhoneBill bill = parser.parse();
        System.out.println("file contents -> bill");
        bill.display();
        return bill;
      }
      //PhoneBill bill = new PhoneBill();
      //}
    } catch (ParserException ex) {
      // insert code to run when exception occurs
      System.out.println("error w parser");
    } catch (java.io.FileNotFoundException ex) {
      //} catch (java.text.FileNotFoundException ex) {
      // insert code to run when exception occurs
      System.out.println(ex);
      //PrintWriter writer = new PrintWriter(filename, "UTF-8");
      //Reader readerType = new FileReader(filename);
      //TextParser parser = new TextParser(readerType);

    } catch (IOException e) {
      e.printStackTrace();
    }

    PhoneBill bill = new PhoneBill();
    return bill;
  }

  /**
   * main - handles bulk of program managment
   * @param args
   */


  public static void main(String[] args) {
    System.out.println("Entering program: compilation success");
    String targs[] = new String[args.length];
    Project2 proj = null;
    //TextParser parser = new TextParser();
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath

    //String filePattern = "^[a-zA-Z0-9()_-.]+$";
    String filename = null;
    int initialArgFlag = 0;
    int numOptions = 0;
    boolean print = false;
    boolean textfile = false;
    boolean readme = false;
    int var = 0;
    int tempvar = 0;


    if (args.length == 0) {
      System.out.println("Error- missing arguments!");
      System.err.println("Missing command line arguments");
    } else {

      for (int i = 0; i < args.length; i++) {
        if (args[i].matches("-README")) {
          numOptions = numOptions + 1;
          readme = true;
        }

        //readme display
        else {
          if (args[i].matches("-print")) {
            //print
            numOptions = numOptions + 1;
            print = true;
            //System.out.println("in print" + numOptions);
          } else if (args[i].matches("-textFile")) {
            if (args.length >= i + 2) {
              //add to text
              //or read from text
              i = i + 1;
              numOptions = numOptions + 2;
              filename = args[i];
              textfile = true;
            }
          }
        }
      }

      if (readme == true) {
        System.out.println("Ashley Maddix\n" +
                "CS410-Summer 2022 - JavaP\n" +
                "\n" +
                "This project currently reads in 3 strings from the command line, a customer name, caller phone number, and a callee\n" +
                "phone number. The main program reads in these args and confirms that the first arg is a string of only charactors,\n" +
                "and confirms both the following args are strings containing 10 digits (to match a phone number). If these requirements\n" +
                "are met, a phone bill is created with these values. Otherwise the program should exit and return an error message.");
      } else {


        //  IF ARGS ARE PASSED IN COMMAND LINE, CHECK IF THEY MATCH CORRECT FORMAT OF PHONE BILL- SAVE IF SO
        if (args.length >= numOptions) {
          System.out.println("length " + args.length);
          System.out.println(" num options : " + numOptions);
          initialArgFlag = checkArgs(numOptions, args);
          System.out.println("flag - " + initialArgFlag);

          //tbill.display();
          // proj = new Project2(tbill);


          //System.out.println("returned value" + initialArgFlag);

          //    var = 1;

          //  tempvar = var;

          if (initialArgFlag == 1) {
            if (args.length == 6 + numOptions) {
              String tStart = args[numOptions + 2] + " " + args[numOptions + 3];
              String tend = args[numOptions + 4] + " " + args[numOptions + 5];
              proj = new Project2("unknown", args[numOptions], args[numOptions + 1], tStart, tend);
            }
          } else if (initialArgFlag == 2) {
            // System.out.println("length " + args.length);

            if (args.length == numOptions + 7) {
              // System.out.println("im in here");
              String tStart = args[numOptions + 3] + " " + args[numOptions + 4];
              String tend = args[numOptions + 5] + " " + args[numOptions + 6];
              proj = new Project2(args[numOptions], args[numOptions + 1], args[numOptions + 2], tStart, tend);
            }
          }
          if(proj != null) {
            if (print == true) {
              proj.display();
            }
          }

        } /*else {
              System.err.println("Missing command line arguments");
              System.out.println("Arguments found:  ");
              for (String arg : args) {
                System.out.println(args);
              }
            }*/


        //if print flag is called + we have arguments for a phone call

        if (textfile == true) {
          //  DISPLAY MENU
          //String tfile = filename;
          try {
            File tfile = new File(filename);
            if (!tfile.exists()) {
              tfile.createNewFile();
            }

              /*Scanner scanning = new Scanner(System.in);
              int choice = 0;
              while (choice != 3) {
                System.out.println(" 1.   Would you like to read in a list of bills from a text file? \n" +
                        " 2.  Would you like to save pre-exsisting bill list to a text file? \n" +
                        " 3.  Exit \n");
                choice = scanning.nextInt();

                PhoneBill bill = proj.fileManager(choice, filename);
              }*/
            if (proj != null) {
              PhoneBill bill = proj.fileManager(filename);
            }
          } catch (ParserException ex) {
            System.err.println(ex);
          } catch (java.io.FileNotFoundException ex) {
            System.err.println(ex);
          } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
          } catch (IOException e) {
            e.printStackTrace();
          }
        }
      }

    }

  }
}





/*
    //read in name of text file name from command line
    //verify that file exists

    //verify alpha,num,ok other-then call function ...
    if(filename.matches(filePattern))

    {

      //create function
      File billList = new File(filename);
      if (billList.exists()) {
        Proj = new Project1();
        Scanner myReader = new Scanner(billList);
        while (myReader.hasNextLine()) {
          String data = myReader.nextLine();
          if (data.matches(pattern)) {


          }
        }

      }
    }
    //otherwise create empty file
    else

    {
      System.out.println("The file does not exist.");
    }

  }
*/

    //call file writer - to save file contents into bill array


    //allow option to read bill array into file
    // System.out.println(""Would you );

    //allow option add a bill from text file (new one?) - save to bill array
    //allow option to save to file

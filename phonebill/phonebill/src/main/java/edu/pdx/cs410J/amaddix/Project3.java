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
public class Project3 {

  //private int billNum;
  //private PhoneBill billList[];
  private PhoneBill customerBill;

  /**
   * Project constructor to initalize bill list to null
   */
  public Project3() {
   // this.customerBill = new PhoneBill();
    this.customerBill = new PhoneBill();
  //  this.billList = new PhoneBill[50];
  //  this.billList[0] = new PhoneBill();
  //  billNum =billNum++;
  }

  /**
   * Initialize constructor with string arg for each variable in bill
   * @param tname- string customer name
   * @param tcaller - string caller number
   * @param tcallee - string callee number
   * @param tstart - string start time
   * @param tend - string end time
   */
  public Project3(String tname, String tcaller, String tcallee, String tstart, String tend) { this.customerBill = new PhoneBill(tname, tcaller, tcallee, tstart, tend); }

  /**
   * initialize with a phonebill type
   * @param tbill object
   */
  public Project3 (PhoneBill tbill){ this.customerBill= new PhoneBill(tbill); }

  /**
   * Takes a string that should be a customer phone number, and verifies that it matches that format.
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
   * @param initialIndex - int to keep track of index of targs
   * @param targs - sting array with command line args
   * @return int 0 if a phoneBill was not made, 1 if it was made without call time, 2 if it was made with all arguments
   */
  static public int checkArgs(int initialIndex, String targs[]) {
    String namePattern = "^[a-zA-Z0-9 ]+$";
    String datePattern = "^(0[0-9]|1[0-2])/([012][0-9]|3[01])/2022 ([0-9]|[012][0-9]):[0-6][0-9] ([a][m]|[p][m])$";
    boolean flag = true;


    //READING IN ARGS FROM FILE- SAVING AS INITIAL PHONE BILL-
    //if no args were read in from command line
    if (targs.length == 0) {
      System.err.println("Missing command line arguments");
    }
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
        } else {
          System.err.println("Callee Phone number Invalid");
          return 0;
        }
        //if args 4 and 5 are read in, we confirm they match the pattern of a date.
        if (targs.length >= initialIndex + 9) {
          String tstart = targs[initialIndex + 3] + " " + targs[initialIndex + 4] + " " + targs[initialIndex +5];

          if (tstart.matches(datePattern)) {
            String tend = targs[initialIndex + 6] + " " + targs[initialIndex + 7] + " " + targs[initialIndex+8];
            if (tend.matches(datePattern)) {
              if (flag == true) {
                return 2;
              } else {
                return 1;
              }
            } else {
              System.err.println("End time invalid");

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
          return 0;
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
    int var =0;
      if(this.customerBill != null) {
        var = this.customerBill.display();
      }
    return var;
  }

  /**
   * allows option to save a phone bill from a text file, or write a phone bill to a text file
   *
   * @param filename - name of file
   * @return
   * @throws ParserException
   * @throws java.io.FileNotFoundException
   * @throws UnsupportedEncodingException
   */
  public static PhoneBill fileManager(String filename, PhoneCall tcall) throws ParserException, java.io.FileNotFoundException, UnsupportedEncodingException {
    try {

          PhoneBill tbill = null;
          File tfile = new File(filename);
          if(tfile.isFile() == false || tfile.length() == 0){
            System.out.println("bill -> file contents");
            PrettyPrinter pp = new PrettyPrinter((new FileWriter(filename)));
            tbill = new PhoneBill(tcall);
            //tbill.display();
            pp.dump(tbill);
            return tbill;
          }
          else{
            System.out.println("found file name  " + filename);
            Reader readerType = new FileReader(filename);
            TextParser parser = new TextParser(readerType);
            System.out.println("file contents -> bill");
            tbill = parser.parse();
            //tbill.display();
            //if(tbill != null){
           // System.out.println(tcall.getCustomer());
            //System.out.println(tbill.getCustomer());
            //System.out.println((tbill.getCustomer().equals(tcall.getCustomer())));
            if(tbill != null && (tbill.getCustomer().equals(tcall.getCustomer()))) {

              tbill.addPhoneCall(tcall);
              // tbill.display();
              System.out.println("bill -> file contents");
              PrettyPrinter pp = new PrettyPrinter((new FileWriter(filename)));
              // tbill = new PhoneBill(tcall);
              //tbill.display();
              pp.dump(tbill);
            }
            else if (tbill != null){
              System.err.println("Text file name doesnt match customer name given through command line- file not altered");
            }

              return tbill;

          }


      /*
      if (filename != null) {

        PhoneBill tbill = null;
        File tfile = new File(filename);


        //  IF NO FILE EXISTS -
        //  -CREATE NEW, ADD COMMAND LINE PHONE CALL TO THAT FILE
        if (!tfile.exists()) {
          tfile.createNewFile();

          System.out.println("bill -> file contents");
          PrettyPrinter pp = new PrettyPrinter((new FileWriter(filename)));
          tbill = new PhoneBill(tcall);
          tbill.display();
          System.out.println("what?");
          pp.dump(tbill);
        }

        System.out.println("found file name  " + filename);
        Reader readerType = new FileReader(filename);
        TextParser parser = new TextParser(readerType);
        System.out.println("file contents -> bill");

        //is.callNum = this.callNum +1;
        tbill = parser.parse();
        if (tbill != null) {
          tbill.addPhoneCall(tcall);
          tbill.display();
          System.out.println("bill -> file contents");
          PrettyPrinter pp = new PrettyPrinter((new FileWriter(filename)));
          pp.dump(tbill);
        } else {
          System.out.println("bill -> file contents");
          PrettyPrinter pp = new PrettyPrinter((new FileWriter(filename)));
          tbill = new PhoneBill(tcall);
          pp.dump(tbill);
          return tbill;
        }
      }
      //PhoneBill bill = new PhoneBill();
      //} */
    } catch (ParserException ex) {
      // insert code to run when exception occurs
      System.out.println("error w parser :" + ex);
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
   * @param args- String array with command line args
   */


  public static void main(String[] args) {

    System.err.println("usage: phonebill");
    System.out.println("Entering program: compilation success");
    String targs[] = new String[args.length];
    Project3 proj = null;
    //TextParser parser = new TextParser();
    PhoneCall tcall = null;  // Refer to one of Dave's classes so that we can be sure it is on the classpath
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
          //System.out.println("length " + args.length);
          //System.out.println(" num options : " + numOptions);
          initialArgFlag = checkArgs(numOptions, args);

          if (initialArgFlag == 1) {
            if (args.length == 8 + numOptions) {
              String tStart = args[numOptions + 2] + " " + args[numOptions + 3] + " " + args[numOptions + 4];
              String tend = args[numOptions + 5] + " " + args[numOptions + 6] + " " + args[numOptions + 7];
              //proj = new Project2
              tcall = new PhoneCall("unknown", args[numOptions], args[numOptions + 1], tStart, tend);
              //PhoneBill temp = new PhoneBill(tcall);
              //temp.display();
            }
          } else if (initialArgFlag == 2) {
            // System.out.println("length " + args.length);

            if (args.length == numOptions + 9) {
              //] System.out.println("im in here");
              String tStart = args[numOptions + 3] + " " + args[numOptions + 4] + " " + args[numOptions + 5];
              String tend = args[numOptions + 6] + " " + args[numOptions + 7] + " " + args[numOptions + 8];
              //proj = new Project2
              tcall = new PhoneCall(args[numOptions], args[numOptions + 1], args[numOptions + 2], tStart, tend);
              //PhoneBill temp = new PhoneBill(tcall);
              //temp.display();
            }
          }

            //if print flag is called + we have arguments for a phone call
          PhoneBill bill = null;
            if (textfile == true) {
              try{
                if(filename != null && tcall != null) {
                  if(tcall != null) {
                    bill = fileManager(filename, tcall);
                  }
                }
               // }
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

            if(print == true){
              if( bill != null) {
                bill.display();
              }
            }

            //print here
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

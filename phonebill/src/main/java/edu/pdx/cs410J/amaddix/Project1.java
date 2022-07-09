package edu.pdx.cs410J.amaddix;

import com.google.common.annotations.VisibleForTesting;
import java.util.regex.*;
/**
 * The main class for the CS410J Phone Bill Project
 */
public class Project1 {

  private PhoneBill customerBill;

  public Project1(){
    this.customerBill=new PhoneBill();
  }

  public Project1(String tname, String tcaller, String tcallee){
    this.customerBill= new PhoneBill(tname, tcaller, tcallee);
  }

  @VisibleForTesting
  static boolean isValidPhoneNumber(String phoneNumber) {
    String phonePattern ="^[0-9]{10}$";
    if(phoneNumber.matches(phonePattern)){
      return true;
    }
    else
      return false;
  }

/*
  public void display(){
    String tname= this.customerBill.getCustomer();
    System.out.println("Customer  :   " + tname);
    PhoneCall tcall= this.customerBill.getPhoneCall();
    String tcaller = tcall.getCaller();
    String tcallee = tcall.getCallee();
    System.out.println("Caller : " + tcaller + "    Callee :  " + tcallee);
  }

 */

  public static void main(String[] args) {

    Project1 proj;
    PhoneCall call = new PhoneCall();  // Refer to one of Dave's classes so that we can be sure it is on the classpath
    String pattern = "^[a-zA-Z0-9]+$";

    //if no args were read in from command line
    if(args.length == 0){
      System.err.println("Missing command line arguments");
    }

    //if the first arg read in matches char pattern- its the customer name/save

    else if (args[0].matches(pattern)) {
      System.out.println("Customer Name : " + args[0]);
        //should only be reading in 3 args atm- customer name callee number and caller number
        //check that args 2 and 3 are both phone numbers- save
      if (isValidPhoneNumber(args[1])) { if (isValidPhoneNumber(args[2])) {
        proj = new Project1(args[0], args[1], args[2]);
        System.out.println("Caller : " + args[1] + "   Callee: " + args[2]);
      }
      //if theres a first num but no second num- error
      }

        //if there are no num - error
        else {
          System.err.println("Missing phone numbers");
        }
    }

    System.out.println("Missing command line arguments");
    for (String arg : args) {
      System.out.println(arg);
    }



  }
}
package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AbstractPhoneBill;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class PrettyPrinter implements PhoneBillDumper<PhoneBill> {

  private final Writer writer;

  /**
   * constructor to Text dumper
   * @param writer- writer type variable for writing to files
   */

  public PrettyPrinter(Writer writer) {
    this.writer = writer;
  }

  /**
   * Dumps contents of a phonebill to text file
   * @param bill- to write to fill
   * @throws IOException - if phone bill cant be parsed
   */
  @Override
  public void dump(PhoneBill bill) throws IOException {
    try (
            PrintWriter pw = new PrintWriter(this.writer)
    ) {

      if(bill != null) {
        if (bill.getCustomer() != null) {
          pw.println("\t \t \t \t-Entering Bill Log-     \n");
          pw.println("\t \t Call list of customer     :\t" + bill.getCustomer());
        }
        for (int i = 0; i < bill.getCallNum(); i++) {
          if (bill.getCaller(i) != null) {
            pw.println("\t \t Incoming call from        :\t" + (bill.getCaller(i)));

            if (bill.getCallee(i) != null) {

              pw.println("\t \t Call received by          :\t" + (bill.getCallee(i)));

              if (bill.getStartTime(i) != null) {
                //pw.println(bill.getStartTime(i));

                int shourtomin = 0;
                int ehourtomin = 0;

                if (bill.getEndTime(i) != null) {
                  //split start time + end time ( search for patten of the two times) - save 2 date vars
                  //find difference
                  String stime = bill.getStartTime(i);
                  String stArray[] = stime.split(" ");
                  //System.out.println("what what " + stime + stArray[0]);
                  if (stArray[1].matches("(0[1-9]|[012][0-9]):([0-6][0-9]|[0-9])$")) {
                    String sttime[] = stArray[1].split(":");
                    shourtomin = Integer.parseInt(sttime[0]);
                    shourtomin = shourtomin * 60; //convert hours to min
                    shourtomin = shourtomin + Integer.parseInt(sttime[1]); // add orginal min on
                  }
                  String etime = bill.getEndTime(i);
                  String endArray[] = etime.split(" ");
                  if (endArray[1].matches("([0-9]|[012][0-9]):[0-6][0-9]$")) {
                    String ettime[] = endArray[1].split(":| ");
                    ehourtomin = Integer.parseInt(ettime[0]);
                    ehourtomin = ehourtomin * 60; //convert hours to min
                    ehourtomin = ehourtomin + Integer.parseInt(ettime[1]); // add orginal min on
                  }
                  int totalTime = 0;
                  if (stArray[2].matches(endArray[2])) {
                    totalTime = ehourtomin - shourtomin;
                  } else {
                    totalTime = (720 - shourtomin) + ehourtomin;
                  }

                  pw.println("\t \t Total call time           :\t" + totalTime + " minutes");
                  pw.println("\t \t" + bill.getStartTime(i) + "\t - \t" + bill.getEndTime(i));
                }
              }
            }
          }
        }


        pw.flush();
      }
    }
  }
}

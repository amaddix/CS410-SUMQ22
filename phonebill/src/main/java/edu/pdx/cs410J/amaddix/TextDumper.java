package edu.pdx.cs410J.amaddix;

import edu.pdx.cs410J.AppointmentBookDumper;
import edu.pdx.cs410J.PhoneBillDumper;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * TextDumper - writes a phone bill to a text file
 */

public class TextDumper implements PhoneBillDumper<PhoneBill> {
  private final Writer writer;

    /**
     * constructor to Text dumper
     * @param writer
     */

  public TextDumper(Writer writer) {
    this.writer = writer;
  }

    /**
     * dump- writes a phone bill to a text file
     * @param bill
     */

  @Override
  public void dump(PhoneBill bill) {
    try (
      PrintWriter pw = new PrintWriter(this.writer)
    ) {
        pw.println(bill.getCustomer());

<<<<<<< HEAD
        for(int i=0; i< bill.getCallNum(); i++) {
            if (bill.getCaller(i) != null) {
                pw.println(bill.getCaller(i));

                if (bill.getCallee(i) != null) {
                    pw.println(bill.getCallee(i));

                    if (bill.getStartTime(i) != null) {
                        pw.println(bill.getStartTime(i));
                    }
                    if (bill.getEndTime(i) != null) {
                        pw.println(bill.getEndTime(i));
                    }
                }
            }
=======
        if (bill.getCaller(0) != null) {
          pw.println(bill.getCaller(0));
        }
        if (bill.getCallee(0) != null) {
          pw.println(bill.getCallee(0));
        }
        if (bill.getStartTime(0) != null) {
          pw.println(bill.getStartTime(0));
        }
        if (bill.getEndTime(0) != null) {
          pw.println(bill.getEndTime(0));
>>>>>>> c76be71a01acacd17615f6f74cbc08106523de9a
        }


        pw.flush();
    }
  }
}

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
        }


        pw.flush();
    }
  }
}

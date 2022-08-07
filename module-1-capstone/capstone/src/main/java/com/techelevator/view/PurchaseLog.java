package com.techelevator.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
/*
This class creates the log file and keeps track of all transactions in the vending machine. This includes a time and date stamp
of every instance of money being fed or dispensed, as well as tracking which items are being sold.
 */


public class PurchaseLog {
    private static String getCurrentTime() {
        SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date now = new Date();
        return date.format(now);
    }

    public static void log(String logEvent, double startingBalance, double endingBalance) {
        File file = new File("log.txt");

        try (PrintWriter writer = new PrintWriter(new FileWriter(file, true))) {
            writer.println(getCurrentTime() + logEvent + " " + Product.df.format(startingBalance)
                    + " " + Product.df.format(endingBalance));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

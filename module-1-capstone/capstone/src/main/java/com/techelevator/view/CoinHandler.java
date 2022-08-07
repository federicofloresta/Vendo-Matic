package com.techelevator.view;
/*
This class does the arithmetic of the Vending Machine. It establishes the starting balance and updated it constantly throughout the
program. This class is also responsible for giving back the correct amount of coins as change in the largest denominations possible.
 */

import static com.techelevator.view.Product.df;

public class CoinHandler {
    //variable
    public double balance;

    //constructor 1 - sets the initial balance to 0
    public CoinHandler() {
        balance = 0;
    }

    //constructor 2 - updates balance
    public CoinHandler(double balance) {
        this.balance = balance;
    }

    //getters
    public double getBalance() {
        return balance;
    }

    //add money method - adds the money fed into the balance of the machine
    public void addMoney(int amountToDeposit) {
        PurchaseLog.log(" FEED MONEY", balance, balance + amountToDeposit);
        balance += amountToDeposit;
    }

    //subtracts the machine balance when an item is purchased/dispensed.
    public void subtractMoney(double amountToSubtract) {
        this.balance -= amountToSubtract;
    }

    //creating coins
    public String returnChangeAsCoins() {
        final int QUARTER = 25;
        final int DIME = 10;
        final int NICKEL = 5;
        int changeTracker = (int) (balance *= 100);
        int quartersToReturn = 0;
        int dimesToReturn = 0;
        int nickelsToReturn = 0;

        //determine what denomination of change to give, update machine balance to zero
        //passes change through while loop giving the smallest amount of change possible until balance is zero
        //return the change output message
        // while (big % flag != 0)  we get a remainder here, we use that remainder and divide by small to see if we get the big jumps  {
        //  flag/small =
        // }
        while (changeTracker > 0) {
            if (changeTracker >= QUARTER) {
                quartersToReturn++;
                changeTracker -= QUARTER;
            } else if (changeTracker >= DIME) {
                dimesToReturn++;
                changeTracker -= DIME;
            } else if (changeTracker >= NICKEL) {
                nickelsToReturn++;
                changeTracker -= NICKEL;
            }
        }

        //logs the given change; set balance back to 0
        PurchaseLog.log(" GIVE CHANGE", balance / 100, 0);
        balance = 0;

        //output message
        return "Thank you for your purchase! \n \nYour change is: " + (quartersToReturn) + " quarter" + (quartersToReturn == 1 ? "" : "s") + ", " +
                dimesToReturn + " dime" + (dimesToReturn == 1 ? "" : "s") + ", " +
                "and " + nickelsToReturn + " nickel" + (nickelsToReturn == 1 ? "" : "s") + "!";
    }

    //toString override - display the updated balance after feeding the money
    @Override
    public String toString() {
        return "Your balance is " + df.format(balance);
    }
}
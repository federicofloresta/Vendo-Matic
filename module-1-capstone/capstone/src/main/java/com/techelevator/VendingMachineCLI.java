package com.techelevator;

import com.techelevator.view.*;

import java.io.IOException;
import java.util.Scanner;

import static com.techelevator.view.Product.df;

/*
This is the Vendo-Matic 800, our Java-written Vending Machine assigned as our first Capstone Project with Merit America! We were able to
 successfully complete this project before the deadline and efficiently planned and designated goals for the program.
 We are proud of the program we have created and had a lot of fun making it!

Authored by: Federico Floresta(Navigator) and Kyle Blakley(Driver) 04/2022
 */


public class VendingMachineCLI {
    //instance variables with arrays for each set
    //menu menu variables
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    //sub menu purchasing variables
    private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
    private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select a product to buy";
    private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish the transaction";
    private static final String[] SUB_MENU_OPTIONS = {SUB_MENU_OPTION_FEED_MONEY, SUB_MENU_OPTION_SELECT_PRODUCT, SUB_MENU_OPTION_FINISH_TRANSACTION};
    //dollar bill variables
    private static final String MONEY_ONE_DOLLAR_BILL = "Feed me $1.00!";
    private static final String MONEY_TWO_DOLLAR_BILL = "Feed me $2.00!";
    private static final String MONEY_FIVE_DOLLAR_BILL = "Feed me $5.00!";
    private static final String MONEY_TEN_DOLLAR_BILL = "Feed me $10.00!";
    private static final String[] SUB_MENU_MONEY = {MONEY_ONE_DOLLAR_BILL, MONEY_TWO_DOLLAR_BILL, MONEY_FIVE_DOLLAR_BILL, MONEY_TEN_DOLLAR_BILL};

    //instantiates new menu
    private final Menu MENU;

    //constructor
    public VendingMachineCLI(Menu menu) {
        this.MENU = menu;
    }

    //run method; instantiates a coinHandler and Inventory
    public void run() throws IOException {
        Inventory inventory = new Inventory();
        CoinHandler coinHandler = new CoinHandler();


        //creating the banner
        System.out.println("*".repeat(50));
        System.out.println("*".repeat(9) + " " + "WELCOME TO THE VENDING MACHINE" + " " + "*".repeat(9));
        System.out.println("*".repeat(50));

        //a lot of logic for navigating the menu
        while (true) {

            String choice = (String) MENU.getChoiceFromOptions(MAIN_MENU_OPTIONS);
            // While the loop is active it uses if/else logic to navigate the different menus
            //main menu choice 1 - displays vending machine items; loops back to main menu
            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                inventory.displayInventory();

                //main menu option 2 - if option 2 is selected, open money sub menu
            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
                int balance = 0;
                System.out.println("Your balance is: " + df.format(balance));
                boolean isPurchasing = true;
                while (isPurchasing) {
                    //sub menu options displayed here
                    String subChoice = (String) MENU.getChoiceFromOptions(SUB_MENU_OPTIONS);

                    //feeding money logic; accesses dollar bill sub menu; updates balance accordingly
                    if (subChoice.equals(SUB_MENU_OPTION_FEED_MONEY)) {
                        //sub menu money displayed here
                        String moneyChoice = (String) MENU.getChoiceFromOptions(SUB_MENU_MONEY);
                        switch (moneyChoice) {
                            case MONEY_ONE_DOLLAR_BILL:
                                coinHandler.addMoney(1);
                                break;
                            case MONEY_TWO_DOLLAR_BILL:
                                coinHandler.addMoney(2);
                                break;
                            case MONEY_FIVE_DOLLAR_BILL:
                                coinHandler.addMoney(5);
                                break;
                            case MONEY_TEN_DOLLAR_BILL:
                                coinHandler.addMoney(10);
                        }
                        System.out.println("Your balance is: " + df.format(coinHandler.getBalance()));

                        //sub menu option 2 - displays inventory; asks for product selection; displays error if
                        //incorrect selection is made
                    } else if (subChoice.equals(SUB_MENU_OPTION_SELECT_PRODUCT)) {
                        System.out.println("-".repeat(50));
                        inventory.displayInventory();
                        System.out.println("-".repeat(50));
                        System.out.println("Enter the product code for your favorite snack!: ");
                        Scanner productScanner = new Scanner(System.in);
                        String selection = productScanner.next();
                        Product prod = inventory.getProductLocations().get(selection.toUpperCase());
                        if (prod == null) {
                            System.out.println("Hey, we don't sell that here!");
                        } else {
                            prod.dispenseProduct(coinHandler, prod);
                        }
                        //sub menu option 3 - finishes transaction by returning the remaining balance as coins; closes while loop;
                        // returns to main menu
                    } else if (subChoice.equals(SUB_MENU_OPTION_FINISH_TRANSACTION)) {
                        System.out.println(coinHandler.returnChangeAsCoins());
                        isPurchasing = false;
                    }

                }
                //main menu option 3 - terminates program with exit code 0
            } else {
                System.exit(0);
            }
        }
    }

    //main method - instantiates new menu and CLI; runs CLI;
    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }
}
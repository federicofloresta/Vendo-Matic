package com.techelevator.view;


import java.text.DecimalFormat;
/*
The Product class creates a product containing the item's location, name, price, category, and quantity. It is responsible for
dispensing the correct product when selected, logging the transaction, and updating the balance and quantity after a purchase. This class
also contains the toString override that prints each product as a list when prompted by the user in the main menu.
 */

public class Product {
    //instance variables
    public static DecimalFormat df = new DecimalFormat("$0.00");
    private final String LOCATION;
    private final String NAME;
    private final double PRICE;
    private final String CATEGORY;
    private int quantity;

    //constructor
    public Product(String location, String name, double price, String category, int quantity) {
        this.LOCATION = location;
        this.NAME = name;
        this.PRICE = price;
        this.CATEGORY = category;
        this.quantity = quantity;
    }

    //getter
    public String getLocation() {
        return LOCATION;
    }

    /*
        method for dispensing product
        displays as sold out if qty is 0
        displays insufficient funds if not given enough money
        if meeting all requirements:
        logs transaction
        subtracts 1 from item quantity
        subtracts item price from balance
        */
    public void dispenseProduct(CoinHandler money, Product product) {
        if (quantity == 0) {
            System.out.println("Product is sold out! Please make another selection.");
        } else if (product.PRICE <= money.getBalance()) {
            PurchaseLog.log(" " + NAME + " " + LOCATION, money.getBalance(), money.getBalance() - product.PRICE);
            money.subtractMoney(product.PRICE);
            quantity -= 1;
            System.out.println("Dispensing " + NAME + ", " + df.format(PRICE) + "\n" + printSound(CATEGORY));
        } else {
            System.out.println("Insufficient funds! Feed me more money!");
        }
    }

    //method to display appropriate "sound" when an item is dispensed
    public String printSound(String category) {
        switch (category) {
            case "Chip":
                return "Crunch Crunch, Yum!";
            case "Candy":
                return "Munch Munch, Yum!";
            case "Drink":
                return "Glug Glug, Yum!";
            case "Gum":
                return "Chew Chew, Yum!";
            default:
                return "Category doesn't exist!";
        }
    }

    //toString override for displaying all products
    @Override
    public String toString() {
        return "Select: " + LOCATION + " | " + NAME + " - " + df.format(PRICE) + " - " + " QTY: " + quantity;

    }
}
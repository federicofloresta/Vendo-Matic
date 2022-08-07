package com.techelevator.view;

import java.io.File;
import java.io.IOException;
import java.util.*;

/*
The Inventory class is responsible for reading the csv file, splitting the file at the pipe, and placing each new string into
a Product. This also contains an Inventory Map to separate the item location and price to later update the quantity in stock
and isolate the price for any purchases.
 */

public class Inventory {
    //instance variables for the product list and map
    List<Product> products = new ArrayList<>();
    Map<String, Product> productLocations = new HashMap<>();

    //inventory method - creates the list and map
    public Inventory() throws IOException {
        createInventoryList();
        createInventoryMap();
    }

    //getter
    public Map<String, Product> getProductLocations() {
        return productLocations;
    }

    //method to create inventory list
    //reads the csv file and splits the file at the pipe
    //saves each delimiter as a product
    public void createInventoryList() {
        String fileName = "vendingmachine.csv";
        try (Scanner sc = new Scanner(new File(fileName))) {
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] entries = line.split("\\|");
                products.add(new Product(entries[0], entries[1], Double.parseDouble(entries[2]), entries[3], 5));
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    //method to create inventory map with key as product location; value as price
    public void createInventoryMap() {
        for (Product product : products) {
            productLocations.put(product.getLocation(), product);
        }
    }

    //method for displaying all products
    public void displayInventory() {
        for (Product product : products) {
            System.out.println(product);
        }
    }
}

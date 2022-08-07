package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class ProductTest {
    @Test
    public void soundTest() {
        //Arrange
        String testCategory = "Chip";
        Product productTest = new Product("A1", "Potato Crisps", 3.05, "Chips", 5);
        //Act
        String expectedOutput = "Crunch Crunch, Yum!";
        //Assert
        Assert.assertEquals(expectedOutput, productTest.printSound(testCategory));
    }
}
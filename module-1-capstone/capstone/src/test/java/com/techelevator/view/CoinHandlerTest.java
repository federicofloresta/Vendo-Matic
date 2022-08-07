package com.techelevator.view;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CoinHandlerTest {

    public CoinHandlerTest() {

    }

    @Test
    public void testReturnChangeAsCoins() {
        //Arrange
        CoinHandler testCoinHandler = new CoinHandler(0.25);
        //Act
        String expectedOutput = "Thank you for your purchase! \n \nYour change is: 1 quarter, 0 dimes, and 0 nickels!";
        //Assert
        Assert.assertEquals(expectedOutput, testCoinHandler.returnChangeAsCoins());
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine.view;

import com.zakharovakr.vendingmachine.dto.Change;
import com.zakharovakr.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class VendingMachineView {

    private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR ===\n" + message);
    }

    public void displayIntroBanner() {
        io.print("\n==== Hi there! Welcome to my Vending Machine! === \n");
    }

    public void displayItem(List<Item> itemList) {
        for (Item currentItem : itemList) {
            io.print(currentItem.getItemId() + ": "
                    + currentItem.getItemName() + " - $"
                    + currentItem.getItemPrice()+ " - Item Count: "
                    + currentItem.getItemCount());
        }
    }

    public BigDecimal getBalance() {
        //user is only able to type in a Big Decimal format - refactor that if have time
        return io.readBigDecimal("\nHow much money would you like to insert? (e.g. 2.00)");
    }

    public String getMenuSelection(List<Item> items) {
        String userInput = io.readString("Please select an item:\n"
                + "If you'd like to leave please type 'exit'.");

        if (userInput.trim().toLowerCase().equals("exit")) {
            return "exit";
        } else {
            return userInput;
        }
    }

    public void displayExitBanner() {
        io.print("Thanks for shopping with us!");
    }

    public void displayChange(Change change) {
        io.print("Here's your change:\n"
                + "Quarters: " + change.getQuarters() + "\n"
                + "Dimes: " + change.getDimes() + "\n"
                + "Nickels: " + change.getNickels() + "\n"
                + "Pennies: " + change.getPennies());
    }

}

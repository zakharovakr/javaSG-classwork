package com.zakharovakr.vendingmachine.controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.zakharovakr.vendingmachine.dao.VendingMachinePersistenceException;
import com.zakharovakr.vendingmachine.dto.Change;
import com.zakharovakr.vendingmachine.dto.Item;
import com.zakharovakr.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.zakharovakr.vendingmachine.service.VendingMachineNoItemInventoryException;
import com.zakharovakr.vendingmachine.service.VendingMachineOutOfStockException;
import com.zakharovakr.vendingmachine.service.VendingMachineServiceLayer;
import com.zakharovakr.vendingmachine.view.VendingMachineView;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class VendingMachineController {

    private VendingMachineView view;
    private VendingMachineServiceLayer service;

    //constructor - dependency injection
    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    //main method
    public void run() {
        boolean keepGoing = true;
        String menuSelection = "";

        try {
            while (keepGoing) {
                displayMenu();
                insertBalance();
                menuSelection = getMenuSelection();

                if (menuSelection.equals("exit")) {//program quits if user types "exit"
                    displayExitMessage();
                    keepGoing = false;
                } else {
                    vend(menuSelection);
                    displayExitMessage();
                    keepGoing = false;
                }
            }
        } catch (Exception e) {
            displayErrorMessage(e.getMessage());
        }
    }

    //displaying welcome banner and available items 
    private void displayMenu() throws VendingMachinePersistenceException {
        view.displayIntroBanner();
        List<Item> items = service.getAvailableItems();
        view.displayItems(items);
    }

    //asking user for balance input and assigning that value to service layer for further manipulations
    private void insertBalance() {
        BigDecimal balance = view.getBalance();
        service.setBalance(balance);
    }

    //getting an item choice from user
    private String getMenuSelection() throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
        List<Item> items = service.getAvailableItems();
        return view.getMenuSelection(items);
    }

    
    //main method - vends item to user and displays change 
    private void vend(String itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException, VendingMachineNoItemInventoryException {
        Change change = service.vend(itemId);
        view.displayChange(change);
    }
    
    //displays exit banner
    private void displayExitMessage() {
        view.displayExitBanner();
    }
    
    private void displayErrorMessage(String message) {
        view.displayErrorMessage("Something went wrong");
    }

}

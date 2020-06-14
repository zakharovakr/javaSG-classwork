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

    public VendingMachineController(VendingMachineView view, VendingMachineServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;
        String menuSelection = "";

        try {
            //run while the customer doesn't type "exit"
            while (keepGoing) {
                displayMenu();
                insertBalance();
                menuSelection = getMenuSelection();

                if (menuSelection.equals("exit")) {
                    displayExitBanner();
                    keepGoing = false;
                } else {
                    vend(menuSelection);
                    displayExitBanner();
                    keepGoing = false;
                }
            }
//        } catch (VendingMachinePersistenceException e) {
        } catch (Exception e) {
            view.displayErrorMessage(e.getMessage());
        }
    }

    private void displayMenu() throws VendingMachinePersistenceException {
        view.displayIntroBanner();
        List<Item> items = service.getAvailableItems();
        view.displayItem(items);
    }

    private void insertBalance() {
        BigDecimal balance = view.getBalance();
        service.setBalance(balance);
    }

    private String getMenuSelection() throws VendingMachinePersistenceException {
        List<Item> items = service.getAvailableItems();
        return view.getMenuSelection(items);
    }

    private void displayExitBanner() {
        view.displayExitBanner();
    }

    private void vend(String itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException, VendingMachineNoItemInventoryException {
        Change change = service.vend(itemId);
        view.displayChange(change);
    }

}

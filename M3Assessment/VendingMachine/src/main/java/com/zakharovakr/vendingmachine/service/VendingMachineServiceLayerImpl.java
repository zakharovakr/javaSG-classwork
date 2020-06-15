/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine.service;

import com.zakharovakr.vendingmachine.dao.VendingMachineAuditDao;
import com.zakharovakr.vendingmachine.dao.VendingMachineDao;
import com.zakharovakr.vendingmachine.dao.VendingMachinePersistenceException;
import com.zakharovakr.vendingmachine.dto.Change;
import com.zakharovakr.vendingmachine.dto.Item;
import com.zakharovakr.vendingmachine.dto.Coins;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static com.zakharovakr.vendingmachine.dto.Coins.*;

/**
 *
 * @author kristinazakharova
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    private BigDecimal balance;
    private VendingMachineDao dao;
    private VendingMachineAuditDao auditDao;

    //constructor dependency injection
    public VendingMachineServiceLayerImpl(VendingMachineDao dao, VendingMachineAuditDao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Item> getAvailableItems() throws VendingMachinePersistenceException {
        return dao.readAllItems();
    }

    @Override
    public Item getItemById(String itemId) throws VendingMachinePersistenceException {
        return dao.readById(itemId);
    }

    @Override
    //updates inventory list after user performs some action
    public void updateInventory(String itemId, Item item) throws VendingMachinePersistenceException {
        dao.updateInventory(itemId, item);
    }

    @Override
    //add money into machine
    public void setBalance(BigDecimal balance) {
        //setting the correct scale for BigDecimal
        this.balance = balance.setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public BigDecimal getBalance() {
        return balance;
    }

    @Override
    //main method, takes in an item id as a String, returns Change (as a result of purchase)
    public Change vend(String itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException, VendingMachineNoItemInventoryException {
        Item item = dao.readById(itemId);
        BigDecimal price = item.getItemPrice();
        Change change;

        //still working on this exception 
        
        /*if an item with this id doesn't exist, throw an error
        if (item.getItemId().equals(null)) {
            auditDao.writeAuditEntry("Attempt to buy non-existing item.");
            throw new VendingMachineNoItemInventoryException("No such item in the Machine, sorry. "
                                                             + "Here is your money back: $"
                                                             + balance);
        }*/

        //only vend if there more than 0 items in stock and user has inserted enough money
        if (item.getItemCount() != 0) {
            //using compareTo() method of Big Decimal, -1 represents "less than" in regular numbers
            if (balance.compareTo(price) != -1) {     //if balance is lower than price
                //decrease the amount of items in the inventory
                item.setItemCount(item.getItemCount() - 1);
                updateInventory(itemId, item);
                change = calculateChange(balance.subtract(price));

                //adding audit for successful purchase
                auditDao.writeAuditEntry("Item " + itemId + "(" + item.getItemName() + ") was successfully purchased.");
            } else {
                auditDao.writeAuditEntry("Insufficient funds to purchase item " + itemId + "(" + item.getItemName() +").");
                throw new VendingMachineInsufficientFundsException("Looks like you haven't inserted enough money to buy "
                                                                    + item.getItemName() + ". "
                                                                    + "Try again!\n"
                                                                    + "Here is your money back: $"
                                                                    + balance);
            }
        } else { //if item count equals 0
            auditDao.writeAuditEntry("Option " + itemId + "(" + item.getItemName() + ")" + " is not in stock!");
            throw new VendingMachineOutOfStockException("Oops, looks like we are out of this item"
                                                        + " (" + item.getItemName() + ")."
                                                        + " Here is your money back: $"
                                                        + balance);
        }

        balance = null;

        return change;
    }

    
    @Override
    //calculates amount of Coins to be given as change to user
    public Change calculateChange(BigDecimal changeToCalculate) {

        BigDecimal totalChange = changeToCalculate;

        Change change = new Change(totalChange);

        //setting coin values
        BigDecimal noChangeLeft = new BigDecimal("00.00"); //variable for comparison
        
        BigDecimal quarter = new BigDecimal("00.25");
        BigDecimal dime = new BigDecimal("00.10");
        BigDecimal nickel = new BigDecimal("00.05");
        BigDecimal penny = new BigDecimal("00.01");
        
        //setting amout of Coins
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;
        //using enums
        Coins coin = null;

        while (changeToCalculate.compareTo(noChangeLeft) == 1) {
            while (changeToCalculate.subtract(quarter).compareTo(noChangeLeft) != -1) {
                coin = QUARTER;
                break;
            }
            while (changeToCalculate.subtract(quarter).compareTo(noChangeLeft) == -1 && changeToCalculate.subtract(dime).compareTo(noChangeLeft) != -1) {
                coin = DIME;
                break;
            }
            while (changeToCalculate.subtract(dime).compareTo(noChangeLeft) == -1 && changeToCalculate.subtract(nickel).compareTo(noChangeLeft) != -1) {
                coin = NICKEL;
                break;
            }
            while (changeToCalculate.subtract(nickel).compareTo(noChangeLeft) == -1 && changeToCalculate.subtract(penny).compareTo(noChangeLeft) != -1) {
                coin = PENNY;
                break;
            }

            switch (coin) {
                case QUARTER:
                    quarters++;
                    changeToCalculate = changeToCalculate.subtract(quarter);
                    break;
                case DIME:
                    dimes++;
                    changeToCalculate = changeToCalculate.subtract(dime);
                    break;
                case NICKEL:
                    nickels++;
                    changeToCalculate = changeToCalculate.subtract(nickel);
                    break;

                case PENNY:
                    pennies++;
                    changeToCalculate = changeToCalculate.subtract(penny);
                    break;
            }
        }
        change.setQuarters(quarters);
        change.setDimes(dimes);
        change.setNickels(nickels);
        change.setPennies(pennies);
        
        return change;
    }

}

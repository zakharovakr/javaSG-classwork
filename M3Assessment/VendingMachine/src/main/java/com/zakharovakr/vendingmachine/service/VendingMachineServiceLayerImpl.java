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

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

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
        return dao.readAll();
    }

    @Override
    public Item getItemById(String itemId) throws VendingMachinePersistenceException {
        return dao.readById(itemId);
    }

    @Override
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
    public Change vend(String itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException, VendingMachineNoItemInventoryException {
        Item item = dao.readById(itemId);
        BigDecimal price = item.getItemPrice();
        Change change;

        /*
        working on no item in the inventory exception

        //converting itemId into an Integer to set boundaries for menu selection options
        int itemIDInt = Integer.parseInt(itemId);

        if(itemIDInt < 1 || itemIDInt > 10) {//hard coded temporarily, look for a better solution if have time
            throw new VendingMachineNoItemInventoryException("No such item in the machine.");
        }*/

        //only vend if there more than 0 items in stock and user has inserted enough money
        if (item.getItemCount() != 0) {
            //using compareTo() method of Big Decimal, -1 represents "less than" in regular numbers
            if (balance.compareTo(price) != -1) {
                //decrease the amount of items in the inventory
                item.setItemCount(item.getItemCount() - 1);
                updateInventory(itemId, item);
                change = calculateChange(balance.subtract(price));
            } else {
                auditDao.writeAuditEntry("Insufficient funds to purchase " + itemId + " (" + item.getItemName() +")");
                throw new VendingMachineInsufficientFundsException("Looks like you haven't inserted enough money to buy " + item.getItemName() + ". " + balance + "$ is not enough. Try again!");
            }
        } else {
            auditDao.writeAuditEntry("Option " + itemId + " (" + item.getItemName() +")" + " is not in stock!");
            throw new VendingMachineOutOfStockException("Oops, looks like we are out of this item" + " (" + item.getItemName() +").");
        }

        balance = null;

        return change;
    }

    @Override
    public Change calculateChange(BigDecimal changeToCalculate) {
        Change change = new Change();
        BigDecimal penny = new BigDecimal(".01");
        BigDecimal nickel = new BigDecimal(".05");
        BigDecimal dime = new BigDecimal(".10");
        BigDecimal quarter = new BigDecimal(".25");

        //using compareTo() method of Big Decimal, -1 represents "less than" in regular numbers
        if (changeToCalculate.compareTo(quarter) != -1) {
            int quarters = changeToCalculate.divide(quarter, RoundingMode.FLOOR).intValue();
            change.setQuarters(quarters);
            changeToCalculate = changeToCalculate.subtract(quarter.multiply(new BigDecimal(quarters)));
        }

        if (changeToCalculate.compareTo(dime) != -1) {
            int dimes = changeToCalculate.divide(dime, RoundingMode.FLOOR).intValue();
            change.setQuarters(dimes);
            changeToCalculate = changeToCalculate.subtract(dime.multiply(new BigDecimal(dimes)));
        }

        if (changeToCalculate.compareTo(nickel) != -1) {
            int nickels = changeToCalculate.divide(nickel, RoundingMode.FLOOR).intValue();
            change.setQuarters(nickels);
            changeToCalculate = changeToCalculate.subtract(nickel.multiply(new BigDecimal(nickels)));
        }

        if (changeToCalculate.compareTo(penny) != -1) {
            int pennies = changeToCalculate.divide(penny, RoundingMode.FLOOR).intValue();
            change.setQuarters(pennies);
            changeToCalculate = changeToCalculate.subtract(penny.multiply(new BigDecimal(pennies)));
        }
        return change;
    }

}

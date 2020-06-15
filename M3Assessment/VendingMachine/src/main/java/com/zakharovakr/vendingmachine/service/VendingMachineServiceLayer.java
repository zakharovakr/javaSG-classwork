/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine.service;

import com.zakharovakr.vendingmachine.dao.VendingMachinePersistenceException;
import com.zakharovakr.vendingmachine.dto.Change;
import com.zakharovakr.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public interface VendingMachineServiceLayer {

    public List<Item> getAvailableItems() throws VendingMachinePersistenceException;

    public Item getItemById(String itemId) throws VendingMachinePersistenceException;

    public void updateInventory(String itemId, Item item) throws VendingMachinePersistenceException;

    public void setBalance(BigDecimal balance);

    public BigDecimal getBalance();

    public Change vend(String itemId) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException, VendingMachineNoItemInventoryException;

    public Change calculateChange(BigDecimal changeToCalculate);
}

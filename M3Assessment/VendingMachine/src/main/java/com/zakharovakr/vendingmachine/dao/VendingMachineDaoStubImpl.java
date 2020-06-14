package com.zakharovakr.vendingmachine.dao;

import com.zakharovakr.vendingmachine.dto.Item;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VendingMachineDaoStubImpl implements VendingMachineDao {

    private Item validItem,
            invalidItem;
    private Map<String, Item> itemList = new HashMap<>();

    public VendingMachineDaoStubImpl() {
        invalidItem = new Item("1");
        invalidItem.setItemName("Coke");
        invalidItem.setItemPrice(new BigDecimal("1.50"));
        invalidItem.setItemCount(0);

        itemList.put(invalidItem.getItemId(), invalidItem);

        validItem = new Item("2");
        validItem.setItemName("Diet Coke");
        validItem.setItemPrice(new BigDecimal("1.50"));
        validItem.setItemCount(5);

        itemList.put(validItem.getItemId(), validItem);

    }

    @Override
    public List<Item> readAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<>(itemList.values());
    }

    @Override
    public Item readById(String itemId) throws VendingMachinePersistenceException {
        if (itemList.containsKey(itemId)) {
            return itemList.get(itemId);
        } else {
            return null;
        }
    }

    @Override
    public void updateInventory(String itemId, Item item) throws VendingMachinePersistenceException {
        if (itemList.containsKey(itemId)) {
            if (!itemList.get(itemId).getItemName().equals(item.getItemName())) {
                itemList.get(itemId).setItemName(item.getItemName());
            }

            if (!itemList.get(itemId).getItemPrice().equals(item.getItemPrice())) {
                itemList.get(itemId).setItemPrice(item.getItemPrice());
            }

            if (itemList.get(itemId).getItemCount() != item.getItemCount()) {
                itemList.get(itemId).setItemCount(item.getItemCount());
            }
        }
    }

}

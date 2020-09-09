package com.zakharovakr.vendingmachine.dao;

import com.zakharovakr.vendingmachine.dto.Item;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

public class VendingMachineDaoFileImpl implements VendingMachineDao {

    private Map<String, Item> items = new HashMap();
    public static final String INVENTORY_FILE = "inventory.txt";
    public static final String DELIMITER = "::";

    public VendingMachineDaoFileImpl() throws VendingMachinePersistenceException {
        loadInventory();
    }

    @Override
    public List<Item> readAllItems() throws VendingMachinePersistenceException {
        return new ArrayList<Item>(items.values());
    }

    @Override
    public Item readById(String itemId) throws VendingMachinePersistenceException {
        if (items.containsKey(itemId)) {
            return items.get(itemId);
        } else {
            return null;
        }
    }

    @Override
    public void updateInventory(String itemId, Item item) throws VendingMachinePersistenceException {
        if (items.containsKey(itemId)) {
            if (!items.get(itemId).getItemName().equals(item.getItemName())) {
                items.get(itemId).setItemName(item.getItemName());
            }

            if (!items.get(itemId).getItemPrice().equals(item.getItemPrice())) {
                items.get(itemId).setItemPrice(item.getItemPrice());
            }

            if (items.get(itemId).getItemCount() != item.getItemCount()) {
                items.get(itemId).setItemCount(item.getItemCount());
            }
            writeInventory();
        }
    }

    private void loadInventory() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(INVENTORY_FILE)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException("-_- Could not load inventory data into memory", e);
        }

        String currentLine;
        String[] currentTokens;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Item currentItem = new Item(currentTokens[0]);
            currentItem.setItemName(currentTokens[1]);
            currentItem.setItemPrice(new BigDecimal(currentTokens[2]));
            currentItem.setItemCount(Integer.parseInt(currentTokens[3]));

            items.put(currentItem.getItemId(), currentItem);
        }
        scanner.close();
    }

    private void writeInventory() throws VendingMachinePersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(INVENTORY_FILE));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException("Could not save inventory data", e);
        }

        List<Item> itemList = this.readAllItems();
        //implementing lambdas and streams 
        
        //Example from the lesson:
        //people.stream()
        //      .forEach((p) -> {
        //          System.out.println(p.getName());
        //          System.out.println(p.getAge());
        //      });
        
        itemList.stream()
                .forEach((item) -> {
                    out.println(item.getItemId() + DELIMITER
                    + item.getItemName() + DELIMITER
                    + item.getItemPrice() + DELIMITER
                    + item.getItemCount());
                });
        
        //old code
        /*for (Item currentItem : itemList) {
            out.println(currentItem.getItemId() + DELIMITER
                    + currentItem.getItemName() + DELIMITER
                    + currentItem.getItemPrice() + DELIMITER
                    + currentItem.getItemCount());*/
            out.flush();
        //}
        out.close();
    }
}
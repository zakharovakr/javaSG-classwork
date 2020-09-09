/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.dto.Product;
import com.zakharovakr.flooringmastery.dto.Tax;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 *
 * @author kristinazakharova
 */
public class OrderDaoFileImpl implements OrderDao {

    private Map<LocalDate, Map> orderList = new HashMap<>();
    private Map<Integer, Order> currentOrders = new HashMap<>();

    public final String ORDER_FILE;
    public static final String DELIMITER = ",";

    public OrderDaoFileImpl() throws FlooringPersistenceException {
        ORDER_FILE = "Orders/Orders_";
    }

    //for testing
    public  OrderDaoFileImpl(String testOrderFile) throws FlooringPersistenceException {
        ORDER_FILE = testOrderFile;
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order newOrder) throws FlooringPersistenceException {
        //checking if a file with orders for that date already exists
        currentOrders.clear();
        if(checkIfOrderForDateExists(orderDate)) {
        loadOrders(orderDate);
    } //else {
//            currentOrders.clear();
            //currentOrders = new HashMap<>();
        //}
        //putting an order into a map
        //currentDate = orderDate;
        currentOrders.put(newOrder.getOrderNumber(), newOrder);
        //putting a map inside a "meta-map"
        orderList.put(orderDate, currentOrders);

        writeOrders(orderDate, currentOrders);
        return newOrder;
    }

    @Override
    public List<Order> readAllOrders(LocalDate orderDate) throws FlooringPersistenceException {
        currentOrders.clear();
        loadOrders(orderDate);
        if (orderList.containsKey(orderDate)) {
            currentOrders = orderList.get(orderDate);
            return new ArrayList<>(currentOrders.values());
        } else {
            //loadOrders(orderDate);

            orderList.put(orderDate, currentOrders);
            return new ArrayList<>(currentOrders.values());
        }
    }

    @Override
    public Order readOrderById(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {

        currentOrders.clear();
        loadOrders(orderDate);
        if (orderList.containsKey(orderDate)) {
            currentOrders = orderList.get(orderDate);
            if (currentOrders.containsKey(orderNumber)) {
                return currentOrders.get(orderNumber);
            }
        } else {
//            loadOrders(orderDate);

            orderList.put(orderDate, currentOrders);
            if (currentOrders.containsKey(orderNumber)) {
                return currentOrders.get(orderNumber);
            }
        }
        return null;
    }

    @Override
    public Order updateOrder(LocalDate orderDate, int orderNumber, Order updatedOrder) throws FlooringPersistenceException {
        currentOrders.clear();
        loadOrders(orderDate);
        if (orderList.containsKey(orderDate)) {
            currentOrders = orderList.get(orderDate);
            if (currentOrders.containsKey(orderNumber)) {
                currentOrders.put(orderNumber, updatedOrder);
            }
        } else {
            //loadOrders(orderDate);
            if (currentOrders.containsKey(orderNumber)) {
                currentOrders.put(orderNumber, updatedOrder);
            }
        }
        orderList.put(orderDate, currentOrders);
        writeOrders(orderDate, currentOrders);
        return updatedOrder;
    }

    @Override
    public Order deleteOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException {
        currentOrders.clear();
        loadOrders(orderDate);
        Order removedOrder = null;

        if (orderList.containsKey(orderDate)) {
            currentOrders = orderList.get(orderDate);
        }
//        } else {
//            //loadOrders(orderDate);
//        }
        if (currentOrders.containsKey(orderNumber)) {
            removedOrder = currentOrders.remove(orderNumber);

        }

        orderList.put(orderDate, currentOrders);
        //re-write to file again
        writeOrders(orderDate, currentOrders);

        //if file is empty after deleting an order, delete the file too
        deleteEmptyOrderFile(orderDate);

        return removedOrder;
    }

    @Override
    public List<Order> getAllOrders() throws FlooringPersistenceException {
        List<Order> allCurrentOrders = new ArrayList<>();

        allCurrentOrders = loadAllOrderFiles();

        if (allCurrentOrders.isEmpty()) {
            throw new FlooringPersistenceException("No orders found");
        } else {
            return allCurrentOrders;
        }
    }

    @Override
    //checking if file with Orders for a certain date exists or not
    public boolean checkIfOrderForDateExists(LocalDate orderDate) {
        String dateAsString = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fullFileName = ORDER_FILE + dateAsString + ".txt";
        File orderFile = new File(fullFileName);

        if(orderFile.exists()) {
            return true;
        }
        return false;
    }

    private void deleteEmptyOrderFile(LocalDate orderDate) {
        String dateAsString = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fullFileName = ORDER_FILE + dateAsString + ".txt";
        File orderFile = new File(fullFileName);

        //System.out.println(orderFile.length());

        if (orderFile.length() == 130)  { //if only the header is left
            orderFile.delete(); //file gets deleted
        }
    }

    //methods to swap commas in case customer name contains one (to avoid program crashing, as it will take comma as delimiter)
    private String removeCommas(String oldCustomerName) {
        String newCustomerName;
        newCustomerName = oldCustomerName.replace(",", "#*##");
        return newCustomerName;
    }

    private String addCommas(String formattedCustomerName) {
        String customerName;
        customerName = formattedCustomerName.replace("#*##",",");
        return customerName;
    }

    private Order unmarshallOrder (String orderAsText) {
        String[] orderTokens = orderAsText.split(DELIMITER);

        int orderNumber = Integer.parseInt(orderTokens[0]);
        String customerName = addCommas(orderTokens[1]);
        String state = orderTokens[2];
        BigDecimal taxRate = new BigDecimal(orderTokens[3]);
        String product = orderTokens[4];
        BigDecimal area = new BigDecimal(orderTokens[5]);
        BigDecimal costPerSquareFoot = new BigDecimal(orderTokens[6]);
        BigDecimal laborCostPerSquareFoot = new BigDecimal(orderTokens[7]);
        BigDecimal materialCost = new BigDecimal(orderTokens[8]);
        BigDecimal laborCost = new BigDecimal(orderTokens[9]);
        BigDecimal tax = new BigDecimal(orderTokens[10]);
        BigDecimal total = new BigDecimal(orderTokens[11]);

        Order orderFromFile = new Order(orderNumber);
        orderFromFile.setCustomerName(customerName);
        orderFromFile.setState(new Tax(state));
        orderFromFile.getState().setTaxRate(taxRate);
        orderFromFile.setProduct(new Product(product));
        orderFromFile.setArea(area);
        orderFromFile.getProduct().setCostPerSquareFoot(costPerSquareFoot);
        orderFromFile.getProduct().setLaborCostPerSquareFoot(laborCostPerSquareFoot);
        orderFromFile.setMaterialCost(materialCost);
        orderFromFile.setLaborCost(laborCost);
        orderFromFile.setTax(tax);
        orderFromFile.setTotal(total);

        return orderFromFile;
   }

    private void loadOrders(LocalDate orderDate) throws FlooringPersistenceException {
//        currentOrders.clear();
//        currentOrders = new HashMap<>();

        String dateAsString = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fullFileName = ORDER_FILE + dateAsString + ".txt";
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(new BufferedReader(new FileReader(fullFileName)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException("No orders for this date found.", e);
        }
        // currentLine holds the most recent line read from the file
        String currentLine;

        Order currentOrder;
        // Go through ORDER_FILE line by line, decoding each line into an Order object
        // Process while we have more lines in the file

        //skipping file header
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();

            currentOrder = unmarshallOrder(currentLine);

            currentOrders.put(currentOrder.getOrderNumber(), currentOrder);
        }
        // close scanner
        scanner.close();

    }

    private String marshallOrder(Order anOrder) {
        String customerNameNoCommas = removeCommas(anOrder.getCustomerName());

        String orderAsText = Integer.toString(anOrder.getOrderNumber()) + DELIMITER;
        orderAsText += customerNameNoCommas + DELIMITER;
        orderAsText += anOrder.getState().getStateAbbreviation() + DELIMITER;
        orderAsText += anOrder.getState().getTaxRate().toString() + DELIMITER;
        orderAsText += anOrder.getProduct().getProductType() + DELIMITER;
        orderAsText += anOrder.getArea().toString() + DELIMITER;
        orderAsText += anOrder.getProduct().getCostPerSquareFoot().toString() + DELIMITER;
        orderAsText += anOrder.getProduct().getLaborCostPerSquareFoot().toString() + DELIMITER;
        orderAsText += anOrder.getMaterialCost().toString() + DELIMITER;
        orderAsText += anOrder.getLaborCost().toString() + DELIMITER;
        orderAsText += anOrder.getTax().toString() + DELIMITER;
        orderAsText += anOrder.getTotal().toString();

        return orderAsText;
    }

    private void writeOrders(LocalDate orderDate, Map<Integer, Order> currentOrders) throws FlooringPersistenceException {
        String dateAsString = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        String fullFileName = ORDER_FILE + dateAsString + ".txt";
        PrintWriter out;

        try {
            //File f = new File(fullFileName);
            out = new PrintWriter(new FileWriter(fullFileName), true);
        } catch (IOException e) {
            throw new FlooringPersistenceException("Could not save order data.", e);
        }

        //adding a file header
        String heading = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total";
        out.println(heading);

        String orderAsText;
        List<Order> myOrderList = new ArrayList<>(currentOrders.values());

        for (Order currentOrder : myOrderList) {
            orderAsText = marshallOrder(currentOrder);

            out.println(orderAsText);

            out.flush();
        }
        out.close();
    }

    private List<Order> loadAllOrderFiles() throws FlooringPersistenceException {
        //first make an empty arraylist to hold all Orders
        List<Order> allExistingOrdersList = new ArrayList<>();
        //create a list of all files in the folder
        File ordersFolder = new File("Orders/");
        File[] listOfOrderFiles = ordersFolder.listFiles();

        //for every file in the folder, unmarshall and add to the arraylist
        for (File file : listOfOrderFiles) {
            if (file.isFile()) {
                Scanner scanner;

                try {
                    // Create Scanner for reading the file
                    scanner = new Scanner(new BufferedReader(new FileReader(file)));
                } catch (FileNotFoundException e) {
                    throw new FlooringPersistenceException("No orders for this date found.", e);
                }
                // currentLine holds the most recent line read from the file
                String currentLine;

                Order currentOrder;
                // Go through ORDER_FILE line by line, decoding each line into an Order object
                // Process while we have more lines in the file

                //skipping file header
                scanner.nextLine();

                while (scanner.hasNextLine()) {
                    currentLine = scanner.nextLine();

                    currentOrder = unmarshallOrder(currentLine);

                    //adding date from file name to Order objects
                    String fileName = file.getName();
                    String dateFromFileNameStr = fileName.substring(7, 15);
                    LocalDate dateFromFileName = LocalDate.parse(dateFromFileNameStr, DateTimeFormatter.ofPattern("MMddyyyy"));
                    currentOrder.setDate(dateFromFileName);

                    allExistingOrdersList.add(currentOrder);
                }
                // close scanner
                scanner.close();
            }
        }
        return allExistingOrdersList;
    }
}

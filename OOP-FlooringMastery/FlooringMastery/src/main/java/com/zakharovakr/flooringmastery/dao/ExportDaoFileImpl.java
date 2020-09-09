/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Order;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class ExportDaoFileImpl implements ExportDao{

    private List<Order> allData = new ArrayList<Order>();

    public static String BACKUP_FILE = "Backup/DataExport.txt";
    public static final String DELIMITER = ",";

    public ExportDaoFileImpl() throws FlooringPersistenceException {
    }

    public ExportDaoFileImpl(String testFile) throws FlooringPersistenceException {
        BACKUP_FILE = testFile;
    }

    @Override
    public void saveOrdersToFile(List<Order> allOrders) throws FlooringPersistenceException {
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(BACKUP_FILE));
        } catch (IOException e) {
            throw new FlooringPersistenceException(
                    "Could not save item data.", e);
        }

        String heading = "OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCostPerSquareFoot,MaterialCost,LaborCost,Tax,Total,OrderDate";
        out.println(heading);

        String orderAsText;

        for (Order currentOrder : allOrders) {

            orderAsText = marshallOrder(currentOrder);

            out.println(orderAsText);

            out.flush();
        }

        out.close();

    }

    private String removeCommas(String oldCustomerName) {
        String newCustomerName;
        newCustomerName = oldCustomerName.replace(",", "#*##");
        return newCustomerName;
    }

    private String marshallOrder(Order anOrder) {
        String customerNameNoCommas = removeCommas(anOrder.getCustomerName());
        //turning date into a string
        LocalDate orderDate = anOrder.getDate();
        String dateAsString = orderDate.format(DateTimeFormatter.ofPattern("MM-dd-yyyy"));

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
        orderAsText += anOrder.getTotal().toString() + DELIMITER;
        //adding order date here
        orderAsText += dateAsString;

        return orderAsText;
    }

}

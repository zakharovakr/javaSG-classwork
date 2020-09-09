/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.view;

import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.dto.Product;
import com.zakharovakr.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class FlooringView {

    private UserIO io;

    //constructor
    public FlooringView(UserIO io) {
        this.io = io;
    }

    //display methods
    public void displayMenu() {
        io.print("**************************************\n");
        io.print("* <<Flooring Program>>               *\n");
        io.print("* Please choose an option:           *\n");
        io.print("*  1. Display Orders                 *\n");
        io.print("*  2. Add an Order                   *\n");
        io.print("*  3. Edit an Order                  *\n");
        io.print("*  4. Remove an Order                *\n");
        io.print("*  5. Export All Data                *\n");
        io.print("*  6: Quit the program               *\n");
        io.print("**************************************\n");
    }

    public void displayDisplayOrdersBanner() {
        io.print("=== DISPLAY ORDERS === \n");
    }

    public void displayAddOrderBanner() {
        io.print("=== ADD ORDER === \n");
    }

    public void displayEditOrderBanner() {
        io.print("=== EDIT ORDER === \n");
    }

    public void displayRemoveOrderBanner() {
        io.print("=== REMOVE ORDER === \n");
    }

    public void displayExportAllDataBanner() {
        io.print("=== EXPORT ALL DATA === \n");
    }

    public boolean displayOrderToAdd(Order currentOrder) {
        io.print("***Please confirm your Order: *** \n \n");
        io.print("** Customer name: " + currentOrder.getCustomerName() + "\n");
        io.print("** State: " + currentOrder.getState().getStateAbbreviation() + ", " + currentOrder.getState().getStateName() + "\n");
        io.print("** TaxRate: " + currentOrder.getState().getTaxRate() + "\n");
        io.print("** Product: " + currentOrder.getProduct().getProductType() + "\n");
        io.print("** Product Cost Per Sq Foot: " + currentOrder.getProduct().getCostPerSquareFoot() + "\n");
        io.print("** Labor Cost Per Sq Foot: " + currentOrder.getProduct().getLaborCostPerSquareFoot() + "\n");
        io.print("** Area: " + currentOrder.getArea() + " sq. ft. \n");
        io.print("** Material Cost: $" + currentOrder.getMaterialCost() + "\n");
        io.print("** Labor Cost: $" + currentOrder.getLaborCost() + "\n");
        io.print("** Total Tax: $" + currentOrder.getTax() + "\n");
        io.print("** Total: $" + currentOrder.getTotal() + "\n");
        io.print("------------------------- \n");

        boolean keepAsking = true;
         while (keepAsking) {
             String userAnswer = io.readString("Is the Order correct? (type y/n)");

             if (userAnswer.equals("y")) {
                 io.print("Thank you for confirming. Your Order is added to the system. \n");
                 return true;
             } else if (userAnswer.equals("n")) {
                 io.print("Order information was discarded. Try again! \n");
                 return false;
             } else {
                 io.print("Please type y or n. \n");
             }
         }

        return false;
    }

    public boolean displayOrderToEdit(Order updatedOrder) {
        io.print("***Please confirm the changes: *** \n \n");
        io.print("** Customer name: " + updatedOrder.getCustomerName() + "\n");
        io.print("** State: " + updatedOrder.getState().getStateAbbreviation() + "\n");
        io.print("** TaxRate: " + updatedOrder.getState().getTaxRate() + "\n");
        io.print("** Product: " + updatedOrder.getProduct().getProductType() + "\n");
        io.print("** Product Cost Per Sq Foot: " + updatedOrder.getProduct().getCostPerSquareFoot() + "\n");
        io.print("** Labor Cost Per Sq Foot: " + updatedOrder.getProduct().getLaborCostPerSquareFoot() + "\n");
        io.print("** Area: " + updatedOrder.getArea() + " sq. ft. \n");
        io.print("** Material Cost: $" + updatedOrder.getMaterialCost() + "\n");
        io.print("** Labor Cost: $" + updatedOrder.getLaborCost() + "\n");
        io.print("** Total Tax: $" + updatedOrder.getTax() + "\n");
        io.print("** Total: $" + updatedOrder.getTotal() + "\n");
        io.print("------------------------- \n");

        boolean keepAsking = true;
        while (keepAsking) {
            String userAnswer = io.readString("Are you sure you want to save the changes? (type y/n)");

            if (userAnswer.equals("y")) {
                io.print("Thank you for confirming. Your Order has been edited. \n");
                return true;
            } else if (userAnswer.equals("n")) {
                io.print("New order information was discarded. Try again! \n");
                return false;
            } else {
                io.print("Please type y or n. \n");
            }
        }
        return false;
    }

    public boolean displayOrderToRemove(Order currentOrder) {
        io.print("***Please confirm if this is the order you want to remove: *** \n \n");
        io.print("** Customer name: " + currentOrder.getCustomerName() + "\n");
        io.print("** State: " + currentOrder.getState().getStateAbbreviation() + "\n");
        io.print("** TaxRate: " + currentOrder.getState().getTaxRate() + "\n");
        io.print("** Product: " + currentOrder.getProduct().getProductType() + "\n");
        io.print("** Product Cost Per Sq Foot: " + currentOrder.getProduct().getCostPerSquareFoot() + "\n");
        io.print("** Labor Cost Per Sq Foot: " + currentOrder.getProduct().getLaborCostPerSquareFoot() + "\n");
        io.print("** Area: " + currentOrder.getArea() + " sq. ft. \n");
        io.print("** Material Cost: $" + currentOrder.getMaterialCost() + "\n");
        io.print("** Labor Cost: $" + currentOrder.getLaborCost() + "\n");
        io.print("** Total Tax: $" + currentOrder.getTax() + "\n");
        io.print("** Total: $" + currentOrder.getTotal() + "\n");
        io.print("------------------------- \n");

        boolean keepAsking = true;
        while (keepAsking) {
            String userAnswer = io.readString("Are you sure you want to remove this order? (type y/n)");

            if (userAnswer.equals("y")) {
                io.print("Thank you for confirming. Your Order is removed from the system. \n");
                return true;
            } else if (userAnswer.equals("n")) {
                io.print("Ok, we still have your order. \n");
                return false;
            } else {
                io.print("Please type y or n. \n");
            }
        }
        return false;
    }

    public void displaySuccessBanner(String message) {
        io.print("=== SUCCESS === \n");
        io.print(message);
    }

    public void displayErrorMessage(String message) {
        io.print("=== ERROR === \n");
        io.print(message + "\n");
    }

    public void displayGoodByeMessage() {
        io.print("=== THANK YOU! BYE! === \n");
    }

    public void displayUnknownCommand() {
        io.print("Unknown Command. \n");
    }

    public void displayOrders(List<Order> orderList, LocalDate orderDate) {
        io.print("Displaying all orders for " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n");

        for (Order currentOrder : orderList) {
            io.print("** Order number: " + currentOrder.getOrderNumber() + "\n");
            io.print("** Customer name: " + currentOrder.getCustomerName() + "\n");
            io.print("** State: " + currentOrder.getState().getStateAbbreviation() + "\n");
            io.print("** TaxRate: " + currentOrder.getState().getTaxRate() + "\n");
            io.print("** Product: " + currentOrder.getProduct().getProductType() + "\n");
            io.print("** Product Cost Per Sq Foot: " + currentOrder.getProduct().getCostPerSquareFoot() + "\n");
            io.print("** Labor Cost Per Sq Foot: " + currentOrder.getProduct().getLaborCostPerSquareFoot() + "\n");
            io.print("** Area: " + currentOrder.getArea() + " sq. ft. \n");
            io.print("** Material Cost: $" + currentOrder.getMaterialCost() + "\n");
            io.print("** Labor Cost: $" + currentOrder.getLaborCost() + "\n");
            io.print("** Total Tax: $" + currentOrder.getTax() + "\n");
            io.print("** Total: $" + currentOrder.getTotal() + "\n");
            io.print("------------------------- \n");
        }
    }

    //methods to get info from user
    public int getMenuChoice() {
        return io.readInt("Please enter your menu selection: ", 1, 6);
    }

    public LocalDate getOrderDate() {
        return io.readLocalDate("Please enter a date, in the format MMDDYYYY: ");
    }

    public Order getOrderInformation() {

        String name = io.readString("Please enter your name: ");
        String stateAbb = io.readString("Please type your state abbreviation: ").toUpperCase();
        String product = io.readString("Please type in the product you wish to order: ");
        BigDecimal area = io.readBigDecimal("Please type in the square feet of the area you wish to floor: ", BigDecimal.valueOf(100));
        if (!product.isEmpty()) {
            product = firstLetterCaps(product);
        }
        return new Order(name, stateAbb, product, area);
    }

    public int getOrderNumber() {
        return io.readInt("Please enter the order number: ");
    }

    public void displayTaxes(List<Tax> taxes) {
        io.print("*** Here are the States we are covering: *** \n");
        io.print("\n");
        io.print("State, tax rate: \n");
        for (Tax currentTax : taxes) {
            io.print(currentTax.getStateAbbreviation() + ", " + currentTax.getTaxRate() +"\n");
        }
    }

    public void displayProducts(List<Product> products) {
        io.print("*** Here are the products we offer: *** \n");
        io.print("\n");
        io.print("Product type, cost per square foot, labor cost per square foot: \n");
        for (Product currentProduct : products) {
            io.print(currentProduct.getProductType() + ", "
                    + currentProduct.getCostPerSquareFoot() + ", "
                    + currentProduct.getLaborCostPerSquareFoot() + "\n");
        }
        io.print("\n");
    }

    public Order getUpdatedInformation(Order originalOrder) {
        String newName = io.readString("Please enter new name: ");
        String newStateAbb = io.readString("Please type new state abbreviation: ").toUpperCase();
        String newProduct = io.readString("Please type in the new product you wish to order: ");
        BigDecimal newArea = new BigDecimal(originalOrder.getArea().toString());
        String areaAsString = io.readString("Please type in the new square feet of the area you wish to floor (Minimum order size is 100 sq ft): ");
        if (areaAsString.isBlank() || areaAsString.matches("/^[0-9.]+$/")) {
            newArea.equals(originalOrder.getArea());
        } else {
            newArea = new BigDecimal(areaAsString);
        }

        if (!newProduct.isEmpty()) {
            newProduct = firstLetterCaps(newProduct);
        }

        Order updatedOrder = new Order(newName, newStateAbb, newProduct, newArea);
        updatedOrder.setDate(originalOrder.getDate());
        return updatedOrder;
    }

    //capitalizing first letter, leaving the rest in lower case
    public String firstLetterCaps ( String data ) {
        String firstLetter = data.substring(0,1).toUpperCase();
        String restLetters = data.substring(1).toLowerCase();
        return firstLetter + restLetters;
    }
}

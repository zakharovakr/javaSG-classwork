/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.controller;

import com.zakharovakr.flooringmastery.dao.FlooringPersistenceException;
import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.service.*;
import com.zakharovakr.flooringmastery.view.FlooringView;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public class FlooringController {
    private FlooringView view;
    private FlooringServiceLayer service;

    //constructor
    public FlooringController(FlooringView view, FlooringServiceLayer service) {
        this.view = view;
        this.service = service;
    }

    /**
     * Runs the program. Uses a loop to keep running until the user specifies
     * not to.
     */
    public void run() {
        boolean quit = false;
        int menuSelection;

        while (!quit) {
            try {
                displayMenu();
                menuSelection = getMenuChoice();

                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        exportAllData();
                        break;
                    case 6:
                        quit = true;
                        exit();
                        break;
                    default:
                        unknownCommand();
                }
            } catch (FlooringPersistenceException
                    | OrderValidationException
                    | NoTaxesFoundException
                    | NoProductsFoundException
                    | NoOrdersFoundException e) {
                view.displayErrorMessage(e.getMessage());
            }
        }
    }

    /**
     * Displays the menu to the user.
     */
    private void displayMenu() {
        view.displayMenu();
    }

    /**
     * Prompts the user for their menu choice and passes it back to the run()
     * loop.
     *
     * @return The value the user selected for the menu.
     */
    private int getMenuChoice() {
        return view.getMenuChoice();
    }

    /**
     * Displays all orders for a particular date.
     *
     * @throws FlooringPersistenceException - If there are no orders for that
     * date, then an Exception is thrown.
     */
    private void displayOrders() throws FlooringPersistenceException, NoOrdersFoundException {
        view.displayDisplayOrdersBanner();
        LocalDate orderDate = view.getOrderDate();
        List<Order> orderList = service.displayOrders(orderDate);
        view.displayOrders(orderList, orderDate);
    }

    /**
     * Prompts the user to provide information to create a new Order which is
     * then passed to the Service layer for validation and submission, pending
     * user approval.
     *
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     * @throws OrderValidationException - If the provided information is
     * invalid, this Exception is thrown.
     */
    private void addOrder() throws FlooringPersistenceException, OrderValidationException, NoOrdersFoundException, NoProductsFoundException, NoTaxesFoundException {
        //banner
        view.displayAddOrderBanner();
        //displaying tax info
        List taxList = service.getTaxes();
        view.displayTaxes(taxList);
        //displaying product info
        List productList = service.getProducts();
        view.displayProducts(productList);
        //getting order info from user
        LocalDate orderDate = view.getOrderDate();
        Order newOrder = view.getOrderInformation();
        //validating info
        service.validateDate(orderDate);
        newOrder.setDate(orderDate);
        service.validateData(newOrder);
        service.validateName(newOrder);
        service.validateProduct(newOrder);
        service.validateState(newOrder);
        service.calculateCostAndTax(newOrder);
        boolean userAnswer = view.displayOrderToAdd(newOrder);

        //adding order to file
        if (userAnswer == true) {
            service.addOrder(newOrder);
        }

    }

    /**
     * Prompts the user to provide an order date and order number. If an Order
     * matches those parameters, the user is then prompted to provide updated
     * information. That information is then sent to the Service layer for
     * validation and submission.
     *
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     * @throws OrderValidationException - If the provided information is
     * invalid, this Exception is thrown.
     * @throws NoOrdersFoundException - If there is no order number for that
     * date found, then this Exception is thrown.
     */
    private void editOrder() throws FlooringPersistenceException, OrderValidationException, NoOrdersFoundException {
        view.displayEditOrderBanner();
        LocalDate orderDate = view.getOrderDate();
        int orderNumber = view.getOrderNumber();
        Order originalOrder = service.getOrder(orderDate, orderNumber);
        Order updatedOrder = view.getUpdatedInformation(originalOrder);
        updatedOrder.setOrderNumber(originalOrder.getOrderNumber());
        updatedOrder.setDate(orderDate);

        service.validateUpdatedOrderInfo(originalOrder, updatedOrder);
        service.validateName(updatedOrder);
        service.validateState(updatedOrder);
        service.validateProduct(updatedOrder);

        service.calculateCostAndTax(updatedOrder);
        boolean userAnswer = view.displayOrderToEdit(updatedOrder);

        if (userAnswer == true) {
            service.editOrder(orderDate, orderNumber, updatedOrder);
            //updatedOrder.setDate(orderDate);
        }
    }

    /**
     * Prompts the user to provide an order date and order number. If an Order
     * matches those parameters, the user is then provided with an order
     * summary. They are then prompted to if they are sure they want to delete
     * the order. If they do, the order is deleted.
     *
     * @throws - FlooringPersistenceException - If the file cannot be
     * written/read from, then we have an Exception thrown.
     * @throws - NoOrdersFoundException - If there is no order number for that
     * date found, then this Exception is thrown.
     */
    private void removeOrder() throws FlooringPersistenceException, NoOrdersFoundException {
        view.displayRemoveOrderBanner();
        LocalDate orderDate = view.getOrderDate();
        int orderNum = view.getOrderNumber();
        Order orderToRemove = service.getOrder(orderDate, orderNum);

        boolean userAnswer = view.displayOrderToRemove(orderToRemove);
        if (userAnswer == true) {
            service.removeOrder(orderDate, orderNum);
        }

    }

    /**
     * Writes all the orders into a separate file in the Backup folder.
     *
     * @throws - FlooringPersistenceException - If the file cannot be
     * written/read from, then we have an Exception thrown.
     */
    private void exportAllData() throws FlooringPersistenceException {
        view.displayExportAllDataBanner();
        service.exportAllData();
        view.displaySuccessBanner("The data has been successfully exported. \n");
    }

    /**
     * Terminates the program.
     */
    private void exit() {
        view.displayGoodByeMessage();
    }

    /**
     * Displays a message when an unknown command happens.
     */
    private void unknownCommand() {
        view.displayUnknownCommand();
    }

}

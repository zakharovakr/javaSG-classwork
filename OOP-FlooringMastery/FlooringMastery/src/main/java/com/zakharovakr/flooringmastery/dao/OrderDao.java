/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Order;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public interface OrderDao {

    /**
     * Adds a new Order to the currentOrders Map.
     *
     * @param orderDate Takes in the currentDate from the Service layer.
     * @param newOrder Takes in a new Order object from the Service layer.
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     * @return
     */
    public Order createOrder(LocalDate orderDate, Order newOrder) throws FlooringPersistenceException;

    /**
     * Sends a List of all Order objects from a specific date.
     *
     * @param orderDate The date to search for.
     * @return All the Orders associated with the order date.
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     */
    public List<Order> readAllOrders(LocalDate orderDate) throws FlooringPersistenceException;

    /**
     * Returns a singular Order associated with a date and order number.
     *
     * @param orderDate The date to search for.
     * @param orderNumber The order number to look for.
     * @return An Order object if it exists.
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     */
    public Order readOrderById(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException;

    /**
     * Updates an Order associated with a date and order number.
     *
     * @param orderDate The date to look for.
     * @param orderNumber The order number to look for.
     * @param updatedOrder The updated order to place.
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     * @return
     */
    public Order updateOrder(LocalDate orderDate, int orderNumber, Order updatedOrder) throws FlooringPersistenceException;

    /**
     * ** Deletes an Order associated with a date and order number.
     *
     * @param orderDate The date to search for.
     * @param orderNumber The order number to look for.
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     * @return
     */
    public Order deleteOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException;

    /**
     *
     *
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     */
    public List getAllOrders() throws FlooringPersistenceException;

    //checking if file with Orders for a certain date exists or not
    boolean checkIfOrderForDateExists(LocalDate orderDate);
}

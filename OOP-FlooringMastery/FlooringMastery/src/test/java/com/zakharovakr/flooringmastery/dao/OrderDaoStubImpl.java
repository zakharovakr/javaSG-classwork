package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.dto.Product;
import com.zakharovakr.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OrderDaoStubImpl implements OrderDao {

    public Order onlyOrder;

    //constructors
    public OrderDaoStubImpl() {
        onlyOrder = new Order(1);
        onlyOrder.setCustomerName("Kristina Zakharova");
        onlyOrder.setDate(LocalDate.parse("2021-01-01"));
        onlyOrder.setProduct(new Product("Wood"));
        onlyOrder.getProduct().setCostPerSquareFoot(new BigDecimal("5.15"));
        onlyOrder.getProduct().setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        onlyOrder.setState(new Tax("CA"));
        onlyOrder.getState().setStateName("California");
        onlyOrder.getState().setTaxRate(new BigDecimal("25"));
        onlyOrder.setArea(new BigDecimal("100"));
        onlyOrder.setMaterialCost(new BigDecimal("515"));
        onlyOrder.setLaborCost(new BigDecimal("475"));
        onlyOrder.setTax(new BigDecimal("247.50"));
        onlyOrder.setTotal(new BigDecimal("1237.50"));
    }

    public OrderDaoStubImpl(Order testOrder) {
        this.onlyOrder = testOrder;
    }

    @Override
    public Order createOrder(LocalDate orderDate, Order newOrder) {
        if (orderDate.equals(onlyOrder.getDate())) {
            return onlyOrder;
        }
        return null;
    }

    @Override
    public List<Order> readAllOrders(LocalDate orderDate) {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public Order readOrderById(LocalDate orderDate, int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public Order updateOrder(LocalDate orderDate, int orderNumber, Order updatedOrder) {
        onlyOrder.setDate(orderDate);
        onlyOrder.setOrderNumber(orderNumber);
        onlyOrder = updatedOrder;
        return onlyOrder;
    }

    @Override
    public Order deleteOrder(LocalDate orderDate, int orderNumber) {
        if (orderNumber == onlyOrder.getOrderNumber()) {
            return onlyOrder;
        } else {
            return null;
        }
    }

    @Override
    public List getAllOrders() {
        List<Order> allOrderList = new ArrayList<>();
        allOrderList.add(onlyOrder);
        return allOrderList;
    }

    @Override
    public boolean checkIfOrderForDateExists(LocalDate orderDate) {
        return false;
    }
}

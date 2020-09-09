/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.service;

import com.zakharovakr.flooringmastery.dao.*;
import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.dto.Product;
import com.zakharovakr.flooringmastery.dto.Tax;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.OptionalInt;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kristinazakharova
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer {

    private TaxDao taxDao;
    private ProductDao productDao;
    private OrderDao orderDao;
    private ExportDao exportDao;
    private FlooringAuditDao auditDao;

    //constructor
    public FlooringServiceLayerImpl(TaxDao taxDao, ProductDao productDao, OrderDao orderDao, ExportDao exportDao, FlooringAuditDao auditDao) {
        this.taxDao = taxDao;
        this.productDao = productDao;
        this.orderDao = orderDao;
        this.exportDao = exportDao;
        this.auditDao = auditDao;
    }

    @Override
    public List<Tax> getTaxes() throws NoTaxesFoundException {
        List<Tax> taxes = taxDao.readAll();
        if (taxes == null || taxes.isEmpty()) {
            throw new NoTaxesFoundException("No tax information found.");
        }
        return taxes;
    }

    @Override
    public List<Product> getProducts() throws NoProductsFoundException {
        List<Product> products = productDao.readAll();
        if (products == null || products.isEmpty()) {
            throw new NoProductsFoundException("No products found.");
        }
        return products;
    }

    @Override
    public List<Order> displayOrders(LocalDate orderDate) throws NoOrdersFoundException, FlooringPersistenceException {
        List<Order> orders = orderDao.readAllOrders(orderDate);
        if (orders == null || orders.isEmpty()) {
            throw new NoOrdersFoundException("No orders found.");
        }
        return orders;
    }

    @Override
    public Order getOrder(LocalDate orderDate, int orderNumber) throws FlooringPersistenceException, NoOrdersFoundException {
        Order order = orderDao.readOrderById(orderDate, orderNumber);

        if (order == null) {
            throw new NoOrdersFoundException("No such order number.");
        }
        return order;
    }

    @Override
    public void addOrder(Order newOrder) throws FlooringPersistenceException, OrderValidationException {
        //checking if there are existing orders for a particular date
        //if no, assigning order number to 1
        //if yes, assigning order number to the number of orders + 1
        int orderNum;
        int maxNum;

        LocalDate orderDate = newOrder.getDate();
//
//        boolean orderExists = orderDao.checkIfOrderForDateExists(orderDate);
//
//        if (!orderExists) {
//            orderNum = 1;
//        } else {
            List<Order> existingOrders = orderDao.getAllOrders();
            if (existingOrders.isEmpty()) {
                orderNum = 1;
            } else {

            OptionalInt maxNumOptInt = existingOrders.stream()
                    .mapToInt((o) -> o.getOrderNumber())
                    .max();
            maxNum = maxNumOptInt.getAsInt();
            orderNum = maxNum + 1;
        }
        newOrder.setOrderNumber(orderNum);
        //calling Order Dao to create an Order
        orderDao.createOrder(orderDate, newOrder);
    }

    @Override
    public void editOrder(LocalDate orderDate, int orderNum, Order updatedOrder) throws FlooringPersistenceException, OrderValidationException, NoOrdersFoundException {
        Order originalOrder = getOrder(orderDate, orderNum);

        //updatedOrder.setOrderNumber(orderNum);

        orderDao.updateOrder(orderDate, orderNum, updatedOrder);
    }

    @Override
    public Order removeOrder(LocalDate date, int orderNum) throws FlooringPersistenceException {
        orderDao.deleteOrder(date, orderNum);
        return null;
    }

    @Override
    public void exportAllData() throws FlooringPersistenceException {
        List<Order> allCurrentOrders = orderDao.getAllOrders();
        exportDao.saveOrdersToFile(allCurrentOrders);
    }

    @Override
    public Order calculateCostAndTax(Order newOrder) {
        List<Product> productList = productDao.readAll();
        List<Tax> taxList = taxDao.readAll();

        //setting product values
        for (Product currentProduct : productList) {
            if (newOrder.getProduct().getProductType().equals(currentProduct.getProductType())) {
                newOrder.getProduct().setCostPerSquareFoot(currentProduct.getCostPerSquareFoot());
                newOrder.getProduct().setLaborCostPerSquareFoot(currentProduct.getLaborCostPerSquareFoot());
            }
        }

        //setting tax values
        for (Tax currentTax : taxList) {
            if (newOrder.getState().getStateAbbreviation().equals(currentTax.getStateAbbreviation())) {
                newOrder.getState().setStateName(currentTax.getStateName());
                newOrder.getState().setTaxRate(currentTax.getTaxRate());
            }
        }

        BigDecimal area = newOrder.getArea().setScale(2, RoundingMode.HALF_UP);
        BigDecimal cpsf = newOrder.getProduct().getCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP);
        BigDecimal lcpsf = newOrder.getProduct().getLaborCostPerSquareFoot().setScale(2, RoundingMode.HALF_UP);

        BigDecimal materialCost = area.multiply(cpsf).setScale(2, RoundingMode.HALF_UP);
        BigDecimal laborCost = area.multiply(lcpsf).setScale(2, RoundingMode.HALF_UP);

        newOrder.setMaterialCost(materialCost);
        newOrder.setLaborCost(laborCost);

        BigDecimal subTotal = materialCost.add(laborCost);
        BigDecimal taxPercent = newOrder.getState().getTaxRate().divide(new BigDecimal("100"), 4, RoundingMode.HALF_UP);
        BigDecimal totalTax = taxPercent.multiply(subTotal).setScale(2, RoundingMode.HALF_UP);

        newOrder.setTax(totalTax);

        BigDecimal total = materialCost.add(laborCost.add(totalTax).setScale(2, RoundingMode.HALF_UP));

        newOrder.setTotal(total);

        return newOrder;
    }

    @Override
    public void validateName(Order currentOrder) throws OrderValidationException{
        String customerName = currentOrder.getCustomerName();

        Pattern pattern = Pattern.compile("[A-Za-z0-9 _.,]*"); // regex to allow only alphanumeric, comma, period and space
        Matcher matcher = pattern.matcher(customerName);

        boolean isValid = matcher.matches();
        if (!isValid) {
            throw new OrderValidationException("ERROR: Name is only allowed to contain [a-z][0-9] as well as periods and comma characters.");
        }
    }

    @Override
    public void validateData(Order newOrder) throws OrderValidationException {
        if (newOrder.getCustomerName() == null
                || newOrder.getCustomerName().trim().length() == 0
                || newOrder.getProduct() == null
                || newOrder.getProduct().toString().trim().length() == 0
                || newOrder.getArea() == null
                || newOrder.getArea().equals(new BigDecimal("0.00"))
                || newOrder.getState() == null
                || newOrder.getState().toString().trim().length() == 0) {
            throw new OrderValidationException("ERROR: All fields [Name, Product Type, Area and State Name] are required.");
        }
    }

    @Override
    public void validateDate(LocalDate orderDate) throws OrderValidationException {
        LocalDate today = LocalDate.now();
        if (orderDate.isBefore(today)) {
            throw new OrderValidationException("ERROR: Invalid date. Order date must be in the future.");
        }
    }

    @Override
    public void validateProduct(Order currentOrder) throws OrderValidationException {
        String productName = currentOrder.getProduct().getProductType();
        if (productDao.readById(productName) == null) {
            throw new OrderValidationException("ERROR: Invalid product.");
        }

    }

    @Override
    public void validateState(Order currentOrder) throws OrderValidationException {
        String stateAbb = currentOrder.getState().getStateAbbreviation();
        if (taxDao.readById(stateAbb) == null) {
            System.out.println(stateAbb);
            throw new OrderValidationException("ERROR: Invalid State.");
        }

    }

    @Override
    public void validateUpdatedOrderInfo(Order originalOrder, Order updatedOrder) throws OrderValidationException {
        if (updatedOrder.getCustomerName() == null || updatedOrder.getCustomerName().isBlank()) {
            updatedOrder.setCustomerName(originalOrder.getCustomerName());
        }

        if (updatedOrder.getState().getStateAbbreviation() == null || updatedOrder.getState().getStateAbbreviation().isBlank()){
            updatedOrder.setState(new Tax(originalOrder.getState().getStateAbbreviation()));
        }


        if (updatedOrder.getProduct().getProductType() == null || updatedOrder.getProduct().getProductType().isBlank()) {
            updatedOrder.setProduct(new Product(originalOrder.getProduct().getProductType()));
        }

        //new area
        if (updatedOrder.getArea() == null || updatedOrder.getArea().compareTo(BigDecimal.valueOf(100.00)) == -1) {
            throw new OrderValidationException("ERROR: Minimum order size is 100 sq ft.");
        }
        updatedOrder.setDate(originalOrder.getDate());
    }

}

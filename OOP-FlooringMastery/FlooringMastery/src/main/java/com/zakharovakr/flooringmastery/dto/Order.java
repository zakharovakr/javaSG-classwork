/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author kristinazakharova
 */
public class Order {
    private LocalDate date;
    private int orderNumber;
    private String customerName;
    private Tax state;
    private Product product;
    private BigDecimal area;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;

    //constructors
    public Order(int orderNumber) {
        this.orderNumber = orderNumber;
    }

//    public Order(String customerName, Tax stateName, Product product, BigDecimal area) {
//        this.customerName = customerName;
//        this.stateName = stateName;
//        this.product = product;
//        this.area = area;
//    };

    //for view - to get info from user
    public Order(String name, String stateAbbreviation, String product, BigDecimal area) {
        this.customerName = name;
        this.state = new Tax(stateAbbreviation);
        this.product = new Product(product);
        this.area = area;
    }

    //getters and setters
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Tax getState() {
        return state;
    }

    public void setState(Tax state) {
        this.state = state;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTax() {
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return getOrderNumber() == order.getOrderNumber() &&
                Objects.equals(getDate(), order.getDate()) &&
                Objects.equals(getCustomerName(), order.getCustomerName()) &&
                Objects.equals(getState(), order.getState()) &&
                Objects.equals(getProduct(), order.getProduct()) &&
                Objects.equals(getArea(), order.getArea()) &&
                Objects.equals(getMaterialCost(), order.getMaterialCost()) &&
                Objects.equals(getLaborCost(), order.getLaborCost()) &&
                Objects.equals(getTax(), order.getTax()) &&
                Objects.equals(getTotal(), order.getTotal());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDate(), getOrderNumber(), getCustomerName(), getState(), getProduct(), getArea(), getMaterialCost(), getLaborCost(), getTax(), getTotal());
    }

    @Override
    public String toString() {
        return ("Date: " + this.getDate() +
                ", order number: " + this.getOrderNumber() +
                ", customer name: " + this.getCustomerName()  +
                ", state: " + this.getState().getStateAbbreviation() +
                ", product: " + this.getProduct().getProductType() +
                ", area: " + this.getArea() +
                ", materialCost: " + this.getMaterialCost() +
                ", laborCost: " + this.getLaborCost() +
                ", tax: " + this.getTax() +
                ", total: " + this.getTotal() +
                '.');
    }
}

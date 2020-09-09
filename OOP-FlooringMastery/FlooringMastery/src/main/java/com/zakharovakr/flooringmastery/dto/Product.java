/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author kristinazakharova
 */
public class Product {
    private String productType;
    private BigDecimal costPerSquareFoot;
    private BigDecimal laborCostPerSquareFoot;

    /**
     * Constructor for the Product class. Takes in a String that will serve as
     * an ID.
     *
     * @param productType The String that will serve as an ID.
     */
    public Product(String productType) {
        this.productType = productType;
    }

    //getters and setters
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return costPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        this.costPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return laborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSqaureFoot) {
        this.laborCostPerSquareFoot = laborCostPerSqaureFoot;
    }

    @Override
    public String toString() {
        return ("Product type: " + this.getProductType() + '\'' +
                ", Cost per square foot: " + this.getCostPerSquareFoot() +
                ", Labor cost per square foot: " + this.getLaborCostPerSquareFoot() +
                '.');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return Objects.equals(getProductType(), product.getProductType()) &&
                Objects.equals(getCostPerSquareFoot(), product.getCostPerSquareFoot()) &&
                Objects.equals(getLaborCostPerSquareFoot(), product.getLaborCostPerSquareFoot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getProductType(), getCostPerSquareFoot(), getLaborCostPerSquareFoot());
    }
}

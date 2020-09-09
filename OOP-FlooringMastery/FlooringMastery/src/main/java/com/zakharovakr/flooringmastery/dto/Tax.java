/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author kristinazakharova
 */
public class Tax {
    private String stateAbbreviation;
    private String stateName;
    private BigDecimal taxRate;

    /**
     * Constructor for the Tax class. Takes in a State abbreviation that will be its ID.
     *
     * @param stateAbbreviation A String that will work as an ID.
     */
    public Tax(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }

    //constructor for testing
    public Tax(String stateAbbreviation, String stateName, BigDecimal taxRate) {
        this.stateAbbreviation = stateAbbreviation;
        this.stateName = stateName;
        this.taxRate = taxRate;
    }

    //getters and setters
    public String getStateAbbreviation() {
        return stateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        this.stateAbbreviation = stateAbbreviation;
    }


    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    @Override
    public String toString() {
        return ("State: " + this.getStateAbbreviation() + '\'' +
                ", Tax rate: " + this.getTaxRate() +
                '.');
    }
}

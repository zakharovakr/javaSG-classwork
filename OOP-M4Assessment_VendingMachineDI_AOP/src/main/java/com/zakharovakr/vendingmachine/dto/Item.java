/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine.dto;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author kristinazakharova
 */
public class Item {

    private String itemId,
            itemName;
    private BigDecimal itemPrice;
    private int itemCount;

    //constructor
    public Item(String itemId) {
        this.itemId = itemId;
    }

    //getters and setters
    public String getItemId() {
        return itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public BigDecimal getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(BigDecimal itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }

    //for testing purposes
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + Objects.hashCode(this.itemId);
        hash = 61 * hash + Objects.hashCode(this.itemName);
        hash = 61 * hash + Objects.hashCode(this.itemPrice);
        hash = 61 * hash + this.itemCount;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Item other = (Item) obj;
        if (this.itemCount != other.itemCount) {
            return false;
        }
        if (!Objects.equals(this.itemId, other.itemId)) {
            return false;
        }
        if (!Objects.equals(this.itemName, other.itemName)) {
            return false;
        }
        if (!Objects.equals(this.itemPrice, other.itemPrice)) {
            return false;
        }
        return true;
    }

    //adding to string method to use further with AOP (aspectJ framework)
    @Override
    public String toString() {
        return "Item{" +
                "itemId='" + itemId + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemCount=" + itemCount +
                '}';
    }
}

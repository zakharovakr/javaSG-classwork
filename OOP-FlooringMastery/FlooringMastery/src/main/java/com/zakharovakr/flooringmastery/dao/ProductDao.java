/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Product;

import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public interface ProductDao {

    /**
     * Gets all the Products in the product file.
     *
     * @return All the Products in the product file.
     */
    public List<Product> readAll();

    /**
     * Gets a Product from the product file.
     *
     * @param productName The product name to look for.
     * @return The Product associated with the product Name.
     */
    public Product readById(String productName);
    
}

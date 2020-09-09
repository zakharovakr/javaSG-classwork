/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Tax;

import java.util.List;

/**
 *
 * @author kristinazakharova
 */
public interface TaxDao {
    /**
     * Gets all the Taxes in the taxes file.
     *
     * @return All the Taxes in the taxes file.
     */
    public List<Tax> readAll();

    /**
     * Gets a Tax from the product file.
     *
     * @param stateAbbreviation The state name to look for.
     * @return The Tax associated with the product Name.
     */
    public Tax readById(String stateAbbreviation);

}

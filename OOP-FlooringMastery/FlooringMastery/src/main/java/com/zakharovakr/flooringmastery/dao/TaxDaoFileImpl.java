/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Tax;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 * @author kristinazakharova
 */
public class TaxDaoFileImpl implements TaxDao {
    private Map<String, Tax> taxes = new HashMap<>();

    public static String TAX_FILE = "Data/Taxes.txt";
    public static final String DELIMITER = ",";

    public TaxDaoFileImpl() throws FlooringPersistenceException {
        loadTaxes();
    }

    //for testing
    public TaxDaoFileImpl(String taxFile) throws FlooringPersistenceException{
        TAX_FILE = taxFile;
        loadTaxes();
    }

    @Override
    public List<Tax> readAll() {
        return new ArrayList<>(taxes.values());
    }

    @Override
    public Tax readById(String stateAbbreviation) {
        if (taxes.containsKey(stateAbbreviation)) {
            return taxes.get(stateAbbreviation);
        }
        return null;
    }

    /**
     * Loads the orders from a file and places them into a Map.
     *
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     */
    private void loadTaxes() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            //Create scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load tax information into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        //skipping file header
        scanner.nextLine();

        while (scanner.hasNextLine()) {

            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Tax currentItem = new Tax((currentTokens[0]));//state abbreviation
            currentItem.setStateName(new String(currentTokens[1]));//state name
            currentItem.setTaxRate(new BigDecimal(currentTokens[2]));//tax rate

            taxes.put(currentItem.getStateAbbreviation(), currentItem);
        }
        scanner.close();
    }
}

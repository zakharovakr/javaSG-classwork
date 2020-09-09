package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Product;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.*;

public class ProductDaoStabImpl implements ProductDao {
    private Map<String, Product> products = new HashMap<>();

    public static String PRODUCT_FILE = "TestFolder/Products_test.txt";
    public static final String DELIMITER = ",";

    public ProductDaoStabImpl() throws FlooringPersistenceException {
        loadProducts();
    }

//    //for testing
//    public ProductDaoFileImpl(String productFile) throws FlooringPersistenceException {
//        PRODUCT_FILE = productFile;
//        loadProducts();
//    }

    @Override
    public List<Product> readAll() {
        return new ArrayList<>(products.values());
    }

    @Override
    public Product readById(String productName) {
        if (products.containsKey(productName)) {
            return products.get(productName);
        }

        return null;
    }

    /**
     * Loads the products from a file and places them into a Map.
     *
     * @throws FlooringPersistenceException - If the file cannot be written/read
     * from, then we have an Exception thrown.
     */
    private void loadProducts() throws FlooringPersistenceException {
        Scanner scanner;

        try {
            //Create scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new FlooringPersistenceException(
                    "-_- Could not load product information into memory.", e);
        }

        String currentLine;
        String[] currentTokens;

        //skipping file header
        scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTokens = currentLine.split(DELIMITER);

            Product currentItem = new Product((currentTokens[0]));
            currentItem.setCostPerSquareFoot(new BigDecimal(currentTokens[1]));
            currentItem.setLaborCostPerSquareFoot(new BigDecimal(currentTokens[2]));

            products.put(currentItem.getProductType(), currentItem);
        }
        scanner.close();
    }
}

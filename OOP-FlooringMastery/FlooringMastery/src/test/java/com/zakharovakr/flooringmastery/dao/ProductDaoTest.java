/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 *
 * @author kristinazakharova
 */
public class ProductDaoTest {

    private ProductDao productDAO;

    public ProductDaoTest() throws Exception {
        productDAO = new ProductDaoFileImpl("TestFolder/Products_test.txt");
    }

    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of readAll method, of class ProductDao.
     */
    @Test
    public void testReadAll() {
        assertEquals(4, productDAO.readAll().size());
    }

    /**
     * Test of readById method, of class ProductDao.
     */
    @Test
    public void testReadById() {
        assertNotNull(productDAO.readById("Carpet"));
        assertEquals(new BigDecimal("2.25"), productDAO.readById("Carpet").getCostPerSquareFoot());

        assertNotNull(productDAO.readById("Laminate"));
        assertNotNull(productDAO.readById("Tile"));
        assertNotNull(productDAO.readById("Wood"));
    }

}

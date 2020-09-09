/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.dao;

import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author kristinazakharova
 */
public class TaxDaoTest {

    private TaxDao taxDAO;

    public TaxDaoTest() throws Exception {
        taxDAO = new TaxDaoFileImpl("TestFolder/Taxes_test.txt");
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
     * Test of readAll method, of class TaxDao.
     */
    @Test
    public void testReadAll() {
        assertEquals(4, taxDAO.readAll().size());
    }

    /**
     * Test of readById method, of class TaxDao.
     */
    @Test
    public void testReadById() {
        assertNotNull(taxDAO.readById("TX"));
        assertEquals(new BigDecimal("4.45"), taxDAO.readById("TX").getTaxRate());

        assertNotNull(taxDAO.readById("WA"));
        assertEquals(new BigDecimal("9.25"), taxDAO.readById("WA").getTaxRate());

        assertNotNull(taxDAO.readById("KY"));
        assertEquals(new BigDecimal("6.00"), taxDAO.readById("KY").getTaxRate());

        assertNotNull(taxDAO.readById("CA"));
        assertEquals(new BigDecimal("25.00"), taxDAO.readById("CA").getTaxRate());

        assertNull(taxDAO.readById("NY"));
    }
    
}

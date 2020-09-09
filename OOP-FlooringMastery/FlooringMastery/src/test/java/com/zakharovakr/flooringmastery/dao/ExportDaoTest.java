package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dto.Order;
import com.zakharovakr.flooringmastery.dto.Product;
import com.zakharovakr.flooringmastery.dto.Tax;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ExportDaoTest {

    ExportDao testExportDao;

    @BeforeEach
    void setUp() throws FlooringPersistenceException, IOException {
        String testFile = "TestFolder/DataExport_test.txt";

        new FileWriter(testFile);
        testExportDao = new ExportDaoFileImpl(testFile);

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void saveOrdersToFile() throws FlooringPersistenceException {
        //arrange &act
        Order newOrder = new Order(1);
        newOrder.setCustomerName("Kristina");
        newOrder.setState(new Tax("CA"));
        newOrder.getState().setTaxRate(new BigDecimal("25.00"));
        newOrder.setProduct(new Product("Wood"));
        newOrder.setArea(new BigDecimal("100.00"));
        newOrder.getProduct().setCostPerSquareFoot(new BigDecimal("5.15"));
        newOrder.getProduct().setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        newOrder.setMaterialCost(new BigDecimal("515.00"));
        newOrder.setLaborCost(new BigDecimal("475.00"));
        newOrder.setTax(new BigDecimal("247.50"));
        newOrder.setTotal(new BigDecimal("1237.50"));
        LocalDate testDate = LocalDate.parse("2021-01-01");
        newOrder.setDate(testDate);

        List<Order> allTestOrders = new ArrayList<>();
        allTestOrders.add(newOrder);

        File exportFile = new File("TestFolder/DataExport_test.txt");
        testExportDao.saveOrdersToFile(allTestOrders);
        //assert
        assertNotEquals(0, exportFile.length());
        assertEquals(222, exportFile.length()); //the amount of characters this particular order + header should have
    }
}
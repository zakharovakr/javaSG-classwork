package com.zakharovakr.vendingmachine.service;

import com.zakharovakr.vendingmachine.dao.*;
import com.zakharovakr.vendingmachine.dto.Change;
import com.zakharovakr.vendingmachine.dto.Item;
import org.junit.*;

import java.math.BigDecimal;
import java.util.List;
import static org.junit.Assert.*;

public class VendingMachineServiceLayerTest {

    private VendingMachineServiceLayer service;

    public VendingMachineServiceLayerTest() {
        VendingMachineDao dao = new VendingMachineDaoStubImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        service = new VendingMachineServiceLayerImpl(dao, auditDao);
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAvailableItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetAvailableItems() throws Exception {
//        List<Item> itemList = service.getAvailableItems();
//        assertEquals(2,itemList.size());
        assertEquals(2, service.getAvailableItems().size());
    }

    /**
     * Test of getItemById method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemById() throws Exception {
        Item item = service.getItemById("1");
        assertNotNull(item);
        item = service.getItemById("10");
        assertNull(item);
    }

    /**
     * Test of setBalance method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testSetAndGetBalance() {
        BigDecimal balance = new BigDecimal("1.00");
        service.setBalance(balance);
        assertEquals(balance, service.getBalance());
    }

    /**
     * Test of vend method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testVend() throws Exception {
        BigDecimal balance = new BigDecimal("2");

        service.setBalance(balance);

        List<Item> itemList = service.getAvailableItems();

        assertEquals(0,itemList.get(0).getItemCount());
        assertEquals(5,itemList.get(1).getItemCount());
    }

    /**
     * Test of calculateChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testCalculateChange() throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineOutOfStockException {
        BigDecimal balance = new BigDecimal("2.00");
        Change change;

        service.setBalance(balance);
        change = service.vend("2");

        //Once item's been vended the count of the item should be decremented
        List<Item> itemList = service.getAvailableItems();
        assertEquals(4,itemList.get(1).getItemCount());

        assertEquals(2,change.getQuarters());
        assertNull(service.getBalance());

    }

}

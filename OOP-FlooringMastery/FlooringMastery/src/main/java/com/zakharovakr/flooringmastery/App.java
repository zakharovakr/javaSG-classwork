package com.zakharovakr.flooringmastery;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.zakharovakr.flooringmastery.controller.FlooringController;
import com.zakharovakr.flooringmastery.dao.FlooringPersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kristinazakharova
 */
public class App {
    public static void main(String[] args) throws FlooringPersistenceException {

        //dependencies without SpringDI
//        UserIO IO = new UserIOConsoleImpl();
//        FlooringView view = new FlooringView(IO);
//        OrderDao orderDao = new OrderDaoFileImpl();
//        ProductDao productDao = new ProductDaoFileImpl();
//        TaxDao taxDao = new TaxDaoFileImpl();
//        ExportDao exportDao = new ExportDaoFileImpl();
//        FlooringAuditDao auditDao = new FlooringAuditDaoFileImpl();
//        FlooringServiceLayer service = new FlooringServiceLayerImpl(taxDao, productDao, orderDao, exportDao, auditDao);
        ApplicationContext appContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");

        FlooringController controller = appContext.getBean("controller", FlooringController.class);
        controller.run();
    }
    
}

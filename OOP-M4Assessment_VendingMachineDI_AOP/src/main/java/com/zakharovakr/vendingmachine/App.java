/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine;

import com.zakharovakr.vendingmachine.controller.VendingMachineController;
import com.zakharovakr.vendingmachine.dao.VendingMachinePersistenceException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author kristinazakharova
 */
public class App {

    public static void main(String[] args) throws VendingMachinePersistenceException {
//        UserIO iO = new UserIOConsoleImpl();
//        VendingMachineView view = new VendingMachineView(iO);
//        VendingMachineDao dao = new VendingMachineDaoFileImpl();
//        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
//        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao);
//        VendingMachineController controller = new VendingMachineController(view, service);

        //Spring dependency injection
        ApplicationContext ctx =
                new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller =
                ctx.getBean("controller", VendingMachineController.class);
        controller.run();
    }
}

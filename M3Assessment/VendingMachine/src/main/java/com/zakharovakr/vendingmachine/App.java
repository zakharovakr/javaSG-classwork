/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.vendingmachine;

import com.zakharovakr.vendingmachine.controller.VendingMachineController;
import com.zakharovakr.vendingmachine.dao.*;
import com.zakharovakr.vendingmachine.service.VendingMachineServiceLayer;
import com.zakharovakr.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.zakharovakr.vendingmachine.view.UserIO;
import com.zakharovakr.vendingmachine.view.UserIOConsoleImpl;
import com.zakharovakr.vendingmachine.view.VendingMachineView;

/**
 *
 * @author kristinazakharova
 */
public class App {

    public static void main(String[] args) throws VendingMachinePersistenceException {
        UserIO iO = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(iO);
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao);
        VendingMachineController controller = new VendingMachineController(view, service);
        controller.run();
    }
}

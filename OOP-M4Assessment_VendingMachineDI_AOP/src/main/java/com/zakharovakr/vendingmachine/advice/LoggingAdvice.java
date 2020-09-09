package com.zakharovakr.vendingmachine.advice;

import com.zakharovakr.vendingmachine.dao.VendingMachineAuditDao;
import com.zakharovakr.vendingmachine.dao.VendingMachinePersistenceException;
import com.zakharovakr.vendingmachine.service.VendingMachineInsufficientFundsException;
import com.zakharovakr.vendingmachine.service.VendingMachineOutOfStockException;
import org.aspectj.lang.JoinPoint;

public class LoggingAdvice {
    VendingMachineAuditDao auditDao;
    public LoggingAdvice(VendingMachineAuditDao auditDao) {
        this.auditDao = auditDao;
    }
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    //nicer audit entry (formatted)
    public void createFormattedAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Item was successfully purchased - id: ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    //handling exceptions for logging
    public void createInsufficientFundsExceptionAuditEntry (VendingMachineInsufficientFundsException ex) {
        String auditEntry = "Exception thrown: " + ex.getMessage();

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createOutOfStockExceptionAuditEntry (VendingMachineOutOfStockException ex) {
        String auditEntry = "Exception thrown: " + ex.getMessage();

        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (VendingMachinePersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}
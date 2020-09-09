/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zakharovakr.flooringmastery.advice;

import com.zakharovakr.flooringmastery.dao.FlooringAuditDao;
import com.zakharovakr.flooringmastery.dao.FlooringPersistenceException;
import org.aspectj.lang.JoinPoint;

/**
 *
 * @author kristinazakharova
 */
public class LoggingAdvice {
    FlooringAuditDao auditDao;

    //constructor
    public LoggingAdvice(FlooringAuditDao auditDao) {
        this.auditDao = auditDao;
    }

    //writes audit entry with AOP - will add later
    public void createAuditEntry(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": ";
        for (Object currentArg : args) {
            auditEntry += currentArg += ": ";
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }

    }

    //nicer audit entry (formatted)
    public void createFormattedAuditEntryAdd(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Order was successfully added : ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createFormattedAuditEntryRemove(JoinPoint jp) {
        //Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Order was successfully removed : ";
//        for (Object currentArg : args) {
//            auditEntry += currentArg;
//        }
        auditEntry += jp.getArgs();
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createFormattedAuditEntryEdit(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Order was successfully edited : ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createFormattedAuditEntryDisplayAll(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "Orders were successfully displayed : ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }

    public void createFormattedAuditEntryExport(JoinPoint jp) {
        Object[] args = jp.getArgs();
        String auditEntry = jp.getSignature().getName() + ": " + "All orders were successfully exported : ";
        for (Object currentArg : args) {
            auditEntry += currentArg;
        }
        try {
            auditDao.writeAuditEntry(auditEntry);
        } catch (FlooringPersistenceException e) {
            System.err.println(
                    "ERROR: Could not create audit entry in LoggingAdvice.");
        }
    }
}

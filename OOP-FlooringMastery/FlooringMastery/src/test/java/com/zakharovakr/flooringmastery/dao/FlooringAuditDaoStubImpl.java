package com.zakharovakr.flooringmastery.dao;

import com.zakharovakr.flooringmastery.dao.FlooringAuditDao;
import com.zakharovakr.flooringmastery.dao.FlooringPersistenceException;

public class FlooringAuditDaoStubImpl implements FlooringAuditDao {
    @Override
    public void writeAuditEntry(String entry) throws FlooringPersistenceException {
        /// do nothing...
    }
}

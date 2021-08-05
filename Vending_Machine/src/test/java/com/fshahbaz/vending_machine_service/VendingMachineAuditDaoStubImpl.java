/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine_service;

import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public class VendingMachineAuditDaoStubImpl implements Vending_Machine_Audit_Dao{
    
    @Override
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException {
        //do nothing . . .
    }

    
}


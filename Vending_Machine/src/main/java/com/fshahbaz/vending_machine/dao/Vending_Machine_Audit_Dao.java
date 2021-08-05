/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dao;

/**
 *
 * @author farhanshahbaz
 */
public interface Vending_Machine_Audit_Dao {
    
    public void writeAuditEntry(String entry) throws VendingMachinePersistenceException;
}

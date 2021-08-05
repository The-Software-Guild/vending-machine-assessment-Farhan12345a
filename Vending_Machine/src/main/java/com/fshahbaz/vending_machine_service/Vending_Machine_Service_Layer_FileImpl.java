/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine_service;

import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao_FileImpl;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author farhanshahbaz
 */
public class Vending_Machine_Service_Layer_FileImpl implements Vending_Machine_Service_Layer {

    Vending_Machine_Dao dao;
    private Vending_Machine_Audit_Dao auditDao;
    
    public Vending_Machine_Service_Layer_FileImpl(Vending_Machine_Dao dao, Vending_Machine_Audit_Dao auditDao) {
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    //USed for testing purposes
    @Override
    public Item addItem(String name, Item item){
        Item addedItem = dao.addItem(name, item);
        return addedItem;
    }
            
            
    @Override
    public BigDecimal removeItem(BigDecimal currentBalance, String name) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        //If money entered is less then item cost
        Item item = getItem(name);
        if(currentBalance.compareTo(item.getCost()) == -1){
            throw new VendingMachineInsufficientFundsException("Insufficient funds!");
        }
        //Check if there's enough quantity
        if(item.getNumItems() <= 0){
            throw new VendingMachineNoItemInventoryException("Item out of stock.");
        }
        
        //Item removedItem = dao.removeItem(item.getName());
        //Change Quantity
        
        updateNumItems(name, item.getNumItems()-1);
        auditDao.writeAuditEntry("Item: " + item + " REMOVED.");
        return currentBalance.subtract(item.getCost());
    
    }
    
    
    //Using Lambda!!
    @Override
    public List<Item> getAllItem() throws VendingMachinePersistenceException {
            return dao.getAllItem()
                    .stream()
                    .collect(Collectors.toList());
                    
                    
    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException, VendingMachineNoItemInventoryException {
            return dao.getItem(name);
    }

    @Override
    public void updateNumItems(String name, int newCount) throws VendingMachinePersistenceException  {
        if (newCount < 0) {
            throw new VendingMachinePersistenceException("Count is Invalid");
        }
        dao.updateNumItems(name, newCount);
    }
   


}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine_service;

import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface Vending_Machine_Service_Layer {
  
    public Item addItem(String name, Item item);
    
    //MAY NEED TO ADD ADDITIONAL EXCEPTIONS!!!
    public BigDecimal removeItem(BigDecimal currentBalance, String name) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException;
  
    List<Item> getAllItem() throws
            VendingMachinePersistenceException;
        
    
    public Item getItem(String name) throws
            VendingMachinePersistenceException, VendingMachineNoItemInventoryException;
       
    public void updateNumItems(String name, int newCount) throws VendingMachinePersistenceException;
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dao;

import com.fshahbaz.vending_machine.dto.Item;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface Vending_Machine_Dao {
     
   
    Item removeItem(String item) throws VendingMachinePersistenceException;
    
    //Lists all the items in the inventory
    public List<Item> getAllItem() throws VendingMachinePersistenceException;

    public Item getItem(String name) throws VendingMachinePersistenceException;
    
    public Item addItem(String name, Item item);
    
    public void updateNumItems(String name, int newCount) throws VendingMachinePersistenceException;
}

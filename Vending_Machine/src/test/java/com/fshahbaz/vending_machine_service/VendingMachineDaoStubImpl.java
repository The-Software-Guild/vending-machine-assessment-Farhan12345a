/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine_service;

import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;



public class VendingMachineDaoStubImpl implements Vending_Machine_Dao {
    
    public Item onlyItem;
    
    public VendingMachineDaoStubImpl() {
        onlyItem = new Item("Chips");
        onlyItem.setCost(new BigDecimal("1.00"));
        onlyItem.setNumItems(5);
    }

    public VendingMachineDaoStubImpl(Item testItem){
         this.onlyItem = testItem;
     }

    @Override
    public Item removeItem(String item) throws VendingMachinePersistenceException {
        if (item.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }   

    @Override
    public List<Item> getAllItem() throws VendingMachinePersistenceException {
        List<Item> itemList = new ArrayList<>();
        itemList.add(onlyItem);
        return itemList;

    }

    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }       
    }

    @Override
    public Item addItem(String name, Item item)   {
        if (name.equals(onlyItem.getName())) {
            return onlyItem;
        } else {
            return null;
        }
    }

    @Override
    public void updateNumItems(String name, int newCount) throws VendingMachinePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //    public void updateNumItems(String name, int newCount) {
//        if (name.equals(onlyItem.getName())) {
//                
//            } else {
//                return null;
//            }  
//    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine_service;

import com.fshahbaz.vending_machine.dao.Vending_Machine_Audit_Dao;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author farhanshahbaz
 */
public class Vending_Machine_Service_Layer_FileImplTest {
    
    private Vending_Machine_Service_Layer service;
    
    
    
    public Vending_Machine_Service_Layer_FileImplTest() {
        Vending_Machine_Dao dao = new VendingMachineDaoStubImpl();
        Vending_Machine_Audit_Dao auditDao = new VendingMachineAuditDaoStubImpl();

        service = new Vending_Machine_Service_Layer_FileImpl(dao, auditDao);
    }
    
    
    @Test
    public void testGetItem() throws Exception {
        
        // ARRANGE
        Item testClone = new Item("Fanta");
            testClone.setCost(new BigDecimal("6.00"));
            testClone.setNumItems(12);
        
        service.addItem(testClone.getName(), testClone);
        // ACT & ASSERT
        //Item shouldBeFanta = service.getItem("Fanta");
       // assertNotNull(shouldBeFanta, "Fanta", "Getting Fanta should be not null.");
        assertEquals( service.getItem("Fanta"), testClone,
                                       "Item stored should be Fanta.");

        Item shouldBeNull = service.getItem("Coke");    
        assertNull( shouldBeNull, "Getting Coke should be null");

    }
    
    @Test
    public void testGetAllItems() throws Exception {
        // ARRANGE
        
        
        Item testClone = new Item("Fanta");
            testClone.setCost(new BigDecimal("4.00"));
            testClone.setNumItems(1);
            
        //might need to add Fanta
       service.addItem(testClone.getName(), testClone);
       
       Item testClone2 = new Item("Coke");
            testClone.setCost(new BigDecimal("4.00"));
            testClone.setNumItems(1);
            
        //might need to add Fanta
        service.addItem(testClone2.getName(), testClone2);
        
        // ACT & ASSERT
        assertEquals(service.getAllItem().size(), 1,
                                       "Should only have two item.");

    }
    
    @Test
    public void testRemoveItem() throws Exception {
        // ARRANGE
        Item testClone = new Item("Sprite");
            testClone.setCost(new BigDecimal("8.00"));
            testClone.setNumItems(15);

        BigDecimal currentBalance = new BigDecimal("10.00");
        
        service.addItem(testClone.getName(), testClone);
        // ACT & ASSERT
        BigDecimal spriteChange = service.removeItem(currentBalance, testClone.getName());
        assertNotNull( spriteChange, "Change should not be null (In situation).");
        
        //Change should be $10.00 - $8.00
        assertEquals(spriteChange, new BigDecimal("2.00"));

    }
    
//    @Test
//    public void testUpdateItem() throws Exception {
//        // ARRANGE
//        Item testClone = new Item("Sprite");
//            testClone.setCost(new BigDecimal("8.00"));
//            testClone.setNumItems(15);
//
//        
//        //Changing item count to 1
//        service.updateNumItems(testClone.getName(), 1);
//        //Number of items should be changed from 15 -> 1
//        assertEquals(testClone.getNumItems(), 1, "Error, Cant Update");
//
//    }
    
    
}

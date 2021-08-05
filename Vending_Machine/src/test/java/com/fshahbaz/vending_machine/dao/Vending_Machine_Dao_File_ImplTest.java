/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dao;

import com.fshahbaz.vending_machine.dto.Item;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
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
public class Vending_Machine_Dao_File_ImplTest {
    
    Vending_Machine_Dao testDao;
    
    public Vending_Machine_Dao_File_ImplTest() {
        
    }
    
     @BeforeEach
    public void setUp() throws Exception{
        String testFile = "testVM.txt";
        // Use the FileWriter to quickly blank the file
        new FileWriter(testFile);
        testDao = new Vending_Machine_Dao_File_Impl(testFile);
    }
    
    @Test
    public void testGetItem() throws Exception {
        // Create our method test inputs
        String itemName = "Doritoes";
        Item item = new Item(itemName);
        item.setCost(new BigDecimal("3.00"));
        item.setNumItems(4);
       

        //  Add the Item to the DAO
        
        testDao.addItem(itemName, item);
        // Get the item from the DAO
        Item retrievedItem = testDao.getItem(itemName);

        //Use Item equals and hashcode methods****
        // Check the data is equal
        assertEquals(item.getName(),
                    retrievedItem.getName(),
                    "Checking Item Name.");
        assertEquals(item.getCost(),
                    retrievedItem.getCost(),
                    "Checking Item Cost");
        assertEquals(item.getNumItems(), 
                    retrievedItem.getNumItems(),
                    "Checking Item Number of Items");

    }
    
    @Test
    public void testAddGetAllItems() throws Exception {
        // Create our first item
        String itemName = "Doritoes";
        Item item = new Item(itemName);
        item.setCost(new BigDecimal("3.00"));
        item.setNumItems(4);

        // Create our second student
        String itemName2 = "Lays";
        Item item2 = new Item(itemName2);
        item.setCost(new BigDecimal("4.00"));
        item.setNumItems(5);

        // Add both our students to the DAO
        testDao.addItem(item.getName(), item);
        testDao.addItem(item2.getName(), item2);

        // Retrieve the list of all students within the DAO
        List<Item> allItems = testDao.getAllItem();

        // First check the general contents of the list
        assertNotNull(allItems, "The list of items must not null");
        assertEquals(2, allItems.size(),"List of items should have 2 students.");

        // Then the specifics
        assertTrue(testDao.getAllItem().contains(item),
                    "The list of students should include Ada.");
        assertTrue(testDao.getAllItem().contains(item2),
                "The list of students should include Charles.");

    }
    
    @Test
    public void testRemoveItem() throws Exception {
        // Create our first item
        String itemName = "Doritoes";
        Item item = new Item(itemName);
        item.setCost(new BigDecimal("3.00"));
        item.setNumItems(4);

        // Create our second student
        String itemName2 = "Lays";
        Item item2 = new Item(itemName2);
        item.setCost(new BigDecimal("7.00"));
        item.setNumItems(5);

        // Add both to the DAO
        testDao.addItem(item.getName(), item);
        testDao.addItem(item2.getName(), item2);

        // remove the first item - Doritoes
        Item removedStudent = testDao.removeItem(item.getName());

        // Check that the correct object was removed.
        assertEquals(removedStudent, item, "The removed student should be Doritos.");

        // Get all the items
        List<Item> allItems = testDao.getAllItem();

        // First check the general contents of the list
        assertNotNull( allItems, "All students list should be not null.");
        assertEquals( 1, allItems.size(), "All students should only have 1 student.");

        // Then the specifics
        assertFalse( allItems.contains(item), "All students should NOT include Doritos.");
        assertTrue( allItems.contains(item2), "All students should NOT include Lays.");    

        // Remove the second student
        removedStudent = testDao.removeItem(item2.getName());
        // Check that the correct object was removed.
        assertEquals( removedStudent, item2, "The removed student should be Lays.");

        // retrieve all of the students again, and check the list.
        allItems = testDao.getAllItem();

        // Check the contents of the list - it should be empty
        assertTrue( allItems.isEmpty(), "The retrieved list of students should be empty.");

        // Try to 'get' both students by their old id - they should be null!
        Item retrievedStudent = testDao.getItem(item.getName());
        assertNull(retrievedStudent, "Doritos was removed, should be null.");

        retrievedStudent = testDao.getItem(item2.getName());
        assertNull(retrievedStudent, "Lays was removed, should be null.");

    }
    
    @Test
    public void testUpdateItem() throws Exception {
        // Create our method test inputs
        String itemName = "Doritoes";
        Item item = new Item(itemName);
        item.setCost(new BigDecimal("3.00"));
        item.setNumItems(4);
       
        testDao.addItem(itemName, item);
        //Update number of items to the DAO
        testDao.updateNumItems(itemName, 40);
        // Get the item from the DAO
        Item retrievedItem = testDao.getItem(itemName);
        
        // Check the data is equal
        assertEquals(retrievedItem.getNumItems(),
                    40,
                    "The number of items had been changed!");
       
    }
   
    }

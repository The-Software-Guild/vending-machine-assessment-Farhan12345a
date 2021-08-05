/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dao;

import com.fshahbaz.vending_machine.dto.Item;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


/**
 *
 * @author farhanshahbaz
 */
public class Vending_Machine_Dao_File_Impl implements Vending_Machine_Dao {
    
    private Map<String, Item> inventory = new HashMap<>();
    //private Change current_change;
    
    private final String VM_File;
    public static final String DELIMITER = "::";

    public Vending_Machine_Dao_File_Impl(){
        VM_File = "vending_machine.txt";
    }

    //Made this UN_PUBLIC
    //Change
    public Vending_Machine_Dao_File_Impl(String rosterTextFile){
        VM_File = rosterTextFile;
    }

    @Override
    public Item removeItem(String item) throws VendingMachinePersistenceException{
        loadRoster();
        Item remove = inventory.remove(item);
        
        writeRoster();
        return remove;
    }
    
    //TESTING PURPOSES
    @Override
    public Item addItem(String name, Item item) {
        Item prevItem = inventory.put(name, item);
        return prevItem;
    }
    
    @Override
    public List<Item> getAllItem() throws VendingMachinePersistenceException{
        loadRoster();
        return new ArrayList<Item>(inventory.values()); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Item getItem(String name) throws VendingMachinePersistenceException {
        return inventory.get(name);
    }

    //***CHANGE AROUND
    @Override
    public void updateNumItems(String name, int newCount) throws VendingMachinePersistenceException {
        if(newCount < 0){
            throw new VendingMachinePersistenceException("newCount must be >= 0");
        }
        getItem(name).setNumItems(newCount);
        writeRoster();
    }
    
    

    //Below methods reads from the file
    private Item unmarshallItem(String itemAsText){
    
        String[] itemsTokens = itemAsText.split(DELIMITER);

        // Given the pattern above, the item name is in index 0 of the array.
        String itemName = itemsTokens[0];

        // Which we can then use to create a new Item object to satisfy
        // the requirements of the Student constructor.
        Item itemFromFile = new Item(itemName);

        //Adding second item
        BigDecimal cost = new BigDecimal(itemsTokens[1]);
        
        //Adding third Item
        int numberOfItems = Integer.parseInt(itemsTokens[2]);
        
        Item newItem = new Item(itemName, cost, numberOfItems);

        // We have now created a Item. Return it!
        return newItem;
    }
    
    private void loadRoster() throws VendingMachinePersistenceException {
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(VM_File)));
        } catch (FileNotFoundException e) {
            throw new VendingMachinePersistenceException(
                    "-_- Could not load roster data into memory.", e);
        }
        
        // currentLine holds the most recent line read from the file
        String currentLine;
        
        // currentStudent holds the most recent student unmarshalled
        Item currentItem;
        
        // Go through ROSTER_FILE line by line, decoding each line into a 
        // Student object by calling the unmarshallStudent method.
        // Process while we have more lines in the file
        while (scanner.hasNextLine()) {
            // get the next line in the file
            currentLine = scanner.nextLine();
            // unmarshall the line into a Student
            currentItem = unmarshallItem(currentLine);

            // We are going to use the student id as the map key for our student object.
            // Put currentStudent into the map using student id as the key
            inventory.put(currentItem.getName(), currentItem);
        }
        // close scanner
        scanner.close();
    }

    
    private String marshallStudent(Item aItem){
        // We need to turn a Student object into a line of text for our file.
        // For example, we need an in memory object to end up like this:
        // 4321::Charles::Babbage::Java-September1842

        // It's not a complicated process. Just get out each property,
        // and concatenate with our DELIMITER as a kind of spacer. 

        // Start with the item name, since that's supposed to be first.
        String itemName = aItem.getName() + DELIMITER;

        //Cost
        itemName += aItem.getCost() + DELIMITER;

        // Number of Items
        itemName += aItem.getNumItems();
        
        return itemName;
    }
    
        /**
     * Writes allItems in the inventory to a inventory_data.txt.  See loadRoster
     * for file format.
     * 
     * @throws ClassRosterPersistenceException if an error occurs writing to the file
     */
    private void writeRoster() throws VendingMachinePersistenceException {
        // NOTE FOR APPRENTICES: We are not handling the IOException - but
        // we are translating it to an application specific exception and 
        // then simple throwing it (i.e. 'reporting' it) to the code that
        // called us.  It is the responsibility of the calling code to 
        // handle any errors that occur.
        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(VM_File));
        } catch (IOException e) {
            throw new VendingMachinePersistenceException(
                    "Could not save student data.", e);
        }

        // Write out the Student objects to the roster file.
        // NOTE TO THE APPRENTICES: We could just grab the student map,
        // get the Collection of Students and iterate over them but we've
        // already created a method that gets a List of Students so
        // we'll reuse it.
        String studentAsText;
        List<Item> itemList = this.getAllItem();
        for (Item currentStudent : itemList) {
            // turn a Student into a String
            studentAsText = marshallStudent(currentStudent);
            // write the Student object to the file
            out.println(studentAsText);
            // force PrintWriter to write line to the file
            out.flush();
        }
        // Clean up
        out.close();
    }  

    

    

  
}

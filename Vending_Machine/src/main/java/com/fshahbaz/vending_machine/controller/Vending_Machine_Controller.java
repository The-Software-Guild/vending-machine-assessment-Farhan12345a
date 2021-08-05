/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.controller;

import com.fshahbaz.vending_machine.dao.VendingMachinePersistenceException;
import com.fshahbaz.vending_machine.dao.Vending_Machine_Dao;
import com.fshahbaz.vending_machine.dto.Change;
import com.fshahbaz.vending_machine.dto.Item;
import com.fshahbaz.vending_machine.ui.Vending_Machine_View;
import com.fshahbaz.vending_machine_service.VendingMachineInsufficientFundsException;
import com.fshahbaz.vending_machine_service.VendingMachineNoItemInventoryException;
import com.fshahbaz.vending_machine_service.Vending_Machine_Service_Layer;
import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public class Vending_Machine_Controller {
    private Vending_Machine_View view;
    private Vending_Machine_Service_Layer service;
    private BigDecimal currentBalance;
   
    
    public  Vending_Machine_Controller(Vending_Machine_Service_Layer service, Vending_Machine_View view) {
        this.service = service;
        this.view = view;
        this.currentBalance = new BigDecimal("0.00");
    }
    
    //Displays and acts on Vending Machine Menu
    public void run()  {
        boolean keepGoing = true;
          
        try{
            vendingMachineBanner();
            //Shows menu items at the start of program
            listItems();
            //Ask user for money and retrive money
            obtainMoney();

                while (keepGoing) {
                    displayCurrentBalance();
                    //Display menu again and ask for choice
                    vendingMachineBanner();
                    listItems();

                    //Obtain choice and break if input is 'q'
                    String choice = getInput();
                    
                    if(choice.equals("q")){
                        System.exit(0);
                    }

                    Item choosen = getItem(Integer.parseInt(choice));

                    try{
                        BigDecimal change = removeItem(choosen);

                    }catch(VendingMachineInsufficientFundsException wmi){
                        insuffeicientFunds();
                    }catch(VendingMachineNoItemInventoryException ife){
                        notInStock();
                    }

                    if(!anotherItem()){
                        break;
                    }

                    }

                    }catch(VendingMachinePersistenceException e){
                //view error message
                //Sys.ext
                }
                exitMessage();
                keepGoing=false;
     
        }
   
    private void vendingMachineBanner() {
        view.displayVMBanner();
    }
    
    private void displayCurrentBalance(){
        view.displayBalance(currentBalance);
    }
    
    private String getInput(){
        String choice = view.getItemChoice();
        //check if valid choice
        return choice;
    }
    
    private Item getItem(int choice) throws VendingMachinePersistenceException{
        List<Item> itemList = service.getAllItem();
        //Avoid Array out of bound error
        Item choosen = itemList.get(choice-1);
        view.displayChoosenItem(choosen);
        return choosen;
    }
    
    private boolean anotherItem(){
        return view.askForAnotherItem();
        
    }
    
    private void obtainMoney(){
        String moneyEntered = view.getMoney();
        if(moneyEntered.equals("q")){
            System.exit(0);
        }
        //Converting to BigDecimal
        BigDecimal bdMoneyInput = new BigDecimal(moneyEntered);
        //this.currentBalance.add(bdMoneyInput);
        currentBalance = bdMoneyInput;
        view.displayMoneyAdded(bdMoneyInput);
    }
    
    private void insuffeicientFunds(){
        view.displayInsufficentFunds();
        view.displayCurrentBalance(this.currentBalance);
    }
    
    private void notInStock(){
        view.displayNotInStock();
        
    }
    private void getItemChoice() {
        view.getItemChoice();
    }
    
    private void listItems() throws VendingMachinePersistenceException {
        //view.displayDisplayAllBanner();
        List<Item> itemList = service.getAllItem();
        view.displayItemList(itemList);
    }
    
    //**CHANGE AROUND
    private BigDecimal removeItem(Item item) throws VendingMachinePersistenceException, VendingMachineInsufficientFundsException, VendingMachineNoItemInventoryException {
        //view.displayRemoveStudentBanner();
       
        BigDecimal change = service.removeItem(currentBalance, item.getName());
        //service.decreaseCount(item);
        view.displayRemovalItem(item);
        //Handle change
        if(change.compareTo(BigDecimal.ZERO) == 0){
            //do nothing
        }else{
            //display change
            view.displayChange(change);
            Change coinChange = new Change();
            view.displayCoins(change, coinChange);
        }
        //Updating current Balance
        currentBalance = currentBalance.subtract(item.getCost());
        return change;
    }
    
    private void exitVendingMachine(){
        String exit = view.displayExitOption();
        if(exit == "q"){
            System.exit(1);
        }
    }
    
//    private void displayChange(BigDecimal cost){
//        view.displayChange(currentBalance, cost);
//        Change change = new Change();
//        view.displayCoins(currentBalance, cost, change);
//    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void exitMessage() {
        view.displayExitMessage();
    }
    
}

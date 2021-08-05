/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.ui;

import com.fshahbaz.vending_machine.dto.Change;
import com.fshahbaz.vending_machine.dto.Item;
import java.math.BigDecimal;
import java.util.List;


public class Vending_Machine_View {
        
        private UserIO io;
        public Vending_Machine_View (UserIO io) {
            this.io = io;
        }

       
        
    public void displayVMBanner(){
        io.print("Farhans Vending Machine");
        io.print("==================================");
       
    }
        
    public void displayItemList(List<Item> itemList) {
        int i = 0;
        for (Item currentItem : itemList) {
            String studentInfo = String.format((++i)+".%s:\t%s\tQuantity:%s",
                  currentItem.getName(),
                  currentItem.getCost(),
                  currentItem.getNumItems());
            io.print(studentInfo);
            //i++;
        }
        io.print("==================================");
    }
    
    public boolean askForAnotherItem(){
        boolean flag = true;
        String res =  io.readString("Do you want another item? (y/n)");
            switch (res) {
                case "y":
                    return flag;
                case "n":
                    flag = false;
                    break;
                default:
                    displayUnknownCommandBanner();
                    flag = false;
                    break;
            }
        return flag;
    }
    
    public String getStudentIdChoice() {
        return io.readString("Please enter the Item Name.");
    }
    
    public String getItemChoice() {
        return io.readString("Please enter an Item choice from above: (Or q to  exit)");
    }
    
    public void displayInsufficentFunds() {
        io.print("Insuffiecient Funds!");
    }
    
    public void displayNotInStock() {
        io.print("Item Not in Stock!");
    }
    
    public void displayRemovalItem(Item item){
        io.print("Item that's going to be vended: " + item.getName() );
        
    }
    
    public void displayChoosenItem(Item item){
        io.print("You have choosen " + item.getName() +"\nIt costs: $" +item.getCost());
        
    }

    public void displayRemoveResult(Item item) {
        if(item != null){
          io.print("Item successfully removed.");
        }else{
          io.print("No such student.");
        }
        io.readString("Please hit enter to continue.");
    }
    
    public void displayBalance(BigDecimal balance){
        io.print("Current balance: $"+ balance);
    }
    public String getMoney(){
        String money = io.readString("Please enter money into vending machine or 'q' to quit");
        return money;
    }
    
    public void displayMoneyAdded(BigDecimal money) {
        io.print("You've inputed $" + money);
    }
    
    public void displayChange(BigDecimal change) {
        //BigDecimal change = current.subtract(cost);
        io.print("Actual change is $" + change);
    }
    
    public void displayCoins(BigDecimal cost, Change change){
        change.getChange(cost);
        io.print("Number of Quarters: " + String.valueOf(change.getNumQuarters()));
        io.print("Number of Dimes: " + String.valueOf(change.getNumDimes()));
        io.print("Number of Nickels: " + String.valueOf(change.getNumNickels()));
        io.print("Number of Pennies: " + String.valueOf(change.getNumPennies()));
    }
    
    public void displayCurrentBalance(BigDecimal money) {
        io.print("You're current balance is: $" + money);
    }

    public void displayExitMessage() {
        io.print("Thanks for vending! Goodbye!");
    }
    
    public String displayExitOption() {
        String exit = io.readString("Please hit q to exit.");
        return exit;
    }
    
    public void displayExitBanner() {
        io.print("Good Bye!!!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print("=== ERROR ===");
        io.print(errorMsg);
    }
    
   
}

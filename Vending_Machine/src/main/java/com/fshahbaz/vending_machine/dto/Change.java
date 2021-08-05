/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dto;

import java.math.BigDecimal;


public class Change {
    //WERE INTS BEFORE
    private int numQuarters;
    private int numDimes;
    private int numNickels;
    private int numPennies;
    public enum Coin {PENNY, NICKLE, DIME, QUARTER}

    private String convert = "100";
    private BigDecimal bDConvert = new BigDecimal(convert);
    private BigDecimal qDiv[];
    
    public Change(){
        
    }
    
    
    //takes change in pennies
    public void getChange(BigDecimal itemPrice){
        BigDecimal change = itemPrice;
        //Exception if balance > itemPrice
        
        //First step is find quarters
        //change/quarter = 3Q and remainder 12 coins
        //Converting 25 -> 0.25
        BigDecimal quarter = new BigDecimal(Coins.QUARTER.getCents());
        quarter = quarter.divide(bDConvert);
        
        //Find Quarters
        qDiv = change.divideAndRemainder(quarter);
        this.numQuarters = qDiv[0].intValueExact();
        change = qDiv[1];
        
        //Convert Dimes
        BigDecimal dime = new BigDecimal(Coins.DIME.getCents());
        dime = dime.divide(bDConvert);
        
        //Find Dimes
        qDiv = change.divideAndRemainder(dime);
        this.numDimes = qDiv[0].intValueExact();
        change = qDiv[1];
        
        //Convert Dimes
        BigDecimal nickel = new BigDecimal(Coins.NICKEL.getCents());
        nickel = nickel.divide(bDConvert);
        
        //Find Nickels
        qDiv = change.divideAndRemainder(nickel);
        this.numNickels = qDiv[0].intValueExact();
        change = qDiv[1];
        
        //Convert Pennies
        BigDecimal penny = new BigDecimal(Coins.PENNY.getCents());
        penny  = penny .divide(bDConvert);
        
        //Find Pennies
        qDiv = change.divideAndRemainder(penny);
        this.numPennies = qDiv[0].intValueExact();
        
    }
    
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + this.numQuarters;
        hash = 31 * hash + this.numDimes;
        hash = 31 * hash + this.numNickels;
        hash = 31 * hash + this.numPennies;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Change other = (Change) obj;
        if (this.numQuarters != other.numQuarters) {
            return false;
        }
        if (this.numDimes != other.numDimes) {
            return false;
        }
        if (this.numNickels != other.numNickels) {
            return false;
        }
        if (this.numPennies != other.numPennies) {
            return false;
        }
        return true;
    }

    public int getNumQuarters() {
        return numQuarters;
    }

    public void setNumQuarters(int numQuarters) {
        this.numQuarters = numQuarters;
    }

    public int getNumDimes() {
        return numDimes;
    }

    public void setNumDimes(int numDimes) {
        this.numDimes = numDimes;
    }

    public int getNumNickels() {
        return numNickels;
    }

    public void setNumNickels(int numNickels) {
        this.numNickels = numNickels;
    }

    public int getNumPennies() {
        return numPennies;
    }

    public void setNumPennies(int numPennies) {
        this.numPennies = numPennies;
    }
    
    
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fshahbaz.vending_machine.dto;

import java.math.BigDecimal;


public enum Coins {
    QUARTER("25"),
    DIME("10"),
    NICKEL("5"),
    PENNY("1");
    
    private final String cents;
    
    private Coins(String cents){
        this.cents = cents;
    }
    
    public String getCents(){
        return this.cents;
    }
    
}

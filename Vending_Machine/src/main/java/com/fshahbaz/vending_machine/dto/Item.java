package com.fshahbaz.vending_machine.dto;

import java.util.Objects;

/**
 *
 * @author farhanshahbaz
 */

//****DTO*****

import java.math.BigDecimal;


public class Item {
    private String name;
    private BigDecimal cost;
    private int numItems;

    public Item(){
        
    }
    
    //Constructor that takes name as only parameter
    public Item(String name){
        this.name = name;
    }
    
    //Constructor that takes name as only parameter
    public Item(String name, BigDecimal cost, int numItems){
        this.name = name;
        this.cost = cost;
        this.numItems = numItems;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public int getNumItems() {
        return numItems;
    }

    public void setNumItems(int numItems) {
        this.numItems = numItems;
    }

    
    @Override
    public String toString() {
        return "Item{" + "name=" + name + ", cost=" + cost + ", numItems=" + numItems + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.name);
        hash = 23 * hash + Objects.hashCode(this.cost);
        hash = 23 * hash + this.numItems;
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
        final Item other = (Item) obj;
        if (this.numItems != other.numItems) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.cost, other.cost)) {
            return false;
        }
        return true;
    }
    
    
    
    
}

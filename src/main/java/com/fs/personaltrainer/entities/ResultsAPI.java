/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public class ResultsAPI {
    
    //NOT RECEIVEING ALL OF THE DATA
    @JsonProperty("id")
    private String id;
    
    @JsonProperty("name")
    private String name;
    //private String uuid;
    @JsonProperty("description")
    private String description;
    //private String creation_date;
    @JsonProperty("category")
    private CategoryAPI category;
    //@JsonProperty("equipment")
   // private EquipmentAPI equipment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

   
//    public EquipmentAPI getEquipment() {
//        return equipment;
//    }
//
//    public void setEquipment(EquipmentAPI equipment) {
//        this.equipment = equipment;
//    }

    public CategoryAPI getCategory() {
        return category;
    }

    public void setCategory(CategoryAPI category) {
        this.category = category;
    }

    
    
    
}

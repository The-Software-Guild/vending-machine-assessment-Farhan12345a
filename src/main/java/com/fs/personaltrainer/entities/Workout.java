/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.entities;

import java.util.Objects;

/**
 *
 * @author farhanshahbaz
 */
public class Workout {
    private int workout_id;
    private String name;
    private String target_muscle;
    private String equipment;

    public Workout() {
    }

    public Workout(int workout_id, String name, String target_muscle, String equipment) {
        this.workout_id = workout_id;
        this.name = name;
        this.target_muscle = target_muscle;
        this.equipment = equipment;
    }

    public Workout(int workout_id, String name, String target_muscle) {
        this.workout_id = workout_id;
        this.name = name;
        this.target_muscle = target_muscle;
    }

    
    public int getId() {
        return workout_id;
    }

    public void setId(int workout_id) {
        this.workout_id = workout_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTarget_muscle() {
        return target_muscle;
    }

    public void setTarget_muscle(String target_muscle) {
        this.target_muscle = target_muscle;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.workout_id;
        hash = 37 * hash + Objects.hashCode(this.name);
        hash = 37 * hash + Objects.hashCode(this.target_muscle);
        hash = 37 * hash + Objects.hashCode(this.equipment);
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
        final Workout other = (Workout) obj;
        if (this.workout_id != other.workout_id) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.target_muscle, other.target_muscle)) {
            return false;
        }
        if (!Objects.equals(this.equipment, other.equipment)) {
            return false;
        }
        return true;
    }
    
    
}

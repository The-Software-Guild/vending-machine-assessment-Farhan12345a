/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.entities;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author farhanshahbaz
 */
public class Trainer {
    private int id;
    private String first_name;
    private String lastName;
    private int age;
    private int years_of_experience;
    private boolean isAvailable;
    private Workout workout;
    private List<Client> clients;

    public Trainer() {
    }

    public Trainer(int id, String first_name, String lastName, int age, int years_of_experience, boolean isAvailable, Workout workout, List<Client> clients) {
        this.id = id;
        this.first_name = first_name;
        this.lastName = lastName;
        this.age = age;
        this.years_of_experience = years_of_experience;
        this.isAvailable = isAvailable;
        this.workout = workout;
        this.clients = clients;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getYears_of_experience() {
        return years_of_experience;
    }

    public void setYears_of_experience(int years_of_experience) {
        this.years_of_experience = years_of_experience;
    }

    public boolean isIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Workout getWorkout() {
        return workout;
    }

    public void setWorkout(Workout workout) {
        this.workout = workout;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + Objects.hashCode(this.first_name);
        hash = 97 * hash + Objects.hashCode(this.lastName);
        hash = 97 * hash + this.age;
        hash = 97 * hash + this.years_of_experience;
        hash = 97 * hash + (this.isAvailable ? 1 : 0);
        hash = 97 * hash + Objects.hashCode(this.workout);
        hash = 97 * hash + Objects.hashCode(this.clients);
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
        final Trainer other = (Trainer) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.age != other.age) {
            return false;
        }
        if (this.years_of_experience != other.years_of_experience) {
            return false;
        }
        if (this.isAvailable != other.isAvailable) {
            return false;
        }
        if (!Objects.equals(this.first_name, other.first_name)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.workout, other.workout)) {
            return false;
        }
        if (!Objects.equals(this.clients, other.clients)) {
            return false;
        }
        return true;
    }

    
    

    
}

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
public class ExcerciseInfoAPI {
    
    private List<ResultsAPI> results;
    int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public ExcerciseInfoAPI() {
    }

    @Override
    public String toString() {
        return "ExcerciseInfoAPI{" + "results=" + results + ", count=" + count + '}';
    }

    public ExcerciseInfoAPI(List<ResultsAPI> results) {
        this.results = results;
    }


    public List<ResultsAPI> getResults() {
        return results;
    }

    public void setResults(List<ResultsAPI> results) {
        this.results = results;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.results);
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
        final ExcerciseInfoAPI other = (ExcerciseInfoAPI) obj;
        if (!Objects.equals(this.results, other.results)) {
            return false;
        }
        return true;
    }
    
    
    
}

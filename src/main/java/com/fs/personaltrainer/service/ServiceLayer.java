/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.service;

import com.fs.personaltrainer.entities.CategoryAPI;
import com.fs.personaltrainer.entities.ResultsAPI;
import com.fs.personaltrainer.entities.Workout;
import java.util.List;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author farhanshahbaz
 */
public interface ServiceLayer {
    //Workout
    Workout getWorkoutById(int id);
    List<Workout> getAllWorkouts();
    Workout addWorkout(Workout workout);
    void updateWorkout(Workout workout);
    void deleteWorkoutById(int id);
    
    List <ResultsAPI> getResultsAPI(int id);
    
    CategoryAPI getCategoryAPI();
}

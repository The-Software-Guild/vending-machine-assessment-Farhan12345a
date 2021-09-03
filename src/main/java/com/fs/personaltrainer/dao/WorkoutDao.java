/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.entities.Workout;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface WorkoutDao {
    Workout getWorkoutById(int id);
    List<Workout> getAllWorkouts();
    Workout addWorkout(Workout workout);
    void updateWorkout(Workout workout);
    void deleteWorkoutById(int id);
    
    
}

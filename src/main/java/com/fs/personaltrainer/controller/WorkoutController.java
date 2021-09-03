/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.controller;

import com.fs.personaltrainer.dao.ClientDao;
import com.fs.personaltrainer.dao.TrainerDao;
import com.fs.personaltrainer.dao.WorkoutDao;
import com.fs.personaltrainer.entities.Workout;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class WorkoutController {
    
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    @GetMapping("workouts")
    public String displayworkouts(Model model) {
        List<Workout> workouts = workoutDao.getAllWorkouts();
        model.addAttribute("workouts", workouts);
        return "workouts";
    }
    
    @PostMapping("addWorkout")
    public String addworkout(HttpServletRequest request) {
        String name = request.getParameter("name");
        String muscle = request.getParameter("target_muscle");
        String equipment = request.getParameter("equipment");
        
        Workout workout = new Workout();
        workout.setName(name);
        workout.setTarget_muscle(muscle);
        workout.setEquipment(equipment);
        
        workoutDao.addWorkout(workout);
        
        return "redirect:/workouts";
    }
    
    @GetMapping("deleteWorkout")
    public String deleteWorkout(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        workoutDao.deleteWorkoutById(id);
        
        return "redirect:/workouts";
    }
    
    @GetMapping("editWorkout")
    public String editWorkout(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Workout workout = workoutDao.getWorkoutById(id);
        
        model.addAttribute("workout", workout);
        return "editWorkout";
    }

    @PostMapping("editWorkout")
    public String performEditWorkout(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        Workout workout = workoutDao.getWorkoutById(id);
        
        workout.setName(request.getParameter("name"));
        workout.setTarget_muscle(request.getParameter("target_muscle"));
        workout.setEquipment(request.getParameter("equipment"));
        
        workoutDao.updateWorkout(workout);
        
        return "redirect:/workouts";
    }
}


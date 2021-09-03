/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.controller;

import com.fs.personaltrainer.dao.ClientDao;
import com.fs.personaltrainer.dao.TrainerDao;
import com.fs.personaltrainer.dao.WorkoutDao;
import com.fs.personaltrainer.entities.Client;
import com.fs.personaltrainer.entities.Trainer;
import com.fs.personaltrainer.entities.Workout;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author farhanshahbaz
 */
@Controller
public class TrainerController {
    
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    @GetMapping("trainers")
    public String displayTrainers(Model model) {
        List<Trainer> trainers = trainerDao.getAllTrainers();
        List<Client> clients = clientDao.getAllClients();
        List<Workout> workouts = workoutDao.getAllWorkouts();
        
        model.addAttribute("workouts",workouts);
        model.addAttribute("clients",clients);
        model.addAttribute("trainers", trainers);
        
        return "trainers";
    }
    
    @PostMapping("addTrainer")
    public String addtrainer(Trainer trainer, HttpServletRequest request) {
        String workoutId = request.getParameter("workoutId");
        String[] clientIds =  request.getParameterValues("clientId");
        
        trainer.setWorkout(workoutDao.getWorkoutById(Integer.parseInt(workoutId)));
        
        List <Client> clients = new ArrayList<>();
        
        for(String clientId : clientIds){
            clients.add(clientDao.getClientById(Integer.parseInt(clientId)));
        }
        
        trainer.setClients(clients);
        trainerDao.addTrainer(trainer);
                
        return "redirect:/trainers";
    }
//    
//    @GetMapping("deleteTrainer")
//    public String deleteworkout(HttpServletRequest request) {
//        int id = Integer.parseInt(request.getParameter("trainer_id"));
//        trainerDao.deleteTrainerById(id);
//        
//        return "redirect:/trainer";
//    }
    /*
    @GetMapping("editWorkout")
    public String editworkout(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("workout_id"));
        Workout workout = workoutDao.getWorkoutById(id);
        
        model.addAttribute("workout", workout);
        return "editworkout";
    }
    @PostMapping("editWorkout")
    public String perfromEditworkout(HttpServletRequest request, Model model) {
         int id = Integer.parseInt(request.getParameter("workout_id"));
        Workout workout = workoutDao.getWorkoutById(id);
        String name = request.getParameter("name");
        String target_muscle = request.getParameter("target_muscle");
        String equipment = request.getParameter("equipment");
        
        workout.setName(name);
        workout.setTarget_muscle(target_muscle);
        workout.setEquipment(equipment);
        workoutDao.updateWorkout(workout);
        return "redirect:/workout";
    }
*/
}


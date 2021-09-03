/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.entities.Client;
import com.fs.personaltrainer.entities.Trainer;
import com.fs.personaltrainer.entities.Workout;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class WorkoutDaoDBTest {
    
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    public WorkoutDaoDBTest() {
    }
    
    @BeforeEach 
    public void setUp() {
        List<Workout> workouts = workoutDao.getAllWorkouts();
        for(Workout workout : workouts) {
            workoutDao.deleteWorkoutById(workout.getId());
        }
        
        List<Client> clients = clientDao.getAllClients();
        for(Client client : clients) {
            clientDao.deleteClientById(client.getId());
        }
        
        List<Trainer> trainers = trainerDao.getAllTrainers();
        for(Trainer trainer : trainers) {
            trainerDao.deleteTrainerById(trainer.getId());
        }
    }
    
    @Test
    public void testAddAndGetWorkout() {
        Workout workout = new Workout(1, "Test Name", "Test Target Muscle", "Test Equipment");
        workout = workoutDao.addWorkout(workout);
        
        
        Workout fromDao = workoutDao.getWorkoutById(workout.getId());
        
        assertEquals(workout, fromDao);
    }

    @Test
    public void testGetAllworkouts() {
        Workout workout = new Workout(1, "Test Name", "Test Target Muscle", "Test Equipment");
        workout = workoutDao.addWorkout(workout);
        
        Workout workout2 = new Workout(2, "Test Name 2", "Test Target Muscle 2", "Test Equipment 2");
        workout2 = workoutDao.addWorkout(workout2);
        
        List<Workout> workouts = workoutDao.getAllWorkouts();
        
        assertEquals(2, workouts.size());
        assertTrue(workouts.contains(workout));
        assertTrue(workouts.contains(workout2));
    }

    @Test
    public void testUpdateworkout() {
        Workout workout = new Workout(1, "Test Name", "Test Target Muscle", "Test Equipment");
        workout = workoutDao.addWorkout(workout);
        
        Workout fromDao = workoutDao.getWorkoutById(workout.getId());
        assertEquals(workout, fromDao);
        
        workout.setName("New Test Name");
        workoutDao.updateWorkout(workout);
        
        assertNotEquals(workout, fromDao);
        
        fromDao = workoutDao.getWorkoutById(workout.getId());
        
        assertEquals(workout, fromDao);
    }

    @Test
    public void testDeleteworkoutById() {
        Workout workout = new Workout(1, "Test Name", "Test Target Muscle", "Test Equipment");
        workout = workoutDao.addWorkout(workout);
        
        Client client = new Client(1, "Test First Name", "Test Last Name", 23, 34, 90);
        client = clientDao.addClient(client);
        
        List<Client> clients = new ArrayList<>();
        clients.add(client);
        
        Trainer trainer = new Trainer();
        trainer.setFirst_name("Test First");
        trainer.setLastName("Test Last");
        trainer.setAge(44);
        trainer.setYears_of_experience(2);
        trainer.setIsAvailable(true);

        trainer.setWorkout(workout);
        trainer.setClients(clients);
        trainer = trainerDao.addTrainer(trainer);
        
        Workout fromDao = workoutDao.getWorkoutById(workout.getId());
        assertEquals(workout, fromDao);
        
        workoutDao.deleteWorkoutById(workout.getId());
        
        fromDao = workoutDao.getWorkoutById(workout.getId());
        assertNull(fromDao);
    }
    
}

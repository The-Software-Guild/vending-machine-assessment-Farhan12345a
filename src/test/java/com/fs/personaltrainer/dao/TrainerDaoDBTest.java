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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class TrainerDaoDBTest {
    
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    public TrainerDaoDBTest() {
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
    public void testAddAndGetCourse() {
        Workout workout = new Workout(1, "Test Name Workout", "Test Target Muscle", "Test Equipment");
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
        System.out.println("Workout Name: " + trainer.getWorkout());
        trainer.setClients(clients);
        
        Workout workout2 = trainer.getWorkout();
        
        System.out.println("Workout 2 ID: " + workout2.getId());
        Workout dao = workoutDao.getWorkoutById(workout2.getId());
        
        trainer = trainerDao.addTrainer(trainer);
        
        System.out.println(trainer.toString());
        Trainer fromDao = trainerDao.getTrainerById(trainer.getId());
        //System.out.println(fromDao.toString());
        //FIX BELOW
        assertEquals(trainer.getId(), fromDao.getId());
       // assertEquals(trainer.getId(), fromDao.getId());
    }

    @Test
    public void testGetAllCourses() {
        //First Trainer
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
        
        //Second Trainer
        Trainer trainer2 = new Trainer();
        trainer2.setFirst_name("Test First 2");
        trainer2.setLastName("Test Last 2");
        trainer2.setAge(21);
        trainer2.setYears_of_experience(1);
        trainer2.setIsAvailable(true);

        trainer2.setWorkout(workout);
        trainer2.setClients(clients);
        trainer2 = trainerDao.addTrainer(trainer2);
        
        List<Trainer> trainers = trainerDao.getAllTrainers();
        assertEquals(2, trainers.size());
        //FIX BELOW
//        assertTrue(trainers.contains(trainer));
//        assertTrue(trainers.contains(trainer2));
        
    }
//
    @Test
    public void testUpdateCourse() {
        //First Trainer
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
        
        Trainer fromDao = trainerDao.getTrainerById(trainer.getId());
        assertEquals(trainer.getId(), fromDao.getId());
        
        trainer.setFirst_name("New Test Course Name");
        
        Client client2 = new Client(1, "Test First Name 2", "Test Last Name 2", 23, 34, 90);
        client2 = clientDao.addClient(client2);
        
        clients.add(client2);
        trainer.setClients(clients);
        
        trainerDao.updateTrainer(trainer);
        
        assertNotEquals(trainer, fromDao);
        
        fromDao = trainerDao.getTrainerById(trainer.getId());
        assertEquals(trainer.getId(), fromDao.getId());
    }
//
    @Test
    public void testDeleteTrainerById() {
         //First Trainer
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
        
        Trainer fromDao = trainerDao.getTrainerById(trainer.getId());
        assertEquals(trainer.getId(), fromDao.getId());
        
        trainerDao.deleteTrainerById(trainer.getId());
        
        fromDao = trainerDao.getTrainerById(trainer.getId());
        assertNull(fromDao);
    }

    
}

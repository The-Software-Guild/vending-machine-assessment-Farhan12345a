/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.entities.Trainer;
import com.fs.personaltrainer.entities.Workout;
import com.fs.personaltrainer.entities.Client;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author farhanshahbaz
 */
@SpringBootTest
public class ClientDaoDBTest {
    
   
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    
    public ClientDaoDBTest() {
        
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
    public void testAddAndGetClient() {
        Client client = new Client(1, "Test First Name", "Test Last Name", 23, 34, 90);
        client = clientDao.addClient(client);
        
        Client fromDao = clientDao.getClientById(client.getId());
        
        assertEquals(client, fromDao);
    }

    @Test
    public void testGetAllworkouts() {
        Client client = new Client(1, "Test First Name", "Test Last Name", 23, 34, 90);
        client = clientDao.addClient(client);
        
        Client client2 = new Client(1, "Test First Name 2", "Test Last Name 2", 23, 100, 99);
        client2 = clientDao.addClient(client2);
        
        List<Client> clients = clientDao.getAllClients();
        
        assertEquals(2, clients.size());
        assertTrue(clients.contains(client));
        assertTrue(clients.contains(client2));
    }

    @Test
    public void testUpdateworkout() {
        Client client = new Client(1, "Test First Name", "Test Last Name", 23, 34, 90);
        client = clientDao.addClient(client);
        
        Client fromDao = clientDao.getClientById(client.getId());
        assertEquals(client, fromDao);
        
        client.setFirstName("New Test First");
        clientDao.updateClient(client);
        
        assertNotEquals(client, fromDao);
        
        fromDao = clientDao.getClientById(client.getId());
        
        assertEquals(client, fromDao);
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
        
        Client fromDao = clientDao.getClientById(client.getId());
        assertEquals(client, fromDao);
        
        clientDao.deleteClientById(client.getId());
        
        fromDao = clientDao.getClientById(workout.getId());
        assertNull(fromDao);
    }
  
    
    
}

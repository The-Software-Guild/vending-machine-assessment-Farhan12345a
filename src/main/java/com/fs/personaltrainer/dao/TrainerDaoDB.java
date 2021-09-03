/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.dao.ClientDaoDB.ClientMapper;
import com.fs.personaltrainer.dao.WorkoutDaoDB.WorkoutMapper;
import com.fs.personaltrainer.entities.Client;
import com.fs.personaltrainer.entities.Trainer;
import com.fs.personaltrainer.entities.Workout;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author farhanshahbaz
 */
@Repository
public class TrainerDaoDB implements TrainerDao {

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Trainer getTrainerById(int id) {
        try {
            final String SELECT_TRAINER_BY_ID = "SELECT * FROM trainer WHERE trainer_id = ?";
            Trainer trainer = jdbc.queryForObject(SELECT_TRAINER_BY_ID, new TrainerMapper(), id);
            trainer.setWorkout(getWorkoutForTrainer(id));
            //trainer.setClients(getClientsForTrainer(id));
            return trainer;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    private Workout getWorkoutForTrainer(int id) {
        final String SELECT_WORKOUT_FOR_TRAINER = "SELECT w.* FROM workout w "
                + "JOIN trainer t ON t.workout_id = w.workout_id WHERE t.trainer_id = ?";
        return jdbc.queryForObject(SELECT_WORKOUT_FOR_TRAINER, new WorkoutMapper(), id);
    }
    
    private List<Client> getClientsForTrainer(int id) {
        final String SELECT_CLIENTS_FOR_TRAINER = "SELECT c.* FROM client c "
                + "JOIN trainer_client tc ON tc.client_id = c.client_id WHERE tc.trainerId = ?";
        return jdbc.query(SELECT_CLIENTS_FOR_TRAINER, new ClientMapper(), id);
    }

    @Override
    public List<Trainer> getAllTrainers() {
        final String SELECT_ALL_TRAINERS = "SELECT * FROM trainer";
        List<Trainer> trainers = jdbc.query(SELECT_ALL_TRAINERS, new TrainerMapper());
        //associateTeacherAndStudents(trainers);
        return trainers;
    }
    
    private void associateTeacherAndStudents(List<Trainer> trainers) {
        for (Trainer trainer : trainers) {
            trainer.setWorkout(getWorkoutForTrainer(trainer.getId()));
            trainer.setClients(getClientsForTrainer(trainer.getId()));
        }
    }

    //Additional methods
//    @Override
//    public List<trainer> getTrainersForTeacher(Teacher teacher) {
//        final String SELECT_trainerS_FOR_TEACHER = "SELECT * FROM trainer WHERE teacherId = ?";
//        List<trainer> trainers = jdbc.query(SELECT_trainerS_FOR_TEACHER, 
//                new trainerMapper(), teacher.getId());
//        associateTeacherAndStudents(trainers);
//        return trainers;
//    }
//    
//     @Override
//    public List<trainer> gettrainersForStudent(Student student) {
//        final String SELECT_trainerS_FOR_STUDENT = "SELECT c.* FROM trainer c JOIN "
//                + "trainer_student cs ON cs.trainerId = c.Id WHERE cs.studentId = ?";
//        List<trainer> trainers = jdbc.query(SELECT_trainerS_FOR_STUDENT, 
//                new trainerMapper(), student.getId());
//        associateTeacherAndStudents(trainers);
//        return trainers;
//    }
    
    @Override
    @Transactional
    public Trainer addTrainer(Trainer trainer) {
        final String INSERT_TRAINER = "INSERT INTO trainer (first_name, last_name, age, years_of_experience, isAvailable, workout_id) "
                + "VALUES(?,?,?,?,?,?)";
        jdbc.update(INSERT_TRAINER,
                trainer.getFirst_name(),
                trainer.getLastName(),
                trainer.getAge(),
                trainer.getYears_of_experience(),
                trainer.isIsAvailable(),
                trainer.getWorkout().getId());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        trainer.setId(newId);
        //might not be neccasry
        //insertTrainerClient(trainer);
        return trainer;

    }

    private void insertTrainerClient(Trainer trainer) {
        final String INSERT_TRAINER_CLIENT = "INSERT INTO "
                + "trainer_client(trainer_id, client_id) VALUES(?,?)";
        for(Client client : trainer.getClients()) {
            jdbc.update(INSERT_TRAINER_CLIENT, 
                    trainer.getId(),
                    client.getId());
        }
    }
    
    @Override
    @Transactional
    public void updateTrainer(Trainer trainer) {
        final String UPDATE_TRAINER = "UPDATE trainer SET first_name = ?, last_name = ?, "
                + "age = ?, years_of_experience = ?, isAvailable = ?, workout_id = ? WHERE trainer_id = ?";
        jdbc.update(UPDATE_TRAINER, 
                trainer.getFirst_name(), 
                trainer.getLastName(), 
                trainer.getAge(), 
                trainer.getYears_of_experience(), 
                trainer.isIsAvailable(), 
                trainer.getWorkout().getId(),
                trainer.getId());
        
        final String DELETE_TRAINER_CLIENT = "DELETE FROM trainer_client WHERE trainer_id = ?";
        jdbc.update(DELETE_TRAINER_CLIENT, trainer.getId());
        insertTrainerClient(trainer);
    }

    @Override
    @Transactional
    public void deleteTrainerById(int id) {
        final String DELETE_TRAINER_CLIENT = "DELETE FROM trainer_client WHERE trainer_id = ?";
        jdbc.update(DELETE_TRAINER_CLIENT, id);
        
        final String DELETE_TRAINER = "DELETE FROM trainer WHERE trainer_id = ?";
        jdbc.update(DELETE_TRAINER, id);
    }

    public static final class TrainerMapper implements RowMapper<Trainer> {

        @Override
        public Trainer mapRow(ResultSet rs, int index) throws SQLException {
            Trainer trainer = new Trainer();
            trainer.setId(rs.getInt("trainer_id"));
            trainer.setFirst_name(rs.getString("first_name"));
            trainer.setLastName(rs.getString("last_name"));
            trainer.setAge(rs.getInt("age"));
            trainer.setYears_of_experience(rs.getInt("years_of_experience"));
            trainer.setIsAvailable(rs.getBoolean("isAvailable"));
            
            return trainer;
        }
    }
    
}

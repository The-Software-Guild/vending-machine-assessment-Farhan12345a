/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

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
public class WorkoutDaoDB implements WorkoutDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Workout getWorkoutById(int id) {
        try {
            final String SELECT_workout_BY_ID = "SELECT * FROM workout WHERE workout_id = ?";
            return jdbc.queryForObject(SELECT_workout_BY_ID, new WorkoutMapper(), id);
        } catch (DataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Workout> getAllWorkouts() {
        final String SELECT_ALL_WORKOUTS = "SELECT * FROM workout";
        return jdbc.query(SELECT_ALL_WORKOUTS, new WorkoutMapper());
    }

    @Override
    @Transactional
    public Workout addWorkout(Workout workout) {
        final String INSERT_WORKOUT = "INSERT INTO workout(name, target_muscle, equipment) "
                + "VALUES(?,?,?)";
        jdbc.update(INSERT_WORKOUT,
                workout.getName(),
                workout.getTarget_muscle(),
                workout.getEquipment());
              
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        workout.setId(newId);
        return workout;
    }

    @Override
    public void updateWorkout(Workout workout) {
        final String UPDATE_WORKOUT = "UPDATE workout SET name = ?, target_muscle = ?, equipment = ? "
                + "WHERE workout_id = ?";
        jdbc.update(UPDATE_WORKOUT,
                workout.getName(),
                workout.getTarget_muscle(),
                workout.getEquipment(),
                workout.getId());
    }

    @Override
    public void deleteWorkoutById(int id) {
        final String DELETE_COURSE_STUDENT = "DELETE cs.* FROM trainer_client cs "
                + "JOIN trainer c ON cs.trainer_id = c.trainer_id WHERE c.workout_id = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
        final String DELETE_TRAINER_WORKOUT = "DELETE FROM trainer WHERE workout_id = ?";
        jdbc.update(DELETE_TRAINER_WORKOUT, id);
        
        final String DELETE_WORKOUT = "DELETE FROM workout WHERE workout_id = ?";
        jdbc.update(DELETE_WORKOUT, id);
    }
    
    public static final class WorkoutMapper implements RowMapper<Workout> {

        @Override
        public Workout mapRow(ResultSet rs, int index) throws SQLException {
            Workout workout = new Workout();
            workout.setId(rs.getInt("workout_id"));
            workout.setName(rs.getString("name"));
            workout.setTarget_muscle(rs.getString("target_muscle"));
            workout.setEquipment(rs.getString("equipment"));

            return workout;
        }
    }
    
}

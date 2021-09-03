/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.service;

import com.fs.personaltrainer.dao.WorkoutDao;
import com.fs.personaltrainer.entities.CategoryAPI;
import com.fs.personaltrainer.entities.ExcerciseInfoAPI;
import com.fs.personaltrainer.entities.ResultsAPI;
import com.fs.personaltrainer.entities.Workout;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
  

/**
 *
 * @author farhanshahbaz
 */
@Component
public class ServiceDB implements ServiceLayer{

    
    @Autowired
    WorkoutDao workoutDao;
    
    
            
    //Getting API Key
    @Value("{api.key}")
    private String apiKey;
    
    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public Workout getWorkoutById(int id) {
        return workoutDao.getWorkoutById(id);
    }

    @Override
    public List<Workout> getAllWorkouts() {
        return workoutDao.getAllWorkouts();
    }

    @Override
    public Workout addWorkout(Workout workout) {
        return workoutDao.addWorkout(workout);
    }

    @Override
    public void updateWorkout(Workout workout) {
        workoutDao.updateWorkout(workout);
    }

    @Override
    public void deleteWorkoutById(int id) {
        workoutDao.deleteWorkoutById(id);
    }
    
    //API CALL
    //Side Note: api/v2/exerciseiinfo/<id>/ : A convenience function that returns all the information about an exercise, including, muscles, equipment, etc. This can be used to avoid having to call the other endpoints individually.
//    @Override
    public List <ResultsAPI> getResultsAPI(int id) {
        ExcerciseInfoAPI excerciseAPI = new ExcerciseInfoAPI();
        String url = "https://wger.de/api/v2/exerciseinfo/?format=json";
        ExcerciseInfoAPI eIAPI = restTemplate.getForObject(url, ExcerciseInfoAPI.class);
        
        List <ResultsAPI> result = eIAPI.getResults();
        //System.out.println("RESULT: " + result.toString());
        return result;

    }
    

    @Override
    public CategoryAPI getCategoryAPI() {
        ResultsAPI resultAPI = new ResultsAPI();
        String url = "https://wger.de/api/v2/exerciseinfo/?format=json";
        ResultsAPI rIAPI = restTemplate.getForObject(url, ResultsAPI.class);
        
        System.out.println(rIAPI.toString());
        CategoryAPI category = rIAPI.getCategory();
        //System.out.println("RESULT: " + category.toString());
        return category;
    }
    
    //    public CategoryAPI getCategoryAPI(int id) {
//        ResultsAPI resultAPI = new ResultsAPI();
//        String url = "https://wger.de/api/v2/exerciseinfo/?format=json";
//        ResultsAPI rIAPI = restTemplate.getForObject(url, ResultsAPI.class);
//        
//        System.out.println(rIAPI.toString());
//        CategoryAPI category = rIAPI.getCategory();
//        System.out.println("RESULT: " + category.toString());
//        return category;
//
//    }


    //Gets category from results
//    @Override
//    public List<CategoryAPI> getCategoryAPI() {
//        ExcerciseInfoAPI excerciseAPI = new ExcerciseInfoAPI();
//        ResultsAPI resultsAPI = new ResultsAPI();
//        String url = "https://wger.de/api/v2/exerciseinfo/";
//        
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<List<CategoryAPI>> response = restTemplate.exchange(
//          url,
//          HttpMethod.GET,
//          null,
//          new ParameterizedTypeReference<List<ResultsAPI>>(){});
//        List<CategoryAPI> employees = response.getBody();
//        return employees;
//    }
    
    
}


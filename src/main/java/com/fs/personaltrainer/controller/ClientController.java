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
public class ClientController {
    
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    TrainerDao trainerDao;
    
    @Autowired
    WorkoutDao workoutDao;
    
    @GetMapping("clients")
    public String displayClients(Model model) {
        List<Client> client = clientDao.getAllClients();
        model.addAttribute("clients", client);
        return "clients";
    }
    
    @PostMapping("addClient")
    public String addworkout(HttpServletRequest request) {
         String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        
        
        Client client = new Client();
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAge(age);
        client.setHeight(height);
        client.setWeight(weight);
        
        clientDao.addClient(client);
        
        return "redirect:/clients";
    }
//    
    @GetMapping("deleteClient")
    public String deleteworkout(HttpServletRequest request) {
        int id = Integer.parseInt(request.getParameter("id"));
        clientDao.deleteClientById(id);
        
        return "redirect:/clients";
    }
//    
//    
    @GetMapping("editClient")
    public String editClient(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = clientDao.getClientById(id);
        
        model.addAttribute("clients", client);
        return "editClient";
    }
    
    @PostMapping("editClient")
    public String perfromEditClient(HttpServletRequest request, Model model) {
        int id = Integer.parseInt(request.getParameter("id"));
        Client client = clientDao.getClientById(id);
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        int age = Integer.parseInt(request.getParameter("age"));
        int height = Integer.parseInt(request.getParameter("height"));
        int weight = Integer.parseInt(request.getParameter("weight"));
        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setAge(age);
        client.setHeight(height);
        client.setWeight(weight);
       clientDao.updateClient(client);
        return "redirect:/client";
    }
    
    
}

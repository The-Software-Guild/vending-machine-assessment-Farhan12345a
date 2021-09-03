/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.entities.Client;
import java.util.List;

/**
 *
 * @author farhanshahbaz
 */
public interface ClientDao {
    Client getClientById(int id);
    List<Client> getAllClients();
    Client addClient(Client client);
    void updateClient(Client client);
    void deleteClientById(int id);
    
}


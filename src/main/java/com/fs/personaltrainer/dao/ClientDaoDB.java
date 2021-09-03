/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fs.personaltrainer.dao;

import com.fs.personaltrainer.entities.Client;
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
public class ClientDaoDB implements ClientDao{
   @Autowired
   JdbcTemplate jdbc;

   @Override
   public Client getClientById(int id) {
       try {
            final String GET_CLIENT_BY_ID = "SELECT * FROM client WHERE client_id = ?";
            return jdbc.queryForObject(GET_CLIENT_BY_ID, new ClientMapper(), id);
        } catch(DataAccessException ex) {
            return null;
        }
   }

   @Override
   public List<Client> getAllClients() {
        final String GET_ALL_CLIENTS = "SELECT * FROM client";
        return jdbc.query(GET_ALL_CLIENTS, new ClientMapper());
   }

   @Override
   @Transactional
   public Client addClient(Client client) {
       final String INSERT_CLIENT = "INSERT INTO client(first_name, last_name, age, height, weight) " +
                "VALUES(?,?,?,?,?)";
        jdbc.update(INSERT_CLIENT,
                client.getFirstName(),
                client.getLastName(),
                client.getAge(),
                client.getHeight(),
                client.getWeight());
        
        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        client.setId(newId);
        return client;
   }

   @Override
   public void updateClient(Client client) {
       final String UPDATE_client = "UPDATE client SET first_name = ?, last_name = ?, " +
                "age = ?, height = ?, weight = ? WHERE client_id = ?";
        jdbc.update(UPDATE_client,
                client.getFirstName(),
                client.getLastName(),
                client.getAge(),
                client.getHeight(),
                client.getWeight(),
                client.getId());
   }

   //LOOK OVER
   @Override
   @Transactional
   public void deleteClientById(int id) {
       final String DELETE_COURSE_STUDENT = "DELETE cs.* FROM trainer_client cs "
                + "JOIN client c ON cs.client_id = c.client_id WHERE c.client_id = ?";
        jdbc.update(DELETE_COURSE_STUDENT, id);
        
//        final String DELETE_COURSE = "DELETE FROM course WHERE clientId = ?";
//        jdbc.update(DELETE_COURSE, id);
        
        final String DELETE_client = "DELETE FROM client WHERE client_id = ?";
        jdbc.update(DELETE_client, id);
   }
   
   public static final class ClientMapper implements RowMapper<Client> {

        @Override
        public Client mapRow(ResultSet rs, int index) throws SQLException {
            Client client = new Client();
            client.setId(rs.getInt("client_id"));
            client.setFirstName(rs.getString("first_name"));
            client.setLastName(rs.getString("last_name"));
            client.setAge(rs.getInt("age"));
            client.setHeight(rs.getInt("height"));
            client.setWeight(rs.getInt("weight"));
            
            return client;
        }
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;


import com.mismarcasderuning.model.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jesus Cruz
 */
public interface UserDAO {
    public boolean insert(User user, Manager manager);
    public boolean update(User user, Manager manager);
    public boolean delete(User user, Manager manager);
    public User read(int id, Manager manager) throws SQLException;
    public ArrayList<User> readAll(Manager manager) throws SQLException;
}

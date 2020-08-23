/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;



import com.mismarcasderuning.model.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Jesus Cruz
 */
public class UserSQL implements UserDAO{

    public boolean insert(User user, Manager manager) {
        
        SimpleDateFormat dMy = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQLSentence = "INSERT INTO USERS VALUES(" +
                user.getId() + ",\"" +
                user.getUsername() + "\",\"" +
                user.getPassword() + "\",\"" +
                user.getEmail() + "\",\"" +
                user.getName() + "\",\"" +
                user.getSurnames() + "\"," +
                user.getMobile() + ",\"" +
                dMy.format(user.getBirthDate().getTime()) + "\"," + 
                user.getToken() + "," +
                user.isVerified() + "," +
                user.isLoggedIn() + ");";
        
        System.out.println(SQLSentence);
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    public boolean update(User user, Manager manager) {
        SimpleDateFormat dMy = new SimpleDateFormat("yyyy-MM-dd");
        
        String SQLSentence = "UPDATE USERS SET " +
                "username=\"" + user.getUsername() + "\"," +
                "password=\"" + user.getPassword() + "\"," +
                "email=\"" + user.getEmail() + "\"," +
                "name=\"" + user.getName() + "\"," +
                "surnames=\"" + user.getSurnames() + "\"," +
                "mobile=" + user.getMobile() + "," +
                "birthDate=\"" + dMy.format(user.getBirthDate().getTime()) + "\"," +
                "verified=" + user.isVerified() + "," +
                "loggedIn=" + user.isLoggedIn() + " " +
                "WHERE id=" + user.getId() + ";";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }   
    }

    public boolean delete(User user, Manager manager) {
    
        String SQLSentence = "DELETE FROM USERS WHERE id = " + user.getId() + ";";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }   
    }
    
    public User read(int id, Manager manager) throws SQLException {
        User user = null;
        
        String SQLSentence = "SELECT * FROM USERS WHERE id = " + id;
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
            
            try (ResultSet result = prepStmt.executeQuery()){
                if (result.next()) {
                    Calendar birthDate = Calendar.getInstance();
                    
                    birthDate.setTime(result.getDate("birthDate"));
                    
                    user = new User(id,
                            result.getString("username"),
                            result.getString("password"),
                            result.getString("email"),
                            result.getString("name"),
                            result.getString("surnames"),
                            result.getLong("mobile"),
                            birthDate,
                            result.getInt("token"),
                            result.getBoolean("verified"),
                            result.getBoolean("loggedIn"));
                }
            }
        } catch (SQLException e) {
         throw e;
        }
        
        return user;
    }

    
    public ArrayList<User> readAll(Manager manager) throws SQLException {
        
        ArrayList<User> users = new ArrayList<>();
        
        String SQLSentence = "SELECT * FROM USERS;";
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
            
            try (ResultSet result = prepStmt.executeQuery()) {
                while (result.next()) {
                    Calendar birthDate = Calendar.getInstance();
                   
                    birthDate.setTime(result.getDate("birthDate"));
                   
                    users.add(
                           new User(result.getInt("id"),
                                   result.getString("username"),
                                   result.getString("password"),
                                   result.getString("email"),
                                   result.getString("name"),
                                   result.getString("surnames"),
                                   result.getLong("mobile"),
                                   birthDate,
                                   result.getInt("token"),
                                   result.getBoolean("verified"),
                                   result.getBoolean("loggedIn")));
                }
            } 
        } catch (SQLException e) {
            throw e;
        }
        
        return users;
    }

    public int getNumberOfUsers(Manager manager) throws SQLException {
        int n = 0;
        
        String SQLSentence = "SELECT COUNT(*) AS n FROM USERS;";

        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);

            try (ResultSet result = prepStmt.executeQuery()) {
                if (result.next()) {
                    n = result.getInt("n");
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return n;
    }

    public int getLoggedUsers(Manager manager) throws SQLException {
        int n = 0;
        
        String SQLSentence = "SELECT COUNT(*) AS n FROM USERS WHERE loggedIn = 1;";

        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);

            try (ResultSet result = prepStmt.executeQuery()) {
                if (result.next()) {
                   n = result.getInt("n");
                }
            }
        } catch (SQLException e) {
            throw e;
        }

        return n;
    }
}

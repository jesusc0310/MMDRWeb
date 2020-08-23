/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;


import com.mismarcasderuning.model.Run;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Jesus Cruz
 */
public class RunSQL implements RunDAO {
    
    
    public boolean insert(Run run, Manager manager) {
        
        String SQLSentence = "INSERT INTO RUNS VALUES(" +
                run.getId() + ",\"" +
                run.getName() + "\",\"" +
                run.getPlace() + "\",\"" +
                run.getWeb() + "\"," +
                run.getDistance() + ",\"" +
                run.getPhoto() + "\");";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean update(Run run, Manager manager) {
        
        
       String SQLSentence = "UPDATE RUNS SET " +
               "name=\"" + run.getName() + "\"," +
               "place=\"" + run.getPlace() + "\"," +
               "web=\"" + run.getWeb() + "\"," +
               "distance=" + run.getDistance() + "," + 
               "photo=\"" + run.getPhoto() + "\" " +
               "WHERE id=" + run.getId() + ";";
       
       try (Statement stmt = manager.getConn().createStatement()) {
           stmt.executeUpdate(SQLSentence);
           return true;
       } catch (SQLException e) {
           return false;
       }
    }
    
    
    public boolean delete (Run run, Manager manager) {
        
        String SQLSentence = "DELETE FROM RUNS WHERE id = " + run.getId() + ";";
        
        try (Statement stmt = manager.getConn().createStatement()) {
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public Run read(int id, Manager manager) throws SQLException {
        
        Run run = null;
        
        String SQLSentence = "SELECT * FROM RUNS WHERE id = " + id + ";";
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
            
            try (ResultSet result = prepStmt.executeQuery()) {
                if (result.next()) {
                    run = new Run(id,
                            result.getString("name"),
                            result.getString("place"),
                            result.getString("web"),
                            result.getFloat("distance"),
                            result.getString("photo"));
                }
            }
        }  catch (SQLException e) {
            throw e;
        }
        
        return run;
    }
    
    
    public ArrayList<Run> readAll(Manager manager) throws SQLException {
        
        ArrayList<Run> runs = new ArrayList<>();
        
        String SQLSentence = "SELECT * FROM RUNS";
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
            
            try (ResultSet result = prepStmt.executeQuery()) {
                while (result.next()) {
                    runs.add(
                            new Run(result.getInt("id"),
                                    result.getString("name"),
                                    result.getString("place"),
                                    result.getString("web"),
                                    result.getFloat("distance"),
                                    result.getString("photo")));
                }
            }
        }  catch (SQLException e) {
            throw e;
        }
        
        return runs;
    }
}

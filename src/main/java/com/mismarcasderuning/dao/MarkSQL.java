/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mismarcasderuning.dao;

import com.mismarcasderuning.model.Mark;
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
public class MarkSQL implements MarkDAO {
    
    
    public boolean insert(Mark mark, Manager manager) {
    
        SimpleDateFormat hms = new SimpleDateFormat("hh:mm:ss");
        
        String SQLSentence = "INSERT INTO MARKS VALUES(" +
                mark.getId() + "," +
                mark.getYear() + ",\"" +
                hms.format(mark.getTime().getTime()) + "\"," +
                mark.getGeneralRanking() + "," +
                mark.getCategoryRanking() + "," +
                mark.isOpen() + "," +
                mark.getRunId() + "," +
                mark.getUserId() + ");";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    public boolean update(Mark mark, Manager manager) {
        
        SimpleDateFormat hms = new SimpleDateFormat("hh:mm:ss");
        
        String SQLSentence = "UPDATE MARKS SET " +
                "markYear=" + mark.getYear() + "," +
                "markTime=\"" + hms.format(mark.getTime().getTime()) + "\"," +
                "generalRanking=" + mark.getGeneralRanking() + "," +
                "categoryRanking=" + mark.getCategoryRanking() + "," +
                "isOpen=" + mark.isOpen() + "," +
                "runId=" + mark.getRunId() + "," +
                "userId=" + mark.getUserId() + " " +
                "WHERE id=" + mark.getId() + ";";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }
    
    
    
    public boolean delete(Mark mark, Manager manager) {
        
        String SQLSentence = "DELETE FROM MARKS WHERE id = " + mark.getId() + ";";
        
        try (Statement stmt = manager.getConn().createStatement()){
            stmt.executeUpdate(SQLSentence);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

    
    public Mark read(int id, Manager manager) throws SQLException {
        
        Mark mark = null;
        
        String SQLSentence = "SELECT * FROM MARKS WHERE id = " + id + ";";
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
            
            try (ResultSet result = prepStmt.executeQuery()) {
                if (result.next()) {
                    Calendar time = Calendar.getInstance();
                    
                    time.setTime(result.getDate("markTime"));
                    
                    mark = new Mark(id,
                           result.getInt("markYear"),
                           time,
                           result.getInt("generalRanking"),
                           result.getInt("categoryRanking"),
                           result.getBoolean("isOpen"),
                           result.getInt("runId"),
                           result.getInt("userId"));
                }
            }   
        } catch (SQLException e) {
            throw e;
        }
        
        return mark;
    }
    
        
    public ArrayList<Mark> readAll(Manager manager) throws SQLException {

        ArrayList<Mark> marks = new ArrayList<>();

        String SQLSentence = "SELECT * FROM MARKS;";

        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);

            try (ResultSet result = prepStmt.executeQuery()) {
                while (result.next()) {
                    Calendar time = Calendar.getInstance();

                    time.setTime(result.getDate("markTime"));

                     marks.add(
                             new Mark(result.getInt("id"),
                                     result.getInt("markYear"),
                                     time,
                                     result.getInt("generalRanking"),
                                     result.getInt("categoryRanking"),
                                     result.getBoolean("isOpen"),
                                     result.getInt("runId"),
                                     result.getInt("userId")));
                }
            } 
        } catch (SQLException e) {
            throw e;
        }

        return marks;
    }

    
    public int getNumberOfMarks (Manager manager) throws SQLException {
            
            int n = -1;
        
           String SQLSentence = "SELECT COUNT(*) AS n FROM MARKS;";

        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);

            try (ResultSet result = prepStmt.executeQuery()) {
                if (result.next()) n = result.getInt("n");
            }
        } catch (SQLException e) {
            throw e;
        }

        return n;
    }
    
    
    public ArrayList<Mark> readUserMarks(int userId, Manager manager) throws SQLException {
        
        ArrayList<Mark> marks = new ArrayList<>();
        
        String SQLSentence = "SELECT * FROM MARKS WHERE userId=" + userId;
        
        try {
            PreparedStatement prepStmt = manager.getConn().prepareStatement(SQLSentence);
        try (ResultSet result = prepStmt.executeQuery()) {
                do {
                    Calendar time = Calendar.getInstance();

                    time.setTime(result.getDate("markTime"));

                     marks.add(
                             new Mark(result.getInt("id"),
                                     result.getInt("markYear"),
                                     time,
                                     result.getInt("generalRanking"),
                                     result.getInt("categoryRanking"),
                                     result.getBoolean("isOpen"),
                                     result.getInt("runId"),
                                     result.getInt("userId")));
                } while (result.next());
            } 
        } catch (SQLException e) {
            throw e;
        }

        return marks;
    }

}
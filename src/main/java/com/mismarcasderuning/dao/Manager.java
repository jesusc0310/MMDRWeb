/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Jesus Cruz
 */
public class Manager {
    private Connection conn;
    private final String URL;
    private final String USER;
    private final String PASS;
    private static Manager singleton;
    
    private Manager() {
        conn = null;
        URL = "jdbc:mysql://192.168.99.100/mmdr?allowPublicKeyRetrieval=true&autoReconnect=true&useSSL=false";
        USER = "root";
        PASS = "root";
    }
    
    public static Manager getInstance(){
        if (singleton == null){
            return singleton = new Manager();
        } else return null;
    }
    
    public Connection getConn() {
        return conn;
    }
    
    public void open() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            throw e;
        }
    }
    
    public void close() throws SQLException {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw e;
        }
    }
    
    public void resetInstance() {
        singleton = null;
    }
}

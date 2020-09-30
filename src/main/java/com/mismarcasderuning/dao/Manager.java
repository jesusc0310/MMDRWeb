/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

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

        Properties prop = new Properties();
        
        try {
            prop.load(new FileReader("C:\\Users\\jesus\\Desktop\\DAM\\1DAM\\Programacion\\MisMarcasDeRunning\\src\\main\\webapp\\config\\dbconfig.properties"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        conn = null;
        URL = prop.getProperty("URL");
        USER = prop.getProperty("USER");
        PASS = prop.getProperty("PASS");

    }

    public static Manager getInstance() {
        if (singleton == null) {
            return singleton = new Manager();
        } else {
            return singleton;
        }
    }
    
    public Connection getConn() {
        return conn;
    }

    public void open() throws ClassNotFoundException, SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(URL, USER, PASS);
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
}

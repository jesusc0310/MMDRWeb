/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.notifications;

/**
 *
 * @author Jesus Cruz
 */
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;

public class Telegram {
    private String id;
    private String botUrl;

    // Constructor;

    public Telegram() {
        
        Properties prop = new Properties();
        
        try {
            prop.load(new FileReader("C:\\Users\\jesus\\Desktop\\DAM\\1DAM\\Programacion\\MisMarcasDeRunning\\src\\main\\webapp\\config\\infoTelegram.properties"));
        } catch (IOException e){
        }
        
        this.id = prop.getProperty("ID");
        this.botUrl = prop.getProperty("BOTURL");
    }


    // Getter;

    public String getId() {
        return id;
    }

    //Setter;

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Method that create a connection via Telegram between the bot and 
     * the admin.
     *
     * @param message A String that contains the message you want to send.
     * 
     * @return It returns true if there wasn't any problem, else returns false
     */
    public boolean send(String message) {

        String address = botUrl + id + "&text=" + message;

        URL url;

        try {
            url = new URL(address);
            URLConnection conn = url.openConnection();
            InputStream in = new BufferedInputStream(conn.getInputStream());
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}

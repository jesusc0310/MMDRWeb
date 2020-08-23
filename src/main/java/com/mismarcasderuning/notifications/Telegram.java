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
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Telegram {
    private int id;
    private String botUrl;

    // Constructor;

    public Telegram(int id, String botUrl) {
        this.id = id;
        this.botUrl = botUrl;
    }


    // Getter;

    public int getId() {
        return id;
    }

    //Setter;

    public void setId(int id) {
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
        boolean input = false;

        try {
            url = new URL(address);
            URLConnection connection = url.openConnection();
            BufferedReader in = new BufferedReader((new InputStreamReader(connection.getInputStream())));
            input = true;
        } catch (IOException e) {
            input = false;
        }
        return input;
    }
}

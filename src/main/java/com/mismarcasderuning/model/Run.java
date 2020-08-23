/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.model;

/**
 *
 * @author Jesus Cruz
 */
public class Run {
    
    private final int id;
    private String name;
    private String place;
    private String web;
    private float distance;
    private String photo;
    
    private static int nRuns = 0;

    public Run(String name, String place, String web, float distance, String photo) {
        id = nRuns++;
        this.name = name;
        this.place = place;
        this.web = web;
        this.distance = distance;
        this.photo = photo;
    }

    public Run(int id, String name, String place, String web, float distance, String photo) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.web = web;
        this.distance = distance;
        this.photo = photo;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPlace() {
        return place;
    }

    public String getWeb() {
        return web;
    }

    public float getDistance() {
        return distance;
    }
    
    public String getPhoto(){
        return photo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
    
    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    public static void setNRuns(int i) {
        nRuns = i;
    }
}

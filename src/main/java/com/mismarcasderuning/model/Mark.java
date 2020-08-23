/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.model;

import java.util.Calendar;

/**
 *
 * @author Jesus Cruz
 */
public class Mark implements Comparable<Mark>{
    
    private final int id;
    private int year;
    private Calendar time;
    private int generalRanking;
    private int categoryRanking;
    private boolean open;
    private final int runId;
    private final int userId;
    
    private static int nMarks = 0;

    public Mark(int year, Calendar time, int generalRanking, int categoryRanking, boolean open, int runId, int userId) {
        id = nMarks++;
        this.year = year;
        this.time = time;
        this.generalRanking = generalRanking;
        this.categoryRanking = categoryRanking;
        this.open = open;
        this.runId = runId;
        this.userId = userId;
    }

    public Mark(int id, int year, Calendar time, int generalRanking, int categoryRanking, boolean open, int runId, int userId) {
        this.id = id;
        this.year = year;
        this.time = time;
        this.generalRanking = generalRanking;
        this.categoryRanking = categoryRanking;
        this.open = open;
        this.runId = runId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getYear() {
        return year;
    }

    public Calendar getTime() {
        return time;
    }

    public int getGeneralRanking() {
        return generalRanking;
    }

    public int getCategoryRanking() {
        return categoryRanking;
    }

    public boolean isOpen() {
        return open;
    }

    public int getRunId() {
        return runId;
    }

    public int getUserId() {
        return userId;
    }
    
    public static void setNMarks(int i) {
        nMarks = i;
    }

    @Override
    public int compareTo(Mark mark) {
        return time.getTime().compareTo(mark.getTime().getTime());
    }    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;


import com.mismarcasderuning.model.Run;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jesus Cruz
 */
public interface RunDAO {
    public boolean insert(Run run, Manager manager);
    public boolean update(Run run, Manager manager);
    public boolean delete(Run run, Manager manager);
    public Run read(int id, Manager manager) throws SQLException;
    public ArrayList<Run> readAll(Manager manager) throws SQLException;
}

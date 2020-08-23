/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.dao;

import com.mismarcasderuning.model.Mark;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jesus Cruz
 */
public interface MarkDAO {
    public boolean insert(Mark mark, Manager manager);
    public boolean update(Mark mark, Manager manager);
    public boolean delete(Mark mark, Manager manager);
    public Mark read(int id, Manager manager) throws SQLException;
    public ArrayList<Mark> readAll(Manager manager) throws SQLException;
}

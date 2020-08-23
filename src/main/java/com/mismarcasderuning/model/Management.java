/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.model;

import com.mismarcasderuning.dao.Manager;
import com.mismarcasderuning.dao.MarkSQL;
import com.mismarcasderuning.dao.RunSQL;
import com.mismarcasderuning.dao.UserSQL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Jesus Cruz
 */
public class Management {

    private final Manager manager;
    private final ArrayList<Mark> marks;
    private final ArrayList<User> users;
    private final ArrayList<Run> runs;

    private User currentUser;

    private final int loggedUsers;
    private final int nUsers;
    private static final int NONE = -1;

    // CONSTRUCTOR
    /**
     * Constructor de la clase controladora donde se instancian los objetos de
     * las clases del DAO para hacer la conexion a la base de datos.
     *
     * @throws ClassNotFoundException Lanza la excepción si no puede encontrar
     * la clase para abrir la conexion con la base de datos.
     *
     * @throws SQLException Lanza la excepción si encuentra algún error
     * sintáctico en el comando SQL.
     */
    public Management() throws ClassNotFoundException, SQLException {

        manager = Manager.getInstance();
        
        UserSQL userSQL = new UserSQL();
        RunSQL runSQL = new RunSQL();
        MarkSQL markSQL = new MarkSQL();

        manager.open();

        users = userSQL.readAll(manager);
        runs = runSQL.readAll(manager);
        marks = markSQL.readAll(manager);

        nUsers = userSQL.getNumberOfUsers(manager);
        loggedUsers = userSQL.getLoggedUsers(manager);

        User.setNUsers(users.size());
        Run.setNRuns(runs.size());
        Mark.setNMarks(marks.size());

        currentUser = null;

        manager.close();

        manager.resetInstance();
    }

    // GETTERS
    /**
     * Getter de la lista de usuarios guardados en la base de datos.
     *
     * @return Devuelve una lista de usuarios.
     */
    public ArrayList<User> getUsers() {

        return users;
    }

    /**
     * Getter de la lista de carreras guardadas en la base de datos.
     *
     * @return Devuelve una lista de carreras.
     */
    public ArrayList<Run> getRuns() {

        return runs;
    }

    /**
     * Getter de la lista de marcas guardadas en la base de datos.
     *
     * @return Devuelve una lista de marcas.
     */
    public ArrayList<Mark> getMarks() {

        return marks;
    }

    /**
     * Getter de un solo usuario guardado en la base de datos.
     *
     * @param id El id del usuario que se desea buscar.
     *
     * @return Devuelve el usuario si el id no es mayor que el tamaño del array
     * de usuarios, de otro modo devuelve null.
     */
    public User getUser(int id) {

        if (id <= users.size()) {
            return users.get(id);
        } else {
            return null;
        }
    }

    /**
     * Getter del usuario que actualmente tiene la sesión iniciada.
     *
     * @return devuelve el usuario que tiene la sesión iniciada, si no hay
     * ninguno devuelve un usuario con valor null.
     */
    public User getCurrentUser() {
        return currentUser;
    }

    /**
     * Getter de una sola carrera guardada en la base de datos.
     *
     * @param id El id de la carrera que se desea buscar.
     *
     * @return Devuelve la carrera si el id no es mayor que el tamaño del array
     * de carreras, de otro modo devuelve null.
     */
    public Run getRun(int id) {

        if (id <= runs.size()) {
            return runs.get(id);
        } else {
            return null;
        }
    }

    /**
     * Getter de una sola marca guardada en la base de datos.
     *
     * @param id El id de la marca que se desea buscar.
     *
     * @return Devuelve la marca si el id no es mayor que el tamaño del array de
     * marcas, de otro modo devuelve null.
     */
    public Mark getMark(int id) {

        if (id <= marks.size()) {
            return marks.get(id);
        } else {
            return null;
        }
    }

    /**
     * Getter de las marcas de un usuario concreto y una carrera concreta.
     *
     * @param userId Id del usuario
     * @param runId
     * @return
     */
    public Mark getMark(int userId, int runId) {

        for (Mark mark : marks) {
            if (mark.getRunId() == runId && mark.getUserId() == userId) {
                return mark;
            }
        }

        return null;
    }
    
    /**
     * Getter de todas las marcas que son publicas.
     * 
     * @return Devuelve una lista con todas las marcas publicas.
     */
    public ArrayList<Mark> getPublicMarks() {
        
        ArrayList<Mark> publicMarks = new ArrayList<>();
        
        for (Mark mark : marks) {
            if (mark.isOpen()) {
                publicMarks.add(mark);
            }
        }
        
        return publicMarks;
    }
    
    /**
     * Geeter de todas las marcas publicas de un usuario concreto
     * 
     * @param userId Id del usuario del cual queremos obtener las marcas.
     * 
     * @return Devuelve una lista con todas las marcas publicas de un usuario.
     */
    public ArrayList<Mark> getUserPublicMarks(int userId) {
        
        ArrayList<Mark> userPublicMarks = new ArrayList<>();
        
        for (Mark mark : marks) {
            if (mark.isOpen() && mark.getUserId() == userId) {
                userPublicMarks.add(mark);
            }
        }
        
        return userPublicMarks;
    }
    /**
     * Método que Devuelve el número de usuarios que están logueados.
     *
     * @return Devuelve el número de usuarios logueados.
     */
    public int getLoggedUsers() {

        return loggedUsers;
    }

    /**
     * Método que Devuelve el número de usuarios que están registrados.
     *
     * @return Devuelve el número de usuarios registrados.
     */
    public int getNumberOfUsers() {

        return nUsers;
    }

    // OTROS METODOS
    /**
     * Getter de un usuario a traves de su email.
     *
     * @param email El email del usuario que se desea buscar.
     *
     * @return Devuelve el id del usuario, en caso de no encontrarse devuelve
     * -1.
     *
     * @throws SQLException Lanza la excepción si encuentra algún error
     * sintáctico en el comando SQL.
     */
    public int getUserByEmail(String email) throws SQLException {

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user.getId();
            }
        }

        return NONE;
    }

    /**
     * Método para añadir un usuario en caso de que no exista.
     *
     * @param user Usuario que se desea añadir a la base de datos.
     *
     * @return Devuelve si la acción se ha realizado con exito o hubo algún
     * error durante el intento.
     *
     * @throws java.sql.SQLException Lanza dicha excepción en caso de que no se
     * pueda crear o cerrar la conexion con la base de datos.
     * @throws java.lang.ClassNotFoundException Lanza dicha excepción en caso de
     * que no encuentre la clase com.mysql.cj.jdbc.Driver.
     */
    public boolean addUser(User user) throws SQLException, ClassNotFoundException {

        manager.open();
        UserSQL userSQL = new UserSQL();

        if (getUser(user.getId()) == null) {
            if (userSQL.insert(user, manager)) {
                manager.close();
                return true;
            } else {
                manager.close();
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * Método para añadir una carrera en caso de que no exista.
     *
     * @param run Carrera que se desea añadir a la base de datos.
     *
     * @return Devuelve si la acción se ha realizado con exito o hubo algún
     * error durante el intento.
     *
     * @throws java.sql.SQLException Lanza dicha excepción en caso de que no se
     * pueda crear o cerrar la conexion con la base de datos.
     * @throws java.lang.ClassNotFoundException Lanza dicha excepción en caso de
     * que no encuentre la clase com.mysql.cj.jdbc.Driver.
     */
    public boolean addRun(Run run) throws SQLException, ClassNotFoundException {

        manager.open();
        RunSQL runSQL = new RunSQL();

        if (getRun(run.getId()) != null) {
            return false;
        } else {

            if (runSQL.insert(run, manager)) {
                manager.close();
                return true;
            } else {
                manager.close();
                return false;
            }
        }

    }

    /**
     * Método para añadir una marca en caso de que no exista la marca para ese
     * usuario, en esa carrera y para el mismo año.
     *
     * @param mark La marca que se desea añadir a la base de datos.
     *
     * @return Devuelve verdadero solo y unicamente si no se ha encontrado una
     * marca igual y no ha habido ningún error a la hora de insertar la marca en
     * la base de datos, de otro modo devuelve falso.
     *
     * @throws java.sql.SQLException Lanza dicha excepción en caso de que no se
     * pueda crear o cerrar la conexion con la base de datos.
     * @throws java.lang.ClassNotFoundException Lanza dicha excepción en caso de
     * que no encuentre la clase com.mysql.cj.jdbc.Driver.
     */
    public boolean addMark(Mark mark) throws SQLException, ClassNotFoundException {

        manager.open();
        MarkSQL markSQL = new MarkSQL();

        for (Mark m : marks) {
            if (m.getUserId() == mark.getUserId()
                    && m.getRunId() == mark.getRunId()
                    && m.getYear() == mark.getYear()) {
                return false;
            }
        }

        if (markSQL.insert(mark, manager)) {
            manager.close();
            return true;
        } else {
            manager.close();
            return false;
        }
    }

}

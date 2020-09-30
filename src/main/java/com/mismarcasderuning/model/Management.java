/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.model;

import com.mismarcasderuning.dao.*;
import com.mismarcasderuning.notifications.*;
import com.mismarcasderuning.utils.Utils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Jesus Cruz
 */
//TODO: Cambiar los comentarios de JavaDoc.
public class Management {

    private Manager manager;
    private RunSQL rSQL;
    private UserSQL uSQL;
    private MarkSQL mSQL;

    private Email EMAIL;
    // private Telegram TELEGRAM;
    // Telegram no funciona con la version de java 8

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

        rSQL = new RunSQL();
        uSQL = new UserSQL();
        mSQL = new MarkSQL();

        EMAIL = new Email();

        manager.open();
    }

    // GETTERS
    /**
     * Getter de la lista de usuarios guardados en la base de datos.
     *
     * @return Devuelve una lista de usuarios.
     */
    public ArrayList<User> getUsers() throws SQLException {

        return uSQL.readAll(manager);
    }

    /**
     * Getter de la lista de carreras guardadas en la base de datos.
     *
     * @return Devuelve una lista de carreras.
     */
    public ArrayList<Run> getRuns() throws SQLException {

        return rSQL.readAll(manager);
    }

    /**
     * Getter de la lista de marcas guardadas en la base de datos.
     *
     * @return Devuelve una lista de marcas.
     */
    public ArrayList<Mark> getMarks() throws SQLException {

        return mSQL.readAll(manager);
    }

    /**
     * Getter de un solo usuario guardado en la base de datos.
     *
     * @param id El id del usuario que se desea buscar.
     *
     * @return Devuelve el usuario si el id no es mayor que el tamaño del array
     * de usuarios, de otro modo devuelve null.
     */
    public User getUser(int id) throws SQLException {

        return uSQL.read(id, manager);
    }

    /**
     *
     * @param username
     * @return
     * @throws SQLException
     */
    public User getUser(String username) throws SQLException {

        return uSQL.read(username, manager);
    }

    /**
     * Getter de una sola carrera guardada en la base de datos.
     *
     * @param id El id de la carrera que se desea buscar.
     *
     * @return Devuelve la carrera si el id no es mayor que el tamaño del array
     * de carreras, de otro modo devuelve null.
     */
    public Run getRun(int id) throws SQLException {

        return rSQL.read(id, manager);
    }

    /**
     * Getter de una sola marca guardada en la base de datos.
     *
     * @param id El id de la marca que se desea buscar.
     *
     * @return Devuelve la marca si el id no es mayor que el tamaño del array de
     * marcas, de otro modo devuelve null.
     */
    public Mark getMark(int id) throws SQLException {

        return mSQL.read(id, manager);
    }

    /**
     * Getter de las marcas de un usuario concreto y una carrera concreta.
     *
     * @param userId Id del usuario
     * @param runId
     * @return
     */
    public Mark getMark(int userId, int runId) throws SQLException {

        ArrayList<Mark> marks = getMarks();

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
     *
     * @throws SQLException
     */
    public ArrayList<Mark> getPublicMarks() throws SQLException {

        ArrayList<Mark> publicMarks = new ArrayList<>();

        ArrayList<Mark> marks = getMarks();

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
     *
     * @throws SQLException
     */
    public ArrayList<Mark> getUserPublicMarks(int userId) throws SQLException {

        ArrayList<Mark> userPublicMarks = new ArrayList<>();

        ArrayList<Mark> marks = getMarks();

        for (Mark mark : marks) {
            if (mark.isOpen() && mark.getUserId() == userId) {
                userPublicMarks.add(mark);
            }
        }

        return userPublicMarks;
    }

    // METODOS PARA OBTENER EL NÚMERO DE TUPLAS DE LAS TABLAS.
    public int getNUsers() throws SQLException {
        return getUsers().size();
    }

    public int getNRuns() throws SQLException {
        return getRuns().size();
    }

    public int getNMarks() throws SQLException {
        return getMarks().size();
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
    public User getUserByEmail(String email) throws SQLException {

        ArrayList<User> users = getUsers();

        for (User user : users) {
            if (user.getEmail().equals(email)) {
                return user;
            }
        }

        return null;
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
     */
    public boolean addUser(User user) throws SQLException {

        return uSQL.insert(user, manager);
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
     */
    public boolean addRun(Run run) throws SQLException {

        if (getRun(run.getId()) != null) {
            return false;
        } else {

            return rSQL.insert(run, manager);
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
     */
    public boolean addMark(Mark mark) throws SQLException {

        ArrayList<Mark> marks = getMarks();

        for (Mark m : marks) {
            if (m.getUserId() == mark.getUserId()
                    && m.getRunId() == mark.getRunId()
                    && m.getYear() == mark.getYear()) {
                return false;
            }
        }

        return mSQL.insert(mark, manager);
    }

    public void deleteUser(User user) {
        
        uSQL.delete(user, manager);
    }
    
    /**
     *
     * @param username
     * @param password A string
     * @return A String with the error that doesn't let you log in.
     * @throws SQLException
     */
    public String login(String username, String password) throws SQLException {

        User user = getUser(username);
        String error;

        if (user == null) {
            return "El nombre de usuario no correspone a nadie.";
        } else {
            if (!user.getPassword().equals(password)) {
                return "La contraseña no es correcta";
            } else {
                error = "";
            }
        }

        return error;
    }

    public User regist(String username, String password, String email, String name, String surnames, Calendar birthDate) throws SQLException, IOException {

        User user = getUser(username);

        if (user != null) {
            return null;
        } else {
            user = getUserByEmail(email);

            if (user != null) {
                return null;
            } else {
                user = new User(getNUsers(), username, password, email, name, surnames, birthDate);

                String html = Utils.readFile("C:\\Users\\jesus\\Desktop\\DAM\\1DAM\\Programacion\\MisMarcasDeRunning\\src\\main\\webapp\\welcome.html");
                html = html.replaceAll("=name", user.getFullName());
                html = html.replaceAll("=email", user.getEmail());
                html = html.replaceAll("=token", Integer.toString(user.getToken()));

                System.out.println(html);

                if (addUser(user)) {
                    if (EMAIL.send(email, "WELCOME", html)) {
                        /*
                        try {
                            TELEGRAM.send("Se ha registrado el correo: "
                                    + user.getEmail() + " con el usuario: "
                                    + user.getUsername());
                        } catch (Error e) {
                            e.printStackTrace();
                        }
                         */
                        return user;
                    } else {
                        deleteUser(user);
                        return null;
                    }
                }
            }
        }

        return null;
    }

    public void close() throws SQLException {
        manager.close();
    }
}

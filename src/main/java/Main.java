
import com.mismarcasderuning.model.Management;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesus Cruz
 */
public class Main {
    public static void main(String[] args) {
        try {
            Management m = new Management();
            System.out.println(m.getRun(0).getName());
            
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

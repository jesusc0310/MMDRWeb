
import com.mismarcasderuning.model.Management;
import com.mismarcasderuning.model.Run;
import java.sql.SQLException;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jesus Cruz
 */
public class main {
    
    public static void main(String[] args) {
        
        try {
            Management m = new Management();
            
            ArrayList<Run> runs = m.getRuns();
            
            for (Run r : runs) {
                System.out.println("<h1>" + r.getId() + "</h1>");
            }
            
            m.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mismarcasderuning.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author Jesus Cruz
 */
public class Utils {

    public static String readFile(String file) throws FileNotFoundException, IOException {

        System.out.println(new File(file).getAbsolutePath());

        BufferedReader br = new BufferedReader(new FileReader(file));

        String doc = "";
        String line = "";

        while (line != null) {
            line = br.readLine();

            if (line != null) {
                doc = doc.concat(line);
            }
        }
        
        br.close();

        return doc;
    }

    public static void writeFile(String file) {
        // TODO Programar el escritor de archivos.
    }
}

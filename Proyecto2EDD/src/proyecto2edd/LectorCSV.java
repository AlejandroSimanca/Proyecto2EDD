/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * clase encargada de leer el archivo csv con los usuarios iniciales.
 * @author alejandrosimanca
 */
public class LectorCSV {

    /**
     * lee el archivo desde la ruta especificada y guarda los usuarios en la tabla.
     * @param rutaaArchivo la ruta absoluta del archivo seleccionado con jfilechooser.
     * @param tabla la tabla hash donde se guardaran los usuarios.
     */
    public void leerarchivo(String rutaaArchivo, TablaHash tabla) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(rutaaArchivo));
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length >= 2) {
                    String nombre = datos[0].trim();
                    String prioridad = datos[1].trim();
                    Usuario nuevo = new Usuario(nombre, prioridad);
                    tabla.insertar(nuevo);
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("error al leer el archivo: " + e.getMessage());
        }
    }
}

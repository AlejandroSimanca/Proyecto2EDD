/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * clase encargada de leer el archivo csv con los usuarios.
 * utiliza jfilechooser para seleccionar el archivo.
 * @author alejandrosimanca
 */
public class LectorCSV {

    /**
     * abre una ventana para seleccionar el archivo y carga los usuarios en la tabla hash.
     * maneja excepciones para evitar el cierre brusco del programa.
     * @param tabla la tabla de dispersion donde se guardaran los usuarios leidos.
     */
    public void leerarchivo(TablaHash tabla) {
        JFileChooser selector = new JFileChooser();
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("archivos csv", "csv");
        selector.setFileFilter(filtro);

        int respuesta = selector.showOpenDialog(null);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            File archivo = selector.getSelectedFile();
            try {
                BufferedReader lector = new BufferedReader(new FileReader(archivo));
                String linea;
                
                linea = lector.readLine();
                
                while ((linea = lector.readLine()) != null) {
                    String[] datos = linea.split(",");
                    if (datos.length == 2) {
                        String nombre = datos[0].trim();
                        String tipo = datos[1].trim();
                        Usuario nuevousuario = new Usuario(nombre, tipo);
                        tabla.insertar(nuevousuario);
                    }
                }
                lector.close();
                System.out.println("archivo cargado con exito");
            } catch (Exception e) {
                System.out.println("error al intentar leer el archivo");
            }
        }
    }
}
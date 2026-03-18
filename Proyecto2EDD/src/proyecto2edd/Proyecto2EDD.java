/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2edd;

/**
 * clase principal que inicia la ejecucion del sistema operativo simulado.
 * @author alejandrosimanca
 */
public class Proyecto2EDD {

    /**
     * metodo de entrada del programa.
     * @param args argumentos de la linea de comandos.
     */
    public static void main(String[] args) {
        TablaHash tabla = new TablaHash(100);
        LectorCSV lector = new LectorCSV();
        
        lector.leerarchivo(tabla);
    }
}
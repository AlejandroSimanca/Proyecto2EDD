/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa un documento que sera enviado a la cola de impresion.
 * contiene informacion sobre su tamano, dueno y etiqueta de tiempo.
 * @author alejandrosimanca
 */
public class Documento {
    private String nombre;
    private int tamano;
    private Usuario dueno;
    private int etiquetatiempo;

    /**
     * constructor de la clase documento.
     * @param nombre el nombre del archivo o documento.
     * @param tamano el numero de paginas o peso del documento.
     * @param dueno el usuario que envio a imprimir el documento.
     * @param etiquetatiempo el valor que determina su posicion en la cola.
     */
    public Documento(String nombre, int tamano, Usuario dueno, int etiquetatiempo) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.dueno = dueno;
        this.etiquetatiempo = etiquetatiempo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTamano() {
        return tamano;
    }

    public Usuario getDueno() {
        return dueno;
    }

    public int getEtiquetatiempo() {
        return etiquetatiempo;
    }

    /**
     * permite actualizar la etiqueta de tiempo si es necesario cambiar su prioridad.
     * @param etiquetatiempo el nuevo valor numerico de prioridad.
     */
    public void setEtiquetatiempo(int etiquetatiempo) {
        this.etiquetatiempo = etiquetatiempo;
    }

    @Override
    public String toString() {
        return "documento: " + nombre + " | paginas: " + tamano + " | dueno: " + dueno.getNombre();
    }
}
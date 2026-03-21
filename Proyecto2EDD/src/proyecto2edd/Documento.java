/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa un documento que pertenece a un usuario.
 * contiene informacion sobre su tamano, dueno, etiqueta de tiempo y estado.
 * @author alejandrosimanca
 */
public class Documento {
    private String nombre;
    private int tamano;
    private Usuario dueno;
    private int etiquetatiempo;
    private boolean encola;
    private int indiceCola; // <- NUEVO: Guarda en que posicion del arreglo esta

    /**
     * constructor de la clase documento.
     * @param nombre el nombre del archivo o documento.
     * @param tamano el numero de paginas o peso del documento.
     * @param dueno el usuario que envio a imprimir el documento.
     */
    public Documento(String nombre, int tamano, Usuario dueno) {
        this.nombre = nombre;
        this.tamano = tamano;
        this.dueno = dueno;
        this.etiquetatiempo = 0;
        this.encola = false;
        this.indiceCola = -1; // -1 significa que no esta en la cola
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

    public void setEtiquetatiempo(int etiquetatiempo) {
        this.etiquetatiempo = etiquetatiempo;
    }

    public boolean isEncola() {
        return encola;
    }

    public void setEncola(boolean encola) {
        this.encola = encola;
    }

    public int getIndiceCola() {
        return indiceCola;
    }

    public void setIndiceCola(int indiceCola) {
        this.indiceCola = indiceCola;
    }

    @Override
    public String toString() {
        String estado = encola ? "[en cola]" : "[esperando]";
        return estado + " doc: " + nombre + " | paginas: " + tamano;
    }
}
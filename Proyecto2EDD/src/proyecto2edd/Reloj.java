/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa el reloj global del sistema operativo simulado.
 * lleva el control del tiempo y calcula las etiquetas para la cola de impresion.
 * @author alejandrosimanca
 */
public class Reloj {
    private int tiempoactual;

    /**
     * constructor que inicializa el reloj en 0.
     */
    public Reloj() {
        this.tiempoactual = 0;
    }

    /**
     * avanza el tiempo del sistema en una unidad.
     * debe llamarse cada vez que ocurre una accion en el sistema.
     */
    public void tick() {
        this.tiempoactual++;
    }

    public int getTiempoactual() {
        return tiempoactual;
    }

    /**
     * calcula la etiqueta de tiempo basada en la prioridad del usuario.
     * a menor valor devuelto, mayor sera la prioridad en el min-heap.
     * @param tipoprioridad el nivel de prioridad del usuario creador.
     * @return el valor numerico para posicionar el documento.
     */
    public int crearEtiqueta(String tipoprioridad) {
        int etiqueta = tiempoactual;
        
        if (tipoprioridad.equals("prioridad_alta")) {
            etiqueta = tiempoactual + 1;
        } else if (tipoprioridad.equals("prioridad_media")) {
            etiqueta = tiempoactual + 5;
        } else if (tipoprioridad.equals("prioridad_baja")) {
            etiqueta = tiempoactual + 10;
        }
        
        return etiqueta;
    }
}
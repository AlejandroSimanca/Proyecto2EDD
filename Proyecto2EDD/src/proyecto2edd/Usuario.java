/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa a un usuario del sistema de impresion.
 * @author alejandrosimanca
 */
public class Usuario {
    private String nombre;
    private String tipoprioridad;

    /**
     * constructor para crear un nuevo usuario.
     * @param nombre el identificador del usuario.
     * @param tipoprioridad el nivel de prioridad asignado.
     */
    public Usuario(String nombre, String tipoprioridad) {
        this.nombre = nombre;
        this.tipoprioridad = tipoprioridad;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoprioridad() {
        return tipoprioridad;
    }

    @Override
    public String toString() {
        return "usuario: " + nombre + " (" + tipoprioridad + ")";
    }
}

/**
 *
 * @author alejandrosimanca
 */

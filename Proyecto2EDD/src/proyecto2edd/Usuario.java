/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa un usuario del sistema operativo.
 * contiene su nivel de prioridad y la lista de documentos que ha creado.
 * @author alejandrosimanca
 */
public class Usuario {
    private String nombre;
    private String tipoprioridad;
    private ListaDocumentos documentos;

    /**
     * constructor del usuario.
     * @param nombre identificador unico del usuario.
     * @param tipoprioridad prioridad (alta, media, baja).
     */
    public Usuario(String nombre, String tipoprioridad) {
        this.nombre = nombre;
        this.tipoprioridad = tipoprioridad;
        this.documentos = new ListaDocumentos();
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoprioridad() {
        return tipoprioridad;
    }

    public ListaDocumentos getDocumentos() {
        return documentos;
    }
}
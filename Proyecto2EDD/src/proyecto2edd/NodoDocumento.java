/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * nodo simple para construir la lista enlazada de documentos.
 * @author alejandrosimanca
 */
public class NodoDocumento {
    private Documento documento;
    private NodoDocumento siguiente;

    public NodoDocumento(Documento documento) {
        this.documento = documento;
        this.siguiente = null;
    }

    public Documento getDocumento() {
        return documento;
    }

    public NodoDocumento getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoDocumento siguiente) {
        this.siguiente = siguiente;
    }
}
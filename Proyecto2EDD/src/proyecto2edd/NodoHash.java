/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

/**
 * representa un nodo para la tabla de dispersion.
 * permite manejar colisiones mediante encadenamiento.
 * @author alejandrosimanca
 */
public class NodoHash {
    private String clave;
    private Usuario valor;
    private NodoHash siguiente;

    /**
     * constructor del nodo.
     * @param clave el nombre de usuario.
     * @param valor el objeto usuario asociado.
     */
    public NodoHash(String clave, Usuario valor) {
        this.clave = clave;
        this.valor = valor;
        this.siguiente = null;
    }

    public String getClave() {
        return clave;
    }

    public Usuario getValor() {
        return valor;
    }

    public NodoHash getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoHash siguiente) {
        this.siguiente = siguiente;
    }
}
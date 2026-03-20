/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * panel encargado de dibujar graficamente el monticulo binario.
 * utiliza la clase graphics nativa de java para pintar nodos y aristas.
 * @author alejandrosimanca
 */
public class PanelArbol extends JPanel {
    private Documento[] monticulo;
    private int tamano;

    /**
     * actualiza los datos del arbol y repinta el panel.
     * @param monticulo el arreglo de documentos actual en la cola.
     * @param tamano la cantidad de elementos validos en el arreglo.
     */
    public void actualizararbol(Documento[] monticulo, int tamano) {
        this.monticulo = monticulo;
        this.tamano = tamano;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (monticulo == null || tamano == 0) {
            return;
        }
        dibujarnodo(g, 0, getWidth() / 2, 30, getWidth() / 4);
    }

    /**
     * metodo recursivo para dibujar cada nodo y sus lineas a los hijos.
     * @param g el objeto de graficos de java.
     * @param indice la posicion actual en el arreglo.
     * @param x la coordenada x en pantalla.
     * @param y la coordenada y en pantalla.
     * @param espacio el espacio horizontal para separar los hijos.
     */
    private void dibujarnodo(Graphics g, int indice, int x, int y, int espacio) {
        int hijoizquierdo = 2 * indice + 1;
        int hijoderecho = 2 * indice + 2;

        if (hijoizquierdo < tamano) {
            g.drawLine(x, y, x - espacio, y + 50);
            dibujarnodo(g, hijoizquierdo, x - espacio, y + 50, espacio / 2);
        }

        if (hijoderecho < tamano) {
            g.drawLine(x, y, x + espacio, y + 50);
            dibujarnodo(g, hijoderecho, x + espacio, y + 50, espacio / 2);
        }

        g.setColor(Color.CYAN);
        g.fillOval(x - 15, y - 15, 30, 30);
        g.setColor(Color.BLACK);
        g.drawOval(x - 15, y - 15, 30, 30);
        
        String texto = String.valueOf(monticulo[indice].getEtiquetatiempo());
        g.drawString(texto, x - 5, y + 5);
    }
}
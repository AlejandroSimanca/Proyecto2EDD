/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * panel grafico encargado de dibujar el monticulo binario en forma de arbol.
 * @author alejandrosimanca
 */
public class PanelArbol extends JPanel {
    
    private Documento[] monticulo;
    private int tamano;
    
    private final int RADIO = 20; 
    private final int ESPACIO_VERTICAL = 60; 

    public PanelArbol() {
        this.monticulo = null;
        this.tamano = 0;
        setBackground(Color.WHITE);
    }

    /**
     * actualiza los datos del panel y fuerza a que se vuelva a dibujar.
     * @param monticulo el arreglo actual del monticulo binario.
     * @param tamano la cantidad de elementos en la cola.
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
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.setColor(Color.RED);
            g.drawString("la cola de impresion esta vacia.", 20, 30);
            return;
        }
        
        g.setFont(new Font("Arial", Font.BOLD, 14));
        dibujarnodo(g, 0, getWidth() / 2, 40, getWidth() / 4);
    }

    /**
     * metodo recursivo que dibuja un nodo y sus hijos (lineas y circulos).
     * @param g el objeto de graficos de Java.
     * @param indice la posicion del documento en el arreglo.
     * @param x la coordenada X donde se dibujara el nodo.
     * @param y la coordenada Y donde se dibujara el nodo.
     * @param espacio_horizontal la separacion horizontal para los hijos.
     */
    private void dibujarnodo(Graphics g, int indice, int x, int y, int espacio_horizontal) {
        if (indice >= tamano || monticulo[indice] == null) {
            return;
        }

        int hijoIzq = (2 * indice) + 1;
        int hijoDer = (2 * indice) + 2;

        g.setColor(Color.BLACK); 
        if (hijoIzq < tamano && monticulo[hijoIzq] != null) {
            int nx = x - espacio_horizontal;
            int ny = y + ESPACIO_VERTICAL;
            g.drawLine(x, y, nx, ny);
            dibujarnodo(g, hijoIzq, nx, ny, espacio_horizontal / 2);
        }

        if (hijoDer < tamano && monticulo[hijoDer] != null) {
            int nx = x + espacio_horizontal;
            int ny = y + ESPACIO_VERTICAL;
            g.drawLine(x, y, nx, ny);
            dibujarnodo(g, hijoDer, nx, ny, espacio_horizontal / 2);
        }

        g.setColor(new Color(135, 206, 250)); 
        g.fillOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);
        
        g.setColor(Color.BLACK); 
        g.drawOval(x - RADIO, y - RADIO, 2 * RADIO, 2 * RADIO);

        String prioridad = String.valueOf(monticulo[indice].getEtiquetatiempo());
        int ajustex = g.getFontMetrics().stringWidth(prioridad) / 2;
        int ajustey = g.getFontMetrics().getAscent() / 4;
        
        g.setColor(Color.BLACK);
        g.drawString(prioridad, x - ajustex, y + ajustey);
        
        String nombreDoc = monticulo[indice].getNombre();
        int ajusteNombre = g.getFontMetrics().stringWidth(nombreDoc) / 2;
        g.setColor(Color.DARK_GRAY);
        g.drawString(nombreDoc, x - ajusteNombre, y + RADIO + 15);
    }
}
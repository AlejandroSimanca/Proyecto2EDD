/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * ventana principal de la interfaz grafica.
 * permite al usuario interactuar con el sistema de impresion.
 * @author alejandrosimanca
 */
public class VentanaPrincipal extends JFrame {
    
    private TablaHash tablausuarios;
    private GestorImpresion gestor;
    
    private JButton btncargar;
    private JButton btnenviar;
    private JButton btnimprimir;
    private JButton btnverarbol;
    private JTextField txtusuario;
    private JTextField txtdocumento;
    private JTextField txtpaginas;
    private JTextArea areamensajes;

    /**
     * constructor que inicializa los componentes graficos.
     */
    public VentanaPrincipal() {
        tablausuarios = new TablaHash(100);
        gestor = new GestorImpresion(tablausuarios);
        
        setTitle("sistema de impresion");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        btncargar = new JButton("cargar usuarios csv");
        btncargar.setBounds(20, 20, 200, 30);
        add(btncargar);
        
        JLabel lblusuario = new JLabel("usuario:");
        lblusuario.setBounds(20, 70, 80, 30);
        add(lblusuario);
        
        txtusuario = new JTextField();
        txtusuario.setBounds(100, 70, 120, 30);
        add(txtusuario);
        
        JLabel lbldocumento = new JLabel("documento:");
        lbldocumento.setBounds(20, 110, 80, 30);
        add(lbldocumento);
        
        txtdocumento = new JTextField();
        txtdocumento.setBounds(100, 110, 120, 30);
        add(txtdocumento);
        
        JLabel lblpaginas = new JLabel("paginas:");
        lblpaginas.setBounds(20, 150, 80, 30);
        add(lblpaginas);
        
        txtpaginas = new JTextField();
        txtpaginas.setBounds(100, 150, 120, 30);
        add(txtpaginas);
        
        btnenviar = new JButton("enviar a la cola");
        btnenviar.setBounds(20, 200, 200, 30);
        add(btnenviar);
        
        btnimprimir = new JButton("imprimir siguiente");
        btnimprimir.setBounds(20, 250, 200, 30);
        add(btnimprimir);
        
        btnverarbol = new JButton("ver arbol grafico");
        btnverarbol.setBounds(20, 300, 200, 30);
        add(btnverarbol);
        
        areamensajes = new JTextArea();
        areamensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areamensajes);
        scroll.setBounds(250, 20, 310, 310);
        add(scroll);
        
        configurarEventos();
    }
    
    /**
     * configura las acciones de los botones de la interfaz.
     */
    private void configurarEventos() {
        btncargar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LectorCSV lector = new LectorCSV();
                lector.leerarchivo(tablausuarios);
                areamensajes.append("archivo csv procesado.\n");
            }
        });
        
        btnenviar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                String documento = txtdocumento.getText();
                String paginasstr = txtpaginas.getText();
                
                try {
                    int paginas = Integer.parseInt(paginasstr);
                    gestor.enviaraimprimir(usuario, documento, paginas);
                    areamensajes.append("intento de envio: " + documento + " de " + usuario + "\n");
                    txtusuario.setText("");
                    txtdocumento.setText("");
                    txtpaginas.setText("");
                } catch (Exception ex) {
                    areamensajes.append("error: verifique los datos ingresados.\n");
                }
            }
        });
        
        btnimprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Documento doc = gestor.imprimirsiguiente();
                if (doc != null) {
                    areamensajes.append("imprimiendo: " + doc.getNombre() + " (" + doc.getDueno().getNombre() + ")\n");
                } else {
                    areamensajes.append("la cola de impresion esta vacia.\n");
                }
            }
        });
        
        btnverarbol.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ventanagrafico = new JFrame("arbol del monticulo binario");
                ventanagrafico.setSize(600, 400);
                ventanagrafico.setLocationRelativeTo(null);
                
                PanelArbol panel = new PanelArbol();
                panel.actualizararbol(gestor.getColaimpresion().getMonticulo(), gestor.getColaimpresion().getTamano());
                
                ventanagrafico.add(panel);
                ventanagrafico.setVisible(true);
            }
        });
    }
}
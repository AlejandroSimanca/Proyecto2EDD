/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2edd;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
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
    private JButton btncancelar;
    private JButton btnverlista;
    private JButton btneliminarusuario;
    private JButton btnverdocsusuario;
    private JTextField txtusuario;
    private JTextField txtdocumento;
    private JTextField txtpaginas;
    private JTextArea areamensajes;

    public VentanaPrincipal() {
        tablausuarios = new TablaHash(100);
        gestor = new GestorImpresion(tablausuarios);
        
        setTitle("sistema de impresion");
        setSize(600, 550); 
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
        btnenviar.setBounds(20, 190, 200, 30);
        add(btnenviar);
        
        btncancelar = new JButton("cancelar impresion");
        btncancelar.setBounds(20, 230, 200, 30);
        add(btncancelar);
        
        btnimprimir = new JButton("imprimir siguiente");
        btnimprimir.setBounds(20, 270, 200, 30);
        add(btnimprimir);
        
        btnverarbol = new JButton("ver arbol grafico");
        btnverarbol.setBounds(20, 310, 200, 30);
        add(btnverarbol);
        
        btnverlista = new JButton("ver cola como lista");
        btnverlista.setBounds(20, 350, 200, 30);
        add(btnverlista);

        btneliminarusuario = new JButton("eliminar usuario");
        btneliminarusuario.setBounds(20, 390, 200, 30);
        add(btneliminarusuario);

        btnverdocsusuario = new JButton("ver estado del usuario");
        btnverdocsusuario.setBounds(20, 430, 200, 30);
        add(btnverdocsusuario);
        
        areamensajes = new JTextArea();
        areamensajes.setEditable(false);
        JScrollPane scroll = new JScrollPane(areamensajes);
        scroll.setBounds(250, 20, 310, 440); 
        add(scroll);
        
        configurarEventos();
    }
    
    private void configurarEventos() {
        btncargar.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                javax.swing.JFileChooser fileChooser = new javax.swing.JFileChooser();
                int seleccion = fileChooser.showOpenDialog(null);
                
                if (seleccion == javax.swing.JFileChooser.APPROVE_OPTION) {
                    java.io.File archivo = fileChooser.getSelectedFile();
                    String ruta = archivo.getAbsolutePath();
                    
                    LectorCSV lector = new LectorCSV(); 
                    lector.leerarchivo(ruta, tablausuarios);
                    
                    areamensajes.append("archivo cargado: " + archivo.getName() + "\n");
                } else {
                    areamensajes.append("carga de archivo cancelada.\n");
                }
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
                    areamensajes.append("enviado: " + documento + " de " + usuario + "\n");
                    txtusuario.setText("");
                    txtdocumento.setText("");
                    txtpaginas.setText("");
                } catch (Exception ex) {
                    areamensajes.append("error: verifique los datos ingresados.\n");
                }
            }
        });
        
        btncancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                String documento = txtdocumento.getText();
                
                if (usuario.isEmpty() || documento.isEmpty()) {
                    areamensajes.append("error: ingrese el usuario y el documento a cancelar.\n");
                    return;
                }
                
                String resultado = gestor.cancelarImpresion(usuario, documento);
                areamensajes.append(resultado + "\n");
                
                txtusuario.setText("");
                txtdocumento.setText("");
            }
        });
        
        btnimprimir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Documento doc = gestor.imprimirsiguiente();
                if (doc != null) {
                    areamensajes.append("imprimiendo: " + doc.getNombre());
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
        
        btnverlista.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Documento[] monticulo = gestor.getColaimpresion().getMonticulo();
                int tamano = gestor.getColaimpresion().getTamano();
                
                if (tamano == 0) {
                    areamensajes.append("--- cola vacia ---\n");
                    return;
                }
                
                areamensajes.append("--- estado de la cola ---\n");
                for (int i = 0; i < tamano; i++) {
                    Documento doc = monticulo[i];
                    areamensajes.append((i + 1) + ". " + doc.getNombre() + " (prioridad: " + doc.getEtiquetatiempo() + ")\n");
                }
                areamensajes.append("-------------------------\n");
            }
        });

        btneliminarusuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                if (usuario.isEmpty()) {
                    areamensajes.append("error: ingrese el nombre del usuario a eliminar.\n");
                    return;
                }
                Usuario u = tablausuarios.buscar(usuario);
                if (u != null) {
                    tablausuarios.eliminar(usuario);
                    areamensajes.append("exito: el usuario " + usuario + " ha sido eliminado del sistema.\n");
                    txtusuario.setText("");
                } else {
                    areamensajes.append("error: el usuario no existe.\n");
                }
            }
        });

        btnverdocsusuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String usuario = txtusuario.getText();
                if (usuario.isEmpty()) {
                    areamensajes.append("error: ingrese el nombre del usuario para ver su estado.\n");
                    return;
                }
                Usuario u = tablausuarios.buscar(usuario);
                if (u != null) {
                    areamensajes.append("--- estado del usuario: " + usuario + " ---\n");
                    areamensajes.append("prioridad: " + u.getTipoprioridad() + "\n");
                    // Imprimimos la lista de documentos que tenga asociados
                    areamensajes.append("documentos registrados: \n" + u.getDocumentos().toString() + "\n");
                    areamensajes.append("--------------------------------------\n");
                } else {
                    areamensajes.append("error: el usuario no existe.\n");
                }
            }
        });
    }
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IdSin.Swing;

import Analizador.IdentSintactico;
import Analizador.Lexer;
import Analizador.Token;
import Lexema.Lexema;
import SintaxisCorrecta.SitaxisId;
import analizadorLexico.Archivo.ManejadorArchivo;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.jdesktop.observablecollections.ObservableCollections;
import org.jdesktop.observablecollections.ObservableList;

/**
 *
 * @author angelrg
 */
public class PantallaPrincipal extends javax.swing.JFrame {

    private String pathTemporal = "";
    private String path = "";
    private String pathSalida = "";
    private String analisisSintactico = "";
    private List<Lexema> listaLexemas;
    private List<Lexema> listaLexemasAux;
    private ObservableList<Lexema> listaObservableLexemas;
    private ObservableList<SitaxisId> listaObservableSintaxis;
    private List<SitaxisId> listaSintaxis;
    private ManejadorArchivo archivos;
    private IdentSintactico identificador;

    private int errores = 0;

    public PantallaPrincipal() {
        listaLexemas = new ArrayList<>();
        listaLexemasAux = new ArrayList<>();
        listaSintaxis = new ArrayList<>();
        listaObservableLexemas = ObservableCollections.observableList(listaLexemas);
        listaObservableSintaxis = ObservableCollections.observableList(listaSintaxis);
        archivos = new ManejadorArchivo();
        identificador = new IdentSintactico();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        jMenuItem1 = new javax.swing.JMenuItem();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        workTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        guardarMenuItem = new javax.swing.JMenuItem();
        abrirMenuItem = new javax.swing.JMenuItem();
        nuevoMenuItem = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        lexicoMenuItem = new javax.swing.JMenuItem();
        sintacticoMenuItem = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Analizador");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        org.jdesktop.beansbinding.ELProperty eLProperty = org.jdesktop.beansbinding.ELProperty.create("${listaObservableSintaxis}");
        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable1);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${estructura}"));
        columnBinding.setColumnName("Estructura");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${columna}"));
        columnBinding.setColumnName("Columna");
        columnBinding.setColumnClass(Integer.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fila}"));
        columnBinding.setColumnName("Fila");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane1.setViewportView(jTable1);

        workTextArea.setColumns(20);
        workTextArea.setRows(5);
        workTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                workTextAreaKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(workTextArea);

        jLabel1.setText("Analisis Lexico");

        eLProperty = org.jdesktop.beansbinding.ELProperty.create("${listaObservableLexemas}");
        jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, this, eLProperty, jTable2);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${nombre}"));
        columnBinding.setColumnName("Nombre");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${tokenString}"));
        columnBinding.setColumnName("Token ");
        columnBinding.setColumnClass(String.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fila}"));
        columnBinding.setColumnName("Fila");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${columna}"));
        columnBinding.setColumnName("Columna");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        jScrollPane3.setViewportView(jTable2);

        jLabel2.setText("Analizador Sintactico");

        jMenu1.setText(" Archivo ");

        guardarMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_G, java.awt.event.InputEvent.CTRL_MASK));
        guardarMenuItem.setText("Guardar");
        guardarMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(guardarMenuItem);

        abrirMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        abrirMenuItem.setText("Abrir");
        abrirMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                abrirMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(abrirMenuItem);

        nuevoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        nuevoMenuItem.setText("Nuevo");
        nuevoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nuevoMenuItemActionPerformed(evt);
            }
        });
        jMenu1.add(nuevoMenuItem);

        jMenuBar1.add(jMenu1);

        jMenu2.setText(" Run ");

        lexicoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        lexicoMenuItem.setText("Analizador Lexico");
        lexicoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lexicoMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(lexicoMenuItem);

        sintacticoMenuItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        sintacticoMenuItem.setText("Analizador Sintactico");
        sintacticoMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sintacticoMenuItemActionPerformed(evt);
            }
        });
        jMenu2.add(sintacticoMenuItem);

        jMenuBar1.add(jMenu2);

        jMenu3.setText(" Informacion ");

        aboutMenuItem.setText("Acerca de");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        jMenu3.add(aboutMenuItem);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel1))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(71, 71, 71)
                                .addComponent(jLabel2)
                                .addGap(48, 48, 48))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 522, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lexicoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lexicoMenuItemActionPerformed
        evaluarLexemas(workTextArea.getText());
        actualizarListaObservable(listaLexemasAux);
    }//GEN-LAST:event_lexicoMenuItemActionPerformed

    private void abrirMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirMenuItemActionPerformed
        JFileChooser dialogo = new JFileChooser();
        dialogo.setDialogTitle("Abrir");
        FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivo", "txt");
        dialogo.setFileFilter(filtro);
        if (dialogo.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            path = dialogo.getSelectedFile().getAbsolutePath();
            pathTemporal = path;
            try {
                workTextArea.setText(archivos.lecturaArchivo(path));
                evaluarLexemas(workTextArea.getText());
                JOptionPane.showMessageDialog(this, "Archivo abierto exitosamente", "Informacion", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, "No se ha abierto ningun archivo", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        actualizarListaObservable(listaLexemasAux);
    }//GEN-LAST:event_abrirMenuItemActionPerformed

    private void sintacticoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sintacticoMenuItemActionPerformed
        evaluarLexemas(workTextArea.getText());
        actualizarListaObservable(listaLexemasAux);
        System.out.println("errores:" + errores);
        actualizarListaObservableSintaxis(identificador.analizador(listaLexemasAux));

        try {
            String nombre = "";
            JFileChooser file = new JFileChooser();
            file.setDialogTitle("Guardar");
            file.showSaveDialog(this);
            File guarda = file.getSelectedFile();

            if (guarda != null) {
                nombre = file.getSelectedFile().getName();
                archivos.guardarArchivo(guarda + ".txt", identificador.getTextoSalida());
                pathSalida = file.getSelectedFile().getAbsolutePath();
                System.out.println(pathSalida);
                JOptionPane.showMessageDialog(null,
                        "El archivo se a guardado Exitosamente",
                        "Información", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_sintacticoMenuItemActionPerformed

    private void guardarMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarMenuItemActionPerformed
        try {
            if (path.replace(" ", "").isEmpty()) {
                String nombre = "";
                JFileChooser file = new JFileChooser();
                file.setDialogTitle("Guardar");
                file.showSaveDialog(this);
                File guarda = file.getSelectedFile();

                if (guarda != null) {
                    nombre = file.getSelectedFile().getName();
                    archivos.guardarArchivo(guarda + ".txt", workTextArea.getText());
                    path = file.getSelectedFile().getAbsolutePath();
                    System.out.println(path);
                    JOptionPane.showMessageDialog(null,
                            "El archivo se a guardado Exitosamente",
                            "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                archivos.guardarArchivo(path, workTextArea.getText());
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Su archivo no se ha guardado",
                    "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_guardarMenuItemActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            if (!workTextArea.getText().replace(" ", "").isEmpty()) {
                if (path.isEmpty() || !workTextArea.getText().equals(archivos.lecturaArchivo(path))) {
                    Object[] opciones = {"Aceptar", "Cancelar"};
                    int eleccion = JOptionPane.showOptionDialog(rootPane, "Desea cerrar sin guardar?", "Mensaje de Confirmacion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE, null, opciones, "Aceptar");
                    if (eleccion == JOptionPane.YES_OPTION) {
                        System.exit(0);
                    }
                } else {
                    System.exit(0);
                }
            } else {
                System.exit(0);
            }
        } catch (HeadlessException | IOException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_formWindowClosing

    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "Desarrollado por: \nAngel O. Racancoj G. \nCarne: 201631547  \n2do Semestre 2017 \nVersion 0.1 (Beta)", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed

    private void nuevoMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nuevoMenuItemActionPerformed
        if (!workTextArea.getText().replace(" ", "").isEmpty()) {
            int respuesta = JOptionPane.showConfirmDialog(this, "Seguro que desea cerrar el trabajo realizado?", "Nuevo", JOptionPane.YES_NO_OPTION);
            if (respuesta == 0) {
                limpiar();
                JOptionPane.showMessageDialog(this, "Programa limpio", "Acerca de", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }//GEN-LAST:event_nuevoMenuItemActionPerformed

    private void workTextAreaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_workTextAreaKeyPressed
        evaluarLexemas(workTextArea.getText());
        actualizarListaObservable(listaLexemasAux);
    }//GEN-LAST:event_workTextAreaKeyPressed

    public void actualizarListaObservable(List<Lexema> listaLexe) {
        this.listaObservableLexemas.clear();
        this.listaObservableLexemas.addAll(listaLexe);
    }

    public void actualizarListaObservableSintaxis(List<SintaxisCorrecta.SitaxisId> listaLexe) {
        this.listaObservableSintaxis.clear();
        this.listaObservableSintaxis.addAll(listaLexe);
    }

    public ObservableList<Lexema> getListaObservableLexemas() {
        return listaObservableLexemas;
    }

    public void setListaObservableLexemas(ObservableList<Lexema> listaObservableLexemas) {
        this.listaObservableLexemas = listaObservableLexemas;
    }

    private void agregarListaLexema(String nombre, Token token, int colunma, int fila) {
        listaLexemasAux.add(new Lexema(nombre, token, colunma, fila));
    }

    private void evaluarLexemas(String textoEntrada) {
        listaLexemasAux.clear();
        boolean seguir = true;
        Lexer analizador = new Lexer(new StringReader(textoEntrada));
        while (seguir) {
            try {
                Token miToken = analizador.yylex();
                if (miToken == null) {
                    seguir = false;
                    break;
                }
                switch (miToken) {
                    case NUMERO:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case VARIABLE:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case ESCRIBIR:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case SUMA:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case MULTIPLICACION:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case FIN:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case POR_CADA:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case INICIAR:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case SI:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case COMILLA:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case ERROR:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        errores++;
                        break;
                    case VERDADERO:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case FALSO:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case ENTONCES:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case LITERAL:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case IGUAL:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case PARENTESIS_ABIERTO:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                    case PARENTESIS_CERRADO:
                        agregarListaLexema(analizador.getLexema(), miToken, analizador.getColumna(), analizador.getFila());
                        break;
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(this, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JMenuItem abrirMenuItem;
    private javax.swing.JMenuItem guardarMenuItem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JMenuItem lexicoMenuItem;
    private javax.swing.JMenuItem nuevoMenuItem;
    private javax.swing.JMenuItem sintacticoMenuItem;
    private javax.swing.JTextArea workTextArea;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    private void limpiar() {
        workTextArea.setText("");
        listaObservableLexemas.clear();
        listaLexemasAux.clear();
    }

    public ObservableList<SitaxisId> getListaObservableSintaxis() {
        return listaObservableSintaxis;
    }

    public void setListaObservableSintaxis(ObservableList<SitaxisId> listaObservableSintaxis) {
        this.listaObservableSintaxis = listaObservableSintaxis;
    }

}

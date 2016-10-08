/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.DatasetLogica;

/**
 *
 * @author crisd
 */
public class DatasetVista extends javax.swing.JFrame {

    FileWriter archivoCrear = null;
    File directorio = null;
    File archivo = null;
    DatasetLogica dslogic = new DatasetLogica();
    
    /**
     * Creates new form DatasetVista
     */
    public DatasetVista() {
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

        btnEntrenar = new javax.swing.JButton();
        btnProbar = new javax.swing.JButton();
        btnEstadisticas = new javax.swing.JButton();
        btnPruebasCiclicas = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtMensajes = new javax.swing.JTextArea();
        labelTitulo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEntrenar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnEntrenar.setText("Entrenar");
        btnEntrenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntrenarActionPerformed(evt);
            }
        });

        btnProbar.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnProbar.setText("Probar");
        btnProbar.setEnabled(false);
        btnProbar.setEnabled(false);
        btnProbar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProbarActionPerformed(evt);
            }
        });

        btnEstadisticas.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnEstadisticas.setText("Estadísticas");
        btnEstadisticas.setEnabled(false);
        btnEstadisticas.setEnabled(false);
        btnEstadisticas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEstadisticasActionPerformed(evt);
            }
        });

        btnPruebasCiclicas.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        btnPruebasCiclicas.setText("Pruebas Cíclicas");
        btnPruebasCiclicas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPruebasCiclicasActionPerformed(evt);
            }
        });

        jScrollPane1.setEnabled(false);

        txtMensajes.setEditable(false);
        txtMensajes.setColumns(20);
        txtMensajes.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        txtMensajes.setRows(5);
        jScrollPane1.setViewportView(txtMensajes);

        labelTitulo.setFont(new java.awt.Font("Comic Sans MS", 1, 24)); // NOI18N
        labelTitulo.setText("ACODSEVEC - Clustering TF-IDF");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEntrenar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelTitulo)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnProbar, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnPruebasCiclicas, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(labelTitulo)
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrenar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnProbar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEstadisticas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnPruebasCiclicas, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntrenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntrenarActionPerformed
        try {       
            //this.directorio = new File("C:\\Users\\crisd\\Desktop\\pruebas");            
            //this.directorio.mkdir();

            directorio = new File("C:\\Users\\crisd\\Desktop");
            
            String fichero = "Prueba.txt";            
            this.archivo = new File(directorio, fichero);
            archivo.createNewFile();
            this.archivoCrear = new FileWriter(archivo);
            
            dslogic.imprimirDiagnosticos(archivoCrear);
            dslogic.imprimirEnfermedades(archivoCrear);
            dslogic.imprimirSintomas(archivoCrear);
            dslogic.entrenamiento(archivoCrear);
            
            btnEntrenar.setEnabled(false);
            btnProbar.setEnabled(true);
            btnPruebasCiclicas.setEnabled(false);
            
            txtMensajes.setText(null);
            txtMensajes.setText("Entrenamiento realizado con éxito.");
        } catch (IOException ex) {
            Logger.getLogger(DatasetVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEntrenarActionPerformed

    private void btnProbarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProbarActionPerformed
        dslogic.pruebas(archivoCrear);
        
        btnProbar.setEnabled(false);        
        btnEstadisticas.setEnabled(true);
        
        txtMensajes.setText(null);
        txtMensajes.setText("Pruebas con el 20% de HC's realizada con éxito.");        
    }//GEN-LAST:event_btnProbarActionPerformed

    private void btnPruebasCiclicasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPruebasCiclicasActionPerformed
        File directorioPruebas = new File("C:\\Users\\crisd\\Desktop\\pruebas");        
        
        dslogic.pruebasCiclicas(directorioPruebas);
        
        txtMensajes.setText(null);
        txtMensajes.setText("Resultados de Pruebas de precisión unitarias y precisión promedio listos. Ver resultados en " + directorioPruebas);        
    }//GEN-LAST:event_btnPruebasCiclicasActionPerformed

    private void btnEstadisticasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEstadisticasActionPerformed
        try {
            dslogic.estadisticas(archivoCrear);
            
            btnEntrenar.setEnabled(true);
            btnProbar.setEnabled(false);
            btnEstadisticas.setEnabled(false);
            btnPruebasCiclicas.setEnabled(true);

            txtMensajes.setText(null);
            txtMensajes.setText("Resultados de Prueba listos. Ver fichero .txt en " + directorio);            
            
            archivoCrear.close();
            dslogic = new DatasetLogica();
        } catch (IOException ex) {
            Logger.getLogger(DatasetVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnEstadisticasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DatasetVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DatasetVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DatasetVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DatasetVista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatasetVista().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEntrenar;
    private javax.swing.JButton btnEstadisticas;
    private javax.swing.JButton btnProbar;
    private javax.swing.JButton btnPruebasCiclicas;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelTitulo;
    private javax.swing.JTextArea txtMensajes;
    // End of variables declaration//GEN-END:variables
}

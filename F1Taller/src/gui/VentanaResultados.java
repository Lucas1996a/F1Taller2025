package gui;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class VentanaResultados extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaResultados.class.getName());
    private final Gestion gestion;
    private final Pantalla pantallaAnterior;

    /**
     * Creates new form FormularioRegistro
     */
    public VentanaResultados(Gestion gestion, Pantalla pantallaAnterior) {
        initComponents();
        
        this.gestion = gestion;
        this.pantallaAnterior = pantallaAnterior;
        
        cargarAutoPilotos();
        cargarCarreras();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bntVolver = new javax.swing.JButton();
        titulo = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        comboCampoPiloto = new javax.swing.JComboBox<>();
        lblPiloto = new javax.swing.JLabel();
        lblAuto = new javax.swing.JLabel();
        btnGuardar = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        txtTiempoFinal = new javax.swing.JTextField();
        lblPiloto1 = new javax.swing.JLabel();
        comboCampoCarrera = new javax.swing.JComboBox<>();
        checkVueltaRapida = new javax.swing.JCheckBox();
        txtPosicion = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        fondoimg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        bg.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setLayout(null);

        bntVolver.setText("VOLVER");
        bntVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntVolverActionPerformed(evt);
            }
        });
        jPanel2.add(bntVolver);
        bntVolver.setBounds(6, 10, 90, 27);

        titulo.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titulo.setText("Registrar participación");
        titulo.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(titulo);
        titulo.setBounds(0, 30, 340, 60);

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 90));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboCampoPiloto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCampoPilotoActionPerformed(evt);
            }
        });
        jPanel3.add(comboCampoPiloto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 150, 30));

        lblPiloto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPiloto.setText("Participante:");
        jPanel3.add(lblPiloto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 110, 25));

        lblAuto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAuto.setText("Posicion final:");
        jPanel3.add(lblAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 25));

        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 100, 30));

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("Tiempo Final:");
        jPanel3.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 30));

        txtTiempoFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTiempoFinalActionPerformed(evt);
            }
        });
        jPanel3.add(txtTiempoFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 150, 30));

        lblPiloto1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPiloto1.setText("Carrera:");
        jPanel3.add(lblPiloto1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 110, 30));
        jPanel3.add(comboCampoCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 150, 30));

        checkVueltaRapida.setText("Vuelta Rápida");
        checkVueltaRapida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkVueltaRapidaActionPerformed(evt);
            }
        });
        jPanel3.add(checkVueltaRapida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 190, -1, -1));

        txtPosicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPosicionActionPerformed(evt);
            }
        });
        jPanel3.add(txtPosicion, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 150, 30));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 330, 300));

        fondoimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondoimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/f1logo.png"))); // NOI18N
        fondoimg.setPreferredSize(new java.awt.Dimension(310, 320));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        bg.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 0, 370, 400));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            // 1. Leer datos
            AutoPiloto participante = (AutoPiloto) comboCampoPiloto.getSelectedItem();
            Carrera carrera = (Carrera) comboCampoCarrera.getSelectedItem();
            String posStr = txtPosicion.getText();
            String tiempo = txtTiempoFinal.getText();
            boolean vueltaRapida = checkVueltaRapida.isSelected(); // Lee el checkbox

            // 2. Validación
            if (participante == null || carrera == null || posStr.isEmpty() || tiempo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar Participante, Carrera y completar Posición y Tiempo.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 3. Conversión
            int posicion = Integer.parseInt(posStr);

            // 4. Llamar a la lógica (la firma corregida)
            this.gestion.registrarResultadosCarrera(carrera, participante, posicion, tiempo, vueltaRapida);

            JOptionPane.showMessageDialog(this, "Resultado de carrera guardado con éxito.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "La 'Posición Final' debe ser un número válido.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    

    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        this.pantallaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    private void txtTiempoFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTiempoFinalActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTiempoFinalActionPerformed

    private void checkVueltaRapidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkVueltaRapidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_checkVueltaRapidaActionPerformed

    private void txtPosicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPosicionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPosicionActionPerformed

    private void comboCampoPilotoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCampoPilotoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCampoPilotoActionPerformed

    
    
    private void cargarAutoPilotos() {
        comboCampoPiloto.removeAllItems();
        ArrayList<AutoPiloto> lista = this.gestion.getListaAutoPilotos();
        if (lista != null) {
            for (AutoPiloto ap : lista) comboCampoPiloto.addItem(ap);
        }
    }
    
    private void cargarCarreras() {
        comboCampoCarrera.removeAllItems();
        ArrayList<Carrera> lista = this.gestion.getListaCarreras();
        if (lista != null) {
            for (Carrera c : lista) comboCampoCarrera.addItem(c);
        }
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton bntVolver;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JCheckBox checkVueltaRapida;
    private javax.swing.JComboBox<Carrera> comboCampoCarrera;
    private javax.swing.JComboBox<AutoPiloto> comboCampoPiloto;
    private javax.swing.JLabel fondoimg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAuto;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPiloto;
    private javax.swing.JLabel lblPiloto1;
    private javax.swing.JLabel titulo;
    private javax.swing.JTextField txtPosicion;
    private javax.swing.JTextField txtTiempoFinal;
    // End of variables declaration//GEN-END:variables

}
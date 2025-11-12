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
public class VentanaAsociar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaAsociar.class.getName());
    private final Gestion gestion;
    private final Pantalla pantallaAnterior;

    /**
     * Creates new form FormularioRegistro
     */
    public VentanaAsociar(Gestion gestion, Pantalla pantallaAnterior) {
        initComponents();
        
        this.gestion = gestion;
        this.pantallaAnterior = pantallaAnterior;
        
        cargarAutos();
        cargarPilotos();

    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bntVolver = new javax.swing.JButton();
        tituloRegistro = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        comboCampoPiloto = new javax.swing.JComboBox<>();
        comboCampoAuto = new javax.swing.JComboBox<>();
        lblPiloto = new javax.swing.JLabel();
        lblAuto = new javax.swing.JLabel();
        btnAsociar = new javax.swing.JButton();
        lblFecha = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
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

        tituloRegistro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tituloRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloRegistro.setText("Asociar auto a piloto");
        tituloRegistro.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(tituloRegistro);
        tituloRegistro.setBounds(0, 30, 340, 60);

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 330, 90));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        jPanel3.add(comboCampoPiloto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, 150, 30));
        jPanel3.add(comboCampoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 100, 150, 30));

        lblPiloto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPiloto.setText("Piloto:");
        jPanel3.add(lblPiloto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 110, 25));

        lblAuto.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblAuto.setText("Auto:");
        jPanel3.add(lblAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 100, 110, 25));

        btnAsociar.setText("ASOCIAR");
        btnAsociar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAsociarActionPerformed(evt);
            }
        });
        jPanel3.add(btnAsociar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 200, 100, 30));

        lblFecha.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblFecha.setText("Fecha de Asignacion:");
        jPanel3.add(lblFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 120, 20));

        txtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtFechaActionPerformed(evt);
            }
        });
        jPanel3.add(txtFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 150, 30));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 330, 300));

        fondoimg.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        fondoimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/f1logo.png"))); // NOI18N
        fondoimg.setPreferredSize(new java.awt.Dimension(310, 320));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE)
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

    private void btnAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarActionPerformed
        try{
             Auto auto = (Auto) comboCampoAuto.getSelectedItem(); 
             Piloto pil = (Piloto) comboCampoPiloto.getSelectedItem();
             String fecAs = txtFecha.getText();
             if (pil == null || auto == null || fecAs.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar todos los campos y completar la fecha.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
             
            Escuderia escuderiaDelAuto = auto.getEscuderia();
             
            Escuderia escuderiaDelPiloto = null;
            if (pil.getPilotoEscuderias() != null && !pil.getPilotoEscuderias().isEmpty()) {
            // (Esto es simple, toma el primer contrato que encuentra)
            // (Para hacerlo 100% robusto, deberías chequear la 'fecAs' contra 'desdeFecha' y 'hastaFecha')
            escuderiaDelPiloto = pil.getPilotoEscuderias().get(0).getEscuderia();
            }
            
            if (escuderiaDelPiloto == null || !escuderiaDelAuto.equals(escuderiaDelPiloto)) {
            String msg = String.format("Error de Validación:\nEl Piloto (%s) y el Auto (%s) no pertenecen a la misma escudería.",
                                       (escuderiaDelPiloto != null ? escuderiaDelPiloto.getNombre() : "Sin equipo"),
                                       escuderiaDelAuto.getNombre());
            throw new Exception(msg);
            }
            
            
            
             gestion.gestionarPilotoAuto(pil, auto, fecAs);
             JOptionPane.showMessageDialog(this, "El auto " + auto.getModelo() + " ha sido correctamente asociado al piloto: " + pil.getNombre());
        }   catch (Exception e){
            JOptionPane.showMessageDialog(this, "Error al guardar: " + e.getMessage());
        }
    }//GEN-LAST:event_btnAsociarActionPerformed
    

    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        this.pantallaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    
    
    private void cargarPilotos() {
    comboCampoPiloto.removeAllItems();
    ArrayList<Piloto> lista = gestion.getListaPilotos();
    if (lista != null) {
        for (Piloto p : lista) comboCampoPiloto.addItem(p);
    }
}
    
    private void cargarAutos() {
    comboCampoAuto.removeAllItems();
    ArrayList<Auto> lista = gestion.getListaAutos();
    if (lista != null) {
        for (Auto a : lista) comboCampoAuto.addItem(a);
    }
    
    }

    
    
    


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton bntVolver;
    private javax.swing.JButton btnAsociar;
    private javax.swing.JComboBox<Auto> comboCampoAuto;
    private javax.swing.JComboBox<Piloto> comboCampoPiloto;
    private javax.swing.JLabel fondoimg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblAuto;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblPiloto;
    private javax.swing.JLabel tituloRegistro;
    private javax.swing.JTextField txtFecha;
    // End of variables declaration//GEN-END:variables

}
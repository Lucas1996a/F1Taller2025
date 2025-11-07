package gui;

import logica.Controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class FormularioRegistro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioRegistro.class.getName());
    private final String tipoRegistro;
    private final Controlador control;

    /**
     * Creates new form FormularioRegistro
     */
    public FormularioRegistro(String tipo, Controlador control) {
        initComponents();
        
        this.tipoRegistro = tipo;
        this.control = control;

        // --- BLOQUE QUE OCULTA TODAS LAS VARIABLES PARA SOLO USAR LAS QUE NECESITAMOS ---
    
        lblCampo1.setVisible(false);
        txtCampo1.setVisible(false);
    
        lblCampo2.setVisible(false);
        txtCampo2.setVisible(false);
    
        lblCampo3.setVisible(false);
        txtCampo3.setVisible(false);
    
        lblCampo4.setVisible(false);
        txtCampo4.setVisible(false);
    
        lblCampo5.setVisible(false);
        txtCampo5.setVisible(false);
        
        lblCampo6.setVisible(false);
        txtCampo6.setVisible(false);
        
        lblCampo7.setVisible(false);
        txtCampo7.setVisible(false);
        
        lblCampo8.setVisible(false);
        txtCampo8.setVisible(false);
        
        switch (tipo) {
        case "PILOTO":
            this.setTitle("Registrar Piloto");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("DNI:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Nombre:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo3.setText("Apellido:");
            lblCampo3.setVisible(true); 
            txtCampo3.setVisible(true); 
            
            lblCampo4.setText("N° Competencia:");
            lblCampo4.setVisible(true); 
            txtCampo4.setVisible(true); 
            
            lblCampo5.setText("Victorias:");
            lblCampo5.setVisible(true); 
            txtCampo5.setVisible(true); 
            
            lblCampo6.setText("Pole Positions:");
            lblCampo6.setVisible(true); 
            txtCampo6.setVisible(true); 
            
            lblCampo7.setText("Vueltas Rápidas:");
            lblCampo7.setVisible(true); 
            txtCampo7.setVisible(true); 
            
            lblCampo8.setText("Podios:");
            lblCampo8.setVisible(true); 
            txtCampo8.setVisible(true); 
            
            break;
            
        case "MECANICO":
            this.setTitle("Registrar Mecánico");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("DNI:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Nombre:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo3.setText("Apellido:");
            lblCampo3.setVisible(true); 
            txtCampo3.setVisible(true); 
            
            lblCampo4.setText("Especialidad:");
            lblCampo4.setVisible(true); 
            txtCampo4.setVisible(true); 
            
            lblCampo5.setText("Años de experiencia:");
            lblCampo5.setVisible(true); 
            txtCampo5.setVisible(true); 
            
            lblCampo6.setText("País:");
            lblCampo6.setVisible(true); 
            txtCampo6.setVisible(true); 
            
            break;
            
            case "AUTO":
            this.setTitle("Registrar Auto");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("Modelo:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Motor:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo3.setText("Escuderia:");
            lblCampo3.setVisible(true); 
            txtCampo3.setVisible(true); 
            
            break;
            
            case "ESCUDERIA":
            this.setTitle("Registrar Escudería");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("Nombre:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("País:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            case "CIRCUITO":
            this.setTitle("Registrar Circuito");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("Nombre:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Longitud:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo2.setText("País:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            break;
            
            case "PAIS":
            this.setTitle("Registrar País");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("idPais:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Descripción:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            break;
    }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bntVolver = new javax.swing.JButton();
        tituloRegistro = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblCampo1 = new javax.swing.JLabel();
        lblCampo2 = new javax.swing.JLabel();
        lblCampo3 = new javax.swing.JLabel();
        lblCampo4 = new javax.swing.JLabel();
        lblCampo5 = new javax.swing.JLabel();
        txtCampo1 = new javax.swing.JTextField();
        txtCampo2 = new javax.swing.JTextField();
        txtCampo3 = new javax.swing.JTextField();
        txtCampo4 = new javax.swing.JTextField();
        txtCampo5 = new javax.swing.JTextField();
        bntGuardar = new javax.swing.JButton();
        lblCampo6 = new javax.swing.JLabel();
        txtCampo6 = new javax.swing.JTextField();
        txtCampo7 = new javax.swing.JTextField();
        lblCampo7 = new javax.swing.JLabel();
        lblCampo8 = new javax.swing.JLabel();
        txtCampo8 = new javax.swing.JTextField();
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
        tituloRegistro.setText("REGISTRO");
        tituloRegistro.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(tituloRegistro);
        tituloRegistro.setBounds(0, 0, 380, 60);

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 60));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblCampo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo1.setText("lbl1");
        jPanel3.add(lblCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 175, 25));

        lblCampo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo2.setText("lbl2");
        jPanel3.add(lblCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 175, 25));

        lblCampo3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo3.setText("lbl3");
        jPanel3.add(lblCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 175, 25));

        lblCampo4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo4.setText("lbl4");
        jPanel3.add(lblCampo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 175, 25));

        lblCampo5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo5.setText("lbl5");
        lblCampo5.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel3.add(lblCampo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, 175, 25));

        txtCampo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCampo1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 29, 150, -1));
        jPanel3.add(txtCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 61, 150, -1));
        jPanel3.add(txtCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 93, 150, -1));
        jPanel3.add(txtCampo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 125, 150, -1));
        jPanel3.add(txtCampo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 157, 150, -1));

        bntGuardar.setText("GUARDAR");
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(bntGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, -1));

        lblCampo6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo6.setText("lbl6");
        lblCampo6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblCampo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 175, 25));
        jPanel3.add(txtCampo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 189, 150, -1));
        jPanel3.add(txtCampo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 221, 150, -1));

        lblCampo7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo7.setText("lbl7");
        lblCampo7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblCampo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 220, 175, 25));

        lblCampo8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo8.setText("lbl8");
        jPanel3.add(lblCampo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 250, 175, 25));
        jPanel3.add(txtCampo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(204, 253, 150, -1));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 370, 340));

        fondoimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/f1logo.png"))); // NOI18N
        fondoimg.setPreferredSize(new java.awt.Dimension(310, 320));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        bg.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 320, 400));

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

    private void bntGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntGuardarActionPerformed
        
    }//GEN-LAST:event_bntGuardarActionPerformed

    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        VentanaRegistrar ventanaRegistrar = new VentanaRegistrar(this.control);
        ventanaRegistrar.setVisible(true);
        ventanaRegistrar.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    private void txtCampo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCampo1ActionPerformed
        
    }//GEN-LAST:event_txtCampo1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton bntGuardar;
    private javax.swing.JButton bntVolver;
    private javax.swing.JLabel fondoimg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCampo1;
    private javax.swing.JLabel lblCampo2;
    private javax.swing.JLabel lblCampo3;
    private javax.swing.JLabel lblCampo4;
    private javax.swing.JLabel lblCampo5;
    private javax.swing.JLabel lblCampo6;
    private javax.swing.JLabel lblCampo7;
    private javax.swing.JLabel lblCampo8;
    private javax.swing.JLabel tituloRegistro;
    private javax.swing.JTextField txtCampo1;
    private javax.swing.JTextField txtCampo2;
    private javax.swing.JTextField txtCampo3;
    private javax.swing.JTextField txtCampo4;
    private javax.swing.JTextField txtCampo5;
    private javax.swing.JTextField txtCampo6;
    private javax.swing.JTextField txtCampo7;
    private javax.swing.JTextField txtCampo8;
    // End of variables declaration//GEN-END:variables
}

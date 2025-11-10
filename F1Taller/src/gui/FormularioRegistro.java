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
public class FormularioRegistro extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioRegistro.class.getName());
    private final String tipoRegistro;
    private final Gestion gestion;
    private final VentanaRegistrar ventanaAnterior;

    /**
     * Creates new form FormularioRegistro
     */
    public FormularioRegistro(String tipo, Gestion gestion, VentanaRegistrar ventanaAnterior) {
        initComponents();
        
        this.tipoRegistro = tipo;
        this.gestion = gestion;
        this.ventanaAnterior = ventanaAnterior;
        
        cargarPaises();
        cargarEscuderias();
        cargarEspecialidades();

        // --- BLOQUE QUE OCULTA TODAS LAS VARIABLES PARA SOLO USAR LAS QUE NECESITAMOS ---
    
        lblCampo1.setVisible(false);
        txtCampo1.setVisible(false);
    
        lblCampo2.setVisible(false);
        comboCampo2.setVisible(false);
        txtCampo2.setVisible(false);
    
        lblCampo3.setVisible(false);
        comboCampo3.setVisible(false);
        txtCampo3.setVisible(false);
    
        lblCampo4.setVisible(false);
        comboCampo4.setVisible(false);
        txtCampo4.setVisible(false);
    
        lblCampo5.setVisible(false);
        txtCampo5.setVisible(false);
        comboCampoEsp.setVisible(false);
        
        lblCampo6.setVisible(false);
        txtCampo6.setVisible(false);
        
        lblCampo7.setVisible(false);
        txtCampo7.setVisible(false);
        
        lblCampo8.setVisible(false);
        txtCampo8.setVisible(false);
        
        lblCampo9.setVisible(false);
        txtCampo9.setVisible(false);
        
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
            
            lblCampo4.setText("País:");
            lblCampo4.setVisible(true); 
            comboCampo4.setVisible(true); 
            
            lblCampo5.setText("N° Competencia:");
            lblCampo5.setVisible(true); 
            txtCampo5.setVisible(true); 
            
            lblCampo6.setText("Victorias:");
            lblCampo6.setVisible(true); 
            txtCampo6.setVisible(true); 
            
            lblCampo7.setText("Pole Positions:");
            lblCampo7.setVisible(true); 
            txtCampo7.setVisible(true); 
            
            lblCampo8.setText("Vueltas Rápidas:");
            lblCampo8.setVisible(true); 
            txtCampo8.setVisible(true); 
            
            lblCampo9.setText("Podios:");
            lblCampo9.setVisible(true); 
            txtCampo9.setVisible(true); 
            
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
            
            lblCampo4.setText("País:");
            lblCampo4.setVisible(true); 
            comboCampo4.setVisible(true); 
            
            lblCampo5.setText("Especialidad:");
            lblCampo5.setVisible(true); 
            comboCampoEsp.setVisible(true); 
            
            lblCampo6.setText("Años de experiencia:");
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
            comboCampo3.setVisible(true); 
            
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
            comboCampo2.setVisible(true); 
            break;
            
            case "CIRCUITO":
            this.setTitle("Registrar Circuito");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("Nombre:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Longitud (km):");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo4.setText("País:");
            lblCampo4.setVisible(true); 
            comboCampo4.setVisible(true); 
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
            
            case "CARRERA":
            this.setTitle("Registrar Carrera");
            
            tituloRegistro.setText(tipo);
            
            // "Prendemos" y configuramos los campos para Piloto
            lblCampo1.setText("Fecha de realización:");
            lblCampo1.setVisible(true); 
            txtCampo1.setVisible(true); 
            
            lblCampo2.setText("Número de vueltas:");
            lblCampo2.setVisible(true); 
            txtCampo2.setVisible(true); 
            
            lblCampo3.setText("Hora de realización:");
            lblCampo3.setVisible(true); 
            txtCampo3.setVisible(true); 
            
            lblCampo4.setText("Circuito:");
            lblCampo4.setVisible(true); 
            txtCampo4.setVisible(true); 
            
            lblCampo5.setText("Pais:");
            lblCampo5.setVisible(true); 
            txtCampo5.setVisible(true); 
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
        txtCampo1 = new javax.swing.JTextField();
        comboCampo2 = new javax.swing.JComboBox<>();
        lblCampo2 = new javax.swing.JLabel();
        txtCampo2 = new javax.swing.JTextField();
        comboCampo3 = new javax.swing.JComboBox<>();
        lblCampo3 = new javax.swing.JLabel();
        txtCampo3 = new javax.swing.JTextField();
        lblCampo4 = new javax.swing.JLabel();
        comboCampo4 = new javax.swing.JComboBox<>();
        txtCampo4 = new javax.swing.JTextField();
        comboCampoEsp = new javax.swing.JComboBox<>();
        lblCampo5 = new javax.swing.JLabel();
        txtCampo5 = new javax.swing.JTextField();
        lblCampo6 = new javax.swing.JLabel();
        txtCampo6 = new javax.swing.JTextField();
        lblCampo7 = new javax.swing.JLabel();
        txtCampo7 = new javax.swing.JTextField();
        lblCampo8 = new javax.swing.JLabel();
        txtCampo8 = new javax.swing.JTextField();
        lblCampo9 = new javax.swing.JLabel();
        txtCampo9 = new javax.swing.JTextField();
        bntGuardar = new javax.swing.JButton();
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
        jPanel3.add(lblCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 175, 25));

        txtCampo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCampo1ActionPerformed(evt);
            }
        });
        jPanel3.add(txtCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 150, -1));
        jPanel3.add(comboCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));

        lblCampo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo2.setText("lbl2");
        jPanel3.add(lblCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 175, 25));
        jPanel3.add(txtCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, -1));
        jPanel3.add(comboCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));

        lblCampo3.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo3.setText("lbl3");
        jPanel3.add(lblCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 175, 25));
        jPanel3.add(txtCampo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 70, 150, -1));

        lblCampo4.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo4.setText("lbl4");
        jPanel3.add(lblCampo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 175, 25));
        jPanel3.add(comboCampo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 150, -1));
        jPanel3.add(txtCampo4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 100, 150, -1));

        comboCampoEsp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCampoEspActionPerformed(evt);
            }
        });
        jPanel3.add(comboCampoEsp, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 150, -1));

        lblCampo5.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo5.setText("lbl5");
        jPanel3.add(lblCampo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 175, 25));
        jPanel3.add(txtCampo5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 140, 150, -1));

        lblCampo6.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo6.setText("lbl6");
        lblCampo6.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblCampo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 170, 175, 25));
        jPanel3.add(txtCampo6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 170, 150, -1));

        lblCampo7.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo7.setText("lbl7");
        lblCampo7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel3.add(lblCampo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 175, 25));
        jPanel3.add(txtCampo7, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 150, -1));

        lblCampo8.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo8.setText("lbl8");
        jPanel3.add(lblCampo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 175, 25));
        jPanel3.add(txtCampo8, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 150, -1));

        lblCampo9.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo9.setText("lbl9");
        jPanel3.add(lblCampo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 175, 25));

        txtCampo9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCampo9ActionPerformed(evt);
            }
        });
        jPanel3.add(txtCampo9, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, 150, -1));

        bntGuardar.setText("GUARDAR");
        bntGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntGuardarActionPerformed(evt);
            }
        });
        jPanel3.add(bntGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 300, -1, -1));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 380, 340));

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
    try {
        switch (this.tipoRegistro) {
            case "PILOTO":
            // 1. Lee los datos de los text fields
                String dni = txtCampo1.getText();
                String nombrePil = txtCampo2.getText();
                String apellido = txtCampo3.getText();
                Pais paisPil = (Pais) comboCampo4.getSelectedItem();
                int numeroComp = Integer.parseInt(txtCampo5.getText());
                int victorias = Integer.parseInt(txtCampo6.getText());
                int poles = Integer.parseInt(txtCampo7.getText());
                int fastLap = Integer.parseInt(txtCampo8.getText());
                int podios =  Integer.parseInt(txtCampo9.getText());
            
            // 2. Llama a la controladora de lógica
                this.gestion.crearPilotos(dni, nombrePil, apellido, paisPil, numeroComp, victorias, poles, fastLap, podios);
                JOptionPane.showMessageDialog(this, "Piloto guardado correctamente.");
                break;
            
            case "PAIS":
                    String idStr = txtCampo1.getText();
                    String descripcion = txtCampo2.getText();
                    
                    // 3. Conversión de datos
                    // (El try-catch exterior se encargará del error si idStr no es un número)
                    int id = Integer.parseInt(idStr);
                    
                    // 4. Llama a la controladora de lógica (tu objeto 'gestion')
                    this.gestion.crearPais(id, descripcion);
                    
                    // 5. Mensaje de éxito
                    JOptionPane.showMessageDialog(this, "País guardado correctamente.");
                    
                    break;
            
            
            case "AUTO":
                    String modelo = txtCampo1.getText();
                    String motor = txtCampo2.getText();
                    Escuderia escuderia = (Escuderia) comboCampo3.getSelectedItem();
            
            
                    this.gestion.crearAutos(modelo,motor,escuderia);
                    JOptionPane.showMessageDialog(this, "Auto guardado correctamente.");
                    break;
//            
            case "ESCUDERIA":
                    String nombreEsc = txtCampo1.getText();
                    Pais paisEsc = (Pais) comboCampo2.getSelectedItem();
            
                    this.gestion.crearEscuderias(nombreEsc, paisEsc);
                    JOptionPane.showMessageDialog(this, "Escudería guardada correctamente.");
                    break;
//            
            case "CIRCUITO":
                    String nombreCircuito = txtCampo1.getText();
                    int longitud = Integer.parseInt(txtCampo2.getText());
                    Pais paisCircuito = (Pais) comboCampo4.getSelectedItem();

                    this.gestion.crearCircuitos(nombreCircuito, longitud, paisCircuito);
                    JOptionPane.showMessageDialog(this, "Circuito guardado correctamente.");
                    break;
//            
            case "MECANICO":
                    String dniMec = txtCampo1.getText();
                    String nombreMec = txtCampo2.getText();
                    String apellidoMec = txtCampo3.getText();
                    Pais paisMec = (Pais) comboCampo4.getSelectedItem();
                    Especialidad especialidad = (Especialidad) comboCampoEsp.getSelectedItem();
                    int aniosExp = Integer.parseInt(txtCampo6.getText());

                    this.gestion.crearMecanicos(dniMec, nombreMec, apellidoMec, paisMec, especialidad, aniosExp);
                    JOptionPane.showMessageDialog(this, "Mecánico guardado correctamente.");
                    break;
//            
//          case "MECANICO":
//              String nombreMec = txtCampo1.getText();
//              String apellidoMec = txtCampo2.getText();
//              String especialidad = txtCampo3.getText();
//            
//              // controladora.registrarMecanico(nombreMec, apellidoMec, especialidad);
//              break;
            }
    
    } catch (NumberFormatException e) {
    // Si 'parseInt' falla, el código salta acá y la app NO crashea.
        JOptionPane.showMessageDialog(this, "El ID debe ser un número válido.");
        }
         
    }//GEN-LAST:event_bntGuardarActionPerformed

    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        this.ventanaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    private void txtCampo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCampo1ActionPerformed
        
    }//GEN-LAST:event_txtCampo1ActionPerformed

    private void txtCampo9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCampo9ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCampo9ActionPerformed

    private void comboCampoEspActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCampoEspActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCampoEspActionPerformed

    private void cargarPaises() {
        try {
            // 1. Limpia el combo (borra "Item 1", "Item 2", etc.)
            comboCampo2.removeAllItems(); 
            comboCampo4.removeAllItems(); 
            
            // 2. Le pide la lista de países a tu objeto 'miGestion'
            //    (Esto usa el método que creamos en Gestion.java)
            ArrayList<Pais> listaDePaises = this.gestion.getListaPais();

            // 3. Recorre la lista y añade cada OBJETO 'Pais' al combo
            if (listaDePaises != null) {
                for (Pais p : listaDePaises) {
                    comboCampo2.addItem(p);
                    comboCampo4.addItem(p);
                }
            }
            
            // 4. (Opcional) Pone el primero como seleccionado
            if (comboCampo2.getItemCount() > 0) {
                comboCampo2.setSelectedIndex(0);
            }
            if (comboCampo4.getItemCount() > 0) {
                comboCampo4.setSelectedIndex(0);
            }

        } catch (Exception e) {
            // Muestra un error si 'miGestion' falla
            JOptionPane.showMessageDialog(this, 
                "Error fatal: No se pudo cargar la lista de países.", 
                "Error de Carga", 
                JOptionPane.ERROR_MESSAGE);
            
            // También usamos el 'logger' que ya tienes en tu clase
            logger.severe("Error al cargar países: " + e.getMessage());
        }
    }
    
    private void cargarEscuderias() {
        try {
            comboCampo3.removeAllItems();
            ArrayList<Escuderia> escuderias = this.gestion.getListaEscuderias();

        if (escuderias != null) {
            for (Escuderia e : escuderias) {
                comboCampo3.addItem(e);
            }
        }

        if (comboCampo3.getItemCount() > 0) {
            comboCampo3.setSelectedIndex(0);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error fatal: No se pudo cargar la lista de escuderías.", 
            "Error de Carga", 
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarEspecialidades() {
    try {
        comboCampoEsp.removeAllItems(); // Limpia el combo

        // Pide a la lógica todas las especialidades disponibles
        Especialidad[] especialidades = this.gestion.getListaEspecialidades();

        for (Especialidad e : especialidades) {
            comboCampoEsp.addItem(e);
        }

        if (comboCampoEsp.getItemCount() > 0) {
            comboCampoEsp.setSelectedIndex(0);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error fatal: No se pudo cargar la lista de especialidades.", 
            "Error de Carga", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton bntGuardar;
    private javax.swing.JButton bntVolver;
    private javax.swing.JComboBox<Pais> comboCampo2;
    private javax.swing.JComboBox<Escuderia> comboCampo3;
    private javax.swing.JComboBox<Pais> comboCampo4;
    private javax.swing.JComboBox<Especialidad> comboCampoEsp;
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
    private javax.swing.JLabel lblCampo9;
    private javax.swing.JLabel tituloRegistro;
    private javax.swing.JTextField txtCampo1;
    private javax.swing.JTextField txtCampo2;
    private javax.swing.JTextField txtCampo3;
    private javax.swing.JTextField txtCampo4;
    private javax.swing.JTextField txtCampo5;
    private javax.swing.JTextField txtCampo6;
    private javax.swing.JTextField txtCampo7;
    private javax.swing.JTextField txtCampo8;
    private javax.swing.JTextField txtCampo9;
    // End of variables declaration//GEN-END:variables
}

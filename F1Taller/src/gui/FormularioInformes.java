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
public class FormularioInformes extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(FormularioInformes.class.getName());
    private final String modo;
    private final Gestion gestion;
    private final VentanaInformes ventanaAnterior;

    /**
     * Creates new form FormularioRegistro
     */
    public FormularioInformes(String modo, Gestion gestion, VentanaInformes ventanaAnterior) {
        initComponents();
        
        this.modo = modo;
        this.gestion = gestion;
        this.ventanaAnterior = ventanaAnterior;
        
        cargarEscuderias();
        cargarAutos();
        cargarPilotos();
        cargarMecanicos();
        cargarCircuitos();

        // --- BLOQUE QUE OCULTA TODAS LAS VARIABLES PARA SOLO USAR LAS QUE NECESITAMOS ---
    
        lblCampo1.setVisible(false);
        comboCampoEsc.setVisible(false);
        comboCampoPiloto.setVisible(false);
        btnGenerarResultado.setVisible(false);
        btnGenerarTodosPil.setVisible(false);
        btnGenerarAutos.setVisible(false);
        btnGenerarRanking.setVisible(false);
        btnGenerarCarrera.setVisible(false);
        btnGenerarPilotoCirc.setVisible(false);
        btnGenerarMec.setVisible(false);
        btnPilotoDeterminado.setVisible(false);
        lblO.setVisible(false);
        
        lblCampo2.setVisible(false);
        comboCampoDeterminado.setVisible(false);
        comboCampoAuto.setVisible(false);
        comboCampoMecanico.setVisible(false);
        comboCampoCircuito.setVisible(false);
        comboCampoCarrera.setVisible(false);
    
        lblCampodF.setVisible(false);
        txtCampodF.setVisible(false);
    
        lblCampohF.setVisible(false);
        txtCampohF.setVisible(false);
        
        switch (modo) {
        case "RESULTADOS":
            this.setTitle("GENERAR RESULTADOS DETALLADOS DE CADA CARRERA");
            tituloRegistro.setText(modo);
            
            lblCampodF.setText("Desde fecha:");
            lblCampodF.setVisible(true);
            txtCampodF.setVisible(true);
    
            lblCampohF.setText("Hasta fecha:");
            lblCampohF.setVisible(true);
            txtCampohF.setVisible(true);
            
            btnGenerarResultado.setVisible(true);
            
            
            
            break;
            
        case "RANKING":
            this.setTitle("RANKING DE PILOTOS");
            tituloRegistro.setText(modo);
            
            try {
                    ArrayList<String> informe = this.gestion.generarRankingPilotos();
                    SalidaInforme vSalida = new SalidaInforme(informe, this.ventanaAnterior); // Importante: pasamos ventanaAnterior
                    vSalida.setVisible(true);
                    vSalida.setLocationRelativeTo(null);
                    this.dispose(); // Cierra esta ventana FormularioInformes
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Error al generar el ranking: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    this.ventanaAnterior.setVisible(true); // Vuelve a la ventana anterior
                    this.dispose(); // Cierra esta ventana FormularioInformes
                }
            break;
            
        case "HISTORICO":
            this.setTitle("ELEGIR TODOS LOS PILOTOS O PILOTO DETERMINADO");
            
            tituloRegistro.setText(modo);
            btnGenerarTodosPil.setVisible(true);
            btnPilotoDeterminado.setVisible(true);
            comboCampoDeterminado.setVisible(true);
            lblO.setVisible(true);
            break;
            
        case "AUTOS_ESCUDERIA":
            this.setTitle("AUTOS UTILIZADOS POR ESCUDERIA EN DISTINTAS CARRERAS");
            
            tituloRegistro.setText(modo);
            
            lblCampo1.setText("Escudería:");
            lblCampo1.setVisible(true); 
            comboCampoEsc.setVisible(true); 
            
            btnGenerarAutos.setVisible(true);
            
            break;
            
        case "INFO_MECANICOS":
            this.setTitle("INFORMACIÓN MECANICOS");
            
            tituloRegistro.setText(modo);
            
            lblCampo1.setText("Escudería:");
            lblCampo1.setVisible(true); 
            comboCampoEsc.setVisible(true); 
            
            btnGenerarMec.setVisible(true);
            
            break;
            
        case "CANTIDAD_VECES":
            this.setTitle("CANTIDAD DE CARRERAS DE UN PILOTO EN UN CIRCUITO DETERMINADO");
            
            tituloRegistro.setText(modo);
            
            lblCampo1.setText("Piloto:");
            lblCampo1.setVisible(true); 
            comboCampoPiloto.setVisible(true); 
            
            lblCampo2.setText("Circuito:");
            lblCampo2.setVisible(true); 
            comboCampoCircuito.setVisible(true); 
            
            
            btnGenerarPilotoCirc.setVisible(true);
            
            break;
            
        case "CANTIDAD_CARRERAS":
            this.setTitle("CANTIDAD DE CARRERAS QUE SE CORRIERON EN UN CIRCUITO DETERMINADO");
            
            tituloRegistro.setText(modo);
            
            lblCampo2.setText("Circuito:");
            lblCampo2.setVisible(true); 
            comboCampoCircuito.setVisible(true); 
            
            btnGenerarCarrera.setVisible(true);
            
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
        comboCampoEsc = new javax.swing.JComboBox<>();
        comboCampoDeterminado = new javax.swing.JComboBox<>();
        comboCampoAuto = new javax.swing.JComboBox<>();
        comboCampoMecanico = new javax.swing.JComboBox<>();
        comboCampoCircuito = new javax.swing.JComboBox<>();
        comboCampoCarrera = new javax.swing.JComboBox<>();
        comboCampoPiloto = new javax.swing.JComboBox<>();
        lblCampo1 = new javax.swing.JLabel();
        lblCampo2 = new javax.swing.JLabel();
        lblCampodF = new javax.swing.JLabel();
        lblCampohF = new javax.swing.JLabel();
        txtCampodF = new javax.swing.JTextField();
        txtCampohF = new javax.swing.JTextField();
        lblO = new javax.swing.JLabel();
        btnPilotoDeterminado = new javax.swing.JButton();
        btnGenerarCarrera = new javax.swing.JButton();
        btnGenerarRanking = new javax.swing.JButton();
        btnGenerarResultado = new javax.swing.JButton();
        btnGenerarPilotoCirc = new javax.swing.JButton();
        btnGenerarAutos = new javax.swing.JButton();
        btnGenerarTodosPil = new javax.swing.JButton();
        btnGenerarMec = new javax.swing.JButton();
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
        bntVolver.setBounds(6, 10, 90, 23);

        tituloRegistro.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        tituloRegistro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tituloRegistro.setText("Gestionar");
        tituloRegistro.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        jPanel2.add(tituloRegistro);
        tituloRegistro.setBounds(0, 0, 380, 60);

        bg.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 380, 60));

        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        comboCampoEsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCampoEscActionPerformed(evt);
            }
        });
        jPanel3.add(comboCampoEsc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, 30));

        comboCampoDeterminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCampoDeterminadoActionPerformed(evt);
            }
        });
        jPanel3.add(comboCampoDeterminado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 200, 190, 30));
        jPanel3.add(comboCampoAuto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, 30));

        comboCampoMecanico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCampoMecanicoActionPerformed(evt);
            }
        });
        jPanel3.add(comboCampoMecanico, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 150, 30));
        jPanel3.add(comboCampoCircuito, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 150, 30));
        jPanel3.add(comboCampoCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 150, 30));
        jPanel3.add(comboCampoPiloto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, 30));

        lblCampo1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo1.setText("lbl1");
        jPanel3.add(lblCampo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 25));

        lblCampo2.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampo2.setText("lbl2");
        jPanel3.add(lblCampo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 25));

        lblCampodF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampodF.setText("lbl2");
        jPanel3.add(lblCampodF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 110, 20));

        lblCampohF.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblCampohF.setText("lbl2");
        jPanel3.add(lblCampohF, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 110, 30));
        jPanel3.add(txtCampodF, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 150, 30));
        jPanel3.add(txtCampohF, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 150, 30));

        lblO.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblO.setText("O");
        jPanel3.add(lblO, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 20, 20));

        btnPilotoDeterminado.setText("REALIZAR DETERMINADO");
        btnPilotoDeterminado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPilotoDeterminadoActionPerformed(evt);
            }
        });
        jPanel3.add(btnPilotoDeterminado, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 190, 30));

        btnGenerarCarrera.setText("GENERAR INFORME");
        btnGenerarCarrera.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarCarreraActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarCarrera, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 30));

        btnGenerarRanking.setText("GENERAR RANKING");
        btnGenerarRanking.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarRankingActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarRanking, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 30));

        btnGenerarResultado.setText("GENERAR RESULTADOS");
        btnGenerarResultado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarResultadoActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarResultado, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 30));

        btnGenerarPilotoCirc.setText("GENERAR INFORME");
        btnGenerarPilotoCirc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarPilotoCircActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarPilotoCirc, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 30));

        btnGenerarAutos.setText("GENERAR INFORME");
        btnGenerarAutos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarAutosActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarAutos, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 160, 30));

        btnGenerarTodosPil.setText("TODOS LOS PILOTOS");
        btnGenerarTodosPil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarTodosPilActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarTodosPil, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 90, 160, 30));

        btnGenerarMec.setText("GENERAR INFORME");
        btnGenerarMec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGenerarMecActionPerformed(evt);
            }
        });
        jPanel3.add(btnGenerarMec, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 170, 30));

        bg.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 380, 340));

        fondoimg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/f1logo.png"))); // NOI18N
        fondoimg.setPreferredSize(new java.awt.Dimension(310, 320));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(fondoimg, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(fondoimg, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );

        bg.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 0, 380, 400));

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

    private void btnGenerarResultadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarResultadoActionPerformed
     try {
        // 1. Leemos las fechas de los JTextFields
        String fechaInicio = txtCampodF.getText(); // Asumo que se llama txtFechaInicio
        String fechaFin = txtCampohF.getText();       // Asumo que se llama txtFechaFin

        // 2. Validación
        if (fechaInicio.isEmpty() || fechaFin.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar una fecha de inicio y una fecha de fin.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // (Opcional: validación de formato AAAA-MM-DD)
//        if (!fechaInicio.matches("\\d{4}-\\d{2}-\\d{2}") || !fechaFin.matches("\\d{4}-\\d{2}-\\d{2}")) {
//             JOptionPane.showMessageDialog(this, "El formato debe ser AAAA-MM-DD (ej: 2025-03-05).", "Error de Formato", JOptionPane.ERROR_MESSAGE);
//             return;
//        }

        // 3. Llamamos al método de la lógica
        ArrayList<String> informe = this.gestion.generarInformeResultadosPorFecha(fechaInicio, fechaFin);

        // 4. Creamos y mostramos la nueva ventana de resultados
        SalidaInforme vSalida = new SalidaInforme(informe, this); // Le pasamos el informe y esta ventana
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
        
        this.dispose(); // Ocultamos esta ventana (VentanaInformes)

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al generar el informe: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
    }
        
    }//GEN-LAST:event_btnGenerarResultadoActionPerformed
    
    
    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        this.ventanaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    private void btnPilotoDeterminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPilotoDeterminadoActionPerformed
        try {
        // 1. Obtiene el piloto seleccionado
        Piloto piloto = (Piloto) comboCampoDeterminado.getSelectedItem();
        
        if (piloto == null) {
            throw new Exception("Debe seleccionar un piloto de la lista.");
        }

        // 2. Llama al método de Gestion (¡este ya lo tenés!)
        ArrayList<String> informe = this.gestion.generarHistoricoPilotoIndividual(piloto.getDni());
        
        // 3. Abre la ventana de salida
        SalidaInforme vSalida = new SalidaInforme(informe, this);
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
        this.dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al generar el informe: " + e.getMessage());
    }
    }//GEN-LAST:event_btnPilotoDeterminadoActionPerformed

    private void btnGenerarTodosPilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarTodosPilActionPerformed
        try {
        // 1. Llama al NUEVO método de Gestion (el que acabamos de crear)
        ArrayList<String> informe = this.gestion.generarHistoricoTodosPilotos();
        
        // 2. Crea la ventana de salida
        // Le pasamos el informe (ArrayList<String>) y 'this' (el FormularioInformes)
        SalidaInforme vSalida = new SalidaInforme(informe, this);
        
        // 3. Muestra la ventana de salida
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
        
        // 4. Cierra esta ventana (FormularioInformes)
        this.dispose(); 

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al generar el informe: " + e.getMessage());
    }
    }//GEN-LAST:event_btnGenerarTodosPilActionPerformed

    private void btnGenerarRankingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarRankingActionPerformed
        try {
        // 1. Llama al método de Gestion que genera el informe
        ArrayList<String> informe = this.gestion.generarRankingPilotos();
        
        // 2. Crea la ventana de salida
        // Le pasamos el informe (ArrayList<String>) y 'this' (el FormularioInformes)
        SalidaInforme vSalida = new SalidaInforme(informe, this);
        
        // 3. La muestra y oculta esta
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
        this.dispose();

    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, "Error al generar el informe de Ranking: " + e.getMessage());
    }
    }//GEN-LAST:event_btnGenerarRankingActionPerformed

    private void btnGenerarAutosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarAutosActionPerformed
        // 1. Obtener el OBJETO seleccionado del combo
    // (Revisá que 'jComboEscuderia' sea el nombre de tu variable)
    Object itemEscuderia = comboCampoEsc.getSelectedItem();

    // 2. Validar que se haya seleccionado algo
    if (itemEscuderia == null) {
        javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar una escudería.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // 3. Convertir el 'Object' a 'Escuderia'
    Escuderia escuderiaSeleccionada = (Escuderia) itemEscuderia;

    // 4. Llamar al NUEVO método de Gestion (el que creamos en el Paso 1)
    ArrayList<String> informe = this.gestion.generarInformeAutosEnCarreras(escuderiaSeleccionada);

    // 5. Crear y mostrar la ventana de salida
    SalidaInforme vSalida = new SalidaInforme(informe, this);
    vSalida.setVisible(true);
    vSalida.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGenerarAutosActionPerformed

    private void btnGenerarPilotoCircActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarPilotoCircActionPerformed
        // 1. Obtener los OBJETOS seleccionados de los combos
        // (Asegúrate de que los nombres de variables jComboPiloto y jComboCircuito sean correctos)
        Object itemPiloto = comboCampoPiloto.getSelectedItem();
        Object itemCircuito = comboCampoCircuito.getSelectedItem();

        // 2. Validar que se haya seleccionado AMBOS
        if (itemPiloto == null || itemCircuito == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar un piloto Y un circuito.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Convertir los 'Objects' a sus tipos correctos
        Piloto pilotoSeleccionado = (Piloto) itemPiloto;
        Circuito circuitoSeleccionado = (Circuito) itemCircuito;

        // 4. Extraer los Strings que el método de Gestion necesita
        String dniPiloto = pilotoSeleccionado.getDni();
        String nombreCircuito = circuitoSeleccionado.getNombre();

        // 5. Llamar al método de Gestion que modificamos en el Paso 1
        ArrayList<String> informe = this.gestion.generarInformePilotoEnCircuito(dniPiloto, nombreCircuito);

        // 6. Crear y mostrar la ventana de salida
        SalidaInforme vSalida = new SalidaInforme(informe, this);
        vSalida.setVisible(true); 
        vSalida.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGenerarPilotoCircActionPerformed

    private void btnGenerarCarreraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarCarreraActionPerformed
        // 1. Obtener el *objeto* seleccionado del ComboBox
        // El JComboBox devuelve un 'Object', que en tu caso es un 'Circuito'
        Object itemSeleccionado = comboCampoCircuito.getSelectedItem(); // REEMPLAZÁ 'jComboCircuito' si se llama distinto

        // 2. Validar que se haya seleccionado algo
        if (itemSeleccionado == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "No se ha seleccionado ningún circuito.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        // En lugar de (String), convertimos el 'Object' a 'Circuito'
        Circuito circuitoSeleccionado = (Circuito) itemSeleccionado;
    
        // 4. Ahora que tenemos el objeto, obtenemos el String (el nombre)
        String nombreCircuito = circuitoSeleccionado.getNombre();

        // 5. Llamar al método de Gestion (esta parte ya estaba bien)
        ArrayList<String> informe = this.gestion.generarInformeCarrerasEnCircuito(nombreCircuito);
    
        // 6. Crear la ventana de salida
        SalidaInforme vSalida = new SalidaInforme(informe, this);
    
        // 7. Mostrar la ventana de informe
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGenerarCarreraActionPerformed


    private void btnGenerarMecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGenerarMecActionPerformed
        // 1. Obtener el OBJETO seleccionado del combo
        // (Revisá que 'jComboEscuderia' sea el nombre de tu variable)
        Object itemEscuderia = comboCampoEsc.getSelectedItem();

        // 2. Validar que se haya seleccionado algo
        if (itemEscuderia == null) {
            javax.swing.JOptionPane.showMessageDialog(this, "Debe seleccionar una escudería.", "Error", javax.swing.JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 3. Convertir el 'Object' a 'Escuderia' (como aprendimos en el error anterior)
        Escuderia escuderiaSeleccionada = (Escuderia) itemEscuderia;

        // 4. Llamar al NUEVO método de Gestion (el que creamos en el Paso 1)
        ArrayList<String> informe = this.gestion.generarInformeMecanicos(escuderiaSeleccionada);

        // 5. Crear y mostrar la ventana de salida
        SalidaInforme vSalida = new SalidaInforme(informe, this);
        vSalida.setVisible(true);
        vSalida.setLocationRelativeTo(null);
    }//GEN-LAST:event_btnGenerarMecActionPerformed

    private void comboCampoMecanicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCampoMecanicoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCampoMecanicoActionPerformed

    private void comboCampoEscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCampoEscActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCampoEscActionPerformed

    private void comboCampoDeterminadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCampoDeterminadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCampoDeterminadoActionPerformed

    private void cargarEscuderias() {
        try {
            comboCampoEsc.removeAllItems();
            ArrayList<Escuderia> escuderias = this.gestion.getListaEscuderias();

        if (escuderias != null) {
            for (Escuderia e : escuderias) {
                comboCampoEsc.addItem(e);
            }
        }

        if (comboCampoEsc.getItemCount() > 0) {
            comboCampoEsc.setSelectedIndex(0);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error fatal: No se pudo cargar la lista de escuderías.", 
            "Error de Carga", 
            JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarPilotos() {
    comboCampoDeterminado.removeAllItems();
    comboCampoPiloto.removeAllItems();
    ArrayList<Piloto> lista = gestion.getListaPilotos();
    if (lista != null) {
        for (Piloto p : lista){
            comboCampoDeterminado.addItem(p);
            comboCampoPiloto.addItem(p);
        }
    }
}
    
    private void cargarAutos() {
    comboCampoAuto.removeAllItems();
    ArrayList<Auto> lista = gestion.getListaAutos();
    if (lista != null) {
        for (Auto a : lista) comboCampoAuto.addItem(a);
    }
    }
    
    private void cargarMecanicos() {
    comboCampoMecanico.removeAllItems();
    ArrayList<Mecanico> lista = gestion.getListaMecanicos();
    if (lista != null) {
        for (Mecanico m : lista) comboCampoMecanico.addItem(m);
        }
    }
    
    private void cargarCircuitos() {
    comboCampoCircuito.removeAllItems();
    ArrayList<Circuito> lista = gestion.getListaCircuitos();
    if (lista != null) {
        for (Circuito c : lista) comboCampoCircuito.addItem(c);
        }
    }
    
    
    
    
    
    
    

    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton bntVolver;
    private javax.swing.JButton btnGenerarAutos;
    private javax.swing.JButton btnGenerarCarrera;
    private javax.swing.JButton btnGenerarMec;
    private javax.swing.JButton btnGenerarPilotoCirc;
    private javax.swing.JButton btnGenerarRanking;
    private javax.swing.JButton btnGenerarResultado;
    private javax.swing.JButton btnGenerarTodosPil;
    private javax.swing.JButton btnPilotoDeterminado;
    private javax.swing.JComboBox<Auto> comboCampoAuto;
    private javax.swing.JComboBox<Carrera> comboCampoCarrera;
    private javax.swing.JComboBox<Circuito> comboCampoCircuito;
    private javax.swing.JComboBox<Piloto> comboCampoDeterminado;
    private javax.swing.JComboBox<Escuderia> comboCampoEsc;
    private javax.swing.JComboBox<Mecanico> comboCampoMecanico;
    private javax.swing.JComboBox<Piloto> comboCampoPiloto;
    private javax.swing.JLabel fondoimg;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel lblCampo1;
    private javax.swing.JLabel lblCampo2;
    private javax.swing.JLabel lblCampodF;
    private javax.swing.JLabel lblCampohF;
    private javax.swing.JLabel lblO;
    private javax.swing.JLabel tituloRegistro;
    private javax.swing.JTextField txtCampodF;
    private javax.swing.JTextField txtCampohF;
    // End of variables declaration//GEN-END:variables
}

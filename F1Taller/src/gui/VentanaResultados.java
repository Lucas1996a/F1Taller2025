package gui;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.*;

/**
 * Representa la ventana (JFrame) dedicada a registrar el resultado
 * ({@link logica.ResultadoCarrera}) de un participante en una carrera.
 *
 * Esta interfaz recopila el participante (un {@link logica.AutoPiloto}),
 * la {@link logica.Carrera}, la posición final, el tiempo y si obtuvo
 * la vuelta rápida.
 *
 * Incluye validaciones robustas para prevenir la duplicación de datos, como:
 * - Que un piloto no esté registrado dos veces en la misma carrera.
 * - Que una posición no esté ocupada dos veces en la misma carrera.
 * - Que la vuelta rápida no se asigne a más de un piloto por carrera.
 *
 * @author Admin 
 */
public class VentanaResultados extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaResultados.class.getName());
    /** Referencia al controlador principal de la lógica de negocio ({@link Gestion}). */
    private final Gestion gestion;
    /** Referencia a la pantalla principal ({@link Pantalla}) que la invocó, para poder volver. */
    private final Pantalla pantallaAnterior;

    /**
     * Crea un nuevo formulario VentanaResultados.
     *
     * @param gestion La instancia del controlador de lógica principal ({@link Gestion}).
     * @param pantallaAnterior La pantalla principal ({@link Pantalla}) a la que se debe volver.
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

    /**
     * Manejador del evento del botón 'GUARDAR'.
     * Es el controlador principal de esta ventana.
     * 1. Llama a {@link #validarFormularioResultados()} para revisar los datos.
     * 2. Si la validación falla, captura la Excepción y muestra el error.
     * 3. Si la validación tiene éxito, llama a {@link #guardarResultado()} para
     * crear el resultado y persistirlo.
     * 4. Muestra un mensaje de éxito.
     *
     * @param evt El evento de acción.
     */
    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
            try {
            // 1. Llama a todas las validaciones
            // (Si algo falla, salta directamente al 'catch')
            validarFormularioResultados();

            // 2. Si las validaciones pasaron, llama a guardar
            guardarResultado();

            // 3. Muestra el mensaje de éxito
            JOptionPane.showMessageDialog(this, "Resultado de carrera guardado con éxito.");

        } catch (Exception e) {
            // 4. Muestra CUALQUIER error de validación que haya ocurrido
            JOptionPane.showMessageDialog(this, 
                "Error al guardar: " + e.getMessage(), // Muestra el mensaje de nuestra validación
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed
    
    /**
     * Manejador del botón 'Volver'.
     * Cierra (dispose) esta ventana y vuelve a hacer visible la pantalla
     * principal ({@link Pantalla}).
     * @param evt El evento de acción.
     */
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

    
    /**
     * Carga la lista de {@link AutoPiloto} (participantes) desde la capa
     * de gestión y las añade al JComboBox 'comboCampoPiloto'.
     */
    private void cargarAutoPilotos() {
        comboCampoPiloto.removeAllItems();
        ArrayList<AutoPiloto> lista = this.gestion.getListaAutoPilotos();
        if (lista != null) {
            for (AutoPiloto ap : lista) comboCampoPiloto.addItem(ap);
        }
    }
    
    /**
     * Carga la lista de {@link Carrera} (eventos) desde la capa de gestión
     * y las añade al JComboBox 'comboCampoCarrera'.
     */
    private void cargarCarreras() {
        comboCampoCarrera.removeAllItems();
        ArrayList<Carrera> lista = this.gestion.getListaCarreras();
        if (lista != null) {
            for (Carrera c : lista) comboCampoCarrera.addItem(c);
        }
    }
    
    
    //VALIDACIONES
    
    /**
     * Ejecuta un conjunto de reglas de validación para el formulario de
     * registro de resultados.
     * Comprueba:
     * 1. Campos vacíos.
     * 2. Posición: Numérica y en el rango [1, 20].
     * 3. Tiempo: Formato HHMMSS (6 dígitos), rango [01:00:00 - 02:59:59].
     * 4. Duplicado de Piloto: Que el piloto no tenga ya un resultado en esa
     * carrera.
     * 5. Duplicado de Posición: Que la posición no esté ya ocupada en esa
     * carrera.
     * 6. Duplicado de Vuelta Rápida: Que la vuelta rápida no esté ya asignada
     * en esa carrera.
     *
     * @throws Exception Si alguna regla de validación no se cumple. El mensaje
     * de la excepción está listo para mostrarse al usuario.
     */
    private void validarFormularioResultados() throws Exception {
    
        // --- 1. LECTURA DE DATOS ---
        Object partObj = comboCampoPiloto.getSelectedItem();
        Object carrObj = comboCampoCarrera.getSelectedItem();
        String posStr = txtPosicion.getText().trim();
        String tiempoStr = txtTiempoFinal.getText().trim();
        
        boolean quiereMarcarVueltaRapida = checkVueltaRapida.isSelected();

        // --- 2. VALIDACIÓN DE CAMPOS VACÍOS ---
        if (partObj == null || carrObj == null || posStr.isEmpty() || tiempoStr.isEmpty()) {
            throw new Exception("Debe seleccionar un Participante, una Carrera y completar la Posición y el Tiempo.");
        }

        // --- 3. VALIDACIÓN DE POSICIÓN (Reglas: 1-20, solo números) ---
        int pos;
        try {
            pos = Integer.parseInt(posStr);
        } catch (NumberFormatException e) {
            throw new Exception("La 'Posición final' debe ser un número (no se permiten letras).");
        }

        // ¡Tu validación de 1 a 20!
        if (pos < 1 || pos > 20) {
            throw new Exception("La 'Posición final' debe ser un número entre 1 y 20.");
        }

        // --- 4. VALIDACIÓN DE TIEMPO (Reglas: >1h, <3h, formato HHMMSS) ---
        if (!tiempoStr.matches("\\d{6}")) {
            throw new Exception("El 'Tiempo Final' debe tener 6 números en formato HHMMSS (ej: 012545).");
        }

        try {
            int h = Integer.parseInt(tiempoStr.substring(0, 2)); // HH
            int m = Integer.parseInt(tiempoStr.substring(2, 4)); // MM
            int s = Integer.parseInt(tiempoStr.substring(4, 6)); // SS
            if (h < 1) { // 00:59:59 no es válido
                throw new Exception("El tiempo debe ser mayor a 1 hora (HH debe ser 01 o 02).");
            }
            if (h >= 3) { // 03:00:00 no es válido
                throw new Exception("El tiempo debe ser menor a 3 horas (HH debe ser 01 o 02).");
            }

            // Validación lógica de minutos y segundos
            if (m > 59 || s > 59) {
                throw new Exception("Los Minutos (MM) o Segundos (SS) deben estar entre 00 y 59.");
            }

        } catch (Exception e) {
            // Si fallan los substrings o las validaciones de rango
            throw new Exception("El formato del Tiempo (HHMMSS) es inválido.");
        }

        // --- 5. VALIDACIÓN DE DUPLICADOS ---

        AutoPiloto autoPiloto = (AutoPiloto) partObj;
        Carrera carrera = (Carrera) carrObj;
        Piloto piloto = autoPiloto.getPiloto(); // El piloto real

        // Traemos la lista de resultados que ya existen
        ArrayList<ResultadoCarrera> resultados = this.gestion.getListaResultados();

        if (resultados != null) {
            for (ResultadoCarrera res : resultados) {

                // Comparamos el piloto y la carrera de CADA resultado existente
                boolean mismoPiloto = res.getAutoPiloto().getPiloto().equals(piloto);
                boolean mismaCarrera = res.getCarrera().equals(carrera);

                // Si encontramos un resultado donde el piloto Y la carrera coinciden...
                if (mismoPiloto && mismaCarrera) {
                    throw new Exception("Error de Lógica: El piloto " + piloto.getApellido() + 
                                      " ya tiene un resultado registrado para esta carrera.");
                }
                
                int posExistente = res.getPosicionFinal(); 

                // Si es la misma carrera Y la posición ya está ocupada por OTRO piloto...
                if (mismaCarrera && (posExistente == pos)) {
                    throw new Exception("Error de Lógica: La posición " + pos + 
                                        " ya está ocupada por el piloto " + res.getAutoPiloto().getPiloto().getApellido() +
                                        " en esta carrera.");
                }
                
                boolean otroYaLaTiene = res.isVueltaRapida(); 
                
                // Si queremos marcar la vuelta rápida Y es la misma carrera Y otro ya la tiene...
                if (quiereMarcarVueltaRapida && mismaCarrera && otroYaLaTiene) {
                    throw new Exception("Error de Lógica: La vuelta rápida para esta carrera" +
                                        " ya fue registrada por el piloto " + res.getAutoPiloto().getPiloto().getApellido() + ".");
                }
                
            }
        }
    }
    
    /**
     * Lee los datos (ya validados) del formulario y llama a la capa de
     * 'gestion' para crear y persistir el nuevo resultado de carrera.
     * Finalmente, limpia los campos del formulario para un nuevo ingreso.
     */
    private void guardarResultado() {
        // 1. Leemos los datos (ya sabemos que son válidos)
        AutoPiloto participante = (AutoPiloto) comboCampoPiloto.getSelectedItem();
        Carrera carrera = (Carrera) comboCampoCarrera.getSelectedItem();
        int posicion = Integer.parseInt(txtPosicion.getText()); // Es seguro
        String tiempo = txtTiempoFinal.getText();
        boolean vueltaRapida = checkVueltaRapida.isSelected(); 

        // 2. Llamamos a la lógica
        this.gestion.registrarResultadosCarrera(carrera, participante, posicion, tiempo, vueltaRapida);

        // 3. Limpiamos los campos para el próximo registro
        txtPosicion.setText("");
        txtTiempoFinal.setText("");
        checkVueltaRapida.setSelected(false);
        if (comboCampoPiloto.getItemCount() > 0) comboCampoPiloto.setSelectedIndex(0);
        if (comboCampoCarrera.getItemCount() > 0) comboCampoCarrera.setSelectedIndex(0);
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
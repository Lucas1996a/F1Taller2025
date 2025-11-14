package gui;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import logica.*;

/**
 * Representa la ventana (JFrame) dedicada a crear la asociación N-a-N
 * entre un {@link Piloto} y un {@link Auto} (la entidad {@link AutoPiloto}).
 *
 * Esta pantalla valida reglas de negocio críticas antes de asociar, tales como:
 * 1. Que el piloto no esté ya asociado a OTRO auto (Regla 1-a-1).
 * 2. Que el piloto tenga un contrato vigente con la escudería a la que
 * pertenece el auto.
 * 3. Que el formato de fecha (YYYYMMDD) sea válido.
 *
 * @author Admin 
 */
public class VentanaAsociar extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VentanaAsociar.class.getName());
    /** Referencia al controlador principal de la lógica de negocio. */
    private final Gestion gestion;
    /** Referencia a la pantalla principal ({@link Pantalla}) que invocó este formulario. */
    private final Pantalla pantallaAnterior;

    /**
     * Crea un nuevo formulario VentanaAsociar.
     *
     * @param gestion La instancia del controlador de lógica principal ({@link Gestion}).
     * @param pantallaAnterior La pantalla principal ({@link Pantalla}) a la que se debe volver.
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

    /**
     * Manejador del evento del botón 'ASOCIAR'.
     * Desencadena una serie de validaciones antes de guardar:
     * 1. Valida que los campos no estén vacíos.
     * 2. Valida el formato de fecha (YYYYMMDD) y rangos lógicos (mes/día).
     * 3. Valida la lógica de negocio (1-a-1): que el piloto no esté ya
     * asociado a *otro* auto.
     * 4. Valida la lógica de equipo: que el piloto tenga un contrato
     * (PilotoEscuderia) con la escudería propietaria del auto.
     *
     * Si todas las validaciones pasan, llama a 'gestion.gestionarPilotoAuto()'
     * y muestra un mensaje de éxito.
     *
     * @param evt El evento de acción.
     */
    private void btnAsociarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAsociarActionPerformed
            try {
            // --- 1. LECTURA DE DATOS ---
            Auto auto = (Auto) comboCampoAuto.getSelectedItem();
            Piloto pil = (Piloto) comboCampoPiloto.getSelectedItem();
            String fecAs = txtFecha.getText().trim(); // Usamos .trim()

            // --- 2. VALIDACIÓN DE CAMPOS VACÍOS ---
            if (pil == null || auto == null || fecAs.isEmpty()) {
                throw new Exception("Debe seleccionar un Piloto, un Auto y completar la Fecha.");
            }

            // --- 3. VALIDACIÓN DE FECHA (Formato YYYYMMDD y Lógica) ---
            if (!fecAs.matches("\\d{8}")) {
                throw new Exception("La 'Fecha' debe tener 8 números en formato YYYYMMDD (ej: 20251026).");
            }
            try {
                int mes = Integer.parseInt(fecAs.substring(4, 6)); // (MM)
                int dia = Integer.parseInt(fecAs.substring(6, 8)); // (DD)

                if (mes < 1 || mes > 12) {
                    throw new Exception("El Mes (MM) en la fecha debe estar entre 01 y 12.");
                }
                if (dia < 1 || dia > 31) {
                    throw new Exception("El Día (DD) en la fecha debe estar entre 01 y 31.");
                }
            } catch (NumberFormatException e) {
                throw new Exception("La fecha '" + fecAs + "' tiene un formato numérico inválido.");
            }
            // --- (Fin de validación de fecha) ---


            // --- 4. VALIDACIÓN LÓGICA  ---
            Auto autoYaAsociado = null;
            // Recorremos la lista de todas las asociaciones existentes
            for (AutoPiloto ap : gestion.getListaAutoPilotos()) {
                if (ap.getPiloto().equals(pil)) {
                    // Si encontramos a este piloto, guardamos el auto que YA tiene
                    autoYaAsociado = ap.getAuto();
                    break; 
                }
            }

            // Si encontramos un auto (autoYaAsociado no es null)
            // Y ESE auto es DIFERENTE al que estamos intentando asociar...
            if (autoYaAsociado != null && !autoYaAsociado.equals(auto)) {
                throw new Exception("Error de Lógica: El piloto " + pil.getNombre() + 
                                  " ya está asociado a otro auto (" + autoYaAsociado.getModelo() + ").");
            }
            // --- (Fin de validación de piloto duplicado) ---


            // --- 5. VALIDACIÓN DE EQUIPO ---
            Escuderia escuderiaDelAuto = auto.getEscuderia();
            Escuderia escuderiaDelPiloto = null; 

            if (pil.getPilotoEscuderias() != null && !pil.getPilotoEscuderias().isEmpty()) {

                // Buscamos si el piloto tiene contrato con la escudería del auto
                for (PilotoEscuderia pe : pil.getPilotoEscuderias()) {
                    if (pe.getEscuderia().equals(escuderiaDelAuto)) {
                        escuderiaDelPiloto = pe.getEscuderia(); // Encontramos el contrato
                        break;
                    }
                }
            }

            // Si después de buscar, no encontramos contrato (sigue siendo null)...
            if (escuderiaDelPiloto == null) {
                String msg = String.format("Error de Validación:\nEl Piloto (%s) no tiene contrato vigente con la escudería del Auto (%s).",
                                            pil.getNombre(),
                                            escuderiaDelAuto.getNombre());
                throw new Exception(msg);
            }
            // --- (Fin de la validación de equipo) ---


            // --- 6. GUARDADO ---
            // Si llegamos aquí, todas las validaciones pasaron
            gestion.gestionarPilotoAuto(pil, auto, fecAs);
            JOptionPane.showMessageDialog(this, "El auto " + auto.getModelo() + " ha sido correctamente asociado al piloto: " + pil.getNombre());
            
            txtFecha.setText("");
            if (comboCampoAuto.getItemCount() > 0) comboCampoAuto.setSelectedIndex(0);
            if (comboCampoPiloto.getItemCount() > 0) comboCampoPiloto.setSelectedIndex(0);

        } catch (Exception e) {
            // El 'catch' general atrapa cualquier 'throw new Exception' que hayamos lanzado
            JOptionPane.showMessageDialog(this, "Error al asociar: " + e.getMessage(), "Error de Validación", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnAsociarActionPerformed
    

    /**
     * Manejador del botón 'Volver'.
     * Cierra este formulario (dispose) y vuelve a hacer visible la pantalla
     * principal ({@link Pantalla}).
     * @param evt El evento de acción.
     */
    private void bntVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntVolverActionPerformed
        this.pantallaAnterior.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_bntVolverActionPerformed

    
    private void txtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtFechaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtFechaActionPerformed

    
    /**
     * Carga la lista de {@link Piloto} desde la capa de gestión
     * y las añade al JComboBox 'comboCampoPiloto'.
     */
    private void cargarPilotos() {
    comboCampoPiloto.removeAllItems();
    ArrayList<Piloto> lista = gestion.getListaPilotos();
    if (lista != null) {
        for (Piloto p : lista) comboCampoPiloto.addItem(p);
    }
}
    
    /**
     * Carga la lista de {@link Auto} desde la capa de gestión
     * y las añade al JComboBox 'comboCampoAuto'.
     */
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
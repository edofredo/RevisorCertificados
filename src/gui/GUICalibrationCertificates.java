package gui;


import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Sensor;
import certificates.General.DataParser;
import certificates.General.DataParserSelection;
import java.io.File;
import javax.swing.JFileChooser;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author CRA
 */
public class GUICalibrationCertificates extends javax.swing.JFrame {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private DefaultTableModel defaultTableModel;

    private int laboratorySelection;
    private int sensorSelection;
    private String path;
    
    /**
     * Creates new form GUICalibrationCertificates
     */
    public GUICalibrationCertificates() {
        initComponents();
        defaultTableModel = (DefaultTableModel) jTableCalData.getModel();
        configureApplicationWindow();
        //DataParser sensor = new AC6THLufftWs300();
        //populateTable(sensor);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCalData = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jCBLaboratory = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jCBSensorType = new javax.swing.JComboBox<>();
        jLabelPath = new javax.swing.JLabel();
        jButtonPath = new javax.swing.JButton();
        jButtonGetData = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableCalData.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {},

            new String [] {
                "Measurand", "Laboratory", "Serial", "Slope", "Offset", "CalDate", "Uncert"
            }
        ));
        jTableCalData.setCellSelectionEnabled(true);
        jScrollPane1.setViewportView(jTableCalData);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 493, Short.MAX_VALUE)
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("  Calibration laboratory");
        jLabel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel1.setFocusable(false);
        jLabel1.setMinimumSize(new java.awt.Dimension(200, 18));
        jLabel1.setPreferredSize(new java.awt.Dimension(108, 18));

        jCBLaboratory.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select laboratory", "IDR", "DWG", "AC6" }));
        jCBLaboratory.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBLaboratoryActionPerformed(evt);
            }
        });

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("  Sensor type");
        jLabel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel2.setFocusable(false);
        jLabel2.setMinimumSize(new java.awt.Dimension(200, 18));
        jLabel2.setPreferredSize(new java.awt.Dimension(108, 18));

        jCBSensorType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select sensor", "" }));
        jCBSensorType.setEnabled(false);
        jCBSensorType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCBSensorTypeActionPerformed(evt);
            }
        });

        jLabelPath.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButtonPath.setText("Path");
        jButtonPath.setEnabled(false);
        jButtonPath.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPathActionPerformed(evt);
            }
        });

        jButtonGetData.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonGetData.setText("GET DATA");
        jButtonGetData.setEnabled(false);
        jButtonGetData.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGetDataActionPerformed(evt);
            }
        });

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("  Certificates path");
        jLabel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jLabel3.setFocusable(false);
        jLabel3.setMinimumSize(new java.awt.Dimension(200, 18));
        jLabel3.setPreferredSize(new java.awt.Dimension(108, 18));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(28, 28, 28)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jCBLaboratory, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jCBSensorType, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonGetData, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                        .addComponent(jLabelPath, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBLaboratory, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jCBSensorType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonGetData, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelPath, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCBLaboratoryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBLaboratoryActionPerformed
        laboratorySelection = jCBLaboratory.getSelectedIndex();
        if (laboratorySelection == 0) {
            jCBSensorType.removeAllItems();
            jCBSensorType.setEnabled(false);
            jButtonGetData.setEnabled(false);
            jCBSensorType.addItem("Select sensor");
            path="";
            jLabelPath.setText(path);
        } else if (laboratorySelection == 1) {
            jCBSensorType.removeAllItems();
            jCBSensorType.setEnabled(true);
            jCBSensorType.addItem("Select sensor");
            jCBSensorType.addItem("TFCA .000");
        } else if (laboratorySelection == 2) {
            jCBSensorType.removeAllItems();
            jCBSensorType.setEnabled(true);
            jCBSensorType.addItem("Select sensor");
            jCBSensorType.addItem("TFCA .000");
            jCBSensorType.addItem("TFCA .400");
        } else if (laboratorySelection == 3) {
            jCBSensorType.removeAllItems();
            jCBSensorType.setEnabled(true);
            jCBSensorType.addItem("Select sensor");
            jCBSensorType.addItem("Lufft WS 300 Barometer");
            jCBSensorType.addItem("Lufft WS 300 ThermoHygrometer");
        }

    }//GEN-LAST:event_jCBLaboratoryActionPerformed

    private void jCBSensorTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCBSensorTypeActionPerformed
        sensorSelection = jCBSensorType.getSelectedIndex();
        
        if(sensorSelection!=0)
        jButtonPath.setEnabled(true);
        else {
            path="";
            jLabelPath.setText(path);
            jButtonPath.setEnabled(false);
            jButtonGetData.setEnabled(false);
        }
    }//GEN-LAST:event_jCBSensorTypeActionPerformed

    private void jButtonPathActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPathActionPerformed
        fileChooser();
    }//GEN-LAST:event_jButtonPathActionPerformed

    private void jButtonGetDataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGetDataActionPerformed
        defaultTableModel.setNumRows(0);  
        DataParserSelection selectedDataParser = new DataParserSelection(laboratorySelection, sensorSelection, path);
        DataParser dataparser = selectedDataParser.selectParser();
        populateTable(dataparser);
        resetUserInput();
        ArrayList<String> certificateErrorPaths = new ArrayList<>();
        try {
            certificateErrorPaths = dataparser.getCertificateErrorPaths();
        } catch (IOException ex) {
            Logger.getLogger(GUICalibrationCertificates.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(!certificateErrorPaths.isEmpty()){
            String errorPaths = "";
            for(String eP : certificateErrorPaths){
                errorPaths = errorPaths + eP + "\n";
            }
            JOptionPane.showMessageDialog(null, "The following files could not be parsed:\n" + errorPaths);
        }
    }//GEN-LAST:event_jButtonGetDataActionPerformed

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
            java.util.logging.Logger.getLogger(GUICalibrationCertificates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GUICalibrationCertificates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GUICalibrationCertificates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GUICalibrationCertificates.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GUICalibrationCertificates().setVisible(true);
            }
        });
    }

    private void populateTable(DataParser certificateData) {
        ArrayList<Sensor> sensorList = null;
        try {
            sensorList = certificateData.parser();
        } catch (IOException ex) {
            Logger.getLogger(GUICalibrationCertificates.class.getName()).log(Level.SEVERE, null, ex);
        }

        Object[] sensorsForTable;

        //Order in table: "Measurand", "laboratory" "Serial", "Slope", "Offset", "CalDate", "Result"
        if (sensorList.isEmpty()) {
            JOptionPane.showMessageDialog(this, "The list is empty. Please check"
                    + " the user inputs.");
        } else {

            for (Sensor a : sensorList) {
                sensorsForTable = new Object[]{a.getMeasurand(), a.getLaboratory(), 
                    a.getSerialNumber(), a.getSlope(), a.getOffset(), 
                    a.getCalibrationDate(), a.getUncertainty()};
                defaultTableModel.addRow(sensorsForTable);
            }
        }
               
    }
    
    private void fileChooser() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int result = fileChooser.showOpenDialog(this);
        if (result != JFileChooser.CANCEL_OPTION) {
            File folderName = fileChooser.getSelectedFile();
            path = folderName.toString();
            jLabelPath.setText(path);
            jButtonGetData.setEnabled(true);
        }
    }
    
    private void resetUserInput(){
        jCBLaboratory.setSelectedIndex(0);
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonGetData;
    private javax.swing.JButton jButtonPath;
    private javax.swing.JComboBox<String> jCBLaboratory;
    private javax.swing.JComboBox<String> jCBSensorType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelPath;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableCalData;
    // End of variables declaration//GEN-END:variables

    private void configureApplicationWindow() {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Calibration Certificate Data Extractor");
        this.pack();
        this.setLocationRelativeTo(null);
    }
}

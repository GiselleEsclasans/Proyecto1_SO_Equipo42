/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Interfaces;

import DataStructure.Data;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author andresimery
 */
public class TxtData extends javax.swing.JFrame {

    private Inicio inicio;
    /**
     * Creates new form TxtData
     */
    public TxtData(Inicio inicio) {
        initComponents();
        this.inicio = inicio;
        this.loadFromFile();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    private void saveToFile() {
        String dayDuration = dayDurationTextField.getText(); // Duración de un día
        String daysLeft = daysLeftTextField.getText(); // Días entre entregas
//        String placaBase = PlacaBaseTextField.getText(); // Cantidad inicial de trabajadores tipo Placa Base
//        String cpu = CPUTextField.getText(); // Cantidad inicial de trabajadores tipo CPU
//        String ramMemory = RamMemoryTextField.getText(); // Cantidad inicial de trabajadores tipo RAM
//        String fuenteA = FuenteATextField.getText(); // Cantidad inicial de trabajadores tipo Fuente de Alimentación
//        String assemblers = AssemblersTextField.getText(); // Cantidad inicial de ensambladores

        try {
            File file = new File("./src/Public/settingsApple.txt");
            System.out.println("Ruta absoluta del archivo: " + file.getAbsolutePath());
            
            FileWriter writer = new FileWriter(file);
            writer.write("Duracion del dia (segundos): " + dayDuration + "\n");
            writer.write("Dias para entrega: " + daysLeft + "\n");
//            writer.write("Trabajadores tipo Placa Base: " + placaBase + "\n");
//            writer.write("Trabajadores tipo CPU: " + cpu + "\n");
//            writer.write("Trabajadores tipo RAM: " + ramMemory + "\n");
//            writer.write("Trabajadores tipo Fuente de Alimentacion: " + fuenteA + "\n");
//            writer.write("Ensambladores: " + assemblers + "\n");
            writer.close();
            System.out.println("Parámetros guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar los parámetros.");
            e.printStackTrace();
        }
    }
    
    public void loadFromFile() {
        try {
            File file = new File("./src/Public/settingsApple.txt");
            
//            if (!file.exists()) {
////                file.getParentFile().mkdirs(); // Crea los directorios necesarios si no existen
//                file.createNewFile(); // Crea el archivo si no existe
//                return;
//            }
            
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();

                // Separar el nombre del parámetro y el valor
                String[] parts = line.split(": ");
                String parameterName = parts[0];
                String parameterValue = parts[1];
                System.out.println(parameterName + parameterValue);

                // Asignar los valores a los JTextFields y variables correspondientes
                switch (parameterName) {
                    case "Duracion del dia (segundos)":
                        // Asignar al JTextField correspondiente y a la variable global
                        dayDurationTextField.setText(parameterValue);
                    
                        Data.dayDuration = Integer.parseInt(parameterValue) * 1000; // Convertir a milisegundos
                        break;
                    case "Dias para entrega":
                        daysLeftTextField.setText(parameterValue);
                        Data.totalDays = Integer.parseInt(parameterValue);
                        break;
//                    case "Trabajadores tipo Placa Base":
//                        PlacaBaseTextField.setText(parameterValue);
//                        Data.workerCounts[0] = Integer.parseInt(parameterValue);
//                        break;
//                    case "Trabajadores tipo CPU":
//                        CPUTextField.setText(parameterValue);
//                        Data.workerCounts[1] = Integer.parseInt(parameterValue);
//                        break;
//                    case "Trabajadores tipo RAM":
//                        RamMemoryTextField.setText(parameterValue);
//                        Data.workerCounts[2] = Integer.parseInt(parameterValue);
//                        break;
//                    case "Trabajadores tipo Fuente de Alimentacion":
//                        FuenteATextField.setText(parameterValue);
//                        Data.workerCounts[3] = Integer.parseInt(parameterValue);
//                        break;
//                    case "Ensambladores":
//                        AssemblersTextField.setText(parameterValue);
//                        Data.workerCounts[4] = Integer.parseInt(parameterValue);
//                        break;
                    default:
                        System.out.println("Parámetro desconocido: " + parameterName);
                        break;
                }
            }
            scanner.close();
            System.out.println("Parámetros cargados correctamente.");
        } catch (FileNotFoundException e) {
            System.out.println("Archivo no encontrado.");
            
            e.printStackTrace();
        } 
//        catch (IOException ex) {
//            Logger.getLogger(TxtDataApple.class.getName()).log(Level.SEVERE, null, ex);
//        }
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
        MenuButton = new javax.swing.JButton();
        TitleLabel = new javax.swing.JLabel();
        dayDurationLabel = new javax.swing.JLabel();
        dayDurationTextField = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dayDurationLabel1 = new javax.swing.JLabel();
        daysLeftTextField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        MenuButton.setText("Guardar y Volver");
        MenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuButtonActionPerformed(evt);
            }
        });
        jPanel1.add(MenuButton, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 140, -1, -1));

        TitleLabel.setFont(new java.awt.Font("Helvetica Neue", 1, 18)); // NOI18N
        TitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        TitleLabel.setText("Data para el TXT Apple");
        jPanel1.add(TitleLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 230, -1));

        dayDurationLabel.setText("Duración en segundos de un día:");
        jPanel1.add(dayDurationLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, -1, -1));

        dayDurationTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        dayDurationTextField.setText("5");
        dayDurationTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayDurationTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(dayDurationTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 60, -1));

        jLabel1.setText("segundos");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 50, -1, 20));

        jLabel2.setText("días");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 90, -1, 20));

        dayDurationLabel1.setText("Cantidad de días entre entregas:");
        jPanel1.add(dayDurationLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, -1, -1));

        daysLeftTextField.setHorizontalAlignment(javax.swing.JTextField.TRAILING);
        daysLeftTextField.setText("30");
        daysLeftTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                daysLeftTextFieldActionPerformed(evt);
            }
        });
        jPanel1.add(daysLeftTextField, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, 60, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 400, 180));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuButtonActionPerformed
        this.saveToFile();
        this.inicio.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_MenuButtonActionPerformed

    private void dayDurationTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dayDurationTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dayDurationTextFieldActionPerformed

    private void daysLeftTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_daysLeftTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_daysLeftTextFieldActionPerformed

    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(TxtData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(TxtData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(TxtData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(TxtData.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new TxtData().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton MenuButton;
    private javax.swing.JLabel TitleLabel;
    private javax.swing.JLabel dayDurationLabel;
    private javax.swing.JLabel dayDurationLabel1;
    private javax.swing.JTextField dayDurationTextField;
    private javax.swing.JTextField daysLeftTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}

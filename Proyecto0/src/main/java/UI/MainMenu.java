/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package UI;

/**
 *
 * @author Gabriel
 */
public class MainMenu extends javax.swing.JPanel {

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
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

        ClienteButton = new javax.swing.JButton();
        CitaButton = new javax.swing.JButton();
        ListaEsperaButton = new javax.swing.JButton();
        SevicioButton = new javax.swing.JButton();
        AdminButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(51, 51, 51));
        setPreferredSize(new java.awt.Dimension(720, 405));

        ClienteButton.setBackground(new java.awt.Color(19, 23, 25));
        ClienteButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ClienteButton.setForeground(new java.awt.Color(177, 177, 177));
        ClienteButton.setText("Menú Clientes");
        ClienteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ClienteButtonActionPerformed(evt);
            }
        });

        CitaButton.setBackground(new java.awt.Color(19, 23, 25));
        CitaButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        CitaButton.setForeground(new java.awt.Color(177, 177, 177));
        CitaButton.setText("Menú Citas");
        CitaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CitaButtonActionPerformed(evt);
            }
        });

        ListaEsperaButton.setBackground(new java.awt.Color(19, 23, 25));
        ListaEsperaButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        ListaEsperaButton.setForeground(new java.awt.Color(177, 177, 177));
        ListaEsperaButton.setText("Lista de Espera");
        ListaEsperaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaEsperaButtonActionPerformed(evt);
            }
        });

        SevicioButton.setBackground(new java.awt.Color(19, 23, 25));
        SevicioButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        SevicioButton.setForeground(new java.awt.Color(177, 177, 177));
        SevicioButton.setText("Menú Servicios");
        SevicioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SevicioButtonActionPerformed(evt);
            }
        });

        AdminButton.setBackground(new java.awt.Color(19, 23, 25));
        AdminButton.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        AdminButton.setForeground(new java.awt.Color(177, 177, 177));
        AdminButton.setText("Administración");
        AdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(29, 29, 29));
        jPanel3.setPreferredSize(new java.awt.Dimension(322, 85));

        jLabel3.setFont(new java.awt.Font("Century Gothic", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(222, 222, 222));
        jLabel3.setText("Menú Principal");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(407, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(125, 125, 125)
                                .addComponent(ListaEsperaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(AdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addComponent(ClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(CitaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(SevicioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 714, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CitaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SevicioButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ClienteButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AdminButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListaEsperaButton, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 91, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ClienteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ClienteButtonActionPerformed
        MainFrame.getInstance().showPage("ClientMenu");
    }//GEN-LAST:event_ClienteButtonActionPerformed

    private void CitaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CitaButtonActionPerformed
        MainFrame.getInstance().showPage("CitaMenu");
    }//GEN-LAST:event_CitaButtonActionPerformed

    private void SevicioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SevicioButtonActionPerformed
        MainFrame.getInstance().showPage("ServicioMenu");
    }//GEN-LAST:event_SevicioButtonActionPerformed

    private void ListaEsperaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaEsperaButtonActionPerformed
        MainFrame.getInstance().showPage("ListaEsperaMenu");
    }//GEN-LAST:event_ListaEsperaButtonActionPerformed

    private void AdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminButtonActionPerformed
        MainFrame.getInstance().showPage("AdminMenu");
    }//GEN-LAST:event_AdminButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminButton;
    private javax.swing.JButton CitaButton;
    private javax.swing.JButton ClienteButton;
    private javax.swing.JButton ListaEsperaButton;
    private javax.swing.JButton SevicioButton;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel3;
    // End of variables declaration//GEN-END:variables
}

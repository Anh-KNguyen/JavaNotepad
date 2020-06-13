/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

/**
 *
 * @author Kimberly Nguyen
 */
public class SaveDialog extends javax.swing.JPanel {

    /**
     * Creates new form SaveDialog
     */
    public SaveDialog() {
        initComponents();
        
        jDialog1 = new JDialog(frame, "Notepad");
                    
                    jDialog1.setTitle("Notepad");
                    jDialog1.setModal(true);
                    jDialog1.setResizable(false);
                    jDialog1.setSize(350, 180);
                    jDialog1.setLayout(new FlowLayout());
                    jDialog1.setLocationRelativeTo(null);
                    
                    dSaveLab = new JButton();
                    dDontSaveLab = new JButton();
                    dCancelLab = new JButton();
                    saveChangeLab = new JLabel();

                    dSaveLab.setText("Save");
                    dSaveLab.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent le) {
                            saveAs();
                            jDialog1.dispose();
                        }
                    });
                    
                    
                    dDontSaveLab.setText("Don't Save");                
                    dDontSaveLab.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent le) {
                            jtextArea.setText("");
                            file = null;
                            jDialog1.dispose();
                        }
                        
                    });
                        
                                        
                    dCancelLab.setText("Cancel");
                    dCancelLab.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent le) {
                            jDialog1.dispose();
                        }
                    });
                    

                    saveChangeLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
                    saveChangeLab.setForeground(new java.awt.Color(65, 105, 225));
                    saveChangeLab.setText("Do you want to save changes to Untitled?");

                    javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
                    jDialog1.getContentPane().setLayout(jDialog1Layout);
                    jDialog1Layout.setHorizontalGroup(
                            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                                                            .addGap(0, 0, Short.MAX_VALUE)
                                                            .addComponent(dSaveLab)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(dDontSaveLab)
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                            .addComponent(dCancelLab))
                                                    .addComponent(saveChangeLab, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                                            .addContainerGap())
                    );
                    jDialog1Layout.setVerticalGroup(
                            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jDialog1Layout.createSequentialGroup()
                                            .addGap(20, 20, 20)
                                            .addComponent(saveChangeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                    .addComponent(dSaveLab)
                                                    .addComponent(dDontSaveLab)
                                                    .addComponent(dCancelLab))
                                            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    );

                

                


             jDialog1.setLocationRelativeTo(null);
             jDialog1.setVisible(true);
        
        
        
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        dSaveLab = new javax.swing.JButton();
        dDontSaveLab = new javax.swing.JButton();
        dCancelLab = new javax.swing.JButton();
        saveChangeLab = new javax.swing.JLabel();

        jDialog1.setTitle("Notepad");
        jDialog1.setModal(true);
        jDialog1.setResizable(false);

        dSaveLab.setText("Save");

        dDontSaveLab.setText("Don't Save");
        dDontSaveLab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dDontSaveLabActionPerformed(evt);
            }
        });

        dCancelLab.setText("Cancel");

        saveChangeLab.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        saveChangeLab.setForeground(new java.awt.Color(65, 105, 225));
        saveChangeLab.setText("Do you want to save changes to Untitled?");

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDialog1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(dSaveLab)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dDontSaveLab, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dCancelLab))
                    .addComponent(saveChangeLab, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE))
                .addContainerGap())
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDialog1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(saveChangeLab, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dSaveLab)
                    .addComponent(dDontSaveLab)
                    .addComponent(dCancelLab))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void dDontSaveLabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dDontSaveLabActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dDontSaveLabActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton dCancelLab;
    public javax.swing.JButton dDontSaveLab;
    public javax.swing.JButton dSaveLab;
    public javax.swing.JDialog jDialog1;
    public javax.swing.JLabel saveChangeLab;
    // End of variables declaration//GEN-END:variables
}

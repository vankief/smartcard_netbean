/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import com.sun.xml.internal.ws.api.pipe.Tube;
import javacard.connect.ConnectCard;
import javax.swing.JOptionPane;

/**
 *
 * @author PC Nga
 */
public class doiMaPin extends javax.swing.JFrame {

    /**
     * Creates new form doiMaPin
     */
    public doiMaPin() {
        initComponents();
        this.setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txt_mapinxacthuc = new javax.swing.JPasswordField();
        txt_mapincu = new javax.swing.JPasswordField();
        txt_mapinmoi = new javax.swing.JPasswordField();
        btn_xacNhan = new javax.swing.JButton();
        btn_huy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_mapinxacthuc.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txt_mapinxacthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mapinxacthucActionPerformed(evt);
            }
        });
        getContentPane().add(txt_mapinxacthuc, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 400, 410, 60));

        txt_mapincu.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txt_mapincu.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        txt_mapincu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mapincuActionPerformed(evt);
            }
        });
        getContentPane().add(txt_mapincu, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 200, 410, 60));

        txt_mapinmoi.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        txt_mapinmoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_mapinmoiActionPerformed(evt);
            }
        });
        getContentPane().add(txt_mapinmoi, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 300, 410, 60));

        btn_xacNhan.setBorderPainted(false);
        btn_xacNhan.setContentAreaFilled(false);
        btn_xacNhan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xacNhanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_xacNhan, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 500, 170, 50));

        btn_huy.setBorderPainted(false);
        btn_huy.setContentAreaFilled(false);
        btn_huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_huy, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 490, 160, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/doiMaPin.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_xacNhanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xacNhanActionPerformed
        String txt_ma_cu = txt_mapincu.getText();
        String txt_ma_moi = txt_mapinmoi.getText();
        String txt_ma_xac_nhan = txt_mapinxacthuc.getText();
        if (txt_ma_cu.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui Lòng Nhập Mã Pin");
            return;
        }
        if(txt_ma_moi.equals("nhom12"))
        {
            JOptionPane.showMessageDialog(null, "Mã Pin không được trùng mã pin mặc định");
            return;
        }
        if (txt_ma_moi.equals(txt_ma_xac_nhan)) {
            if (ConnectCard.getInstance().createPIN(txt_ma_moi)) {
                nguoiDung nguoiDung = new nguoiDung();
                nguoiDung.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu pin");
                return;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng kiểm tra lại mã PIN");
            return;
        }

    }//GEN-LAST:event_btn_xacNhanActionPerformed

    private void btn_huyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huyActionPerformed
    
            this.dispose();
        
    }//GEN-LAST:event_btn_huyActionPerformed

    private void txt_mapinxacthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mapinxacthucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mapinxacthucActionPerformed

    private void txt_mapincuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mapincuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mapincuActionPerformed

    private void txt_mapinmoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_mapinmoiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_mapinmoiActionPerformed

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
            java.util.logging.Logger.getLogger(doiMaPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(doiMaPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(doiMaPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(doiMaPin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new doiMaPin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_huy;
    private javax.swing.JButton btn_xacNhan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPasswordField txt_mapincu;
    private javax.swing.JPasswordField txt_mapinmoi;
    private javax.swing.JPasswordField txt_mapinxacthuc;
    // End of variables declaration//GEN-END:variables
}

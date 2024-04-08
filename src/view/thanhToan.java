/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connectDB.DataUser;
import connectDB.DataUserDAO;
import connectDB.HoaDon;
import java.nio.ByteBuffer;
import java.security.PublicKey;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javacard.connect.ConnectCard;
import javacard.connect.RSAAppletHelper;
import javacard.utils.RSAData;
import javacard.utils.RandomUtil;
import javax.smartcardio.CardException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author DELL
 */
public class thanhToan extends javax.swing.JFrame {

    DefaultTableModel dsthanhtoan = new DefaultTableModel();
    List<HoaDon> list = new ArrayList<>();
    private DataUserDAO dataUserDAO = new DataUserDAO();
    private DataUser dataUser;

    /**
     * Creates new form thanhToan
     */
    public thanhToan() throws SQLException {
        initComponents();
        dsthanhtoan = (DefaultTableModel) tbl_thanhtoan.getModel();
        ShowDl();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_thanhtoan = new javax.swing.JTable();
        btn_thanhToan = new javax.swing.JButton();
        btn_Huy = new javax.swing.JButton();
        txt_tongtien = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tbl_thanhtoan.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_thanhtoan.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Dịch vụ", "Thuốc"
            }
        ));
        jScrollPane1.setViewportView(tbl_thanhtoan);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 480, 270));

        btn_thanhToan.setBorderPainted(false);
        btn_thanhToan.setContentAreaFilled(false);
        btn_thanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_thanhToanActionPerformed(evt);
            }
        });
        getContentPane().add(btn_thanhToan, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 440, 150, 40));

        btn_Huy.setBorderPainted(false);
        btn_Huy.setContentAreaFilled(false);
        btn_Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_HuyActionPerformed(evt);
            }
        });
        getContentPane().add(btn_Huy, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 430, 150, 50));

        txt_tongtien.setFont(new java.awt.Font("Times New Roman", 0, 24)); // NOI18N
        getContentPane().add(txt_tongtien, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 390, 210, 30));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/thanhToan.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_thanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_thanhToanActionPerformed
        int Tongtien = Integer.parseInt(txt_tongtien.getText());
        ConnectCard.getInstance().ReadInformation();
        int Sodu = ConnectCard.getInstance().SoDu;
        if (Sodu < Tongtien) {
            JOptionPane.showMessageDialog(null, "Số Dư Tài Khoản không đủ để thanh toán, vui lòng nạp thêm tiền");
        } else if (Tongtien == 0) {
            JOptionPane.showMessageDialog(null, "Không có gì để thanh toán");
        } else {
            int Sodumoi = Sodu - Tongtien;
            ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
            buffer.putInt(Sodumoi);
            byte[] byteSoDuMoi = buffer.array();
           
            byte[] data = new byte[byteSoDuMoi.length + 1];
            int offSet = 0;
            System.arraycopy(byteSoDuMoi, 0, data, offSet, byteSoDuMoi.length);
            offSet += byteSoDuMoi.length;

            data[offSet] = (byte) 0x7E;
            if (rsaAuthentication()) {
                dataUser = new DataUser();
                dataUser.setBalance(Sodumoi);
                dataUser.setUserId(ConnectCard.getInstance().strID);
                boolean check = dataUserDAO.updateBalance(dataUser);
                if (check && ConnectCard.getInstance().TopUp(data)) {
                    JOptionPane.showMessageDialog(null, "Thanh Toán thành công");
                    dataUserDAO.updateIsPayment(ConnectCard.getInstance().strID.trim());
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi Thanh Toán");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Lỗi Xác Thực");
            }
        }

    }//GEN-LAST:event_btn_thanhToanActionPerformed

    private void btn_HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_HuyActionPerformed

        this.dispose();
    }//GEN-LAST:event_btn_HuyActionPerformed

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
            java.util.logging.Logger.getLogger(thanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(thanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(thanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(thanhToan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new thanhToan().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(thanhToan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void ShowDl() throws SQLException {
        String maBn = ConnectCard.getInstance().strID;
        list = connectDB.DataUserDAO.getHoaDonList(maBn);
        dsthanhtoan.setRowCount(0);
        int Tongtien = 0;
        for (HoaDon hoaDon : list) {
            dsthanhtoan.addRow(new Object[]{hoaDon.getDichVu(), hoaDon.getThuoc()});
            Tongtien += hoaDon.getTongTien();
        }
        txt_tongtien.setText(String.valueOf(Tongtien));
        txt_tongtien.setEnabled(false);
    }

    private boolean rsaAuthentication() {
        try {
            PublicKey publicKeys = RSAData.getPublicKey();
            if (publicKeys == null) {
                return false;
            }
            System.out.println("publicKeys: " + Arrays.toString(publicKeys.getEncoded()));
            byte[] data = RandomUtil.randomData(20);

            byte[] signed = RSAAppletHelper.getInstance(
                    ConnectCard.getInstance().channel).requestSign(data);

            if (signed == null) {
                return false;
            }

            System.out.println("signed: " + Arrays.toString(signed));

            return RSAData.verify(publicKeys, signed, data);
        } catch (CardException ex) {
        }
        return false;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_Huy;
    private javax.swing.JButton btn_thanhToan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_thanhtoan;
    private javax.swing.JTextField txt_tongtien;
    // End of variables declaration//GEN-END:variables
}
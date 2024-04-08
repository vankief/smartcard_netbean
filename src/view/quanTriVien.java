/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import connectDB.DataUser;
import connectDB.DataUserDAO;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.PublicKey;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javacard.connect.ConnectCard;
import javacard.connect.RSAAppletHelper;
import javacard.define.APPLET;
import javax.print.DocFlavor;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PC Nga
 */
public class quanTriVien extends javax.swing.JFrame {

    DefaultTableModel dsCreatCard = new DefaultTableModel();
    List<DataUser> list = new ArrayList<>();
    private DataUserDAO dataUserDAO = new DataUserDAO();

    public quanTriVien() throws SQLException {

        initComponents();
        dsCreatCard = (DefaultTableModel) tbl_danhSach.getModel();
        ShowDl();
        ShowDuLieu();
        this.setLocationRelativeTo(null);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_taoThe = new javax.swing.JButton();
        btn_datLaiMaPin = new javax.swing.JButton();
        btn_khoaThe = new javax.swing.JButton();
        btn_moThe = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_danhSach = new javax.swing.JTable();
        btn_back = new javax.swing.JButton();
        txt_ID = new javax.swing.JTextField();
        txt_hoTen = new javax.swing.JTextField();
        txt_ngaySinh = new javax.swing.JTextField();
        txt_diaChi = new javax.swing.JTextField();
        txt_dienThoai = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_taoThe.setBorderPainted(false);
        btn_taoThe.setContentAreaFilled(false);
        btn_taoThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_taoTheActionPerformed(evt);
            }
        });
        getContentPane().add(btn_taoThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 580, 220, 70));

        btn_datLaiMaPin.setBorderPainted(false);
        btn_datLaiMaPin.setContentAreaFilled(false);
        btn_datLaiMaPin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datLaiMaPinActionPerformed(evt);
            }
        });
        getContentPane().add(btn_datLaiMaPin, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 250, 50));

        btn_khoaThe.setBorderPainted(false);
        btn_khoaThe.setContentAreaFilled(false);
        btn_khoaThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoaTheActionPerformed(evt);
            }
        });
        getContentPane().add(btn_khoaThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 120, 50));

        btn_moThe.setBorderPainted(false);
        btn_moThe.setContentAreaFilled(false);
        btn_moThe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moTheActionPerformed(evt);
            }
        });
        getContentPane().add(btn_moThe, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 600, 120, 50));

        tbl_danhSach.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tbl_danhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID", "Ngày Đăng Ký"
            }
        ));
        jScrollPane1.setViewportView(tbl_danhSach);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 260, 430));

        btn_back.setText("BACK");
        btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_backActionPerformed(evt);
            }
        });
        getContentPane().add(btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 595, 110, 40));

        txt_ID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_ID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IDActionPerformed(evt);
            }
        });
        getContentPane().add(txt_ID, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 180, 410, 80));

        txt_hoTen.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        txt_hoTen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_hoTenActionPerformed(evt);
            }
        });
        getContentPane().add(txt_hoTen, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 410, 70));

        txt_ngaySinh.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(txt_ngaySinh, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 410, 70));

        txt_diaChi.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(txt_diaChi, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 430, 410, 70));

        txt_dienThoai.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        getContentPane().add(txt_dienThoai, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 410, 70));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/quanTriVien.jpg"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1190, 680));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IDActionPerformed

    }//GEN-LAST:event_txt_IDActionPerformed

    private void txt_hoTenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_hoTenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_hoTenActionPerformed

    private void btn_datLaiMaPinActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_datLaiMaPinActionPerformed
        Reset();
        JDialog dialog = new JDialog(this, "Đặt lại mã pin");
        JTextField txt_ma_bn = new JTextField(20);
        JLabel lb_ma_bn = new JLabel("Mã Bệnh Nhân: ");
        dialog.setLayout(new FlowLayout());
        dialog.add(lb_ma_bn);
        txt_ma_bn.setBounds(20, 20, 30, 40);
        JButton confirmButton = new JButton("Xác nhận");
        dialog.add(txt_ma_bn);
        dialog.add(confirmButton);
        dialog.setVisible(true);
        dialog.setSize(300, 150);
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String maBn = txt_ma_bn.getText().toString();
                if (ConnectCard.getInstance().ReadInformation()) {
                    String id = ConnectCard.getInstance().strID.trim();
                    if (!id.equals(maBn)) {
                        JOptionPane.showMessageDialog(rootPane, "Mã Bệnh Nhân không chính xác");
                        return;
                    }
                    ConnectCard.getInstance().ResetPin();
                }
            }
        });
    }//GEN-LAST:event_btn_datLaiMaPinActionPerformed

    private void btn_khoaTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoaTheActionPerformed
        Reset();
        JDialog d = new JDialog(this, "Khóa Thẻ");
        JTextField txt_ma_bn = new JTextField(20);
        JLabel lb_ma_bn = new JLabel("Mã Bệnh Nhân: ");
        d.setLayout(new FlowLayout());
        d.add(lb_ma_bn);
        txt_ma_bn.setBounds(10, 20, 30, 40);
        JButton confirmButton = new JButton("Xác nhận");
        d.add(txt_ma_bn);
        d.add(confirmButton);
        d.setVisible(true);
        d.setSize(300, 150);
        d.setLocationRelativeTo(null);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_ma_bn.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Mã Bệnh Nhân không được bỏ trống");
                    return;
                }
                String maBn = txt_ma_bn.getText().toString();
                if (dataUserDAO.Blocked(maBn)) {
                    JOptionPane.showMessageDialog(rootPane, "Đã Khóa Thẻ Thành Công");
                    d.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Mã Bệnh nhân không chính xác");
                };
            }
        });
    }//GEN-LAST:event_btn_khoaTheActionPerformed

    private void btn_moTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moTheActionPerformed
        Reset();
        JDialog d = new JDialog(this, "Mở Thẻ");
        JTextField txt_ma_bn = new JTextField(20);
        JLabel lb_ma_bn = new JLabel("Mã Bệnh Nhân: ");
        d.setLayout(new FlowLayout());
        d.add(lb_ma_bn);
        txt_ma_bn.setBounds(10, 20, 30, 40);
        JButton confirmButton = new JButton("Xác nhận");
        d.add(txt_ma_bn);
        d.add(confirmButton);
        d.setVisible(true);
        d.setSize(300, 150);
        d.setLocationRelativeTo(null);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txt_ma_bn.getText().trim().isEmpty()) {
                    JOptionPane.showMessageDialog(rootPane, "Mã Bệnh Nhân không được bỏ trống");
                    return;
                }
                String maBn = txt_ma_bn.getText().trim();
                boolean check = dataUserDAO.UnBlock(maBn);
                if (check) {
                    JOptionPane.showMessageDialog(rootPane, "Mở khóa thẻ thành công");
                    d.dispose();
                } else {
                    JOptionPane.showMessageDialog(rootPane, "Mã Bệnh Nhân không chính xác");
                }
            }

        });

    }//GEN-LAST:event_btn_moTheActionPerformed

    private void btn_taoTheActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_taoTheActionPerformed
        String strId = txt_ID.getText();
        String strName = txt_hoTen.getText();
        String strDate = txt_ngaySinh.getText();
        String strAddress = txt_diaChi.getText();
        String strPhone = txt_dienThoai.getText();
        if (strId.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn thông tin để tạo thẻ");
            return;
        }
        byte[] byteID = strId.getBytes();
        byte[] byteName = strName.getBytes();
        byte[] byteDate = strDate.getBytes();
        byte[] byteDiaChi = strAddress.getBytes();
        byte[] bytePhone = strPhone.getBytes();
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(0);
        byte[] byteBalance = buffer.array();
        byte[] data = new byte[byteID.length + byteName.length + byteDate.length + bytePhone.length + byteDiaChi.length + byteBalance.length + 6];
        //byteID
        int offSet = 0;
        System.arraycopy(byteID, 0, data, offSet, byteID.length);
        offSet += byteID.length;
        data[offSet] = (byte) 0x7E;
        
        //byteName
        offSet += 1;
        System.arraycopy(byteName, 0, data, offSet, byteName.length);
        offSet += byteName.length;
        //data[offSet] = (byte) 0x03;
        data[offSet] = (byte) 0x7E;
        
        //byteDate
        offSet += 1;
        System.arraycopy(byteDate, 0, data, offSet, byteDate.length);
        offSet += byteDate.length;
        data[offSet] = (byte) 0x7E;
        
        //bytePhone
        offSet += 1;
        System.arraycopy(bytePhone, 0, data, offSet, bytePhone.length);
        offSet += bytePhone.length;
        data[offSet] = (byte) 0x7E;

        //byteDiaChi
        offSet += 1;
        System.arraycopy(byteDiaChi, 0, data, offSet, byteDiaChi.length);
        offSet += byteDiaChi.length;
        data[offSet] = (byte) 0x7E;
        
        //byteBalance
        offSet += 1;
        System.arraycopy(byteBalance, 0, data, offSet, byteBalance.length);
        offSet += byteBalance.length;
        data[offSet] = (byte) 0x7E;

       
        System.out.println("length" + data.length);
        if (ConnectCard.getInstance().CreateInformation(data)) {
            try {
                // luu pubplickey vao file publickey.txt
                PublicKey publicKey = RSAAppletHelper.getInstance(ConnectCard.getInstance().channel).getPublicKey();
                DataUser dataUser1 = new DataUser();
                dataUser1.setUserId(strId);
                dataUser1.setPublicKey(Arrays.toString(publicKey.getEncoded()));
                dataUserDAO.SetpublicKey(dataUser1);
            } catch (Exception e) {
                Logger.getLogger(quanTriVien.class.getName()).log(Level.SEVERE, null, e);
            }
            JOptionPane.showMessageDialog(rootPane, "Tạo Thẻ Thành Công");
            if (dataUserDAO.setIsCreated(strId)) {
                try {
                    ShowDl();
                } catch (SQLException ex) {
                    Logger.getLogger(quanTriVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Reset();
        } else {
            JOptionPane.showMessageDialog(rootPane, "Lỗi Tạo Thẻ");
        }
    }//GEN-LAST:event_btn_taoTheActionPerformed

    private void btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_backActionPerformed
        dangNhap dangNhap = new dangNhap();
        dangNhap.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(quanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(quanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(quanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(quanTriVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new quanTriVien().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(quanTriVien.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void ShowDl() throws SQLException {
        list = connectDB.DataUserDAO.hienthi();
        dsCreatCard.setRowCount(0);
        for (DataUser dataUser : list) {
            dsCreatCard.addRow(new Object[]{dataUser.getUserId(), dataUser.getRegistrationDate()});
        }
    }

    public void ShowDuLieu() {
        list = connectDB.DataUserDAO.hienthi();
        int selectRow = tbl_danhSach.getSelectedRow();
        try {
            tbl_danhSach.getSelectionModel().addListSelectionListener((new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    int selectRow = tbl_danhSach.getSelectedRow();
                    if (selectRow >= 0) {
                        String MaBN = tbl_danhSach.getValueAt(selectRow, 0).toString();
                        for (DataUser dataUser : list) {
                            if (MaBN.equals(dataUser.getUserId())) { // Sử dụng phương thức equals() để so sánh chuỗi
                                txt_ID.setText(dataUser.getCicNumber());
                                txt_hoTen.setText(dataUser.getName());
                                txt_ngaySinh.setText(dataUser.getDob());
                                txt_diaChi.setText(dataUser.getAddress());
                                txt_dienThoai.setText(dataUser.getPhone());
                                break;
                            }
                        }
                    }
                }
            }));
        } catch (Exception e) {
        }
    }

    public void Reset() {
        txt_ID.setText("");
        txt_diaChi.setText("");
        txt_dienThoai.setText("");
        txt_hoTen.setText("");
        txt_ngaySinh.setText("");
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_back;
    private javax.swing.JButton btn_datLaiMaPin;
    private javax.swing.JButton btn_khoaThe;
    private javax.swing.JButton btn_moThe;
    private javax.swing.JButton btn_taoThe;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbl_danhSach;
    private javax.swing.JTextField txt_ID;
    private javax.swing.JTextField txt_diaChi;
    private javax.swing.JTextField txt_dienThoai;
    private javax.swing.JTextField txt_hoTen;
    private javax.swing.JTextField txt_ngaySinh;
    // End of variables declaration//GEN-END:variables
}

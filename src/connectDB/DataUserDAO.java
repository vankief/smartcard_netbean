/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

import com.mysql.cj.jdbc.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 *
 * @author admin
 */
public class DataUserDAO {

    public static Connection getConnection() {
        // String url = "jdbc:mysql://localhost:3308/smart_card";
        String url = "jdbc:mysql://localhost:3307/test1";
        try {
            Connection con = (Connection) DriverManager.getConnection(url, "root", "root");

            System.out.println("Database connected");

            return con;
        } catch (SQLException ex) {

            System.out.println("Can't connect to database");
            ex.printStackTrace();
        }
        return null;
    }

    public static void closeConnection(Connection con) {
        try {
            con.close();
            System.out.println("Database closed");
        } catch (SQLException ex) {
            System.out.println("Can't close connection");
        }
    }

    public static List<DataUser> hienthi() {
        List<DataUser> list = new ArrayList<>();
        Connection con = getConnection();
        String sql = "SELECT smart_card_entity.patientId, smart_card_entity.name, smart_card_entity.cicNumber, smart_card_entity.dob, smart_card_entity.address, smart_card_entity.gender, smart_card_entity.phone, smart_card_entity.publicKey, smart_card_entity.balance, smart_card_entity.createdAt\n"
                + "FROM smart_card_entity\n"
                + "JOIN patient_entity ON patient_entity.id = smart_card_entity.patientId\n"
                + "WHERE patient_entity.smartCardStatus = 'PENDING';";

         try {
            Statement selectStatement = con.createStatement();
            ResultSet rs = selectStatement.executeQuery(sql);
            while (rs.next()) {
                String userId = rs.getString("patientId");
                String cicNumber = rs.getString("cicNumber");
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                Boolean gender1 = rs.getBoolean("gender");
                String gender = "";
                if(gender1){
                    gender = "Nam";
                }else{
                    gender = "Nữ";
                }
                Blob image = null;
                String phone = rs.getString("phone");
                String publicKey = rs.getString("publicKey");
                int balance = rs.getInt("balance");
                Date registrationDate = rs.getDate("createdAt");
                DataUser dataUser = new DataUser(userId, cicNumber, name, dob, address, gender, image, phone, publicKey, balance, registrationDate);
                list.add(dataUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return list;
    }

    public boolean Blocked(String cardNumber) {
        Connection con = getConnection();
        String sql = "UPDATE smart_card_entity SET isBlocked = 1 WHERE cicNumber = ?;";
        
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, cardNumber);
            updateStatement.executeUpdate();
            updateStatement.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public boolean CheckCardBlocked(String cardNumber) {
        Connection con = getConnection();
        String sql = "SELECT isBlocked FROM smart_card_entity WHERE cicNumber = ?;";
        try {
            PreparedStatement checkStatement = con.prepareStatement(sql);
            checkStatement.setString(1, cardNumber);
            ResultSet rs = checkStatement.executeQuery();
            boolean check = false;
            if (rs.next()) {
                check = rs.getBoolean("isBlocked");
                System.out.println("check block from DAO: " + check);
            }
            return check;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return false;
    }

     public boolean UnBlock(String cardNumber) {
        Connection con = getConnection();
        String sql = "UPDATE  smart_card_entity SET isBlocked = 0 Where cicNumber = ?;";
        try {
            PreparedStatement updatePreparedStatement = con.prepareStatement(sql);
            updatePreparedStatement.setString(1, cardNumber);
            updatePreparedStatement.executeUpdate(sql);
            updatePreparedStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public boolean UpdateUser(DataUser user) {
        Connection con = getConnection();
        String sql = "UPDATE  smart_card_entity SET name = ?, dob = ?, address = ?, phone= ? Where cicNumber = ?;";
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getDob());
            updateStatement.setString(3, user.getAddress());
            updateStatement.setString(4, user.getPhone());
            updateStatement.setString(5, user.getCicNumber());
            updateStatement.executeUpdate();
            updateStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public boolean SetpublicKey(DataUser user) {
        Connection con = getConnection();
        String sql = "UPDATE smart_card_entity set publicKey = ? where cicNumber = ?;";
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, user.getPublicKey());
            updateStatement.setString(2, user.getCicNumber());
            updateStatement.executeUpdate();

            updateStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public int getBalance(String cardNumber) {
        Connection con = getConnection();
        String sql = "SELECT balance FROM smart_card_entity Where cicNumber = ?;";
        try {
            PreparedStatement checkStatement = con.prepareStatement(sql);
            checkStatement.setString(1, cardNumber);
            ResultSet rs = checkStatement.executeQuery(sql);
            int Balance = rs.getInt("balance");
            return Balance;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return 0;
    }

    public boolean updateBalance(DataUser user) {
        Connection con = getConnection();
        String sql = "UPDATE smart_card_entity SET balance = ? Where cicNumber = ?;";
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1, user.getBalance());
            updateStatement.setString(2, user.getCicNumber());
            updateStatement.executeUpdate();
            updateStatement.close();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            closeConnection(con);
        }
        return false;
    }

    public boolean setIsCreated(String userId) {
        try (Connection con = getConnection()) {
            String sql = "UPDATE patient_entity SET smartCardStatus = 'PUBLISH' WHERE id = ?;";
            try (PreparedStatement updateStatement = con.prepareStatement(sql)) {
                updateStatement.setString(1, userId);
                int rowsAffected = updateStatement.executeUpdate();
                System.out.println("row " + rowsAffected);
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return false;
    }

    public static List<HoaDon> getHoaDonList(String maBn) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        int maxLength = 3;
        String idString = maBn.substring(maxLength);
        int Id = Integer.parseInt(idString);

        try {
            connection = getConnection();
            String sql = "SELECT EC, medicine, total FROM histories WHERE patientId = ? AND isPayment = 0";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, Id);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String dichVu = resultSet.getString("EC");
                String thuoc = resultSet.getString("medicine");
                int tongTien = resultSet.getInt("total");

                HoaDon hoaDon = new HoaDon(dichVu, thuoc, tongTien);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return hoaDonList;
    }
//    public boolean UpdateIsPayment(String maBn)
//    {
//        Connection con = getConnection();
//        String sql = "UPDATE histories SET isPayment = 1 Where patientId = ? AND isPayment = 0;";
//        int maxLength = 3;
//        String idString = maBn.substring(maxLength);
//        int Id = Integer.parseInt(idString);
//        try {
//            PreparedStatement updatePreparedStatement = con.prepareStatement(sql);
//            updatePreparedStatement.setInt(1, Id);
//            updatePreparedStatement.executeUpdate(sql);
//            updatePreparedStatement.close();
//            return true;
//        } catch (Exception e) {
//            System.out.println(e);
//        } finally {
//            closeConnection(con);
//        }
//        return false;
//    }

    public boolean updateIsPayment(String maBn) {
        Connection con = getConnection();
        String sql = "UPDATE histories SET isPayment = 1 WHERE patientId = ? AND isPayment = 0 OR isPayment = null ;";
        int maxLength = 3;
        String idString = maBn.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updatePreparedStatement = con.prepareStatement(sql);
            updatePreparedStatement.setInt(1, Id);
            updatePreparedStatement.executeUpdate();
            updatePreparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return false;
    }

}

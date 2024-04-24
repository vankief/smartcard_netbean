/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

import com.mysql.cj.jdbc.Blob;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
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
        String sql = "SELECT smart_card_entity.name, smart_card_entity.id, smart_card_entity.dob, smart_card_entity.address, smart_card_entity.gender, smart_card_entity.phone, smart_card_entity.publicKey, smart_card_entity.balance, smart_card_entity.createdAt\n"
                + "FROM smart_card_entity\n"
                + "JOIN patient_entity ON patient_entity.id = smart_card_entity.patientId\n"
                + "WHERE patient_entity.smartCardStatus = 'PENDING';";
        String maBV = "BN";
        int maxLength = 3;

        try {
            Statement selectStatement = con.createStatement();
            ResultSet rs = selectStatement.executeQuery(sql);
            while (rs.next()) {
                int Id = rs.getInt("id");
                StringBuilder stringBuilder = new StringBuilder(maBV);
                stringBuilder.append(String.format("%0" + maxLength + "d", Id));
                String cardId = stringBuilder.toString();
                String name = rs.getString("name");
                String dob = rs.getString("dob");
                String address = rs.getString("address");
                Boolean gender1 = rs.getBoolean("gender");
                String gender = "";
                if (gender1) {
                    gender = "Nam";
                } else {
                    gender = "Nữ";
                }
                Blob image = null;
                String phone = rs.getString("phone");
                String publicKey = rs.getString("publicKey");
                int balance = rs.getInt("balance");
                String registrationDate = convertDateFormat(rs.getDate("createdAt"));
                DataUser dataUser = new DataUser(cardId, name, dob, address, gender, image, phone, publicKey, balance, registrationDate);
                list.add(dataUser);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return list;
    }

    public boolean Blocked(String maBN) {
        Connection con = getConnection();
        String sql = "UPDATE smart_card_entity SET isBlocked = 1 WHERE id = ?;";
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1, Id);
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

    public boolean CheckCardBlocked(String maBN) {
        Connection con = getConnection();
        String sql = "SELECT isBlocked FROM smart_card_entity WHERE id = ?;";
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement checkStatement = con.prepareStatement(sql);
            checkStatement.setInt(1, Id);
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

    public boolean UnBlock(String maBN) {
        Connection con = getConnection();
        String sql = "UPDATE  smart_card_entity SET isBlocked = 0 Where id = ?;";
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updatePreparedStatement = con.prepareStatement(sql);
            updatePreparedStatement.setInt(1, Id);
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
        String sql = "UPDATE  smart_card_entity SET name = ?, dob = ?, address = ?, phone= ?, publicKey = ? Where id = ?;";

        int maxLength = 3;
        String idString = user.getCardId().substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, user.getName());
            updateStatement.setString(2, user.getDob());
            updateStatement.setString(3, user.getAddress());
            updateStatement.setString(4, user.getPhone());
            updateStatement.setString(5, user.getPublicKey());
            updateStatement.setInt(6, Id);
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
        String sql = "UPDATE smart_card_entity set publicKey = ? where id = ?;";

        int maxLength = 3;
        String idString = user.getCardId().substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setString(1, user.getPublicKey());
            updateStatement.setInt(2, Id);
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

    public PublicKey getpublicKey(String maBN) {
        Connection con = getConnection();
        String sql = "SELECT publicKey FROM smart_card_entity WHERE id = ?;";
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement selectStatement = con.prepareStatement(sql);
            selectStatement.setInt(1, Id);
            ResultSet rs = selectStatement.executeQuery();
            if (rs.next()) {
                String publicKeyString = rs.getString("publicKey");
                // Chuyển đổi chuỗi khóa công khai thành đối tượng PublicKey
                PublicKey publicKey = convertStringToPublicKey(publicKeyString);
                return publicKey;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeConnection(con);
        }
        return null;
    }

    // Phương thức chuyển đổi chuỗi khóa công khai sang đối tượng PublicKey
    private PublicKey convertStringToPublicKey(String publicKeyString) throws Exception {
        byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    public int getBalance(String maBN) {
        Connection con = getConnection();
        String sql = "SELECT balance FROM smart_card_entity Where id = ?;";

        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement checkStatement = con.prepareStatement(sql);
            checkStatement.setInt(1, Id);
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
        String sql = "UPDATE smart_card_entity SET balance = ? Where id = ?;";

        int maxLength = 3;
        String idString = user.getCardId().substring(maxLength);
        int Id = Integer.parseInt(idString);
        try {
            PreparedStatement updateStatement = con.prepareStatement(sql);
            updateStatement.setInt(1, user.getBalance());
            updateStatement.setInt(2, Id);
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

    public boolean setIsCreated(String maBN) {
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        try (Connection con = getConnection()) {
            String sql = "UPDATE patient_entity SET smartCardStatus = 'PUBLISH' WHERE smartcardId = ?;";
            try (PreparedStatement updateStatement = con.prepareStatement(sql)) {
                updateStatement.setInt(1, Id);
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

    public static List<HoaDon> getHoaDonList(String maBN) {
        List<HoaDon> hoaDonList = new ArrayList<>();
        Connection connection = getConnection();
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int Id = Integer.parseInt(idString);
        String patientId = getPatientId(Id);

        try {

            String sql = "SELECT appointment_entity.fee, doctor_entity.name AS doctorName, specialist_entity.name AS specialistName "
                    + "FROM appointment_entity "
                    + "JOIN doctor_entity ON appointment_entity.doctorId = doctor_entity.id "
                    + "JOIN specialist_entity ON doctor_entity.specialistId = specialist_entity.id "
                    + "WHERE appointment_entity.patientId = ? AND appointment_entity.status = 'AWAITING_PAYMENT' "
                    + "AND appointment_entity.paymentType = 'SMARTCARD'";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, patientId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int fee = resultSet.getInt("fee");
                String doctorName = resultSet.getString("doctorName");
                String specialistName = resultSet.getString("specialistName");
                HoaDon hoaDon = new HoaDon(doctorName, specialistName, fee);
                hoaDonList.add(hoaDon);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
        }

        return hoaDonList;
    }

    public static String getPatientId(int smartcardId) {
        String patientId = "";

        try (Connection con = getConnection()) {
            String sql = "SELECT id FROM patient_entity WHERE smartcardId = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, smartcardId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patientId = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return patientId;
    }

    public boolean updateAppointmentStatus(String maBN) {
        int maxLength = 3;
        String idString = maBN.substring(maxLength);
        int smartcardId = Integer.parseInt(idString);
        Connection connection = null;
        PreparedStatement statement = null;
        boolean success = false;

        try {
            connection = getConnection();
            String updateSql = "UPDATE appointment_entity "
                    + "SET status = 'APPROVED' "
                    + "WHERE patientId = (SELECT patientId FROM smart_card_entity WHERE smartcardId = ?) "
                    + "AND status = 'AWAITING_PAYMENT' "
                    + "AND paymentType = 'SMARTCARD'";
            statement = connection.prepareStatement(updateSql);
            statement.setInt(1, smartcardId);
            int rowsAffected = statement.executeUpdate();

            success = rowsAffected > 0;

            if (success) {
                System.out.println("Trạng thái của cuộc hẹn đã được cập nhật thành công.");
            } else {
                System.out.println("Không có cuộc hẹn nào được cập nhật.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection(connection);
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return success;
    }

    public static String convertDateFormat(Date inputDate) {
        DateFormat outputDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return outputDateFormat.format(inputDate);
    }
}

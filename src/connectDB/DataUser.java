/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author admin
 */
public class DataUser {
    public String userId;
    public String cicNumber ;
    public String name ;
    public String dob ;
    public String address;
    public String gender;
    public Blob image ;
    public String phone;
    public String publicKey;
    int balance;
    Date registrationDate;
    boolean isBlocked;
    public DataUser() {
    }
    
//    
   
    public DataUser(String userId, String cicNumber, String name, String dob, String address, String gender, Blob image, String phone, String publicKey, int balance, Date registrationDate) 
    {
        this.userId = userId;
        this.cicNumber = cicNumber;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.image = image;
        this.phone = phone;
        this.publicKey = publicKey;
        this.balance = balance;
        this.registrationDate = registrationDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCicNumber() {
        return cicNumber;
    }

    public void setCicNumber(String cicNumber) {
        this.cicNumber = cicNumber;
    }
     
    public String getDob() {
        return dob;
    }

    public void setDob(String Dob) {
        this.dob = Dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }


    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

  

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

public class HoaDon {
   private String doctorName;
   private String specialistName;
   private int fee;

    public HoaDon() {
    }

    public HoaDon(String doctorName, String specialistName, int fee) {
        this.doctorName = doctorName;
        this.specialistName = specialistName;
        this.fee = fee;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialistName() {
        return specialistName;
    }

    public void setSpecialistName(String specialistName) {
        this.specialistName = specialistName;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }
   
}
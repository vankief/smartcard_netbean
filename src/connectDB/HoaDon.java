/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

public class HoaDon {
   private String appointmentId;
   private String doctorName;
   private String specialistName;
   private int fee;

    public HoaDon() {
    }

    public HoaDon(String appointmentId, String doctorName, String specialistName, int fee) {
        this.appointmentId = appointmentId;
        this.doctorName = doctorName;
        this.specialistName = specialistName;
        this.fee = fee;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentIdS) {
        this.appointmentId = appointmentId;
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
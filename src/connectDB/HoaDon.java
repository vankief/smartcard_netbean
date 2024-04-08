/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connectDB;

public class HoaDon {
    private int idBenhNhan;
    private String dichVu;
    private String thuoc;
    private int tongTien;

     public HoaDon() {
    }
    public HoaDon(int idBenhNhan, String dichVu, String thuoc, int tongTien) {
        this.idBenhNhan = idBenhNhan;
        this.dichVu = dichVu;
        this.thuoc = thuoc;
        this.tongTien = tongTien;
    }

    public HoaDon(String dichVu, String thuoc, int tongTien) {
        this.dichVu = dichVu;
        this.thuoc = thuoc;
        this.tongTien = tongTien;
    }
    
   
    

    public int getIdBenhNhan() {
        return idBenhNhan;
    }

    public void setIdBenhNhan(int idBenhNhan) {
        this.idBenhNhan = idBenhNhan;
    }

    public String getDichVu() {
        return dichVu;
    }

    public void setDichVu(String dichVu) {
        this.dichVu = dichVu;
    }

    public String getThuoc() {
        return thuoc;
    }

    public void setThuoc(String thuoc) {
        this.thuoc = thuoc;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
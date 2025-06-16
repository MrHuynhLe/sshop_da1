/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class GioHang {
    private int idSpct;
     private String maSPCT;
    private String tenSP;
    private int soLuong;
    private float giaBan;

    public GioHang() {
    }

    public GioHang(int idSpct, String maSPCT, String tenSP, int soLuong, float giaBan) {
        this.idSpct = idSpct;
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.soLuong = soLuong;
        this.giaBan = giaBan;
    }

    public int getIdSpct() {
        return idSpct;
    }

    public void setIdSpct(int idSpct) {
        this.idSpct = idSpct;
    }

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(float giaBan) {
        this.giaBan = giaBan;
    }

   
    
}

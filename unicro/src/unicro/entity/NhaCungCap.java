/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class NhaCungCap {
     private int id;
    private String ten;
    private String diaChi;
    private String maNhaCungCap;

    public NhaCungCap() {
    }

    public NhaCungCap(int id, String ten, String diaChi, String maNhaCungCap) {
        this.id = id;
        this.ten = ten;
        this.diaChi = diaChi;
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    
}

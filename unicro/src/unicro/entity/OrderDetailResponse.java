/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class OrderDetailResponse {
       private int idSpct;
    private String maSp;
    private String tenSp;
    private int soLuong;
    private BigDecimal gia;
    private BigDecimal thanhTien;

    public OrderDetailResponse() {
    }

    public OrderDetailResponse(int idSpct, String maSp, String tenSp, int soLuong, BigDecimal gia, BigDecimal thanhTien) {
        this.idSpct = idSpct;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.soLuong = soLuong;
        this.gia = gia;
        this.thanhTien = thanhTien;
    }

    public int getIdSpct() {
        return idSpct;
    }

    public void setIdSpct(int idSpct) {
        this.idSpct = idSpct;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public BigDecimal getGia() {
        return gia;
    }

    public void setGia(BigDecimal gia) {
        this.gia = gia;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}

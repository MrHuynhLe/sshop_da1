/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.util.Date;

/**
 *
 * @author Admin
 */
public class SanPhamChiTiet {

    private int id;
    private int idSp;
    private String maSp;
    private int maNhaCungCap;
    private int maMau;
    private int maSize;
    private int maChatLieu;
    private int soLuong;
    private float donGia;
    private String moTa;
    private Date ngayTao;
    private int maThuongHieu;
    private boolean trangThai;

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int id, int idSp, String maSp, int maNhaCungCap, int maMau, int maSize, int maChatLieu, int soLuong, float donGia, String moTa, Date ngayTao, int maThuongHieu, boolean trangThai) {
        this.id = id;
        this.idSp = idSp;
        this.maSp = maSp;
        this.maNhaCungCap = maNhaCungCap;
        this.maMau = maMau;
        this.maSize = maSize;
        this.maChatLieu = maChatLieu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.maThuongHieu = maThuongHieu;
        this.trangThai = trangThai;
    }

    public SanPhamChiTiet(int idSp, int maNhaCungCap, int maMau, int maSize, int maChatLieu, int soLuong, float donGia, String moTa, Date ngayTao, int maThuongHieu, boolean trangThai) {
        this.idSp = idSp;
        this.maNhaCungCap = maNhaCungCap;
        this.maMau = maMau;
        this.maSize = maSize;
        this.maChatLieu = maChatLieu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.maThuongHieu = maThuongHieu;
        this.trangThai = trangThai;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public int getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(int maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public int getMaMau() {
        return maMau;
    }

    public void setMaMau(int maMau) {
        this.maMau = maMau;
    }

    public int getMaSize() {
        return maSize;
    }

    public void setMaSize(int maSize) {
        this.maSize = maSize;
    }

    public int getMaChatLieu() {
        return maChatLieu;
    }

    public void setMaChatLieu(int maChatLieu) {
        this.maChatLieu = maChatLieu;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getDonGia() {
        return donGia;
    }

    public void setDonGia(float donGia) {
        this.donGia = donGia;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(int maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
  

  
}

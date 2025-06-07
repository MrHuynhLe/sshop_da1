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
public class SanPhamChiTietResponse {

    private int id;
    private String tenSanPham;
    private String tenNhaCungCap;
    private String tenMau;
    private String tenSize;
    private String tenChatLieu;
    private int soLuong;
    private float donGia;
    private String moTa;
    private Date ngayTao;
    private String thuongHieu;
    private boolean trangThai;

    public SanPhamChiTietResponse(int id, String tenSanPham, String tenNhaCungCap, String tenMau, String tenSize, String tenChatLieu, int soLuong, float donGia, String moTa, Date ngayTao, String thuongHieu, boolean trangThai) {
        this.id = id;
        this.tenSanPham = tenSanPham;
        this.tenNhaCungCap = tenNhaCungCap;
        this.tenMau = tenMau;
        this.tenSize = tenSize;
        this.tenChatLieu = tenChatLieu;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.moTa = moTa;
        this.ngayTao = ngayTao;
        this.thuongHieu = thuongHieu;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenMau() {
        return tenMau;
    }

    public void setTenMau(String tenMau) {
        this.tenMau = tenMau;
    }

    public String getTenSize() {
        return tenSize;
    }

    public void setTenSize(String tenSize) {
        this.tenSize = tenSize;
    }

    public String getTenChatLieu() {
        return tenChatLieu;
    }

    public void setTenChatLieu(String tenChatLieu) {
        this.tenChatLieu = tenChatLieu;
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

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "SanPhamChiTietResponse{"
                + "id=" + id
                + ", tenSanPham='" + tenSanPham + '\''
                + ", tenNhaCungCap='" + tenNhaCungCap + '\''
                + ", tenMau='" + tenMau + '\''
                + ", tenSize='" + tenSize + '\''
                + ", tenChatLieu='" + tenChatLieu + '\''
                + ", soLuong=" + soLuong
                + ", donGia=" + donGia
                + ", moTa='" + moTa + '\''
                + ", ngayTao='" + ngayTao + '\''
                + ", thuongHieu='" + thuongHieu + '\''
                + ", trangThai=" + trangThai
                + '}';
    }

}

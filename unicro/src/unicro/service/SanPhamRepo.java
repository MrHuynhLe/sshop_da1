/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import unicro.entity.SanPham;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class SanPhamRepo {

    private static final String url = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private static final String username = "postgres";
    private static final String password = "password";

    public ArrayList<SanPham> getAll() {
        String sql = """
        SELECT 
             sp.id,
             sp.ma_san_pham,
             sp.ten,
             sp.mo_ta,
             sp.ngay_tao,
             sp.trang_thai,
             sp.don_gia,
             COALESCE(SUM(spct.so_luong), 0) AS so_luong
         FROM 
             san_pham sp
             LEFT JOIN san_pham_chi_tiet spct ON spct.idsp = sp.id
         GROUP BY 
             sp.id,
             sp.ma_san_pham,
             sp.ten,
             sp.mo_ta,
             sp.ngay_tao,
             sp.trang_thai,
             sp.don_gia;
        """;
        ArrayList<SanPham> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPham sanPham = new SanPham();
                sanPham.setId(rs.getInt("id"));
                sanPham.setMaSP(rs.getString("ma_san_pham"));
                sanPham.setTenSP(rs.getString("ten"));
                sanPham.setMoTa(rs.getString("mo_ta"));
                sanPham.setNgayTao(rs.getDate("ngay_tao"));
                sanPham.setTrangThai(rs.getBoolean("trang_thai"));
                sanPham.setDonGia(rs.getFloat("don_gia"));
                sanPham.setSoLuong(rs.getInt("so_luong"));

                list.add(sanPham);
            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

    }

    public boolean remove(Integer id) {
        int check = 0;
        String sql = """
                  UPDATE san_pham
                  SET trangthai = 1
                  WHERE id = $1;
                     
                     """;
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, id);
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;

    }

    public boolean update(SanPham newSanPham) {
        int check = 0;
        String sql = """
         UPDATE san_pham
            SET ten = ?, mo_ta = ?, trang_thai = ?, don_gia = ?
            WHERE id = ?
      """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newSanPham.getTenSP());
            ps.setString(2, newSanPham.getMoTa());
            ps.setBoolean(3, newSanPham.isTrangThai());
            ps.setFloat(4, newSanPham.getDonGia());
            ps.setInt(5, newSanPham.getId());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public boolean add(SanPham sanPham) {
        int check = 0;
        String sql = """
            INSERT INTO san_pham
                  (ma_san_pham, ten, mo_ta, ngay_tao, trang_thai, don_gia)
                VALUES
                  (?, ?, ?, ?, ?, ?);
                """;
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setObject(1, sanPham.getMaSP());
            ps.setObject(2, sanPham.getTenSP());
            ps.setObject(3, sanPham.getMoTa());
            java.util.Date ngayTao = sanPham.getNgayTao();
            if (ngayTao != null) {
                ps.setDate(4, new java.sql.Date(ngayTao.getTime()));
            } else {
                ps.setDate(4, null);
            }
            ps.setObject(5, sanPham.isTrangThai());
            ps.setObject(6, sanPham.getDonGia());
            check = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return check > 0;
    }

    public ArrayList<SanPham> search(String keyword) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}

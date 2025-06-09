/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.util.ArrayList;
import java.util.List;
import unicro.entity.SanPham;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.ZoneId;
import unicro.config.Connect;
import unicro.entity.OrderDetail;
import unicro.entity.SanPhamChiTiet;

/**
 *
 * @author Admin
 */
public class SanPhamService {

    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String user = "postgres";
    private final String password = "password";

  public List<SanPhamChiTiet> getAll() {
    List<SanPhamChiTiet> list = new ArrayList<>();
    String sql = "SELECT spct.ID, spct.IDSP, sp.MA_SAN_PHAM AS MaSP, spct.MA_NHA_CUNG_CAP, spct.MA_MAU, spct.MA_SIZE, "
               + "spct.MA_CHAT_LIEU, spct.SO_LUONG, spct.DON_GIA, spct.MO_TA, spct.NGAY_TAO, spct.MA_THUONG_HIEU, spct.TRANG_THAI "
               + "FROM SAN_PHAM_CHI_TIET spct "
               + "JOIN SAN_PHAM sp ON spct.IDSP = sp.ID";
    try (Connection conn = DriverManager.getConnection(url, user, password);
         PreparedStatement ps = conn.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setId(rs.getInt("ID"));
            spct.setIdSp(rs.getInt("IDSP"));
            spct.setMaSp(rs.getString("MaSP"));
            spct.setMaNhaCungCap(rs.getInt("MA_NHA_CUNG_CAP"));
            spct.setMaMau(rs.getInt("MA_MAU"));
            spct.setMaSize(rs.getInt("MA_SIZE"));
            spct.setMaChatLieu(rs.getInt("MA_CHAT_LIEU"));
            spct.setSoLuong(rs.getInt("SO_LUONG"));
            spct.setDonGia(rs.getFloat("DON_GIA"));
            spct.setMoTa(rs.getString("MO_TA"));

            Timestamp t = rs.getTimestamp("NGAY_TAO");
            if (t != null) {
                LocalDateTime ldt = t.toLocalDateTime();
                java.util.Date utilDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                spct.setNgayTao(sqlDate);
            }

            spct.setMaThuongHieu(rs.getInt("MA_THUONG_HIEU"));
            spct.setTrangThai(rs.getBoolean("TRANG_THAI"));
            list.add(spct);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}


   public SanPhamChiTiet findById(int id) {
    String sql = "SELECT spct.ID, spct.IDSP, sp.MA_SAN_PHAM AS MaSP, spct.MA_NHA_CUNG_CAP, spct.MA_MAU, spct.MA_SIZE, "
               + "spct.MA_CHAT_LIEU, spct.SO_LUONG, spct.DON_GIA, spct.MO_TA, spct.NGAY_TAO, spct.MA_THUONG_HIEU, spct.TRANG_THAI "
               + "FROM SAN_PHAM_CHI_TIET spct "
               + "JOIN SAN_PHAM sp ON spct.IDSP = sp.ID WHERE spct.ID = ?";
    try (Connection conn = Connect.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, id);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt("ID"));
                spct.setIdSp(rs.getInt("IDSP"));
                spct.setMaSp(rs.getString("MaSP"));
                spct.setMaNhaCungCap(rs.getInt("MA_NHA_CUNG_CAP"));
                spct.setMaMau(rs.getInt("MA_MAU"));
                spct.setMaSize(rs.getInt("MA_SIZE"));
                spct.setMaChatLieu(rs.getInt("MA_CHAT_LIEU"));
                spct.setSoLuong(rs.getInt("SO_LUONG"));
                spct.setDonGia(rs.getFloat("DON_GIA"));
                spct.setMoTa(rs.getString("MO_TA"));

                Timestamp t = rs.getTimestamp("NGAY_TAO");
                if (t != null) {
                    LocalDateTime ldt = t.toLocalDateTime();
                    java.util.Date utilDate = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    spct.setNgayTao(sqlDate);
                }

                spct.setMaThuongHieu(rs.getInt("MA_THUONG_HIEU"));
                spct.setTrangThai(rs.getBoolean("TRANG_THAI"));
                return spct;
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}


    public boolean updateSoLuong(int id, int soLuongMoi) {
        String sql = "UPDATE SAN_PHAM_CHI_TIET SET SO_LUONG = ? WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongMoi);
            ps.setInt(2, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}

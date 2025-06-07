/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import unicro.entity.ChatLieu;
import unicro.entity.MauSac;
import unicro.entity.NhaCungCap;
import unicro.entity.Size;
import unicro.entity.ThuongHieu;

/**
 *
 * @author Admin
 */
public class ThuocTinhRepo {
       private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String username = "postgres";
    private final String password = "password";
     public ArrayList<Object[]> getAllThuocTinh(String tenBang) {
        ArrayList<Object[]> listThuocTinh = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM " + tenBang
        //                     + "WHERE TrangThai = 0 ORDER BY" + getColumnNameForTable(tenBang) + "DESC"
        )) {
            while (rs.next()) {
                if (tenBang.equalsIgnoreCase("MAU_SAC")) {
                    String maMau = rs.getString("MA_MAU");
                    String tenMauSac = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maMau, tenMauSac});
                } else if (tenBang.equalsIgnoreCase("NHA_CUNG_CAP")) {
                    String maNhaCungCap = rs.getString("MA_NHA_CUNG_CAP");
                    String tenNhaCungCap = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maNhaCungCap, tenNhaCungCap});
                } else if (tenBang.equalsIgnoreCase("SIZE")) {
                    String maSize = rs.getString("MA_SIZE");
                    String tenSize = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maSize, tenSize});
                } else if (tenBang.equalsIgnoreCase("CHAT_LIEU")) {
                    String maChatLieu = rs.getString("MA_CHAT_LIEU");
                    String tenChatLieu = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maChatLieu, tenChatLieu});
                } else if (tenBang.equalsIgnoreCase("THUONG_HIEU")) {
                    String maThuongHieu = rs.getString("MA_THUONG_HIEU");
                    String tenThuongHieu = rs.getString("TEN");
                    listThuocTinh.add(new Object[]{maThuongHieu, tenThuongHieu});
                
                } else {
                    // Các bảng khác, lấy mã thuộc tính và tên thuộc tính
                    String maThuocTinh = rs.getString(1); // Cột đầu tiên là mã
                    String tenThuocTinh = rs.getString(2); // Cột thứ hai là tên
                    listThuocTinh.add(new Object[]{maThuocTinh, tenThuocTinh});
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listThuocTinh;
    }

    private String getColumnNameForTable(String tenBang) {
        switch (tenBang) {
            case "MAU_SAC":
                return "MA_MAU";
            case "NHA_CUNG_CAP":
                return "MA_NHA_CUNG_CAP";
            case "SIZE":
                return "MA_SIZE";
            // ... (các bảng khác)
            default:
                return "MA_THUOC_TINH"; // Giả sử các bảng khác đều có cột MA_THUOC_TINH
        }
    }

    public boolean kiemTraMaThuocTinhTonTai(String tenBang, String maThuocTinh) throws SQLException {
        String sql = "SELECT COUNT(*) FROM " + tenBang + " WHERE MaThuocTinh = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, maThuocTinh);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
                    return count > 0;
                }
            }
        }
        return false;
    }

    public void addThuocTinh(String tenBang, String maThuocTinh, String tenThuocTinh) throws SQLException {
            String sql = "INSERT INTO " + tenBang + " VALUES (?, ?)";
            try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maThuocTinh);
                ps.setString(2, tenThuocTinh);
                ps.executeUpdate();
            }
    }
    
     public void addNhaCC(String tenBang, String maThuocTinh, String tenThuocTinh, String diaChi) throws SQLException {
        if (tenBang.equalsIgnoreCase("NHA_CUNG_CAP")) {
            String sql = "INSERT INTO " + tenBang + " VALUES (?, ?, ?)";
            try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, maThuocTinh);
                ps.setString(2, tenThuocTinh);
                ps.setString(3, diaChi);
                ps.executeUpdate();
            }
        }
     }
     
     public void updateNhaCC(String tenBang, String maThuocTinh, String maTT, String tenThuocTinhMoi, String diaChi) throws SQLException {
        String sql = "UPDATE " + tenBang + " SET TEN = ?, DIA_CHI = ? WHERE " + maTT + " = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinhMoi);
            ps.setString(2, diaChi);
            ps.setString(3, maThuocTinh);
            ps.executeUpdate();
        }
    }
     
     
     
     

    public void updateThuocTinh(String tenBang, String maThuocTinh, String maTT, String tenThuocTinhMoi) throws SQLException {
        String sql = "UPDATE " + tenBang + " SET TEN = ? WHERE " + maTT + " = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinhMoi);
            ps.setString(2, maThuocTinh);
            ps.executeUpdate();
        }
    }

//    public void deleteThuocTinh(String tenBang, String maThuocTinh) throws SQLException {
//    String cotMaThuocTinh = tenBang.substring(0, tenBang.length() - 1); // Xác định tên cột mã 
//    String sql = "DELETE FROM " + tenBang + " WHERE " + cotMaThuocTinh + " = ?";
//    try (Connection con = DBConnect.getConnection();
//         PreparedStatement ps = con.prepareStatement(sql)) {
//        ps.setString(1, maThuocTinh);
//        ps.executeUpdate();
//    }
//}
    public void deleteThuocTinh(String tenBang, String maThuocTinh) throws SQLException {
        String cotMaThuocTinh;
        switch (tenBang) {
            case "CHAT_LIEU":
                cotMaThuocTinh = "MA_CHAT_LIEU";
                break;
            case "THUONG_HIEU":
                cotMaThuocTinh = "MA_THUONG_HIEU";
                break;

            default:
                cotMaThuocTinh = tenBang.substring(0, tenBang.length() - 1);
        }

        String sql = "DELETE FROM " + tenBang + " WHERE " + cotMaThuocTinh + " = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maThuocTinh);
            ps.executeUpdate();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<Size> getSize() {
        ArrayList<Size> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM SIZE";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                Size dc = new Size();
                dc.setId(rs.getInt(1));
                dc.setMaSize(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<ChatLieu> getCL() {
        ArrayList<ChatLieu> listCDV = new ArrayList<>();
        String query = "SELECT * FROM CHAT_LIEU";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                ChatLieu dc = new ChatLieu();
                dc.setId(rs.getInt(1));
                dc.setMaChatLieu(rs.getString(2));
                dc.setTen(rs.getString(3));
                listCDV.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listCDV;
    }

    public ArrayList<ThuongHieu> getTH() {
        ArrayList<ThuongHieu> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM THUONG_HIEU";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {

            while (rs.next()) {
                ThuongHieu dc = new ThuongHieu();
                dc.setId(rs.getInt(1));
                dc.setMaThuongHieu(rs.getString(2));
                dc.setTen(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

   

    public ArrayList<MauSac> getMauSac() {
        ArrayList<MauSac> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM MAU_SAC";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {
            while (rs.next()) {
                MauSac dc = new MauSac();
                dc.setId(rs.getInt(1));
                dc.setMaMauSac(rs.getString(2));
                dc.setTenMau(rs.getString(3));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

    public ArrayList<NhaCungCap> getNhaCungCap() {
        ArrayList<NhaCungCap> listDoCang = new ArrayList<>();
        String query = "SELECT * FROM NHA_CUNG_CAP";
        try (Connection conn = DriverManager.getConnection(url, username, password); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query
        )) {
            while (rs.next()) {
                NhaCungCap dc = new NhaCungCap();
                dc.setId(rs.getInt(1));
                dc.setMaNhaCungCap(rs.getString(2));
                dc.setTen(rs.getString(3));
                dc.setDiaChi(rs.getString(4));
                listDoCang.add(dc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listDoCang;
    }

   
}

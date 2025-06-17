/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import unicro.config.Connect;
import unicro.entity.ChatLieu;
import unicro.entity.MauSac;
import unicro.entity.SanPhamChiTiet;
import unicro.entity.SanPhamChiTietResponse;
import unicro.entity.Size;
import unicro.entity.ThuongHieu;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class SanPhamChiTietRepo {

    private final String url = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public ArrayList<SanPhamChiTietResponse> getAllSPCT() {
        String sql = """
           SELECT 
               spct.id, 
               sp.ma_san_pham AS ma_san_pham,
               sp.ten AS ten_san_pham, 
               ncc.ten AS ten_nha_cung_cap,
               ms.ten AS ten_mau, 
               size.ten AS ten_size, 
               chat_lieu.ten AS ten_chat_lieu,
               spct.so_luong, 
               spct.don_gia, 
               spct.mo_ta, 
               spct.ngay_tao,
               thuong_hieu.ten AS ten_thuong_hieu, 
               spct.trang_thai
           FROM san_pham_chi_tiet spct
           JOIN san_pham sp ON spct.idsp = sp.id
           JOIN nha_cung_cap ncc ON spct.ma_nha_cung_cap = ncc.id
           JOIN mau_sac ms ON spct.ma_mau = ms.id
           JOIN size size ON spct.ma_size = size.id
           JOIN chat_lieu chat_lieu ON spct.ma_chat_lieu = chat_lieu.id
           JOIN thuong_hieu thuong_hieu ON spct.ma_thuong_hieu = thuong_hieu.id;
       """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPhamChiTietResponse spct = new SanPhamChiTietResponse(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getString("ten_mau"),
                        rs.getString("ten_size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getInt("so_luong"),
                        rs.getFloat("don_gia"),
                        rs.getString("mo_ta"),
                        rs.getDate("ngay_tao"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getBoolean("trang_thai")
                );
                list.add(spct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean update(SanPhamChiTiet spct) {
        String sql = """
     
         UPDATE san_pham_chi_tiet
            SET 
                idsp = ?,
                ma_nha_cung_cap = ?,
                ma_mau = ?,
                ma_size = ?,
                ma_chat_lieu = ?,
                so_luong = ?,
                don_gia = ?,
                mo_ta = ?,
                ngay_tao = ?,
                ma_thuong_hieu = ?,
                trang_thai = ?
            WHERE id = ?;
           """;
        int check = 0;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getIdSp());
            ps.setInt(2, spct.getMaNhaCungCap());
            ps.setInt(3, spct.getMaMau());
            ps.setInt(4, spct.getMaSize());
            ps.setInt(5, spct.getMaChatLieu());
            ps.setInt(6, spct.getSoLuong());
            ps.setFloat(7, spct.getDonGia());
            ps.setString(8, spct.getMoTa());
            ps.setDate(9, new Date(spct.getNgayTao().getTime()));
            ps.setInt(10, spct.getMaThuongHieu());
            ps.setBoolean(15, spct.isTrangThai());
            ps.setInt(16, spct.getId());
            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietResponse> search(String tenSanPham) {
        String sql = """
            SELECT 
                    spct.id, 
                    sp.ma_san_pham, 
                    sp.ten AS ten_san_pham,
                    ncc.ten AS ten_nha_cung_cap, 
                    ms.ten AS ten_mau,
                    s.ten AS ten_size, 
                    cl.ten AS ten_chat_lieu,
                    spct.so_luong, 
                    spct.don_gia, 
                    spct.mo_ta, 
                    spct.ngay_tao,
                    th.ten AS ten_thuong_hieu, 
                    spct.trang_thai
                FROM san_pham_chi_tiet spct
                JOIN san_pham sp ON spct.idsp = sp.id
                JOIN nha_cung_cap ncc ON spct.ma_nha_cung_cap = ncc.id
                JOIN mau_sac ms ON spct.ma_mau = ms.id
                JOIN size s ON spct.ma_size = s.id
                JOIN chat_lieu cl ON spct.ma_chat_lieu = cl.id
                JOIN thuong_hieu th ON spct.ma_thuong_hieu = th.id
                WHERE sp.ten ILIKE ?
    """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenSanPham + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("TEN_SIZE"),
                        rs.getString("TEN_CHAT_LIEU"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("TEN_THUONG_HIEU"),
                        rs.getBoolean("TRANG_THAI")
                );
                list.add(spctResponse);
            }

        } catch (SQLException e) {
            System.err.println("Lỗi khi tìm kiếm sản phẩm chi tiết: " + e.getMessage());
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi tìm kiếm sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

        return list;
    }

    public ArrayList<String> getAllThem(String tenBang) {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT TEN FROM " + tenBang;

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("TEN"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void addThuocTinh(String tenBang, String tenThuocTinh) throws SQLException {
        String sql = "INSERT INTO " + tenBang + " (TEN) VALUES (?)";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenThuocTinh);
            ps.executeUpdate();
        }
    }

    public boolean updateSoLuong(SanPhamChiTiet spct) {
        String sql = """
 
           UPDATE san_pham_chi_tiet
            SET so_luong = ?
            WHERE id = ?;

           """;
        int check = 0;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, spct.getSoLuong());
            ps.setInt(2, spct.getId());

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public ArrayList<SanPhamChiTietResponse> LocSPCT(Integer mauSac_id, Integer size_id, Integer thuongHieu_id, Integer chatLieu_id) {
        String sql = """
       SELECT SPCT.id, SP.ten AS ten_san_pham, NCC.ten AS ten_nha_cung_cap,
                 MS.ten AS ten_mau, S.ten AS ten_size, CL.ten AS ten_chat_lieu,
                 SPCT.so_luong, SPCT.don_gia, SPCT.mo_ta, SPCT.ngay_tao,
                 TH.ten AS ten_thuong_hieu, SPCT.trang_thai
          FROM san_pham_chi_tiet SPCT
          JOIN san_pham SP ON SPCT.idsp = SP.id
          JOIN nha_cung_cap NCC ON SPCT.ma_nha_cung_cap = NCC.id
          JOIN mau_sac MS ON SPCT.ma_mau = MS.id
          JOIN size S ON SPCT.ma_size = S.id
          JOIN chat_lieu CL ON SPCT.ma_chat_lieu = CL.id
          JOIN thuong_hieu TH ON SPCT.ma_thuong_hieu = TH.id
          WHERE SP.trang_thai = 0
            AND ($1::UUID IS NULL OR SPCT.ma_mau = $1::UUID)
            AND ($2::UUID IS NULL OR SPCT.ma_size = $2::UUID)
            AND ($3::UUID IS NULL OR SPCT.ma_thuong_hieu = $3::UUID)
            AND ($4::UUID IS NULL OR SPCT.ma_chat_lieu = $4::UUID);
    """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, mauSac_id);
            ps.setObject(2, mauSac_id);
            ps.setObject(3, size_id);
            ps.setObject(4, size_id);
            ps.setObject(5, thuongHieu_id);
            ps.setObject(6, thuongHieu_id);
            ps.setObject(7, chatLieu_id);
            ps.setObject(8, chatLieu_id);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("TEN_SIZE"),
                        rs.getString("TEN_CHAT_LIEU"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"),
                        rs.getString("TEN_THUONG_HIEU"),
                        rs.getBoolean("TRANG_THAI")
                );
                list.add(spctResponse);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }

    public MauSac selectByTenMau(String tenMau) {
        String sql = " SELECT ID, TEN ,MA_MAU FROM MAU_SAC where TEN like ?";
        List<MauSac> lst = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, tenMau);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac(
                        rs.getInt(1), rs.getString(2),
                        rs.getString(3)
                );
                lst.add(ms);
            }
            if (!lst.isEmpty()) {
                return lst.get(0);
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public Size SelectbyTenSize(String tenSize) {
        String sql = "SELECT ID, TEN, MA_SIZE FROM SIZE WHERE TEN like?";
        List<Size> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenSize);

            while (rs.next()) {
                Size s = new Size();
                s.setId(rs.getInt("ID"));
                s.setTen(rs.getString("TEN"));
                s.setMaSize(rs.getString("MA_SIZE"));

                list.add(s);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

        return null;

    }

    public ChatLieu SelectbyTenChatLieu(String tenChatLieu) {
        String sql = "SELECT ID, TEN, MA_CHAT_LIEU FROM CHAT_LIEU WHERE TEN like ?";
        List<ChatLieu> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenChatLieu);
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("ID"));
                cl.setTen(rs.getString("TEN"));
                cl.setMaChatLieu(rs.getString("MA_CHAT_LIEU"));

                list.add(cl);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }

        return null;
    }

    public ThuongHieu SelectbyTenThuongHieu(String tenThuongHieu) {
        String sql = "SELECT ID, TEN, MA_THUONG_HIEU FROM THUONG_HIEU WHERE TEN like ?";
        List<ThuongHieu> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            ps.setObject(1, tenThuongHieu);
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("ID"));
                th.setTen(rs.getString("TEN"));
                th.setMaThuongHieu(rs.getString("MA_THUONG_HIEU"));
                list.add(th);
            }
            if (!list.isEmpty()) {
                return list.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
        return null;
    }

    public ArrayList<SanPhamChiTietResponse> getProductDetailByIdProduct(Integer idSP) {
        String sql = """
        SELECT 
            SPCT.id, 
            sp.ma_san_pham AS ma_san_pham,
            SP.ten AS ten_san_pham, 
            NCC.ten AS ten_nha_cung_cap,
            MS.ten AS ten_mau, 
            S.ten AS ten_size, 
            CL.ten AS ten_chat_lieu,
            SPCT.so_luong, 
            SPCT.don_gia, 
            SPCT.mo_ta, 
            SPCT.ngay_tao,
            TH.ten AS ten_thuong_hieu, 
            SPCT.trang_thai
        FROM san_pham_chi_tiet SPCT
        JOIN san_pham SP ON SPCT.idsp = SP.id
        JOIN nha_cung_cap NCC ON SPCT.ma_nha_cung_cap = NCC.id
        JOIN mau_sac MS ON SPCT.ma_mau = MS.id
        JOIN size S ON SPCT.ma_size = S.id
        JOIN chat_lieu CL ON SPCT.ma_chat_lieu = CL.id
        JOIN thuong_hieu TH ON SPCT.ma_thuong_hieu = TH.id
        WHERE SPCT.idsp = ?;
    """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getString("ten_mau"),
                        rs.getString("ten_size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getInt("so_luong"),
                        rs.getFloat("don_gia"),
                        rs.getString("mo_ta"),
                        rs.getDate("ngay_tao"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getBoolean("trang_thai")
                );
                list.add(spctResponse);
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi truy vấn chi tiết sản phẩm: " + e.getMessage());
        }

        return list;
    }

    public SanPhamChiTietResponse getProductDetailResponseById(Integer idSPCT) {
        String sql = """
        SELECT spct.id,
               sp.ma_san_pham,
               sp.ten AS ten_san_pham,
               ncc.ten AS ten_nha_cung_cap,
               ms.ten AS ten_mau,
               size.ten AS ten_size,
               chat_lieu.ten AS ten_chat_lieu,
               spct.so_luong,
               spct.don_gia,
               spct.mo_ta,
               spct.ngay_tao,
               thuong_hieu.ten AS ten_thuong_hieu,
               spct.trang_thai
        FROM san_pham_chi_tiet spct
        JOIN san_pham sp ON spct.idsp = sp.id
        JOIN nha_cung_cap ncc ON spct.ma_nha_cung_cap = ncc.id
        JOIN mau_sac ms ON spct.ma_mau = ms.id
        JOIN size size ON spct.ma_size = size.id
        JOIN chat_lieu chat_lieu ON spct.ma_chat_lieu = chat_lieu.id
        JOIN thuong_hieu thuong_hieu ON spct.ma_thuong_hieu = thuong_hieu.id
        WHERE spct.id = ?;
    """;

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SanPhamChiTietResponse(
                        rs.getInt("id"),
                        rs.getString("ma_san_pham"),
                        rs.getString("ten_san_pham"),
                        rs.getString("ten_nha_cung_cap"),
                        rs.getString("ten_mau"),
                        rs.getString("ten_size"),
                        rs.getString("ten_chat_lieu"),
                        rs.getInt("so_luong"),
                        rs.getFloat("don_gia"),
                        rs.getString("mo_ta"),
                        rs.getDate("ngay_tao"),
                        rs.getString("ten_thuong_hieu"),
                        rs.getBoolean("trang_thai")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public boolean updateStatus(Integer idSpct, Boolean trangThai) {
        String sql = """
           UPDATE san_pham_chi_tiet
            SET trang_thai = $1
            WHERE id = $2;
           """;
        int check = 0;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, trangThai);
            ps.setObject(2, idSpct);

            check = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi cập nhật sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        return check > 0;
    }

    public SanPhamChiTiet getProductDetailyId(Integer idSPCT) {
        String sql = """
              SELECT 
                   spct.id,
                   spct.idsp,
                   sp.ma_san_pham,
                   spct.ma_nha_cung_cap,
                   spct.ma_mau,
                   spct.ma_size,
                   spct.ma_chat_lieu,
                   spct.so_luong,
                   spct.don_gia,
                   spct.mo_ta,
                   spct.ngay_tao,
                   spct.ma_thuong_hieu,
                   spct.trang_thai
               FROM san_pham_chi_tiet spct
               JOIN san_pham sp ON spct.idsp = sp.id
               WHERE spct.id = ?;
                    """;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                float donGia = rs.getBigDecimal(9).floatValue();
                SanPhamChiTiet spct = new SanPhamChiTiet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getInt(8),
                        donGia,
                        rs.getString(10),
                        rs.getDate(11),
                        rs.getInt(12),
                        rs.getBoolean(13));
                return spct;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int creatNewProductDetail(SanPhamChiTiet spct) {
        String sql = """
                  INSERT INTO san_pham_chi_tiet
                                    (idsp,
                                     ma_nha_cung_cap,
                                     ma_mau,
                                     ma_size,
                                     ma_chat_lieu,
                                     so_luong,
                                     don_gia,
                                     mo_ta,
                                     ngay_tao,
                                     ma_thuong_hieu,
                                     trang_thai)
                                VALUES
                                    (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);
                    """;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, spct.getIdSp());
            ps.setObject(2, spct.getMaNhaCungCap());
            ps.setObject(3, spct.getMaMau());
            ps.setObject(4, spct.getMaSize());
            ps.setObject(5, spct.getMaChatLieu());
            ps.setObject(6, spct.getSoLuong());
            ps.setObject(7, spct.getDonGia());
            ps.setObject(8, spct.getMoTa());
            ps.setDate(9, new java.sql.Date(spct.getNgayTao().getTime()));
            ps.setObject(10, spct.getMaThuongHieu());
            ps.setObject(11, spct.isTrangThai());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public int updateProductDetail(SanPhamChiTiet spct) {
        String sql = """
                UPDATE san_pham_chi_tiet
                                 SET
                                     ma_nha_cung_cap = ?,
                                     ma_mau = ?,
                                     ma_size = ?,
                                     ma_chat_lieu = ?,
                                     so_luong = ?,
                                     don_gia = ?,
                                     mo_ta = ?,
                                     ma_thuong_hieu = ?
                                 WHERE id = ?;
                    """;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, spct.getMaNhaCungCap());
            ps.setObject(2, spct.getMaMau());
            ps.setObject(3, spct.getMaSize());
            ps.setObject(4, spct.getMaChatLieu());
            ps.setObject(5, spct.getSoLuong());
            ps.setObject(6, spct.getDonGia());
            ps.setObject(7, spct.getMoTa());
            ps.setObject(8, spct.getMaThuongHieu());
            ps.setObject(9, spct.getId());

            return ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }

    public void capNhatSoLuongTon(int idSpct, int soLuongMoi) {
        String sql = "UPDATE san_pham_chi_tiet SET so_luong = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, soLuongMoi);
            ps.setInt(2, idSpct);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật số lượng tồn kho sản phẩm!");
        }
    }

    public SanPhamChiTiet findById(int id) {
        String sql = """
        SELECT spct.*, sp.ma_san_pham
        FROM san_pham_chi_tiet spct
        JOIN san_pham sp ON sp.id = spct.idsp
        WHERE spct.id = ?
    """;
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                SanPhamChiTiet sp = new SanPhamChiTiet();
                sp.setId(rs.getInt("id"));
                sp.setIdSp(rs.getInt("idsp"));
                sp.setMaSp(rs.getString("ma_san_pham"));
                sp.setMaNhaCungCap(rs.getInt("ma_nha_cung_cap"));
                sp.setMaMau(rs.getInt("ma_mau"));
                sp.setMaSize(rs.getInt("ma_size"));
                sp.setMaChatLieu(rs.getInt("ma_chat_lieu"));
                sp.setSoLuong(rs.getInt("so_luong"));
                sp.setDonGia(rs.getFloat("don_gia"));
                sp.setMoTa(rs.getString("mo_ta"));
                sp.setNgayTao(rs.getDate("ngay_tao"));
                sp.setMaThuongHieu(rs.getInt("ma_thuong_hieu"));
                sp.setTrangThai(rs.getBoolean("trang_thai"));
                return sp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<SanPhamChiTietResponse> findByTenSanPham(String keyword) {
        List<SanPhamChiTietResponse> list = new ArrayList<>();
        String sql = """
        SELECT spct.id, sp.ma_san_pham, sp.ten AS ten_san_pham, 
               spct.so_luong, spct.don_gia,
               ms.ten AS ten_mau, size.ten AS ten_size, chat_lieu.ten AS ten_chat_lieu,
               spct.trang_thai
        FROM san_pham_chi_tiet spct
        JOIN san_pham sp ON spct.idsp = sp.id
        JOIN mau_sac ms ON spct.ma_mau = ms.id
        JOIN size ON spct.ma_size = size.id
        JOIN chat_lieu ON spct.ma_chat_lieu = chat_lieu.id
        WHERE sp.ten ILIKE ?
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPhamChiTietResponse sp = new SanPhamChiTietResponse();
                sp.setId(rs.getInt("id"));
                sp.setMaSp(rs.getString("ma_san_pham"));
                sp.setTenSanPham(rs.getString("ten_san_pham"));
                sp.setSoLuong(rs.getInt("so_luong"));
                sp.setDonGia(rs.getFloat("don_gia"));
                sp.setTenMau(rs.getString("ten_mau"));
                sp.setTenSize(rs.getString("ten_size"));
                sp.setTenChatLieu(rs.getString("ten_chat_lieu"));
                sp.setTrangThai(rs.getBoolean("trang_thai"));
                list.add(sp);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}

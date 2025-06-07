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

    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public ArrayList<SanPhamChiTietResponse> getAllSPCT() {
        String sql = """
               SELECT SPCT.ID, SP.TEN AS TEN_SAN_PHAM, NCC.TEN AS TEN_NHA_CUNG_CAP,
                      MS.TEN AS TEN_MAU, SIZE.TEN AS TEN_SIZE, CHAT_LIEU.TEN AS TEN_CHAT_LIEU,
                      SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
                      THUONG_HIEU.TEN AS TEN_THUONG_HIEU, SPCT.TRANG_THAI
               FROM SAN_PHAM_CHI_TIET SPCT
               JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
               JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
               JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
               JOIN SIZE SIZE ON SPCT.MA_SIZE = SIZE.ID
               JOIN CHAT_LIEU CHAT_LIEU ON SPCT.MA_CHAT_LIEU = CHAT_LIEU.ID
               JOIN THUONG_HIEU THUONG_HIEU ON SPCT.MA_THUONG_HIEU = THUONG_HIEU.ID
           """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
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
            e.printStackTrace();
        }
        return list;
    }

//    public boolean add(SanPhamChiTiet spct) {
//        String sql = """
//
//                      INSERT INTO SAN_PHAM_CHI_TIET (IDSP, MA_NHA_CUNG_CAP, MA_MAU, MA_DO_CUNG_DUA, MA_DO_CANG, SO_LUONG, DON_GIA, MO_TA, NGAY_TAO, MA_CD_VOT, MA_DIEM_CAN_BANG, MA_TRONG_LUONG, MA_DO_BAY, MA_DANG_MAT_VOT, TRANG_THAI)
//                             SELECT ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?
//                             FROM SAN_PHAM SP
//                             JOIN NHA_CUNG_CAP NCC ON ? = NCC.ID
//                             JOIN MAU_SAC MS ON ? = MS.ID
//                             JOIN DO_CUNG_DUA DCD ON ? = DCD.ID
//                             JOIN DO_CANG DC ON ? = DC.ID
//                             JOIN CHIEU_DAI_VOT CDV ON ? = CDV.ID
//                             JOIN DIEM_CAN_BANG DCB ON ? = DCB.ID
//                             JOIN TRONG_LUONG TL ON ? = TL.ID
//                             JOIN DO_BAY DB ON ? = DB.ID
//                             JOIN DANG_MAT_VOT DMV ON ? = DMV.ID
//                             WHERE SP.ID = ?
//            """;
//        int check = 0;
//        try (Connection con = DBConnect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
//
//            ps.setInt(1, spct.getIdSp());
//            ps.setInt(2, spct.getMaNhaCungCap());
//            ps.setInt(3, spct.getMaMau());
//            ps.setInt(4, spct.getMaDoCungDua());
//            ps.setInt(5, spct.getMaDoCang());
//            ps.setInt(6, spct.getSoLuong());
//            ps.setFloat(7, spct.getDonGia());
//            ps.setString(8, spct.getMoTa());
//            ps.setInt(10, spct.getMaCdVot());
//            ps.setInt(11, spct.getMaDiemCanBang());
//            ps.setInt(12, spct.getMaTrongLuong());
//            ps.setInt(13, spct.getMaDoBay());
//            ps.setInt(14, spct.getMaDangMatVot());
//            ps.setBoolean(15, spct.isTrangThai());
//
//            check = ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            JOptionPane.showMessageDialog(null, "Có lỗi xảy ra khi thêm sản phẩm chi tiết", "Lỗi", JOptionPane.ERROR_MESSAGE);
//        }
//        return check > 0;
//    }
    public boolean update(SanPhamChiTiet spct) {
        String sql = """
     
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [IDSP] = ?,
                [MA_NHA_CUNG_CAP] = ?,
                [MA_MAU] = ?,
                [MA_SIZE] = ?,
                [MA_CHAT_LIEU] = ?,
                [SO_LUONG] = ?,
                [DON_GIA] = ?,
                [MO_TA] = ?,
                [NGAY_TAO] = ?,
                [MA_THUONG_HIEU] = ?,
               
                [TRANG_THAI] = ?
            WHERE ID = ?
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
//           ps.setDate(9, new Date(spct.getNgayTao().getTime()));
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
        SELECT SPCT.ID, SP.MA_SAN_PHAM, SP.TEN AS TEN_SAN_PHAM,
               NCC.TEN AS TEN_NHA_CUNG_CAP, MS.TEN AS TEN_MAU,
               SIZE.TEN AS TEN_SIZE, CHAT_LIEU.TEN AS TEN_CHAT_LIEU,
               SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
               THUONG_HIEU.TEN AS TEN_THUONG_HIEU, SPCT.TRANG_THAI
        FROM SAN_PHAM_CHI_TIET SPCT
        JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
        JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
        JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
        JOIN SIZE SIZE ON SPCT.MA_SIZE = SIZE.ID
        JOIN CHAT_LIEU CHAT_LIEU ON SPCT.MA_CHAT_LIEU = CHAT_LIEU.ID
        JOIN THUONG_HIEU THUONG_HIEU ON SPCT.MA_THUONG_HIEU = THUONG_HIEU.ID
        WHERE SP.TEN LIKE ?
    """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, "%" + tenSanPham + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
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
 
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [SO_LUONG] = ?
            WHERE ID = ?

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
        SELECT SPCT.ID, SP.TEN AS TEN_SAN_PHAM, NCC.TEN AS TEN_NHA_CUNG_CAP,
               MS.TEN AS TEN_MAU, SIZE.TEN AS TEN_SIZE, CHAT_LIEU.TEN AS TEN_CHAT_LIEU,
               SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
               THUONG_HIEU.TEN AS TEN_THUONG_HIEU,
               SPCT.TRANG_THAI
        FROM SAN_PHAM_CHI_TIET SPCT
        JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
        JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
        JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
        JOIN SIZE SIZE ON SPCT.MA_SIZE = SIZE.ID
        JOIN CHAT_LIEU CHAT_LIEU ON SPCT.MA_CHAT_LIEU = CHAT_LIEU.ID
        JOIN THUONG_HIEU THUONG_HIEU ON SPCT.MA_THUONG_HIEU = THUONG_HIEU.ID
        WHERE SP.TRANG_THAI = 0
          AND (? IS NULL OR SPCT.MA_MAU = ?)
          AND (? IS NULL OR SPCT.MA_SIZE = ?)
          AND (? IS NULL OR SPCT.MA_THUONG_HIEU = ?)
          AND (? IS NULL OR SPCT.MA_CHAT_LIEU = ?)
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

////////////////////////////////////////////////////////////////////////////////////////////
    public ArrayList<SanPhamChiTietResponse> getProductDetailByIdProduct(Integer idSP) {
        String sql = """
        SELECT SPCT.ID, SP.TEN AS TEN_SAN_PHAM, NCC.TEN AS TEN_NHA_CUNG_CAP,
               MS.TEN AS TEN_MAU, SIZE.TEN AS TEN_SIZE, CHAT_LIEU.TEN AS TEN_CHAT_LIEU,
               SPCT.SO_LUONG, SPCT.DON_GIA, SPCT.MO_TA, SPCT.NGAY_TAO,
               THUONG_HIEU.TEN AS TEN_THUONG_HIEU,
               SPCT.TRANG_THAI
        FROM SAN_PHAM_CHI_TIET SPCT
        JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
        JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
        JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
        JOIN SIZE SIZE ON SPCT.MA_SIZE = SIZE.ID
        JOIN CHAT_LIEU CHAT_LIEU ON SPCT.MA_CHAT_LIEU = CHAT_LIEU.ID
        JOIN THUONG_HIEU THUONG_HIEU ON SPCT.MA_THUONG_HIEU = THUONG_HIEU.ID
        WHERE SPCT.IDSP = ?
    """;

        ArrayList<SanPhamChiTietResponse> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSP);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTietResponse spctResponse = new SanPhamChiTietResponse(
                        rs.getInt("ID"),
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

    public SanPhamChiTietResponse getProductDetailResponseById(Integer idSPCT) {
        String sql = """
        SELECT SPCT.ID,
               SP.MA_SAN_PHAM,
               SP.TEN AS TEN_SAN_PHAM,
               NCC.TEN AS TEN_NHA_CUNG_CAP,
               MS.TEN AS TEN_MAU,
               SIZE.TEN AS TEN_SIZE,
               CHAT_LIEU.TEN AS TEN_CHAT_LIEU,
               SPCT.SO_LUONG,
               SPCT.DON_GIA,
               SPCT.MO_TA,
               SPCT.NGAY_TAO,
               THUONG_HIEU.TEN AS THUONG_HIEU,
               SPCT.TRANG_THAI
        FROM SAN_PHAM_CHI_TIET SPCT
        JOIN SAN_PHAM SP ON SPCT.IDSP = SP.ID
        JOIN NHA_CUNG_CAP NCC ON SPCT.MA_NHA_CUNG_CAP = NCC.ID
        JOIN MAU_SAC MS ON SPCT.MA_MAU = MS.ID
        JOIN SIZE SIZE ON SPCT.MA_SIZE = SIZE.ID
        JOIN CHAT_LIEU CHAT_LIEU ON SPCT.MA_CHAT_LIEU = CHAT_LIEU.ID
        JOIN THUONG_HIEU THUONG_HIEU ON SPCT.MA_THUONG_HIEU = THUONG_HIEU.ID
        WHERE SPCT.ID = ?
    """;

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new SanPhamChiTietResponse(
                        rs.getInt("ID"),
                        rs.getString("MA_SAN_PHAM"),
                        rs.getString("TEN_NHA_CUNG_CAP"),
                        rs.getString("TEN_MAU"),
                        rs.getString("TEN_SIZE"),
                        rs.getString("TEN_CHAT_LIEU"),
                        rs.getInt("SO_LUONG"),
                        rs.getFloat("DON_GIA"),
                        rs.getString("MO_TA"),
                        rs.getDate("NGAY_TAO"), // hoặc .toString() tùy theo field khai báo
                        rs.getString("THUONG_HIEU"),
                        rs.getBoolean("TRANG_THAI")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    //udpate trạng thái
    public boolean updateStatus(Integer idSpct, Boolean trangThai) {
        String sql = """
            UPDATE [dbo].[SAN_PHAM_CHI_TIET]
            SET [TRANG_THAI] = ?
            WHERE ID = ?
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
                  SELECT [ID]
                           ,[IDSP]
                           ,[MA_NHA_CUNG_CAP]
                           ,[MA_MAU]
                           ,[MA_SIZE]
                           ,[MA_CHAT_LIEU]
                           ,[SO_LUONG]
                           ,[DON_GIA]
                           ,[MO_TA]
                           ,[NGAY_TAO]
                           ,[MA_THUONG_HIEU]
                           ,[TRANG_THAI]
                       FROM [dbo].[SAN_PHAM_CHI_TIET]
                     WHERE ID = ?
                    """;
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setObject(1, idSPCT);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet(
                        rs.getInt(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4),
                        rs.getInt(5),
                        rs.getInt(6),
                        rs.getInt(7),
                        rs.getFloat(8),
                        rs.getString(9),
                        rs.getDate(10),
                        rs.getInt(11),
                        rs.getBoolean(12));
                return spct;
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return null;
    }

    public int creatNewProductDetail(SanPhamChiTiet spct) {
        String sql = """
                  INSERT INTO [dbo].[SAN_PHAM_CHI_TIET]
                                ([IDSP]
                                                           ,[MA_NHA_CUNG_CAP]
                                                           ,[MA_MAU]
                                                           ,[MA_SIZE]
                                                           ,[MA_CHAT_LIEU]
                                                           ,[SO_LUONG]
                                                           ,[DON_GIA]
                                                           ,[MO_TA]
                                                           ,[NGAY_TAO]
                                                           ,[MA_THUONG_HIEU]
                                                           ,[TRANG_THAI])
                          VALUES
                                (?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                ,?
                                )
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
            ps.setObject(9, spct.getNgayTao());
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
                 UPDATE [dbo].[SAN_PHAM_CHI_TIET]
                                   SET 
                                      [MA_NHA_CUNG_CAP] = ?
                                      ,[MA_MAU] = ?
                                      ,[MA_SIZE] = ?
                                      ,[MA_CHAT_LIEU] = ?
                                      ,[SO_LUONG] = ?
                                      ,[DON_GIA] = ?
                                      ,[MO_TA] = ?
                                      ,[MA_THUONG_HIEU] = ?
                                 WHERE ID = ?
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

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import unicro.config.Connect;
import unicro.entity.Order;
import unicro.entity.OrderDetail;
import unicro.entity.OrderDetailResponse;
import unicro.entity.SanPhamChiTiet;

/**
 *
 * @author Admin
 */
public class OrderDetailService {

    private static final String URL = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private static final String USER = "postgres";
    private static final String PASSWORD = "password";

    public boolean insert(OrderDetail ct) {
        String sql = "INSERT INTO order_details (order_id, product_detail_id, price, number_of_product) "
                + "VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, ct.getOrder_id());
            ps.setInt(2, ct.getProduct_detail_id());
            ps.setBigDecimal(3, ct.getPrice());
            ps.setInt(4, ct.getNumber_of_product());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean delete(int id) {
        String sql = "DELETE FROM order_details WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<OrderDetailResponse> getByOrderId1(int orderId) {
        List<OrderDetailResponse> list = new ArrayList<>();
        String sql = """
        SELECT 
            od.id AS id_cthd,
            spct.id AS id_spct,
            sp.ma_san_pham AS ma_sp,
            sp.ten AS ten_sp,
            od.number_of_product,
            od.price,
            od.number_of_product * od.price AS thanh_tien
        FROM order_details od
        JOIN san_pham_chi_tiet spct ON od.product_detail_id = spct.id
        JOIN san_pham sp ON spct.idsp = sp.id
        WHERE od.order_id = ?
    """;
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                OrderDetailResponse dto = new OrderDetailResponse();
                dto.setIdSpct(rs.getInt("id_spct"));
                dto.setMaSp(rs.getString("ma_sp"));
                dto.setTenSp(rs.getString("ten_sp"));
                dto.setSoLuong(rs.getInt("number_of_product"));
                dto.setGia(rs.getBigDecimal("price"));
                dto.setThanhTien(rs.getBigDecimal("thanh_tien"));
                list.add(dto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<OrderDetail> getByOrderId(int orderId) {
        List<OrderDetail> list = new ArrayList<>();
        String sql = "SELECT od.product_detail_id,"
                + " sp.ten AS ten_san_pham, "
                + "od.number_of_product, od.price, "
                + "od.number_of_product * od.price AS thanh_tien "
                + "FROM order_details od "
                + "JOIN san_pham_chi_tiet spct ON od.product_detail_id = spct.id "
                + "JOIN san_pham sp ON spct.idsp = sp.id "
                + "WHERE od.order_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderDetail ct = new OrderDetail();
                    ct.setProduct_detail_id(rs.getInt("product_detail_id"));
                    ct.setTenSp(rs.getString("ten_san_pham"));
                    ct.setNumber_of_product(rs.getInt("number_of_product"));
                    ct.setPrice(rs.getBigDecimal("price"));
                    ct.setThanhTien(rs.getBigDecimal("thanh_tien"));
                    list.add(ct);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public String getMaSPByIdSpct(int idSpct) {
        String sql = """
       SELECT sp.ma_san_pham FROM san_pham sp "
                       + "JOIN san_pham_chi_tiet spct ON sp.id = spct.idsp "
                       + "WHERE spct.id = ?
    """;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idSpct);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("ma_san_pham");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public OrderDetail findByOrderIdAndProductDetailId(int orderId, int productDetailId) {
        String sql = "SELECT * FROM order_details WHERE order_id = ? AND product_detail_id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productDetailId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new OrderDetail(
                        rs.getInt("order_id"),
                        rs.getInt("product_detail_id"),
                        rs.getBigDecimal("price"),
                        rs.getInt("number_of_product")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean update(OrderDetail detail) {
        String sql = "UPDATE order_details SET number_of_product = ? WHERE order_id = ? AND product_detail_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, detail.getNumber_of_product());
            ps.setInt(2, detail.getOrder_id());
            ps.setInt(3, detail.getProduct_detail_id());
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteByOrderIdAndProductDetailId(int orderId, int productDetailId) {
        String sql = "DELETE FROM order_details WHERE order_id = ? AND product_detail_id = ?";
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ps.setInt(2, productDetailId);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

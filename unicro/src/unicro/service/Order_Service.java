/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import unicro.entity.Order;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class Order_Service {

    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Order order = new Order();

                order.setId(rs.getInt("id"));
                order.setUser_id(rs.getInt("user_id"));
                order.setVoucher_id(rs.getInt("voucher_id"));
                order.setOrder_date(rs.getDate("order_date"));
                order.setNote(rs.getString("note"));
                order.setTotal(rs.getBigDecimal("total"));
                order.setPayment_method(rs.getString("payment_method"));
                order.setStatus(rs.getString("status"));

                orders.add(order);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return orders;
    }

    public List<Order> getDanhSachDonHang(Date fromDate, Date toDate) {
        List<Order> list = new ArrayList<>();
        String sql = "SELECT id, order_date, total, status FROM orders WHERE order_date BETWEEN ? AND ?  AND status like 'Completed'";

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(fromDate.getTime()));
            ps.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order order = new Order();
                order.setId(rs.getInt("id"));
                order.setOrder_date(rs.getDate("order_date"));
                order.setTotal(rs.getBigDecimal("total"));
                order.setStatus(rs.getString("status"));
                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public BigDecimal getTongDoanhThu(Date fromDate, Date toDate) {
        String sql = "SELECT SUM(total) AS total_revenue FROM orders WHERE order_date BETWEEN ? AND ? AND status like 'Completed'";
        BigDecimal total = BigDecimal.ZERO;

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setDate(1, new java.sql.Date(fromDate.getTime()));
            ps.setDate(2, new java.sql.Date(toDate.getTime()));

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal("total_revenue");
                if (total == null) {
                    total = BigDecimal.ZERO;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }

    public BigDecimal getTongDoanhThuTatCa() {
        String sql = "SELECT SUM(total) AS total_revenue FROM orders";
        BigDecimal total = BigDecimal.ZERO;

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                total = rs.getBigDecimal("total_revenue");
                if (total == null) {
                    total = BigDecimal.ZERO;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return total;
    }
}

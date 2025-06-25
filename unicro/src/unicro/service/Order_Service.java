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
import javax.swing.JOptionPane;
import unicro.entity.GioHang;
import unicro.entity.OrderDetail;
import unicro.entity.SanPhamChiTiet;

/**
 *
 * @author Admin
 */
public class Order_Service {

    private final String url = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders "
                + "order by id DESC";

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
        String sql = "SELECT SUM(total) AS total_revenue FROM orders WHERE order_date BETWEEN ? AND ? AND status like 'Đã thanh toán'";
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

    public int taoHoaDon(int userId, Integer voucherId, BigDecimal tongTien, String status, String paymentMethod) {
        String sql = "INSERT INTO orders (user_id, voucher_id, total, status, payment_method) VALUES (?, ?, ?, ?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            if (voucherId == null) {
                ps.setNull(2, Types.INTEGER);
            } else {
                ps.setInt(2, voucherId);
            }
            ps.setBigDecimal(3, tongTien);
            ps.setString(4, status);
            ps.setString(5, paymentMethod);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public boolean themChiTietHoaDon(int orderId, int productDetailId, BigDecimal price, int quantity) {
        String sql = "INSERT INTO order_details (order_id, product_detail_id, price, number_of_product) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, orderId);
            ps.setInt(2, productDetailId);
            ps.setBigDecimal(3, price);
            ps.setInt(4, quantity);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public int createOrder(int userId, Integer voucherId, String note,
            String paymentMethod, String status, List<GioHang> gh) throws SQLException {

        double total = 0;
        for (GioHang d : gh) {
            total += d.getGiaBan() * d.getSoLuong();
        }

        String insertOrderSql = "INSERT INTO orders (user_id, voucher_id, note, total, payment_method, status) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
        String insertDetailSql = "INSERT INTO order_details (order_id, product_detail_id, price, number_of_product) VALUES (?, ?, ?, ?)";

        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/unicro_qlbh", "postgres", "password");

        conn.setAutoCommit(false);
        try (
                PreparedStatement orderStmt = conn.prepareStatement(insertOrderSql); PreparedStatement detailStmt = conn.prepareStatement(insertDetailSql)) {
            orderStmt.setInt(1, userId);
            if (voucherId != null) {
                orderStmt.setInt(2, voucherId);
            } else {
                orderStmt.setNull(2, Types.INTEGER);
            }
            orderStmt.setString(3, note);
            orderStmt.setDouble(4, total);
            orderStmt.setString(5, paymentMethod);
            orderStmt.setString(6, status);

            ResultSet rs = orderStmt.executeQuery();
            int orderId = -1;
            if (rs.next()) {
                orderId = rs.getInt("id");
            }

            String getProductDetailIdSql = "SELECT ctsp.id "
                    + "FROM san_pham_chi_tiet ctsp "
                    + "JOIN san_pham sp ON ctsp.idsp = sp.id "
                    + "WHERE sp.ma_san_pham = ?";

            PreparedStatement getIdStmt = conn.prepareStatement(getProductDetailIdSql);

            for (GioHang d : gh) {
                getIdStmt.setString(1, d.getMaSPCT());
                ResultSet rsId = getIdStmt.executeQuery();
                int productDetailId = -1;

                if (rsId.next()) {
                    productDetailId = rsId.getInt("id");
                } else {
                    throw new SQLException("Không tìm thấy chi tiết sản phẩm với mã: " + d.getMaSPCT());
                }

                detailStmt.setInt(1, orderId);
                detailStmt.setInt(2, productDetailId);
                detailStmt.setDouble(3, d.getGiaBan());
                detailStmt.setInt(4, d.getSoLuong());
                detailStmt.addBatch();
            }
            conn.commit();
            return orderId;
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

//    public int getIdChiTietSanPhamTuMa(String maSP) throws SQLException {
//        String query = "SELECT ct.id "
//                + "from san_pham_chi_tiet ct "
//                + "JOIN san_pham sp ON ct.idsp = sp.id "
//                + "WHERE sp.ma_san_pham = ?";
//        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(query)) {
//
//            stmt.setString(1, maSP);
//            try (ResultSet rs = stmt.executeQuery()) {
//                if (rs.next()) {
//                    return rs.getInt("id");
//                }
//            }
//        }
//        return -1;
//    }
    public boolean capNhatHoaDon(int orderId, Integer voucherId, BigDecimal tongTien,
            String paymentMethod, String note) throws SQLException {

        String sql = "UPDATE orders SET voucher_id = ?, total = ?, payment_method = ?, "
                + "note = ?, status = 'Đã thanh toán', order_date = CURRENT_TIMESTAMP "
                + "WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            if (voucherId != null) {
                ps.setInt(1, voucherId);
            } else {
                ps.setNull(1, Types.INTEGER);
            }

            ps.setBigDecimal(2, tongTien);
            ps.setString(3, paymentMethod);
            ps.setString(4, note);
            ps.setInt(5, orderId);

            return ps.executeUpdate() > 0;
        }
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

    public String getUserNameByUserId(int userId) {
        String sql = "SELECT fullname FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getTenNhanVienByOrderId(int orderId) {
        String sql = "SELECT u.fullname FROM orders o JOIN users u ON o.user_id = u.id WHERE o.id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, orderId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("fullname");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public BigDecimal getTienGiam(int voucherId, BigDecimal tongTien) {
        String sql = "SELECT discount_type, discount_value FROM vouchers WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, voucherId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String type = rs.getString("discount_type");
                BigDecimal value = rs.getBigDecimal("discount_value");
                if ("percent".equalsIgnoreCase(type)) {
                    return tongTien.multiply(value).divide(BigDecimal.valueOf(100));
                } else if ("amount".equalsIgnoreCase(type)) {
                    return value;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return BigDecimal.ZERO;
    }

    public List<Order> timKiemTheoID(int idHoaDon) {
        List<Order> list = new ArrayList<>();
        String sql = """
        SELECT o.id, u.fullname AS ten_nhan_vien, o.voucher_id, o.order_date, o.total, o.status
        FROM orders o
        JOIN users u ON o.user_id = u.id
        WHERE o.id = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idHoaDon);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order hd = new Order();
                hd.setId(rs.getInt("id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                hd.setOrder_date(rs.getDate("order_date"));
                hd.setTotal(rs.getBigDecimal("total"));
                hd.setStatus(rs.getString("status"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Order> LocTrangThai(String status) {
        List<Order> list = new ArrayList<>();
        String sql = """
        SELECT o.id, u.fullname AS ten_nhan_vien, o.voucher_id, o.order_date, o.total, o.status
        FROM orders o
        JOIN users u ON o.user_id = u.id
        WHERE o.status = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order hd = new Order();
                hd.setId(rs.getInt("id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                hd.setOrder_date(rs.getDate("order_date"));
                hd.setTotal(rs.getBigDecimal("total"));
                hd.setStatus(rs.getString("status"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<Order> locTheoNgay(Date fromDate, Date toDate) {
        List<Order> list = new ArrayList<>();
        String sql = """
        SELECT o.id, u.fullname AS ten_nhan_vien, o.voucher_id, o.order_date, o.total, o.status
        FROM orders o
        JOIN users u ON o.user_id = u.id
        WHERE DATE(o.order_date) BETWEEN ? AND ?
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDate(1, new java.sql.Date(fromDate.getTime()));
            ps.setDate(2, new java.sql.Date(toDate.getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Order hd = new Order();
                hd.setId(rs.getInt("id"));
                hd.setVoucher_id(rs.getInt("voucher_id"));
                hd.setOrder_date(rs.getDate("order_date"));
                hd.setTotal(rs.getBigDecimal("total"));
                hd.setStatus(rs.getString("status"));
                list.add(hd);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean insert(Order order) {
        String sql = "INSERT INTO orders (user_id, order_date, status, total) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, order.getUser_id());

            ps.setDate(2, new java.sql.Date(order.getOrder_date().getTime()));
            ps.setString(3, order.getStatus());
            ps.setBigDecimal(4, order.getTotal());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Order findPendingByUser(int userId) {
        String sql = "SELECT * FROM orders WHERE user_id = ? AND status = 'Pending' ORDER BY order_date DESC LIMIT 1";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUser_id(rs.getInt("user_id"));
                ps.setDate(2, new java.sql.Date(o.getOrder_date().getTime()));
                o.setStatus(rs.getString("status"));
                o.setTotal(rs.getBigDecimal("total_price"));
                return o;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Order createPendingOrder(int userId) {
        String sql = "INSERT INTO orders (user_id, order_date, status, total) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            Date now = new Date();
            ps.setInt(1, userId);
            ps.setDate(2, new java.sql.Date(now.getTime()));
            ps.setString(3, "Pending");
            ps.setBigDecimal(4, BigDecimal.ZERO);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("id");
                Order o = new Order();
                o.setId(orderId);
                o.setUser_id(userId);
                o.setOrder_date(now);
                o.setStatus("Pending");
                o.setTotal(BigDecimal.ZERO);
                return o;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

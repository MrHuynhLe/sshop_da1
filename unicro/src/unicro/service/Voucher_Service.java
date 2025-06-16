/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import unicro.entity.Voucher;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

/**
 *
 * @author Admin
 */
public class Voucher_Service {

    private final String url = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM vouchers";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Voucher v = new Voucher();
                v.setId(rs.getInt("id"));
                v.setCode(rs.getString("code"));
                v.setDiscount_type(rs.getString("discount_type"));
                v.setDiscount_value(rs.getBigDecimal("discount_value"));
                Date startDate = rs.getDate("start_date");
                if (startDate != null) {
                    v.setStart_date(startDate.toLocalDate());
                }

                Date endDate = rs.getDate("end_date");
                if (endDate != null) {
                    v.setEnd_date(endDate.toLocalDate());
                }
                v.setMax_purchase_amount(rs.getBigDecimal("max_purchase_amount"));
                v.setMin_purchase_amount(rs.getBigDecimal("min_purchase_amount"));
                Timestamp createdAt = rs.getTimestamp("created_at");
                if (createdAt != null) {
                    v.setCreated_at(createdAt.toLocalDateTime());
                }
                v.setActive(rs.getString("active").equals("1"));

                vouchers.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vouchers;
    }

    public boolean addVoucher(Voucher v) {

        if (v.getCode() == null || v.getCode().trim().isEmpty()) {
            System.err.println("Mã voucher không được để trống.");
            return false;
        }

        if (existsByCode(v.getCode())) {
            System.err.println("Mã voucher đã tồn tại trong hệ thống.");
            return false;
        }

        String sql = "INSERT INTO vouchers (code, discount_type, discount_value, start_date, end_date, created_at, active) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, v.getCode());
            ps.setString(2, v.getDiscount_type());
            ps.setBigDecimal(3, v.getDiscount_value());
            ps.setDate(4, java.sql.Date.valueOf(v.getStart_date()));
            ps.setDate(5, java.sql.Date.valueOf(v.getEnd_date()));
            ps.setTimestamp(6, Timestamp.valueOf(v.getCreated_at()));
            ps.setBoolean(7, v.getActive());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean existsByCode(String code) {
        String sql = "SELECT COUNT(*) FROM vouchers WHERE code = ?";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateVoucher(Voucher v) {
        String sql = "UPDATE vouchers SET discount_type = ?, discount_value = ?, start_date = ?, end_date = ?, created_at = ?, active = ? WHERE code = ?";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, v.getDiscount_type());
            ps.setBigDecimal(2, v.getDiscount_value());
            ps.setTimestamp(3, Timestamp.valueOf(v.getStart_date().atStartOfDay()));
            ps.setTimestamp(4, Timestamp.valueOf(v.getEnd_date().atStartOfDay()));
            ps.setTimestamp(5, Timestamp.valueOf(v.getCreated_at()));
            ps.setString(6, v.getActive() ? "1" : "0");
            ps.setString(7, v.getCode());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteVoucher(String code) {
        String sql = "DELETE FROM vouchers WHERE code = ?";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, code);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public Voucher getVoucherByCode(String code) {
        try (Connection conn = DriverManager.getConnection(url, username, password)) {
            String sql = "SELECT * FROM vouchers WHERE code = ? AND active = true";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, code);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Voucher(
                        rs.getInt("id"),
                        rs.getString("code"),
                        rs.getString("discount_type"),
                        rs.getBigDecimal("discount_value"),
                        rs.getBigDecimal("min_purchase_amount"),
                        rs.getBigDecimal("max_purchase_amount"),
                        rs.getBoolean("active")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Voucher> getAvailableVouchers(BigDecimal tongTien) {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM vouchers WHERE active = true AND CURRENT_DATE BETWEEN start_date AND end_date AND min_purchase_amount <= ?";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setBigDecimal(1, tongTien);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Voucher v = new Voucher();
                v.setId(rs.getInt("id"));
                v.setCode(rs.getString("code"));
                v.setDiscount_type(rs.getString("discount_type"));
                v.setDiscount_value(rs.getBigDecimal("discount_value"));
                v.setMin_purchase_amount(rs.getBigDecimal("min_purchase_amount"));
                v.setMax_purchase_amount(rs.getBigDecimal("max_purchase_amount"));
                vouchers.add(v);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vouchers;
    }

    public BigDecimal applyVoucher(Voucher voucher, BigDecimal tongTien) {
        if (voucher == null) {
            return tongTien;
        }

        BigDecimal giam = BigDecimal.ZERO;
        if (voucher.getDiscount_type().equalsIgnoreCase("percent")) {
            giam = tongTien.multiply(voucher.getDiscount_value()).divide(new BigDecimal(100));
        } else if (voucher.getDiscount_type().equalsIgnoreCase("amount")) {
            giam = voucher.getDiscount_value();
        }

        if (voucher.getMax_purchase_amount() != null && giam.compareTo(voucher.getMax_purchase_amount()) > 0) {
            giam = voucher.getMax_purchase_amount();
        }

        return tongTien.subtract(giam);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;


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
    private final String url = "jdbc:postgresql://localhost:5432/your_database";
    private final String username = "postgres";
    private final String password = "password";

    public List<Voucher> getAllVouchers() {
        List<Voucher> vouchers = new ArrayList<>();
        String sql = "SELECT * FROM vouchers";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Voucher v = new Voucher();
                v.setId(rs.getInt("id"));
                v.setCode(rs.getString("code"));
                v.setDiscount_type(rs.getString("discount_type"));
                v.setDiscount_value(rs.getBigDecimal("discount_value"));
                v.setStart_at(rs.getTimestamp("start_at").toLocalDateTime());
                v.setEnd_at(rs.getTimestamp("end_at").toLocalDateTime());
                v.setMax_purchase_amount(rs.getBigDecimal("max_purchase_amount"));
                v.setMin_purchase_amount(rs.getBigDecimal("min_purchase_amount"));
                v.setCreated_at(rs.getTimestamp("created_at").toLocalDateTime());
                v.setActive(rs.getBoolean("active"));

                vouchers.add(v);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return vouchers;
    }
}

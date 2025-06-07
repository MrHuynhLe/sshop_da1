/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import unicro.entity.ThuongHieu;

/**
 *
 * @author Admin
 */
public class ThuongHieuRepo {

    private static final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private static final String username = "postgres";
    private static final String password = "password";

    public ArrayList<ThuongHieu> getAll() {
        String sql = "SELECT ID, TEN,MA_THUONG_HIEU FROM THUONG_HIEU";
        ArrayList<ThuongHieu> list = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("ID"));
                th.setTen(rs.getString("TEN"));
                th.setMaThuongHieu(rs.getString("MA_THUONG_HIEU"));

                list.add(th);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }

    public void addDoBay(String tenDoBay) throws SQLException {
        String sql = "INSERT INTO DO_BAY (TEN) VALUES (?)";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, tenDoBay);
            ps.executeUpdate();
        }
    }
}

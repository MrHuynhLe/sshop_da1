/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.*;
import java.util.ArrayList;
import unicro.entity.MauSac;

/**
 *
 * @author Admin
 */
public class MauSacRepo {

    private static final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private static final String username = "postgres";
    private static final String password = "password";

    public ArrayList<MauSac> getAll() {
        String sql = "SELECT ID, TEN ,MA_MAU FROM MAU_SAC";
        ArrayList<MauSac> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                MauSac mauSac = new MauSac();
                mauSac.setId(rs.getInt("ID"));
                mauSac.setTenMau(rs.getString("TEN"));
                mauSac.setMaMauSac(rs.getString("MA_MAU"));

                list.add(mauSac);
            }
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }
        return list;
    }

    public void addMauSac(String tenMauSac, String maMau) throws SQLException {
        String sql = "INSERT INTO MAU_SAC (TEN, MA_MAU) VALUES (?, ?)";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, tenMauSac);
            ps.setString(2, maMau); 
            ps.executeUpdate();
        }
    }
}

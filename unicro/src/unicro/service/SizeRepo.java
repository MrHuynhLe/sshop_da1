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
import unicro.entity.Size;

/**
 *
 * @author Admin
 */
public class SizeRepo {

    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String username = "postgres";
    private final String password = "password";

    public ArrayList<Size> getAll() {

        String sql = "SELECT ID,MA_SIZE, TEN FROM SIZE";
        ArrayList<Size> list = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Size dmv = new Size();
                dmv.setId(rs.getInt("ID"));
                dmv.setTen(rs.getString("TEN"));
                dmv.setMaSize(rs.getString("MA_SIZE"));

                list.add(dmv);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }

    public void addThuocTinh(String tenBang, String tenCot, String giaTri) throws SQLException {
        String sql = "INSERT INTO " + tenBang + " (" + tenCot + ") VALUES (?)";
        try (Connection con = DriverManager.getConnection(url, username, password); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, giaTri);
            ps.executeUpdate();
        }
    }
}

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
import unicro.entity.ChatLieu;
import java.sql.*;
import unicro.config.Connect;

/**
 *
 * @author Admin
 */
public class ChatLieuRepo {

    public ArrayList<ChatLieu> getAll() {
        String sql = "SELECT ID, TEN, MA_CHAT_LIEU FROM CHAT_LIEU";
        ArrayList<ChatLieu> list = new ArrayList<>();

        try (Connection con = Connect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ChatLieu dcbv = new ChatLieu();
                dcbv.setId(rs.getInt("ID"));
                dcbv.setTen(rs.getString("TEN"));
                dcbv.setMaChatLieu(rs.getString("MA_CHAT_LIEU"));

                list.add(dcbv);
            }

        } catch (SQLException e) {
            e.printStackTrace(System.out);
        }

        return list;
    }

    public void addThuocTinh(String tenBang, String tenCot, String giaTri) throws SQLException {
        String sql = "INSERT INTO " + tenBang + " (" + tenCot + ") VALUES (?)";
        try (Connection con = Connect.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, giaTri);
            ps.executeUpdate();
        }
    }
}

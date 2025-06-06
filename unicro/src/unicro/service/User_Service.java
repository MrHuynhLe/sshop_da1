/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.postgresql.Driver;
import unicro.User_Model;
import java.sql.*;
import unicro.config.Connect;

/**
 *
 * @author Admin
 */
public class User_Service {
    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String username = "postgres";
    private final String password = "password";
    public static String login(String username, String password) {
        String sql = "SELECT u.id, u.fullname, r.name AS role_name " +
                     "FROM users u " +
                     "JOIN user_roles ur ON u.id = ur.user_id " +
                     "JOIN roles r ON ur.role_id = r.id " +
                     "WHERE u.username = ? AND u.password = ? AND r.active = TRUE";

        try (
            Connection conn = Connect.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
        ) {
            stmt.setString(1, username);
            stmt.setString(2, password); // Lưu ý: nên mã hóa mật khẩu trước khi so sánh

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String fullname = rs.getString("fullname");
                String role = rs.getString("role_name");
                return "Đăng nhập thành công! Xin chào " + fullname + " (" + role + ")";
            } else {
                return "Sai tên đăng nhập hoặc mật khẩu!";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Lỗi kết nối cơ sở dữ liệu!";
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import unicro.config.Connect;
import unicro.entity.LoginResult;
/**
 *
 * @author hn100
 */
public class LoginSevice {

    public static LoginResult login(String username, String password) {
        String sql = "SELECT u.id, u.fullname, r.name AS role_name "
                + "FROM users u "
                + "JOIN user_roles ur ON u.id = ur.user_id "
                + "JOIN roles r ON ur.role_id = r.id "
                + "WHERE u.username = ? AND u.password = ? AND r.active = TRUE";

        try (
                Connection conn = Connect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int userId = rs.getInt("id");
                String fullname = rs.getString("fullname");
                String role = rs.getString("role_name");
                return new LoginResult(true, "Đăng nhập thành công!", userId, fullname, role);
            } else {
                return new LoginResult(false, "Sai tên đăng nhập hoặc mật khẩu!", -1, null, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new LoginResult(false, "Lỗi kết nối cơ sở dữ liệu!", -1, null, null);
        }
    }
}

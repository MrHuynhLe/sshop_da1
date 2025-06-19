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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import unicro.config.Connect;
import unicro.entity.LoginResult;
import unicro.entity.Session;
import unicro.entity.User;

/**
 *
 * @author Admin
 */
public class User_Service {

    private final String url = "jdbc:postgresql://localhost:5432/unicro_qlbh";
    private final String username = "postgres";
    private final String password = "password";

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

    public String getTenNhanVienById(String userId) {
        String sql = "SELECT fullname FROM users WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, Integer.parseInt(userId));
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getString("fullname");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<User> getAllUsersWithRoles() {
        Map<Integer, User> userMap = new LinkedHashMap<>();

        String sql = """
        SELECT 
            u.id, u.username, u.fullname, u.address, u.phone_number,
            u.date_of_birth, u.password, u.created_at, u.update_at,
            r.name AS role_name
        FROM users u
        JOIN user_roles ur ON u.id = ur.user_id
        JOIN roles r ON ur.role_id = r.id
        ORDER BY u.id
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int userId = rs.getInt("id");
                User user = userMap.get(userId);

                if (user == null) {
                    user = new User();
                    user.setId(userId);
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setAddress(rs.getString("address"));
                    user.setPhone_number(rs.getString("phone_number"));
                    user.setDate_of_birth(rs.getDate("date_of_birth"));
                    user.setPassword(rs.getString("password"));
                    user.setCreated_at(rs.getDate("created_at"));
                    user.setUpdate_at(rs.getDate("update_at"));
                    userMap.put(userId, user);
                }

                String roleName = rs.getString("role_name");
                user.getRoleNames().add(roleName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(userMap.values());
    }

    public User getUserById(int id) {
        User user = null;

        String sql = """
    SELECT u.*, r.name AS role_name
    FROM users u
    JOIN user_roles ur ON u.id = ur.user_id
    JOIN roles r ON ur.role_id = r.id
    WHERE u.id = ?
""";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (user == null) {
                    user = new User();
                    user.setId(rs.getInt("id"));
                    user.setUsername(rs.getString("username"));
                    user.setFullname(rs.getString("fullname"));
                    user.setAddress(rs.getString("address"));
                    user.setPhone_number(rs.getString("phone_number"));
                    user.setDate_of_birth(rs.getDate("date_of_birth"));
                    user.setPassword(rs.getString("password"));
                    user.setCreated_at(rs.getDate("created_at"));
                    user.setUpdate_at(rs.getDate("update_at"));
                }
                String roleName = rs.getString("role_name");
                if (roleName != null && !user.getRoleNames().contains(roleName)) {
                    user.getRoleNames().add(roleName);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public String getPasswordByUserId(int userId) {
        String password = null;
        String sql = "SELECT password FROM users WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(url, username, this.password); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                password = rs.getString("password");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return password;
    }

    public boolean addUser(User user) {
        String insertUserSql = """
        INSERT INTO users (username, fullname, address, phone_number, date_of_birth, password, created_at, update_at)
        VALUES (?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP)
        RETURNING id
    """;

        String insertUserRoleSql = "INSERT INTO user_role (user_id, role_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement userStmt = conn.prepareStatement(insertUserSql);) {
            userStmt.setString(1, user.getUsername());
            userStmt.setString(2, user.getFullname());
            userStmt.setString(3, user.getAddress());
            userStmt.setString(4, user.getPhone_number());
            userStmt.setDate(5, user.getDate_of_birth());
            userStmt.setString(6, user.getPassword());

            ResultSet rs = userStmt.executeQuery();
            int userId = -1;
            if (rs.next()) {
                userId = rs.getInt("id");
            } else {
                return false;
            }

            for (String roleName : user.getRoleNames()) {
                int roleId = getRoleIdByName(roleName);
                if (roleId != -1) {
                    try (PreparedStatement roleStmt = conn.prepareStatement(insertUserRoleSql)) {
                        roleStmt.setInt(1, userId);
                        roleStmt.setInt(2, roleId);
                        roleStmt.executeUpdate();
                    }
                }
            }

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private int getRoleIdByName(String roleName) {
        String sql = "SELECT id FROM roles WHERE name = ?";
        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, roleName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<String> getRoleNamesByUserId(int userId) {
        List<String> roleNames = new ArrayList<>();
        String sql = """
        SELECT r.name
        FROM user_roles ur
        JOIN roles r ON ur.role_id = r.id
        WHERE ur.user_id = ?
    """;

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                roleNames.add(rs.getString("name"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roleNames;
    }

    public boolean updateUser(User user) {
        String updateUserSql = """
        UPDATE users SET
            username = ?, fullname = ?, address = ?, phone_number = ?,
            date_of_birth = ?, password = ?, update_at = CURRENT_TIMESTAMP
        WHERE id = ?
    """;

        String deleteRoleSql = "DELETE FROM user_roles WHERE user_id = ?";
        String insertRoleSql = "INSERT INTO user_roles(user_id, role_id) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(url, username, password); PreparedStatement updateStmt = conn.prepareStatement(updateUserSql); PreparedStatement deleteStmt = conn.prepareStatement(deleteRoleSql)) {

            conn.setAutoCommit(false);
            updateStmt.setString(1, user.getUsername());
            updateStmt.setString(2, user.getFullname());
            updateStmt.setString(3, user.getAddress());
            updateStmt.setString(4, user.getPhone_number());
            updateStmt.setDate(5, user.getDate_of_birth());
            updateStmt.setString(6, user.getPassword());
            updateStmt.setInt(7, user.getId());
            updateStmt.executeUpdate();
            if ("admin".equalsIgnoreCase(Session.currentRole) && user.getRoleNames() != null) {
                deleteStmt.setInt(1, user.getId());
                deleteStmt.executeUpdate();
                for (String roleName : user.getRoleNames()) {
                    int roleId = getRoleIdByName(roleName);
                    if (roleId != -1) {
                        try (PreparedStatement insertStmt = conn.prepareStatement(insertRoleSql)) {
                            insertStmt.setInt(1, user.getId());
                            insertStmt.setInt(2, roleId);
                            insertStmt.executeUpdate();
                        }
                    }
                }
            }

            conn.commit();
            return true;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}

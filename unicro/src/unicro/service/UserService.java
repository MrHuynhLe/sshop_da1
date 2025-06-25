package unicro.service;

import unicro.config.Connect;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JOptionPane;
import unicro.entity.User;
import unicro.model.UserModel;

/**
 *
 * @author Khang
 */
public class UserService {

    public ArrayList<UserModel> getAll() {
        ArrayList<UserModel> list = new ArrayList<>();

        String sql = "select r.id as role_id, r.name as role_name, u.username, u.fullname, u.password, u.id, u.phone_number, u.date_of_birth, u.created_at, u.update_at, u.address\n"
                + "from user_roles ur \n"
                + "join roles r on ur.role_id = r.id \n"
                + "join users u on ur.user_id  = u.id\n"
                + "WHERE r.active = true";

        Connect dbConnection = new Connect();

        try (Connection cn = Connect.getConnection(); PreparedStatement pstm = cn.prepareStatement(sql); ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                UserModel userModel = new UserModel();

                userModel.setId(rs.getInt("id"));
                userModel.setRole_id(rs.getInt("role_id"));
                userModel.setRole_name(rs.getString("role_name"));
                userModel.setUsername(rs.getString("username"));
                userModel.setFullname(rs.getString("fullname"));
                userModel.setPhone_number(rs.getString("phone_number"));
                userModel.setDate_of_birth(rs.getDate("date_of_birth"));
                userModel.setAddress(rs.getString("address"));
                userModel.setCreated_at(rs.getDate("created_at"));
                userModel.setUpdate_at(rs.getDate("update_at"));
                userModel.setPassword(rs.getString("password"));
                // Thêm đối tượng vào danh sách
                list.add(userModel);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public Integer ADD(UserModel nd) {
        Integer row = null;

        // Câu lệnh INSERT vào bảng users
        String insertUserSql = "INSERT INTO users "
                + "(username, fullname, address, phone_number, date_of_birth, password, created_at, update_at) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        // Câu lệnh INSERT vào bảng user_roles
        String insertUserRoleSql = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";

        Connect dbConnection = new Connect();

        try (
                Connection cn = dbConnection.getConnection(); PreparedStatement pstmUser = cn.prepareStatement(insertUserSql, Statement.RETURN_GENERATED_KEYS);) {
            // Gán giá trị cho các tham số trong bảng users
            pstmUser.setString(1, nd.getUsername());
            pstmUser.setString(2, nd.getFullname());
            pstmUser.setString(3, nd.getAddress());
            pstmUser.setString(4, nd.getPhone_number());
            pstmUser.setDate(5, nd.getDate_of_birth());
            pstmUser.setString(6, nd.getPassword());
            pstmUser.setDate(7, new java.sql.Date(System.currentTimeMillis()));
            pstmUser.setDate(8, new java.sql.Date(System.currentTimeMillis()));

            // Thực thi thêm user
            row = pstmUser.executeUpdate();

            // Lấy ID user vừa thêm
            ResultSet rs = pstmUser.getGeneratedKeys();
            if (rs.next()) {
                int userId = rs.getInt(1); // ID vừa được sinh ra

                // Thêm user_role
                try (PreparedStatement pstmUserRole = cn.prepareStatement(insertUserRoleSql)) {
                    pstmUserRole.setInt(1, userId);
                    pstmUserRole.setInt(2, nd.getRole_id()); // Giả sử model có roleId
                    pstmUserRole.executeUpdate();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return row;
    }
    

    public Integer update(UserModel kh) {
    Integer rowsUpdated = 0;

    String currentPasswordSql = "SELECT password FROM users WHERE id = ?";
    String updateUserSqlWithPassword = """
        UPDATE users 
        SET username=?, fullname=?, address=?, phone_number=?, date_of_birth=?, update_at=now(), password=? 
        WHERE id=?
    """;
    String updateUserSqlWithoutPassword = """
        UPDATE users 
        SET username=?, fullname=?, address=?, phone_number=?, date_of_birth=?, update_at=now() 
        WHERE id=?
    """;
    String deleteRoleSql = "DELETE FROM user_roles WHERE user_id = ?";
    String insertUserRoleSql = "INSERT INTO user_roles (user_id, role_id) VALUES (?, ?)";

    Connect dbConnection = new Connect();

    try (Connection cn = dbConnection.getConnection()) {
        cn.setAutoCommit(false);

        // 1. Lấy mật khẩu hiện tại
        String currentPassword = null;
        try (PreparedStatement pstmCurrent = cn.prepareStatement(currentPasswordSql)) {
            pstmCurrent.setInt(1, kh.getId());
            ResultSet rs = pstmCurrent.executeQuery();
            if (rs.next()) {
                currentPassword = rs.getString("password");
            }
        }

        // 2. Kiểm tra mật khẩu có thay đổi không
        boolean passwordChanged = (currentPassword != null && !currentPassword.equals(kh.getPassword()));
        String finalUpdateSql = passwordChanged ? updateUserSqlWithPassword : updateUserSqlWithoutPassword;

        // 3. Cập nhật user
        try (
            PreparedStatement pstmUser = cn.prepareStatement(finalUpdateSql);
            PreparedStatement deleteRoleStmt = cn.prepareStatement(deleteRoleSql);
            PreparedStatement insertRoleStmt = cn.prepareStatement(insertUserRoleSql)
        ) {
            pstmUser.setString(1, kh.getUsername());
            pstmUser.setString(2, kh.getFullname());
            pstmUser.setString(3, kh.getAddress());
            pstmUser.setString(4, kh.getPhone_number());

 
            pstmUser.setDate(5, kh.getDate_of_birth());

            if (passwordChanged) {
                pstmUser.setString(6, kh.getPassword());
                pstmUser.setInt(7, kh.getId());
            } else {
                pstmUser.setInt(6, kh.getId());
            }

            int userRows = pstmUser.executeUpdate();
            System.out.println("Số dòng user được cập nhật: " + userRows);

            if (userRows > 0) {
                // 4. Cập nhật role (xóa và chèn lại)
                deleteRoleStmt.setInt(1, kh.getId());
                deleteRoleStmt.executeUpdate();

                insertRoleStmt.setInt(1, kh.getId());
                insertRoleStmt.setInt(2, kh.getRole_id());
                int roleRows = insertRoleStmt.executeUpdate();

                cn.commit();
                rowsUpdated = userRows + roleRows;

                System.out.println("Cập nhật thành công. Role rows: " + roleRows);
            } else {
                cn.rollback();
                System.err.println("Không tìm thấy user để cập nhật.");
            }

        } catch (Exception e) {
            cn.rollback();
            System.err.println("Lỗi khi cập nhật user hoặc vai trò:");
            e.printStackTrace();
        } finally {
            cn.setAutoCommit(true);
        }

    } catch (Exception e) {
        System.err.println("Lỗi kết nối CSDL:");
        e.printStackTrace();
    }

    return rowsUpdated;
}




    public static void main(String[] args) {
        UserModel userModel = new UserModel();
        userModel.setId(5);
        userModel.setUsername("nguyenvana");
        userModel.setFullname("Nguyen Van A1");
        userModel.setAddress("123 Đường ABC, Quận 1, TP.HCM");
        userModel.setPhone_number("0909123456");
        userModel.setDate_of_birth(java.sql.Date.valueOf("1990-05-30")); // yyyy-MM-dd
        userModel.setPassword("111");
        userModel.setRole_id(1);

        // Gọi phương thức update
        UserService service = new UserService();
        service.update(userModel);
//        List<UserModel> result = service.getAll();
//        for (UserModel user : result) {
//            System.out.println("user" + user.toString());
//        }
        // Kiểm tra kết quả

    }

    
    public boolean checkUserName(String userName) {
        String sql = "Select 1 from  users where username = ?";
        try (Connection conn = Connect.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userName);
            ResultSet rs = stmt.executeQuery();
            return rs.next();

        } catch (SQLException e) {
            e.printStackTrace();

            return false;
        }
    }
    
    public UserModel getUserById(int id) {
        System.out.println("id: "+ id);
        UserModel user = null;

        String sql = """
    SELECT u.*, r.name AS role_name
    FROM users u
    JOIN user_roles ur ON u.id = ur.user_id
    JOIN roles r ON ur.role_id = r.id
    WHERE u.id = ?
""";

        try (Connection conn = Connect.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                UserModel userModel = new UserModel();

                userModel.setId(rs.getInt("id"));
                userModel.setRole_id(rs.getInt("role_id"));
                userModel.setRole_name(rs.getString("role_name"));
                userModel.setUsername(rs.getString("username"));
                userModel.setFullname(rs.getString("fullname"));
                userModel.setPhone_number(rs.getString("phone_number"));
                userModel.setDate_of_birth(rs.getDate("date_of_birth"));
                userModel.setAddress(rs.getString("address"));
                userModel.setCreated_at(rs.getDate("created_at"));
                userModel.setUpdate_at(rs.getDate("update_at"));
                userModel.setPassword(rs.getString("password"));
  
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

}

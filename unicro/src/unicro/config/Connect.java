/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Admin
 */
public class Connect {
        public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/vn_qlbh";
        String user = "postgres";
        String password = "password";

        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println(" Kết nối thành công!");
        } catch (SQLException e) {
            System.out.println(" Lỗi: " + e.getMessage());
        }
            System.out.println("");
    }

}

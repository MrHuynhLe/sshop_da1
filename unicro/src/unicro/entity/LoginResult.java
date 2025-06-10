/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class LoginResult {
     public int userId;
    public String fullName;
    public String role;
    public boolean success;
    public String message;

    public LoginResult(boolean success, String message, int userId, String fullName, String role) {
        this.success = success;
        this.message = message;
        this.userId = userId;
        this.fullName = fullName;
        this.role = role;
    }
}

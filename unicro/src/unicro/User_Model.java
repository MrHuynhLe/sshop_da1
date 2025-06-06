/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class User_Model {
       private int id ;
    private String username;
    private String fullname;
    private String address;
    private String phone_number;
    private String password;
    private Date date_of_birth;
    private int role_id; 
    private String role_name; 
    private Date created_at; 
    private Date update_at; 

    public User_Model() {
    }

    public User_Model(int id, String username, String fullname, String address, String phone_number, String password, Date date_of_birth, int role_id, String role_name, Date created_at, Date update_at) {
        this.id = id;
        this.username = username;
        this.fullname = fullname;
        this.address = address;
        this.phone_number = phone_number;
        this.password = password;
        this.date_of_birth = date_of_birth;
        this.role_id = role_id;
        this.role_name = role_name;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate_of_birth() {
        return date_of_birth;
    }

    public void setDate_of_birth(Date date_of_birth) {
        this.date_of_birth = date_of_birth;
    }

    public int getRole_id() {
        return role_id;
    }

    public void setRole_id(int role_id) {
        this.role_id = role_id;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(Date update_at) {
        this.update_at = update_at;
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Product {
    private int id;
    private String product_name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private Boolean active;

    public Product() {
    }

    public Product(int id, String product_name, LocalDateTime created_at, LocalDateTime updated_at, Boolean active) {
        this.id = id;
        this.product_name = product_name;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.active = active;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalDateTime updated_at) {
        this.updated_at = updated_at;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    
}

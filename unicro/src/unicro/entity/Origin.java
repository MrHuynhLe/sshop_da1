/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class Origin {
    private int id;
    private String origin_name;

    public Origin() {
    }

    public Origin(int id, String origin_name) {
        this.id = id;
        this.origin_name = origin_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin_name() {
        return origin_name;
    }

    public void setOrigin_name(String origin_name) {
        this.origin_name = origin_name;
    }
    
    
}

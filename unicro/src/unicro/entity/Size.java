/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class Size {
    
    private int id;
    private String size_name;

    public Size() {
    }

    public Size(int id, String size_name) {
        this.id = id;
        this.size_name = size_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSize_name() {
        return size_name;
    }

    public void setSize_name(String size_name) {
        this.size_name = size_name;
    }
    
    
}

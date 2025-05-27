/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

/**
 *
 * @author Admin
 */
public class Metarial {
    
    private int id;
    private String material_id;

    public Metarial() {
    }

    public Metarial(int id, String material_id) {
        this.id = id;
        this.material_id = material_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(String material_id) {
        this.material_id = material_id;
    }
    
    
}

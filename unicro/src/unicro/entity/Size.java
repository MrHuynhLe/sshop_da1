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
    private String maSize;
    private String ten ;

    public Size() {
    }

    public Size(int id, String size_name, String maSize, String ten) {
        this.id = id;
        this.size_name = size_name;
        this.maSize = maSize;
        this.ten = ten;
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

    public String getMaSize() {
        return maSize;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }



    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.util.Objects;

/**
 *
 * @author Admin
 */
public class ThuongHieu {
     private int id;
    private String ten;
    private String maThuongHieu;

    public ThuongHieu() {
    }

    public ThuongHieu(int id, String ten, String maThuongHieu) {
        this.id = id;
        this.ten = ten;
        this.maThuongHieu = maThuongHieu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMaThuongHieu() {
        return maThuongHieu;
    }

    public void setMaThuongHieu(String maThuongHieu) {
        this.maThuongHieu = maThuongHieu;
    }

    

 

 

    

    @Override
    public String toString() {
        return ten;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ThuongHieu other = (ThuongHieu) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.ten, other.ten)) {
            return false;
        }
        return Objects.equals(this.maThuongHieu, other.maThuongHieu);
    }

}

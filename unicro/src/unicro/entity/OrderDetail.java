/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.math.BigDecimal;

/**
 *
 * @author Admin
 */
public class OrderDetail {

    private int id;
    private int order_id;
    private int product_detail_id;
    private BigDecimal price;
    private int number_of_product;
    private String tenSp;
    private BigDecimal thanhTien;

    public OrderDetail(int order_id, int product_detail_id, BigDecimal price, int number_of_product) {
        this.order_id = order_id;
        this.product_detail_id = product_detail_id;
        this.price = price;
        this.number_of_product = number_of_product;
    }


    

    public OrderDetail() {
    }

    public OrderDetail(int id, int order_id, int product_detail_id, BigDecimal price, int number_of_product, String tenSp, BigDecimal thanhTien) {
        this.id = id;
        this.order_id = order_id;
        this.product_detail_id = product_detail_id;
        this.price = price;
        this.number_of_product = number_of_product;
        this.tenSp = tenSp;
        this.thanhTien = thanhTien;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public BigDecimal getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(BigDecimal thanhTien) {
        this.thanhTien = thanhTien;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getProduct_detail_id() {
        return product_detail_id;
    }

    public void setProduct_detail_id(int product_detail_id) {
        this.product_detail_id = product_detail_id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getNumber_of_product() {
        return number_of_product;
    }

    public void setNumber_of_product(int number_of_product) {
        this.number_of_product = number_of_product;
    }

}

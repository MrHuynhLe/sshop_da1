/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;


/**
 *
 * @author Admin
 */
public class Order {
    private int id;
    private int user_id;
    private int voucher_id;
    private Date order_date;
    private String note;
    private BigDecimal total;
    private String payment_method;
    private String status;

    public Order() {
    }

    public Order(int id, int user_id, int voucher_id, Date order_date, String note, BigDecimal total, String payment_method, String status) {
        this.id = id;
        this.user_id = user_id;
        this.voucher_id = voucher_id;
        this.order_date = order_date;
        this.note = note;
        this.total = total;
        this.payment_method = payment_method;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getVoucher_id() {
        return voucher_id;
    }

    public void setVoucher_id(int voucher_id) {
        this.voucher_id = voucher_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    
}

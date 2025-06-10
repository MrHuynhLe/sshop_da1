/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Voucher {

    private int id;
    private String code;
    private String discount_type;
    private BigDecimal discount_value;
    private LocalDate start_date;
    private LocalDate end_date;
    private BigDecimal max_purchase_amount;
    private BigDecimal min_purchase_amount;
    private LocalDateTime created_at;
    private Boolean active;

    public Voucher() {
    }

    public Voucher(int id, String code, String discount_type, BigDecimal discount_value, LocalDate start_date, LocalDate end_date, BigDecimal max_purchase_amount, BigDecimal min_purchase_amount, LocalDateTime created_at, Boolean active) {
        this.id = id;
        this.code = code;
        this.discount_type = discount_type;
        this.discount_value = discount_value;
        this.start_date = start_date;
        this.end_date = end_date;
        this.max_purchase_amount = max_purchase_amount;
        this.min_purchase_amount = min_purchase_amount;
        this.created_at = created_at;
        this.active = active;
    }

    public Voucher(int id, String code, String discount_type, BigDecimal discount_value, BigDecimal max_purchase_amount, BigDecimal min_purchase_amount, Boolean active) {
        this.id = id;
        this.code = code;
        this.discount_type = discount_type;
        this.discount_value = discount_value;
        this.max_purchase_amount = max_purchase_amount;
        this.min_purchase_amount = min_purchase_amount;
        this.active = active;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount_type() {
        return discount_type;
    }

    public void setDiscount_type(String discount_type) {
        this.discount_type = discount_type;
    }

    public BigDecimal getDiscount_value() {
        return discount_value;
    }

    public void setDiscount_value(BigDecimal discount_value) {
        this.discount_value = discount_value;
    }

    public LocalDate getStart_date() {
        return start_date;
    }

    public void setStart_date(LocalDate start_date) {
        this.start_date = start_date;
    }

    public LocalDate getEnd_date() {
        return end_date;
    }

    public void setEnd_date(LocalDate end_date) {
        this.end_date = end_date;
    }

    public BigDecimal getMax_purchase_amount() {
        return max_purchase_amount;
    }

    public void setMax_purchase_amount(BigDecimal max_purchase_amount) {
        this.max_purchase_amount = max_purchase_amount;
    }

    public BigDecimal getMin_purchase_amount() {
        return min_purchase_amount;
    }

    public void setMin_purchase_amount(BigDecimal min_purchase_amount) {
        this.min_purchase_amount = min_purchase_amount;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalDateTime created_at) {
        this.created_at = created_at;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

      @Override
    public String toString() {
        return code + " (" +
               (discount_type.equalsIgnoreCase("percentage") ? discount_value + "%" : "-" + discount_value + "đ") +
               ")";
    }
}

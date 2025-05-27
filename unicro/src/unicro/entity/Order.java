/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package unicro.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author Admin
 */
public class Order {
    private int id;
    private int user_id;
    private int voucher_id;
    private LocalDateTime order_date;
    private String note;
    private BigDecimal total;
    private String payment_method;
    private String status;
    
}

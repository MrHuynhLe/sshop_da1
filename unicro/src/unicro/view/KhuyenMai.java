/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unicro.view;

import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import unicro.entity.Voucher;
import unicro.service.Voucher_Service;

/**
 *
 * @author Admin
 */
public class KhuyenMai extends javax.swing.JPanel {

    private DefaultTableModel model;
    List<Voucher> list = new ArrayList<>();
    private Voucher_Service service = new Voucher_Service();

    /**
     * Creates new form KhuyenMai
     */
    public KhuyenMai() {
        initComponents();
        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Đang hoạt động", "Không hoạt động"}));
        fillTable();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVoucher = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txtMaVoucher = new javax.swing.JTextField();
        txtGiamGia2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboTrangThai = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        JdBd = new com.toedter.calendar.JDateChooser();
        jdKt = new com.toedter.calendar.JDateChooser();
        cboLoaiGiamGia = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        txtToiThieu = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtToida = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin khuyến mãi", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12))); // NOI18N
        setLayout(new java.awt.BorderLayout());

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tblVoucher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã khuyến mãi", "Giảm giá theo", "Giá trị giảm", "Ngày bắt đầu", "Ngày kết thúc", "Giảm tối đa", "Điều kiện giảm", "Ngày tạo", "Trạng thái"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblVoucher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVoucherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVoucher);

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        txtMaVoucher.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaVoucherActionPerformed(evt);
            }
        });

        txtGiamGia2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGiamGia2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Mã voucher :");

        jLabel3.setText("Giảm giá theo :");

        jLabel4.setText("Giá trị giảm");

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel7.setText("Trạng thái :");

        jLabel6.setText("Ngày KT(yyyy-mm-dd) :");

        jLabel5.setText("Ngày BĐ(yyyy-mm-dd) :");

        cboLoaiGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "percent", "amount" }));

        jLabel1.setText("Giảm tối thiếu");

        jLabel8.setText("Giảm tối đa");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                        .addGap(36, 36, 36))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtMaVoucher)
                    .addComponent(txtGiamGia2)
                    .addComponent(cboLoaiGiamGia, 0, 154, Short.MAX_VALUE)
                    .addComponent(txtToiThieu))
                .addGap(136, 136, 136)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                        .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(51, 51, 51)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JdBd, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                    .addComponent(jdKt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtToida))
                .addGap(32, 32, 32))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel8)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2)
                                .addComponent(jLabel5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(JdBd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(cboLoaiGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdKt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtGiamGia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(txtToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtToida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xoá");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(btnThem)
                .addGap(105, 105, 105)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addGap(128, 128, 128)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnReset, btnSua, btnThem, btnXoa});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnReset, btnSua, btnThem, btnXoa});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        add(jPanel1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void tblVoucherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVoucherMouseClicked
        int selectedRow = tblVoucher.getSelectedRow();
        if (selectedRow >= 0) {
            txtMaVoucher.setText(tblVoucher.getValueAt(selectedRow, 0).toString());
              String discountType =  tblVoucher.getValueAt(selectedRow, 1).toString();
              cboLoaiGiamGia.setSelectedItem(discountType);
            txtGiamGia2.setText(tblVoucher.getValueAt(selectedRow, 2).toString());
              txtToida.setText(tblVoucher.getValueAt(selectedRow,5 ).toString());
              txtToiThieu.setText(tblVoucher.getValueAt(selectedRow,6 ).toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            try {
                String startStr = tblVoucher.getValueAt(selectedRow, 3).toString();
                String endStr = tblVoucher.getValueAt(selectedRow, 4).toString();

                Date startDate = sdf.parse(startStr);
                Date endDate = sdf.parse(endStr);

                JdBd.setDate(startDate);
                jdKt.setDate(endDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            String trangThai = tblVoucher.getValueAt(selectedRow, 8).toString();
            if (trangThai.equalsIgnoreCase("Ngưng hoạt động")) {
                cboTrangThai.setSelectedItem("Ngưng hoạt động");
            } else {
                cboTrangThai.setSelectedItem("Đang hoạt động");
            }
        }
    }//GEN-LAST:event_tblVoucherMouseClicked

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:

        try {
            String code = txtMaVoucher.getText().trim();
            String discountType = cboLoaiGiamGia.getSelectedItem().toString().toLowerCase(); 
            String discountStr = txtGiamGia2.getText().trim();
            String minPurchase= txtToiThieu.getText().trim();
            String maxPurchase = txtToida.getText().trim();
            BigDecimal min = new BigDecimal(minPurchase);
            BigDecimal max = new BigDecimal(maxPurchase);
            if (min.compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this, "Giá trị  phải lớn hơn 0.");
                    return;
                }
            if (max.compareTo(BigDecimal.ZERO) <= 0 ) {
                    JOptionPane.showMessageDialog(this, "Giá trị giảm phải lớn hơn 0.");
                    return;
                }
            Date fromDate = JdBd.getDate();
            Date toDate = jdKt.getDate();
            if (code.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Mã voucher không được để trống!");
                return;
            }
           
            if (service.check(code)) {
                JOptionPane.showMessageDialog(this, "Mã voucher đã tồn tại!");
                return;
            }
            BigDecimal discountValue;
            try {
                discountValue = new BigDecimal(discountStr);
                if (discountValue.compareTo(BigDecimal.ZERO) <= 0) {
                    JOptionPane.showMessageDialog(this, "Giá trị giảm phải lớn hơn 0.");
                    return;
                }
                if (discountType.equals("percent") && discountValue.compareTo(new BigDecimal("100")) > 0) {
                    JOptionPane.showMessageDialog(this, "Phần trăm giảm không được vượt quá 100%.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm không hợp lệ.");
                return;
            }
            if (fromDate == null || toDate == null) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày bắt đầu và kết thúc.");
                return;
            }

            LocalDate startDate = fromDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            LocalDate endDate = toDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

            if (endDate.isBefore(startDate)) {
                JOptionPane.showMessageDialog(this, "Ngày kết thúc phải sau hoặc bằng ngày bắt đầu.");
                return;
            }
            boolean isActive = cboTrangThai.getSelectedItem().toString().equals("Đang hoạt động");
            Voucher v = new Voucher();
            v.setCode(code);
            v.setDiscount_type(discountType);
            v.setDiscount_value(discountValue);
            v.setStart_date(startDate);
            v.setMin_purchase_amount(min);
            v.setMax_purchase_amount(max);
            v.setEnd_date(endDate);
            v.setCreated_at(LocalDateTime.now());
            v.setActive(isActive);

            boolean success = service.addVoucher(v);
            if (success) {
                JOptionPane.showMessageDialog(this, "Thêm thành công!");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Thêm thất bại!");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }

    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        try {
            String code = txtMaVoucher.getText().trim();
            String discountType = cboLoaiGiamGia.getSelectedItem().toString().toLowerCase();
             BigDecimal max_value;
             BigDecimal min_value;
             max_value = new BigDecimal(txtToida.getText().trim());
             min_value = new BigDecimal(txtToiThieu.getText().trim());
            BigDecimal discountValue;
            try {
                discountValue = new BigDecimal(txtGiamGia2.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Giá trị giảm giá không hợp lệ!");
                return;
            }

            Date fromDate = JdBd.getDate();
            LocalDate startLocalDate = fromDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            Date toDate = jdKt.getDate();
            LocalDate endLocalDate = toDate.toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDateTime createdAt = LocalDateTime.now();
            boolean active = cboTrangThai.getSelectedIndex() == 0;

            Voucher v = new Voucher();
            v.setCode(code);
            v.setDiscount_type(discountType);
            v.setDiscount_value(discountValue);
            v.setStart_date(startLocalDate);
            v.setEnd_date(endLocalDate);
            v.setMax_purchase_amount(max_value);
            v.setMin_purchase_amount(min_value);
            v.setCreated_at(createdAt);
            v.setActive(active);

            boolean result = service.updateVoucher(v);
            if (result) {
                JOptionPane.showMessageDialog(this, "Cập nhật voucher thành công!");
                fillTable();
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật voucher thất bại!");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        // TODO add your handling code here:
        try {
            String code = txtMaVoucher.getText().trim();
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xoá voucher này không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean result = service.deleteVoucher(code);
                if (result) {
                    JOptionPane.showMessageDialog(this, "Xoá voucher thành công!");
                    fillTable();
                } else {
                    JOptionPane.showMessageDialog(this, "Xoá voucher thất bại!");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi: " + e.getMessage());
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void txtMaVoucherActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaVoucherActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaVoucherActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        // TODO add your handling code here:
        txtMaVoucher.setText("");
        cboLoaiGiamGia.setSelectedIndex(0);
        txtGiamGia2.setText("");
        jdKt.setDate(null);
        JdBd.setDate(null);
    }//GEN-LAST:event_btnResetActionPerformed

    private void txtGiamGia2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGiamGia2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGiamGia2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private com.toedter.calendar.JDateChooser JdBd;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboLoaiGiamGia;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private com.toedter.calendar.JDateChooser jdKt;
    private javax.swing.JTable tblVoucher;
    private javax.swing.JTextField txtGiamGia2;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtToiThieu;
    private javax.swing.JTextField txtToida;
    // End of variables declaration//GEN-END:variables
 public void fillTable() {
        model = (DefaultTableModel) tblVoucher.getModel();
        model.setRowCount(0);
        list = service.getAllVouchers();

        for (Voucher v : list) {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String startDate = sdf.format(Date.from(v.getStart_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String endDate = sdf.format(Date.from(v.getEnd_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            String ngayTao = sdf.format(Date.from(v.getEnd_date().atStartOfDay(ZoneId.systemDefault()).toInstant()));
            model.addRow(new Object[]{
                v.getCode(),
                v.getDiscount_type(),
                formatDiscount(v.getDiscount_type(), v.getDiscount_value()),
                startDate,
                endDate,
                 String.format("%,.0f", v.getMax_purchase_amount()),
                 String.format("%,.0f", v.getMin_purchase_amount()),
                ngayTao,
                v.getActive() ? "Ngưng hoạt động" : "Đang hoạt đông"
            });
        }
    }

    private String formatDiscount(String type, BigDecimal value) {
        if ("percent".equalsIgnoreCase(type)) {
            return value.stripTrailingZeros().toPlainString() + "%";
        } else if ("amount".equalsIgnoreCase(type)) {
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
            return currencyFormat.format(value);
        } else {
            return value.toPlainString();
        }
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unicro.view;

import java.awt.Desktop;
import java.awt.Font;
import java.awt.image.SampleModel;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import unicro.config.Connect;
import unicro.entity.Order;
import unicro.entity.OrderDetail;
import unicro.entity.SanPham;
import unicro.entity.SanPhamChiTietResponse;
import unicro.service.OrderDetailService;
import unicro.service.Order_Service;
import unicro.service.SanPhamChiTietRepo;
import unicro.service.SanPhamService;
import java.sql.*;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import unicro.entity.GioHang;
import unicro.entity.LoginResult;
import unicro.entity.OrderDetailResponse;
import unicro.entity.SanPhamChiTiet;
import unicro.entity.Session;
import unicro.entity.Voucher;
import unicro.service.User_Service;
import unicro.service.Voucher_Service;

/**
 *
 * @author Admin
 */
public class BanHang extends javax.swing.JPanel {

    private final SanPhamService spctService = new SanPhamService();
    private final Order_Service orderService = new Order_Service();
    private final OrderDetailService orderDetailService = new OrderDetailService();
    private final Voucher_Service voucher_Service = new Voucher_Service();
    private List<GioHang> gioHang = new ArrayList<>();
    private final User_Service usr = new User_Service();
    private final Session s = new Session();
    SanPhamChiTietRepo chiTietRepo = new SanPhamChiTietRepo();

    private int currentOrderId = -1;
    private int currentUserId = Session.currentUserId;
    private int idHoaDonDangChon = -1;
    private Integer currentOrderId1 = null;
    private String currentOrderStatus = "Pending";

    private DefaultTableModel modelHD;
    private DefaultTableModel modelCT;
    private DefaultTableModel modelSPCT;

    private final String url = "jdbc:postgresql://localhost:5432/da_qlbh";
    private final String user = "postgres";
    private final String password = "password";

    public BanHang() {
        initComponents();
        modelHD = (DefaultTableModel) tblHoaDon.getModel();
        modelCT = (DefaultTableModel) tblChiTietHoaDon.getModel();
        modelSPCT = (DefaultTableModel) tblSanPham.getModel();

        txtNhanVien.setEditable(false);
        txtThanhToan.setEditable(false);
        txtMaHd.setEditable(false);
        txtTongTienSauGIam.setEditable(false);
        txtTongtien.setEditable(false);
        txtTienThua.setEnabled(false);
        loadTableHoaDon();
        loadTableSanPhamChiTiet();
        setPhuongThucThanhToan();

        upvoucher();

    }

    private void setPhuongThucThanhToan() {
        cboPhuongThucThanhToan.removeAllItems();
        cboPhuongThucThanhToan.addItem("Cash");
        cboPhuongThucThanhToan.addItem("Credit Cash");

    }

    private String getMauSacName(int mauId) {

        String result = "";
        String sql = "SELECT TEN FROM MAU_SAC WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, mauId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("TEN");
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getSizeName(int sizeId) {
        String result = "";
        String sql = "SELECT TEN FROM SIZE WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, sizeId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("TEN");
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String getChatLieuName(int chatLieuId) {
        String result = "";
        String sql = "SELECT TEN FROM CHAT_LIEU WHERE ID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, chatLieuId);
            try (java.sql.ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    result = rs.getString("TEN");
                }
            }
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        cboKhuyenMai = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblChiTietHoaDon = new javax.swing.JTable();
        btnXoa = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        txtTimKiem = new javax.swing.JTextField();
        btnThemSanPham = new javax.swing.JButton();
        btnTimkiem = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        btnThanhToan = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        txtMaHD = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JLabel();
        txtTienThua = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtTongtien = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cboPhuongThucThanhToan = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        lblMaHD = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNote = new javax.swing.JTextArea();
        jLabel13 = new javax.swing.JLabel();
        txtTongTienSauGIam = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        txtMaHd = new javax.swing.JTextField();
        txtNhanVien = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtThanhToan = new javax.swing.JTextField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Bán hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Thanh toán");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)), "Hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên NV", "Mã HD", "Ngày tạo", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 673, Short.MAX_VALUE)
                .addGap(9, 9, 9))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Voucher", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        cboKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboKhuyenMaiActionPerformed(evt);
            }
        });

        jLabel1.setText("Khuyến mãi :");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(285, 285, 285))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(0, 14, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hoá đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblChiTietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDSPCT", "Mã SPCT", "Tên SP", "Số lượng", "Giá", "Thành Tiền"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblChiTietHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChiTietHoaDonMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblChiTietHoaDon);

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "IDSPCT", "Mã SPCT", "Tên sản phẩm", "Số lượng", "Giá bán", "Màu sắc", "Kích thước", "Chất liệu", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, true, true, true, true, true, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblSanPham);
        if (tblSanPham.getColumnModel().getColumnCount() > 0) {
            tblSanPham.getColumnModel().getColumn(1).setResizable(false);
            tblSanPham.getColumnModel().getColumn(9).setResizable(false);
        }

        btnThemSanPham.setText("Thêm SP");
        btnThemSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemSanPhamActionPerformed(evt);
            }
        });

        btnTimkiem.setText("Tìm kiếm");
        btnTimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(btnTimkiem)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 307, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnThemSanPham)
                .addGap(80, 80, 80))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 656, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnTimkiem)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnThemSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2)
                .addGap(9, 9, 9))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnXoa)
                .addContainerGap())
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnXoa)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin hoá đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel8.setText("Mã hóa đơn");

        jLabel9.setText("Tên nhân viên");

        jLabel10.setText("Tổng tiền");

        jLabel12.setText("Tiền khách đưa : ");

        jLabel14.setText("Tiền thừa : ");

        btnThanhToan.setBackground(new java.awt.Color(102, 255, 102));
        btnThanhToan.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        btnThanhToan.setForeground(new java.awt.Color(51, 0, 153));
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTienThua.setText("0");

        jLabel6.setText("Phương thúc thanh toán");

        cboPhuongThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel11.setText("Note");

        txtNote.setColumns(20);
        txtNote.setRows(5);
        jScrollPane3.setViewportView(txtNote);

        jLabel13.setText("Tổng tiền sau giảm:");

        jButton1.setText("Reset");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtMaHd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaHdActionPerformed(evt);
            }
        });

        jLabel16.setText("Trạng thái :");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtMaHD, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(166, 166, 166)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel14))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(17, 17, 17)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jButton1)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(53, 53, 53)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(txtTongTienSauGIam, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cboPhuongThucThanhToan, 0, 146, Short.MAX_VALUE)
                                        .addComponent(txtTongtien))
                                    .addComponent(btnThanhToan)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addGap(14, 14, 14)
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel9))
                                .addGap(28, 28, 28)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtMaHd)
                                    .addComponent(txtNhanVien, javax.swing.GroupLayout.DEFAULT_SIZE, 145, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblMaHD, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {cboPhuongThucThanhToan, txtMaHd, txtNhanVien, txtThanhToan, txtTienKhachDua, txtTongTienSauGIam, txtTongtien});

        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(txtMaHD))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(lblMaHD)))
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(txtMaHd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTenNV)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboPhuongThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienThua, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtTongTienSauGIam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 44, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThanhToan)
                    .addComponent(jButton1))
                .addGap(14, 14, 14))
        );

        jPanel5Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {cboPhuongThucThanhToan, txtMaHd, txtNhanVien, txtThanhToan, txtTienKhachDua, txtTongTienSauGIam, txtTongtien});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(jLabel3)))
                        .addGap(16, 16, 16))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        int row = tblHoaDon.getSelectedRow();
        if (row == -1) {
            return;
        }
        gioHang.clear();
        String maHD = tblHoaDon.getValueAt(row, 2).toString();
        String tenNv = tblHoaDon.getValueAt(row, 1).toString();
        String trangThai = tblHoaDon.getValueAt(row, 4).toString();
        currentOrderId1 = Integer.parseInt(maHD);
        currentOrderStatus = trangThai;
        txtMaHd.setText(maHD);
        txtThanhToan.setText(trangThai);
        if (trangThai.equalsIgnoreCase("Pending")) {
            idHoaDonDangChon = Integer.parseInt(maHD);
        } else {
            idHoaDonDangChon = -1;
        }
        txtNhanVien.setText(tenNv);

        List<OrderDetailResponse> dsCT = orderDetailService.getByOrderId1(Integer.parseInt(maHD));
        loadChiTietHoaDonVaoTable(dsCT);
        txtTongtien.setText(String.valueOf(tinhTongTien()));
        tinhTongTien();
        loadTableSanPhamChiTiet();

    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void tblChiTietHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblChiTietHoaDonMouseClicked

    }//GEN-LAST:event_tblChiTietHoaDonMouseClicked

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked


    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        String status = txtThanhToan.getText();
        if (status.equalsIgnoreCase("Đã thanh toán")) {
            JOptionPane.showMessageDialog(null, "Hoá đơn đã thanh toán");
        } else {
            try {
                int orderId = Integer.parseInt(txtMaHd.getText().trim());
                if (tblChiTietHoaDon.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(null, "Hóa đơn chưa có sản phẩm! Không thể thanh toán.");
                    return;
                }
                BigDecimal tongTien = new BigDecimal(txtTongtien.getText().replace(",", "").trim());

                String phuongThuc = cboPhuongThucThanhToan.getSelectedItem().toString();
                String ghiChu = txtNote.getText();

                Voucher selectedVoucher = (Voucher) cboKhuyenMai.getSelectedItem();
                Integer voucherId = (selectedVoucher != null) ? selectedVoucher.getId() : null;

                BigDecimal tienKhachDua = BigDecimal.ZERO;
                BigDecimal tienThua = BigDecimal.ZERO;

                if (phuongThuc.equalsIgnoreCase("Cash")) {
                    String tienKhachStr = txtTienKhachDua.getText().replace(",", "").trim();
                    if (tienKhachStr.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Vui lòng nhập tiền khách đưa!");
                        return;
                    }
                    try {
                        tienKhachDua = new BigDecimal(tienKhachStr);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Tiền khách đưa phải là số hợp lệ!");
                        return;
                    }
                    tienThua = tienKhachDua.subtract(tongTien);

                    if (tienThua.compareTo(BigDecimal.ZERO) < 0) {
                        JOptionPane.showMessageDialog(null, "Tiền khách đưa không đủ!");
                        return;
                    }
                    txtTienThua.setText(String.format("%,.0f", tienThua));
                } else {
                    tienKhachDua = tongTien;
                    tienThua = BigDecimal.ZERO;
                    txtTienThua.setText("0");
                }
                Order_Service service = new Order_Service();
                boolean ok = service.capNhatHoaDon(orderId, voucherId, tongTien, phuongThuc, ghiChu);

                if (ok) {
                    JOptionPane.showMessageDialog(null, "Thanh toán thành công!");
                    InHoaDon();
                    loadTableHoaDon();
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy hóa đơn cần cập nhật!");
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi định dạng mã hóa đơn hoặc tổng tiền!");
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Lỗi thanh toán: " + e.getMessage());
            }
            int row = tblHoaDon.getSelectedRow();
            if (row == -1) {
                return;
            }
            loadTableHoaDon();
        }

    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnThemSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemSanPhamActionPerformed

        int selectedRow = tblSanPham.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
            return;
        }
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        int idSpct = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());
        String tenSpct = model.getValueAt(selectedRow, 3).toString();
        SanPhamChiTiet spct = chiTietRepo.findById(idSpct);

        if (spct == null) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy sản phẩm!");
            return;
        }
        String input = JOptionPane.showInputDialog(null, "Nhập số lượng:");
        if (input == null) {
            return;
        }
        int soLuongNhap;
        try {
            soLuongNhap = Integer.parseInt(input);
            if (soLuongNhap <= 0) {
                JOptionPane.showMessageDialog(null, "Số lượng phải lớn hơn 0!");
                return;
            }
            if (soLuongNhap > spct.getSoLuong()) {
                JOptionPane.showMessageDialog(null, "Không đủ hàng trong kho!");
                return;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Số lượng phải là số nguyên!");
            return;
        }

        if (currentOrderId1 == null || !"Pending".equalsIgnoreCase(currentOrderStatus)) {
            Order newOrder = orderService.createPendingOrder(currentUserId);
            if (newOrder == null) {
                JOptionPane.showMessageDialog(null, "Không thể tạo hóa đơn mới!");
                return;
            }
            currentOrderId1 = newOrder.getId();
            currentOrderStatus = newOrder.getStatus();
            loadTableHoaDon();
        }

        if ("Pending".equalsIgnoreCase(currentOrderStatus)) {
            OrderDetail existing = orderDetailService.findByOrderIdAndProductDetailId(currentOrderId1, spct.getId());

            if (existing != null) {
                int soLuongMoi = existing.getNumber_of_product() + soLuongNhap;
                if (soLuongMoi > spct.getSoLuong()) {
                    JOptionPane.showMessageDialog(null, "Số lượng vượt quá tồn kho!");
                    return;
                }
                existing.setNumber_of_product(soLuongMoi);
                boolean updated = orderDetailService.update(existing);

                if (updated) {
                    chiTietRepo.capNhatSoLuongTon(spct.getId(), spct.getSoLuong() - soLuongNhap);
                    JOptionPane.showMessageDialog(null, "Đã cập nhật số lượng sản phẩm trong hóa đơn!");
                    loadChiTietHoaDonVaoTable(orderDetailService.getByOrderId1(currentOrderId1));
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật sản phẩm!");
                }

            } else {
                OrderDetail ct = new OrderDetail();
                ct.setOrder_id(currentOrderId1);
                ct.setProduct_detail_id(spct.getId());
                ct.setPrice(BigDecimal.valueOf(spct.getDonGia()));
                ct.setNumber_of_product(soLuongNhap);

                boolean ok = orderDetailService.insert(ct);
                if (ok) {
                    chiTietRepo.capNhatSoLuongTon(spct.getId(), spct.getSoLuong() - soLuongNhap);
                    JOptionPane.showMessageDialog(null, "Đã thêm sản phẩm vào hóa đơn!");
                    loadChiTietHoaDonVaoTable(orderDetailService.getByOrderId1(currentOrderId1));
                } else {
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm sản phẩm!");
                }
            }

        } else {
            JOptionPane.showMessageDialog(null, "Không thể thêm vào hóa đơn đã thanh toán. Hãy tạo hóa đơn mới.");
        }

        upvoucher();

    }//GEN-LAST:event_btnThemSanPhamActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int selectedRow = tblChiTietHoaDon.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xoá!");
            return;
        }

        if (currentOrderStatus == null || !currentOrderStatus.equalsIgnoreCase("Pending")) {
            JOptionPane.showMessageDialog(null, "Chỉ được xoá sản phẩm khi hóa đơn đang ở trạng thái 'Pending' hoặc chưa thanh toán!");
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(null, "Bạn có muốn xoá sản phẩm này khỏi hóa đơn?", "Xác nhận xoá", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        int idSpct = Integer.parseInt(model.getValueAt(selectedRow, 1).toString());
        int soLuongXoa = Integer.parseInt(model.getValueAt(selectedRow, 4).toString());
        SanPhamChiTiet spct = chiTietRepo.findById(idSpct);
        if (spct != null) {
            chiTietRepo.capNhatSoLuongTon(idSpct, spct.getSoLuong() + soLuongXoa);
        }

        boolean deleted = orderDetailService.deleteByOrderIdAndProductDetailId(currentOrderId1, idSpct);
        if (!deleted) {
            JOptionPane.showMessageDialog(null, "Xóa thất bại trong CSDL!");
            return;
        }

        JOptionPane.showMessageDialog(null, "Đã xoá sản phẩm khỏi hóa đơn!");
        model.removeRow(selectedRow);

        loadChiTietHoaDonVaoTable(orderDetailService.getByOrderId1(currentOrderId1));
        loadTableSanPhamChiTiet();
        upvoucher();
        List<OrderDetailResponse> chiTietConLai = orderDetailService.getByOrderId1(currentOrderId1);
        if (chiTietConLai == null || chiTietConLai.isEmpty()) {
            boolean deletedOrder = orderService.deleteById(currentOrderId1);
            if (deletedOrder) {
                JOptionPane.showMessageDialog(null, "Không còn sản phẩm nào, hóa đơn đã được xoá!");
                ((DefaultTableModel) tblChiTietHoaDon.getModel()).setRowCount(0);
                loadTableHoaDon();
            } else {
                JOptionPane.showMessageDialog(null, "Xoá hóa đơn thất bại trong CSDL!");
            }
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void cboKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboKhuyenMaiActionPerformed
        // TODO add your handling code here:
        // tinhTongTienSauGiam();
        apDungVoucher();
    }//GEN-LAST:event_cboKhuyenMaiActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        resetHoaDon();
        upvoucher();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtMaHdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaHdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaHdActionPerformed

    private void tblHoaDonMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_tblHoaDonMouseEntered

    private void btnTimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimkiemActionPerformed
        // TODO add your handling code here:
        String keyword = txtTimKiem.getText().trim();
        if (keyword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập từ khóa tìm kiếm.");
            return;
        }
        List<SanPhamChiTietResponse> result = chiTietRepo.findByTenSanPham(keyword);
        DefaultTableModel model = (DefaultTableModel) tblSanPham.getModel();
        model.setRowCount(0);

        int stt = 1;
        for (SanPhamChiTietResponse sp : result) {
            model.addRow(new Object[]{
                stt++,
                sp.getId(),
                sp.getMaSp(),
                sp.getTenSanPham(),
                sp.getSoLuong(),
                sp.getDonGia(),
                sp.getTenMau(),
                sp.getTenSize(),
                sp.getTenChatLieu(),
                sp.getSoLuong() == 0 ? "Hết hàng " : "Còn hàng"
            });
        }

        if (result.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm nào.");
        }
    }//GEN-LAST:event_btnTimkiemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnThemSanPham;
    private javax.swing.JButton btnTimkiem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cboKhuyenMai;
    private javax.swing.JComboBox<String> cboPhuongThucThanhToan;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lblMaHD;
    private javax.swing.JTable tblChiTietHoaDon;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JLabel txtMaHD;
    private javax.swing.JTextField txtMaHd;
    private javax.swing.JTextField txtNhanVien;
    private javax.swing.JTextArea txtNote;
    private javax.swing.JLabel txtTenNV;
    private javax.swing.JTextField txtThanhToan;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JLabel txtTienThua;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTongTienSauGIam;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables

    private void loadTableHoaDon() {
        modelHD.setRowCount(0);
        List<Order> ds = orderService.getAllOrders();
        int stt = 1;
        for (Order hd : ds) {
            modelHD.addRow(new Object[]{
                stt++,
                // hd.getUser_id(),
                orderService.getUserNameByUserId(hd.getUser_id()),
                hd.getId(),
                hd.getOrder_date(),
                hd.getStatus()
            });
        }
    }

    private void loadTableSanPhamChiTiet() {
        modelSPCT.setRowCount(0);
        List<SanPhamChiTietResponse> ds = chiTietRepo.getAllSPCT();
        DecimalFormat df = new DecimalFormat("#,###");
        int stt = 1;
        for (SanPhamChiTietResponse spct : ds) {
            String trangThai;
            int soLuong = spct.getSoLuong();
            if (soLuong <= 0) {
                trangThai = "Hết hàng";
            } else {
                trangThai = "Còn Hàng";
            }
            modelSPCT.addRow(new Object[]{
                stt++,
                spct.getId(),
                spct.getMaSp(),
                spct.getTenSanPham(),
                soLuong,
                df.format(spct.getDonGia()),
                (spct.getTenMau()),
                spct.getTenSize(),
                spct.getTenChatLieu(),
                trangThai

            });
        }
    }

    private void loadTableGioHang(int orderId) {
        modelCT.setRowCount(0);
        List<OrderDetail> dsCT = orderDetailService.getByOrderId(orderId);
        int stt = 1;
        DecimalFormat df = new DecimalFormat("#,###");
        for (OrderDetail ct : dsCT) {
            int idSpct = ct.getProduct_detail_id();
            String maSp = orderDetailService.getMaSPByIdSpct(idSpct);
            String tenSP = ct.getTenSp();
            int soLuongBan = ct.getNumber_of_product();
            BigDecimal gia = ct.getPrice();
            double thanhTien = gia.doubleValue() * soLuongBan;

            modelCT.addRow(new Object[]{
                stt++,
                ct.getProduct_detail_id(),
                maSp,
                tenSP,
                soLuongBan,
                df.format(gia),
                df.format(thanhTien),
                ct.getId()
            });
        }
    }

    private void capNhatBangGioHang() {
        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        model.setRowCount(0);
        int stt = 1;
        DecimalFormat df = new DecimalFormat("#,###");
        for (GioHang sp : gioHang) {
            model.addRow(new Object[]{
                stt++,
                sp.getIdSpct(),
                sp.getMaSPCT(),
                sp.getTenSP(),
                sp.getSoLuong(),
                df.format(sp.getGiaBan()),
                df.format(sp.getGiaBan() * sp.getSoLuong())
            });
        }
    }

    private void tinhTienThua() {
        try {
            String tongTienStr = txtTongtien.getText().replace(",", "");
            String tienKhachStr = txtTienKhachDua.getText().replace(",", "");
            BigDecimal tongTien = new BigDecimal(tongTienStr);
            BigDecimal tienKhach = new BigDecimal(tienKhachStr);
            if (tienKhach.compareTo(tongTien) < 0) {
                JOptionPane.showMessageDialog(this, "Tiền khách đưa không đủ!");
                return;
            }
            BigDecimal tienThua = tienKhach.subtract(tongTien);
            txtTienThua.setText(String.format("%,.0f", tienThua));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tiền khách đưa phải là số!");
        }
    }

    private void apDungVoucher() {
        Voucher selectedVoucher = (Voucher) cboKhuyenMai.getSelectedItem();
        BigDecimal tongTienGoc = tinhTongTien();
        if (selectedVoucher == null) {
            txtTongtien.setText(String.format("%,.0f", tongTienGoc));
            txtTongTienSauGIam.setText(String.format("%,.0f", tongTienGoc));
            return;
        }
        BigDecimal minPurchase = Optional.ofNullable(selectedVoucher.getMin_purchase_amount()).orElse(BigDecimal.ZERO);
        if (tongTienGoc.compareTo(minPurchase) < 0) {
            JOptionPane.showMessageDialog(this,
                    "Hóa đơn chưa đủ điều kiện để áp dụng voucher.\nĐơn tối thiểu: "
                    + String.format("%,.0f", minPurchase));
            txtTongTienSauGIam.setText(String.format("%,.0f", tongTienGoc));
            return;
        }

        BigDecimal giamGia = BigDecimal.ZERO;

        if ("PERCENT".equalsIgnoreCase(selectedVoucher.getDiscount_type())) {
            BigDecimal percent = Optional.ofNullable(selectedVoucher.getDiscount_value()).orElse(BigDecimal.ZERO);
            giamGia = tongTienGoc.multiply(percent).divide(BigDecimal.valueOf(100));
            BigDecimal maxGiam = selectedVoucher.getMax_purchase_amount();
            if (maxGiam != null && giamGia.compareTo(maxGiam) > 0) {
                giamGia = maxGiam;
            }
        } else if ("AMOUNT".equalsIgnoreCase(selectedVoucher.getDiscount_type())) {
            giamGia = Optional.ofNullable(selectedVoucher.getDiscount_value()).orElse(BigDecimal.ZERO);
        }
        if (giamGia.compareTo(tongTienGoc) > 0) {
            giamGia = tongTienGoc;
        }

        BigDecimal tongSauGiam = tongTienGoc.subtract(giamGia);

        txtTongtien.setText(String.format("%,.0f", tongTienGoc));
        txtTongTienSauGIam.setText(String.format("%,.0f", tongSauGiam));
    }

    private BigDecimal tinhTongTien() {
        BigDecimal tongTien = BigDecimal.ZERO;
        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        for (int i = 0; i < model.getRowCount(); i++) {
            Object thanhTienObj = model.getValueAt(i, 6);
            if (thanhTienObj != null) {
                try {
                    String thanhTienStr = thanhTienObj.toString().replace(",", "");
                    BigDecimal thanhTien = new BigDecimal(thanhTienStr);
                    tongTien = tongTien.add(thanhTien);
                } catch (NumberFormatException e) {
                    System.err.println("Lỗi chuyển đổi số: " + thanhTienObj);
                }
            }
        }
        return tongTien;
    }

    private void tinhTongTienSauGiam() {
        BigDecimal tongTienGoc = tinhTongTien();
        BigDecimal giamGia = BigDecimal.ZERO;
        Voucher selectedVoucher = (Voucher) cboKhuyenMai.getSelectedItem();
        if (selectedVoucher != null) {
            if ("PERCENT".equalsIgnoreCase(selectedVoucher.getDiscount_type())) {
                giamGia = tongTienGoc.multiply(selectedVoucher.getDiscount_value())
                        .divide(new BigDecimal("100"));
            } else if ("AMOUNT".equalsIgnoreCase(selectedVoucher.getDiscount_type())) {
                giamGia = selectedVoucher.getDiscount_value();
            }
            if (giamGia.compareTo(tongTienGoc) > 0) {
                giamGia = tongTienGoc;
            }
        }

        BigDecimal tongTienSauGiam = tongTienGoc.subtract(giamGia);
        txtTongTienSauGIam.setText(String.format("%,.0f", tongTienSauGiam));
    }

    public void loadChiTietHoaDonVaoTable(List<OrderDetailResponse> dsCT) {
        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        model.setRowCount(0);
        int stt = 1;
        DecimalFormat df = new DecimalFormat("#,###");
        for (OrderDetailResponse od : dsCT) {
            model.addRow(new Object[]{
                stt++,
                od.getIdSpct(),
                od.getMaSp(),
                od.getTenSp(),
                od.getSoLuong(),
                df.format(od.getGia()),
                df.format(od.getThanhTien())
            });
        }
    }

    private void resetHoaDon() {
        txtMaHD.setText("");
        txtNote.setText("");
        txtTenNV.setText("");
        txtTienKhachDua.setText("");
        txtTienThua.setText("");
        cboKhuyenMai.setSelectedIndex(0);
        cboPhuongThucThanhToan.setSelectedIndex(0);
        txtTongtien.setText("");
        txtTongTienSauGIam.setText("");
        DefaultTableModel model = (DefaultTableModel) tblChiTietHoaDon.getModel();
        model.setRowCount(0);
    }

    private void upvoucher() {
        List<Voucher> listVoucher = voucher_Service.getAllActiveValid();
        DefaultComboBoxModel<Voucher> model = new DefaultComboBoxModel<>();
        for (Voucher v : listVoucher) {
            model.addElement(v);
        }
        cboKhuyenMai.setModel((ComboBoxModel) model);
    }

    public Order createPendingOrder(int userId) {
        try {
            Order order = new Order();
            order.setUser_id(userId);
            LocalDateTime ldt = LocalDateTime.now();
            ZoneId zoneId = ZoneId.systemDefault();
            Date date = (Date) Date.from(ldt.atZone(zoneId).toInstant());
            order.setOrder_date(date); //
            order.setStatus("Pending");
            order.setTotal(BigDecimal.ZERO);

            boolean success = orderService.insert(order);
            if (success) {
                Order pendingOrder = orderService.findPendingByUser(userId);
                return pendingOrder;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void InHoaDon() {
        try {
            String orderId = txtMaHd.getText().trim();
            String tongTien = txtTongtien.getText().trim();
            String thanhTien = txtTongTienSauGIam.getText().trim();
            String tienKhachDua = txtTienKhachDua.getText().trim();
            String tienThua = txtTienThua.getText().trim();
            String nhanVien = txtNhanVien.getText().trim();
            String phuongThuc = cboPhuongThucThanhToan.getSelectedItem().toString();

            String voucher = "Không áp dụng";
            if (cboKhuyenMai.getSelectedItem() instanceof Voucher selectedVoucher) {
                voucher = selectedVoucher.getCode();
            }

            BigDecimal giamGia = new BigDecimal(tongTien.replace(",", "")).subtract(new BigDecimal(thanhTien.replace(",", "")));

            String ngayTao = java.time.LocalDateTime.now()
                    .format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            List<OrderDetailResponse> danhSachSp = orderDetailService.getByOrderId1(Integer.parseInt(orderId));
            StringBuilder spBuilder = new StringBuilder();
            spBuilder.append("====== DANH SÁCH SẢN PHẨM ======\n");
            spBuilder.append(String.format("%-25s %-10s %-15s %-15s\n", "Tên SP", "SL", "Đơn giá", "Thành tiền"));
            for (OrderDetailResponse sp : danhSachSp) {
                spBuilder.append(String.format("%-25s %-10d %,-15.0f %,-15.0f\n",
                        sp.getTenSp(),
                        sp.getSoLuong(),
                        sp.getGia(),
                        sp.getThanhTien()));
            }
            String noiDung = """
            ========= HÓA ĐƠN =========
            Mã HĐ       : %s
            Nhân viên   : %s
            Ngày tạo    : %s
            %s
            -------------------------------
            Tổng tiền   : %s
            Giảm giá    : %s
            Thành tiền  : %s
            Voucher     : %s
            Phương thức : %s
            Khách đưa   : %s
            Tiền thừa   : %s
            ========== CẢM ƠN QUÝ KHÁCH ==========
            """.formatted(
                    orderId,
                    nhanVien,
                    ngayTao,
                    spBuilder.toString(),
                    tongTien,
                    String.format("%,.0f", giamGia),
                    thanhTien,
                    voucher,
                    phuongThuc,
                    tienKhachDua,
                    tienThua
            );

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Chọn nơi lưu hóa đơn");
            fileChooser.setSelectedFile(new File("hoadon_" + orderId + ".txt"));
            int result = fileChooser.showSaveDialog(null);

            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                try (OutputStream os = new FileOutputStream(file)) {
                    os.write(noiDung.getBytes("UTF-8"));
                }
                Desktop.getDesktop().open(file);

                JOptionPane.showMessageDialog(null, "Đã in hóa đơn thành công:\n" + file.getAbsolutePath());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi in hóa đơn: " + ex.getMessage());
        }
    }

}

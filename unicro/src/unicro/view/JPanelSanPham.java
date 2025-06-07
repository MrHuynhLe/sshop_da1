/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unicro.view;

import java.awt.Frame;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;
import unicro.entity.SanPham;
import unicro.entity.SanPhamChiTietResponse;
import unicro.service.SanPhamChiTietRepo;
import unicro.service.SanPhamRepo;
import unicro.service.ThuocTinhRepo;
import java.sql.SQLException;
import java.util.*;

/**
 *
 * @author Admin
 */
public class JPanelSanPham extends javax.swing.JPanel {
 private SanPhamRepo rp = new SanPhamRepo();
    private DefaultTableModel mol = new DefaultTableModel();

    ////spct
    
    private SanPhamChiTietRepo rpspct = new SanPhamChiTietRepo();
    private DefaultTableModel molspct = new DefaultTableModel();
    ////San Pham chi tiet////

    ////thuoc tinh
    private ThuocTinhRepo thuocTinhRepo = new ThuocTinhRepo();
    private DefaultTableModel model;
    private String selectedTable;
    private String matt;

    /**
     * Creates new form JPanelSanPham
     */
    public JPanelSanPham() {
        initComponents();
           this.showDataTableSP(rp.getAll());

//        this.showDataTable(rpspct.getAllSPCT());
       
        tblSP.setRowHeight(25);
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });

        model = new DefaultTableModel(new String[]{"STT", "Mã Thuộc Tính", "Tên Thuộc Tính",}, 0);
        tblThuocTinh.setModel(model);

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(rbtMauSac);
        buttonGroup.add(rbtSize);
        buttonGroup.add(rbtNhaCungCap);
        buttonGroup.add(rbtThuongHieu);
        buttonGroup.add(rbtChatLieu);

        rbtMauSac.addActionListener(e -> {
            if (rbtMauSac.isSelected()) {
                selectedTable = "MAU_SAC";
                matt = "MA_MAU";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });
       
        rbtSize.addActionListener(e -> {
            if (rbtSize.isSelected()) {
                selectedTable = "SIZE";
                matt = "MA_SIZE";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });
        rbtNhaCungCap.addActionListener(e -> {
            if (rbtNhaCungCap.isSelected()) {
                selectedTable = "NHA_CUNG_CAP";
                matt = "MA_NHA_CUNG_CAP";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtChatLieu.addActionListener(e -> {
            if (rbtChatLieu.isSelected()) {
                selectedTable = "CHAT_LIEU";
                matt = "MA_CHAT_LIEU";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });

        rbtThuongHieu.addActionListener(e -> {
            if (rbtThuongHieu.isSelected()) {
                selectedTable = "THUONG_HIEU";
                matt = "MA_THUONG_HIEU";
                hienThiThuocTinhTrenBang(selectedTable);
            }
        });


        // Hiển thị bảng thuộc tính màu sắc mặc định khi mở form
        rbtMauSac.setSelected(true);
        hienThiThuocTinhTrenBang("MAU_SAC");
        selectedTable = "MAU_SAC";
        matt = "MA_MAU";
    }
 public static void main(String[] args) {
        JFrame jFrame = new JFrame("SP");
        JPanelSanPham jbh = new JPanelSanPham();
        jFrame.add(jbh);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.pack();
        jFrame.setVisible(true);
    }

    public void showDataTableSP(ArrayList<SanPham> list) {
        mol = (DefaultTableModel) tblSP.getModel();
        mol.setRowCount(0);
        AtomicInteger index = new AtomicInteger(1);
        list.forEach(s -> mol.addRow(new Object[]{
            index.getAndIncrement(), s.getMaSP(), s.getTenSP(),
            s.getSoLuong(),
            s.isTrangThai() ? "Còn hàng" : "Hết hàng"

        }));

    }

    private void detailSanPham(int index) {
        SanPham sp = rp.getAll().get(index);
        txtMaSP.setText(sp.getMaSP());
        txtTenSP.setText(sp.getTenSP());
        rdoConHang.setSelected(sp.isTrangThai());
        rdoHetHang.setSelected(!sp.isTrangThai());
//        txtIDSP.setText(sp.getId() + "");
        txtMoTa.setText(sp.getMoTa());

    }

    private SanPham getFormData() {
        try {
            SanPham sp = new SanPham();
                    sp.setMaSP(generateProductCode(8));
                    sp.setTenSP(txtTenSP.getText());
                    sp.setTrangThai(rdoConHang.isSelected());
                    sp.setMoTa(txtMoTa.getText());
                    sp.setNgayTao(new Date());
                    return sp;
        } catch (NumberFormatException e) {
            return null;
        }

    }

    private void clearInputFields() {
        txtMaSP.setText("");
        txtTenSP.setText("");

    }

//    private void filterAndShowResults() {
//        String tenSanPham = (String) cbbTen.getSelectedItem();
//        String mauSac = (String) cbbMauSac1.getSelectedItem();
//        String trongLuong = (String) cbbTrongLuong1.getSelectedItem();
//        String doCang = (String) cbbDoCang1.getSelectedItem();
//        String keyword = txtTimKiem1.getText().trim();
//
//        ArrayList<SanPhamChiTietResponse> filteredList = rpspct.getAllSPCT().stream()
//                .filter(spct
//                        -> (tenSanPham.equals("Tất cả") || spct.getTenSP().equalsIgnoreCase(tenSanPham))
//                && (mauSac.equals("Tất cả") || spct.getMauSac().equalsIgnoreCase(mauSac))
//                && (trongLuong.equals("Tất cả") || spct.getTrongLuong().equalsIgnoreCase(trongLuong))
//                && (doCang.equals("Tất cả") || spct.getDoCang().equalsIgnoreCase(doCang))
//                && (keyword.isEmpty() || spct.getTenSP().toLowerCase().contains(keyword.toLowerCase()))
//                )
//                .collect(Collectors.toCollection(ArrayList::new));
//
//        showSanPhamChiTietSearch(filteredList);
//    }
    private void themDuLieuVaoCombobox(javax.swing.JComboBox<String> comboBox, String tenThuocTinh) {
        Set<String> giaTriDuyNhat = new HashSet<>();
        giaTriDuyNhat.add("Tất cả");
        rpspct.getAllSPCT().forEach(spct -> {
            try {
                String giaTri = String.valueOf(spct.getClass().getMethod("get" + Character.toUpperCase(tenThuocTinh.charAt(0)) + tenThuocTinh.substring(1)).invoke(spct));
                giaTriDuyNhat.add(giaTri);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        List<String> sortedList = new ArrayList<>(giaTriDuyNhat);
        int tatCaIndex = sortedList.indexOf("Tất cả");
        if (tatCaIndex != -1) { // Kiểm tra xem "Tất cả" có tồn tại trong danh sách không
            Collections.swap(sortedList, 0, tatCaIndex);
        }
        comboBox.setModel(new javax.swing.DefaultComboBoxModel<>(sortedList.toArray(new String[0])));

    }

    public static String generateProductCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder couponCode = new StringBuilder();
        Random rnd = new Random();
        while (couponCode.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            couponCode.append(chars.charAt(index));
        }
        return couponCode.toString();
    }
    public static String generatePropertiesCode(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder couponCode = new StringBuilder();
        Random rnd = new Random();
        while (couponCode.length() < length) {
            int index = (int) (rnd.nextFloat() * chars.length());
            couponCode.append(chars.charAt(index));
        }
        return couponCode.toString();
    }

    private void detailSanPhamCT(int index) {
        SanPhamChiTietResponse spct = rpspct.getAllSPCT().get(index);

    }


    //// Thuoc Tinh /////
    private void hienThiThuocTinhTrenBang(String tenBang) {
        model.setRowCount(0);
        ArrayList<Object[]> listThuocTinh = thuocTinhRepo.getAllThuocTinh(tenBang);
        for (int i = 0; i < listThuocTinh.size(); i++) {
            Object[] thuocTinh = listThuocTinh.get(i);
            model.addRow(new Object[]{i + 1, thuocTinh[0], thuocTinh[1]});
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtMaSP = new javax.swing.JTextField();
        txtTenSP = new javax.swing.JTextField();
        rdoConHang = new javax.swing.JRadioButton();
        rdoHetHang = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSP = new javax.swing.JTable();
        btnSearch = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        btnLamMoi = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        rbtSize = new javax.swing.JRadioButton();
        rbtChatLieu = new javax.swing.JRadioButton();
        rbtNhaCungCap = new javax.swing.JRadioButton();
        rbtThuongHieu = new javax.swing.JRadioButton();
        rbtMauSac = new javax.swing.JRadioButton();
        txtMaThuocTinh = new javax.swing.JTextField();
        txtTenThuocTinh = new javax.swing.JTextField();
        btnThem2 = new javax.swing.JButton();
        btnSua2 = new javax.swing.JButton();
        btnLamMoi2 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tblThuocTinh = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông Tin Sản Phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Mã Sản Phẩm");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setText("Tên Sản Phẩm");

        txtMaSP.setEditable(false);

        rdoConHang.setText("Còn Hàng");

        rdoHetHang.setText("Hết Hàng");
        rdoHetHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdoHetHangActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Trạng Thái");

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        btnThem.setBackground(new java.awt.Color(0, 204, 51));
        btnThem.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(255, 204, 0));
        btnSua.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setText("Làm Mới");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                        .addComponent(btnThem, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jButton1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(btnThem)
                .addGap(12, 12, 12)
                .addComponent(btnSua)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setText("Mô tả");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(108, 108, 108)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(rdoConHang)
                                    .addGap(18, 18, 18)
                                    .addComponent(rdoHetHang)))))
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addComponent(jLabel7)
                .addGap(12, 12, 12)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(rdoConHang)
                    .addComponent(rdoHetHang))
                .addGap(18, 18, 18))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel4Layout.createSequentialGroup()
                        .addGap(47, 47, 47)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtMaSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel7))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTenSP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh sách sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        jPanel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        tblSP.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "ID Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "Trạng Thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblSP.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSPMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSP);

        btnSearch.setBackground(new java.awt.Color(204, 255, 255));
        btnSearch.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnSearch.setText("Tìm Kiếm");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoiActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(109, 109, 109)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(btnSearch)
                .addGap(541, 541, 541)
                .addComponent(btnLamMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnSearch)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addComponent(btnLamMoi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Sản Phẩm", jPanel1);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        jLabel6.setText("Thuộc Tính");

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        rbtSize.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtSize.setText("Size");
        rbtSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtSizeActionPerformed(evt);
            }
        });

        rbtChatLieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtChatLieu.setText("Chất Liệu");
        rbtChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtChatLieuActionPerformed(evt);
            }
        });

        rbtNhaCungCap.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtNhaCungCap.setText("Nhà Cung Cấp");

        rbtThuongHieu.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtThuongHieu.setText("Thương Hiệu");

        rbtMauSac.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        rbtMauSac.setText("Màu Sắc");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtSize)
                    .addComponent(rbtChatLieu))
                .addGap(57, 57, 57)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(rbtNhaCungCap)
                        .addGap(31, 31, 31)
                        .addComponent(rbtMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rbtThuongHieu))
                .addContainerGap(108, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtSize)
                    .addComponent(rbtNhaCungCap)
                    .addComponent(rbtMauSac))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rbtChatLieu)
                    .addComponent(rbtThuongHieu))
                .addGap(58, 58, 58))
        );

        txtMaThuocTinh.setEditable(false);
        txtMaThuocTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        txtMaThuocTinh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaThuocTinhActionPerformed(evt);
            }
        });

        txtTenThuocTinh.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tên Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        btnThem2.setBackground(new java.awt.Color(0, 204, 102));
        btnThem2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnThem2.setText("Thêm");
        btnThem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThem2ActionPerformed(evt);
            }
        });

        btnSua2.setBackground(new java.awt.Color(255, 204, 0));
        btnSua2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnSua2.setText("Sửa");
        btnSua2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSua2ActionPerformed(evt);
            }
        });

        btnLamMoi2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnLamMoi2.setText("Làm Mới");
        btnLamMoi2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLamMoi2ActionPerformed(evt);
            }
        });

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Danh Sách Thuộc Tính", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc Tính", "Tên Thuộc Tính"
            }
        ));
        tblThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tblThuocTinh);

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 898, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(498, 498, 498)
                .addComponent(jLabel6)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator2)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addGap(290, 430, Short.MAX_VALUE)
                .addComponent(btnThem2)
                .addGap(76, 76, 76)
                .addComponent(btnSua2)
                .addGap(101, 101, 101)
                .addComponent(btnLamMoi2)
                .addGap(313, 313, 313))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 264, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(145, 145, 145))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(txtTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThem2)
                            .addComponent(btnSua2)
                            .addComponent(btnLamMoi2))
                        .addGap(26, 26, 26)))
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Thuộc Tính", jPanel3);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel5.setText("QUẢN LÝ SẢN PHẨM");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(476, 476, 476)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void rdoHetHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdoHetHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rdoHetHangActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        // TODO add your handling code here:
        SanPham newSanPham = getFormData();
        if (newSanPham != null) {
            if (checkFormCreateProduct()) {
                if (rp.add(newSanPham)) {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công!");
                    //                clearInputFields();
                    showDataTableSP(rp.getAll());
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }//GEN-LAST:event_btnThemActionPerformed
  private boolean isDialogOpen = false;
    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        int index = tblSP.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một sản phẩm để sửa.");
            return;
        }
        SanPham sanPham = rp.getAll().get(index);
        SanPham newSanPham = getFormData();
        newSanPham.setId(sanPham.getId());
        if (!txtTenSP.getText().isBlank()) {
            if (rp.update(newSanPham)) {
                showDataTableSP(rp.getAll());
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thành công!");
            } else {
                JOptionPane.showMessageDialog(this, "Cập nhật sản phẩm thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        txtTenSP.setText("");
        txtMaSP.setText("");
        //buttonGroup1.clearSelection();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblSPMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSPMouseClicked

        int index = tblSP.getSelectedRow();
        this.detailSanPham(index);

        String maSP =txtMaSP.getText();
        if (evt.getClickCount() == 2 && !isDialogOpen) {
            isDialogOpen = true;
            Integer idSP = Integer.parseInt(tblSP.getValueAt(index, 0).toString());
            String tenSP = tblSP.getValueAt(index, 2).toString();
            SanPhamChiTietDialog dialogSPCT = new SanPhamChiTietDialog((Frame) SwingUtilities.getWindowAncestor(this), true, idSP, tenSP);
            dialogSPCT.setVisible(true);
            dialogSPCT.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosed(java.awt.event.WindowEvent e) {
                    isDialogOpen = false;
                    showDataTableSP(rp.getAll());
                }
            });
        }
    }//GEN-LAST:event_tblSPMouseClicked

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
        String keyword = txtTimKiem.getText();
        ArrayList<SanPham> ketQuaTimKiem = rp.search(keyword);
        if (ketQuaTimKiem != null) {
            showDataTableSP(ketQuaTimKiem);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm phù hợp.");
        }
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoiActionPerformed
        // TODO add your handling code here:
        txtMaThuocTinh.setText("");
        txtTenThuocTinh.setText("");
    }//GEN-LAST:event_btnLamMoiActionPerformed

    private void rbtSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtSizeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtSizeActionPerformed

    private void rbtChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtChatLieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbtChatLieuActionPerformed

    private void txtMaThuocTinhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaThuocTinhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtMaThuocTinhActionPerformed

    private void btnThem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThem2ActionPerformed
        // TODO add your haString maThuocTinh = txtMaThuocTinh.getText();
        String maThuocTinh = txtMaThuocTinh.getText();
        String tenThuocTinh = txtTenThuocTinh.getText();

        if ( tenThuocTinh.isBlank()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tên thuộc tính.");
            return;
        }

        if(tenThuocTinh.length() > 20){
            JOptionPane.showMessageDialog(this, "Tên thuộc tính không được quá 20 ký tự.");
            return;
        }
        //
        //        for (int i = 0; i < tblThuocTinh.getRowCount(); i++) {
            //            String ma = tblThuocTinh.getValueAt(i, 1).toString();
            //            if (ma.equals(maThuocTinh)) {
                //                JOptionPane.showMessageDialog(this, "Trùng mã!");
                //                return;
                //            }
            //        }

        try {
            if (!selectedTable.equalsIgnoreCase("NHA_CUNG_CAP")) {
                thuocTinhRepo.addThuocTinh(selectedTable, generatePropertiesCode(6), tenThuocTinh);
                JOptionPane.showMessageDialog(this, "Thêm " + selectedTable + " thành công!");
                hienThiThuocTinhTrenBang(selectedTable);
                txtMaThuocTinh.setText("");
                txtTenThuocTinh.setText("");
            } else {
                thuocTinhRepo.addNhaCC(selectedTable, generatePropertiesCode(6), tenThuocTinh, "Việt Nam");
                JOptionPane.showMessageDialog(this, "Thêm " + selectedTable + " thành công!");
                hienThiThuocTinhTrenBang(selectedTable);
                txtMaThuocTinh.setText("");
                txtTenThuocTinh.setText("");
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm " + selectedTable + ": " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnThem2ActionPerformed

    private void btnSua2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSua2ActionPerformed
        // TODO add your handling code here:
        if (tblThuocTinh.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Chọn thuộc tính cần sửa");
        } else {
            String maThuocTinh = txtMaThuocTinh.getText();
            String tenThuocTinh = txtTenThuocTinh.getText();

            if (maThuocTinh.isBlank() || tenThuocTinh.isBlank()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ mã và tên thuộc tính.");
                return;
            }

            try {
                if (!selectedTable.equalsIgnoreCase("NHA_CUNG_CAP")) {
                    thuocTinhRepo.updateThuocTinh(selectedTable, maThuocTinh, matt, tenThuocTinh);
                    hienThiThuocTinhTrenBang(selectedTable);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    thuocTinhRepo.updateNhaCC(selectedTable, maThuocTinh, matt, tenThuocTinh, "Việt Nam");
                    hienThiThuocTinhTrenBang(selectedTable);
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                }

            } catch (SQLException ex) {
                java.util.logging.Logger.getLogger(JPanelSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }

        }

    }//GEN-LAST:event_btnSua2ActionPerformed

    private void btnLamMoi2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLamMoi2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnLamMoi2ActionPerformed

    private void tblThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblThuocTinhMouseClicked
        // TODO add your handling code here:
        txtMaThuocTinh.setText(tblThuocTinh.getValueAt(tblThuocTinh.getSelectedRow(), 1).toString());
        txtTenThuocTinh.setText(tblThuocTinh.getValueAt(tblThuocTinh.getSelectedRow(), 2).toString());
    }//GEN-LAST:event_tblThuocTinhMouseClicked

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        //        Node = new JPanelSanPhamCT();
        //        setView(JpanelRoot);
    }//GEN-LAST:event_jTabbedPane1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLamMoi;
    private javax.swing.JButton btnLamMoi2;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnSua2;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThem2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JRadioButton rbtChatLieu;
    private javax.swing.JRadioButton rbtMauSac;
    private javax.swing.JRadioButton rbtNhaCungCap;
    private javax.swing.JRadioButton rbtSize;
    private javax.swing.JRadioButton rbtThuongHieu;
    private javax.swing.JRadioButton rdoConHang;
    private javax.swing.JRadioButton rdoHetHang;
    private javax.swing.JTable tblSP;
    private javax.swing.JTable tblThuocTinh;
    private javax.swing.JTextField txtMaSP;
    private javax.swing.JTextField txtMaThuocTinh;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenSP;
    private javax.swing.JTextField txtTenThuocTinh;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
private boolean isValidCouponCode(String str) {
        // Biểu thức chính quy cho phép các ký tự chữ và số
        String regex = "^[$,^,&,*,<,>,|,!,;,:,  ,#,'',+,=,{}]+$";
        return str.matches(regex);
    }

    private boolean checkFormCreateProduct() {

        if (txtTenSP.getText().isBlank()) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được để trống!");
            return false;
        }
        if (txtTenSP.getText().length() > 20) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm không được quá 20 ký tự!");
            return false;
        }
        if (!rdoConHang.isSelected() && !rdoHetHang.isSelected()) {
            JOptionPane.showMessageDialog(this, "Chọn trạng thái cho sản phẩm!");
            return false;
        }
        if (isValidCouponCode(txtMaSP.getText())) {
            JOptionPane.showMessageDialog(this, "Tên mã sản phẩm chỉ được chứa chữ và số.");
            return false;
        }
        if (isValidCouponCode(txtTenSP.getText())) {
            JOptionPane.showMessageDialog(this, "Tên sản phẩm chỉ được chứa chữ và số.");
            return false;
        }

        return true;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package unicro.view;

import java.util.ArrayList;

import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import unicro.entity.Session;
import unicro.entity.User;
import java.sql.Date;
import java.util.concurrent.atomic.AtomicInteger;
import unicro.model.UserModel;
import unicro.service.UserService;

/**
 *
 * @author Admin
 */
public class NguoiDung extends javax.swing.JPanel {

    private final UserService ser = new UserService();
    private int idNhanVien;
    ArrayList<UserModel> listUserModel = new ArrayList<UserModel>();
    /**
     * Creates new form NguoiDung
     */
    public NguoiDung() {
        initComponents();
        loadDataToTable();
        if (!"admin".equalsIgnoreCase(Session.currentRole)) {
            cbochucvu.setEnabled(false);

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

        jLabel5 = new javax.swing.JLabel();
        txtquequan = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        txtten = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        cbochucvu = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        txttimkiem = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblnguoidung = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtsdt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        txtDate = new com.toedter.calendar.JDateChooser();
        txtPassword = new javax.swing.JPasswordField();

        setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Thông tin người dùng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("sansserif", 1, 12))); // NOI18N

        jLabel5.setText("Quê quán");

        txtquequan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquequanActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1.setForeground(new java.awt.Color(153, 153, 153));
        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel1.setText("Tên nhân viên");

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton2.setForeground(new java.awt.Color(153, 153, 153));
        jButton2.setText("Mới");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel11.setText("Chức Vụ");

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setForeground(new java.awt.Color(153, 153, 153));
        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        cbochucvu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Nhân Viên", "KhachHang" }));

        jLabel2.setText("Ngày sinh");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setText("Tìm kiếm");

        txttimkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttimkiemActionPerformed(evt);
            }
        });
        txttimkiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txttimkiemKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(txttimkiem, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txttimkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tblnguoidung.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "ID", "Tên người dùng", "User name", "Ngày sinh", "Số điện thoại", "Quê Quán", "Role", "Ngày tạo", "Ngày cập nhập"
            }
        ));
        tblnguoidung.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblnguoidungMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblnguoidung);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 856, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jLabel13.setText("Password");

        jLabel3.setText("Số điện thoại");

        jLabel4.setText("User name");

        txtPassword.setText("unicro1");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbochucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDate, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                            .addComponent(txtten)
                            .addComponent(txtquequan))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 98, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(53, 53, 53)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1))
                                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtPassword)
                                    .addComponent(txtsdt, javax.swing.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton3)))
                        .addContainerGap(12, Short.MAX_VALUE))))
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtten, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(txtsdt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(25, 25, 25)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtquequan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel13)
                            .addComponent(jButton2)
                            .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(cbochucvu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1))
                        .addGap(18, 18, 18)
                        .addComponent(jButton3)
                        .addGap(92, 92, 92)))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2, jButton3});

    }// </editor-fold>//GEN-END:initComponents

    private void txtquequanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquequanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquequanActionPerformed
public UserModel GETMODE() {
        UserModel nguoiDung = new UserModel();

        // Lấy giá trị từ ComboBox để xác định ID Chức vụ
        String chucVu = (String) cbochucvu.getSelectedItem();  // Lấy giá trị từ ComboBox

        // Kiểm tra nếu người dùng không chọn chức vụ hợp lệ
        if (chucVu == null || chucVu.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chức vụ không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        switch (chucVu) {
            case "Admin":
                nguoiDung.setRole_id(1);
                break;
            case "Nhân Viên":
                nguoiDung.setRole_id(2);
                break;
            default:
                nguoiDung.setRole_id(3);  // Mặc định nếu là khách hàng
                break;
        }

        // Lấy dữ liệu từ các trường nhập liệu
        nguoiDung.setFullname(txtten.getText()); // Tên người dùng
        nguoiDung.setUsername(txtUserName.getText());
        java.util.Date utilDate = txtDate.getDate(); 
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        nguoiDung.setDate_of_birth(sqlDate);
        nguoiDung.setPhone_number(txtsdt.getText()); // Số điện thoại
        nguoiDung.setAddress(txtquequan.getText()); // Quê quán
        nguoiDung.setPassword(txtPassword.getText());

//
//        // Kiểm tra trạng thái hoạt động của người dùng
//        if (rdodanglam.isSelected()) {
//            nguoiDung.setTrangThai("Hoạt động");
//        } else if (rdonghiviec.isSelected()) {
//            nguoiDung.setTrangThai("Nghỉ việc");
//        }
        return nguoiDung;
    }

 private boolean containsNumbers(String str) {
        for (char c : str.toCharArray()) {
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

public boolean check(int checked) {
        txtten.setText(txtten.getText().trim());
        if (txtten.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Tên không được để trống");
            return false;
        }
        String tenNguoiDung = txtten.getText().trim(); // Loại bỏ khoảng trắng ở đầu và cuối
//        if (tenNguoiDung.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Không để trống tên");
//            return false;
//        }

        if (tenNguoiDung.length() > 50) {
            JOptionPane.showMessageDialog(this, "Tên không được lớn hơn 50 ký tự");
            return false;
        }
        if (containsNumbers(tenNguoiDung)) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa số");
            return false;
        }
        if (!tenNguoiDung.matches("[a-zA-ZÀ-ỹ\\s]+")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa ký tự đặc biệt");
            return false;
        }

// Kiểm tra ngày sinh
        java.util.Date utilDate = txtDate.getDate();
                if (utilDate != null) {
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        } else {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh");
                }
       
// Kiểm tra số điện thoại
        String sdt = txtsdt.getText().trim(); // Loại bỏ khoảng trắng ở đầu và cuối
        if (sdt.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Không để trống số điện thoại");
            return false;
        } else if (sdt.length() != 10) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải có đúng 10 ký tự");
            return false;
        } else if (!sdt.matches("[0-9]+")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại không được chứa chữ");
            return false;
        } else if (!sdt.startsWith("0")) {
            JOptionPane.showMessageDialog(this, "Số điện thoại phải bắt đầu bằng '0'");
            return false;
        }

// Kiểm tra quê quán
        String queQuan = txtquequan.getText().trim(); // Loại bỏ khoảng trắng ở đầu và cuối
        if (queQuan.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Quê quán không được để trống");
            return false;
        }
        if (checked == 1 && txtPassword.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "mật khẩu không được để trống");
            return false;
        }
        String password = txtPassword.getText().trim();
        if (password.length() > 50) {
            JOptionPane.showMessageDialog(this, "mật khẩu không được lớn hơn 50 ký tự");
            return false;
        }
        return true;
    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
         if (check(1)) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn thêm không ?", "Confirm", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                System.out.println(GETMODE().toString());
                Integer result = ser.ADD(GETMODE());

                if (result != null && result > 0) { // Kiểm tra nếu phương thức ADD thực thi thành công
                    JOptionPane.showMessageDialog(this, "Thêm thành công");
                    loadDataToTable();
                    clean();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm thất bại");
                }
            }
        }

    }//GEN-LAST:event_jButton1ActionPerformed
    public boolean isValidPhoneNumber(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            return false;
        }
        return phone.matches("^0\\d{8}$");
    }
    public boolean clean() {
        txtten.setText("");
        txtPassword.setText("unicro1");
        txtUserName.setText("");
        txtDate.setDate(null);
        txtquequan.setText("");
        txtsdt.setText("");
        return true;
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        txtten.setText("");
        txtPassword.setText("");
        txtUserName.setText("");
        txtDate.setDate(null);
        txtquequan.setText("");
        txtsdt.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    public UserModel getModelSua() {
        UserModel kh = new UserModel();
        kh.setFullname(txtten.getText()); // Tên người dùng
        kh.setUsername(txtUserName.getText());
        java.util.Date utilDate = txtDate.getDate(); 
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        kh.setDate_of_birth(sqlDate);
        kh.setPhone_number(txtsdt.getText()); // Số điện thoại
        kh.setAddress(txtquequan.getText()); // Quê quán
        kh.setPassword(txtPassword.getText());
        kh.setId(idNhanVien);
        String chucVu = (String) cbochucvu.getSelectedItem();  // Lấy giá trị từ ComboBox

        // Kiểm tra nếu người dùng không chọn chức vụ hợp lệ
        if (chucVu == null || chucVu.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chức vụ không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        switch (chucVu) {
            case "Admin":
                kh.setRole_id(1);
                break;
            case "Nhân Viên":
                kh.setRole_id(2);
                break;
            default:
                kh.setRole_id(3);  // Mặc định nếu là khách hàng
                break;
        }

        return kh;
    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed

        try {
            int row = tblnguoidung.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn 1 dòng trong bảng!");
                return;
            }
            if (check(2)) {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn sửa không", "", JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                Integer result = ser.update(getModelSua());
                if (result != null && result > 0) {
                    JOptionPane.showMessageDialog(this, "Sửa thành công");
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa thất bại");
                }
                loadDataToTable();
                clean();
            }

        }

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật người dùng!");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txttimkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttimkiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txttimkiemActionPerformed

    private void txttimkiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txttimkiemKeyReleased
ArrayList<UserModel> listNV = new ArrayList<>();
        String timkiem = txttimkiem.getText().trim(); // Lấy và loại bỏ khoảng trắng dư thừa
        // Kiểm tra nếu có giá trị tìm kiếm
        if (!timkiem.isEmpty()) {
            for (UserModel nguoidung : listUserModel) {
                if (nguoidung.getFullname().toLowerCase().contains(timkiem.toLowerCase())) {
                    listNV.add(nguoidung);
                }
            }
        }
        // Nếu không có giá trị tìm kiếm, tải lại dữ liệu ban đầu
        if (timkiem.equals("")) {
            loadDataToTable();
        } else {
            loadUserTable(listNV); // Hiển thị danh sách tìm được
        }

        clean(); // Xóa các trường tìm kiếm hoặc thực hiện hành động khác sau khi tìm kiếm

    }//GEN-LAST:event_txttimkiemKeyReleased

    private void tblnguoidungMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblnguoidungMouseClicked
        // TODO add your handling code here:
      filldate();

    }//GEN-LAST:event_tblnguoidungMouseClicked

    
    public void filldate() {
        try {
            int index = tblnguoidung.getSelectedRow();

            if (index == -1) {
                return;
            }

            UserModel nguoiDung = listUserModel.get(index);
            idNhanVien = nguoiDung.getId();
            txtten.setText(nguoiDung.getFullname());
            txtDate.setDate(nguoiDung.getDate_of_birth());
            txtsdt.setText(nguoiDung.getPhone_number());
            txtquequan.setText(nguoiDung.getAddress());
            txtUserName.setText(nguoiDung.getUsername());
            txtPassword.setText(nguoiDung.getPassword());
            // Chọn chức vụ tương ứng trong cbbchucvu
            int idChucVu = nguoiDung.getRole_id();
            for (int i = 0; i < cbochucvu.getItemCount(); i++) {
}
            switch (idChucVu) {
                case 1:
                    cbochucvu.setSelectedItem("Admin");
                    break;
                case 2:
                    cbochucvu.setSelectedItem("Nhân Viên");
                    break;
                case 3:
                    cbochucvu.setSelectedItem("KhachHang");
                    break;
                default:
                    cbochucvu.setSelectedItem("Không xác định");
                    break;
            }

        } catch (Exception e) {
            e.printStackTrace(); // In lỗi nếu có
        }
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbochucvu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblnguoidung;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    private javax.swing.JTextField txtquequan;
    private javax.swing.JTextField txtsdt;
    private javax.swing.JTextField txtten;
    private javax.swing.JTextField txttimkiem;
    // End of variables declaration//GEN-END:variables

    public void loadDataToTable() {
        DefaultTableModel model = (DefaultTableModel) tblnguoidung.getModel();
        model.setRowCount(0);
        int stt = 1;
        if ("ADMIN".equalsIgnoreCase(Session.currentRole)) {
            listUserModel = ser.getAll();
        } else {
            UserModel u = ser.getUserById(Session.currentUserId);
            listUserModel.add(u);
        }
        for (UserModel user : listUserModel) {
            model.addRow(new Object[]{
                stt++,
                user.getId(),
                user.getFullname(),
                user.getUsername(),
                user.getDate_of_birth(),
                user.getPhone_number(),
                user.getAddress(),
                user.getRole_name(),
                user.getCreated_at(),
                user.getUpdate_at()
            });
        }
    }

    private boolean validateForm() {
        if (txtUserName.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập!");
            txtUserName.requestFocus();
            return false;
        }

        if (txtten.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập họ tên!");
            txtten.requestFocus();
            return false;
        }

        if (txtPassword.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu!");
            txtPassword.requestFocus();
            return false;
        }

        if (txtsdt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!");
            txtsdt.requestFocus();
            return false;
        }

        if (txtDate.getDate() == null) {
            JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày sinh!");
            return false;
        }
        String sdt = txtsdt.getText().trim();
        if (!sdt.matches("0\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Số điện thoại phải bắt đầu từ 0 và gồm 9 chữ số");
            txtsdt.requestFocus();
            return false;
        }
        return true;
    }

    private void loadUserTable(List<UserModel> result) {
          DefaultTableModel model = (DefaultTableModel) tblnguoidung.getModel();
    model.setRowCount(0);

    AtomicInteger index = new AtomicInteger(1); 

    for (UserModel user : result) {
        model.addRow(new Object[]{
            index.getAndIncrement(),
            user.getId(),
            user.getFullname(),
            user.getUsername(),
            user.getDate_of_birth(),
            user.getPhone_number(),
            user.getAddress(),
            user.getRole_name(),
            user.getCreated_at(),
            user.getUpdate_at()
        });
    }
    }

}

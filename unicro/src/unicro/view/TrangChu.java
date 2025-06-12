/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package unicro.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import unicro.entity.Session;
import unicro.view.KhuyenMai;

/**
 *
 * @author Admin
 */
public class TrangChu extends javax.swing.JFrame {

    /**
     * Creates new form TrangChu
     */
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private Session s;

    public TrangChu() {
        initComponents();
        setTitle("Phần mềm quản lý bán hàng");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 600);
        setLocationRelativeTo(null);

      

        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

          JPanel userInfoPanel = new JPanel();
        userInfoPanel.setLayout(new BoxLayout(userInfoPanel, BoxLayout.Y_AXIS));
        userInfoPanel.setBackground(Color.WHITE);
        userInfoPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel lblUser = new JLabel("" + Session.currentFullname);
        JLabel lblRole = new JLabel("    " + Session.currentRole);
        lblUser.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblRole.setFont(new Font("Segoe UI", Font.BOLD, 13));
        lblUser.setForeground(Color.DARK_GRAY);
        lblRole.setForeground(Color.GRAY);

        userInfoPanel.add(lblUser);
        userInfoPanel.add(lblRole);
        userInfoPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        menuPanel.add(userInfoPanel);
        Font menuFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color btnColor = new Color(52, 152, 219);
        Color btnText = Color.WHITE;

        String[] menuNames = {"Bán Hàng", "Quản lý sản phẩm", "Thống kê", "Khuyến mãi", "Quản lý nhân viên", "Quản lý hoá đơn", "Đăng xuất"};
        JButton[] menuButtons = new JButton[menuNames.length];
        for (int i = 0; i < menuNames.length; i++) {
            JButton btn = new JButton(menuNames[i]);
            btn.setFocusPainted(false);
            btn.setFont(menuFont);
            btn.setBackground(btnColor);
            btn.setForeground(btnText);
            btn.setMaximumSize(new Dimension(160, 40));
            btn.setAlignmentX(Component.CENTER_ALIGNMENT);
            menuButtons[i] = btn;
            menuPanel.add(btn);
            menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        }

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        mainPanel.add(new BanHang(), "BanHang");
        mainPanel.add(new JPanelSanPham(), "QLSP");
        mainPanel.add(new ThongKe(), "ThongKe");
        mainPanel.add(new KhuyenMai(), "KhuyenMai");
        mainPanel.add(new NguoiDung(), "NguoiDung");

        menuButtons[0].addActionListener(e -> cardLayout.show(mainPanel, "BanHang"));
        menuButtons[1].addActionListener(e -> cardLayout.show(mainPanel, "QLSP"));
        menuButtons[2].addActionListener(e -> cardLayout.show(mainPanel, "ThongKe"));
        menuButtons[3].addActionListener(e -> cardLayout.show(mainPanel, "KhuyenMai"));
        menuButtons[4].addActionListener(e -> cardLayout.show(mainPanel, "NguoiDung"));
        menuButtons[5].addActionListener(e -> cardLayout.show(mainPanel, "HoaDon"));
        menuButtons[6].addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn đăng xuất?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose();
                new Dang_Nhap(this, rootPaneCheckingEnabled).setVisible(true);
            }
        });

        JLabel titleLabel = new JLabel("PHẦN MỀM QUẢN LÝ BÁN HÀNG", JLabel.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(41, 128, 185));

        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setBackground(Color.WHITE);
        titlePanel.add(titleLabel, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(titlePanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.CENTER);

        if ("Nhân Viên".equalsIgnoreCase(Session.currentRole)) {

            menuButtons[1].setEnabled(false);
            menuButtons[3].setEnabled(false);
            menuButtons[4].setEnabled(false);
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        EventQueue.invokeLater(() -> new TrangChu().setVisible(true));

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}

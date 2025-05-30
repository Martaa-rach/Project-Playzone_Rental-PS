/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectelis;

import projectelis.Koneksi_db;
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import projectelis.Koneksi_db;
import projectelis.SessionLogin;

/**
 *
 * @author USER
 */
public class login extends javax.swing.JFrame {

    PreparedStatement st;
    ResultSet rs;
    Connection con;
    Koneksi_db DB = new Koneksi_db();
    
    public login() {
        this.setUndecorated(true); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        RF.requestFocusInWindow();
        DB.configDB();
        con = DB.koneksi;
        
    }
    public static String activeID_akun;
    public static String activeusername;
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        username_s = new javax.swing.JTextField();
        login_b = new javax.swing.JButton();
        sign_in = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        RF = new javax.swing.JTextField();
        Tampilkanpassword = new javax.swing.JCheckBox();
        password_s = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                FormWindowOpened(evt);
            }
        });

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        username_s.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        username_s.setForeground(new java.awt.Color(255, 255, 255));
        username_s.setBorder(null);
        username_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                username_sActionPerformed(evt);
            }
        });
        jPanel1.add(username_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 400, 490, 60));

        login_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn login.png"))); // NOI18N
        login_b.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk login.png"))); // NOI18N
        login_b.setLabel("");
        login_b.setContentAreaFilled(false);
        login_b.setBorderPainted(false);
        login_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                login_bActionPerformed(evt);
            }
        });
        jPanel1.add(login_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 590, 620, 80));

        sign_in.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn sign up.png"))); // NOI18N
        sign_in.setContentAreaFilled(false);
        sign_in.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk sign up.png"))); // NOI18N
        sign_in.setLabel("");
        sign_in.setContentAreaFilled(false);
        sign_in.setBorderPainted(false);
        sign_in.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sign_inActionPerformed(evt);
            }
        });
        jPanel1.add(sign_in, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 677, 130, 40));

        jButton1.setLabel("");
        jButton1.setContentAreaFilled(false);
        jButton1.setBorderPainted(false);
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn exit login.png"))); // NOI18N
        jButton1.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk exit login.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(-5, 685, 280, -1));

        RF.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        RF.setForeground(new java.awt.Color(255, 255, 255));
        RF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RFActionPerformed(evt);
            }
        });
        RF.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                RFKeyTyped(evt);
            }
        });
        jPanel1.add(RF, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 310, 490, 40));

        Tampilkanpassword.setBorder(null);
        Tampilkanpassword.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/PWno.png")));
        Tampilkanpassword.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/PW.png")));
        Tampilkanpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TampilkanpasswordActionPerformed(evt);
            }
        });
        jPanel1.add(Tampilkanpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(1250, 487, 60, 60));

        password_s.setFont(new java.awt.Font("Calibri", 1, 28)); // NOI18N
        password_s.setForeground(new java.awt.Color(255, 255, 255));
        password_s.setBorder(null);
        password_s.setCaretColor(new java.awt.Color(255, 255, 255));
        jPanel1.add(password_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 506, 450, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bg no login.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void FormWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FormWindowOpened
        username_s.setOpaque(false);  // Membuat JTextField transparan
        username_s.setBackground(new Color(0,0,0,0)); // Membuat background benar-benar transparan
        username_s.setBorder(null);   // Menghapus border
        username_s.setForeground(Color.WHITE);  // Warna teks menjadi putih
        username_s.setCaretColor(Color.WHITE);

        RF.setOpaque(false);  // Membuat JTextField transparan
        RF.setBackground(new Color(0,0,0,0)); // Membuat background benar-benar transparan
        RF.setBorder(null);   // Menghapus border
        RF.setForeground(Color.WHITE);  // Warna teks menjadi putih
        RF.setCaretColor(Color.WHITE);

        password_s.setOpaque(false);  // Membuat JTextField transparan
        password_s.setBackground(new Color(0,0,0,0)); // Membuat background benar-benar transparan
        password_s.setBorder(null);   // Menghapus border
        password_s.setForeground(Color.WHITE);  // Warna teks menjadi putih
        password_s.setCaretColor(Color.WHITE);
     
    }//GEN-LAST:event_FormWindowOpened

    private void username_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_username_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_username_sActionPerformed

    private void login_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_login_bActionPerformed
        try {
            String username = username_s.getText(); // ganti sesuai nama komponen kamu
        String password = password_s.getText(); // ganti sesuai nama komponen kamu

        String sql = "SELECT * FROM akun WHERE username = ? AND password = ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, username);
        pst.setString(2, password);
        ResultSet rs = pst.executeQuery();
        
            if (rs.next()) {
                String level = rs.getString("level");
                
                // Simpan ke SessionLogin
                SessionLogin.idAkun = rs.getString("ID_akun");
                SessionLogin.username = rs.getString("username");
                SessionLogin.level = level;
                
                // 🔍 Ambil ID_pegawai berdasarkan ID_akun
                String sqlPegawai = "SELECT * FROM pegawai WHERE ID_akun = ?";
                PreparedStatement stPegawai = con.prepareStatement(sqlPegawai);
                stPegawai.setString(1, SessionLogin.idAkun);
                ResultSet rsPegawai = stPegawai.executeQuery();
                
                if (rsPegawai.next()) {
                    String idPegawai = rsPegawai.getString("ID_pegawai");
                    String namaPegawai = rsPegawai.getString("Nama");
                    
                    // Simpan ke session
                    SessionLogin.setIDPegawai(idPegawai);
                    SessionLogin.setNamaPegawai(namaPegawai);
                    } 

                
                /*if ("Admin".equalsIgnoreCase(level)) {
                    new homeAdmin().setVisible(true);
                } else if ("Pegawai".equalsIgnoreCase(level)) {
                    new homePegawai().setVisible(true);
                }*/
                
                dispose();
                
                } else {
                JOptionPane.showMessageDialog(null, "Akun tidak ditemukan. Periksa kembali username dan password Anda.");
                }
        } catch (SQLException ex) {
            Logger.getLogger(login.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }//GEN-LAST:event_login_bActionPerformed

    private void sign_inActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sign_inActionPerformed
        register sign_in = new register();
        sign_in.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_sign_inActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        System.exit(0); 
    }//GEN-LAST:event_jButton1ActionPerformed

    private void RFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RFActionPerformed
                             
    String idRFID = RF.getText();
    
    try {
        // Ganti sesuai koneksi database kamu
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playzonee", "root", "");
        String sql = "SELECT username, password FROM akun WHERE ID_kartuRFID = ?";
        PreparedStatement pst = conn.prepareStatement(sql);
        pst.setString(1, idRFID);
        
        ResultSet rs = pst.executeQuery();
        
        if (rs.next()) {
            String username = rs.getString("username");
            String password = rs.getString("password");
            
            username_s.setText(username);
            password_s.setText(password);
            
            // Trigger tombol login
            login_b.doClick(); // Menekan tombol secara otomatis
        } else {
            JOptionPane.showMessageDialog(this, "ID RFID tidak dikenali.");
        }
        
        rs.close();
        pst.close();
        conn.close();
        
    } catch (Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
    }

    RF.setText("");
    RF.requestFocusInWindow();


    }//GEN-LAST:event_RFActionPerformed

    private void RFKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_RFKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // Cegah input selain angka
        }
    }//GEN-LAST:event_RFKeyTyped

    private void TampilkanpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TampilkanpasswordActionPerformed
        if (Tampilkanpassword.isSelected()) {
    password_s.setEchoChar((char) 0);
} else {
    password_s.setEchoChar('*');
}

    }//GEN-LAST:event_TampilkanpasswordActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField RF;
    private javax.swing.JCheckBox Tampilkanpassword;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton login_b;
    private javax.swing.JPasswordField password_s;
    private javax.swing.JButton sign_in;
    private javax.swing.JTextField username_s;
    // End of variables declaration//GEN-END:variables
}

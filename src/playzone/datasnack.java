/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package playzone;
import java.awt.Color;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class datasnack extends javax.swing.JFrame {
     public Statement st;
    public PreparedStatement ps;
    public ResultSet rs;
    java.sql.Connection cobakonek=playzone.Koneksi_db.configDB(); //penting
    private boolean editMode = false;

    private Object Color;
    private Object btnUpdate;

    public datasnack() {
        this.setUndecorated(true); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
      //  KodeSnack.requestFocusInWindow();
        showdata();
        updateEditModeUI();

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Harga = new javax.swing.JTextField();
        Stock = new javax.swing.JTextField();
        NamaSnack = new javax.swing.JTextField();
        KodeSnack = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelsnack = new javax.swing.JTable();
        logout = new javax.swing.JButton();
        home = new javax.swing.JButton();
        employee = new javax.swing.JButton();
        jButtonPS = new javax.swing.JButton();
        jButtonMode = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        TglMasukBarang = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1375, 770));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Harga.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        Harga.setForeground(new java.awt.Color(255, 255, 255));
        Harga.setBorder(null);
        Harga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HargaActionPerformed(evt);
            }
        });
        Harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                HargaKeyTyped(evt);
            }
        });
        getContentPane().add(Harga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 604, 290, 50));

        Stock.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        Stock.setForeground(new java.awt.Color(255, 255, 255));
        Stock.setBorder(null);
        Stock.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                StockActionPerformed(evt);
            }
        });
        Stock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                StockKeyTyped(evt);
            }
        });
        getContentPane().add(Stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 542, 290, 50));

        NamaSnack.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        NamaSnack.setForeground(new java.awt.Color(255, 255, 255));
        NamaSnack.setBorder(null);
        NamaSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NamaSnackActionPerformed(evt);
            }
        });
        getContentPane().add(NamaSnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 574, 400, 50));

        KodeSnack.setFont(new java.awt.Font("Calibri", 1, 30)); // NOI18N
        KodeSnack.setForeground(new java.awt.Color(255, 255, 255));
        KodeSnack.setBorder(null);
        KodeSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KodeSnackActionPerformed(evt);
            }
        });
        KodeSnack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KodeSnackKeyPressed(evt);
            }
        });
        getContentPane().add(KodeSnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 510, 400, 50));

        save.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn save data.png"))); // NOI18N
        save.setContentAreaFilled(false);
        save.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk save data.png"))); // NOI18N
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(644, 705, 390, 50));

        tabelsnack.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode Snack", "Nama Snack", "Tgl Masuk Barang", "Stock", "Harga"
            }
        ));
        tabelsnack.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelsnackMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelsnack);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 80, 1020, 400));

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi logout.png"))); // NOI18N
        logout.setContentAreaFilled(false);
        logout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi logout.png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        getContentPane().add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 670, 220, 80));

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi home.png"))); // NOI18N
        home.setContentAreaFilled(false);
        home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi home.png"))); // NOI18N
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        getContentPane().add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 240, 80));

        employee.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Employee.png"))); // NOI18N
        employee.setContentAreaFilled(false);
        employee.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Employee.png"))); // NOI18N
        employee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                employeeActionPerformed(evt);
            }
        });
        getContentPane().add(employee, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 370, 180, 40));

        jButtonPS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi PlayStation.png"))); // NOI18N
        jButtonPS.setContentAreaFilled(false);
        jButtonPS.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi PlayStation.png"))); // NOI18N
        jButtonPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPSActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 440, 180, 40));

        jButtonMode.setContentAreaFilled(false);
        jButtonMode.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn edit.png"))); // NOI18N
        jButtonMode.setContentAreaFilled(false);
        jButtonMode.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk edit.png"))); // NOI18N
        jButtonMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(318, 705, 310, 50));

        jButtonDelete.setContentAreaFilled(false);
        jButtonDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn delete.png"))); // NOI18N
        jButtonDelete.setContentAreaFilled(false);
        jButtonDelete.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk delete.png"))); // NOI18N
        jButtonDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeleteActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonDelete, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 706, 310, -1));
        getContentPane().add(TglMasukBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 640, 330, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bg no Snack Inventory.png"))); // NOI18N
        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1430, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KodeSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KodeSnackActionPerformed

    }//GEN-LAST:event_KodeSnackActionPerformed

    private void NamaSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NamaSnackActionPerformed
      
    }//GEN-LAST:event_NamaSnackActionPerformed

    private void StockActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_StockActionPerformed

    }//GEN-LAST:event_StockActionPerformed

    private void HargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HargaActionPerformed

    }//GEN-LAST:event_HargaActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
    DefaultTableModel model = (DefaultTableModel) tabelsnack.getModel();

    String kode = KodeSnack.getText();
    String nama = NamaSnack.getText();
    String tgl = new SimpleDateFormat("yyyy-MM-dd").format(TglMasukBarang.getDate());
    String stock = Stock.getText();
    String harga = Harga.getText();

    int selectedRow = tabelsnack.getSelectedRow(); // Ambil baris yang sedang dipilih

    if (selectedRow != -1) {
        // Update data di database
        try {
            String sql = "UPDATE snack SET Nama = ?, tgl_masukbarang = ?, Stok = ?, Harga = ? WHERE Kode_snack = ?";
            ps = cobakonek.prepareStatement(sql);
            ps.setString(1, nama);
            ps.setString(2, tgl);
            ps.setInt(3, Integer.parseInt(stock));
            ps.setInt(4, Integer.parseInt(harga));
            ps.setString(5, kode);
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal mengupdate database: " + e.getMessage());
        }

        // Update data di tabel (GUI)
        model.setValueAt(kode, selectedRow, 0);
        model.setValueAt(nama, selectedRow, 1);
        model.setValueAt(tgl, selectedRow, 2);
        model.setValueAt(stock, selectedRow, 3);
        model.setValueAt(harga, selectedRow, 4);
    } else {
        // Tambah data baru
        model.addRow(new Object[]{kode, nama, tgl, stock, harga});

        // Simpan juga ke database
        try {
            String sql = "INSERT INTO snack (Kode_snack, Nama, tgl_masukbarang, Stok, Harga) VALUES (?, ?, ?, ?, ?)";
            ps = cobakonek.prepareStatement(sql);
            ps.setString(1, kode);
            ps.setString(2, nama);
            ps.setString(3, tgl);
            ps.setInt(4, Integer.parseInt(stock));
            ps.setInt(5, Integer.parseInt(harga));
            ps.executeUpdate();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan ke database: " + e.getMessage());
        }
    }

    clearTextField();
    tabelsnack.clearSelection(); // Bersihkan seleksi di tabel
    showdata(); // refresh data dari database
    editMode = false;
    updateEditModeUI();


    }//GEN-LAST:event_saveActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
 
        // Menampilkan dialog konfirmasi
        /*int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        
        // Jika memilih YES, logout
        if (confirm == JOptionPane.YES_OPTION) {
            new homeAdmin().setVisible(true);
            this.dispose();     
        } else {
            System.out.println("Logout dibatalkan.");
        }*/
    
    }//GEN-LAST:event_logoutActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        KodeSnack.setOpaque(false);
        KodeSnack.setBorder(null);
        KodeSnack.setBackground(new java.awt.Color(0, 0, 0, 0));
        KodeSnack.setForeground(new Color(255, 255, 255));
        
        NamaSnack.setOpaque(false);
        NamaSnack.setBorder(null);
        NamaSnack.setBackground(new java.awt.Color(0, 0, 0, 0));
        NamaSnack.setForeground(new Color(255, 255, 255));
        
        Stock.setOpaque(false);
        Stock.setBorder(null);
        Stock.setBackground(new java.awt.Color(0, 0, 0, 0));
        Stock.setForeground(new Color(255, 255, 255));
        
        Harga.setOpaque(false);
        Harga.setBorder(null);
        Harga.setBackground(new java.awt.Color(0, 0, 0, 0));
        Harga.setForeground(new Color(255, 255, 255));
        
        save.setOpaque(false);
        save.setBorder(null);
        save.setBackground(new java.awt.Color(0, 0, 0, 0));
        save.setForeground(new Color(255, 255, 255));
        
        logout.setOpaque(false);
        logout.setBorder(null);
        logout.setBackground(new java.awt.Color(0, 0, 0, 0));
        logout.setForeground(new Color(255, 255, 255));
        
        employee.setOpaque(false);
        employee.setBorder(null);
        employee.setBackground(new java.awt.Color(0, 0, 0, 0));
        employee.setForeground(new Color(255, 255, 255));
        
        jButtonPS.setOpaque(false);
        jButtonPS.setBorder(null);
        jButtonPS.setBackground(new java.awt.Color(0, 0, 0, 0));
        jButtonPS.setForeground(new Color(255, 255, 255));
        

        
        home.setOpaque(false);
        home.setBorder(null);
        home.setBackground(new java.awt.Color(0, 0, 0, 0));
        home.setForeground(new Color(255, 255, 255));
    }//GEN-LAST:event_formWindowOpened

    private void employeeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_employeeActionPerformed
        datapegawai nw_transaksi = new datapegawai();
        nw_transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_employeeActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
   /* new homeAdmin().setVisible(true);
    this.dispose(); */   
    }//GEN-LAST:event_homeActionPerformed

    private void tabelsnackMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelsnackMouseClicked

        int row = tabelsnack.getSelectedRow();

        KodeSnack.setText(tabelsnack.getValueAt(row, 0).toString());
        NamaSnack.setText(tabelsnack.getValueAt(row, 1).toString());

        try {
            String tanggal = tabelsnack.getValueAt(row, 2).toString();
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            Date date = sdf.parse(tanggal);
            TglMasukBarang.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Stock.setText(tabelsnack.getValueAt(row, 3).toString());
        Harga.setText(tabelsnack.getValueAt(row, 4).toString());
        
        editMode = true;
        updateEditModeUI();

    }//GEN-LAST:event_tabelsnackMouseClicked

    private void jButtonPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPSActionPerformed
       /* dataPS_admin nw_transaksi = new dataPS_admin();
        nw_transaksi.setVisible(true);
        this.dispose(); */  
    }//GEN-LAST:event_jButtonPSActionPerformed

    private void KodeSnackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KodeSnackKeyPressed
       if(evt.getKeyCode() == KeyEvent.VK_ENTER){
        String kode = KodeSnack.getText();
            // Proses pencarian snack berdasarkan kode
            System.out.println("Kode yang discan: " + kode);
        }
        
    }//GEN-LAST:event_KodeSnackKeyPressed

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeleteActionPerformed
        int selectedRow = tabelsnack.getSelectedRow();
        if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Pilih data yang ingin dihapus!");
        return;
        }

        String ID = (String) tabelsnack.getValueAt(selectedRow, 0); // Kolom 1 adalah ID_playstation

        // Konfirmasi sebelum menghapus
        int confirm = JOptionPane.showConfirmDialog(null, "Apakah Anda yakin ingin menghapus data ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM snack WHERE Kode_snack = ?";

            try {
                PreparedStatement stat = cobakonek.prepareStatement(sql);
                stat.setString(1, ID); 
                stat.executeUpdate();

                JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
                clearTextField();
                showdata();
                editMode = false;
                selectedRow = -1;
                updateEditModeUI();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error saat menghapus data: " + e.getMessage());
            }
        } 
    }//GEN-LAST:event_jButtonDeleteActionPerformed

    private void jButtonModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModeActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this,
            "Apakah Anda yakin ingin membatalkan pengeditan?\nPerubahan yang belum disimpan akan hilang!",
            "Konfirmasi", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            clearTextField(); // method kamu untuk mengosongkan input
            editMode = false;
            updateEditModeUI();
        }
    }//GEN-LAST:event_jButtonModeActionPerformed

    private void HargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HargaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); 
        }
    }//GEN-LAST:event_HargaKeyTyped

    private void StockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_StockKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); 
        }
    }//GEN-LAST:event_StockKeyTyped

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
            java.util.logging.Logger.getLogger(datasnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(datasnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(datasnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(datasnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new datasnack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField Harga;
    private javax.swing.JTextField KodeSnack;
    private javax.swing.JTextField NamaSnack;
    private javax.swing.JTextField Stock;
    private com.toedter.calendar.JDateChooser TglMasukBarang;
    private javax.swing.JButton employee;
    private javax.swing.JButton home;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonMode;
    private javax.swing.JButton jButtonPS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JButton save;
    private javax.swing.JTable tabelsnack;
    // End of variables declaration//GEN-END:variables

     private void showdata() {
        DefaultTableModel model = (DefaultTableModel) tabelsnack.getModel();
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM snack"; //WHERE ID_Pegawai = ? ---- ini kalau difilter pake sesi login aktif
            ps = cobakonek.prepareStatement(sql); //ps.setInt(1, SessionLogin.getID_pegawai()); // Panggil dari session
            rs = ps.executeQuery();

            while (rs.next()) {
                
                String kodesnack = rs.getString("Kode_snack");
                String namasnack = rs.getString("Nama");
                int harga = rs.getInt("Harga");
 
                String TglMasukBarang = rs.getString("tgl_masukbarang");
                int Stock = rs.getInt("Stok");

                Object[] Rowdata = {kodesnack, namasnack, TglMasukBarang, Stock, harga};
                model.addRow(Rowdata);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void clearTextField() {
        // Reset input field
        KodeSnack.setText("");
        NamaSnack.setText("");
        TglMasukBarang.setDate(null);
        Stock.setText("");
        Harga.setText("");    
    }
    
    private void updateEditModeUI() {
    if (editMode) {
        jButtonMode.setEnabled(true);
        jButtonDelete.setEnabled(true);
    } else {
        jButtonMode.setEnabled(false); 
        jButtonDelete.setEnabled(false);
    }
}

    
}

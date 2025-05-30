/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package playzone;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author OwOwO
 */
public class LprnSewa extends javax.swing.JFrame {
   public Statement st;
   public PreparedStatement ps;
   public ResultSet rs;
   Connection cobakonek=playzone.Koneksi_db.configDB(); //penting, koneksi untuk menyambungkan ke database
    
    //untuk update data
    /*    boolean editMode = false;
        int selectedID = -1;    */
  
    /**
     * Creates new form LprnSewa
     */
   public LprnSewa() {
       this.setUndecorated(true); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    initComponents();
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonHome = new javax.swing.JButton();
        jButtonGameRent = new javax.swing.JButton();
        jButtonOnSpotPlay = new javax.swing.JButton();
        jButtonSnack = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        btnExportExcel = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi home.png"))); // NOI18N
        jButtonHome.setContentAreaFilled(false);
        jButtonHome.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi home.png"))); // NOI18N
        jButtonHome.setLabel("");
        jButtonHome.setContentAreaFilled(false);
        jButtonHome.setBorderPainted(false);
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 250, 80));

        jButtonGameRent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Game Rent.png"))); // NOI18N
        jButtonGameRent.setContentAreaFilled(false);
        jButtonGameRent.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Game Rent.png"))); // NOI18N
        jButtonGameRent.setLabel("");
        jButtonGameRent.setContentAreaFilled(false);
        jButtonGameRent.setBorderPainted(false);
        jButtonGameRent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGameRentActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonGameRent, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 330, 190, 40));

        jButtonOnSpotPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi ON-Spot Play.png"))); // NOI18N
        jButtonOnSpotPlay.setContentAreaFilled(false);
        jButtonOnSpotPlay.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi ON-Spot Play.png"))); // NOI18N
        jButtonOnSpotPlay.setLabel("");
        jButtonOnSpotPlay.setContentAreaFilled(false);
        jButtonOnSpotPlay.setBorderPainted(false);
        jButtonOnSpotPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnSpotPlayActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOnSpotPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 390, 270, 60));

        jButtonSnack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Snack.png"))); // NOI18N
        jButtonSnack.setContentAreaFilled(false);
        jButtonSnack.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Snack.png"))); // NOI18N
        jButtonSnack.setLabel("");
        jButtonSnack.setContentAreaFilled(false);
        jButtonSnack.setBorderPainted(false);
        jButtonSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSnackActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 460, 130, 50));

        jButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi logout.png"))); // NOI18N
        jButtonLogout.setContentAreaFilled(false);
        jButtonLogout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi logout.png"))); // NOI18N
        jButtonLogout.setLabel("");
        jButtonLogout.setContentAreaFilled(false);
        jButtonLogout.setBorderPainted(false);
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 230, 100));

        jTable1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID Sewa", "ID Pegawai", "Nama Penyewa", "Alamat Penyewa", "Barang", "ID Playstation", "Tgl Sewa", "Tgl Kembali", "Durasi", "KTP", "Surat Pernyataan", "Total Biaya", "Bayar", "Kembalian", "Status"
            }
        ));
        jTable1.setShowGrid(true);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 180, 1000, 490));

        jComboBox1.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Game Rent", "ON-SPOT Play", "Snack" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 33, 290, 40));

        jComboBox2.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 33, 220, 40));

        btnExportExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn export.png"))); // NOI18N
        btnExportExcel.setBorder(null);
        btnExportExcel.setContentAreaFilled(false);
        btnExportExcel.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk export.png"))); // NOI18N
        btnExportExcel.setContentAreaFilled(false);
        btnExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportExcelActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportExcel, new org.netbeans.lib.awtextra.AbsoluteConstraints(686, 695, 320, 60));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bg no Reports Game Rent.png"))); // NOI18N
        jLabel1.addHierarchyListener(new java.awt.event.HierarchyListener() {
            public void hierarchyChanged(java.awt.event.HierarchyEvent evt) {
                jLabel1HierarchyChanged(evt);
            }
        });
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1HierarchyChanged(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel1HierarchyChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1HierarchyChanged

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
    /*    String selectedItem = jComboBox1.getSelectedItem().toString();

      if (selectedItem.equalsIgnoreCase("On-Spot Play")) {
        LprnMainDitempat nw_transaksi = new LprnMainDitempat();
        nw_transaksi.setVisible(true);
        this.dispose();
    } else if (selectedItem.equalsIgnoreCase("Snack")) {
        reportsnack nw_transaksi = new reportsnack();
        nw_transaksi.setVisible(true);
        this.dispose();
    }*/
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
      /*  homePegawai nw_transaksi = new homePegawai();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
     /*   int confirm = JOptionPane.showConfirmDialog(null, "Yakin ingin logout?", "Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            login nw_transaksi = new login();
            nw_transaksi.setVisible(true);
            this.dispose();
        } else {
            System.out.println("Logout dibatalkan.");
        }*/
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSnackActionPerformed
       /* transaksiSnack nw_transaksi = new transaksiSnack();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButtonSnackActionPerformed

    private void jButtonOnSpotPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOnSpotPlayActionPerformed
      /* transaksiMainDitempat nw_transaksi = new transaksiMainDitempat();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButtonOnSpotPlayActionPerformed
                                              

    private void jButtonGameRentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGameRentActionPerformed
        TrSewaPegawai nw_transaksi = new TrSewaPegawai();
        nw_transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonGameRentActionPerformed

    private int getMonthFromString(String monthName) {
        switch(monthName.toLowerCase()) {
            case "januari": return 1;
            case "februari": return 2;
            case "maret": return 3;
            case "april": return 4;
            case "mei": return 5;
            case "juni": return 6;
            case "juli": return 7;
            case "agustus": return 8;
            case "september": return 9;
            case "oktober": return 10;
            case "november": return 11;
            case "desember": return 12;
            default: return 0; // jika tidak cocok
        }
    }
    
    private void loadDataToTable(String sql, int param){
        DefaultTableModel model = new DefaultTableModel(); // private class yang berfungsi untuk menyediakan colom di swing tabel
        model.addColumn("ID sewa");
        model.addColumn("ID Pegawai");
        model.addColumn("Alamat penyewa");
        model.addColumn("Barang");
        model.addColumn("ID playstation");
        model.addColumn("Tgl sewa");
        model.addColumn("Tgl kembali");
        model.addColumn("Durasi"); 
        model.addColumn("KTP"); 
        model.addColumn("Surat pernyataan"); 
        model.addColumn("Total biaya"); 
        model.addColumn("Bayar");
        model.addColumn("Kembalian");     
        model.addColumn("Status"); 


        try {
           ps = cobakonek.prepareStatement(sql);  // fungsi Read dalam CRUD
           ps.setInt(1, param);
           rs = ps.executeQuery();
           
           while(rs.next()) {             // Kode ini digunakan untuk mengambil data pada database yang akan ditampilkan di swing tabel
            model.addRow(new Object[]{ 
            rs.getString("ID_sewa"),  
            rs.getString("ID_pegawai"),
            rs.getString("Alamat"),
            rs.getString("Barang_sewa"),
            rs.getString("ID_playstation"),
            rs.getDate("tgl_sewa"),
            rs.getDate("tgl_kembali"),
            rs.getInt("Durasi_sewa"),
            rs.getString("KTP"),
            rs.getString("Surat_pernyataan"),
            rs.getDouble("Total_biaya"),  
            rs.getDouble("Bayar"),
            rs.getDouble("Kembalian"),        
            rs.getString("status")




           });
           }
           jTable1.setModel(model); // nantinya akan diabil oleh variabel tabel dan ditampilkan
           
        } catch (SQLException e) {   // menampilkan error bila terjadi kesalahan supaya lebih mudah jika akan diperbaiki
           JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());   
        }
    }
    
        private void loadDataToTable(String sql) {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID sewa");
        model.addColumn("ID Pegawai");
        model.addColumn("Alamat penyewa");
        model.addColumn("Barang");
        model.addColumn("ID playstation");
        model.addColumn("Tgl sewa");
        model.addColumn("Tgl kembali");
        model.addColumn("Durasi"); 
        model.addColumn("KTP"); 
        model.addColumn("Surat pernyataan"); 
        model.addColumn("Total biaya"); 
        model.addColumn("Bayar");
        model.addColumn("Kembalian"); 
        model.addColumn("Status"); 

    try {
        ps = cobakonek.prepareStatement(sql);
        rs = ps.executeQuery();

        while (rs.next()) {
            model.addRow(new Object[]{
                rs.getString("ID_sewa"),  
                rs.getString("ID_pegawai"),
                rs.getString("Alamat"),
                rs.getString("Barang_sewa"),
                rs.getString("ID_playstation"),
                rs.getDate("tgl_sewa"),
                rs.getDate("tgl_kembali"),
                rs.getInt("Durasi_sewa"),
                rs.getString("KTP"),
                rs.getString("Surat_pernyataan"),
                rs.getDouble("Total_biaya"),  
                rs.getDouble("Bayar"),
                rs.getDouble("Kembalian"),
                rs.getString("status")
            });
        }

        jTable1.setModel(model);
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage());
    }
}

    
    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
    String selectedMonth = (String) jComboBox2.getSelectedItem();

    // Hindari eksekusi jika belum pilih bulan
    if (selectedMonth.equals("Pilih Bulan")) {
        String sql = "SELECT s.ID_sewa, p.ID_pegawai, s.Alamat, s.Barang_sewa, ps.ID_playstation, " +
                     "s.tgl_sewa, s.tgl_kembali, s.Durasi_sewa, s.KTP, s.Surat_pernyataan, " +
                     "s.Total_biaya, s.Bayar, s.Kembalian, s.status " +
                     "FROM sewa s " +
                     "JOIN pegawai p ON s.ID_pegawai = p.ID_pegawai " +
                     "JOIN playstation ps ON s.ID_playstation = ps.ID_playstation";
        loadDataToTable(sql); // Panggil versi tanpa parameter
        return;
    }

    // Konversi nama bulan ke angka
    int month = getMonthFromString(selectedMonth);
    String sql = "SELECT s.ID_sewa, p.ID_pegawai, s.Alamat, s.Barang_sewa, ps.ID_playstation, s.tgl_sewa, s.tgl_kembali, " +
             "s.Durasi_sewa, s.KTP, s.Surat_pernyataan, s.Total_biaya, s.Bayar, s.Kembalian, s.status " +
             "FROM sewa s " +
             "JOIN pegawai p ON s.ID_pegawai = p.ID_pegawai " +
             "JOIN playstation ps ON s.ID_playstation = ps.ID_playstation " +
             "WHERE MONTH(s.tgl_sewa) = ?";

    // Tampilkan data ke tabel
    loadDataToTable(sql, month);

            
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened

    String sql = "SELECT s.ID_sewa, p.ID_pegawai, s.Alamat, s.Barang_sewa, ps.ID_playstation, " +
                     "s.tgl_sewa, s.tgl_kembali, s.Durasi_sewa, s.KTP, s.Surat_pernyataan, " +
                     "s.Total_biaya, s.Bayar, s.Kembalian, s.status " +
                     "FROM sewa s " +
                     "JOIN pegawai p ON s.ID_pegawai = p.ID_pegawai " +
                     "JOIN playstation ps ON s.ID_playstation = ps.ID_playstation";

    loadDataToTable(sql); // <- yang versi TANPA int param

    }//GEN-LAST:event_formWindowOpened

    private void btnExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExportExcelActionPerformed

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Simpan Laporan Transaksi");
            fileChooser.setSelectedFile(new File("Laporan_Transaksi.xlsx"));

            int userSelection = fileChooser.showSaveDialog(this);
            if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();

            //  method, langsung pakai XSSFWorkbook
            try (Workbook workbook = new XSSFWorkbook()) {
            org.apache.poi.ss.usermodel.Sheet sheet = workbook.createSheet("Laporan Transaksi");

            //  jTableLaporanTransaksi yang sudah ada
            TableModel model = jTable1.getModel();

            // header
            org.apache.poi.ss.usermodel.Row headerRow = sheet.createRow(0);
            for (int col = 0; col < model.getColumnCount(); col++) {
                Cell cell = headerRow.createCell(col);
                cell.setCellValue(model.getColumnName(col));
            }

            // data baris per baris
            for (int row = 0; row < model.getRowCount(); row++) {
                    org.apache.poi.ss.usermodel.Row excelRow = sheet.createRow(row + 1);
                    for (int col = 0; col < model.getColumnCount(); col++) {
                        Cell cell = excelRow.createCell(col);
                        Object value = model.getValueAt(row, col);
                        cell.setCellValue(value != null ? value.toString() : "");
                    }
            }

            // Simpan file
            try (FileOutputStream out = new FileOutputStream(fileToSave)) {
                workbook.write(out);
            }

                JOptionPane.showMessageDialog(this, "Laporan Transaksi Berhasil Disimpan di: " + fileToSave.getAbsolutePath());
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Terjadi kesalahan saat menyimpan file Excel.");
            }
        }
    }//GEN-LAST:event_btnExportExcelActionPerformed

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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LprnSewa.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
       //</editor-fold>

       /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LprnSewa().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExportExcel;
    private javax.swing.JButton jButtonGameRent;
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonOnSpotPlay;
    private javax.swing.JButton jButtonSnack;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    
    
}
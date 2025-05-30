/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package projectelis;

import projectelis.Koneksi_db;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.Connection;
//import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projectelis.Koneksi_db;
import projectelis.SessionLogin;
import projectelis.login;
import projectelis.repostDAO;


/**
 *
 * @author USER
 */
public class transaksiSnack extends javax.swing.JFrame {
    private String total;
    private repostDAO report = new repostDAO();
    Map<String, Integer> stokSementara = new HashMap<>();
    Map<String, Integer> stokAsli = new HashMap<>();
    PreparedStatement st;
    ResultSet rs;
    Connection con;
    Koneksi_db DB = new Koneksi_db();
    long lastTypedTime = 0;
    StringBuilder scannedText = new StringBuilder();
    
    public transaksiSnack() {
        this.setUndecorated(true);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        DB.configDB();
        con = DB.koneksi;
        nama_s.setEditable(false);
        stok.setEditable(false);
        
        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        model.setRowCount(0);
    }

    private String generateAutoID(String transaksi_snack, String ID_transaksi, String T) {
    String newID = "";
    try {
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMdd");
        String datePart = dateFormat.format(now);
        String fullPrefix = T + datePart;

        String sql = "SELECT MAX(" + ID_transaksi + ") FROM " + transaksi_snack + " WHERE " + ID_transaksi + " LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, fullPrefix + "%");
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String maxID = rs.getString(1);
            if (maxID != null && maxID.startsWith(fullPrefix)) {
                // Ambil bagian angka setelah prefix
                String numberPart = maxID.substring(fullPrefix.length());
                int number = Integer.parseInt(numberPart);
                number++;
                newID = fullPrefix + String.format("%03d", number); // padding 3 digit
            } else {
                newID = fullPrefix + "001";
            }
        } else {
            newID = fullPrefix + "001";
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error generate ID: " + e.getMessage());
    }
    return newID;
}


    
    private String generateAutoIDdetail(String detail_transaksiSnack, String ID_detailTransaksiSnack, String DBS) {
    String newID = "";
    try {
        String sql = "SELECT MAX(" + ID_detailTransaksiSnack + ") FROM " + detail_transaksiSnack + " WHERE " + ID_detailTransaksiSnack + " LIKE ?";
        PreparedStatement pst = con.prepareStatement(sql);
        pst.setString(1, DBS + "%");
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String maxID = rs.getString(1);
            if (maxID != null && maxID.startsWith(DBS)) {
                int number = Integer.parseInt(maxID.substring(DBS.length())) + 1;
                newID = DBS + String.format("%03d", number);
            } else {
                newID = DBS + "001";
            }
        } else {
            newID = DBS + "001";
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error generate ID: " + e.getMessage());
    }
    return newID;
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        kodesnack = new javax.swing.JTextField();
        harga_s = new javax.swing.JTextField();
        nama_s = new javax.swing.JTextField();
        jumlah = new javax.swing.JTextField();
        bayar_s = new javax.swing.JTextField();
        kembali_s = new javax.swing.JTextField();
        total_s = new javax.swing.JTextField();
        bayar_b = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabel_transaksi = new javax.swing.JTable();
        tambah = new javax.swing.JButton();
        clear = new javax.swing.JButton();
        stok = new javax.swing.JTextField();
        logout = new javax.swing.JButton();
        home = new javax.swing.JButton();
        jButton1rent = new javax.swing.JButton();
        jButton2onspot = new javax.swing.JButton();
        jButton4report = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                FrameWindowOpened(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(1375, 770));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kodesnack.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        kodesnack.setForeground(new java.awt.Color(255, 255, 255));
        kodesnack.setBorder(null);
        kodesnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodesnackActionPerformed(evt);
            }
        });
        kodesnack.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                kodesnackKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kodesnackKeyTyped(evt);
            }
        });
        jPanel1.add(kodesnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(572, 101, 240, 50));

        harga_s.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        harga_s.setForeground(new java.awt.Color(255, 255, 255));
        harga_s.setBorder(null);
        harga_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                harga_sActionPerformed(evt);
            }
        });
        harga_s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                harga_sKeyTyped(evt);
            }
        });
        jPanel1.add(harga_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(994, 106, 260, 40));

        nama_s.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        nama_s.setForeground(new java.awt.Color(255, 255, 255));
        nama_s.setBorder(null);
        nama_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nama_sActionPerformed(evt);
            }
        });
        nama_s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nama_sKeyPressed(evt);
            }
        });
        jPanel1.add(nama_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(573, 164, 250, 50));

        jumlah.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jumlah.setForeground(new java.awt.Color(255, 255, 255));
        jumlah.setBorder(null);
        jumlah.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jumlahKeyTyped(evt);
            }
        });
        jPanel1.add(jumlah, new org.netbeans.lib.awtextra.AbsoluteConstraints(999, 164, 140, 50));

        bayar_s.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        bayar_s.setForeground(new java.awt.Color(255, 255, 255));
        bayar_s.setBorder(null);
        bayar_s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayar_sKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayar_sKeyTyped(evt);
            }
        });
        jPanel1.add(bayar_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 607, 300, 50));

        kembali_s.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        kembali_s.setForeground(new java.awt.Color(255, 255, 255));
        kembali_s.setToolTipText("");
        kembali_s.setBorder(null);
        kembali_s.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                kembali_sKeyTyped(evt);
            }
        });
        jPanel1.add(kembali_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 687, 250, 50));

        total_s.setFont(new java.awt.Font("Calibri", 1, 35)); // NOI18N
        total_s.setForeground(new java.awt.Color(255, 255, 255));
        total_s.setToolTipText("");
        total_s.setBorder(null);
        total_s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                total_sActionPerformed(evt);
            }
        });
        jPanel1.add(total_s, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 617, 240, 50));

        bayar_b.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn bayar snack.png"))); // NOI18N
        bayar_b.setContentAreaFilled(false);
        bayar_b.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk bayar snack.png"))); // NOI18N
        bayar_b.setLabel("");
        bayar_b.setContentAreaFilled(false);
        bayar_b.setBorderPainted(false);
        bayar_b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayar_bActionPerformed(evt);
            }
        });
        jPanel1.add(bayar_b, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 690, 430, 50));

        tabel_transaksi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Kode snack", "Nama snack", "Harga", "Jumlah", "Sub total"
            }
        ));
        tabel_transaksi.setShowGrid(true);
        tabel_transaksi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabel_transaksiMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabel_transaksi);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 310, 920, 250));

        tambah.setLabel("");
        tambah.setContentAreaFilled(false);
        tambah.setBorderPainted(false);
        tambah.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn save snack.png"))); // NOI18N
        tambah.setContentAreaFilled(false);
        tambah.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk save snack.png"))); // NOI18N
        tambah.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahActionPerformed(evt);
            }
        });
        jPanel1.add(tambah, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 234, 200, 50));

        clear.setLabel("");
        clear.setContentAreaFilled(false);
        clear.setBorderPainted(false);
        clear.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn delete snack.png"))); // NOI18N
        clear.setContentAreaFilled(false);
        clear.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk delete snack.png"))); // NOI18N
        clear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearActionPerformed(evt);
            }
        });
        jPanel1.add(clear, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 235, 200, 50));

        stok.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        stok.setForeground(new java.awt.Color(255, 255, 255));
        stok.setBorder(null);
        stok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stokActionPerformed(evt);
            }
        });
        jPanel1.add(stok, new org.netbeans.lib.awtextra.AbsoluteConstraints(502, 236, 100, 40));

        logout.setLabel("");
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);
        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi logout.png"))); // NOI18N
        logout.setContentAreaFilled(false);
        logout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi logout.png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 670, 220, 90));

        home.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi home.png"))); // NOI18N
        home.setContentAreaFilled(false);
        home.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi home.png"))); // NOI18N
        home.setLabel("");
        home.setContentAreaFilled(false);
        home.setBorderPainted(false);
        home.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                homeActionPerformed(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 210, 240, 80));

        jButton1rent.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Game Rent.png"))); // NOI18N
        jButton1rent.setContentAreaFilled(false);
        jButton1rent.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Game Rent.png"))); // NOI18N
        jButton1rent.setLabel("");
        jButton1rent.setContentAreaFilled(false);
        jButton1rent.setBorderPainted(false);
        jButton1rent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1rentActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1rent, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 330, 230, 50));

        jButton2onspot.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi ON-Spot Play.png"))); // NOI18N
        jButton2onspot.setContentAreaFilled(false);
        jButton2onspot.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi ON-Spot Play.png"))); // NOI18N
        jButton2onspot.setLabel("");
        jButton2onspot.setContentAreaFilled(false);
        jButton2onspot.setBorderPainted(false);
        jButton2onspot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2onspotActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2onspot, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 410, 220, 50));

        jButton4report.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Reports.png"))); // NOI18N
        jButton4report.setContentAreaFilled(false);
        jButton4report.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Reports.png"))); // NOI18N
        jButton4report.setLabel("");
        jButton4report.setContentAreaFilled(false);
        jButton4report.setBorderPainted(false);
        jButton4report.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4reportActionPerformed(evt);
            }
        });
        jPanel1.add(jButton4report, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 570, 190, 50));

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bg no Snack.png"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 43, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private String GetNamaSnack(String kodeSnack) {
    String namaSnack = "";
    String sql = "SELECT Nama FROM snack WHERE Kode_snack = ?";
    try (PreparedStatement pst = con.prepareStatement(sql)) {
        pst.setString(1, kodeSnack);
        ResultSet rs = pst.executeQuery();
        if (rs.next()) {
            namaSnack = rs.getString("Nama");
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Gagal mengambil nama produk: " + e.getMessage());
    }
    return namaSnack;
}


 private int GetStok(String namaSnack) { //fungsi untuk mendapatkan stok produk berdasarkan nama produk
        int stok = 0;

        if (namaSnack == null || namaSnack.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Nama produk tidak boleh kosong.");
            return stok;
        }

        String sqlStok = "SELECT Stok FROM snack WHERE Nama = ?";
        try (PreparedStatement pst = con.prepareStatement(sqlStok)) {
            pst.setString(1, namaSnack);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    stok = rs.getInt("Stok");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal mengambil stok untuk produk: " + namaSnack + ". Error: " + e.getMessage());
        }

        return stok;
    }
 
    private void FrameWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_FrameWindowOpened
        
        kodesnack.setOpaque(false); 
        kodesnack.setBackground(new Color(0,0,0,0));
        kodesnack.setBorder(null);  
        kodesnack.setForeground(Color.WHITE);  
        kodesnack.setCaretColor(Color.WHITE);
        
        nama_s.setOpaque(false); 
        nama_s.setBackground(new Color(0,0,0,0));
        nama_s.setBorder(null);  
        nama_s.setForeground(Color.WHITE);  
        nama_s.setCaretColor(Color.WHITE);
        
        stok.setOpaque(false);  
        stok.setBackground(new Color(0,0,0,0)); 
        stok.setBorder(null);  
        stok.setForeground(Color.WHITE);  
        stok.setCaretColor(Color.WHITE);


        harga_s.setOpaque(false);  
        harga_s.setBackground(new Color(0,0,0,0)); 
        harga_s.setBorder(null);  
        harga_s.setForeground(Color.WHITE); 
        harga_s.setCaretColor(Color.WHITE);
     
        jumlah.setOpaque(false); 
        jumlah.setBackground(new Color(0,0,0,0)); 
        jumlah.setBorder(null);   
        jumlah.setForeground(Color.WHITE);  
        jumlah.setCaretColor(Color.WHITE);
        
        bayar_s.setOpaque(false); 
        bayar_s.setBackground(new Color(0,0,0,0)); 
        bayar_s.setBorder(null);  
        bayar_s.setForeground(Color.WHITE); 
        bayar_s.setCaretColor(Color.WHITE);
        
        kembali_s.setOpaque(false); 
        kembali_s.setBackground(new Color(0,0,0,0)); 
        kembali_s.setBorder(null);  
        kembali_s.setForeground(Color.WHITE);  
        kembali_s.setCaretColor(Color.WHITE);
        
        total_s.setOpaque(false); 
        total_s.setBackground(new Color(0,0,0,0));
        total_s.setBorder(null);   
        total_s.setForeground(Color.WHITE); 
        total_s.setCaretColor(Color.WHITE);
    }//GEN-LAST:event_FrameWindowOpened

    private void kodesnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodesnackActionPerformed
 
    }//GEN-LAST:event_kodesnackActionPerformed

    private void nama_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nama_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nama_sActionPerformed

    private void harga_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_harga_sActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_harga_sActionPerformed

    private void tambahActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahActionPerformed
                                              
    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
    
    String kode = kodesnack.getText();
    String nama = nama_s.getText();
    double harga = Double.parseDouble(harga_s.getText());
    int jumlahBaru = Integer.parseInt(jumlah.getText());
    double subtotal = harga * jumlahBaru;

   
    int stokSekarang;
    if (stokSementara.containsKey(kode)) {
        stokSekarang = stokSementara.get(kode);
    } else {
        stokSekarang = Integer.parseInt(stok.getText());
    }


    // Cek apakah cukup
    if (stokSekarang >= jumlahBaru) {
        int sisa = stokSekarang - jumlahBaru;
        System.out.println("Sisa stok setelah transaksi: " + sisa);

        // Simpan ke stok sementara
        stokSementara.put(kode, sisa);
        

        // Tampilkan sisa ke TextField stok
        stok.setText(String.valueOf(sisa));
        System.out.println("Setelah update, stok TextField: " + stok.getText());
        stok.revalidate();
        stok.repaint();
    } else {
        JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
        return;
    }

    boolean ditemukan = false;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object value = model.getValueAt(i, 0);
        String kodeTabel = (value != null) ? value.toString() : "";

        if (kodeTabel.equals(kode)) {
    int jumlahLama = Integer.parseInt(model.getValueAt(i, 3).toString());
    int jumlahTotal = jumlahLama + jumlahBaru;

    model.setValueAt(jumlahTotal, i, 3);
    model.setValueAt(jumlahTotal * harga, i, 4);

    int stokAwal = stokAsli.getOrDefault(kode, 0);
    int stokBaru = stokAwal - jumlahTotal;

    stokSementara.put(kode, stokBaru); // ✅ Fix double pengurangan
    stok.setText(String.valueOf(stokBaru));

    ditemukan = true;
    break;
}

    }

    if (!ditemukan) {
        model.addRow(new Object[]{kode, nama, harga, jumlahBaru, subtotal});
    }

    TotalHarga();

    // Kosongkan TextField (stok jangan dikosongkan karena akan terisi saat ketik kode lagi)
    kodesnack.setText("");
    nama_s.setText("");
    harga_s.setText("");
    jumlah.setText("");
    stok.setText("");
    
    kodesnack.requestFocus();

 // Balik ke field kode buat scan/tik manual
    }//GEN-LAST:event_tambahActionPerformed

    private void clearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearActionPerformed
        try {
    int selectedRow = tabel_transaksi.getSelectedRow();

    if (selectedRow == -1) {
        JOptionPane.showMessageDialog(null, "Silakan pilih baris yang ingin dihapus.");
        return;
    }

    int confirm = JOptionPane.showConfirmDialog(null,
        "Apakah Anda yakin ingin menghapus produk ini?",
        "Konfirmasi Hapus",
        JOptionPane.YES_NO_OPTION);
    if (confirm != JOptionPane.YES_OPTION) {
        return;
    }

    DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
    
    // Ambil kembali jumlah yang sudah ditambahkan ke keranjang sebelumnya
    String kode = model.getValueAt(selectedRow, 0).toString(); // kode snack
    int qtyDihapus = Integer.parseInt(model.getValueAt(selectedRow, 3).toString()); // kolom kuantitas

  /*  if (stok.getText().isEmpty()) {
    JOptionPane.showMessageDialog(null, "Field stok kosong. Klik baris yang ingin dihapus terlebih dahulu.");
    return; 
} */
     // Ambil stok sekarang dari stok sementara
        int stokSekarang = stokSementara.getOrDefault(kode, 0);
        int stokBaru = stokSekarang + qtyDihapus;

        // Update stok sementara
        stokSementara.put(kode, stokBaru);

        // Kalau kode yang dihapus sedang aktif ditampilkan di form, baru update field stok
        if (kode.equals(kodesnack.getText())) {
            stok.setText(String.valueOf(stokBaru));
        }
    
    model.removeRow(selectedRow);

    // Kosongkan field
    kodesnack.setText("");
    nama_s.setText("");
    harga_s.setText("");
    stok.setText("");
    jumlah.setText("");


        // Hitung ulang total
        int totalBaru = 0;
    for (int i = 0; i < model.getRowCount(); i++) {
        Object val = model.getValueAt(i, 4);
        if (val != null) {
            String subTotal = val.toString().replace("Rp ", "").replace(".", "");

            try {
                totalBaru += Integer.parseInt(subTotal);
            } catch (NumberFormatException ex) {
                // Optional: tampilkan warning/log
                System.err.println("Format subtotal salah di baris " + i + ": " + val);
            }
        }}

    total_s.setText("Rp " + String.format("%,d", totalBaru).replace(',', '.'));

    tambah.setEnabled(true);

    JOptionPane.showMessageDialog(null, "Produk berhasil dihapus!");
} catch (Exception e) {
    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + e.getMessage());
    e.printStackTrace();
}

    }//GEN-LAST:event_clearActionPerformed

    private void tabel_transaksiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabel_transaksiMouseClicked
        int selectedRow = tabel_transaksi.getSelectedRow();

    }//GEN-LAST:event_tabel_transaksiMouseClicked

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Apakah anda yakin ingin keluar?",
                "Konfirmasi Logout",
                JOptionPane.YES_NO_OPTION
            );

            if (confirm == JOptionPane.YES_OPTION) {
                SessionLogin.clearSession();
                login backToLogin = new login();
                backToLogin.setVisible(true);
                this.dispose();
            }
    }//GEN-LAST:event_logoutActionPerformed

    private void TotalHarga() { //fungsi menghitung total harga dari produk yang di tambahkan di gui tabel transaksi
        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
    int total_harga = 0;

    for (int i = 0; i < model.getRowCount(); i++) {
        Object val = model.getValueAt(i, 4); // kolom subtotal
        if (val != null) {
            try {
                double parsed;
                if (val instanceof Number) {
                    parsed = ((Number) val).doubleValue();
                } else {
                    String text = val.toString().replaceAll("[^\\d]", ""); // hanya angka
                    parsed = Double.parseDouble(text);
                }
                total_harga += parsed;
            } catch (NumberFormatException e) {
                System.err.println("Gagal parsing subtotal baris " + i + ": " + val);
            }
        }
    }

    total_s.setText("Rp " + String.format("%,d", total_harga).replace(',', '.'));
}


    private void bayar_sKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayar_sKeyReleased
        hitungkembalian();
    }//GEN-LAST:event_bayar_sKeyReleased

    private void nama_sKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nama_sKeyPressed
        
    }//GEN-LAST:event_nama_sKeyPressed

    private void kodesnackKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodesnackKeyPressed
                                        
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            String kode;

            // Deteksi apakah input dari scanner
            boolean isScan = scannedText.length() > 3; // >3 karakter dalam waktu singkat

            if (isScan) {
                kode = scannedText.toString().trim();
            } else {
                kode = kodesnack.getText().trim();
            }

            if (kode.isEmpty()) return;

            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/playzonee", "root", "");
                String sql = "SELECT * FROM snack WHERE kode_snack = ?";
                PreparedStatement pst = conn.prepareStatement(sql);
                pst.setString(1, kode);
                ResultSet rs = pst.executeQuery();

                if (rs.next()) {
                    String nama = rs.getString("Nama");
                    int harga = rs.getInt("Harga");
                    int stokDB = rs.getInt("Stok");

                    // Simpan stok awal jika belum ada
                    if (!stokAsli.containsKey(kode)) {
                        stokAsli.put(kode, stokDB);
                    }

                    // Ambil stok sementara
                    int stokSekarang = stokSementara.getOrDefault(kode, stokDB);

                    if (isScan) {
                        // Tambahkan langsung ke tabel dengan jumlah 1
                        int jumlahBaru = 1;

                        if (stokSekarang >= jumlahBaru) {
                            int sisa = stokSekarang - jumlahBaru;
                            stokSementara.put(kode, sisa);

                            DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
                            boolean ditemukan = false;

                            for (int i = 0; i < model.getRowCount(); i++) {
                                String kodeTabel = model.getValueAt(i, 0).toString();
                                if (kodeTabel.equals(kode)) {
                                    int jumlahLama = Integer.parseInt(model.getValueAt(i, 3).toString());
                                    int jumlahTotal = jumlahLama + jumlahBaru;
                                    model.setValueAt(jumlahTotal, i, 3);
                                    model.setValueAt(jumlahTotal * harga, i, 4);

                                    stokSementara.put(kode, stokAsli.get(kode) - jumlahTotal);
                                    ditemukan = true;
                                    break;
                                }
                            }

                            if (!ditemukan) {
                                model.addRow(new Object[]{kode, nama, harga, jumlahBaru, harga});
                            }

                            TotalHarga();

                            // Kosongkan input
                            kodesnack.setText("");
                            nama_s.setText("");
                            harga_s.setText("");
                            jumlah.setText("");
                            stok.setText("");
                            kodesnack.requestFocus();
                        } else {
                            JOptionPane.showMessageDialog(this, "Stok tidak mencukupi!");
                        }

                    } else {
                        // 👇 Tampilkan ke text field seperti biasa
                        kodesnack.setText(kode);
                        nama_s.setText(nama);
                        harga_s.setText(String.valueOf(harga));
                        stok.setText(String.valueOf(stokSekarang));
                        jumlah.setText("");
                        jumlah.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Snack tidak ditemukan!");
                }

                rs.close();
                pst.close();
                conn.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
            }

            // Reset scanned text
            scannedText.setLength(0);
            lastTypedTime = 0;
        }

    
    }//GEN-LAST:event_kodesnackKeyPressed

    private void total_sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_total_sActionPerformed
        
    }//GEN-LAST:event_total_sActionPerformed

    private void bayar_bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayar_bActionPerformed
        Connection conn = null;
        PreparedStatement pstTransaksi = null;
        PreparedStatement pstDetail = null;
        ResultSet rs = null;

    try {
       String idTr = generateAutoID("transaksi_snack", "ID_transaksi", "TRX");


        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playzonee", "root", "");

        String idPegawai = SessionLogin.getID_pegawai();

        // Mengambil dan membersihkan nilai dari TextField
        String totalStr = total_s.getText().replaceAll("[^0-9]", "");
        String bayarStr = bayar_s.getText().replaceAll("[^0-9]", "");
        String kembaliStr = kembali_s.getText().replaceAll("[^0-9]", "");

        // Validasi input angka
        int total = 0;
        int bayar = 0;
        int kembali = 0;
        
        
            total = Integer.parseInt(totalStr);
            bayar = Integer.parseInt(bayarStr);
            kembali = Integer.parseInt(kembaliStr);
            
            // Cek apakah uang bayar cukup
        if (bayar < total) {
            JOptionPane.showMessageDialog(null, "Uang bayar kurang!");
            return;
        }

        // 1. Simpan ke tabel transaksi
        String sqlTransaksi = "INSERT INTO transaksi_snack (ID_pegawai, Tanggal, Total_harga, Bayar, Kembalian, ID_transaksi) VALUES (?,?, ?, ?, ?, ?)";
        pstTransaksi = conn.prepareStatement(sqlTransaksi, Statement.RETURN_GENERATED_KEYS);
        pstTransaksi.setString(1, idPegawai);
        pstTransaksi.setDate(2, new java.sql.Date(System.currentTimeMillis()));
        pstTransaksi.setInt(3, total);
        pstTransaksi.setInt(4, bayar);
        pstTransaksi.setInt(5, kembali);
        pstTransaksi.setString(6, idTr);
        pstTransaksi.executeUpdate();

        // Ambil ID_transaksi yang baru dibuat
        String idTransaksi = idTr;

        // 2. Simpan ke tabel detail_transaksi_snack
        String sqlDetail = "INSERT INTO detail_transaksisnack (ID_transaksi, Kode_snack, Harga, Jumlah, Total_harga, ID_detailTransaksiSnack) VALUES (?,?, ?, ?, ?, ?)";
        pstDetail = conn.prepareStatement(sqlDetail);

        DefaultTableModel model = (DefaultTableModel) tabel_transaksi.getModel();
        for (int i = model.getRowCount() - 1; i >= 0; i--) {
            boolean barisKosong = true;
            for (int j = 0; j < model.getColumnCount(); j++) {
                Object value = model.getValueAt(i, j);
                if (value != null && !value.toString().trim().isEmpty()) {
                    barisKosong = false;
                    break;
                }
            }
            if (barisKosong) {
                model.removeRow(i);
            }
        }

        String lastID = generateAutoIDdetail("detail_transaksiSnack", "ID_detailTransaksiSnack", "DBS");
        int lastNumber = Integer.parseInt(lastID.replaceAll("[^0-9]", ""));

        for (int i = 0; i < model.getRowCount(); i++) {
            Object valKode = model.getValueAt(i, 0);
            Object valHarga = model.getValueAt(i, 2);
            Object valJumlah = model.getValueAt(i, 3);
            Object valTotal = model.getValueAt(i, 4);

            if (valKode == null || valHarga == null || valJumlah == null || valTotal == null ||
                valKode.toString().trim().equals("") || valHarga.toString().trim().equals("") ||
                valJumlah.toString().trim().equals("") || valTotal.toString().trim().equals("")) {
                continue;
            }

            String kodeSnack = valKode.toString();
            int harga = (int) Double.parseDouble(valHarga.toString());
            int jumlah = Integer.parseInt(valJumlah.toString()); // jumlah tetap integer
            int total_m = (int) Double.parseDouble(valTotal.toString());

           //String idTrD = "DBS" + (i + 1);
          // String idTrD = generateAutoIDdetail("detail_transaksiSnack", "ID_detailTransaksiSnack", "DBS");
             String idTrD;
                do {
                    idTrD = String.format("DBS%d", ++lastNumber);

                    // Pastikan ID belum ada di database
                    PreparedStatement cekID = conn.prepareStatement(
                        "SELECT COUNT(*) FROM detail_transaksisnack WHERE ID_detailTransaksiSnack = ?");
                    cekID.setString(1, idTrD);
                    ResultSet rsCek = cekID.executeQuery();
                    rsCek.next();
                    if (rsCek.getInt(1) == 0) {
                        break; // ID belum digunakan, aman dipakai
                    }
                    // else: lanjutkan increment
                } while (true);


            pstDetail.setString(1, idTransaksi);
            pstDetail.setString(2, kodeSnack);
            pstDetail.setInt(3, harga);
            pstDetail.setInt(4, jumlah);
            pstDetail.setInt(5, total_m);
            pstDetail.setString(6, idTrD);
            pstDetail.addBatch();
        }
        pstDetail.executeBatch();

        // Kurangi stok snack
        String sqlUpdateStok = "UPDATE snack SET stok = stok - ? WHERE Kode_snack = ?";
        PreparedStatement pstUpdateStok = conn.prepareStatement(sqlUpdateStok);

        for (int i = 0; i < model.getRowCount(); i++) {
            int jumlah_stok = Integer.parseInt(model.getValueAt(i, 3).toString());
            Object valKode = model.getValueAt(i, 0);
            String kodeSnack = (valKode != null) ? valKode.toString() : "";

            pstUpdateStok.setInt(1, jumlah_stok);
            pstUpdateStok.setString(2, kodeSnack);
            pstUpdateStok.addBatch();
        }
        pstUpdateStok.executeBatch();
        pstUpdateStok.close();
        
        JOptionPane.showMessageDialog(null, "Transaksi berhasil disimpan!");
        
        report.printStruk(idTr);
     
        DefaultTableModel modelReset = (DefaultTableModel) tabel_transaksi.getModel();
        modelReset.setRowCount(0); // Kosongkan tabel

        total_s.setText("");     
        bayar_s.setText("");       
        kembali_s.setText("");
        nama_s.setText(""); 
        kodesnack.setText(""); 
        stok.setText(""); 
        harga_s.setText("");
        jumlah.setText(""); 
        

    } catch (Exception ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Gagal menyimpan transaksi: " + ex.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (pstTransaksi != null) pstTransaksi.close();
            if (pstDetail != null) pstDetail.close();
            if (conn != null) conn.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    
}
    }//GEN-LAST:event_bayar_bActionPerformed

    private void homeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_homeActionPerformed
        /*homePegawai nw_transaksi = new homePegawai();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_homeActionPerformed

    private void stokActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stokActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stokActionPerformed

    private void jButton4reportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4reportActionPerformed
       /*LprnSewa nw_transaksi = new LprnSewa();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButton4reportActionPerformed

    private void jButton1rentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1rentActionPerformed
        /*TrSewaPegawai nw_transaksi = new TrSewaPegawai();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButton1rentActionPerformed

    private void jButton2onspotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2onspotActionPerformed
        /*transaksiMainDitempat nw_transaksi = new transaksiMainDitempat();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButton2onspotActionPerformed

    private void kodesnackKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodesnackKeyTyped
        long currentTime = System.currentTimeMillis();
        if (lastTypedTime == 0 || (currentTime - lastTypedTime < 50)) {
            scannedText.append(evt.getKeyChar());
        } else {
            scannedText.setLength(0);
            scannedText.append(evt.getKeyChar());
        }
        lastTypedTime = currentTime;
    }//GEN-LAST:event_kodesnackKeyTyped

    private void harga_sKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_harga_sKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); 
        }
    }//GEN-LAST:event_harga_sKeyTyped

    private void jumlahKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jumlahKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); 
        }
    }//GEN-LAST:event_jumlahKeyTyped

    private void bayar_sKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayar_sKeyTyped
       char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); 
        }
    }//GEN-LAST:event_bayar_sKeyTyped

    private void kembali_sKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kembali_sKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_kembali_sKeyTyped

    
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
            java.util.logging.Logger.getLogger(transaksiSnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(transaksiSnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(transaksiSnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(transaksiSnack.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new transaksiSnack().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayar_b;
    private javax.swing.JTextField bayar_s;
    private javax.swing.JButton clear;
    private javax.swing.JTextField harga_s;
    private javax.swing.JButton home;
    private javax.swing.JButton jButton1rent;
    private javax.swing.JButton jButton2onspot;
    private javax.swing.JButton jButton4report;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jumlah;
    private javax.swing.JTextField kembali_s;
    private javax.swing.JTextField kodesnack;
    private javax.swing.JButton logout;
    private javax.swing.JTextField nama_s;
    private javax.swing.JTextField stok;
    private javax.swing.JTable tabel_transaksi;
    private javax.swing.JButton tambah;
    private javax.swing.JTextField total_s;
    // End of variables declaration//GEN-END:variables

 
private void hitungkembalian() {
    try {
        // Bersihkan "Rp", titik, koma dari total
        String totalStr = total_s.getText().replace("Rp", "").replace(".", "").replace(",", "").trim();
        double totalValue = Double.parseDouble(totalStr);

        // Bersihkan titik, koma dari bayar juga
        String bayarStr = bayar_s.getText().replace(".", "").replace(",", "").trim();
        double bayarValue = Double.parseDouble(bayarStr);

        double kembalianValue = bayarValue - totalValue;

        if (kembalianValue < 0) {
            kembali_s.setText("- Rp " + String.format("%,.0f", Math.abs(kembalianValue)));
        } else {
            kembali_s.setText("Rp " + String.format("%,.0f", kembalianValue));
        }

        // Format nilai bayar dengan tanda koma
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        bayar_s.setText(formatter.format(bayarValue));

    } catch (NumberFormatException e) {
        kembali_s.setText("Rp 0");
    }
}
}
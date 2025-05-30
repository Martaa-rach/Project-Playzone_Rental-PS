package playzone;

import java.awt.Color;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableModel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import playzone.LprnSewa;
import playzone.SessionLogin;



/**
 *
 * @author OwOwO
 */
public class TrSewaPegawai extends javax.swing.JFrame {
    public Statement st;
    public PreparedStatement ps;
    public ResultSet rs;
    Connection cobakonek=playzone.Koneksi_db.configDB();
 

    /**
     * Creates new form TrSewaPegawai
     */
    public TrSewaPegawai() {
        this.setUndecorated(true); 
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        initComponents();
        jTextFieldTotalHarga.setEditable(false);
        jTextFieldKembalian.setEditable(false);
        jTextFieldBarang.setEditable(false);
        clearTextField();
        jComboBoxIDPS.setSelectedIndex(0);

        // Cek apakah koneksi berhasil
        if (cobakonek != null) {
            showdata();           // Menampilkan data di tabel
            isiComboBoxIDPS();    // Baru panggil ini setelah koneksi berhasil
        } else {
            System.out.println("Database belum aktif. GUI tetap dijalankan.");
        }

        SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 12);
        jSpinner1.setModel(model);

        tampilkanNamaBarang(); // langsung tampilkan
        int total = hitungTotalHarga(); // langsung hitung
        jTextFieldTotalHarga.setText(String.valueOf(total)); // tampilkan hasil
    }

    private String generateAutoID(String sewa, String ID_sewa, String TrSP) {
                    String newID = "";
                    try {
                        String sql = "SELECT MAX(" + ID_sewa + ") FROM " + sewa;
                        PreparedStatement pst = cobakonek.prepareStatement(sql);
                        ResultSet rs = pst.executeQuery();

                        if (rs.next()) {
                            String maxID = rs.getString(1);
                            if (maxID != null && maxID.startsWith(TrSP)) {
                                int number = Integer.parseInt(maxID.substring(TrSP.length())) + 1;
                                newID = TrSP + number; 
                            } else {
                                newID = TrSP + "1";
                            }
                        }
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Error generate ID: " + e.getMessage());
                    }
                    return newID;
                }
    
        private int hitungTotalHarga() {
            if (jComboBoxIDPS.getSelectedItem() == null) {
                return 0;
            }

            String idPS;
            try {
                idPS = jComboBoxIDPS.getSelectedItem().toString();

            } catch (NumberFormatException e) {
                return 0; // kalau bukan angka valid
            }

            int harga12 = 0;
            int harga24 = 0;
            int durasi = (Integer) jSpinner1.getValue(); // durasi dalam jam

            try {
                //if (cobakonek == null) {
                  //  cobakonek = playzone.Koneksi_db.configDB(); // tambahkan ini
                //}

                String sql = "SELECT harga_sewa12, harga_sewa24 FROM playstation WHERE ID_playstation = ?";
                ps = cobakonek.prepareStatement(sql);
                ps.setString(1, idPS);
                rs = ps.executeQuery();

                if (rs.next()) {
                    harga12 = rs.getInt("harga_sewa12");
                    harga24 = rs.getInt("harga_sewa24");
                }

                rs.close();
                ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Gagal ambil harga: " + e.getMessage());
            }

            int paket24 = durasi / 24;
            int sisaJam = durasi % 24;
            int paket12 = (sisaJam > 0) ? (int) Math.ceil(sisaJam / 12.0) : 0;

            return (paket24 * harga24) + (paket12 * harga12);
        }



        private void hitungkembalian() {
            try {
                // Bersihkan "Rp", titik, koma dari total
                String totalStr = jTextFieldTotalHarga.getText().replace("Rp", "").replace(".", "").replace(",", "").trim();
                double totalValue = Double.parseDouble(totalStr);

                // Bersihkan titik, koma dari bayar juga
                String bayarStr = jTextFieldBayar.getText().replace(".", "").replace(",", "").trim();
                double bayarValue = Double.parseDouble(bayarStr);

                double kembalianValue = bayarValue - totalValue;

                if (kembalianValue < 0) {
                    jTextFieldKembalian.setText("- Rp " + String.format("%,.0f", Math.abs(kembalianValue)));
                } else {
                    jTextFieldKembalian.setText("Rp " + String.format("%,.0f", kembalianValue));
                }

                // Format nilai bayar dengan tanda koma
                DecimalFormat formatter = new DecimalFormat("###,###,###");
                jTextFieldBayar.setText(formatter.format(bayarValue));

            } catch (NumberFormatException e) {
                jTextFieldKembalian.setText("Rp 0");
            }
        }


    public void tampilData() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // clear tabel

        try {
            Statement stmt = cobakonek.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM sewa");

            while (rs.next()) {
                Object[] row = new Object[]{
                    
                rs.getString("ID_sewa"),
                rs.getString("ID_pegawai"),
                rs.getString("Nama"),
                rs.getString("Alamat"),
                rs.getString("ID_playstation"),
                rs.getString("Barang_sewa"),
                rs.getDate("tgl_sewa"),
                rs.getDate("tgl_kembali"),
                rs.getString("KTP"),
                rs.getString("Surat_pernyataan"),
                rs.getString("status")
            };

                model.addRow(row);
            }
        rs.close();
        stmt.close();
        
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan data: " + e.getMessage());
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

        jButtonHome = new javax.swing.JButton();
        jButtonLogout = new javax.swing.JButton();
        jTextFieldNama = new javax.swing.JTextField();
        jTextFieldAlamat = new javax.swing.JTextField();
        jTextFieldBarang = new javax.swing.JTextField();
        jCheckBoxKTP = new javax.swing.JCheckBox();
        jCheckBoxSuratPernyataan = new javax.swing.JCheckBox();
        jButtonSave = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButtonOnSpotPlay = new javax.swing.JButton();
        jButtonSnack = new javax.swing.JButton();
        jButtonReports = new javax.swing.JButton();
        jTextFieldTotalHarga = new javax.swing.JTextField();
        jTextFieldBayar = new javax.swing.JTextField();
        jTextFieldKembalian = new javax.swing.JTextField();
        jComboBoxIDPS = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        jCalendarTanggalSewa = new com.toedter.calendar.JDateChooser();
        jCalendarTanggalKembali = new com.toedter.calendar.JDateChooser();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButtonHome.setContentAreaFilled(false);
        jButtonHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi home.png"))); // NOI18N
        jButtonHome.setContentAreaFilled(false);
        jButtonHome.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi home.png"))); // NOI18N
        jButtonHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHomeActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 240, 70));

        jButtonLogout.setContentAreaFilled(false);
        jButtonLogout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi logout.png"))); // NOI18N
        jButtonLogout.setContentAreaFilled(false);
        jButtonLogout.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi logout.png"))); // NOI18N
        jButtonLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLogoutActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonLogout, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 670, 220, 90));

        jTextFieldNama.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldNama.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldNama.setBorder(null);
        jTextFieldNama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNamaActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldNama, new org.netbeans.lib.awtextra.AbsoluteConstraints(455, 401, 370, 40));

        jTextFieldAlamat.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldAlamat.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldAlamat.setBorder(null);
        jTextFieldAlamat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldAlamatActionPerformed(evt);
            }
        });
        getContentPane().add(jTextFieldAlamat, new org.netbeans.lib.awtextra.AbsoluteConstraints(462, 461, 370, 40));

        jTextFieldBarang.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldBarang.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldBarang.setBorder(null);
        getContentPane().add(jTextFieldBarang, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 582, 370, 40));

        jCheckBoxKTP.setBorder(null);
        jCheckBoxKTP.setIcon(new ImageIcon(getClass().getResource("/asset/centangKosong.png")));
        jCheckBoxKTP.setSelectedIcon(new ImageIcon(getClass().getResource("/asset/centang.png")));
        jCheckBoxKTP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxKTPActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBoxKTP, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 636, 50, 50));

        jCheckBoxSuratPernyataan.setBorder(null);
        jCheckBoxSuratPernyataan.setIcon(new ImageIcon(getClass().getResource("/asset/centangKosong.png")));
        jCheckBoxSuratPernyataan.setSelectedIcon(new ImageIcon(getClass().getResource("/asset/centang.png")));
        jCheckBoxSuratPernyataan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxSuratPernyataanActionPerformed(evt);
            }
        });
        getContentPane().add(jCheckBoxSuratPernyataan, new org.netbeans.lib.awtextra.AbsoluteConstraints(765, 636, 50, 50));

        jButtonSave.setContentAreaFilled(false);
        jButtonSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn save sewa-main.png"))); // NOI18N
        jButtonSave.setBorder(null);
        jButtonSave.setContentAreaFilled(false);
        jButtonSave.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk save sewa+main.png"))); // NOI18N
        jButtonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSaveActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSave, new org.netbeans.lib.awtextra.AbsoluteConstraints(698, 697, 310, 60));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "No", "ID Pegawai", "Nama Penyewa", "Alamat Penyewa", "ID PS", "Barang", "Tanggal Sewa", "Tanggal Kembali", "KTP", "Surat Pernyataan", "Status"
            }
        ));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(367, 78, 970, 290));

        jButtonOnSpotPlay.setContentAreaFilled(false);
        jButtonOnSpotPlay.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi ON-Spot Play.png"))); // NOI18N
        jButtonOnSpotPlay.setContentAreaFilled(false);
        jButtonOnSpotPlay.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi ON-Spot Play.png"))); // NOI18N
        jButtonOnSpotPlay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonOnSpotPlayActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonOnSpotPlay, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 450, 220, 30));

        jButtonSnack.setContentAreaFilled(false);
        jButtonSnack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Snack.png"))); // NOI18N
        jButtonSnack.setContentAreaFilled(false);
        jButtonSnack.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Snack.png"))); // NOI18N
        jButtonSnack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSnackActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonSnack, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 510, 150, 40));

        jButtonReports.setContentAreaFilled(false);
        jButtonReports.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/btn isi Reports.png"))); // NOI18N
        jButtonReports.setContentAreaFilled(false);
        jButtonReports.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/efk isi Reports.png"))); // NOI18N
        jButtonReports.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonReportsActionPerformed(evt);
            }
        });
        getContentPane().add(jButtonReports, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 580, 130, 40));

        jTextFieldTotalHarga.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldTotalHarga.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldTotalHarga.setBorder(null);
        jTextFieldTotalHarga.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTotalHargaActionPerformed(evt);
            }
        });
        jTextFieldTotalHarga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTotalHargaKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldTotalHarga, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 583, 320, 40));

        jTextFieldBayar.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldBayar.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldBayar.setBorder(null);
        jTextFieldBayar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBayarKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBayarKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldBayar, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 640, 140, 40));

        jTextFieldKembalian.setFont(new java.awt.Font("Calibri", 1, 24)); // NOI18N
        jTextFieldKembalian.setForeground(new java.awt.Color(255, 255, 255));
        jTextFieldKembalian.setBorder(null);
        jTextFieldKembalian.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldKembalianActionPerformed(evt);
            }
        });
        jTextFieldKembalian.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldKembalianKeyTyped(evt);
            }
        });
        getContentPane().add(jTextFieldKembalian, new org.netbeans.lib.awtextra.AbsoluteConstraints(1197, 640, 140, 40));

        jComboBoxIDPS.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jComboBoxIDPS.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jComboBoxIDPS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxIDPSActionPerformed(evt);
            }
        });
        jComboBoxIDPS.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBoxIDPSKeyReleased(evt);
            }
        });
        getContentPane().add(jComboBoxIDPS, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 520, 90, 40));

        jSpinner1.setFont(new java.awt.Font("Calibri", 1, 20)); // NOI18N
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });
        jSpinner1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jSpinner1KeyReleased(evt);
            }
        });
        getContentPane().add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 402, 110, 40));
        getContentPane().add(jCalendarTanggalSewa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 460, 320, 40));
        getContentPane().add(jCalendarTanggalKembali, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 520, 310, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/pic/bg no sewa pegawai.png"))); // NOI18N
        jLabel1.setText("0f0f9f");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 770));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jCheckBoxKTPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxKTPActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxKTPActionPerformed

    // isian kalkulasi otomatis
    
    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSaveActionPerformed
                                         
    try {
        String idPegawai = SessionLogin.getID_pegawai();
        String idTransaksi = generateAutoID("sewa", "ID_sewa", "TrSP");

        if (idPegawai == null || idPegawai.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID Pegawai tidak valid. Silakan login ulang.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String nama = jTextFieldNama.getText().trim();
        String alamat = jTextFieldAlamat.getText().trim();
        String isi_bayar = jTextFieldBayar.getText().trim();
        int durasi = (Integer) jSpinner1.getValue();

        if (nama.isEmpty() || alamat.isEmpty() || isi_bayar.isEmpty() || durasi == 0) {
            JOptionPane.showMessageDialog(null, "Pastikan Nama, Alamat, dan Bayar tidak kosong, serta durasi sewa tidak 0 jam!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }
                   
      //  String nama = jTextFieldNama.getText().trim();
       // String alamat = jTextFieldAlamat.getText().trim();
        String IDPS = jComboBoxIDPS.getSelectedItem().toString();
        String barang = jTextFieldBarang.getText().trim();

        java.util.Date tanggalSewaUtil = jCalendarTanggalSewa.getDate();
        java.util.Date tanggalKembaliUtil = jCalendarTanggalKembali.getDate();

        if (tanggalSewaUtil == null || tanggalKembaliUtil == null) {
            JOptionPane.showMessageDialog(null, "Tanggal belum dipilih!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        java.sql.Date tanggalSewa = new java.sql.Date(tanggalSewaUtil.getTime());
        java.sql.Date tanggalKembali = new java.sql.Date(tanggalKembaliUtil.getTime());

        String KTP = jCheckBoxKTP.isSelected() ? "ada" : "tidak ada";
        String SuratPernyataan = jCheckBoxSuratPernyataan.isSelected() ? "ada" : "tidak ada";

        long durasiSewa = (tanggalKembali.getTime() - tanggalSewa.getTime()) / (1000 * 60 * 60 * 24);

        // Format angka: hilangkan simbol Rp, titik, dan koma
        String totalStr = jTextFieldTotalHarga.getText().replaceAll("[^0-9]", "");
        String bayarStr = jTextFieldBayar.getText().replaceAll("[^0-9]", "");
        String kembalianStr = jTextFieldKembalian.getText().replaceAll("[^0-9]", "");

        int total = Integer.parseInt(totalStr);
        int bayar = Integer.parseInt(bayarStr);
        int kembalian = Integer.parseInt(kembalianStr);

         if (bayar < total) {
                JOptionPane.showMessageDialog(null, "Uang bayar kurang!");
                return;
            }
        
        String status = "sedang disewa";

        // Simpan ke tabel sewa
        String sql = "INSERT INTO sewa ( Nama, Alamat, ID_playstation, ID_pegawai, Barang_sewa, tgl_sewa, tgl_kembali, Durasi_sewa, total_biaya, bayar, kembalian, status, KTP, Surat_pernyataan, ID_sewa) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement stmt = cobakonek.prepareStatement(sql);
        stmt.setString(1, nama);
        stmt.setString(2, alamat);
        stmt.setString(3, IDPS);
        stmt.setString(4, idPegawai);
        stmt.setString(5, barang);
        stmt.setDate(6, tanggalSewa);
        stmt.setDate(7, tanggalKembali);
        stmt.setLong(8, durasiSewa);
        stmt.setInt(9, total);
        stmt.setInt(10, bayar);
        stmt.setInt(11, kembalian);
        stmt.setString(12, status);
        stmt.setString(13, KTP);
        stmt.setString(14, SuratPernyataan);
        stmt.setString(15, idTransaksi);
        stmt.executeUpdate();
        stmt.close();

        // Update status playstation
        String updateSQL = "UPDATE playstation SET status = ? WHERE ID_playstation = ?";
        PreparedStatement updateStmt = cobakonek.prepareStatement(updateSQL);
        
        String statusPS = "disewa";
        updateStmt.setString(1, statusPS);
        updateStmt.setString(2, IDPS);
        updateStmt.executeUpdate();
        updateStmt.close();

        JOptionPane.showMessageDialog(null, "Data berhasil disimpan!");
        tampilData(); 
        isiComboBoxIDPS();
        clearTextField();

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "SQL Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(null, "Input angka tidak valid!", "Format Error", JOptionPane.ERROR_MESSAGE);
    }
             
    }//GEN-LAST:event_jButtonSaveActionPerformed

    private void jCheckBoxSuratPernyataanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxSuratPernyataanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxSuratPernyataanActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        // Bikin tulisan tabel berada di tengah
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
    
        // Terapkan rata tengah ke semua kolom di tabel
    for (int i = 0; i < jTable1.getColumnCount(); i++) {
    jTable1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    
        // Tampilkan garis tabel antar sel
        jTable1.setShowGrid(true);
        jTable1.setGridColor(Color.LIGHT_GRAY);
        
        // Membuat jTextField transparan
        jTextFieldNama.setOpaque(false);
        jTextFieldNama.setBackground(new Color(0,0,0,0));
        jTextFieldNama.setBorder(null);
        jTextFieldNama.setForeground(Color.WHITE);
        jTextFieldNama.setCaretColor(Color.WHITE);
        
        jTextFieldAlamat.setOpaque(false);
        jTextFieldAlamat.setBackground(new Color(0,0,0,0));
        jTextFieldAlamat.setBorder(null);
        jTextFieldAlamat.setForeground(Color.WHITE);
        jTextFieldAlamat.setCaretColor(Color.WHITE);
        
      //  jTextFieldIDPS.setOpaque(false);
      //  jTextFieldIDPS.setBackground(new Color(0,0,0,0));
      //  jTextFieldIDPS.setBorder(null);
      //  jTextFieldIDPS.setForeground(Color.WHITE);
      //  jTextFieldIDPS.setCaretColor(Color.WHITE);
        
        jTextFieldBarang.setOpaque(false);
        jTextFieldBarang.setBackground(new Color(0,0,0,0));
        jTextFieldBarang.setBorder(null);
        jTextFieldBarang.setForeground(Color.WHITE);
        jTextFieldBarang.setCaretColor(Color.WHITE);
        
        jCalendarTanggalSewa.setOpaque(false);
        jCalendarTanggalSewa.setBackground(new Color(0,0,0,0));
        jCalendarTanggalSewa.setBorder(null);
        jCalendarTanggalSewa.setForeground(Color.WHITE);
        
        jCalendarTanggalKembali.setOpaque(false);
        jCalendarTanggalKembali.setBackground(new Color(0,0,0,0));
        jCalendarTanggalKembali.setBorder(null);
        jCalendarTanggalKembali.setForeground(Color.WHITE);
        
        
        jCheckBoxKTP.setOpaque(false);
        jCheckBoxKTP.setBackground(new Color(0,0,0,0));
        jCheckBoxKTP.setBorder(null);
        jCheckBoxKTP.setForeground(Color.WHITE);
        
        jCheckBoxSuratPernyataan.setOpaque(false);
        jCheckBoxSuratPernyataan.setBackground(new Color(0,0,0,0));
        jCheckBoxSuratPernyataan.setBorder(null);
        jCheckBoxSuratPernyataan.setForeground(Color.WHITE);

        jTextFieldTotalHarga.setOpaque(false);
        jTextFieldTotalHarga.setBackground(new Color(0,0,0,0));
        jTextFieldTotalHarga.setBorder(null);
        jTextFieldTotalHarga.setForeground(Color.WHITE);
        jTextFieldTotalHarga.setCaretColor(Color.WHITE);
        
        jTextFieldBayar.setOpaque(false);
        jTextFieldBayar.setBackground(new Color(0,0,0,0));
        jTextFieldBayar.setBorder(null);
        jTextFieldBayar.setForeground(Color.WHITE);
        jTextFieldBayar.setCaretColor(Color.WHITE);
        
        jTextFieldKembalian.setOpaque(false);
        jTextFieldKembalian.setBackground(new Color(0,0,0,0));
        jTextFieldKembalian.setBorder(null);
        jTextFieldKembalian.setForeground(Color.WHITE);
        jTextFieldKembalian.setCaretColor(Color.WHITE);
        
        jTextFieldBarang.setOpaque(false);
        jTextFieldBarang.setBackground(new Color(0,0,0,0));
        jTextFieldBarang.setBorder(null);
        jTextFieldBarang.setForeground(Color.WHITE);
        jTextFieldBarang.setCaretColor(Color.WHITE);
        
    }//GEN-LAST:event_formWindowOpened

    private void jButtonHomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHomeActionPerformed
           /* homePegawai home = new homePegawai();
            home.setVisible(true);
            this.dispose();*/
    }//GEN-LAST:event_jButtonHomeActionPerformed

    private void jButtonOnSpotPlayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonOnSpotPlayActionPerformed
           /* transaksiMainDitempat home = new transaksiMainDitempat();
            home.setVisible(true);
            this.dispose(); */
    }//GEN-LAST:event_jButtonOnSpotPlayActionPerformed

    private void jButtonLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLogoutActionPerformed
     /*   int confirm = JOptionPane.showConfirmDialog(
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
            } */
    }//GEN-LAST:event_jButtonLogoutActionPerformed

    private void jButtonReportsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonReportsActionPerformed
        LprnSewa nw_transaksi = new LprnSewa();
        nw_transaksi.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonReportsActionPerformed

    private void jTextFieldAlamatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldAlamatActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldAlamatActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        int row = jTable1.rowAtPoint(evt.getPoint());
        int col = jTable1.columnAtPoint(evt.getPoint());

        String columnName = jTable1.getColumnName(col);
        String valueKTP = jTable1.getValueAt(row, jTable1.getColumnModel().getColumnIndex("KTP")).toString();
        String valueStatus = jTable1.getValueAt(row, jTable1.getColumnModel().getColumnIndex("Status")).toString();

        if ("KTP".equals(columnName) && "ada".equalsIgnoreCase(valueKTP)) {
            int jawab = JOptionPane.showConfirmDialog(null,
                    "Apakah KTP telah dikembalikan?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);
            if (jawab == JOptionPane.YES_OPTION) {
                jTable1.setValueAt("dikembalikan", row, col); // Update tampilan tabel
                updateDatabase(row, "KTP");
                isiComboBoxIDPS();
            }

        } else if ("Status".equals(columnName) && "sedang disewa".equalsIgnoreCase(valueStatus)) {
            int jawab = JOptionPane.showConfirmDialog(null,
                    "Yakin ingin mengubah status menjadi DIKEMBALIKAN?", "Konfirmasi",
                    JOptionPane.YES_NO_OPTION);
            if (jawab == JOptionPane.YES_OPTION) {
                jTable1.setValueAt("dikembalikan", row, col); // Update tampilan tabel
                updateDatabase(row, "Status");
                isiComboBoxIDPS();
            }
        } else if (col == 0) { // Kolom ID
                String idSewa = (String) jTable1.getValueAt(row, 0);
                int confirmDelete = JOptionPane.showConfirmDialog(this,
                        "Apakah Anda yakin ingin menghapus data dengan ID Sewa " + idSewa + "?",
                        "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);

                if (confirmDelete == JOptionPane.YES_OPTION) {
                    deleteData(idSewa);
                    showdata(); // Refresh isi tabel
                    isiComboBoxIDPS();
                }
            }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTextFieldTotalHargaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTotalHargaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTotalHargaActionPerformed

    private void jTextFieldNamaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNamaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNamaActionPerformed

    private void jButtonSnackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSnackActionPerformed
       /* transaksiSnack nw_transaksi = new transaksiSnack();
        nw_transaksi.setVisible(true);
        this.dispose();*/
    }//GEN-LAST:event_jButtonSnackActionPerformed

    private void jTextFieldKembalianActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldKembalianActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldKembalianActionPerformed

    private void jTextFieldBayarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBayarKeyReleased
        hitungkembalian();
    }//GEN-LAST:event_jTextFieldBayarKeyReleased

    private void jComboBoxIDPSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxIDPSActionPerformed
        if (jComboBoxIDPS.getSelectedItem() != null) {
            tampilkanNamaBarang();
        }    
    }//GEN-LAST:event_jComboBoxIDPSActionPerformed

    private void jComboBoxIDPSKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBoxIDPSKeyReleased

    }//GEN-LAST:event_jComboBoxIDPSKeyReleased

    private void jSpinner1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jSpinner1KeyReleased
        
    }//GEN-LAST:event_jSpinner1KeyReleased

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        if (jComboBoxIDPS.getSelectedItem() != null) {
            int total = hitungTotalHarga();
            jTextFieldTotalHarga.setText(String.valueOf(total));
        } else {
            jTextFieldTotalHarga.setText("0");
        }
    }//GEN-LAST:event_jSpinner1StateChanged

    private void jTextFieldTotalHargaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTotalHargaKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // Cegah input selain angka
        }
    }//GEN-LAST:event_jTextFieldTotalHargaKeyTyped

    private void jTextFieldBayarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBayarKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // Cegah input selain angka
        }
    }//GEN-LAST:event_jTextFieldBayarKeyTyped

    private void jTextFieldKembalianKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldKembalianKeyTyped
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '\b') {
            evt.consume(); // Cegah input selain angka
        }
    }//GEN-LAST:event_jTextFieldKembalianKeyTyped

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
            java.util.logging.Logger.getLogger(TrSewaPegawai.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TrSewaPegawai().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonHome;
    private javax.swing.JButton jButtonLogout;
    private javax.swing.JButton jButtonOnSpotPlay;
    private javax.swing.JButton jButtonReports;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JButton jButtonSnack;
    private com.toedter.calendar.JDateChooser jCalendarTanggalKembali;
    private com.toedter.calendar.JDateChooser jCalendarTanggalSewa;
    private javax.swing.JCheckBox jCheckBoxKTP;
    private javax.swing.JCheckBox jCheckBoxSuratPernyataan;
    private javax.swing.JComboBox<String> jComboBoxIDPS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldAlamat;
    private javax.swing.JTextField jTextFieldBarang;
    private javax.swing.JTextField jTextFieldBayar;
    private javax.swing.JTextField jTextFieldKembalian;
    private javax.swing.JTextField jTextFieldNama;
    private javax.swing.JTextField jTextFieldTotalHarga;
    // End of variables declaration//GEN-END:variables

        // Method untuk menampilkan data dari database ke jTabel 
   private void showdata() {
    if (cobakonek == null) {
        System.out.println("Koneksi database tidak tersedia.");
        return;
    }
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0);
        try {
            String sql = "SELECT * FROM sewa"; //WHERE ID_Pegawai = ? ---- ini kalau difilter pake sesi login aktif
            ps = cobakonek.prepareStatement(sql); //ps.setInt(1, SessionLogin.getID_pegawai()); // Panggil dari session
            rs = ps.executeQuery();

            while (rs.next()) {
                String No = rs.getString("ID_sewa");
                String ID_Pegawai = rs.getString("ID_pegawai");
                String Nama = rs.getString("Nama");
                String Alamat = rs.getString("Alamat");
                String ID_Playstation = rs.getString("ID_playstation");
                String Barang = rs.getString("Barang_sewa");
                String TanggalSewa = rs.getString("tgl_sewa");
                String TanggalKembali = rs.getString("tgl_kembali");
                String KTP = rs.getString("KTP");
                String SuratPernyataan = rs.getString("Surat_pernyataan");
                String Status = rs.getString("Status");

                Object[] Rowdata = {No, ID_Pegawai, Nama, Alamat, ID_Playstation, Barang, TanggalSewa, TanggalKembali, KTP, SuratPernyataan, Status};
                model.addRow(Rowdata);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    
   
   
    // Method untuk membersihkan tulisan yg ada pada jTextField dan jComboBox
    private void clearTextField() {
        jTextFieldNama.setText("");
        jTextFieldAlamat.setText("");
        jTextFieldBarang.setText("");
        jTextFieldBayar.setText("");
        jTextFieldKembalian.setText("");
        jCalendarTanggalSewa.setDate(new java.util.Date());
        jCalendarTanggalKembali.setDate(new java.util.Date());
        jCheckBoxKTP.setSelected(false);
        jCheckBoxSuratPernyataan.setSelected(false);
        jSpinner1.setValue(0);

    }

        private void isiComboBoxIDPS() {
            try {
                String query = "SELECT ID_playstation FROM playstation " +
             "WHERE kategori = 'Sewa' AND kondisi = 'normal' AND status = 'kosong'";
                PreparedStatement stmt = cobakonek.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();

                jComboBoxIDPS.removeAllItems(); // Bersihin dulu
                
                while (rs.next()) {
                    String id = rs.getString("ID_playstation");
                    jComboBoxIDPS.addItem(id);
                }

                // ✅ Cukup satu kali saja
                if (jComboBoxIDPS.getItemCount() > 0) {
                    jComboBoxIDPS.setSelectedIndex(0);         // Pilih item pertama
                   tampilkanNamaBarang();                     // Tampilkan nama barang
                    int total = hitungTotalHarga();            // Hitung total harga
                    jTextFieldTotalHarga.setText(String.valueOf(total)); // Tampilkan ke TextField
                }

                rs.close();
                stmt.close();
            } catch (SQLException ex) {
                Logger.getLogger(TrSewaPegawai.class.getName()).log(Level.SEVERE, null, ex);
            }
        }



    private void tampilkanNamaBarang() {
        try {
            String idPS = jComboBoxIDPS.getSelectedItem().toString();
            String query = "SELECT Nama_barang FROM playstation WHERE ID_playstation = ?";
            PreparedStatement stmt = cobakonek.prepareStatement(query);
            stmt.setString(1, idPS);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String namaBarang = rs.getString("Nama_barang");
                jTextFieldBarang.setText(namaBarang);
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menampilkan nama barang: " + e.getMessage());
        }
    }

    private void updateDatabase(int row, String kolom) {
        try {
            if ("Status".equals(kolom)) {
                String sql = "UPDATE sewa SET status = 'dikembalikan' WHERE ID_sewa = ?";
                PreparedStatement ps = cobakonek.prepareStatement(sql);
                String ID_sewa = (String) jTable1.getValueAt(row, 0); // Kolom 0: ID_sewa
                ps.setString(1, ID_sewa);
                ps.executeUpdate();
                ps.close();

                String selesai = "UPDATE playstation SET status = 'kosong' WHERE ID_playstation = ?";
                PreparedStatement pstSelesai = cobakonek.prepareStatement(selesai);
                String idPS = (String) jTable1.getValueAt(row, 4); // Kolom 4: ID_playstation
                pstSelesai.setString(1, idPS);
                pstSelesai.executeUpdate();
                pstSelesai.close();

            } else if ("KTP".equals(kolom)) {
                String sql = "UPDATE sewa SET ktp = 'dikembalikan' WHERE ID_sewa = ?";
                PreparedStatement ps = cobakonek.prepareStatement(sql);
                String ID_sewa = (String) jTable1.getValueAt(row, 0); // Kolom 0: ID_sewa
                ps.setString(1, ID_sewa);
                ps.executeUpdate();
                ps.close();
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteData(String idSewa) {
        int selectedRow = jTable1.getSelectedRow();
        String status = jTable1.getValueAt(selectedRow, 10).toString();

        if (status.equalsIgnoreCase("sedang disewa")) {
            JOptionPane.showMessageDialog(this, "Data tidak bisa dihapus karena status sedang disewa.");
        } else {
            try {
                String sql = "DELETE FROM sewa WHERE ID_sewa = ?";
                PreparedStatement ps = cobakonek.prepareStatement(sql);
                ps.setString(1, idSewa);
                ps.executeUpdate();
                ps.close();
                JOptionPane.showMessageDialog(this, "Data berhasil dihapus.");
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error saat menghapus data: " + e.getMessage());
            }
        }
    }

    
}
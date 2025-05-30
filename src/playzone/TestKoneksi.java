package playzone;
import javax.swing.JOptionPane;

public class TestKoneksi {
    public static void main(String[] args) {
        try {
           if (Koneksi_db.configDB() != null) {
              JOptionPane.showMessageDialog(null, "Koneksi ke database sukses!", "Informasi", JOptionPane.INFORMATION_MESSAGE);
           }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi gagal : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}


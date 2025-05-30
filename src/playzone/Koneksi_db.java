package playzone;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

public class Koneksi_db {
    public static Connection koneksi;
    public static Connection configDB(){
            try {
                String url ="jdbc:mysql://localhost:3306/playzonee";
                String user ="root";
                String password ="";

                koneksi =DriverManager.getConnection(url, user, password);
            }catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Koneksi gagal : " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            return koneksi;
    }
}

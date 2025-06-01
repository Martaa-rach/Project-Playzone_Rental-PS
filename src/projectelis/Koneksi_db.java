package projectelis;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Koneksi_db {
    public static Connection koneksi;
    public static Connection configDB(){
            try {
                String url ="jdbc:mysql://localhost:3306/playzonee";
                String user ="root";
                String password ="";
                /*DriverManager registerDriver = */ //DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                koneksi =DriverManager.getConnection(url, user, password);
            }catch (Exception e) {
                System.err.println("koneksi gagal" +e.getMessage());
            }
            return koneksi;
    }
}

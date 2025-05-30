/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package projectelis;

import projectelis.Koneksi_db;
import java.sql.Connection;
import java.util.HashMap;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import projectelis.Koneksi_db;

/**
 *
 * @author USER
 */
public class repostDAO {
    Connection con;
    Koneksi_db DB = new Koneksi_db();

    public repostDAO() {
        con = DB.configDB(); // pastikan method ini ada di class Koneksi_db
    }

    public void printStruk(String idTr) {
        try {
            String reportPath = "src/playzone/StrukSnack.jasper";
            
            HashMap<String, Object> parameters = new HashMap<>();
            parameters.put("IdPRIMARY", idTr);
            
            JasperPrint print = JasperFillManager.fillReport(reportPath, parameters, con);
            JasperViewer viewer = new JasperViewer(print, false);
            viewer.setExtendedState(JasperViewer.MAXIMIZED_BOTH);
            viewer.setVisible(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Print error: " + e.getMessage());
        }
    }
}

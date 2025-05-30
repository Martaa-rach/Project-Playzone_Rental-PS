
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package playzone;

/**
 *
 * @author User
 */

public class SessionLogin {
    private static String ID_pegawai;
    private static String nama_pegawai;
    public static String idAkun;
    public static String username;
    public static String level;

    public static void setIDPegawai(String id) {
        ID_pegawai = id;
    }

    public static String getID_pegawai() {
        return ID_pegawai;
    }

    public static void setNamaPegawai(String nama) {
        nama_pegawai = nama;
    }

    public static String getNamaPegawai() {
        return nama_pegawai;
    }
    
    public static void clearSession() {
        ID_pegawai = "";
        nama_pegawai = null;
    }
}
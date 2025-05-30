package playzone;

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
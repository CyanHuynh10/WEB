package vn.iotstar.connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    private final String serverName = "localhost";
    private final String dbName = "LTWeb_DB";
    private final String portNumber = "1433";
    private final String instance = ""; // Bỏ trống nếu là SQL Server default instance
    private final String userID = "sa";
    private final String password = "2006";

    public Connection getConnection() throws Exception {
        // Cấu hình mã hóa bảo mật của driver bản mới
        String url = "jdbc:sqlserver://" + serverName + ":" + portNumber + "\\" + instance + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
        if (instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName + ";encrypt=true;trustServerCertificate=true;";
        }
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        return DriverManager.getConnection(url, userID, password);
    }
    
    // Hàm test thử kết nối
    public static void main(String[] args) {
        try {
            System.out.println(new DBConnection().getConnection());
            System.out.println("Kết nối thành công tới LTWeb_DB!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
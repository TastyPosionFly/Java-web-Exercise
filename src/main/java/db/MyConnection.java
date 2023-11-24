package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    private static final String DATABASE_HOME = "localhost";
    private static final String DATABASE_NAME = "JavaWeb";
    private static final String username = "JavaWebWorker";
    private static final String password = "Ww13501675983";
    private static final String dbUrl = "jdbc:sqlserver://"+DATABASE_HOME+":1433;databaseName="+DATABASE_NAME+";user="+username+";password="+password+";encrypt=false;";
    public static Connection getConnection(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            return DriverManager.getConnection(dbUrl);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println("数据库未连接");
            throw new RuntimeException(e);
        }
    }
}

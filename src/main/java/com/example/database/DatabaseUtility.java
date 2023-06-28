package com.example.database;
import javax.crypto.SecretKey;
import java.sql.*;

import static com.example.encryption.Encryption.*;

public class DatabaseUtility {

    private static SecretKey secretKey;
    private static byte[] iv;
    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/Dynamic_Processor";
        String username = "root";
        String password = "new_password";
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Connection con = DriverManager.getConnection(url,username,password);
        con.setAutoCommit(false);
        return con;
    }

    public static void createTable() throws SQLException {
        secretKey = generateKey();
        iv = generateIV();
        Connection con = getConnection();
        Statement statement = con.createStatement();
        createHistoryTable(con, statement);
        createLoginTable(con, statement);
        con.commit();
        statement.close();
        con.close();
    }

    private static void createHistoryTable(Connection con, Statement statement) throws SQLException {
        String dropTableQuery = "DROP TABLE IF EXISTS history";
        statement.executeUpdate(dropTableQuery);
        String createTableQuery = "CREATE TABLE IF NOT EXISTS history (id INTEGER AUTO_INCREMENT PRIMARY KEY, fileName VARCHAR(255), columnName VARCHAR(255), average VARCHAR(255), sum VARCHAR(255))";
        statement.executeUpdate(createTableQuery);
    }

    private static void createLoginTable(Connection con, Statement statement) throws SQLException {
        String dropTableQuery = "DROP TABLE IF EXISTS login";
        statement.executeUpdate(dropTableQuery);
        String createTableQuery = "CREATE TABLE IF NOT EXISTS login (username VARCHAR(255) PRIMARY KEY, password VARCHAR(255))";
        statement.executeUpdate(createTableQuery);
        String insertQuery = "INSERT INTO login (username, password) VALUES ('test', ?)";
        PreparedStatement preparedStatement = con.prepareStatement(insertQuery);
        preparedStatement.setString(1, encrypt("test", secretKey, iv));
        preparedStatement.executeUpdate();
    }

    public static void applyQuery(String fileName, String columnName, Double average, Double sum) {
        try {
            Connection con = getConnection();
            String query = "INSERT INTO history (fileName, columnName, average, sum) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, fileName);
            statement.setString(2, columnName);
            statement.setDouble(3, average);
            statement.setDouble(4, sum);
            statement.executeUpdate();
            con.commit();
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean checkLogin(String username, String password){
        try {
            ResultSet result = getTable("login");
            while (result.next()){
                String usernameDatabase = result.getString("username");
                String encryptedPasswordDatabase = result.getString("password");
                String passwordDatabase = decrypt(encryptedPasswordDatabase, secretKey, iv);
                if (usernameDatabase.equals(username) && passwordDatabase.equals(password)){
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void updateQuery(String fileName, String column, Double value, boolean isAvg){
        try {
            Connection con = getConnection();
            String query = null;
            if (isAvg){
                query = "UPDATE history SET average = ? WHERE fileName = ? AND columnName = ?";
            } else {
                query = "UPDATE history SET sum = ? WHERE fileName = ? AND columnName = ?";
            }
            PreparedStatement statement = con.prepareStatement(query);
            statement.setDouble(1, value);
            statement.setString(2, fileName);
            statement.setString(3, column);
            statement.executeUpdate();
            con.commit();
            statement.close();
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static boolean ifRecordExists(String fileNameNew, String columnNameNew) throws SQLException {
        Connection con = getConnection();
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM history");
        while (result.next()){
            String fileNameOld = result.getString("fileName");
            String columnNameOld = result.getString("columnName");
            if (fileNameOld.equals(fileNameNew) && columnNameOld.equals(columnNameNew)){
                result.close();
                statement.close();
                con.close();
                return true;
            }
        }
        result.close();
        statement.close();
        con.close();
        return false;
    }

    public static ResultSet getTable(String tableName) throws SQLException{
        Connection con = getConnection();
        Statement statement = con.createStatement();
        return statement.executeQuery("SELECT * FROM " + tableName);
    }

}
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DBUtil {

    private static Connection connection;

    public static Connection getConnection() {
        try {
            connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/shopeasy",
                "root",
                "root123"   // use your password
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static String getUser(String username) {

        String query = "SELECT username FROM users WHERE username='" + username + "'";
        String result = "";

        try {
            System.out.println("Connecting to DB...");
            Connection conn = getConnection();

            if (conn == null) {
                System.out.println("DB connection is NULL ❌");
                return "";
            } else {
                System.out.println("DB Connected ✔");
            }

            System.out.println("Executing query: " + query);

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                result = rs.getString("username");
                System.out.println("DB Result: " + result);
            }

            if (result.isEmpty()) {
                System.out.println("No data found in DB ❌");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}
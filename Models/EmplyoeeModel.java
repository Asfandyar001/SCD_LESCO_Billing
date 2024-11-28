
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmplyoeeModel {
    public EmplyoeeModel() {
    }

    public static boolean validateEmployee(String username, String pass) {
        String query = "SELECT Password FROM EmployeesData WHERE Username = ?";

        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("Password");
                return storedPassword.equals(pass);
            }
        } catch (SQLException var7) {
            SQLException e = var7;
            System.out.println("Error: Database connection or query failed: " + e.getMessage());
        }

        return false;
    }

    public static void updatePass(String username, String newPass) {
        String query = "UPDATE EmployeesData SET Password = ? WHERE Username = ?";

        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, newPass);
            pstmt.setString(2, username);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Password updated successfully for username: " + username);
            } else {
                System.out.println("Username not found: " + username);
            }
        } catch (SQLException var6) {
            SQLException e = var6;
            System.out.println("Error: Database connection or query failed: " + e.getMessage());
        }

    }
}

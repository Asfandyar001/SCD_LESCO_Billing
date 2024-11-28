
package Models;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class BillingModel {
    public BillingModel() {
    }
    public static boolean addBillVerification1(String custid,String month){
        String sql = "SELECT * FROM BillingInfo WHERE CustomerID = ? AND CurrentMonth = ?";
        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement  stmt = conn.prepareStatement(sql);

            stmt.setInt(1, Integer.parseInt(custid));  // Setting the CustomerID value
            stmt.setString(2, month);   // Setting the Month value

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
               return true;

            }

        } catch (SQLException e) {

            e.printStackTrace();
        }
        return false;

    }
    public static ArrayList<String> getBillforAdd(){
        String sql = "SELECT * FROM BillingInfo";
        ArrayList<String> list = new ArrayList<>();

        try {

            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();


            while (rs.next()) {

                String[] data = new String[12];


                data[0] = String.valueOf(rs.getInt("CustomerID"));
                data[1] = rs.getString("CurrentMonth");
                data[2] = rs.getString("CurrentRegularUnitsConsumed");
                data[3] = rs.getString("CurrentPeakHourUnitsConsumed");
                data[4] = rs.getString("EntryDate");
                data[5] = String.valueOf(rs.getDouble("ElectricityCost"));
                data[6] = String.valueOf(rs.getDouble("SalesTaxAmount"));
                data[7] = String.valueOf(rs.getDouble("FixedCharges"));
                data[8] = String.valueOf(rs.getDouble("TotalAmount"));  // Persisted total
                data[9] = rs.getString("DueDate");
                data[10] = rs.getString("Status");
                data[11] = rs.getString("PaymentDate");


                String row = String.join(",", data);
                list.add(row);
            }

            rs.close();
            stmt.close();
            conn.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static void addBill(int customerID, String currentMonth, String regularUnits, String peakUnits,
                               String entryDate, double electricityCost, double salesTax, double fixedCharges,
                               String dueDate, String status, String paymentDate) {
        String sql = "INSERT INTO BillingInfo (CustomerID, CurrentMonth, CurrentRegularUnitsConsumed, " +
                "CurrentPeakHourUnitsConsumed, EntryDate, ElectricityCost, SalesTaxAmount, FixedCharges, " +
                "DueDate, Status, PaymentDate) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);


            stmt.setInt(1, customerID);
            stmt.setString(2, currentMonth);
            stmt.setString(3, regularUnits);
            stmt.setString(4, peakUnits);
            stmt.setString(5, entryDate);
            stmt.setDouble(6, electricityCost);
            stmt.setDouble(7, salesTax);
            stmt.setDouble(8, fixedCharges);
            stmt.setString(9, dueDate);
            stmt.setString(10, status);
            stmt.setString(11, paymentDate);

            int rowsAffected = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteBill(String id, String month, String eDate) {

        String sql = "DELETE FROM BillingInfo WHERE CustomerID = ? AND CurrentMonth = ? AND EntryDate = ?";

        try {

            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, id);
            stmt.setString(2, month);
            stmt.setString(3, eDate);


            int rowsAffected = stmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void editBill(String str){
        String[] data = str.trim().split(",");
        String sql = "UPDATE BillingInfo SET " +
                "CurrentRegularUnitsConsumed = ?, " +
                "CurrentPeakHourUnitsConsumed = ?, " +
                "ElectricityCost = ?, " +
                "SalesTaxAmount = ?, " +
                "FixedCharges = ?, " +
                "DueDate = ?, " +
                "Status = ?, " +
                "PaymentDate = ? " +
                "WHERE CustomerID = ? AND CurrentMonth = ? AND EntryDate = ?";

        try {
            Connection con= DataBaseConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setString(1, data[2]);
            pstmt.setString(2, data[3]);
            pstmt.setString(3, data[5]);
            pstmt.setString(4, data[6]);
            pstmt.setString(5, data[7]);
            pstmt.setString(6, data[9]);
            pstmt.setString(7, data[10]);
            pstmt.setString(8, data[11]);

            pstmt.setInt(9, Integer.parseInt(data[0]));
            pstmt.setString(10, data[1]);
            pstmt.setString(11, data[4]);

            int rowsAffected = pstmt.executeUpdate();
            System.out.println("Rows affected: " + rowsAffected);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static boolean isAccessAble(String id, String month, String eDate){
        ArrayList<String> list=new ArrayList<>();
        String Sql="Select * from BillingInfo where CustomerID = ? AND CurrentMonth=? AND EntryDate =?";
        try(Connection conn=DataBaseConnection.getConnection();
        PreparedStatement pstmt= conn.prepareStatement(Sql)){
            pstmt.setString(1, id);
            pstmt.setString(2, month);
            pstmt.setString(3, eDate);
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
                String[] data = new String[12];


                data[0] = String.valueOf(rs.getInt("CustomerID"));
                data[1] = rs.getString("CurrentMonth");
                data[2] = rs.getString("CurrentRegularUnitsConsumed");
                data[3] = rs.getString("CurrentPeakHourUnitsConsumed");
                data[4] = rs.getString("EntryDate");
                data[5] = String.valueOf(rs.getDouble("ElectricityCost"));
                data[6] = String.valueOf(rs.getDouble("SalesTaxAmount"));
                data[7] = String.valueOf(rs.getDouble("FixedCharges"));
                data[8] = String.valueOf(rs.getDouble("TotalAmount"));
                data[9] = rs.getString("DueDate");
                data[10] = rs.getString("Status");
                data[11] = rs.getString("PaymentDate");
                if(data[10].trim().equals("UnPaid"))
                    return false;

                String row = String.join(",", data);
                list.add(row);
            }

        }
        catch (SQLException e){

        }
        return true;

     }
    public static ArrayList<String> viewSearchedBills(String search){
        ArrayList<String> list=new ArrayList<>();

        String sql="Select * from BillingInfo";

        try(Connection con=DataBaseConnection.getConnection();
        PreparedStatement pstmt=con.prepareStatement(sql)){

            ResultSet rs=pstmt.executeQuery();

            while(rs.next()) {
                String[] data = new String[12];


                data[0] = String.valueOf(rs.getInt("CustomerID"));
                data[1] = rs.getString("CurrentMonth");
                data[2] = rs.getString("CurrentRegularUnitsConsumed");
                data[3] = rs.getString("CurrentPeakHourUnitsConsumed");
                data[4] = rs.getString("EntryDate");
                data[5] = String.valueOf(rs.getDouble("ElectricityCost"));
                data[6] = String.valueOf(rs.getDouble("SalesTaxAmount"));
                data[7] = String.valueOf(rs.getDouble("FixedCharges"));
                data[8] = String.valueOf(rs.getDouble("TotalAmount"));
                data[9] = rs.getString("DueDate");
                data[10] = rs.getString("Status");
                data[11] = rs.getString("PaymentDate");
                String row = String.join(",", data);

                if(search.equals("Search") || search.isEmpty()){
                    list.add(row);
                }
                else if(data[0].equals(search) || data[1].equals(search) || data[2].equals(search) || data[3].equals(search) || data[4].equals(search) || data[5].equals(search) || data[6].equals(search) || data[7].equals(search) || data[8].equals(search) || data[9].equals(search) || data[10].equals(search) || data[11].equals(search) )
                {
                    list.add(row);
                }
            }

        }
        catch (SQLException e){

        }
        return list;
    }
    public static void changePaidstatus(String paymentDate,String id,String billingmonth,String eDate){
        String sql="Update BillingInfo Set Status='Paid', PaymentDate= ? WHERE CustomerID=? AND CurrentMonth=? AND EntryDate=?";
        try(Connection con=DataBaseConnection.getConnection();
        PreparedStatement pstmt=con.prepareStatement(sql)){
            pstmt.setString(1, paymentDate);
            pstmt.setInt(2, Integer.parseInt(id));
            pstmt.setString(3, billingmonth);
            pstmt.setString(4, eDate);
            pstmt.executeUpdate();
            System.out.println("Updated");

        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
    public static void updateCustomerFile(String custId,String RUC,String phuc){
        String sql="Update CustomerInfo Set CurrentRegularUnitsConsumed=?, CurrentPeakHourUnitsConsumed=? Where CustomerID=?";
                try(Connection con=DataBaseConnection.getConnection();
                PreparedStatement pstmt=con.prepareStatement(sql)){
                    pstmt.setString(1,RUC);
                    pstmt.setString(2,phuc);
                    pstmt.setString(3,custId);
                    pstmt.executeUpdate();
                }
                catch (SQLException E){

                }
    }
    public static String[] getBill(String id, String month, String entryDate) {

        String query = "SELECT * FROM BillingInfo WHERE CustomerID = ? AND CurrentMonth = ? AND EntryDate = ?";
        String[] customerDetails = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.setString(2, month);
            stmt.setString(3, entryDate);


            ResultSet rs = stmt.executeQuery();


            if (rs.next()) {
                // Retrieve details into a String array
                customerDetails = new String[]{
                        rs.getString("CustomerID"),
                        rs.getString("CurrentMonth"),
                        rs.getString("CurrentRegularUnitsConsumed"),
                        rs.getString("CurrentPeakHourUnitsConsumed"),
                        rs.getString("EntryDate"),
                        rs.getString("ElectricityCost"),
                        rs.getString("SalesTaxAmount"),
                        rs.getString("FixedCharges"),
                        rs.getString("TotalAmount"),
                        rs.getString("DueDate"),
                        rs.getString("Status"),
                        rs.getString("PaymentDate")
                };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerDetails;
    }

}

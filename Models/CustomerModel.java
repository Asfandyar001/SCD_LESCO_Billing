
package Models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CustomerModel {
    public static String userName = "";
    public static String[] custInfo = new String[12];
    public static String[] billInfo = new String[]{"Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found", "Not Found"};

    public CustomerModel() {
    }

    public static boolean isCustomerValid(String id, String cnic) {
        boolean valid = false;
        String query = "SELECT Name FROM CustomerInfo WHERE CustomerID = ? AND CNIC = ?";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(query)) {

            pstmt.setString(1, id);
            pstmt.setString(2, cnic);


            ResultSet rs = pstmt.executeQuery();


            if (rs.next()) {
                valid = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return valid;
    }

    public static boolean addCustomer(String id, String cnic, String name, String address, String phone, String custType, String meterType, String date, String RUC, String PHUC) {
        String query = "INSERT INTO CustomerInfo (CustomerID,CNIC, Name, Address, Phone, CustomerType, MeterType, EntryDate, RegularUnitsConsumed, PeakHourUnitsConsumed)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, id);
            stmt.setString(2, cnic);
            stmt.setString(3, name);
            stmt.setString(4, address);
            stmt.setString(5, phone);
            stmt.setString(6, custType);
            stmt.setString(7, meterType);
            stmt.setString(8, date);
            stmt.setString(9, RUC);
            stmt.setString(10, PHUC);


            int rowsAffected = stmt.executeUpdate();

            return rowsAffected > 0;
        }
        catch (SQLException e){
            e.printStackTrace();

        }
        return false;
    }
    public static String[] getBill(String customerId, String year) {

  String query = "SELECT * FROM BillingInfo " +
                "WHERE CustomerID = ? AND YEAR(CONVERT(DATE, DueDate, 103)) = ?";
        String[] billDetails = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, customerId);
            stmt.setString(2, year);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {

                billDetails = new String[]{
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

        return billDetails;
    }

    public static String[] getCustomer(String id){
        String query = "SELECT * FROM CustomerInfo WHERE CustomerID = ?";
        String[] customerDetails = null;

        try (Connection conn =DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the CustomerID parameter
            stmt.setString(1, id);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Check if a result is found
            if (rs.next()) {
                // Retrieve details into a String array
                customerDetails = new String[]{
                        rs.getString("CustomerID"),
                        rs.getString("CNIC"),
                        rs.getString("Name"),
                        rs.getString("Address"),
                        rs.getString("Phone"),
                        rs.getString("CustomerType"),
                        rs.getString("MeterType"),
                        rs.getString("EntryDate"),
                        rs.getString("RegularUnitsConsumed"),
                        rs.getString("PeakHourUnitsConsumed")
                };
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return customerDetails;
    }
    public static boolean validateCustomer(String id, String cnic, String month, int year) {
        boolean valid = false;
        String billQuery = "SELECT * FROM CustomerInfo WHERE CustomerID = ? AND CNIC = ?";

        try {
            Connection conn = DataBaseConnection.getConnection();
            PreparedStatement ptsmt= conn.prepareStatement(billQuery);
            ptsmt.setString(1, id);
            ptsmt.setString(2,cnic);
            ResultSet rs= ptsmt.executeQuery();
            if(rs.next()){
                return true;
            }
            else
                return false;

        }
        catch (SQLException e){
            System.out.println("Bill Couldn't be fetched for a Specific Customer");
        }

        return valid;
    }

    public static ArrayList<String> viewExpireCnic() {
        String query = "SELECT * FROM NADRADBfile WHERE " +
                "CAST(CONVERT(DATE, ExpiryDate, 103) AS DATETIME) <= DATEADD(DAY, 30, GETDATE())";

        ArrayList<String> resultList = new ArrayList<>();

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // Process the result set
            while (rs.next()) {
                String[] record = new String[3];
                record[0] = rs.getString("UniqueCode");  // Get UniqueCode
                record[1] = rs.getString("IssueDate");  // Get IssueDate
                record[2] = rs.getString("ExpiryDate");
                String record2=record[0]+','+record[1]+','+record[2];
                resultList.add(record2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultList;

    }

    public static boolean searchNadraFile(String cnic){
        String query = "SELECT 1 FROM NADRADBfile WHERE UniqueCode = ?";
        boolean exists = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, cnic);


            ResultSet rs = stmt.executeQuery();


            exists = rs.next();

        } catch (Exception e) {
            System.out.println("Cnic abnormality of a specific user");
        }

        return exists;
    }
    public static boolean updateExpiryDate(String cnic, String newExpiryDate) {
        String query = "UPDATE NADRADBfile SET ExpiryDate = ? WHERE UniqueCode = ?";
        boolean isUpdated = false;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, newExpiryDate);
            stmt.setString(2, cnic);

            // Execute the update query
            int rowsAffected = stmt.executeUpdate();

            // Check if the update was successful
            if (rowsAffected > 0) {
                isUpdated = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isUpdated;
    }
    public static String[] getNadra(String cnic){
        String query = "SELECT UniqueCode, IssueDate, ExpiryDate FROM NADRADBfile WHERE UniqueCode = ?";
        String[] record = null;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            // Set the parameter for the query
            stmt.setString(1, cnic);

            // Execute the query
            ResultSet rs = stmt.executeQuery();

            // If a result is found, populate the record array
            if (rs.next()) {
                record = new String[3];
                record[0] = rs.getString("UniqueCode"); // UniqueCode
                record[1] = rs.getString("IssueDate");  // IssueDate
                record[2] = rs.getString("ExpiryDate"); // ExpiryDate
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return record;
    }

    public static ArrayList<String> viewAllCnic() {
        ArrayList<String> list = new ArrayList();
        String query = "SELECT UniqueCode, IssueDate, ExpiryDate FROM NADRADBfile";

        try {
            Connection conn = DataBaseConnection.getConnection();

            try {
                Statement stmt = conn.createStatement();

                try {
                    ResultSet rs = stmt.executeQuery(query);

                    try {
                        while(rs.next()) {
                            String cnic = rs.getString("UniqueCode");
                            String name = rs.getString("IssueDate");
                            String expiryDate = rs.getString("ExpiryDate");
                            list.add(cnic + "," + name + "," + expiryDate);
                        }
                    } catch (Throwable var11) {
                        if (rs != null) {
                            try {
                                rs.close();
                            } catch (Throwable var10) {
                                var11.addSuppressed(var10);
                            }
                        }

                        throw var11;
                    }

                    if (rs != null) {
                        rs.close();
                    }
                } catch (Throwable var12) {
                    if (stmt != null) {
                        try {
                            stmt.close();
                        } catch (Throwable var9) {
                            var12.addSuppressed(var9);
                        }
                    }

                    throw var12;
                }

                if (stmt != null) {
                    stmt.close();
                }
            } catch (Throwable var13) {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (Throwable var8) {
                        var13.addSuppressed(var8);
                    }
                }

                throw var13;
            }

            if (conn != null) {
                conn.close();
            }
        } catch (SQLException var14) {
            SQLException e = var14;
            System.out.println("Error while retrieving all CNICs: " + e.getMessage());
        }

        return list;
    }

    public static ArrayList<String> viewSearchCnic(String search) {
     ArrayList<String> list=new ArrayList<>();
     String sql="Select * from NADRADBfile";

     try(Connection con=DataBaseConnection.getConnection();
     PreparedStatement pstmt=con.prepareStatement(sql)){
         ResultSet rs=pstmt.executeQuery();
         while (rs.next()){
             String []data=new String[3];
              data[0]=rs.getString("UniqueCode");
             data[1]=rs.getString("IssueDate");
             data[2]=rs.getString("ExpiryDate");

             if(search.equals("Search") || search.isEmpty()){
                 list.add(data[0] + "," + data[1] + "," + data[2]);
             }
             else if(data[0].equals(search) || data[1].equals(search) || data[2].equals(search) )
             {
                 list.add(data[0] + "," + data[1] + "," + data[2]);
             }

         }

    }
    catch(SQLException e){

        }
     return list;
    }

    public static ArrayList<String> viewAllCustomers() {
        ArrayList<String> list = new ArrayList();
        String query = "SELECT * FROM CustomerInfo";
        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                StringBuilder customer = new StringBuilder();

                customer.append(rs.getInt("CustomerID")).append(", ")
                        .append(rs.getString("CNIC")).append(", ")
                        .append(rs.getString("Name")).append(", ")
                        .append(rs.getString("Address")).append(", ")
                        .append(rs.getString("Phone")).append(", ")
                        .append(rs.getString("CustomerType")).append(", ")
                        .append(rs.getString("MeterType")).append(", ")
                        .append(rs.getString("EntryDate")).append(", ")
                        .append(rs.getString("RegularUnitsConsumed")).append(", ")
                        .append(rs.getString("PeakHourUnitsConsumed"));


                list.add(customer.toString());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        return list;
    }

    public static ArrayList<String> viewSearchCustomer(String search) {
        ArrayList<String> list = new ArrayList<>();
        if(search.trim().equalsIgnoreCase("Domestic")){
            search = "D";
        }
        else if(search.trim().equalsIgnoreCase("Commercial")){
            search = "C";
        }
        else if(search.trim().equalsIgnoreCase("1-Phase")){
            search = "S";
        }
        else if(search.trim().equalsIgnoreCase("3-Phase")){
            search = "T";
        }

        String sql = "SELECT * FROM CustomerInfo";

        try (Connection con = DataBaseConnection.getConnection();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                String[] data = new String[10];
                data[0] = rs.getString("CustomerID");
                data[1] = rs.getString("CNIC");
                data[2] = rs.getString("Name");
                data[3] = rs.getString("Address");
                data[4] = rs.getString("Phone");
                data[5] = rs.getString("CustomerType");
                data[6] = rs.getString("MeterType");
                data[7] = rs.getString("EntryDate");
                data[8] = rs.getString("RegularUnitsConsumed");
                data[9] = rs.getString("PeakHourUnitsConsumed");

                if (search.equals("Search") || search.isEmpty()) {
                    list.add(String.join(",", data));
                }

                else if (data[0].equals(search) || data[1].equals(search) || data[2].equals(search) ||
                        data[3].equals(search) || data[4].equals(search) || data[5].equals(search) ||
                        data[6].equals(search) || data[7].equals(search) || data[8].equals(search) ||
                        data[9].equals(search)) {
                    list.add(String.join(",", data));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public static void deleteCustomer(String id) {
        String deleteQuery = "DELETE FROM CustomerInfo WHERE CustomerID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(deleteQuery)) {

            stmt.setString(1, id);

            int rowsDeleted = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void editCustomer(String editedString) {
        String[] data = editedString.split(",");

        if (data.length != 10) {
            System.out.println("Error: Invalid number of fields in input string.");
            return;
        }

        if(data[5].equals("Commercial")){
            data[5]="C";
        }
        else if(data[5].equals("Domestic")){
            data[5] = "D";
        }

        if(data[6].equals("1-Phase")){
            data[6] = "S";
        }
        else if(data[6].equals("3-Phase")){
            data[6] = "T";
        }

        if (data[6].equals("T") && (data[9] == null || data[9].trim().isEmpty())) {
            data[9] = "0";
        }

        if(data[6].equals("S")){
            data[9] = "not_supported";
        }

        String customerId = data[0]; // CustomerID
        String cnic = data[1];       // CNIC
        String name = data[2];       // Name
        String address = data[3];    // Address
        String phone = data[4];      // Phone
        String customerType = data[5]; // CustomerType
        String meterType = data[6];  // MeterType
        String entryDate = data[7];  // EntryDate
        String regularUnits = data[8]; // RegularUnitsConsumed
        String peakHourUnits = data[9]; // PeakHourUnitsConsumed

        String query = "UPDATE CustomerInfo " +
                "SET CNIC = ?, Name = ?, Address = ?, Phone = ?, CustomerType = ?, MeterType = ?, EntryDate = ?, RegularUnitsConsumed = ?, PeakHourUnitsConsumed = ? " +
                "WHERE CustomerID = ?";

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, cnic);
            stmt.setString(2, name);
            stmt.setString(3, address);
            stmt.setString(4, phone);
            stmt.setString(5, customerType);
            stmt.setString(6, meterType);
            stmt.setString(7, entryDate);
            stmt.setString(8, regularUnits);
            stmt.setString(9, peakHourUnits);
            stmt.setString(10, customerId);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Customer record updated successfully.");
            } else {
                System.out.println("Error: Customer record not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public static int cnic_count(String cnic) {
        String query = "SELECT COUNT(*) FROM CustomerInfo WHERE CNIC = ?";
        int count = 0;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {


            stmt.setString(1, cnic);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    count = rs.getInt(1);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return count;
    }

    public static ArrayList<String> viewBill() {
        String customerType = "";
        if (!custInfo[5].equals("d") && !custInfo[5].equals("D")) {
            if (custInfo[5].equals("c") || custInfo[5].equals("C")) {
                customerType = "Commercial";
            }
        } else {
            customerType = "Domestic";
        }

        String meterType = "";
        if (!custInfo[6].equals("s") && !custInfo[6].equals("S")) {
            if (custInfo[6].equals("t") || custInfo[6].equals("T")) {
                meterType = "Three Phase";
            }
        } else {
            meterType = "Single Phase";
        }

        float due = 0.0F;
        if (!billInfo[10].equals("Paid") && !billInfo[10].equals("Not Found")) {
            due = Float.parseFloat(billInfo[8]);
        }

        ArrayList<String> list = new ArrayList();
        String customerId = custInfo[0];

        try {
            Connection conn = DataBaseConnection.getConnection();
            String customerQuery = "SELECT Name, Address, Phone FROM CustomerInfo WHERE CustomerID = ?";
            PreparedStatement customerStmt = conn.prepareStatement(customerQuery);
            customerStmt.setString(1, customerId);
            ResultSet customerResult = customerStmt.executeQuery();
            if (customerResult.next()) {
                list.add(customerResult.getString("Name"));
                list.add(customerResult.getString("Address"));
                list.add(customerResult.getString("Phone"));
            }

            String billQuery = "SELECT CurrentMonth, CurrentRegularUnitsConsumed, CurrentPeakHourUnitsConsumed, ElectricityCost, SalesTaxAmount, FixedCharges, DueDate, Status FROM BillingInfo WHERE CustomerID = ? ORDER BY EntryDate DESC LIMIT 1";
            PreparedStatement billStmt = conn.prepareStatement(billQuery);
            billStmt.setString(1, customerId);
            ResultSet billResult = billStmt.executeQuery();
            if (billResult.next()) {
                list.add(billResult.getString("CurrentMonth"));
                list.add(billResult.getString("CurrentRegularUnitsConsumed") + " units");
                list.add(billResult.getString("CurrentPeakHourUnitsConsumed") + " units");
                list.add(billResult.getString("ElectricityCost"));
                list.add(billResult.getString("SalesTaxAmount"));
                list.add(billResult.getString("FixedCharges"));
                list.add(billResult.getString("DueDate"));
                list.add(billResult.getString("Status"));
            }

            String tariffQuery = "SELECT RegularUnitPrice, PeakHourUnitPrice, TaxPercentage, FixedCharges FROM TariffTaxInfo WHERE MeterType = ?";
            PreparedStatement tariffStmt = conn.prepareStatement(tariffQuery);
            tariffStmt.setString(1, meterType);
            ResultSet tariffResult = tariffStmt.executeQuery();
            if (tariffResult.next()) {
                list.add(tariffResult.getString("RegularUnitPrice"));
                list.add(tariffResult.getString("PeakHourUnitPrice"));
                list.add(tariffResult.getString("TaxPercentage"));
                list.add(tariffResult.getString("FixedCharges"));
            }

            list.add(String.valueOf(due));
            list.add(billInfo[9]);
            list.add(billInfo[10]);
        } catch (SQLException var15) {
            SQLException e = var15;
            e.printStackTrace();
        }

        return list;
    }

    public static boolean isUnique(String str,int index) {
        String query = "SELECT * FROM CustomerInfo";
        index++;

        try (Connection conn = DataBaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {


            while (rs.next()) {
                String valueAtIndex = rs.getString(index);
                if (valueAtIndex != null && valueAtIndex.equals(str)) {
                    return false;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return true;

    }

}

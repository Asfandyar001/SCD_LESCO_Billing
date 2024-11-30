package Models;

import java.sql.*;
import java.util.ArrayList;

public class TaxModel {
    public static ArrayList<String> getTaxData(){
        String query = "SELECT * FROM TariffTaxInfo";
        ArrayList<String> list=new ArrayList<>();
        try (Connection connection =DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            while (resultSet.next()) {
                String[] row = new String[5];
                row[0] = resultSet.getString("MeterType");
                row[1] = resultSet.getString("RegularUnitPrice");
                row[2] = resultSet.getString("PeakHourUnitPrice");
                row[3] = resultSet.getString("TaxPercentage");
                row[4] = resultSet.getString("FixedCharges");
                list.add(row[0]+","+row[1]+","+row[2]+","+row[3]+","+row[4]);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public static ArrayList<String> getData(){
        String sql="SELECT * FROM TariffTaxInfo";
        String []record=new String[5];
        ArrayList<String> list=new ArrayList<>();
        try(Connection con=DataBaseConnection.getConnection();
            PreparedStatement pstmt=con.prepareStatement(sql)){
            ResultSet rs=pstmt.executeQuery();

            while(rs.next()){
               record[0]=(rs.getString("MeterType"));
               record[1]=(rs.getString("RegularUnitPrice"));
               record[2]=(rs.getString("PeakHourUnitPrice"));
               record[3]=(rs.getString("TaxPercentage"));
               record[4]=(rs.getString("FixedCharges"));
               if(record[2]==null)
                   record[2]="";
               list.add(record[0]+','+record[1]+','+record[2]+','+record[3]+","+record[4]);
            }
        }catch (SQLException e){

        }
        return list;
    }
    public static void performTaxChanges(int row, String value) {
        //oof took some time

        String[] values = value.split(",");

        if (values.length == 5) {
            String meterType = values[0];
            String regularUnitPrice = values[1];
            String peakHourUnitPrice = values[2];
            String taxPercentage = values[3];
            String fixedCharges = values[4];

            String sql = "WITH RowNum AS (\n" +
                    "    SELECT \n" +
                    "        MeterType, \n" +
                    "        RegularUnitPrice, \n" +
                    "        PeakHourUnitPrice, \n" +
                    "        TaxPercentage, \n" +
                    "        FixedCharges,\n" +
                    "        ROW_NUMBER() OVER (ORDER BY (SELECT NULL)) AS rn\n" +
                    "    FROM TariffTaxInfo\n" +
                    ")\n" +
                    "UPDATE TariffTaxInfo\n" +
                    "SET \n" +
                    "    RegularUnitPrice = ?,   -- New value for RegularUnitPrice\n" +
                    "    PeakHourUnitPrice = ?,  -- New value for PeakHourUnitPrice (if any)\n" +
                    "    TaxPercentage = ?,      -- New value for TaxPercentage\n" +
                    "    FixedCharges = ?       -- New value for FixedCharges\n" +
                    "FROM TariffTaxInfo T\n" +
                    "INNER JOIN RowNum R\n" +
                    "    ON T.MeterType = R.MeterType\n" +
                    "    AND T.RegularUnitPrice = R.RegularUnitPrice  -- Adding more conditions to ensure row uniqueness\n" +
                    "WHERE R.rn = ?;  -- Row number you want to update (e.g., update the 2nd row)\n";

            try (Connection con = DataBaseConnection.getConnection();
                 PreparedStatement pstmt = con.prepareStatement(sql)) {


                pstmt.setString(1, regularUnitPrice);
                pstmt.setString(2, peakHourUnitPrice.isEmpty() ? "" : peakHourUnitPrice);
                pstmt.setString(3, taxPercentage);
                pstmt.setString(4, fixedCharges);
                pstmt.setInt(5, row);


                int rowsUpdated = pstmt.executeUpdate();
                if (rowsUpdated > 0) {

                } else {

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {

        }
    }

}

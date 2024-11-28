package Models;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;

public class TaxModel {
    public static String[] getTaxData(){
        String query = "SELECT * FROM TariffTaxInfo";
        String []record=new String[4];
        try (Connection connection =DataBaseConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            int i=0;
            while (resultSet.next()) {
                String[] row = new String[5];
                row[0] = resultSet.getString("MeterType");
                row[1] = resultSet.getString("RegularUnitPrice");
                row[2] = resultSet.getString("PeakHourUnitPrice");
                row[3] = resultSet.getString("TaxPercentage");
                row[4] = resultSet.getString("FixedCharges");
                  record[i]=row[0]+","+row[1]+","+row[2]+","+row[3]+","+row[4];
                  i++;


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return record;
    }
}

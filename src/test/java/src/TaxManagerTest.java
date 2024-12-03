package src;
import GUI.Emp_TaxesInfo;
import org.junit.Test;

import java.sql.SQLException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class TaxManagerTest {

    @Test
    public void testGetData() throws SQLException {
        TaxManager manager = new TaxManager();
        ArrayList<String> result = manager.getData();

        ArrayList<String> expected = new ArrayList<>();
        expected.add("1Phase,5,,17,150");
        expected.add("1Phase,15,,20,250");
        expected.add("3Phase,8,12,17,150");
        expected.add("3Phase,18,25,20,250");

        for(int i=0; i< expected.size(); i++){
            assertEquals(expected.get(i),result.get(i));
        }
    }

    @Test
    public void testUpdateTaxMenu() {
        Emp_TaxesInfo taxesInfo = new Emp_TaxesInfo("Name");
        TaxManager manager = new TaxManager();
        boolean result = manager.updateTaxMenu(3,taxesInfo);
        assertEquals(false,result);
    }

    @Test
    public void testIsDigits() {
        TaxManager manager = new TaxManager();
        boolean result = manager.isDigits("hello");
        assertEquals(false,result);
    }
}
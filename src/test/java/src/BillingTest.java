package src;
import GUI.Emp_Change_Bill_Status;
import GUI.Emp_ViewBill_NoBill;
import GUI.frame;
import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class BillingTest {

    @Test
    public void testAddNewBill() throws IOException {
        Billing bill = new Billing();
        boolean result = bill.addNewBill("111","-110","100","Feb");
        assertEquals(false,result);
    }

    @Test
    public void testViewAllBills() throws IOException {
        Billing b = new Billing();
        ArrayList<String> list = b.viewAllBills();
        assertEquals(4,list.size());
    }

    @Test
    public void testViewSearchedBills() throws IOException, InterruptedException {
        Billing b = new Billing();
        ArrayList<String> list = b.viewSearchedBills("hhhh");
        assertEquals(0,list.size());
    }

    @Test
    public void testDeleteBill() throws IOException, InterruptedException {
        Billing b = new Billing();
        b.deleteBill("111","Jan","11/11/2020");
        ArrayList<String> list = b.viewSearchedBills("111");
        assertEquals(0,list.size());
    }

    @Test
    public void testIsAccessAble() throws IOException {
        Billing b = new Billing();
        boolean result = b.isAccessAble("111","Jan","11/11/2020");
        assertEquals(true,result);
    }

    @Test
    public void testIsValidEdit() throws IOException {
        Billing b = new Billing();
        boolean result = b.isValidEdit("hhhh");
        assertEquals(false,result);
    }

    @Test
    public void testChangePaidStatus() throws IOException {
        Billing b = new Billing();
        boolean result = b.changePaidStatus(new Emp_Change_Bill_Status("Ahmed"));
        assertEquals(false,result);
    }

    @Test
    public void testGetTaxData() throws IOException {
        Billing b = new Billing();
        String[] result = b.getTaxData("111","T");
        assertEquals(1,result.length);
    }

    @Test
    public void testValidateCustomerID() throws IOException {
        Billing b = new Billing();
        boolean result = b.validateCustomerID("11110000");
        assertEquals(false,result);
    }

    @Test
    public void testValidateCustomerIDfromBillFile() throws IOException {
        Billing b = new Billing();
        boolean result = b.validateCustomerIDfromBillFile("11110000","Jan","11/11/2020");
        assertEquals(false,result);
    }

    @Test
    public void testViewBill() throws IOException {
        Billing b = new Billing();
        boolean result = b.viewBill(new frame(),new Emp_ViewBill_NoBill("Asfandyar"));
        assertEquals(false,result);
    }

    @Test
    public void testIsDigits() {
        Billing b = new Billing();
        boolean result = b.isDigits("1111");
        assertEquals(true,result);
    }
}
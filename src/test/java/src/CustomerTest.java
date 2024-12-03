package src;

import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test
    public void testIsCustomerValid() throws IOException {
        Customer c = new Customer();
        boolean result = c.isCustomerValid("222","222");
        assertEquals(false,result);
    }

    @Test
    public void testGetUserName() throws IOException {
        Customer c = new Customer();
        c.isCustomerValid("222","222"); //------------------------------------ make it work

        String result = c.getUserName();
        assertEquals(null,result);
    }

    @Test
    public void testAddCustomer() throws IOException { //---------------- make it work
        Customer c = new Customer();
        boolean result = c.addCustomer("100","Asfandyar","170-D Rehman Pura","12345678911","Commercial","1-Phase");
        assertEquals(false,result);
    }

    @Test
    public void testValidateCustomer() throws IOException { // ------------ make it work
        Customer c = new Customer();
        boolean result = c.validateCustomer("100","1234567890123","Jan",2024);
        assertEquals(false,result);
    }

    @Test
    public void testUpdateCNIC() throws IOException { //-------------- make it work
        Customer c = new Customer();
        boolean result = c.updateCNIC("100","1234455","12/10/2024");
        assertEquals(false,result);
    }

    @Test
    public void testViewExpireCnic() throws IOException { //------------- make it work
        Customer c = new Customer();
        ArrayList<String> list = c.viewExpireCnic();
        assertEquals(7,list.size());
    }

    @Test
    public void testViewSearchCNIC() throws IOException { //-------------make it work
        Customer c = new Customer();
        ArrayList<String> list = c.viewSearchCNIC("hehehehehe");
        assertEquals(0,list.size());
    }

    @Test
    public void testViewAllCnic() throws IOException { //--------------- make it work
        Customer c = new Customer();
        ArrayList<String> list = c.viewAllCnic();
        assertEquals(27,list.size());
    }

    @Test
    public void testViewAllCustomers() throws IOException {//-------------make it work
        Customer c = new Customer();
        ArrayList<String> list = c.viewAllCustomers();
        assertEquals(5,list.size());
    }

    @Test
    public void testViewSearchCustomer() throws IOException {//--------------make it work
        Customer c = new Customer();
        ArrayList<String> list = c.viewSearchCustomer("hehehe");
        assertEquals(0,list.size());
    }

    @Test
    public void testDeleteCustomer() throws IOException { //-------------make it work
        Customer c = new Customer();
        c.deleteCustomer("111");
        ArrayList<String> list = c.viewSearchCustomer("111");
        assertEquals(0,list.size());
    }

    @Test
    public void testIsVlaidEdit() {
        Customer c = new Customer();
        boolean result = c.isVlaidEdit("6385,1122334455662,Ahmed,190-C Muslim,11223344556,Commercial,3-Phase,19/10/2024,100,100");
        assertEquals(true,result);
    }

    @Test
    public void testCnic_count() throws IOException {//------------------make it work
        Customer c = new Customer();
        int result = c.cnic_count("123456");
        assertEquals(0,result);
    }

    @Test
    public void testSearchNadraFile() throws IOException {//--------------make it work
        Customer c = new Customer();
        boolean result = c.searchNadraFile("hhhh");
        assertEquals(false,result);
    }

    @Test
    public void testViewBill() throws IOException {//------------------make it work
        Customer c = new Customer();
        c.validateCustomer("6065","1234567890123","Aug",2024);
        ArrayList<String> list = c.viewBill();
        assertEquals(19,list.size());
    }

    @Test
    public void testIsAlphabets() {
        Customer c = new Customer();
        boolean result = c.isAlphabets("11AB@");
        assertEquals(false,result);
    }

    @Test
    public void testIsDigits() {
        Customer c = new Customer();
        boolean result = c.isDigits("1111");
        assertEquals(true,result);
    }

    @Test
    public void testIsUnique() throws IOException {//----------make it work
        Customer c = new Customer();
        boolean result = c.isUnique("111100000",0);
        assertEquals(true,result);
    }
}
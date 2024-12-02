package src;
import GUI.Emp_Update_Password;
import org.junit.Test;
import java.io.IOException;
import static org.junit.Assert.assertEquals;

public class EmployeesTest {

    @Test
    public void testValidateEmployee() throws IOException {//-----------make it work
        Employees e = new Employees();
        boolean result = e.validateEmployee("Hello","hello");
        assertEquals(false,result);
    }

    @Test
    public void testUpdateMenu() throws IOException {
        Emp_Update_Password updatePanel = new Emp_Update_Password("Hello");
        Employees e = new Employees();
        String result = e.updateMenu("Asfandyar","Hello",updatePanel);
        assertEquals("no change",result);
    }
}
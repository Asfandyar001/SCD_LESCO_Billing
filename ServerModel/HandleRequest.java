package ServerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class HandleRequest extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;

    public HandleRequest(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public void run() {
        try {
            while (true) {
                String request = input.readLine();
                if (request == null) break;
                entertainRequest(request);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void entertainRequest(String request) {
        String[] file = request.split(" ");
        if (file[0].equalsIgnoreCase("addNewBill") || file[0].equalsIgnoreCase("viewAllBills") ||
                file[0].equalsIgnoreCase("viewSearchedBills") || file[0].equalsIgnoreCase("deleteBill") ||
                file[0].equalsIgnoreCase("isAccessAble") || file[0].equalsIgnoreCase("isValidEdit") ||
                file[0].equalsIgnoreCase("editBill") || file[0].equalsIgnoreCase("changePaidStatus") ||
                file[0].equalsIgnoreCase("updateCustomerFile") || file[0].equalsIgnoreCase("writeFile") ||
                file[0].equalsIgnoreCase("appendFile") || file[0].equalsIgnoreCase("getTaxData")) {
            synchronized (Server.billingInfo) {
                // Add your logic here
            }
        } else if (file[0].equalsIgnoreCase("viewCustomerDetails") || file[0].equalsIgnoreCase("updateCustomerInfo") ||
                file[0].equalsIgnoreCase("deleteCustomer") || file[0].equalsIgnoreCase("addCustomer") ||
                file[0].equalsIgnoreCase("getCustomerHistory") || file[0].equalsIgnoreCase("searchCustomer") ||
                file[0].equalsIgnoreCase("isValidCustomer") || file[0].equalsIgnoreCase("getCustomerBalance") ||
                file[0].equalsIgnoreCase("viewCustomerTransactions") || file[0].equalsIgnoreCase("generateCustomerReport") ||
                file[0].equalsIgnoreCase("viewSearchCustomer") || file[0].equalsIgnoreCase("deleteCustomer") ||
                file[0].equalsIgnoreCase("isValidEdit") || file[0].equalsIgnoreCase("editCustomer") ||
                file[0].equalsIgnoreCase("cnic_count") || file[0].equalsIgnoreCase("searchNadraFile") ||
                file[0].equalsIgnoreCase("viewBill") || file[0].equalsIgnoreCase("getTaxData")) {
            synchronized (Server.customerInfo) {
                // Add your logic here
            }
        } else if (file[0].equalsIgnoreCase("validateEmployee") || file[0].equalsIgnoreCase("updateMenu") ||
                file[0].equalsIgnoreCase("updatePass")) {
            synchronized (Server.employeesInfo) {
                // Add your logic here
            }
        } else if (file[0].equalsIgnoreCase("getData") || file[0].equalsIgnoreCase("updateTaxMenu") ||
                file[0].equalsIgnoreCase("performTaxChanges")) {
            synchronized (Server.TariffTaxInfo) {
                // Add your logic here
            }
        }
    }
}
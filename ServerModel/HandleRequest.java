package ServerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import Models.DataBaseHandler;
import org.json.JSONObject;



public class HandleRequest extends Thread {
    private Socket socket;
    private BufferedReader input;
    private PrintWriter output;
    private Object response;

    public HandleRequest(Socket socket) throws IOException {
        this.socket = socket;
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }


    public void run() {
        try {
            String requestLine;
            while ((requestLine = input.readLine()) != null) {
                JSONObject request = new JSONObject(requestLine); // Parse requestLine as JSON
                entertainRequest(request); // Pass JSONObject directly
            }
        } catch (IOException e) {
            throw new RuntimeException("Error reading input", e);
        } finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }




    public void entertainRequest(JSONObject jsonRequest) {
        try {
            String function = jsonRequest.getString("function");

            if (function.equalsIgnoreCase("addNewBill") || function.equalsIgnoreCase("viewAllBills") ||
                    function.equalsIgnoreCase("viewSearchedBills") || function.equalsIgnoreCase("deleteBill") ||
                    function.equalsIgnoreCase("isAccessAble") || function.equalsIgnoreCase("isValidEdit") ||
                    function.equalsIgnoreCase("editBill") || function.equalsIgnoreCase("changePaidStatus") ||
                    function.equalsIgnoreCase("updateCustomerFile") || function.equalsIgnoreCase("writeFile") ||
                    function.equalsIgnoreCase("appendFile") || function.equalsIgnoreCase("getTaxData")) {

                synchronized (Server.billingInfo) {

                    // Add your logic here
                }
            } else if (function.equalsIgnoreCase("viewCustomerDetails") || function.equalsIgnoreCase("updateCustomerInfo") ||
                    function.equalsIgnoreCase("deleteCustomer") || function.equalsIgnoreCase("addCustomer") ||
                    function.equalsIgnoreCase("getCustomerHistory") || function.equalsIgnoreCase("searchCustomer") ||
                    function.equalsIgnoreCase("isValidCustomer") || function.equalsIgnoreCase("getCustomerBalance") ||
                    function.equalsIgnoreCase("viewCustomerTransactions") || function.equalsIgnoreCase("generateCustomerReport") ||
                    function.equalsIgnoreCase("viewSearchCustomer") || function.equalsIgnoreCase("deleteCustomer") ||
                    function.equalsIgnoreCase("isValidEdit") || function.equalsIgnoreCase("editCustomer") ||
                    function.equalsIgnoreCase("cnic_count") || function.equalsIgnoreCase("searchNadraFile") ||
                    function.equalsIgnoreCase("viewBill") || function.equalsIgnoreCase("getTaxData")) {

                synchronized (Server.customerInfo) {
                    // Add your logic here
                }
            } else if (function.equalsIgnoreCase("validateEmployee") || function.equalsIgnoreCase("updateMenu") ||
                    function.equalsIgnoreCase("updatePass")) {

                synchronized (Server.employeesInfo) {

                    if(function.equalsIgnoreCase("validateEmployee")){
                      response=  DataBaseHandler.validateEmployee(jsonRequest.getString("username"),jsonRequest.getString("pass"));

                    }
                }
            } else if (function.equalsIgnoreCase("getData") || function.equalsIgnoreCase("updateTaxMenu") ||
                    function.equalsIgnoreCase("performTaxChanges")) {

                synchronized (Server.TariffTaxInfo) {
                    // Add your logic here
                }
            }
            output.println(response.toString());
        } catch (Exception e) {
            e.printStackTrace();  // Handle parsing errors or unknown functions
        }
    }

    public Object getResponse() {
        return response;
    }

    public void setResponse(Object response) {
        this.response = response;
    }
}

package ServerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import Models.DataBaseHandler;
import org.json.JSONArray;
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
                    function.equalsIgnoreCase("isCustomerValid") || function.equalsIgnoreCase("getCustomerBalance") ||
                    function.equalsIgnoreCase("viewCustomerTransactions") || function.equalsIgnoreCase("generateCustomerReport") ||
                    function.equalsIgnoreCase("viewSearchCustomer") || function.equalsIgnoreCase("deleteCustomer") ||
                    function.equalsIgnoreCase("isValidEdit") || function.equalsIgnoreCase("editCustomer") ||
                    function.equalsIgnoreCase("cnic_count") || function.equalsIgnoreCase("searchNadraFile") ||
                    function.equalsIgnoreCase("viewBill") || function.equalsIgnoreCase("getTaxData") ||
                    function.equalsIgnoreCase("getCustomer") ||  function.equalsIgnoreCase("validateCustomer")
                    || function.equalsIgnoreCase("getBill") || function.equalsIgnoreCase("updateExpiryDate")
            ||function.equalsIgnoreCase("viewExpireCnic") || function.equalsIgnoreCase("viewAllCnic")
            ||function.equalsIgnoreCase("viewSearchCnic") || function.equalsIgnoreCase("viewAllCustomers")
            ||function.equalsIgnoreCase("getNadra") ||function.equalsIgnoreCase("getTax")
            ||function.equalsIgnoreCase("isUnique")){

               synchronized (Server.customerInfo) {
                    if(function.equalsIgnoreCase("isCustomerValid")){

                       response= DataBaseHandler.isCustomerValid(jsonRequest.getString("id"),jsonRequest.getString("cnic"));
                    } if (function.equalsIgnoreCase("getCustomer")) {
                       response= DataBaseHandler.getCustomer(jsonRequest.getString("id"));
                    }
                     if (function.equalsIgnoreCase("addCustomer")) {
                        response=DataBaseHandler.addCustomer(jsonRequest.getString("id"),jsonRequest.getString("cnic"),jsonRequest.getString("name"),jsonRequest.getString("address"),
                                jsonRequest.getString("phone"),jsonRequest.getString("custType"),jsonRequest.getString("meterType"),jsonRequest.getString("date"),jsonRequest.getString("RUC")
                        ,jsonRequest.getString("PHUC"));
                    }
                     if(function.equalsIgnoreCase("validateCustomer")){
                        response=DataBaseHandler.validateCustomer(jsonRequest.getString("id"),jsonRequest.getString("cnic"),jsonRequest.getString("month"), Integer.parseInt(jsonRequest.getString("year")));

                    }  if (function.equalsIgnoreCase("getBill")) {
                    response=DataBaseHandler.getBill(jsonRequest.getString("id"),jsonRequest.getString("year"));
                    }
                     if(function.equalsIgnoreCase("updateExpiryDate")){
                        response=DataBaseHandler.updateExpiryDate(jsonRequest.getString("cnic"),jsonRequest.getString("newdate"));
                    }
                     if(function.equalsIgnoreCase("viewExpireCnic")){
                        ArrayList<String> expiredCnicList = DataBaseHandler.viewExpireCnic();
                        JSONArray jsonArray = new JSONArray(expiredCnicList);
                        response = jsonArray;
                    }
                     if(function.equalsIgnoreCase("viewAllCnic")){
                        ArrayList<String> viewAllCnic = DataBaseHandler.viewAllCnic();
                        JSONArray jsonArray = new JSONArray(viewAllCnic);
                        response = jsonArray;
                    }  if (function.equalsIgnoreCase("viewSearchCnic")) {
                        ArrayList<String> viewSearchCnic = DataBaseHandler.viewSearchCnic(jsonRequest.getString("search"));
                        JSONArray jsonArray = new JSONArray(viewSearchCnic);
                        response = jsonArray;
                    }  if (function.equalsIgnoreCase("viewSearchCustomer")) {
                        ArrayList<String> viewSearchCustomer = DataBaseHandler.viewSearchCustomer(jsonRequest.getString("search"));
                        JSONArray jsonArray = new JSONArray(viewSearchCustomer);
                        response = jsonArray;
                    }  if (function.equalsIgnoreCase("deleteCustomer")) {
                        DataBaseHandler.deleteCustomer(jsonRequest.getString("id"));
                    }
                     if(function.equalsIgnoreCase("editCustomer")){
                        DataBaseHandler.editCustomer(jsonRequest.getString("editedString"));
                    }  if (function.equalsIgnoreCase("cnic_count")) {
                        response=DataBaseHandler.cnic_count(jsonRequest.getString("cnic"));
                    }  if (function.equalsIgnoreCase("searchNadraFile")) {
                        response=DataBaseHandler.searchNadraFile(jsonRequest.getString("cnic"));

                    }   if(function.equalsIgnoreCase("viewAllCustomers")){
                        ArrayList<String> viewAllCustomers = DataBaseHandler.viewAllCustomers();
                        JSONArray jsonArray = new JSONArray(viewAllCustomers);
                        response = jsonArray;
                    }  if (function.equalsIgnoreCase("getNadra")) {
                        response=DataBaseHandler.getNadra(jsonRequest.getString("cnic"));
                    }  if (function.equalsIgnoreCase("getTax")) {
                        response=DataBaseHandler.getTax();
                    }  if (function.equalsIgnoreCase("isUnique")) {
                        response=DataBaseHandler.isUnique(jsonRequest.getString("str"), Integer.parseInt(jsonRequest.getString("index")));
                    }

                    // Add your logic here
                }
            } else if (function.equalsIgnoreCase("validateEmployee") || function.equalsIgnoreCase("updateMenu") ||
                    function.equalsIgnoreCase("updatePass")) {

                synchronized (Server.employeesInfo) {

                    if(function.equalsIgnoreCase("validateEmployee")){
                      response=  DataBaseHandler.validateEmployee(jsonRequest.getString("username"),jsonRequest.getString("pass"));

                    }
                    else if(function.equalsIgnoreCase("updatePass")){
                       DataBaseHandler.updateEmployeePassword(jsonRequest.getString("username"),jsonRequest.getString("newPass"));
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

package ServerModel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

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
response=null;
            if (function.equalsIgnoreCase("addBill") || function.equalsIgnoreCase("viewAllBills") ||
                    function.equalsIgnoreCase("viewSearchedBills") || function.equalsIgnoreCase("deleteBill") ||
                    function.equalsIgnoreCase("isAccessAble") || function.equalsIgnoreCase("isValidEdit") ||
                    function.equalsIgnoreCase("editBill") || function.equalsIgnoreCase("changePaidStatus") ||
                    function.equalsIgnoreCase("updateCustomerFile") || function.equalsIgnoreCase("writeFile") ||
                    function.equalsIgnoreCase("appendFile") || function.equalsIgnoreCase("getTaxData")
                    || function.equalsIgnoreCase("getBillforAdd") || function.equalsIgnoreCase("deleteBill")
        ||function.equalsIgnoreCase("changePaidstatus")){

                synchronized (Server.billingInfo) {
                    if(function.equalsIgnoreCase("changePaidstatus")){
                        DataBaseHandler.changePaidstatus(jsonRequest.getString("paymentDate"),jsonRequest.getString("custId"),jsonRequest.getString("billingmonth"),jsonRequest.getString("eDate"));
                    }
                    if (function.equalsIgnoreCase("getBillforAdd")) {
                        ArrayList<String> bills = DataBaseHandler.getBillforAdd();
                        // Convert ArrayList to JSON array
                        JSONArray jsonArray = new JSONArray(bills);
                        output.println(jsonArray.toString()); // Send JSON array as response
                    }


                    if( function.equalsIgnoreCase("deleteBill")){
                        DataBaseHandler.deleteBill(jsonRequest.getString("id"),jsonRequest.getString("month"),jsonRequest.getString("eDate"));
                    }
                    if (function.equalsIgnoreCase("addBill")) {
                        DataBaseHandler.addBill(
                                jsonRequest.getInt("customerID"),
                                jsonRequest.getString("currentMonth"),
                                jsonRequest.getString("regularUnits"),
                                jsonRequest.getString("peakUnits"),
                                jsonRequest.getString("entryDate"),
                                jsonRequest.getDouble("electricityCost"),
                                jsonRequest.getDouble("salesTax"),
                                jsonRequest.getDouble("fixedCharges"),
                                jsonRequest.getString("dueDate"),
                                jsonRequest.getString("status"),
                                jsonRequest.getString("paymentDate")
                        );
                    }
                    else if (function.equalsIgnoreCase("deleteBill")) {
                        DataBaseHandler.deleteBill(
                                jsonRequest.getString("id"),
                                jsonRequest.getString("month"),
                                jsonRequest.getString("eDate")
                        );
                    }
                    else if (function.equalsIgnoreCase("editBill")) {
                        DataBaseHandler.editBill(jsonRequest.getString("str"));
                    }
                    else if (function.equalsIgnoreCase("isAccessAble")) {
                        boolean result = DataBaseHandler.isAccessAble(
                                jsonRequest.getString("id"),
                                jsonRequest.getString("month"),
                                jsonRequest.getString("eDate")
                        );
                        response = result;
                    }
                    else if (function.equalsIgnoreCase("viewSearchedBills")) {
                        // Get bills from the database handler
                        ArrayList<String> bills = DataBaseHandler.viewSearchedBills(jsonRequest.getString("search"));
                        JSONArray jsonArray = new JSONArray(bills);
                        response = jsonArray; // Send as a stringified JSON array
                    }

                    else if (function.equalsIgnoreCase("changePaidstatus")) {
                        DataBaseHandler.changePaidstatus(
                                jsonRequest.getString("paymentDate"),
                                jsonRequest.getString("custId"),
                                jsonRequest.getString("billingmonth"),
                                jsonRequest.getString("eDate")
                        );
                    }
                    else if (function.equalsIgnoreCase("updateCustomerFile")) {
                        DataBaseHandler.updateCustomerFile(
                                jsonRequest.getString("custId"),
                                jsonRequest.getString("RUC"),
                                jsonRequest.getString("PHUC")
                        );
                    }
                    else if (function.equalsIgnoreCase("getBill1")) {
                        String[] billData = DataBaseHandler.getBill1(
                                jsonRequest.getString("id"),
                                jsonRequest.getString("month"),
                                jsonRequest.getString("entryDate")
                        );

                        response = String.join(",", billData);
                    }
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

                   if (function.equalsIgnoreCase("isCustomerValid")) {

                       response = DataBaseHandler.isCustomerValid(jsonRequest.getString("id"), jsonRequest.getString("cnic"));
                   }
                   if (function.equalsIgnoreCase("getCustomer")) {

                       String[] customerData = DataBaseHandler.getCustomer(jsonRequest.getString("id"));
                       response = String.join(",", customerData);
                   }

                   if (function.equalsIgnoreCase("addCustomer")) {
                       response = DataBaseHandler.addCustomer(jsonRequest.getString("id"), jsonRequest.getString("cnic"), jsonRequest.getString("name"), jsonRequest.getString("address"),
                               jsonRequest.getString("phone"), jsonRequest.getString("custType"), jsonRequest.getString("meterType"), jsonRequest.getString("date"), jsonRequest.getString("RUC")
                               , jsonRequest.getString("PHUC"));
                   }
                   if (function.equalsIgnoreCase("validateCustomer")) {
                       response = DataBaseHandler.validateCustomer(jsonRequest.getString("id"), jsonRequest.getString("cnic"), jsonRequest.getString("month"), jsonRequest.getString("year"));

                   }
                   if (function.equalsIgnoreCase("getBill")) {
                       String []Billdata= DataBaseHandler.getBill(jsonRequest.getString("id"), jsonRequest.getString("year"));
                       response = String.join(",", Billdata);
                   }
                   if (function.equalsIgnoreCase("updateExpiryDate")) {
                       response = DataBaseHandler.updateExpiryDate(jsonRequest.getString("cnic"), jsonRequest.getString("newdate"));
                   }
                   if (function.equalsIgnoreCase("viewExpireCnic")) {
                       ArrayList<String> expiredCnicList = DataBaseHandler.viewExpireCnic();
                       JSONArray jsonArray = new JSONArray(expiredCnicList);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("viewAllCnic")) {
                       ArrayList<String> viewAllCnic = DataBaseHandler.viewAllCnic();
                       JSONArray jsonArray = new JSONArray(viewAllCnic);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("viewSearchCnic")) {
                       ArrayList<String> viewSearchCnic = DataBaseHandler.viewSearchCnic(jsonRequest.getString("search"));
                       JSONArray jsonArray = new JSONArray(viewSearchCnic);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("viewSearchCustomer")) {
                       ArrayList<String> viewSearchCustomer = DataBaseHandler.viewSearchCustomer(jsonRequest.getString("search"));
                       JSONArray jsonArray = new JSONArray(viewSearchCustomer);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("deleteCustomer")) {
                       DataBaseHandler.deleteCustomer(jsonRequest.getString("id"));
                   }
                   if (function.equalsIgnoreCase("editCustomer")) {
                       DataBaseHandler.editCustomer(jsonRequest.getString("editedString"));
                   }
                   if (function.equalsIgnoreCase("cnic_count")) {
                       response = DataBaseHandler.cnic_count(jsonRequest.getString("cnic"));
                   }
                   if (function.equalsIgnoreCase("searchNadraFile")) {
                       response = DataBaseHandler.searchNadraFile(jsonRequest.getString("cnic"));
                   }
                   if (function.equalsIgnoreCase("viewAllCustomers")) {
                       ArrayList<String> viewAllCustomers = DataBaseHandler.viewAllCustomers();
                       JSONArray jsonArray = new JSONArray(viewAllCustomers);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("getNadra")) {
                       String[]Nadra = DataBaseHandler.getNadra(jsonRequest.getString("cnic"));
                       response=String.join(",", Nadra);
                   }
                   if (function.equalsIgnoreCase("getTax")) {
                       ArrayList<String> getTax = DataBaseHandler.getTax();
                       JSONArray jsonArray = new JSONArray(getTax);
                       response = jsonArray;
                   }
                   if (function.equalsIgnoreCase("getCustomer")) {

                       String[] customerData = DataBaseHandler.getCustomer(jsonRequest.getString("id"));
                       response = String.join(",", customerData);
                   }


                   if (function.equalsIgnoreCase("isUnique")) {
                        response=DataBaseHandler.isUnique(jsonRequest.getString("str"), jsonRequest.getString("index"));
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
if(response!=null){
            output.println(response.toString());
        }} catch (Exception e) {
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

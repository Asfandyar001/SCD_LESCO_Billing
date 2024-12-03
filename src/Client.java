package src;

import GUI.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.simple.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Client {
    private static Client client;
    private Socket socket;
    private static BufferedReader input;
    private PrintWriter output;
    final private String IPAddress = "localhost";
    final private int port = 1234;

    private Client() throws IOException {
        socket = new Socket(IPAddress, port);
        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        output = new PrintWriter(socket.getOutputStream(), true);
    }

    public static Client getInstance() throws IOException {
        if (client == null) {
            client = new Client();
        }
        return client;
    }

    private String getResponse() throws IOException {
        return input.readLine();
    }

    public boolean addNewBill(String custID, String currentMeterReading, String currentMeterReadingPeak, String billingMonth) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "addNewBill");
        object.put("custID", custID);
        object.put("currentMeterReading", currentMeterReading);
        object.put("currentMeterReadingPeak", currentMeterReadingPeak);
        object.put("billingMonth", billingMonth);
        output.println(object.toString());

        String response = getResponse();
        return Boolean.parseBoolean(response);
    }

    public boolean validateEmployee(String employeeId, String password) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "validateEmployee");
        object.put("username", employeeId);
        object.put("pass", password);
        output.println(object.toString());

        String response = getResponse();
        return Boolean.parseBoolean(response);
    }


    public ArrayList<String> viewAllBills() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewAllBills");
        output.println(object.toString());

        String response = getResponse();
        return new ArrayList<>(Arrays.asList(response.split(","))); // Example: Parse CSV response
    }
    public boolean isValidEdit(String row) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isValidEdit");
        object.put("row", row);
        output.println(object.toString());

        return Boolean.parseBoolean(input.readLine());
    }
    public boolean changePaidStatus(Emp_Change_Bill_Status changeStatus) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "changePaidStatus");
        object.put("changeStatus", changeStatus.toString());
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public void writeFile(ArrayList<String> array, String filename) {
        JSONObject object = new JSONObject();
        object.put("function", "writeFile");
        object.put("filename", filename);
        object.put("data", array);
        output.println(object.toString());
    }

    public void appendFile(String filename, String data) {
        JSONObject object = new JSONObject();
        object.put("function", "appendFile");
        object.put("filename", filename);
        object.put("data", data);
        output.println(object.toString());
    }

        public String[] getTaxData(String custType, String phase) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getTaxData");
        object.put("custType", custType);
        object.put("phase", phase);
        output.println(object.toString());
        String response = input.readLine();
        return response.split(",");


    }

    public boolean validateCustomerID(String id) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "validateCustomerID");
        object.put("id", id);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }
    public boolean validateCustomer(String id, String cnic, String month, String year) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "validateCustomer");
        object.put("id", id);
        object.put("cnic", cnic);
        object.put("month", month);
        object.put("year", year);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

        public boolean validateCustomerIDfromBillFile(String id, String month, String date) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "validateCustomerIDfromBillFile");
        object.put("id", id);
        object.put("month", month);
        object.put("date", date);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public boolean viewBill(frame f, Emp_ViewBill_NoBill noBill) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewBill");
        object.put("frame", f.toString());
        object.put("noBill", noBill.toString());
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public String[] getBillList() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getBillList");
        object.put("action", "getBillList");
        output.println(object.toString());
        String response = input.readLine();
        return response.split(",");
    }
    public String[] getBill(String id,String year) throws IOException {
        System.out.println("Started");
        JSONObject object = new JSONObject();
        object.put("function", "getBill");
        object.put("id", id);
        object.put("year", year);
        output.println(object.toString());

        String response = input.readLine();
        System.out.println("ended");
        String[] responseArray = response.split(",");


        return responseArray;
    }
    public  boolean updateExpiryDate(String cnic,String newdate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "updateExpiryDate");
        object.put("cnic", cnic);
        object.put("newdate", newdate);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

        public int[] viewReport() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewReport");
        output.println(object.toString());
        String response = input.readLine();
        String[] responseArray = response.split(",");
        int[] reportData = new int[responseArray.length];
        for (int i = 0; i < responseArray.length; i++) {
            reportData[i] = Integer.parseInt(responseArray[i].trim());
        }
        return reportData;
    }

    public boolean isDigits(String str) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isDigits");
        object.put("str", str);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public boolean isCustomerValid(String id, String cnic) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isCustomerValid");
        object.put("id", id);
        object.put("cnic", cnic);
        output.println(object);

        String response = getResponse();

        return Boolean.parseBoolean(response);
    }
    public String getUserName() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getUserName");
        object.put("action", "getUserName");
        output.println(object.toString());
        return input.readLine();
    }

    public void CustInMenu(frame f, String name, Cust_Bill_Not_Found noBill) {
        JSONObject object = new JSONObject();
        object.put("function", "CustInMenu");
        object.put("frame", f.toString());
        object.put("name", name);
        object.put("noBill", noBill.toString());
        output.println(object.toString());
    }
    /*
    public boolean addCustomer(String cnic, String name, String address, String phone, String custType, String meterType) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "addCustomer");
        object.put("cnic", cnic);
        object.put("name", name);
        object.put("address", address);
        object.put("phone", phone);
        object.put("custType", custType);
        object.put("meterType", meterType);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

     */
    public boolean addCustomer(String id, String cnic, String name, String address, String phone, String custType, String meterType, String date, String RUC, String PHUC) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function","addCustomer");
        object.put("id", id);
        object.put("cnic", cnic);
        object.put("name", name);
        object.put("address", address);
        object.put("phone", phone);
        object.put("custType", custType);
        object.put("meterType", meterType);
        object.put("date", date);
        object.put("RUC", RUC);
        object.put("PHUC", PHUC);
        output.println(object);
        return Boolean.parseBoolean(input.readLine());
    }

    public boolean updateCNIC(String id, String cnic, String newDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "updateCNIC");
        object.put("id", id);
        object.put("cnic", cnic);
        object.put("newDate", newDate);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public ArrayList<String> viewExpireCnic() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewExpireCnic");

        // Send the request to the server
        output.println(object.toString());
        output.flush(); // Ensure the output is flushed and sent immediately

        // Receive response
        String response = getResponse(); // Use getResponse to get the data

        ArrayList<String> expiredCnicList = new ArrayList<>();

        if (response != null && !response.isEmpty()) {
            try {
                // Parse the JSON response into a JSONArray
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    expiredCnicList.add(jsonArray.getString(i));
                }
            } catch (Exception e) {
                System.err.println("Error parsing JSON response: " + e.getMessage());
                e.printStackTrace(); // Handle parsing issues
            }
        } else {
            // Handle empty or invalid response
            System.err.println("Received empty or invalid response: " + response);
        }

        return expiredCnicList;
    }

    public  ArrayList<String> viewAllCnic() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewAllCnic");
        output.println(object); // Send the request to the server

        // Receive response
        String response = getResponse(); // Response is a stringified JSON
        ArrayList<String> viewAllCnic = new ArrayList<>();

        try {
            // Parse the JSON response into a JSONArray
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                viewAllCnic.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing issues
        }

        return viewAllCnic;
    }
    public ArrayList<String> viewSearchCnic(String search) throws IOException {

        JSONObject object = new JSONObject();
        object.put("function", "viewSearchCnic");
        object.put("search",search);
        output.println(object.toString()); // Send the request to the server

        // Receive response
        String response = getResponse(); // Response is a stringified JSON
        ArrayList<String> viewSearchCnic = new ArrayList<>();

        try {
            // Parse the JSON response into a JSONArray
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                viewSearchCnic.add(jsonArray.getString(i));
            }
        } catch (Exception e) {
            e.printStackTrace(); // Handle parsing issues
        }

        return viewSearchCnic;
    }
    public ArrayList<String> viewSearchCustomer(String search) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewSearchCustomer");
        object.put("search",search);
        output.println(object.toString());

        // Read the response from the server
        String response = getResponse();

        ArrayList<String> viewSearchCustomer = new ArrayList<>();

        try {
            // Parse the response as a JSON array
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                viewSearchCustomer.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {

        }

        return viewSearchCustomer;
    }

    public ArrayList<String> viewAllCustomers() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewAllCustomers");
        output.println(object);

        // Read the response from the server
        String response = getResponse();

        ArrayList<String> viewAllCustomers = new ArrayList<>();

        try {
            // Parse the response as a JSON array
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                viewAllCustomers.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {

        }

        return viewAllCustomers;
    }

    public String[] getNadra(String cnic) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getNadra");
        object.put("cnic", cnic);
        output.println(object.toString());
        String response = input.readLine();
        return response.split(",");
    }



        public void deleteCustomer(String id) {
        JSONObject object = new JSONObject();
        object.put("function", "deleteCustomer");
        object.put("id", id);
        output.println(object);
    }

    public boolean isVlaidEdit(String str) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isVlaidEdit");
        object.put("str", str);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

    public void editCustomer(String editedString) {
        JSONObject object = new JSONObject();
        object.put("function", "editCustomer");
        object.put("editedString", editedString);
        output.println(object.toString());
    }
    public int cnic_count(String cnic) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "cnic_count");
        object.put("cnic", cnic);
        output.println(object.toString());
        return Integer.parseInt(input.readLine());
    }
    public boolean searchNadraFile(String cnic) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "searchNadraFile");
        object.put("cnic", cnic);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }

        public void updatePass(String username, String newPass) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "updatePass");
        object.put("username", username);
        object.put("newPass", newPass);
        output.println(object.toString());
    }

    public String[] getCustomer(String id) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getCustomer");
        object.put("id", id);

        output.println(object.toString());

        String response = input.readLine();

        String[] responseArray = response.split(",");
        System.out.println("Reached");

        return responseArray;
    }

    public ArrayList<String> getTax() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getTax");
        output.println(object.toString());

        // Read the response from the server
        String response = getResponse();

        ArrayList<String> getTax = new ArrayList<>();

        try {
            // Parse the response as a JSON array
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                getTax.add(jsonArray.getString(i));
            }
        } catch (JSONException e) {

        }

        return getTax;
    }


    public boolean isUnique(String str, String index) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isUnique");
        object.put("str", str);
        object.put("index", index);
        output.println(object.toString());
        return Boolean.parseBoolean(input.readLine());
    }
    // Add a Bill
    public void addBill(int customerID, String currentMonth, String regularUnits, String peakUnits,
                        String entryDate, double electricityCost, double salesTax, double fixedCharges,
                        String dueDate, String status, String paymentDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "addBill");
        object.put("customerID", customerID);
        object.put("currentMonth", currentMonth);
        object.put("regularUnits", regularUnits);
        object.put("peakUnits", peakUnits);
        object.put("entryDate", entryDate);
        object.put("electricityCost", electricityCost);
        object.put("salesTax", salesTax);
        object.put("fixedCharges", fixedCharges);
        object.put("dueDate", dueDate);
        object.put("status", status);
        object.put("paymentDate", paymentDate);
        output.println(object);
    }


    public void deleteBill(String id, String month, String eDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "deleteBill");
        object.put("id", id);
        object.put("month", month);
        object.put("eDate", eDate);
        output.println(object);
    }

    // Edit a Bill
    public void editBill(String str) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "editBill");
        object.put("str", str);
        output.println(object);
    }

    // Check if Access is Allowed for a Bill
    public boolean isAccessAble(String id, String month, String eDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "isAccessAble");
        object.put("id", id);
        object.put("month", month);
        object.put("eDate", eDate);
        output.println(object.toString());

        String response = input.readLine();
        return Boolean.parseBoolean(response);
    }

    public void changePaidstatus(String paymentDate, String custId, String billingmonth, String eDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "changePaidstatus");
        object.put("paymentDate", paymentDate);
        object.put("custId", custId);
        object.put("billingmonth", billingmonth);
        object.put("eDate", eDate);
        output.println(object);
    }

    // Update Customer File
    public void updateCustomerFile(String custId, String RUC, String PHUC) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "updateCustomerFile");
        object.put("custId", custId);
        object.put("RUC", RUC);
        object.put("PHUC", PHUC);
        output.println(object.toString());
    }

    // Get Bill for a Specific Customer
    public String[] getBill(String id, String month, String entryDate) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getBill1");
        object.put("id", id);
        object.put("month", month);
        object.put("entryDate", entryDate);

        output.println(object.toString());
        output.flush(); // Ensure the output is flushed and sent immediately

        // Wait for a valid response (ensure it is not null or empty)
        String response = getResponse(); // Use the getResponse method to fetch the data

        if (response != null && !response.isEmpty()) {
            System.out.println("Raw response: " + response); // Debugging output
            return response.split(","); // Split the response into an array and return
        } else {
            // Handle error or empty response
            System.err.println("Received empty or invalid response: " + response);
            return new String[0]; // Return an empty array or handle the error appropriately
        }
    }

    public ArrayList<String> getBillforAdd() throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "getBillforAdd");
        output.println(object.toString()); // Send the request to the server
        output.flush(); // Ensure the output is flushed and sent immediately

        // Receive response
        String response = getResponse(); // Response is a stringified JSON
        System.out.println("Raw response: " + response); // Debugging output

        ArrayList<String> getBillforAdd = new ArrayList<>();

        try {
            // Check if the response starts with "[" (indicating a JSON array)
            if (response != null && response.startsWith("[")) {
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    getBillforAdd.add(jsonArray.getString(i));
                }
            } else {
                // Handle non-JSON array responses (e.g., error messages)
                System.err.println("Unexpected response format: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace(); // Handle parsing issues
        }

        return getBillforAdd;
    }

    public ArrayList<String> viewSearchedBills(String search) throws IOException {
        JSONObject object = new JSONObject();
        object.put("function", "viewSearchedBills");
        object.put("search", search);
        output.println(object.toString()); // Send the request to the server

        // Receive response
        String response = getResponse(); // Response is a stringified JSON
        System.out.println("Raw response: " + response); // Debugging output

        ArrayList<String> viewSearchedBills = new ArrayList<>();

        try {
            if (response.startsWith("[")) {
                // If the response starts with '[', parse it as a JSONArray
                JSONArray jsonArray = new JSONArray(response);
                for (int i = 0; i < jsonArray.length(); i++) {
                    viewSearchedBills.add(jsonArray.getString(i));
                }
            } else {
                // Handle non-JSON array responses (e.g., error messages)
                System.err.println("Unexpected response format: " + response);
            }
        } catch (Exception e) {
            System.err.println("Error parsing JSON: " + e.getMessage());
            e.printStackTrace(); // Handle parsing issues
        }

        return viewSearchedBills;
    }



}

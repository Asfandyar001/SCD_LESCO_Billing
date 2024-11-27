//
////import org.json.simple.JSONObject;
//
//import java.io.*;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.Map;
//
//public class Client {
//    private static Client client;
//    private Socket socket;
//    private BufferedReader input;
//    private PrintWriter output;
//
//    private Client(String IPAddress, int port) throws IOException {
//        socket = new Socket(IPAddress, port);
//        input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
//        output = new PrintWriter(socket.getOutputStream(), true); // Auto-flush enabled
//    }
//
//    public static Client getInstance(String IPAddress, int port) throws IOException {
//        if (client == null) {
//            client = new Client(IPAddress, port);
//        }
//        return client;
//    }
//
//    public boolean addNewBill(String custID, String currentMeterReading, String currentMeterReadingPeak, String billingMonth) {
//        JSONObject object = new JSONObject();
//        object.put("custID", custID);
//        object.put("currentMeterReading", currentMeterReading);
//        object.put("currentMeterReadingPeak", currentMeterReadingPeak);
//        object.put("billingMonth", billingMonth);
//        output.println(object.toString());
//        return true;
//    }
//
//    public ArrayList<String> viewAllBills() {
//        JSONObject object = new JSONObject();
//        object.put("action", "viewAllBills");
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public ArrayList<String> viewSearchedBills(String search) {
//        JSONObject object = new JSONObject();
//        object.put("search", search);
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public void deleteBill(String id, String month, String eDate) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        object.put("month", month);
//        object.put("eDate", eDate);
//        output.println(object.toString());
//    }
//
//    public boolean isAccessAble(String id, String month, String eDate) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        object.put("month", month);
//        object.put("eDate", eDate);
//        output.println(object.toString());
//        return true;
//    }
//
//    public boolean isValidEdit(String row) {
//        JSONObject object = new JSONObject();
//        object.put("row", row);
//        output.println(object.toString());
//        return true;
//    }
//
//    public void editBill(String editedString) {
//        JSONObject object = new JSONObject();
//        object.put("editedString", editedString);
//        output.println(object.toString());
//    }
//
//    public boolean changePaidStatus(Emp_Change_Bill_Status changeStatus) {
//        JSONObject object = new JSONObject();
//
//        output.println(object.toString());
//        return true;
//    }
//
//    public void updateCustomerFile(String custID, String RUC, String PHUC) {
//        JSONObject object = new JSONObject();
//        object.put("custID", custID);
//        object.put("RUC", RUC);
//        object.put("PHUC", PHUC);
//        output.println(object.toString());
//    }
//
//    public void writeFile(ArrayList<String> array, String filename) {
//        JSONObject object = new JSONObject();
//        object.put("filename", filename);
//        object.put("data", array);
//        output.println(object.toString());
//    }
//
//    public void appendFile(String filename, String data) {
//        JSONObject object = new JSONObject();
//        object.put("filename", filename);
//        object.put("data", data);
//        output.println(object.toString());
//    }
//
//    public String[] getTaxData(String custType, String phase) {
//        JSONObject object = new JSONObject();
//        object.put("custType", custType);
//        object.put("phase", phase);
//        output.println(object.toString());
//        return new String[]{};
//    }
//
//    public boolean validateCustomerID(String id) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        output.println(object.toString());
//        return true;
//    }
//
//    public boolean validateCustomerIDfromBillFile(String id, String month, String date) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        object.put("month", month);
//        object.put("date", date);
//        output.println(object.toString());
//        return true;
//    }
//
//    public boolean viewBill(frame f, Emp_ViewBill_NoBill noBill) {
//        JSONObject object = new JSONObject();
//        object.put("frame", f.toString());
//        object.put("noBill", noBill.toString());
//        output.println(object.toString());
//        return true;
//    }
//
//    public String[] getBillList() {
//        JSONObject object = new JSONObject();
//        object.put("action", "getBillList");
//        output.println(object.toString());
//        return new String[]{};
//    }
//
//    public void viewReport(Emp_View_Report viewReport) {
//        JSONObject object = new JSONObject(viewReport);
//        output.println(object.toString());
//    }
//
//    public boolean isDigits(String str) {
//        JSONObject object = new JSONObject();
//        object.put("str", str);
//        output.println(object.toString());
//        return true;
//    }
//
//    public boolean isCustomerValid(String id, String cnic) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        object.put("cnic", cnic);
//        output.println(object.toString());
//        return true;
//    }
//
//    public String getUserName() {
//        JSONObject object = new JSONObject();
//        object.put("action", "getUserName");
//        output.println(object.toString());
//        return "";
//    }
//
//    public void CustInMenu(frame f, String name, Cust_Bill_Not_Found noBill) {
//        JSONObject object = new JSONObject();
//        object.put("frame", f.toString());
//        object.put("name", name);
//        object.put("noBill", noBill.toString());
//        output.println(object.toString());
//    }
//
//    public boolean addCustomer(String cnic, String name, String address, String phone, String custType, String meterType) {
//        JSONObject object = new JSONObject();
//        object.put("cnic", cnic);
//        object.put("name", name);
//        object.put("address", address);
//        object.put("phone", phone);
//        object.put("custType", custType);
//        object.put("meterType", meterType);
//        output.println(object.toString());
//        return true;
//    }
//
//    public boolean updateCNIC(String id, String cnic, String newDate) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        object.put("cnic", cnic);
//        object.put("newDate", newDate);
//        output.println(object.toString());
//        return true;
//    }
//
//    public ArrayList<String> viewExpireCnic() {
//        JSONObject object = new JSONObject();
//        object.put("action", "viewExpireCnic");
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public ArrayList<String> viewAllCnic() {
//        JSONObject object = new JSONObject();
//        object.put("action", "viewAllCnic");
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public ArrayList<String> viewSearchCNIC(String search) {
//        JSONObject object = new JSONObject();
//        object.put("search", search);
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public ArrayList<String> viewAllCustomers() {
//        JSONObject object = new JSONObject();
//        object.put("action", "viewAllCustomers");
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public ArrayList<String> viewSearchCustomer(String search) {
//        JSONObject object = new JSONObject();
//        object.put("search", search);
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//
//    public void deleteCustomer(String id) {
//        JSONObject object = new JSONObject();
//        object.put("id", id);
//        output.println(object.toString());
//    }
//
//    public boolean isVlaidEdit(String str) {
//        JSONObject object = new JSONObject();
//        object.put("str", str);
//        output.println(object.toString());
//        return true;
//    }
//
//    public void editCustomer(String editedString) {
//        JSONObject object = new JSONObject();
//        object.put("editedString", editedString);
//        output.println(object.toString());
//    }
//
//    public int cnic_count(String cnic) {
//        JSONObject object = new JSONObject();
//        object.put("cnic", cnic);
//        output.println(object.toString());
//        return 0;
//    }
//
//    public boolean searchNadraFile(String cnic) {
//        JSONObject object = new JSONObject();
//        object.put("cnic", cnic);
//        output.println(object.toString());
//        return true;
//    }
//
//    public ArrayList<String> viewBill() {
//        JSONObject object = new JSONObject();
//        object.put("action", "viewBill");
//        output.println(object.toString());
//        return new ArrayList<>();
//    }
//}

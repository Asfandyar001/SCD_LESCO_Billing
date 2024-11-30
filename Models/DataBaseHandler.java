//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package Models;

import java.sql.SQLException;
import java.util.ArrayList;

public class DataBaseHandler {
    private static DataBaseHandler instance = null;

    private DataBaseHandler() throws SQLException {
        DataBaseConnection.getConnection();
    }

    public static DataBaseHandler getInstance() throws SQLException {
        if (instance == null) {
            instance = new DataBaseHandler();
        }

        return instance;
    }

    public static String[] getBill(String id,String year){
        return CustomerModel.getBill(id,year);
    }
    public static String[] getCustomer(String id){
        return CustomerModel.getCustomer(id);
    }
    public static boolean validateEmployee(String username, String pass) {
        return EmplyoeeModel.validateEmployee(username, pass);
    }

    public static void updateEmployeePassword(String username, String newPass) {
        EmplyoeeModel.updatePass(username, newPass);
    }

    public static boolean isCustomerValid(String id, String cnic) {
        return CustomerModel.isCustomerValid(id, cnic);
    }

    public static boolean addCustomer(String id, String cnic, String name, String address, String phone, String custType, String meterType, String date, String RUC, String PHUC) {
        return CustomerModel.addCustomer(id, cnic, name, address, phone, custType, meterType, date, RUC, PHUC);
    }

    public static boolean validateCustomer(String id, String cnic, String month, String year) {
        return CustomerModel.validateCustomer(id, cnic, month, String.valueOf(year));
    }


    public static ArrayList<String> viewExpireCnic() {
        return CustomerModel.viewExpireCnic();
    }

    public static ArrayList<String> viewAllCnic() {
        return CustomerModel.viewAllCnic();
    }

    public static ArrayList<String> viewSearchCnic(String search) {
        return CustomerModel.viewSearchCnic(search);
    }
    public static boolean searchNadraFile(String cnic){
        return CustomerModel.searchNadraFile(cnic);
    }
    public static String[] getNadra(String cnic){
        return CustomerModel.getNadra(cnic);
    }
    public static ArrayList<String> viewAllCustomers() {
        return CustomerModel.viewAllCustomers();
    }
public static boolean updateExpiryDate(String cnic,String newdate){
        return CustomerModel.updateExpiryDate(cnic,newdate);
}
    public static ArrayList<String> viewSearchCustomer(String search) {
        return CustomerModel.viewSearchCustomer(search);
    }

    public static void deleteCustomer(String id) {
        CustomerModel.deleteCustomer(id);
    }

    public static void editCustomer(String editedString) {
        CustomerModel.editCustomer(editedString);
    }

    public static int cnic_count(String cnic) {
        return CustomerModel.cnic_count(cnic);
    }

    public static ArrayList<String> viewBill() {
        return CustomerModel.viewBill();
    }

    public static boolean isUnique(String str, String index) {
        return CustomerModel.isUnique(str, index);
    }
    public static ArrayList<String> getTax(){
        return TaxModel.getTaxData();
    }
    public static ArrayList<String> getData(){
        return TaxModel.getData();
    }
    public static void performTaxChanges(int row, String value) {
         TaxModel.performTaxChanges(row, value);
    }
    public static boolean addBillVerification1(String custid,String month){
        return BillingModel.addBillVerification1(custid,month);
    }
    public static ArrayList<String> getBillforAdd(){
        return BillingModel.getBillforAdd();
    }
    public static void addBill(int customerID, String currentMonth, String regularUnits, String peakUnits,
                               String entryDate, double electricityCost, double salesTax, double fixedCharges,
                               String dueDate, String status, String paymentDate) {
        BillingModel.addBill(customerID,currentMonth,regularUnits,peakUnits,entryDate,electricityCost,salesTax,fixedCharges,dueDate,status,paymentDate);
    }
    public static void deleteBill(String id, String month, String eDate) {
        BillingModel.deleteBill(id,month,eDate);
    }
    public static void editBill(String str){
        BillingModel.editBill(str);
    }
    public static boolean isAccessAble(String id, String month, String eDate){
        return BillingModel.isAccessAble(id,month,eDate);
    }
    public static ArrayList<String> viewSearchedBills(String search){
        return BillingModel.viewSearchedBills(search);
    }
    public static void changePaidstatus(String paymentDate,String custId,String billingmonth,String eDate){
        BillingModel.changePaidstatus(paymentDate,custId,billingmonth,eDate);
    }
    public static void updateCustomerFile(String custId,String RUC,String phuc){
        BillingModel.updateCustomerFile(custId,RUC,phuc);
    }
    public static String[] getBill1(String id, String month, String entryDate) {
        return BillingModel.getBill(id,month,entryDate);
    }
}

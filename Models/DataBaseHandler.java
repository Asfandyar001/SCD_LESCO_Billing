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

    public static boolean validateCustomer(String id, String cnic, String month, int year) {
        return CustomerModel.validateCustomer(id, cnic, month, year);
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

    public static boolean isUnique(String str, int index) {
        return CustomerModel.isUnique(str, index);
    }
    public static String[] getTax(){
        return TaxModel.getTaxData();
    }
}

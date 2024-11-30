package src;

import GUI.Emp_Change_Bill_Status;
import GUI.Emp_ViewBill_NoBill;
import GUI.Emp_View_Report;
import GUI.frame;
import Models.DataBaseHandler;

import javax.swing.*;
import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class Billing
{
    private String billFilename = "BillingInfo.txt";
    private String custFilename = "CustomerInfo.txt";
    private String taxFilename = "TariffTaxInfo.txt";
    private String[] arrayList;
    private String[] billList;

    public boolean addNewBill(String custID, String currentMeterReading, String currentMeterReadingPeak, String billingMonth) throws IOException {
        String paidStatus = "UnPaid";
        String paymentDate = "Not Paid";

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String readingEntryDate = currentDate.format(formatter);

        LocalDate reading = LocalDate.parse(readingEntryDate,formatter);
        LocalDate due = reading.plusDays(7);
        String dueDate = due.format(formatter);


            if(validateCustomerID(custID))
            {
            }
            else{
                JOptionPane.showMessageDialog(null,"Customer ID Invalid","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }


            if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("Aug") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
            {

            }
            else{
                JOptionPane.showMessageDialog(null,"Billing Month Invalid","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }

        String line;
        String[] data;
        LocalDate now = LocalDate.parse(readingEntryDate,formatter);
        int current_year = now.getYear();

        ArrayList<String> Bills=Client.getInstance().getBillforAdd();
                for(int i=0;i<Bills.size();i++){
                data = Bills.get(i).split(",");

                if(data[0].equals(custID) && data[1].equals(billingMonth))
                {

                    LocalDate fileDate = LocalDate.parse(data[4],formatter);
                    int billYear = fileDate.getYear();
                    if(billYear == current_year) {
                        JOptionPane.showMessageDialog(null,"The Bill For the Month Issued Already","Error",JOptionPane.ERROR_MESSAGE);
                        return false;
                    }
                }
                }


            if(!currentMeterReading.equals("0")  && isDigits(currentMeterReading))
            {
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid Current Meter Reading","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }

        String meter = arrayList[6];
        if (meter.equalsIgnoreCase("T"))
        {
            if (!currentMeterReadingPeak.equals("0") && isDigits(currentMeterReadingPeak))
            {
            }
            else
            {
                JOptionPane.showMessageDialog(null, "Current Meter Peak Readings Invalid", "Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        else if (meter.equalsIgnoreCase("S") && (currentMeterReadingPeak.trim().equals("Enter Current Peak Meter") || currentMeterReadingPeak.trim().isEmpty()))
        {
            currentMeterReadingPeak = "not_supported";
        }
        else if (meter.equalsIgnoreCase("S"))
        {
            JOptionPane.showMessageDialog(null, "Current Meter Reading Peak is Not Required for this Customer", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Current Meter Reading Peak Invalid", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }


        String[] tax = getTaxData(arrayList[5], arrayList[6]);
        float costOfElectricity = 0;
        if(meter.equals("s") || meter.equals("S"))
        {
            if(Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8])<=0){
                JOptionPane.showMessageDialog(null,"Current Meter Reading Should be Greater than Regular Units Consumed","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            costOfElectricity = Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8]);
            costOfElectricity = costOfElectricity * Float.parseFloat(tax[1]);
        }
        else if(meter.equals("t") || meter.equals("T"))
        {
            if((Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8]))<=0 || (Float.parseFloat(currentMeterReadingPeak) - Float.parseFloat(arrayList[9]))<=0){
                JOptionPane.showMessageDialog(null,"Meter Readings Should be Greater than Regular Units","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
            float regular = (Float.parseFloat(currentMeterReading) - Float.parseFloat(arrayList[8])) * Float.parseFloat(tax[1]);
            float peak = (Float.parseFloat(currentMeterReadingPeak) - Float.parseFloat(arrayList[9])) * Float.parseFloat(tax[2]);
            costOfElectricity = regular + peak;
        }

        float salesTaxAmount = costOfElectricity * (Float.parseFloat(tax[3])/100);
        float fixedCharges = Float.parseFloat(tax[4]);
        float totalBillingAmount = costOfElectricity + salesTaxAmount + fixedCharges;

        //String billData = custID + "," + billingMonth + "," + currentMeterReading + "," + currentMeterReadingPeak + "," + readingEntryDate + "," + costOfElectricity + "," + salesTaxAmount + "," + fixedCharges + "," + totalBillingAmount + "," + dueDate + "," + paidStatus + "," + paymentDate;
        Client.getInstance().addBill(Integer.parseInt(custID),billingMonth,currentMeterReading,currentMeterReadingPeak,readingEntryDate,costOfElectricity, salesTaxAmount,fixedCharges,dueDate,paidStatus,paymentDate);
       // appendFile(billFilename,billData);
        return true;
    }
    public ArrayList<String> viewAllBills() throws IOException {
        ArrayList <String> list= Client.getInstance().getBillforAdd();
        return list;
        /*
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            String line;
            while((line=br.readLine())!=null){
                list.add(line);
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return list;

         */
    }
    public ArrayList<String> viewSearchedBills(String search) throws IOException, InterruptedException {

        return Client.getInstance().viewSearchedBills(search);
        /*
        ArrayList<String> list = new ArrayList<>();
        String line;
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            while((line=br.readLine())!=null){
                String[] data;
                data = line.split(",");

                if(search.equals("Search") || search.isEmpty()){
                    list.add(line);
                }
                else if(data[0].equals(search) || data[1].equals(search) || data[2].equals(search) || data[3].equals(search) || data[4].equals(search) || data[5].equals(search) || data[6].equals(search) || data[7].equals(search) || data[8].equals(search) || data[9].equals(search) || data[10].equals(search) || data[11].equals(search) )
                {
                    list.add(line);
                }
            }
        }catch(IOException e){
            System.out.println("Error: " + e.getMessage());
        }
        return list;

         */
    }
    public void deleteBill(String id, String month, String eDate) throws IOException {
        Client.getInstance().deleteBill(id,month,eDate);
        /*
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            String line;
            while((line = br.readLine())!=null){
                String[] data = line.split(",");
                if(data[0].equals(id) && data[1].equals(month) && data[4].equals(eDate))
                {

                }
                else{
                    list.add(line);
                }
            }
        }catch (IOException e){
            System.out.println("Reader: " + e.getMessage() );
        }

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(billFilename))) {
            for(int i=0; i< list.size(); i++)
            {
                bw.write(list.get(i));
                bw.newLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("Error while writing to File: " + e.getMessage());
        }

         */
    }
    public boolean isAccessAble(String id,String month, String eDate) throws IOException {

       return Client.getInstance().isAccessAble(id,month,eDate);
/*
        System.out.println("New stuff");
        list = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            String line;
            while((line=br.readLine())!=null){

                String[] data = line.split(",");

                if(data[0].equals(id)){
                    list.add(line);
                    System.out.println(line);
                }
            }
        }catch(IOException e){
            System.out.println(e.getMessage());
        }

        String last_Index = list.getLast();
        String[] data = last_Index.split(",");
        System.out.println(id);
        System.out.println(data[1]);
        System.out.println();
        if(data[0].equals(id) && data[1].equals(month) && data[4].equals(eDate)){
            return true;
        }
        else{
            return false;
        }

 */
    }
    public boolean isValidEdit(String row) throws IOException {
        String[] data = row.split(",");

        if(data.length<12){
            return false;
        }

        float value1;
        float value2;
        float value3;
        float value4;
        if (data[5] == null || data[5].trim().isEmpty() || data[6] == null || data[6].trim().isEmpty() || data[7] == null || data[7].trim().isEmpty() || data[8] == null || data[8].trim().isEmpty()) {
            return false;
        }
        try {
            value1 = Float.parseFloat(data[5]);
            value2 = Float.parseFloat(data[6]);
            value3 = Float.parseFloat(data[7]);
            value4 = Float.parseFloat(data[8]);
        } catch (NumberFormatException e) {
            return false;
        }
        if(value1<0 || value2<0 || value3<0 || value4<0){
            return false;
        }

        validateCustomerID(data[0]);
        String meter = arrayList[6];
        if(meter.equals("S") && !data[3].trim().isEmpty()){
            return false;
        }
        if(meter.equals("T") && data[3].trim().isEmpty()){
            return false;
        }

        if(!isDigits(data[2]) || !isDigits(data[3]) || data[2].equals("0") || data[3].equals("0")){
            return false;
        }

        if (data[10].equals("UnPaid") && (data[11] == null || !data[11].equals("Not Paid"))) {
            return false;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate eDate = LocalDate.parse(data[4], formatter);
            LocalDate dueDate = LocalDate.parse(data[9], formatter);

            if(data[10].equals("Paid")){
                LocalDate paidDate = LocalDate.parse(data[11], formatter);{
                    if(paidDate.isBefore(eDate)){
                        return false;
                    }
                }
            }

            if(dueDate.isBefore(eDate))
            {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }

        return true;
    }
    public void editBill(String editedString) throws IOException {
        String[] data = editedString.split(",");

        if(data[3].trim().isEmpty()){
            data[3] = "not_supported";
        }

        String fix = data[0] + "," + data[1] + "," +data[2] + "," +data[3] + "," +data[4] + "," +data[5] + "," +data[6] + "," +data[7] + "," +data[8] + "," +data[9] + "," +data[10] + "," +data[11];
        Client.getInstance().editBill(fix);

        /*
        ArrayList<String> list = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            String line;
            while((line=br.readLine())!=null){
                String[] data2 = line.split(",");

                if(data2[0].equals(data[0]) && data2[1].equals(data[1]) && data2[4].equals(data[4])){
                    list.add(fix);
                }
                else{
                    list.add(line);
                }
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

        writeFile(list,billFilename);

         */
    }

    public boolean changePaidStatus(Emp_Change_Bill_Status changeStatus) throws IOException {


        String custID = changeStatus.getCustID();
        String billingMonth = changeStatus.getBillingMonth();

        if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("August") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
        {
        }
        else{
            JOptionPane.showMessageDialog(null,"Incorrect Month","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String entryDate = changeStatus.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            LocalDate date = LocalDate.parse(entryDate,formatter);
        }catch(DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(null,"Invalid Date: dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        if(validateCustomerID(custID))
        {

        }
        else{
            JOptionPane.showMessageDialog(null,"Customer ID Invalid","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        String readingEntryDate="";
        String RUC="";
        String PHUC="";
        String status="";

        String line;
        String[] data;
        boolean found = false;

        ArrayList<String> list=Client.getInstance().getBillforAdd();
        for(int i=0;i<list.size();i++) {
            data =list.get(i).split(",");
            if (data[0].equals(custID) && data[1].equals(billingMonth) && data[4].equals(entryDate)) {
                System.out.println("True");
                readingEntryDate = data[4];
                RUC = data[2];
                PHUC = data[3];
                status = data[10];
                found = true;
            }
        }
/*
        try(BufferedReader br = new BufferedReader(new FileReader(billFilename))){
            while((line = br.readLine())!=null)
            {
                data = line.split(",");
                if(data[0].equals(custID) && data[1].equals(billingMonth) && data[4].equals(entryDate))
                {
                    readingEntryDate = data[4];
                    RUC = data[2];
                    PHUC = data[3];
                    status=data[10];
                    found = true;
                }
            }
        }catch (IOException e){
            System.out.println("Error Reading File: " + e.getMessage());
        }

 */

        if(!found)
        {
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(status.equals("Paid"))
        {
            JOptionPane.showMessageDialog(null,"The Status was Already Updated to Paid","Error",JOptionPane.INFORMATION_MESSAGE);
            return false;
        }

        String paymentDate= changeStatus.getReceivedDate();
        LocalDate readingDate = LocalDate.parse(readingEntryDate,formatter);

        try{
            LocalDate date = LocalDate.parse(paymentDate,formatter);
            if(date.isBefore(readingDate))
            {
                JOptionPane.showMessageDialog(null,"Payment Date is before Reading Date","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }catch(DateTimeParseException e)
        {
            JOptionPane.showMessageDialog(null,"Invalid Received Date : dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }


        Client.getInstance().changePaidstatus(paymentDate,custID,billingMonth,entryDate);
/*
        ArrayList<String> array = new ArrayList<>();
        try {
            FileReader fr = new FileReader(billFilename);
            BufferedReader br = new BufferedReader(fr);

            String line2;

            while ((line2 = br.readLine()) != null) {
                String[] getLine = line2.split(",");
                if (getLine[0].equals(custID) && getLine[1].equals(billingMonth) && getLine[4].equals(entryDate)) {
                    getLine[11] = paymentDate;
                    array.add(getLine[0] + "," + getLine[1] + "," + getLine[2] + "," + getLine[3] + "," + getLine[4] + "," + getLine[5] + "," + getLine[6] + "," + getLine[7] + "," + getLine[8] + "," + getLine[9] + "," + "Paid" + "," + getLine[11]);
                } else {
                    array.add(line2);
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }


        writeFile(array,billFilename);

 */
        updateCustomerFile(custID,RUC,PHUC);
        return true;
    }

    public void updateCustomerFile(String custID, String RUC, String PHUC)
    {
        //Better to not touch this for now
        DataBaseHandler.updateCustomerFile(custID,RUC,PHUC);
        /*
        ArrayList<String> array = new ArrayList<>();
        try {
            FileReader fr = new FileReader(custFilename);
            BufferedReader br = new BufferedReader(fr);

            String line2;

            while ((line2 = br.readLine()) != null) {
                String[] getLine = line2.split(",");
                if (getLine[0].equals(custID)) {
                    getLine[8] = RUC;
                    getLine[9] = PHUC;
                    array.add(getLine[0] + "," + getLine[1] + "," + getLine[2] + "," + getLine[3] + "," + getLine[4] + "," + getLine[5] + "," + getLine[6] + "," + getLine[7] + "," + getLine[8] + "," + getLine[9]);
                } else {
                    array.add(line2);
                }
            }
            fr.close();
            br.close();
        }
        catch(IOException e)
        {
            System.out.println("Error: File Reading: " + e.getMessage());
        }

        writeFile(array,custFilename);*/
    }
    //no need
    /*
    public void writeFile(ArrayList<String> array, String filename)
    {
        try {
            FileWriter fw = new FileWriter(filename);
            BufferedWriter bw = new BufferedWriter(fw);

            for (int i = 0; i < array.size(); i++) {
                bw.write(array.get(i));
                bw.newLine();
            }
            bw.close();
        }
        catch (IOException e)
        {
            System.out.println("Error: File Writing");
        }
    }

    public void appendFile(String filename,String data)
    {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true))){
            bw.write(data);
            bw.newLine();
        }catch(IOException e)
        {
            System.out.println("Error Writing to file: " + e.getMessage());
        }
    }

     */

    public String[] getTaxData(String custType, String phase) throws IOException {
        String[] data = new String[]{""};
        ArrayList<String> list= Client.getInstance().getTax();
        String line1 = list.get(0);
        String line2 = list.get(1);
        String line3 = list.get(2);
        String line4 = list.get(3);


        if((custType.equals("D") || custType.equals("d")) && (phase.equals("s") || phase.equals("S")))
        {
            data = line1.split(",");
        }
        else if((custType.equals("c") || custType.equals("C")) && (phase.equals("s") || phase.equals("S")))
        {
            data = line2.split(",");
        }
        else if((custType.equals("d") || custType.equals("D")) && (phase.equals("t") || phase.equals("T")))
        {
            data = line3.split(",");
        }
        else if((custType.equals("c") || custType.equals("C")) && (phase.equals("t") || phase.equals("T")))
        {
            data = line4.split(",");
        }
        return data;
    }

    public boolean validateCustomerID(String id) throws IOException {
        String[] array= Client.getInstance().getCustomer(id);

        if (array[0].equals(id)) {
                    arrayList = array;
                    return true;
                }
        return false;


    }

    public boolean validateCustomerIDfromBillFile(String id,String month,String date) throws IOException {
        //cant sync dk why
                String[] index = DataBaseHandler.getBill1(id,month,date);

                if (index[0].trim().equals(id) && index[1].trim().equals(month) && index[4].trim().equals(date)) {
                    billList = index;
                    return true;
                }

        return false;
    }

    public boolean viewBill(frame f, Emp_ViewBill_NoBill noBill) throws IOException {
        String custID = noBill.getCustID();
        String billingMonth = noBill.getBillingMonth();

            if(billingMonth.equals("Jan") || billingMonth.equals("Feb") || billingMonth.equals("Mar") || billingMonth.equals("April") || billingMonth.equals("May") || billingMonth.equals("June") || billingMonth.equals("July") || billingMonth.equals("August") || billingMonth.equals("Sept") || billingMonth.equals("Oct") || billingMonth.equals("Nov") || billingMonth.equals("Dec"))
            {
            }
            else{
                JOptionPane.showMessageDialog(null,"Incorrect Billing Month","Error",JOptionPane.ERROR_MESSAGE);
                return false;
            }

        String entryDate = noBill.getDate();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        try{
            LocalDate date = LocalDate.parse(entryDate,formatter);
        }catch(DateTimeParseException e) {
            JOptionPane.showMessageDialog(null,"Invalid Date : dd/MM/yyyy","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }

        if(custID.isEmpty() || custID.equals("Type Customer ID")){
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        else if(validateCustomerIDfromBillFile(custID,billingMonth,entryDate))
        {}
        else{
            JOptionPane.showMessageDialog(null,"No Such Bill Found","Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    public String[] getBillList(){
        return billList;
    }

    public void viewReport(Emp_View_Report viewReport) throws IOException {
        float sum_paid=0;
        float sum_unpaid=0;
        String line;
        String[] data;
        ArrayList<String> list=Client.getInstance().getBillforAdd();

      for(int i=0;i< list.size();i++) {
          data = list.get(i).split(",");
          if (data[10].equals("Paid")) {
              sum_paid = sum_paid + Float.parseFloat(data[8]);
          } else if (data[10].equals("UnPaid")) {
              sum_unpaid = sum_unpaid + Float.parseFloat(data[8]);
          }

      }
        viewReport.setValues(String.valueOf(sum_paid), String.valueOf(sum_unpaid));
    }

    public boolean isDigits(String str)
    {
        for(int i=0; i<str.length();i++)
        {
            if(!Character.isDigit(str.charAt(i)))
            {
                return false;
            }
        }
        return true;
    }
}

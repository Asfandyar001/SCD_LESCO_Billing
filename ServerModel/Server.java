package ServerModel;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static final int port = 1234;
    public static Object billingInfo = new Object();
    public static Object customerInfo = new Object();
    public static Object employeesInfo = new Object();
    public static Object NadraInfo = new Object();
    public static Object TariffTaxInfo = new Object();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is running");
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New Device Connected");
                new HandleRequest(socket).start();

            }
        } catch (IOException exception) {
            JOptionPane.showMessageDialog(null, exception.getMessage());
        }
    }
}
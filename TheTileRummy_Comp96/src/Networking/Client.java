package Networking;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Reference https://www.geeksforgeeks.org/socket-programming-in-java/
 */
public class Client {

    // initialize socket and input output streams 
    private Socket socket = null;
    private DataInputStream in = null;//from server
    private DataInputStream input = null;//from client
    private DataOutputStream out = null;
    
    // constructor to put ip address and port 
    public Client(String address, int port) {
        // establish a connection 
        try {
            socket = new Socket(address, port);
            System.out.println("Connected");

            // takes input from terminal 
            input = new DataInputStream(System.in);

            // takes input from the client socket 
            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));
            // sends output to the socket 
            out = new DataOutputStream(socket.getOutputStream());
        } catch (UnknownHostException u) {
            System.out.println(u);
        } catch (IOException i) {
            System.out.println(i);
        }

        // close the connection 
        try {
            input.close();
            out.close();
            socket.close();
        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        Client client = new Client("127.0.0.1", 5000);
    }

}

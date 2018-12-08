package miniQuiz15Feb;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class IniServer {
    public static void main(String[] args) throws IOException {

        ServerSocket ss = new ServerSocket(3333);
        System.out.println("Waiting For Connectivity from Client");
        Socket s = ss.accept();
        System.out.println("Client Connected!");

        InetAddress ip = InetAddress.getLocalHost();
//        System.out.println("Current IP address : " + ip.getHostAddress());

        DataInputStream dataIn = new DataInputStream(s.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());

        String str = "", str2;
        while (!str.equals("stop")) {
            str = dataIn.readUTF();
            System.out.println("Client : " + str);
//            dataOut.writeUTF(ip.getHostAddress());

            if (str.equals("ITHB")) {
                dataOut.writeUTF("YA INI KAMPUS SAYA");
            } else if (str.equals("UNIKOM") || str.equals("ITB")) {
                dataOut.writeUTF("ITU TETANGGA SAYA");
            } else {
                dataOut.writeUTF("SAYA TIDAK KENAL");
            }

            dataOut.flush();
        }
        dataIn.close();
        s.close();
        ss.close();

    }
}

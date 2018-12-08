package miniQuiz15Feb;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class IniClient {
    public static void main(String[] args) throws IOException {
        InetAddress ip = InetAddress.getLocalHost();
//        System.out.println("Current IP address : " + ip.getHostAddress());

        Socket s = new Socket("localhost", 3333);
        DataInputStream dataIn = new DataInputStream(s.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2;
        while (!str.equals("stop")) {
            str = br.readLine();
            dataOut.writeUTF(str);
//            dataOut.writeUTF(ip.getHostAddress());
            dataOut.flush();
            str2 = dataIn.readUTF();
            System.out.println("Server : " + str2);
        }
        dataOut.close();
        s.close();
    }
}

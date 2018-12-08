package prakTigaA;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

class ThisServer {
    public static void main(String args[]) throws Exception {
        ServerSocket server1 = new ServerSocket(3333);
        ServerSocket server2 = new ServerSocket(4444);

        System.out.println("Waiting For Connectivity from Kios");
        Socket s1 = server1.accept();
        System.out.println("Kios Connected!");

        System.out.println("===================================");

        System.out.println("Waiting For Connectivity from Pusat");
        Socket s2 = server2.accept();
        System.out.println("Pusat Connected!");

        System.out.println("===================================");

        DataInputStream dataIn1 = new DataInputStream(s1.getInputStream());
        DataOutputStream dataOut1 = new DataOutputStream(s1.getOutputStream());

        DataInputStream dataIn2 = new DataInputStream(s2.getInputStream());
        DataOutputStream dataOut2 = new DataOutputStream(s2.getOutputStream());


        String strFrom1 = "";
        String strFrom2 = "";
        while (!strFrom1.equals("stop")) {
            strFrom1 = dataIn1.readUTF();
            System.out.println("Kios says: " + strFrom1);
            dataOut2.writeUTF(strFrom1);
            dataOut2.flush();

            strFrom2 = dataIn2.readUTF();
            System.out.println("Pusat says: " + strFrom2);
            dataOut1.writeUTF(strFrom2);
            dataOut1.flush();
        }
        dataIn1.close();
        s1.close();
        dataIn2.close();
        s2.close();
        server1.close();
    }
}

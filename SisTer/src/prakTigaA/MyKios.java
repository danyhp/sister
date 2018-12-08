package prakTigaA;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class MyKios {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 3333);
        DataInputStream dataIn = new DataInputStream(s.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2;
        while (!str.equals("stop")) {
            str = br.readLine();
            dataOut.writeUTF(str);
            dataOut.flush();
            str2 = dataIn.readUTF();
            System.out.println("Pusat : " + str2);
        }

        dataOut.close();
        s.close();
    }
}

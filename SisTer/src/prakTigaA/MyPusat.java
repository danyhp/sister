package prakTigaA;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

class MyPusat {
    public static void main(String args[]) throws Exception {
        Socket s = new Socket("localhost", 4444);
        DataInputStream dataIn = new DataInputStream(s.getInputStream());
        DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = "", str2;
        while (!str.equals("stop")) {
            str2 = dataIn.readUTF();
            System.out.println("MyKios : " + str2);
            str = br.readLine();
            dataOut.writeUTF(str);
            dataOut.flush();
        }

        dataOut.close();
        s.close();
    }
}

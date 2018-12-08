package prakMultiClient;

// echo server

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ReadyServer {
    public static void main(String args[]) {

        Socket s = null;
        ServerSocket ss2 = null;
        System.out.println("Server Listening......");
        try {
            ss2 = new ServerSocket(4445); // can also use static final PORT_NUM , when defined

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Server error");

        }

        while (true) {
            try {
                s = ss2.accept();
                System.out.println("connection Established");
                ServerThread st = new ServerThread(s);
                st.start();

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Connection Error");

            }
        }

    }

}

class ServerThread extends Thread {
    final String DEFAULT_RESPONSE = ".....";

    String line = null;
    BufferedReader is = null;
    PrintWriter os = null;
    Socket s = null;

    public ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        try {
            is = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = new PrintWriter(s.getOutputStream());

        } catch (IOException e) {
            System.out.println("IO error in server thread");
        }

        try {
            line = is.readLine();
            String response;
            while (line.compareTo("QUIT") != 0) {
                System.out.println("Client Say : " + line);

                if (line.contains("nama")) {
                    if (line.contains("presiden"))
                        if (line.contains("indonesia"))
                            response = ("Presiden Indonesia saat ini, Joko Widodo");
                        else
                            response = DEFAULT_RESPONSE;
                    else if (line.contains("pacar"))
                        response = ("pacarku, dia... :) ");
                    else if (line.contains("kamu") || line.contains("mu") || line.contains("anda"))
                        response = ("Nama Saya YouChat");
                    else if (line.contains("rektor") && line.contains("ithb"))
                        response = ("Pak Samuel Tarigan");
                    else if (line.contains("dosen") && line.contains("keren"))
                        response = ("Tentu saja Pak Sinung Suakanto");
                    else {
                        response = ("nama adalah nama");
                    }
                } else if (line.contains("alamat")) {
                    response = ("Alamat Saya di Cloud :)");
                } else if (line.contains("usia")) {
                    response = ("Saya baru lahir siang ini hehe...");
                } else {
                    response = DEFAULT_RESPONSE;
                }

                os.println(response);
                os.flush();
                System.out.println("Response to Client  :  " + response);
                line = is.readLine();
            }
        } catch (IOException e) {

            line = this.getName(); //reused String line for getting thread name
            System.out.println("IO Error/ Client " + line + " terminated abruptly");
        } catch (NullPointerException e) {
            line = this.getName(); //reused String line for getting thread name
            System.out.println("Client " + line + " Closed");
        } finally {
            try {
                System.out.println("Connection Closing..");
                if (is != null) {
                    is.close();
                    System.out.println(" Socket Input Stream Closed");
                }

                if (os != null) {
                    os.close();
                    System.out.println("Socket Out Closed");
                }
                if (s != null) {
                    s.close();
                    System.out.println("Socket Closed");
                }

            } catch (IOException ie) {
                System.out.println("Socket Close Error");
            }
        }//end finally
    }
}
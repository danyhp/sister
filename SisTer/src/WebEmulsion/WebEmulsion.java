package WebEmulsion;

import java.net.*;
import java.io.*;

public class WebEmulsion {
    public static void main(String[] args) throws Exception {
        // Buat socket
        int port = 80;
        ServerSocket serverSocket = new ServerSocket(port);
        System.err.println("Server Ready at port : " + port);

        // repeatedly wait for connections, and process
        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.err.println("Terima request dari client");

            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

            String s;
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                if (s.isEmpty()) {
                    break;
                }
            }

            out.write("HTTP/2.0 200 OK\r\n");
            out.write("Date: Fri, 31 Dec 1999 23:59:59 GMT\r\n");
            out.write("Server: Web Server Buatan ITHB/0.8.4\r\n");
            out.write("Content-Type: text/html\r\n");
            out.write("Content-Length: 200\r\n");
            out.write("Expires: Sat, 01 Jan 2000 00:59:59 GMT\r\n");
            out.write("Last-modified: Fri, 09 Aug 1996 14:21:40 GMT\r\n");
            out.write("\r\n");
            out.write("<TITLE>Selamat Datang di Web Server</TITLE>");
            out.write("<P>Ini web server buatan .</P>");

            // Tutup Koneksi
            System.err.println("Data telah dikirimkan dan client siap di tutup kembali");
            out.close();
            in.close();
            clientSocket.close();
        }
    }

}

package pw.artwhite;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;


public class ClientSocket {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        try (Socket socket = new Socket("localhost", 8080)) {

            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();

            BufferedReader bufIn = new BufferedReader( new InputStreamReader( inputStream ) );
            BufferedWriter bufOut = new BufferedWriter( new OutputStreamWriter( outputStream ) );

            String msg = "Привет";

            bufOut.write(msg);
            System.out.println("Отправил серверу: " + msg);
            bufOut.newLine(); //HERE!!!!!!
            bufOut.flush();

            while ((msg = bufIn.readLine()) != "") {
                if (msg == "") break;
                System.out.println("Сервер прислал: " + msg);
                msg = sc.nextLine();
                bufOut.write(msg);
                System.out.println("Ты отправил: " + msg);
                bufOut.newLine(); //HERE!!!!!!
                bufOut.flush();
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
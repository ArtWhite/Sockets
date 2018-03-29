package pw.artwhite;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new java.net.ServerSocket(8080);
        System.out.println("Ожижание клиента...");

        try (Socket clientSocket = serverSocket.accept()) {
            InputStream inputStream = clientSocket.getInputStream();
            OutputStream outputStream = clientSocket.getOutputStream();
            System.out.println("Новое соединение: " + clientSocket.getInetAddress().toString());

            BufferedReader bufIn = new BufferedReader( new InputStreamReader( inputStream ) );
            BufferedWriter bufOut = new BufferedWriter( new OutputStreamWriter( outputStream ) );

            String msg;

            while ((msg = bufIn.readLine()) != "") {
                System.out.println("Клиент отправил: " + msg);
                bufOut.write(msg);
                System.out.println("Сервер отправил: " + msg);
                bufOut.newLine(); //HERE!!!!!!
                bufOut.flush();
                Thread.sleep(2000);
            }

            System.out.println("Клиент всё");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
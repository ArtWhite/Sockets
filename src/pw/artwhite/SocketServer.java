package pw.artwhite;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

            int request;

            while ((request = inputStream.read()) != -1) {
                System.out.println("Клиент отправил: " + request);
                outputStream.write(++request);
                System.out.println("Сервер отправил: " + request);
                outputStream.flush();
                Thread.sleep(2000);
            }

            System.out.println("Клиент всё");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
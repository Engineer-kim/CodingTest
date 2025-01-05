package com.brick.codingtest.Solution2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class PinPong {

    public static final int PORT = 8081;


    public static void startServer() {
        new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(PORT)) {
                while (true) {
                    Socket clientSocket = serverSocket.accept();
                    handleClient(clientSocket);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void handleClient(Socket clientSocket) {
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
             PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)) {
            String inputValue;
            while ((inputValue = in.readLine()) != null) {
                String response = "Ping".equals(inputValue) ? "Pong" : inputValue;
                out.println(response);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
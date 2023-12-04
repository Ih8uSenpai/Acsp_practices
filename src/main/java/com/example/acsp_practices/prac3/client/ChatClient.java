package com.example.acsp_practices.prac3.client;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.Scanner;

import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String HOST = "localhost";
    private static final int PORT = 12345;

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(HOST, PORT);
        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        Scanner scanner = new Scanner(System.in);
        System.out.println("System: Enter your name:");
        String name = scanner.nextLine();
        out.println(name);

        Thread readThread = new Thread(() -> {
            try {
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine); // Прямое отображение полученных сообщений
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        readThread.start();

        System.out.println("Enter messages to send, 'exit' to quit:");
        String userInput;
        while (!(userInput = scanner.nextLine()).equals("exit") && !userInput.startsWith("System:")) {
            out.println(userInput);
        }

        socket.close();
        scanner.close();
        readThread.interrupt();
    }
}

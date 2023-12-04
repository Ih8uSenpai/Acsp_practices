package com.example.acsp_practices.prac3.server;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;

public class ChatServer {
    private static final int PORT = 12345;
    private static final List<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private static final ExecutorService pool = Executors.newFixedThreadPool(4);

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        scheduler.scheduleAtFixedRate(ChatServer::broadcastMessages, 0, 5, TimeUnit.SECONDS);

        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);

                String clientName = in.readLine();
                System.out.println("System: New client connected: " + clientName);

                ClientHandler clientThread = new ClientHandler(clientSocket, clientName);
                clients.add(clientThread);
                pool.execute(clientThread);
            }
        } finally {
            serverSocket.close();
            pool.shutdown();
            scheduler.shutdown();
        }
    }

    private static void broadcastMessages() {
        List<String> messagesToBroadcast = new ArrayList<>();
        for (ClientHandler client : clients) {
            messagesToBroadcast.addAll(client.getMessages());
            client.clearMessages();
        }

        if (!messagesToBroadcast.isEmpty()) {
            for (ClientHandler client : clients) {
                for (String message : messagesToBroadcast) {
                    client.out.println(message); // Отправка сообщения без добавления имени
                }
            }
        }
    }


    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;
        private final List<String> messages = new LinkedList<>();
        private PrintWriter out;
        private String clientName;

        public ClientHandler(Socket socket, String clientName) {
            this.clientSocket = socket;
            this.clientName = clientName;
        }

        public void run() {
            try {
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    synchronized (messages) {
                        messages.add(clientName + ": " + inputLine); // Добавление имени к сообщению
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }



        public List<String> getMessages() {
            synchronized (messages) {
                return new ArrayList<>(messages);
            }
        }

        public void clearMessages() {
            synchronized (messages) {
                messages.clear();
            }
        }

        public void sendMessages(List<String> messages) {
            for (String message : messages) {
                out.println(clientName + ": " + message);
            }
        }
    }
}

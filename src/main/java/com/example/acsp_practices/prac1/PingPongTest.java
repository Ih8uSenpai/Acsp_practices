package com.example.acsp_practices.prac1;

public class PingPongTest {
    public static void main(String[] args) throws InterruptedException {
        PingPong pingPong = new PingPong();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        pingPong.ping();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        pingPong.pong();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        thread1.start();
        Thread.sleep(100);
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
package com.example.acsp_practices.prac1;

public class PingPong {
    public void ping() throws InterruptedException {
        synchronized (this){
            Thread.sleep(1000);
            System.out.println("Ping");
            wait();
            notify();
        }
    }
    public void pong() throws InterruptedException {
        synchronized (this){
            Thread.sleep(1000);
            System.out.println("Pong");
            notify();
            wait();
        }
    }
}

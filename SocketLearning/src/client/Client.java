package client;

import java.io.*;
import java.net.*;
import model.MessageModel;

public class Client {

    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("localhost", 2804);
        SendThread sendThread = new SendThread(socket);
        Thread sthread = new Thread(sendThread);
        sthread.start();
        ReceiveThread receiveThread = new ReceiveThread(socket);
        Thread rthread = new Thread(receiveThread);
        rthread.start();
    }
}


package client;

import model.MessageModel;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveThread extends Thread{
    Socket socket;
    ReceiveThread(Socket _socket){
        this.socket = _socket;
    }
    public void run(){
        try{
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            while(true) {
                MessageModel messageModel = (MessageModel) inputStream.readObject();
                System.out.println(messageModel.getSenderName() + " says: " + messageModel.getMessage());
            }
        }catch (Exception e){
            System.out.println(e);
        }
    }
}

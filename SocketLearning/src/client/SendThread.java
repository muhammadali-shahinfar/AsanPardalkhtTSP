package client;

import model.MessageModel;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendThread extends Thread{
    Socket socket;
    String name = "unknown";
    SendThread(Socket _socket){
        this.socket = _socket;

    }
    public void run(){
        try{
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            while(true){
                if (name.equals("unknown")) {
                    name = br.readLine();
                }
                String receiver = br.readLine();
                String message = br.readLine();
                MessageModel messageModel = new MessageModel(name, receiver, message);
                outputStream.writeObject(messageModel);
                outputStream.flush();
            }

        }catch (Exception e){
            System.out.println(e);
        }
    }
}

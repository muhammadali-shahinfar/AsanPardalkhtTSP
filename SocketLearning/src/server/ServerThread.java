package server;
import java.io.*;
import java.net.*;
import java.util.*;
import model.MessageModel;
import model.UsersList;

public class ServerThread extends Thread{
    static HashMap<String,ObjectOutputStream> outputStreams = new HashMap<String,ObjectOutputStream>();
    Socket socket;
    String name = "unknown";

    public ServerThread(Socket _socket){
        this.socket = _socket;
    }

    public void run(){

        try {
            ObjectOutputStream selfOutputStream = new ObjectOutputStream(this.socket.getOutputStream());
            ObjectInputStream inputStream = new ObjectInputStream(this.socket.getInputStream());

            while (true) {
                MessageModel receivedData = (MessageModel) inputStream.readObject();
                if(name.equals("unknown")) {
                    setName(receivedData.getSenderName(), selfOutputStream);
                    System.out.println(receivedData.getSenderName());
                    System.out.println(name + " registered");
                }
                System.out.println(receivedData.getSenderName() + "try to send message to" + receivedData.getReciverName());
                sendMessage(receivedData);

            }
        } catch (Exception e){
            System.out.println(e);
        }
    }
    void setName(String senderName,ObjectOutputStream outputStream){
        outputStreams.put(senderName,outputStream);
        name = senderName;
    }

    void sendMessage(MessageModel messageModel){
        try {
            if(messageModel.getReciverName().equals("Server")){
                if(messageModel.getMessage().equals("Show users")){
                    StringBuilder usersList = new StringBuilder();
                    for(String user : outputStreams.keySet()){
                        usersList.append(user + "&");
                    }
                    outputStreams.get(messageModel.getSenderName()).writeObject(new MessageModel("Server", messageModel.getSenderName(), usersList.toString()));

                }
            }
            else if (outputStreams.containsKey(messageModel.getReciverName()))
                outputStreams.get(messageModel.getReciverName()).writeObject(new MessageModel(messageModel.getSenderName(), messageModel.getReciverName(), messageModel.getMessage()));
        } catch (Exception e){
            System.out.println(e);
        }
    }
}

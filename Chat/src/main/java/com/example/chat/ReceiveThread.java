package com.example.chat;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import model.MessageModel;

import java.io.ObjectInputStream;
import java.net.Socket;

public class ReceiveThread extends Thread {
    Socket socket;
    TextArea content;
    TextArea users;

    ReceiveThread(Socket _socket, TextArea _content, TextArea _users) {
        this.socket = _socket;
        this.content = _content;
        this.users = _users;
    }

    public void run() {
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            MessageModel previousMessageModel = new MessageModel("null","null","null");
            while (true) {
                MessageModel messageModel = (MessageModel) inputStream.readObject();

                String oldContent = content.getText();
                String newContent = new String();
                if(messageModel.getSenderName().equals("Server")){
                    users.setText("registered users:\n" + messageModel.getMessage().replaceAll("&","\n"));
                    newContent = oldContent;
                }
                else if(previousMessageModel.getReciverName().equals("null")){
                    newContent = messageModel.getSenderName() + " says: " + messageModel.getMessage();
                }
                else if(previousMessageModel.getSenderName().equals(messageModel.getSenderName())) {
                    newContent = oldContent + "\n" + messageModel.getMessage();
                }
                else {
                    newContent = oldContent + "\n----\n" + messageModel.getSenderName() + " says: " + messageModel.getMessage();
                }
                content.setText(newContent);
                previousMessageModel = messageModel;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

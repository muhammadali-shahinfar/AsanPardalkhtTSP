package com.example.chat;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MessageModel;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HelloApplication extends Application {
    String name;
    @Override
    public void start(Stage stage) throws IOException {

        Socket socket = new Socket("localhost", 2804);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());

        TextField inputName = new TextField("enter your name");
        TextField message = new TextField("message");
        TextField receiver = new TextField("receiver");
        TextArea content = new TextArea();
        TextArea users = new TextArea();
        GridPane layout = new GridPane();
        Button sendButton = new Button("send");
        Button showUsersButton = new Button("show users");
        Button loginButton = new Button("login");

        ReceiveThread receiveThread = new ReceiveThread(socket, content,users);
        Thread rThread = new Thread(receiveThread);
        rThread.start();

        sendButton.setOnAction(actionEvent -> {
            String receiverText = receiver.getText();
            String messageText = message.getText();
            MessageModel messageModel = new MessageModel(name, receiverText, messageText);
            try {
                outputStream.writeObject(messageModel);
                outputStream.flush();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        loginButton.setOnAction(actionEvent -> {
            layout.getChildren().remove(loginButton);
            layout.getChildren().remove(inputName);
            layout.add(content, 0, 0, 1, 1);
            layout.add(users, 1, 0, 1, 1);
            layout.add(message, 1, 5, 1, 1);
            layout.add(receiver, 0, 5, 1, 1);
            layout.add(sendButton, 0, 6, 1, 1);
            layout.add(showUsersButton, 1, 6, 1, 1);
            name = inputName.getText();
            stage.setTitle("Hello, " + name);
            try {
                outputStream.writeObject(new MessageModel(name, null, null));
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        showUsersButton.setOnAction(actionEvent1 -> {
            try {
                outputStream.writeObject(new MessageModel(name, "Server", "Show users"));
            } catch (Exception e) {
                System.out.println(e);
            }
        });




        Scene scene = new Scene(layout, 320, 240);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}
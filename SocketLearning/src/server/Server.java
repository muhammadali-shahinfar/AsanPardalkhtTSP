package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    /*ArrayList<ObjectOutputStreamModel> outputStreams;

    Server(){
        outputStreams = new ArrayList<ObjectOutputStreamModel>();
    }

    class ObjectOutputStreamModel{
        ObjectOutputStream objectOutputStream;
        String name;

        ObjectOutputStreamModel(ObjectOutputStream _objectOutputStream, String _name){
            name = _name;
            objectOutputStream = _objectOutputStream;
        }
    }

    ObjectOutputStream findReciverOutputStream(String name){
        for(ObjectOutputStreamModel objectOutputStreamModel : outputStreams){
            if(objectOutputStreamModel.name.equals(name)) {
                return objectOutputStreamModel.objectOutputStream;
            }
        }
        return null;
    }

    void addToOutputStreams(ObjectOutputStream objectOutputStream, String name){
        outputStreams.add(new ObjectOutputStreamModel(objectOutputStream, name));
    }*/



    public static void main(String[] args) {

            try {
                ServerSocket serverSocket = new ServerSocket(2804);
                while (true) {
                    Socket socket = serverSocket.accept();
                    ServerThread serverThread = new ServerThread(socket);
                    Thread thread = new Thread(serverThread);
                    thread.start();

                }
            } catch (Exception e){
                System.out.println(e);
            }







/*
            Socket client1S = clientSS.accept();
            Socket client2S = clientSS.accept();
            ObjectInputStream client1OIN = new ObjectInputStream(client1S.getInputStream());
            ObjectInputStream client2OIN = new ObjectInputStream(client2S.getInputStream());
            ObjectOutputStream client1OOUT = new ObjectOutputStream(client1S.getOutputStream());
            ObjectOutputStream client2OOUT = new ObjectOutputStream(client2S.getOutputStream());

            ObjectOutputStreamModel tmp = new Server().new ObjectOutputStreamModel(client1OOUT,"client1");
            server.outputStreams.add(tmp);
            tmp = new Server().new ObjectOutputStreamModel(client2OOUT,"client2");
            server.outputStreams.add(tmp);
            while(true){
               MessageModel recivedData = (MessageModel)client2OIN.readObject();
               ObjectOutputStream reciver = server.findReciverOutputStream(recivedData.getReciverName());
               reciver.writeObject(recivedData);
            }

            // open sockets
            //
        } catch (Exception e) {
            System.out.println(e);
        }*/

    }

}

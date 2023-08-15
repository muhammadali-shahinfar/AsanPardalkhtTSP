
package model;
import java.io.*;
public class MessageModel implements Serializable{
    String senderName;
    String reciverName;
    String message;
    public MessageModel(String _senderName, String _reciverName, String _message){
        senderName = _senderName;
        reciverName = _reciverName;
        message = _message;
    }
    public String getSenderName(){
        return senderName;
    }
    public String getReciverName(){
        return reciverName;
    }

    public String getMessage() {
        return message;
    }
}

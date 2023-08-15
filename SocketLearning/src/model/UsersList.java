package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersList implements Serializable {
    ArrayList<String> users;
    public UsersList(){
        users = new ArrayList<String>();

    }
   public void addUser(String name){
        users.add(name);
    }
}

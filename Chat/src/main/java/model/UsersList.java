package model;

import java.io.Serializable;
import java.util.ArrayList;

public class UsersList implements Serializable {
    ArrayList<String> users;
    UsersList(){
        users = new ArrayList<String>();

    }
    void addUser(String name){
        users.add(name);
    }
}

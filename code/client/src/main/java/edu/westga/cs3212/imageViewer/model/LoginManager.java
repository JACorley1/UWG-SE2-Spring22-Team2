package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;

public class LoginManager {
    
    private ArrayList<User> users;

    public LoginManager() {
        this.users = new ArrayList<User>();
    }

    /*
        Verify user

    */
    public boolean login(String username, String password) {
        for (User currUser : users) {
            if (currUser.getUsername().equals(username) && currUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addUser(User user) {
        return this.users.add(user);
    }
}

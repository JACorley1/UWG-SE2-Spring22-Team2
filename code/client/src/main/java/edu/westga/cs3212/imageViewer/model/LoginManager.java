package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;

/** The Class Login Manager
* 
* @version CS 3212
*/
public class LoginManager {
    
    private ArrayList<User> users;

    /*
    *

    */
    public LoginManager() {
        this.users = new ArrayList<User>();
    }

    /** 
    *  Verifys if a user exist in system with the specified username and password
    *
    */
    public boolean login(String username, String password) {
        for (User currUser : users) {
            if (currUser.getUsername().equals(username) && currUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    /** 
    * Adds a user to a collection of users
    *
    */
    public boolean addUser(User user) {
        return this.users.add(user);
    }
}

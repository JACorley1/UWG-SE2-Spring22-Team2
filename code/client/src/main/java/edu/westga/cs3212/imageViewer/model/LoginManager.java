package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;

public class LoginManager {
    
    public static final String Duplicate_Username = "There is already a user with this username";
    public static final String INCORRECT_LOGIN_INFORMATION = "Invalid user name or password"; 
    private ArrayList<User> users;
    
    public LoginManager() {
        this.users = new ArrayList<User>();
    }

    /*
        Verify user

    */
    public boolean login(String username, String password) {
        for (User currUser : users) {
            if (currUser.getUsername().toLowerCase().equals(username.toLowerCase()) && currUser.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean addUser(User user) {
       for (User currUser : users) {
           if (currUser.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())) {
               return false;
           }
       }
        return this.users.add(user);
    }

    /**
     * Gets the size of the collection of users
     * 
     * @return size  the size of the collection
     */
    public int size() {
        return this.users.size();
    }
}

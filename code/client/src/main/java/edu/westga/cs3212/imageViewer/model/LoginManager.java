package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;

/** The Class Login Manager
* 
* @version CS 3212
*/
public class LoginManager {
    
    public static final String Duplicate_Username = "There is already a user with this username";
    public static final String INCORRECT_LOGIN_INFORMATION = "Invalid user name or password"; 
    private ArrayList<User> users;

    /** 
    * Instantiates the LoginManager class
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
            if (currUser.getUsername().toLowerCase().equals(username.toLowerCase()) && currUser.getPassword().equals(password)) {
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

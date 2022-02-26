package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;

/** The Class Login Manager
* @author Group Two
* @version CS 3212
*/
public class LoginManager {
    
    public static final String DUPLICATE_USERNAME = "There is already a user with this username";
    public static final String INCORRECT_LOGIN_INFORMATION = "Invalid user name or password"; 
    private static ArrayList<User> users;
    public static User loggedInUser;
    

    /** 
    * Instantiates the LoginManager class
    *
    */
    public LoginManager() {
        if (LoginManager.users == null) {
            LoginManager.users = new ArrayList<User>();
        }
    }

    /** 
    *  Verifys if a user exist in system with the specified username and password
    *
    *   @param username the username
    *   @param password the user's password
    *
    *   @return T/F If user was successful in logging in
    */
    public boolean login(String username, String password) {
        for (User currUser : LoginManager.users) {
            if (currUser.getUsername().toLowerCase().equals(username.toLowerCase()) && currUser.getPassword().equals(password)) {
                LoginManager.loggedInUser = currUser;
                return true;
            }
        }
        return false;
    }
    
    /** 
    * Adds a user to a collection of users
    *
    * @param user the user to be added
    *
    * @return T/F if user was added successfully
    */
    public boolean addUser(User user) {
        for (User currUser : LoginManager.users) {
            if (currUser.getUsername().toLowerCase().equals(user.getUsername().toLowerCase())) {
            return false;
            }
        }
        return LoginManager.users.add(user);
    }

    /**
     * Gets the size of the collection of users
     * 
     * @return size  the size of the collection
     */
    public int size() {
        return LoginManager.users.size();
    }

    /** Clears all Users out of LoginManager.
	 * 
	 * @precondition none
	 * @postcondition getUsers().isEmpty()
	 */
	public void clearUsers() {
		LoginManager.users = new ArrayList<User>();
	}

    /** Clears currently logged in user out of System.
	 * 
	 * @precondition none
	 * @postcondition getLoggedInUser() == null
	 */
	public void clearLoggedInUser() {
		LoginManager.loggedInUser = null;
	}

    /**
     * Gets Logged in user
     * 
     * @return User The logged in user
     */
    public User getLoggedInUser() {
        return LoginManager.loggedInUser;
    }
}

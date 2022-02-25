package edu.westga.cs3212.imageViewer.model;

/** The User class
* 
* @author Group Two
* @version CS 3212
*/
public class User {
    private String username;
    private String password;
    private ImageInventory images;

    public User(String username, String password) {
        if (username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }

        if (password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty");
        }

        this.username = username;
        this.password = password;
        this.images = new ImageInventory();
    }

    /**
     * Gets the username
     * 
     * @return username the name of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the username
     * 
     * @return password the password of this user
     */
    public String getPassword() {
        return this.password;
    }

    public String toString() {
        return "User: " + this.username;
    }

    /**
     * Adds image the user's image collection
     * 
     * @param picture
     * @return T/F image was successfully aded
     */
    public boolean addImage(Image picture) {
        return this.images.addImage(picture);
    }
    
}

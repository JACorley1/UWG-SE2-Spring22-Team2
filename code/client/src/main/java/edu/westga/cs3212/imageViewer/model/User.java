package edu.westga.cs3212.imageViewer.model;

// TODO: Auto-generated Javadoc
/**
 *  The User class.
 *
 * @author Group Two
 * @version CS 3212
 */
public class User {
    
    /** The username. */
    private String username;
    
    /** The password. */
    private String password;
    
    /** The images. */
    private ImageInventory images;

    /**
     * Instantiates a new user.
     *
     * @param username the username
     * @param password the password
     */
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
     * Gets the username.
     *
     * @return username the name of the user
     */
    public String getUsername() {
        return this.username;
    }

    /**
     * Gets the username.
     *
     * @return password the password of this user
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * To string.
     *
     * @return the string
     */
    public String toString() {
        return "User: " + this.username;
    }

    /**
     * Adds image the user's image collection.
     *
     * @param picture the picture
     * @return T/F image was successfully aded
     */
    public boolean addImage(Picture picture) {
        return this.images.addImage(picture);
    }
    
    /**
     * Gets the images.
     *
     * @return the images
     */
    public ImageInventory getImages() {
    	return this.images;
    }
    
}

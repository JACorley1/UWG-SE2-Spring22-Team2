package edu.westga.cs3212.imageViewer.model;

public class User {
    private String username;
    private String password;
    // private ArrayList<Image> images;

    public User(String username, String password) {
        if(username == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        if (username.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        if(password == null) {
            throw new IllegalArgumentException("Username cannot be null");
        }

        if (password.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }

        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String ToString() {
        return "User: " + this.username;
    }
    
}

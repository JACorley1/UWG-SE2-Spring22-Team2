package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestLoginManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.User;

class TestLogin {
    
    @Test
    void TestValidLoginWithOneUser() {
        LoginManager login = new LoginManager();
        User genericUser = new User("username", "password");
        login.addUser(genericUser);

        assertTrue(login.login("username", "password"));
        assertEquals(login.getLoggedInUser(), genericUser);
        login.clearUsers();
        login.clearLoggedInUser();
    }

    @Test
    void TestValidLoginWithMultipleUsers() {
        LoginManager login = new LoginManager();
        User genericUser = new User("username", "password");
        login.addUser(genericUser);
        login.addUser(new User("user", "password123"));
        login.addUser(new User("Timbo", "password"));
        login.addUser(new User("LeftLife", "passcode23"));

        assertTrue(login.login("username", "password"));
        assertEquals(login.getLoggedInUser(), genericUser);
        login.clearUsers();
        login.clearLoggedInUser();
    }

    @Test
    void TestInvalidLogin() {
        LoginManager login = new LoginManager();
        login.addUser(new User("username", "password"));

        assertFalse(login.login("username", "pword"));
        assertFalse(login.login("user", "password"));
        assertEquals(login.getLoggedInUser(), null);
        login.clearUsers();
        login.clearLoggedInUser();
    }

    @Test
    void TestInvalidLoginWithMultipleUsers() {
        LoginManager login = new LoginManager();
        login.addUser(new User("username", "password"));
        login.addUser(new User("user", "password123"));
        login.addUser(new User("Timbo", "password"));
        login.addUser(new User("LeftLife", "passcode23"));

        assertFalse(login.login("timbo", ""));
        assertFalse(login.login("timo", "password"));
        assertEquals(login.getLoggedInUser(), null);
        login.clearUsers();
        login.clearLoggedInUser();
    }

}

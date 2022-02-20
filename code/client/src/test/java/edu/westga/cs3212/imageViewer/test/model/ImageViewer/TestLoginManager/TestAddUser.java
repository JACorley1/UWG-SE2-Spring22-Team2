package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestLoginManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.User;

class TestAddUser {
    
    @Test
    void TestValidAddUser() {
        LoginManager login = new LoginManager();
        
        assertTrue(login.addUser(new User("username", "password")));
        assertEquals(1, login.size());
    }

    @Test
    void TestInvalidAddUserDuplicateUsername() {
        LoginManager login = new LoginManager();

        login.addUser(new User("username", "password"));
        login.addUser(new User("Timbo", "password"));
        login.addUser(new User("Lima", "Green"));
        
        assertFalse(login.addUser(new User("username", "password123")));
        assertEquals(3, login.size());
    }
}

package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestUser;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.User;

class TestConstructor {

    @Test
    void TestValidConstructor() {
        User user = new User("John", "password");

        assertAll(
            () -> assertEquals("John", user.getUsername()),
            () -> assertEquals("password", user.getPassword()));
    }
    
    @Test
    void TestInvalidParameters(){
        
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> {
                new User(null, "password");
            }),

            () -> assertThrows(IllegalArgumentException.class, () -> {
                new User("John", null);
            }),
            () -> assertThrows(IllegalArgumentException.class, () -> {
                new User("", "Password");
            }),
            () -> assertThrows(IllegalArgumentException.class, () -> {
                new User("John", "");
            }));
    }

    @Test
    void TestToString() {
        User user = new User("John", "password");

        assertEquals("User: John", user.ToString());
    }
}

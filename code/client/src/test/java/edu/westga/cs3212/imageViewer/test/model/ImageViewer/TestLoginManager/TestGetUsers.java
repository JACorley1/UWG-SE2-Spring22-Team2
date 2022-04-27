package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestLoginManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.LoginManager;

class TestGetUsers {

	@Test
    void testGetOneUser() {
        LoginManager login = new LoginManager();
        
        assertEquals(1, login.getUsers().size());
    }

}

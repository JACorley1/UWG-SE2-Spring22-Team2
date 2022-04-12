package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestUser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.User;

class TestAddImage {

	@Test
	void testAddOneImage() {
		User user = new User("username", "password");
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			//Image image = new Image(inputStream);
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			
			assertEquals(true, user.addImage(picture));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
	}

}

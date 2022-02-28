package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestUser;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.User;
import javafx.scene.image.Image;

class TestGetImages {

	@Test
	void testGetOneImage() {
		User user = new User("username", "password");
		ImageInventory inventory = new ImageInventory();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Image image = new Image(inputStream);
			Picture picture = new Picture(image, "Click to upload image");
			
			inventory.addImage(picture);
			user.addImage(picture);
			
			assertEquals(1, user.getImages().size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testGetSeveralImages() {
ImageInventory inventory = new ImageInventory();
		User user = new User("username", "password");
		
		FileInputStream inputStream;
		FileInputStream inputStream2;
		FileInputStream inputStream3;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			inputStream2 = new FileInputStream("Assets/upload.jpg");
			inputStream3 = new FileInputStream("Assets/upload.jpg");
			
			Image image = new Image(inputStream);
			Image image2 = new Image(inputStream2);
			Image image3 = new Image(inputStream3);
			
			Picture picture = new Picture(image, "Click to upload image");
			Picture picture2 = new Picture(image2, "Click to upload image");
			Picture picture3 = new Picture(image3, "Click to upload image");
			
			inventory.addImage(picture);
			inventory.addImage(picture2);
			inventory.addImage(picture3);
			
			user.addImage(picture);
			user.addImage(picture2);
			user.addImage(picture3);
			
			assertEquals(3, user.getImages().size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

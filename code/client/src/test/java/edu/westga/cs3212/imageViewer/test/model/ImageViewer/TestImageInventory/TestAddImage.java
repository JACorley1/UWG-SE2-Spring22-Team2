package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.scene.image.Image;
import edu.westga.cs3212.imageViewer.model.ImageInventory;

class TestAddImage {

	@Test
	void testNullImage() {
		ImageInventory inventory = new ImageInventory();
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.addImage(null);
		});
	}
	
	@Test
	void testAddOnePicture() {
		ImageInventory inventory = new ImageInventory();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Image image = new Image(inputStream);
			Picture picture = new Picture(image, "Click to upload image");
			inventory.addImage(picture);
			
			assertEquals(1, inventory.size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testAddSeveralImages() {
		ImageInventory inventory = new ImageInventory();
		
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
			
			assertEquals(3, inventory.size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

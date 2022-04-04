package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.scene.image.Image;

class TestRemoveImage {

	@Test
	void testNullPicture() {
		ImageInventory inventory = new ImageInventory();
		Picture picture = null;
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeImage(picture);
		});
	}
	
	@Test
	void testNullImage() {
		ImageInventory inventory = new ImageInventory();
		Image image = null;
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeImage(image);
		});
	}
	
	@Test
	void testRemoveOneImage() {
		ImageInventory inventory = new ImageInventory();
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Image image = new Image(inputStream);
			Picture picture = new Picture(image, "Click to upload image");
			inventory.addImage(picture);
			inventory.removeImage(picture);
			
			assertEquals(0, inventory.size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testRemoveCoupleImages() {
		ImageInventory inventory = new ImageInventory();
		
		FileInputStream inputStream;
		FileInputStream inputStream2;
		FileInputStream inputStream3;
		FileInputStream inputStream4;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			inputStream2 = new FileInputStream("Assets/upload.jpg");
			inputStream3 = new FileInputStream("Assets/upload.jpg");
			inputStream4 = new FileInputStream("Assets/upload.jpg");
			
			Image image = new Image(inputStream);
			Image image2 = new Image(inputStream2);
			Image image3 = new Image(inputStream3);
			Image image4 = new Image(inputStream4);
			
			Picture picture = new Picture(image, "Click to upload image");
			Picture picture2 = new Picture(image2, "Click to upload image");
			Picture picture3 = new Picture(image3, "Click to upload image");
			Picture picture4 = new Picture(image4, "Click to upload image");
			
			inventory.addImage(picture);
			inventory.addImage(picture2);
			inventory.addImage(picture3);
			inventory.addImage(picture4);
			
			inventory.removeImage(picture);
			inventory.removeImage(picture4);
			
			assertEquals(2, inventory.size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

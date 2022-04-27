package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.Picture;

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
	void testRemoveOneImage() {
		ImageInventory inventory = new ImageInventory();
		
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
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
			
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			Picture picture2 = new Picture(inputStream2, "Click to upload image", 2);
			Picture picture3 = new Picture(inputStream3, "Click to upload image", 3);
			Picture picture4 = new Picture(inputStream4, "Click to upload image", 4);
			
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
	
	@Test
	void testRemoveFalseImage() {
		ImageInventory inventory = new ImageInventory();
		
		FileInputStream inputStream;
		FileInputStream inputStream2;
		
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			inputStream2 = new FileInputStream("Assets/upload.jpg");
			
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			Picture picture2 = new Picture(inputStream2, "Click to upload image", 2);
			
			inventory.addImage(picture);
			
			assertEquals(false, inventory.removeImage(picture2));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

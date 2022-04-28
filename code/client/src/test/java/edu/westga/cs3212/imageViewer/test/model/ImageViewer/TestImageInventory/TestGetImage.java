package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.Picture;

class TestGetImage {

	@Test
	void testGetOneImage() {
		ImageInventory inventory = new ImageInventory();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			inventory.addImage(picture);
			
			assertEquals(picture, inventory.getImage(1));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testGetNullImage() {
		ImageInventory inventory = new ImageInventory();
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			inventory.addImage(picture);
			
			assertEquals(null, inventory.getImage(2));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

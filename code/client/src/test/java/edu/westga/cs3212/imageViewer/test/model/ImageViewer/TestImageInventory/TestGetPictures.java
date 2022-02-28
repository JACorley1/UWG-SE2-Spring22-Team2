package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.scene.image.Image;

class TestGetPictures {
	
	@Test
	void testGetZeroPictures() {
		ImageInventory inventory = new ImageInventory();
		
		assertEquals(0, inventory.getPictures().size());
	}
	@Test
	void testGetOnePicture() {
		ImageInventory inventory = new ImageInventory();
		
		FileInputStream inputStream;
		
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			
			Image image = new Image(inputStream);
			
			
			Picture picture = new Picture(image, "Click to upload image");
			
			inventory.addImage(picture);
			inventory.getPictures().add(image);
			
			assertEquals(1, inventory.getPictures().size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testGetSeveralPictures() {
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
			
			inventory.getPictures().add(image);
			inventory.getPictures().add(image2);
			inventory.getPictures().add(image3);
			
			assertEquals(3, inventory.getPictures().size());
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

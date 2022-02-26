package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Image;
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
	void testAddOneImage() {
		ImageInventory inventory = new ImageInventory();
		
		Image image = new Image("Cats", 25);
		
		inventory.addImage(image);
		
		assertEquals(1, inventory.size());
	}
	
	@Test
	void testAddSeveralImages() {
		ImageInventory inventory = new ImageInventory();
		
		Image image = new Image("Cats", 25);
		Image image2 = new Image("Dogs", 45);
		Image image3 = new Image("Pigeons", 80);
		
		inventory.addImage(image);
		inventory.addImage(image2);
		inventory.addImage(image3);
		
		assertEquals(3, inventory.size());
	}

}

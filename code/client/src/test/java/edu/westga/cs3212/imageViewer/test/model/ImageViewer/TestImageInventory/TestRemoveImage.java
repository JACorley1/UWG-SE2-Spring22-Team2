package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImageInventory;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Image;
import edu.westga.cs3212.imageViewer.model.ImageInventory;

class TestRemoveImage {

	@Test
	void testNullImage() {
		ImageInventory inventory = new ImageInventory();
		assertThrows(IllegalArgumentException.class, () -> {
			inventory.removeImage(null);
		});
	}
	
	@Test
	void testRemoveOneImage() {
		ImageInventory inventory = new ImageInventory();
		
		Image image = new Image("Cats", 25);
		
		inventory.addImage(image);
		inventory.removeImage(image);
		
		assertEquals(0, inventory.size());
	}
	
	@Test
	void testRemoveCoupleImages() {
		ImageInventory inventory = new ImageInventory();
		
		Image image = new Image("Cats", 25);
		Image image2 = new Image("Dogs", 45);
		Image image3 = new Image("Pigeons", 80);
		Image image4 = new Image("Cars", 47);
		
		inventory.addImage(image);
		inventory.addImage(image2);
		inventory.addImage(image3);
		inventory.addImage(image4);
		
		inventory.removeImage(image);
		inventory.removeImage(image3);
		
		assertEquals(2, inventory.size());
	}

}

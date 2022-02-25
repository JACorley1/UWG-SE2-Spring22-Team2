package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImage;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Image;

class TestConstructor {

	@Test
	void testNullImageTitle() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Image(null, 25);
		});
	}
	
	@Test
	void testInvalidImageId() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Image("Cats", -1);
		});
	}
	
	@Test
	void testValidImage() {
		Image image = new Image("Cats", 25);
		
		assertAll(
				() -> assertEquals("Cats", image.getTitle()),
				() -> assertEquals(25, image.getImageId()),
				() -> assertEquals("Cats: 25", image.toString())
				);
	}

}

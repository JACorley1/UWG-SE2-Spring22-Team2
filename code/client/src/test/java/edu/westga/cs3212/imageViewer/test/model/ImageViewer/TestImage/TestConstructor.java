package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.scene.image.Image;

class TestConstructor {

	@Test
	void testNullImageTitle() {
		Image image = null;
		assertThrows(IllegalArgumentException.class, () ->{
			new Picture(image, null);
		});
	}
	
	@Test
	void testNullImage() {
		assertThrows(IllegalArgumentException.class, () ->{
			new Picture(null, "Cats");
		});
	}
	
	@Test
	void testInvalidImageId() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream("Assets/upload.jpg");
		Image image = new Image(inputStream);
		Picture picture = new Picture(image, "Click to upload image");
		
		assertThrows(IllegalArgumentException.class, () -> {
			picture.setImageId(-1);
		});
		
	}
	
	@Test
	void testValidPicture() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Image image = new Image(inputStream);
			Picture picture = new Picture(image, "Click to upload image");
			picture.setImageId(25);
			assertAll(
					() -> assertEquals(image, picture.getPic()),
					() -> assertEquals("Click to upload image", picture.getTitle()),
					() -> assertEquals(25, picture.getImageId()),
					() -> assertEquals("Click to upload image: 25", picture.toString())
					);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
	}

}

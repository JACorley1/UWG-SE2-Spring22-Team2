package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImage;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.scene.image.Image;

class TestConstructor {

	@Test
	void testNullPictureTitle() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream("Assets/upload.jpg");
		Image image = null;
		assertThrows(IllegalArgumentException.class, () ->{
			new Picture(inputStream, null, 1);
		});
	}
	
	@Test
	void testInvalidImageId() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream("Assets/upload.jpg");
		
		assertThrows(IllegalArgumentException.class, () -> {
			new Picture(inputStream, "Click to upload image", -1);
		});
		
	}
	
	@Test
	void testValidPicture() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			assertAll(
					() -> assertEquals("Click to upload image", picture.getTitle()),
					() -> assertEquals(1, picture.getImageId())
					);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
	}

}

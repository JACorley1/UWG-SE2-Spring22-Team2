package edu.westga.cs3212.imageViewer.test.model.ImageViewer.TestImage;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.Picture;

class TestConstructor {

	@Test
	void testNullPictureTitle() throws FileNotFoundException {
		FileInputStream inputStream = new FileInputStream("Assets/upload.jpg");
		
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
					() -> assertEquals(1, picture.getImageId()),
					() -> assertEquals("Click to upload image: 1", picture.toString())
					);
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
		
	}
	
	@Test
	void testNotEqauls() {
		FileInputStream inputStream;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			
			assertEquals(false, picture.equals(null));
			
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}
	
	@Test
	void testTwoPicturesEqual() {
		FileInputStream inputStream;
		FileInputStream inputStream2;
		try {
			inputStream = new FileInputStream("Assets/upload.jpg");
			inputStream2 = new FileInputStream("Assets/upload.jpg");
			Picture picture = new Picture(inputStream, "Click to upload image", 1);
			Picture picture2 = new Picture(inputStream2, "Click to upload image", 1);
			assertEquals(true, picture.equals(picture2));
		} catch (FileNotFoundException exception) {
			exception.printStackTrace();
		}
	}

}

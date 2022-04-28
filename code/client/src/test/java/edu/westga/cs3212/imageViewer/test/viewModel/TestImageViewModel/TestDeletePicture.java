package edu.westga.cs3212.imageViewer.test.viewModel.TestImageViewModel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.junit.jupiter.api.Test;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.User;
import edu.westga.cs3212.imageViewer.view.viewModel.ImageViewModel;
import javafx.scene.image.Image;

class TestDeletePicture {

//	@Test
//	void testDeletePicture() {
//		LoginManager login = new LoginManager();
//		ImageInventory inventory = new ImageInventory();
//		ImageViewModel viewModel = new ImageViewModel();
//		User aUser = new User("username", "password");
//		login.addUser(aUser);
//
//		FileInputStream inputStream;
//		try {
//			inputStream = new FileInputStream("Assets/upload.jpg");
//			Image image = new Image(inputStream);
//			String title = "Upload image";
//
//			System.out.println(title);
//			Picture pic = new Picture(image, title);
//			inventory.addImage(pic);
//			aUser.addImage(pic);
//			assertEquals(true, viewModel.deletePicture(image));
//
//		} catch (FileNotFoundException exception) {
//			exception.printStackTrace();
//		}
//
//	}
//	
//	@Test
//	void testFalseDeletePicture() {
//		LoginManager login = new LoginManager();
//		ImageViewModel viewModel = new ImageViewModel();
//		User aUser = new User("username", "password");
//		login.addUser(aUser);
//
//		assertEquals(false, viewModel.deletePicture(null));
//	}

}

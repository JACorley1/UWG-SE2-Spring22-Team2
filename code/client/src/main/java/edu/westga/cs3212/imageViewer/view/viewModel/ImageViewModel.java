package edu.westga.cs3212.imageViewer.view.viewModel;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.User;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.json.JSONArray;
import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;


public class ImageViewModel {
	private final ListProperty<Image> pictureListProperty;
	private final ObjectProperty<Picture> selectedPictureProperty;
	private final ObjectProperty<Image> imageProperty;
	private final StringProperty titleProperty;

	private ImageInventory imageInventory;
	private ImageView imageView;
	
	/**
	 * Instantiates an ImageViewModel.
	 */
	public ImageViewModel() {

		this.selectedPictureProperty = new SimpleObjectProperty<Picture>();
		this.imageProperty = new SimpleObjectProperty<Image>();
		this.titleProperty = new SimpleStringProperty();

		this.imageInventory = new ImageInventory();
		this.imageView = new ImageView();
		this.pictureListProperty = new SimpleListProperty<Image>();
	}

	/**
	 * Gets the picture list property
	 * 
	 * @return the picture list property
	 */
	public ListProperty<Image> getPictureListProperty() {
		return this.pictureListProperty;
	}

	/**
	 * Gets the selected picture property
	 * 
	 * @return the selected picture property
	 */
	public ObjectProperty<Picture> getSelectedPictureProperty() {
		return this.selectedPictureProperty;
	}

	public ObjectProperty<Image> getImageProperty() {
		return this.imageProperty;
	}

	/**
	 * Gets the title property
	 * 
	 * @return the title property
	 */
	public StringProperty getTitleProperty() {
		return this.titleProperty;
	}
	
	/**
	 * Adds a picture to the view
	 * 
	 * @precondition none
	 * @postcondition a picture has been added to view
	 */
	public void addPicture() {
		// Image newImage = this.imageProperty.get();
		// // System.out.println(newImage.getUrl());
		// String imageName = this.titleProperty.getValue();
		// Picture.imageId += 1;
		// Picture test = new Picture(newImage, imageName);
		// this.imageInventory.addImage(test);
		// System.out.println(test);
		// LoginManager.loggedInUser.addImage(test);
		// this.pictureListProperty.set(FXCollections.observableArrayList(this.imageInventory.getPictures()));
	}

	/**
	 * Deletes a picture from the view
	 * 
	 * @precondition none
	 * @postcondition a picture has been deleted
	 * 
	 * @return true if a picture has been deleted; false otherwise
	 */
	public boolean deletePicture(int imageId) {
		//String pictureName = this.removePictureFromClient(picture);
		System.out.println(imageId);
		Context getImageContext = ZMQ.context(1);

		// Socket to talk to server
		System.out.println("Connecting to hello world server");

		try (@SuppressWarnings("deprecation")
		Socket deleteImageSocket = getImageContext.socket(ZMQ.REQ)) {
			deleteImageSocket.connect("tcp://127.0.0.1:5555");

			String deleteImageRequest = "{\"requestType\" : \"deleteImages\", \"imageId\" : \""+ imageId + "\"}";
			System.out.println("Client - Sending delete Image Request");
			deleteImageSocket.send(deleteImageRequest.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Successful request send.");

			String help = deleteImageSocket.recvStr();

			JSONObject checker = new JSONObject(help);

			int success = checker.getInt("successCode");

			if (success == 1) {
				System.out.println("Images Successfully removed");

				return true;
			} else {
				System.out.println("Image failed to be removed");
				return false;
			}

		}

	}

	//private String removePictureFromClient(Image picture) {
		// String pictureName = "";
		// LoginManager login = new LoginManager();
		// for (User currUser : login.getUsers()) {
		// 	if (currUser.getImages().getPictures().contains(picture)) {
		// 		pictureName = currUser.getImages().getImage(picture).getTitle();
		// 		System.out.println(pictureName + "This is the images name");
		// 		currUser.getImages().removeImage(picture);
		// 	}
		// }
		// if (picture != null) {
		// 	this.imageInventory.removeImage(picture);
		// 	this.pictureListProperty.set(FXCollections.observableArrayList(this.imageInventory.getPictures()));
		// }
		// return pictureName;
	//}	
}

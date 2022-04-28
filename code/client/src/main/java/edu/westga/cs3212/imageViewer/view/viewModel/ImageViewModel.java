package edu.westga.cs3212.imageViewer.view.viewModel;

import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.ServerCommunitcator;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import org.json.JSONObject;


public class ImageViewModel {
	private final ListProperty<Image> pictureListProperty;
	private final ObjectProperty<Picture> selectedPictureProperty;
	private final ObjectProperty<Image> imageProperty;
	private final StringProperty titleProperty;

	
	/**
	 * Instantiates an ImageViewModel.
	 */
	public ImageViewModel() {

		this.selectedPictureProperty = new SimpleObjectProperty<Picture>();
		this.imageProperty = new SimpleObjectProperty<Image>();
		this.titleProperty = new SimpleStringProperty();

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
		System.out.println(imageId);
		System.out.println("Connecting to hello world server");

			String deleteImageRequest = "{\"requestType\" : \"deleteImage\", \"imageId\" : \""+ imageId + "\"}";
			System.out.println("Client - Sending delete Image Request");
			JSONObject checker = ServerCommunitcator.sendMessage(deleteImageRequest);
			System.out.println("Successful request send.");

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

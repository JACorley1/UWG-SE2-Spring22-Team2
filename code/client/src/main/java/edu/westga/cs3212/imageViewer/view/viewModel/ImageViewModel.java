package edu.westga.cs3212.imageViewer.view.viewModel;

import edu.westga.cs3212.imageViewer.model.ImageInventory;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageViewModel {
	private final ListProperty<Image> pictureListProperty;
	private final ObjectProperty<Picture> selectedPictureProperty;
	private final ObjectProperty<Image> imageProperty;
	private final StringProperty titleProperty;
	
	private ImageInventory imageInventory;
	private ImageView imageView;
	
	
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
	
	public void addPicture() {
		Image newImage = this.imageProperty.get();
    	//System.out.println(newImage.getUrl());
    	String imageName = this.titleProperty.getValue();
    	Picture.imageId += 1;
    	Picture test = new Picture(newImage, imageName);
    	this.imageInventory.addImage(test);
    	System.out.println(test);
    	LoginManager.loggedInUser.addImage(test);
    	this.pictureListProperty.set(FXCollections.observableArrayList(this.imageInventory.getPictures()));
	}
	
	/**
	 * Deletes a picture from the view
	 * 
	 * @precondition none
	 * @postcondition a picture has been deleted
	 * 
	 * @return true if a picture has been deleted; false otherwise
	 */
	public void deletePicture() {
		Image img = this.imageProperty.get();
//		String title = this.titleProperty.getValue();
//		Picture picture = new Picture(img);
//		System.out.println(picture.getImageId());
		//LoginManager.loggedInUser.deleteImage(picture);
		
//		if (picture != null) {
//			this.imageInventory.removeImage(picture);
//			this.pictureListProperty.set(FXCollections.observableArrayList(this.imageInventory.getPictures()));
//		}
		
	}
}

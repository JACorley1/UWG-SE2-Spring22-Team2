package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

public class ImageInventory {
	
	private List<Picture> images;
	
	public ImageInventory() {
		this.images = new ArrayList<Picture>();
	}
	
	/**
	 * Adds the image.
	 *
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */
	public boolean addImage(Picture picture) {
		return this.images.add(picture);
	}
	
	/**
	 * Removes the image.
	 *
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */
	public boolean removeImage(Picture picture) {
		return this.images.remove(picture);
	}
	
	/**
	 * Gets the pictures.
	 *
	 * @return the pictures
	 */
	public ArrayList<Image> getPictures() {
		ArrayList<Image> allImages = new ArrayList<Image>();
		
		for(Picture img: this.images) {
			allImages.add(img.getPic());
		}
		
		return allImages;
	}

}

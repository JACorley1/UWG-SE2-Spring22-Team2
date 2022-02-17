package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;
import java.util.List;

public class ImageInventory {
	
	private List<Image> images;
	
	public ImageInventory() {
		this.images = new ArrayList<Image>();
	}
	
	/**
	 * Adds the image.
	 *
	 * @param picture the picture
	 */
	public void addImage(Image picture) {
		this.images.add(picture);
	}
	
	/**
	 * Removes the image.
	 *
	 * @param picture the picture
	 */
	public void removeImage(Image picture) {
		this.images.remove(picture);
	}

}

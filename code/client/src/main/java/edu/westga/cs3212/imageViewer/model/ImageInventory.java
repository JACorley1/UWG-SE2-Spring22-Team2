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
	 * 
	 * @return T/F If image was removed
	 */
	public boolean addImage(Image picture) {
		return this.images.add(picture);
	}
	
	/**
	 * Removes the image.
	 *
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */
	public boolean removeImage(Image picture) {
		return this.images.remove(picture);
	}

}

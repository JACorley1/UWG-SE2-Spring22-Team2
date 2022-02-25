package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * The ImageInventory class.
 * 
 * @author Oumar Diallo & Janera Smith
 * @version Spring 2022
 *
 */
public class ImageInventory {
	
	private List<Image> images;
	
	/**
	 * Instantiates a new ImageInventory
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public ImageInventory() {
		this.images = new ArrayList<Image>();
	}
	
	/**
	 * Adds the image.
	 * 
	 * @precondition picture != null
	 * @postcondition size() == size()@prev + 1
	 * @param picture the picture
	 */
	public void addImage(Image picture) {
		if (picture == null) {
			throw new IllegalArgumentException("The picture cannot be null");
		}
		
		this.images.add(picture);
	}
	
	/**
	 * Removes the image.
	 *
	 * @precondition picture != null
	 * @postcondition size()
	 * @param picture the picture
	 */
	public void removeImage(Image picture) {
		this.images.remove(picture);
	}
	
	/**
	 * Gets the size of the images
	 * 
	 * @return the size of the images
	 */
	public int size() {
		return this.images.size();
	}

}

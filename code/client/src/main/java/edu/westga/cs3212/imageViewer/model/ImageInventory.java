package edu.westga.cs3212.imageViewer.model;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.image.Image;

/**
 * The ImageInventory class.
 * 
 * @author Oumar Diallo & Janera Smith
 * @version Spring 2022
 *
 */
public class ImageInventory {

	private ArrayList<Picture> images;

	/**
	 * Instantiates a new ImageInventory
	 * 
	 * @precondition none
	 * @postcondition size() == 0
	 */
	public ImageInventory() {
		this.images = new ArrayList<Picture>();
	}

	/**
	 * Adds the image.
	 * 
	 * @precondition picture != null
	 * @postcondition size() == size()@prev + 1
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */

	public boolean addImage(Picture picture) {
		if (picture == null) {
			throw new IllegalArgumentException("The picture cannot be null");
		}
		return this.images.add(picture);

	}

	public Picture getImage(Image image) {
		Picture picture = null;

		for (Picture currpicture : images) {
			if (currpicture.getPic() == image) {
				picture = currpicture;
			}
		}
		return picture;
	}

	/**
	 * Removes the image.
	 *
	 * @precondition picture != null
	 * @postcondition size()
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */
	public boolean removeImage(Picture picture) {
		if (picture == null) {
			throw new IllegalArgumentException("The picture cannot be null");
		}
		return this.images.remove(picture);
	}

	/**
	 * Removes the image.
	 *
	 * @precondition picture != null
	 * @postcondition size() -1 
	 * @param picture the picture
	 * 
	 * @return T/F If image was removed
	 */
	public boolean removeImage(Image image) {
		if (image == null) {
			throw new IllegalArgumentException("The picture cannot be null");
		}

		for (Picture currpicture : images) {
			if(currpicture.getPic() == image) {
				return this.images.remove(currpicture);
			}
		}
		return false;
	}

	/**
	 * Gets the pictures.
	 *
	 * @return the pictures
	 */

	public ArrayList<Image> getPictures() {
		ArrayList<Image> allImages = new ArrayList<Image>();

		for (Picture img : this.images) {
			allImages.add(img.getPic());
		}

		return allImages;
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

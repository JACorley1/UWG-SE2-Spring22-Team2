package edu.westga.cs3212.imageViewer.model;

import javafx.scene.image.Image;

/**
 * The Image class
 * 
 * @author Janera Smith & Oumar Diallo
 * @version Spring 2022
 */
public class Picture {
	private String title;
	public static int  imageId = 0;
	private Image pic;

	/**
	 * Instantiates the Image with the specified title and image id
	 * 
	 * @precondition title != null && imageId > 0
	 * @postcondition getTitle() == title && getImageId() == imageId
	 * 
	 * @param title   the title of the image
	 * @param imageId the image id
	 */
	public Picture(Image pic,String title) {
		if (title == null) {
			throw new IllegalArgumentException("The title cannot be null");
		}

		this.title = title;
		this.setPic(pic);
	}

	/**
	 * Gets the pic.
	 *
	 * @return the pic
	 */
	public Image getPic() {
		return this.pic;
	}

	/**
	 * Sets the pic.
	 *
	 * @param pic the new pic
	 */
	public void setPic(Image pic) {
		if (pic == null) {
			throw new IllegalArgumentException("The pic cannot be null");
		}
		this.pic = pic;
	}

	/**
	 * Gets the title of the image
	 * 
	 * @precondition none
	 * 
	 * @return the title of the image
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Gets the image id
	 * 
	 * @precondition none
	 * 
	 * @return the image id
	 */
	public int getImageId() {
		return Picture.imageId;
	}
	
	/**
	 * Sets the Image id
	 * 
	 * @precondition imageId > 0
	 * @postcondition getImageId() == imageId
	 * 
	 * @param imageId the image id
	 */
	public void setImageId(int imageId) {
		if (imageId <= 0) {
			throw new IllegalArgumentException("The image id cannot be negative");
		}
		
		Picture.imageId = imageId;
	}

	/**
	 * Returns a String representation of the Image object
	 * 
	 * @returns String  a string representation of the Image object
	 */
	public String toString() {
		return this.title + ": " + Picture.imageId;
	}
}

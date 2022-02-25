package edu.westga.cs3212.imageViewer.model;

/**
 * The Image class
 * 
 * @author Janera Smith
 * @version Spring 2022
 */
public class Image {
	private String title;
	private int imageId;

	/**
	 * Instantiates the Image with the specified title and image id
	 * 
	 * @precondition title != null && imageId > 0
	 * @postcondition getTitle() == title && getImageId() == imageId
	 * 
	 * @param title   the title of the image
	 * @param imageId the image id
	 */
	public Image(String title, int imageId) {
		if (title == null) {
			throw new IllegalArgumentException("The title cannot be null");
		}
		if (imageId <= 0) {
			throw new IllegalArgumentException("The image id must be positive");
		}

		this.title = title;
		this.imageId = imageId;
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
		return this.imageId;
	}

	/**
	 * Returns a String representation of the Image object
	 * 
	 * @returns String  a string representation of the Image object
	 */
	public String toString() {
		return this.title + ": " + this.imageId;
	}
}

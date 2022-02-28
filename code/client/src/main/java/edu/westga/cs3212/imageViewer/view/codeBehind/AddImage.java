package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.control.Tooltip;

/**
 * The Class AddImage.
 */
public class AddImage {

	/** The image view. */
	@FXML
	private ImageView imageView;

	/** The image name text field. */
	@FXML
	private TextField imageNameTextField;

	/** The upload button. */
	@FXML
	private Button uploadButton;

	/** The clear button. */
	@FXML
	private Button clearButton;

	/** The cancel button. */
	@FXML
	private Button cancelButton;

	/** The private radio button. */
	@FXML
	private RadioButton privateRadioButton;

	/** The shareable radio button. */
	@FXML
	private RadioButton shareableRadioButton;

	/** The categories combo box. */
	@FXML
	private ComboBox<?> categoriesComboBox;

	/** The category label. */
	@FXML
	private Label categoryLabel;

	/** The manager. */
	private LoginManager manager;

	/**
	 * On clear clicked.
	 *
	 * @param event the event
	 */
	@FXML
	void onClearClicked(MouseEvent event) {
		this.clearFields();
	}

	/**
	 * On upload clicked add new image and go back to main page.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	void onUploadClicked(MouseEvent event) throws IOException {
		Image newImage = this.imageView.imageProperty().get();
		
		String imageName = this.imageNameTextField.textProperty().getValue();
		Picture.imageId += 1;
		Picture test = new Picture(newImage, imageName);
		
		LoginManager.loggedInUser.addImage(test);

		this.closeWindow();

	}

	/**
	 * Close window and go back to home page.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void closeWindow() throws IOException {
		Stage currentStage = (Stage) this.cancelButton.getScene().getWindow();
		currentStage.close();

		Parent parent = FXMLLoader.load(Main.class.getResource(Main.MAIN_PAGE));
		Scene scene = new Scene(parent);
		Stage maiPage = new Stage();
		maiPage.setScene(scene);
		maiPage.setTitle(Main.WINDOW_TITLE);
		maiPage.show();
	}

	/**
	 * On cancel clicked close the addImage page.
	 *
	 * @param event the event
	 */
	@FXML
	void onCancelClicked(MouseEvent event) {
		Stage addStage = (Stage) this.categoryLabel.getScene().getWindow();
		addStage.close();
	}

	/**
	 * On image view clicked, select new image from file explorer.
	 *
	 * @param event the event
	 */
	@FXML
	void onImageViewClicked(MouseEvent event) {

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"),
				new ExtensionFilter("All Files", "*.*"));

		Stage stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			
			this.setImage(selectedFile.getPath());
		}

	}

	/**
	 * Initializes the add Image page.
	 */
	@FXML
	public void initialize() {
		Tooltip.install(this.imageView, new Tooltip("Click here to upload a new image."));
		this.manager = new LoginManager();
		this.setImage("Assets/upload.jpg");
	}

	/**
	 * Clear fields.
	 */
	private void clearFields() {
		this.setImage("Assets/upload.jpg");
		this.imageNameTextField.setText("");
	}

	/**
	 * Sets the image.
	 *
	 * @param imagePath the new image
	 */
	private void setImage(String imagePath) {
		try {
			FileInputStream inputStream = new FileInputStream(imagePath);
			this.imageView.imageProperty().setValue(new Image(inputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
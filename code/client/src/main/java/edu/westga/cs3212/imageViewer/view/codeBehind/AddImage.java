package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.view.viewModel.ImageViewModel;
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


    @FXML
    private ImageView imageView;

    @FXML
    private TextField imageNameTextField;

    @FXML
    private Button uploadButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cancelButton;

    @FXML
    private RadioButton privateRadioButton;

    @FXML
    private RadioButton shareableRadioButton;

    @FXML
    private ComboBox<?> categoriesComboBox;

    @FXML
    private Label categoryLabel;

    private LoginManager manager;

    private String defaultImage;
    
    private ImageViewModel viewModel;
    
    public AddImage() {
    	this.viewModel = new ImageViewModel();
    }

    @FXML
    void onClearClicked(MouseEvent event) {
        this.clearFields();
    }

    @FXML
    void onUploadClicked(MouseEvent event) throws IOException {
//    	Image newImage = this.imageView.imageProperty().get();
//    	System.out.println(newImage.getUrl());
//    	String imageName = this.imageNameTextField.textProperty().getValue();
//    	Picture.imageId += 1;
//    	Picture test = new Picture(newImage, imageName);
//    	System.out.println(test);
//       LoginManager.loggedInUser.addImage(test);
    	this.viewModel.addPicture();
    	
    	this.closeWindow();
    	
    }

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

    @FXML
    void onCancelClicked(MouseEvent event) throws IOException {
        this.closeWindow();
    }

    
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
		this.bindToViewModel();
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
	
	private void bindToViewModel() {
		this.imageNameTextField.textProperty().bindBidirectional(this.viewModel.getTitleProperty());
		this.imageView.imageProperty().bindBidirectional(this.viewModel.getImageProperty());
	}
}
package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import edu.westga.cs3212.imageViewer.model.LoginManager;
import javafx.fxml.FXML;
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

    @FXML
    void onClearClicked(MouseEvent event) {
        this.clearFields();
    }

    @FXML
    void onUploadClicked(MouseEvent event) {
        // manager.getLoggedInUser().addImage(this.imageView.getImage());
    }

    @FXML
    void onCancelClicked(MouseEvent event) {
        Stage addStage = (Stage) this.categoryLabel.getScene().getWindow();
        addStage.close();
    }

    
    @FXML
    void onImageViewClicked(MouseEvent event) {
        System.out.println("IT WORKS!!");
        FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Image File");
		fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif", "*.bmp"),
				new ExtensionFilter("All Files", "*.*"));

		Stage stage = new Stage();
		File selectedFile = fileChooser.showOpenDialog(stage);
		if (selectedFile != null) {
			System.out.println("Selected file: " + selectedFile.getName() + " " + selectedFile.getPath());
			this.setImage(selectedFile.getPath());
		}
    }


    /**
    * Initializes the add Image page
    *
    */
    @FXML
    public void initialize() {      
        Tooltip.install(this.imageView, new Tooltip("Click here to upload a new image."));
        this.manager = new LoginManager();
        this.setImage("Assets/upload.jpg");
    }

    private void clearFields() {
        this.setImage("Assets/upload.jpg");
        this.imageNameTextField.setText("");
    }

    private void setImage(String imagePath) {
        try {
			FileInputStream inputStream = new FileInputStream(imagePath);
			this.imageView.imageProperty().setValue(new Image(inputStream));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
}
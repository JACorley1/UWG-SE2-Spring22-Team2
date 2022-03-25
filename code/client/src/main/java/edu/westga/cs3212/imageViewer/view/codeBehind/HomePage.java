package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.IOException;
import java.util.ArrayList;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.User;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * The Class HomePage.
 */
public class HomePage {

	/** The user images. */
	@FXML
    private Button logOutButton;

	@FXML
    private VBox userImages;


	@FXML
    private ListView<ImageView> imageListView;


    /** The add image button. */

    @FXML
    private Button addImageButton;
    
    @FXML
    private Button deleteImageButton;
    
    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
    	this.populateVBox();

    }

    /**
     * Populate the Vbox with user images.
     */
    private void populateVBox() {
    	
    	User currentUser = LoginManager.loggedInUser;
    	
    	ArrayList<ImageView> allImages = this.setUpImageViews(currentUser);
		
    	this.userImages.getChildren().addAll(allImages);
		ObservableList<ImageView> images = new SimpleListProperty<ImageView>(FXCollections.observableArrayList(allImages));
		this.imageListView.setItems(FXCollections.observableArrayList(allImages));
		// this.imageListView.itemsProperty().setValue(images);
 
	}

	/**
	 * Sets the up image views.
	 *
	 * @param currentUser the current user
	 * @return the array list
	 */
	private ArrayList<ImageView> setUpImageViews(User currentUser) {
		ArrayList<ImageView> allImages = new ArrayList<ImageView>();
		LoginManager login = new LoginManager();
		for (User currUser : login.getUsers()) {
			for(Image img: currUser.getImages().getPictures()) {
				ImageView someImage = new ImageView();
				someImage.imageProperty().setValue(img);
				someImage.fitWidthProperty().bind(this.userImages.widthProperty());
				someImage.setFitHeight(365);
				someImage.setPreserveRatio(true);
				allImages.add(someImage);
			}
		}
		
		return allImages;
	}

	/**
	 * On add image click.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
    private void onAddImageClick(ActionEvent event) throws IOException {
    	Stage loginStage = (Stage) this.addImageButton.getScene().getWindow();
    	
    	loginStage.close();
        Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_IMAGE));
		Scene scene = new Scene(parent);
        Stage maiPage = new Stage();
        maiPage.setScene(scene);
        maiPage.setTitle(Main.WINDOW_TITLE);
        maiPage.show();
    }

	@FXML
    void onLogOutClick(ActionEvent event) throws IOException {
		Stage currentStage = (Stage) this.addImageButton.getScene().getWindow();
    	currentStage.close();
		
		LoginManager.loggedInUser = null;

        Parent parent = FXMLLoader.load(Main.class.getResource(Main.LOGIN_PAGE));
		Scene scene = new Scene(parent);
        Stage loginPage = new Stage();
        loginPage.setScene(scene);
        loginPage.setTitle(Main.WINDOW_TITLE);
        loginPage.show();
    }
	
	@FXML
    void onDeleteImageClick(ActionEvent event) {

    }
}


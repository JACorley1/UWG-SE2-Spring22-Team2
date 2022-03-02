package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
public class HomePage {

	@FXML
    private Button logOutButton;

	@FXML
    private VBox userImages;

	@FXML
    private ListView<ImageView> imageListView;

    @FXML
    private Button addImageButton;
    
    @FXML
    public void initialize() {
    	this.populateVBox();
    }

    private void populateVBox() {
		// TODO Auto-generated method stub
    	
    	User currentUser = LoginManager.loggedInUser;
    	System.out.println("THIS IS THE CURRENT USER: " + currentUser.getUsername());
    	ArrayList<ImageView> allImages = this.setUpImageViews(currentUser);
    	System.out.println("THIS IS THE IMAGE VIEW SIZE: " + allImages.size());
    	
    	if(allImages.size() > 0) {
    		System.out.println(allImages.get(0).imageProperty().get().getUrl());
    	}
		
    	this.userImages.getChildren().addAll(allImages);
		ObservableList<ImageView> images = new SimpleListProperty<ImageView>(FXCollections.observableArrayList(allImages));
		this.imageListView.setItems(FXCollections.observableArrayList(allImages));
		// this.imageListView.itemsProperty().setValue(images);
 
	}

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

	@FXML
    void onAddImageClick(ActionEvent event) throws IOException {
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
}


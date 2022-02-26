package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.IOException;
import java.util.ArrayList;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.User;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class HomePage {

	@FXML
    private VBox userImages;

    @FXML
    private Button addImageButton;
    
    @FXML
    public void initialize() {
    	this.userImages = new VBox();
    	this.populateVBox();
    	
    }

    private void populateVBox() {
		// TODO Auto-generated method stub
    	//List of ImageViews
    	User currentUser = LoginManager.loggedInUser;
    	System.out.println("THIS IS THE CURRENT USER: " + currentUser.getUsername());
    	ArrayList<ImageView> allImages = this.setUpImageViews(currentUser);
    	System.out.println("THIS IS THE IMAGE VIEW SIZE: " + allImages.size());
    	
    	if(allImages.size() > 0) {
    		System.out.println(allImages.get(0).imageProperty().get().getUrl());
    	}
		
    	this.userImages.getChildren().addAll(allImages);
 
	}

	private ArrayList<ImageView> setUpImageViews(User currentUser) {
		ArrayList<ImageView> allImages = new ArrayList<ImageView>();
		for(Image img: currentUser.getImages().getPictures()) {
    		ImageView someImage = new ImageView();
    		someImage.imageProperty().setValue(img);
    		
    		allImages.add(someImage);
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
}


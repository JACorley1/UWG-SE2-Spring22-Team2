package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.ServerCommunitcator;
import edu.westga.cs3212.imageViewer.model.User;
import edu.westga.cs3212.imageViewer.view.viewModel.ImageViewModel;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

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
	
	@FXML
    private Button shareButton;

	@FXML
	private Label errorLabel;

	private ImageViewModel viewModel;

	public HomePage() {
		this.viewModel = new ImageViewModel();
	}

	/**
	 * Initialize.
	 */
	@FXML
	public void initialize() {
		this.populateVBox();
		// this.bindToViewModel();
	}
	
    @FXML
    void onHomeTabClicked(ActionEvent event) {

    }
    
    @FXML
    void onPublicPhotosTabClicked(ActionEvent event) {

    }
    
    @FXML
    void onShareImageClicked(ActionEvent event) {

    }

	/**
	 * Populate the Vbox with user images.
	 */
	private void populateVBox() {

		ArrayList<ImageView> allImages = this.setUpImageViews();

		// this.userImages.getChildren().addAll(allImages);
		// ObservableList<ImageView> images = new
		// SimpleListProperty<ImageView>(FXCollections.observableArrayList(allImages));
		this.imageListView.setItems(FXCollections.observableArrayList(allImages));

	}

	private void bindToViewModel() {
		// this.imageListView.itemsProperty().bind((ObservableValue<? extends
		// ObservableList<ImageView>>) this.viewModel.getPictureListProperty());
		// this.imageListView.itemsProperty().bind((ObservableValue<? extends
		// ObservableList<ImageView>>) this.viewModel.getPictureListProperty());
		// this.viewModel.getSelectedPictureProperty().bind(this.imageListView.getSelectionModel().selectedItemProperty());
	}

	/**
	 * Sets the up image views.
	 *
	 * @param currentUser the current user
	 * @return the array list
	 * 
	 */
	private ArrayList<ImageView> setUpImageViews() {
		ArrayList<ImageView> allImages = new ArrayList<ImageView>();
		JSONArray jsonArray = this.serverSideGetImages();
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject imageInJSON = new JSONObject(jsonArray.getString(i));
			String imageName = imageInJSON.getString("name");
			String imageBytes = imageInJSON.getString("imageBytes");
			int imageId = imageInJSON.getInt("imageId");
			System.out.println(imageId);

			if (imageBytes != null) {
				byte[] decodedImage = Base64.getDecoder().decode(imageBytes);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(decodedImage);

				Picture img = new Picture(byteStream, imageName, imageId);
				ImageView someImage = new ImageView();
				someImage.imageProperty().setValue(img);
				System.out.println(img.imageId + " Actual");
				System.out.println(((Picture) someImage.imageProperty().getValue()).imageId);
				someImage.fitWidthProperty().bind(this.userImages.widthProperty());
				someImage.setFitHeight(365);
				someImage.setPreserveRatio(true);
				allImages.add(someImage);
			}

		}

		return allImages;
	}

	private JSONArray serverSideGetImages() {
		System.out.println("Connecting to hello world server");

		String getImagesRequest = "{\"requestType\" : \"getImages\"}";
		System.out.println("Client - Sending Get Images Request");
		JSONObject checker = ServerCommunitcator.sendMessage(getImagesRequest);
		System.out.println("Successful request send.");

		int success = checker.getInt("successCode");

		if (success == 1) {
			System.out.println("Images Successfully obtained");
			JSONArray serverImages = new JSONArray(checker.getString("images"));
			return serverImages;
		} else {
			System.out.println("Image failed to be obtained");
			return null;
		}

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
		if (this.imageListView.getItems().isEmpty()) {
			this.setErrorLabel("There are no images!");
		} else {
			int imageId = ((Picture) this.imageListView.getSelectionModel().selectedItemProperty().get()
					.getImage()).imageId;
			this.viewModel.deletePicture(imageId);
			this.populateVBox();
		}
	}

	/**
	 * Sets the error label.
	 *
	 * @param text the new error text
	 */
	private void setErrorLabel(String text) {
		this.errorLabel.setText(text);
		this.errorLabel.disableProperty().setValue(false);
		this.errorLabel.setVisible(true);
	}
}

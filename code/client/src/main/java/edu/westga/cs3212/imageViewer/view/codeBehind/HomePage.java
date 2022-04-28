package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.JOptionPane;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.Picture;
import edu.westga.cs3212.imageViewer.model.ServerCommunitcator;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * The Class HomePage.
 */
public class HomePage {

	@FXML
	private TabPane pagesTabPane;

	@FXML
	private Tab MyPhotosTab;

	@FXML
	private Button logOutButton;

	@FXML
	private ListView<ImageView> myPhotosListView;

	@FXML
	private ListView<ImageView> publicListView;

	@FXML
	private ListView<ImageView> sharedListView;

	@FXML
	private Button addImageButton;

	@FXML
	private Button deleteImageButton;

	@FXML
	private Tab publicTab;

	@FXML
	private Button shareButton;

	@FXML
	private Tab sharedPhotosTab;

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
		
		this.onPublicPhotosTab();
		this.onSharedPhotosTab();
		this.onMyPhotosTab();
		this.setErrorLabel("");
	}

	@FXML
	void onMyPhotosTab() {
		this.pagesTabPane.getSelectionModel().select(this.MyPhotosTab);
		this.populateImages(1, this.myPhotosListView);
	}

	@FXML
	void onPublicPhotosTab() {
		this.pagesTabPane.getSelectionModel().select(this.publicTab);
		this.populateImages(0, this.publicListView);
	}

	@FXML
	void onSharedPhotosTab() {
		this.pagesTabPane.getSelectionModel().select(this.sharedPhotosTab);
		this.populateImages(2, this.sharedListView);
	}

	@FXML
	void onShareImageClicked(ActionEvent event) {
		int imageId = 0;
		if (myPhotosListView.getItems().isEmpty()) {
			this.setErrorLabel("There are no images!");
		} else {
			imageId = ((Picture) myPhotosListView.getSelectionModel().selectedItemProperty().get()
					.getImage()).imageId;
		}
		//Create a dialog to get a username// also have to verify if username is in the list of users on server
		String userToBeSharedTo = JOptionPane.showInputDialog("Enter the username of the user you would like to share to.");
		this.handleShareImage(imageId, userToBeSharedTo);
		this.initialize();
	}


	/**
	 * Populate the Vbox with user images.
	 * 
	 * @param visbility The Visibility to display 0 = Public, 1 = Private, 2= Shared
	 */
	private void populateImages(int visibility, ListView<ImageView> listView) {

		ArrayList<ImageView> allImages = this.setUpImageViews(visibility);
		listView.setItems(FXCollections.observableArrayList(allImages));

	}

	/**
	 * Places all images in the system to be displayed by placing them in Image
	 * Views.
	 *
	 * 
	 * @return An ArrayList of viewable images in the Application
	 */
	private ArrayList<ImageView> setUpImageViews(int visibility) {
		ArrayList<ImageView> allImages = new ArrayList<ImageView>();
		JSONArray jsonArray = this.serverSideGetImages(visibility);
		for (int i = 0; i < jsonArray.length(); i++) {
			JSONObject imageInJSON = new JSONObject(jsonArray.getString(i));
			String imageName = imageInJSON.getString("name");
			String imageBytes = imageInJSON.getString("imageBytes");
			int imageId = imageInJSON.getInt("imageId");
			System.out.println(imageId);

			if (imageBytes != null) {
				allImages.add(buildImage(allImages, imageName, imageBytes, imageId));
			}
		}
		return allImages;
	}

	private ImageView buildImage(ArrayList<ImageView> allImages, String imageName, String imageBytes, int imageId) {
		byte[] decodedImage = Base64.getDecoder().decode(imageBytes);
		ByteArrayInputStream byteStream = new ByteArrayInputStream(decodedImage);

		Picture img = new Picture(byteStream, imageName, imageId);
		ImageView someImage = new ImageView();
		someImage.imageProperty().setValue(img);
		System.out.println(img.imageId + " Actual");
		System.out.println(((Picture) someImage.imageProperty().getValue()).imageId);
		// someImage.fitWidthProperty().bind(this.pagesTabPane.widthProperty());
		someImage.setFitHeight(365);
		someImage.setPreserveRatio(true);

		return someImage;

	}

	private JSONArray serverSideGetImages(int visibility) {
		System.out.println("Connecting to hello world server");
		String getImagesRequest = "";
		switch (visibility) {
			case 0:
				getImagesRequest = "{\"requestType\" : \"getPublicImages\"}";
				break;
			case 1:
				getImagesRequest = "{\"requestType\" : \"getMyImages\"}";
				break;
			case 2:
				getImagesRequest = "{\"requestType\" : \"getMySharedImages\"}";
				break;
			default:
				break;
		}
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

	private void handleShareImage(int imageId, String username) {
		System.out.println("Connecting to hello world server");

		String shareImageRequest = "{\"requestType\" : \"shareImage\", \"imageId\" : \"" + imageId
				+ "\", \"username\" : \"" + username + "\"}";
		System.out.println("Client - Sending share Image Request");
		JSONObject checker = ServerCommunitcator.sendMessage(shareImageRequest);
		System.out.println("Successful request send.");

		int success = checker.getInt("successCode");

		if (success == 1) {
			System.out.println("Images Successfully shared");
		} else if(success == -1) {
			this.setErrorLabel("No such user exists in the system");
			System.out.println("Image failed to share image ");
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
		if (myPhotosListView.getItems().isEmpty()) {
			this.setErrorLabel("There are no images!");
		} else {
			int imageId = ((Picture) myPhotosListView.getSelectionModel().selectedItemProperty().get()
					.getImage()).imageId;
			this.viewModel.deletePicture(imageId);
			this.initialize();
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

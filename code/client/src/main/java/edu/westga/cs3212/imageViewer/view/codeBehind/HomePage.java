package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
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
		ArrayList<ImageView> allImages = this.setUpImageViews();

		this.userImages.getChildren().addAll(allImages);
		ObservableList<ImageView> images = new SimpleListProperty<ImageView>(
				FXCollections.observableArrayList(allImages));
		this.imageListView.setItems(FXCollections.observableArrayList(allImages));

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
		LoginManager login = new LoginManager();

		for (Object imageInBytes : this.serverSideGetImages()) {
			String bytes = (String)imageInBytes;
			if(bytes != null) {
				byte[] decodedImage = Base64.getDecoder().decode(bytes);
				ByteArrayInputStream byteStream = new ByteArrayInputStream(decodedImage);

				Image img = new Image(byteStream);
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

	private List<Object> serverSideGetImages() {
		Context getImageContext = ZMQ.context(1);

		// Socket to talk to server
		System.out.println("Connecting to hello world server");

		try (@SuppressWarnings("deprecation")
		Socket getImagesocket = getImageContext.socket(ZMQ.REQ)) {
			getImagesocket.connect("tcp://127.0.0.1:5555");

			String getImagesRequest = "{\"requestType\" : \"getImages\"}";
			System.out.println("Client - Sending Get Images Request");
			getImagesocket.send(getImagesRequest.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Successful request send.");

			String help = getImagesocket.recvStr();

			JSONObject checker = new JSONObject(help);

			int success = checker.getInt("successCode");

			if (success == 1) {
				System.out.println("Images Successfully obtained");
				JSONArray filePaths = checker.getJSONArray("images");
				return filePaths.toList();
			} else {
				System.out.println("Image failed to be added");
				return null;
			}

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
}

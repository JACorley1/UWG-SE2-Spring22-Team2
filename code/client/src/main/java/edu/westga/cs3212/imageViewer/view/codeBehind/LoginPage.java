package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.IOException;

import edu.westga.cs3212.imageViewer.Main;
import edu.westga.cs3212.imageViewer.model.LoginManager;
import edu.westga.cs3212.imageViewer.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * The Class LoginPage.
 */
public class LoginPage {

	/** The username field. */
	@FXML
	private TextField usernameField;

	/** The password field. */
	@FXML
	private TextField passwordField;

	/** The error text. */
	@FXML
	private Text errorText;

	/** The login button. */
	@FXML
	private Button loginButton;

	/** The create account button. */
	@FXML
	private Button createAccountButton;

	/** The login manager. */
	private LoginManager loginManager;

	/**
	 * On create account clicked.
	 *
	 * @param event the event
	 */
	@FXML
	private void onCreateAccountClicked(MouseEvent event) {

		String password = this.passwordField.getText();
		String username = this.usernameField.getText();
		User user = new User(username, password);

		if (!this.loginManager.addUser(user)) {
			this.setErrorText(LoginManager.DUPLICATE_USERNAME);
		}
	}

	/**
	 * On login.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void onLogin(MouseEvent event) throws IOException {
		String password = this.passwordField.getText();
		String username = this.usernameField.getText();

		if (!this.loginManager.login(username, password)) {
			this.setErrorText(LoginManager.INCORRECT_LOGIN_INFORMATION);
		} else {
			this.setToMainPage();
		}

	}

	/**
	 * Sets the error text.
	 *
	 * @param text the new error text
	 */
	private void setErrorText(String text) {
		this.errorText.setText(text);
		this.errorText.disableProperty().setValue(false);
		this.errorText.setVisible(true);
		this.passwordField.setText("");
	}

	/**
	 * Sets page to main page.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void setToMainPage() throws IOException {
		Stage loginStage = (Stage) this.createAccountButton.getScene().getWindow();

		loginStage.close();
		Parent parent = FXMLLoader.load(Main.class.getResource(Main.MAIN_PAGE));
		Scene scene = new Scene(parent);
		Stage mainPage = new Stage();
		mainPage.setScene(scene);
		mainPage.setTitle(Main.WINDOW_TITLE);
		mainPage.show();

	}

	/**
	 * Initializes the login page.
	 */
	@FXML
	public void initialize() {
		this.errorText.disableProperty().setValue(true);
		this.errorText.setVisible(false);
		this.loginManager = new LoginManager();

	}

}
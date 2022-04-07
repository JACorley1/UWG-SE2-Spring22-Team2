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

import org.json.JSONObject;
import org.zeromq.ZMQ;
import org.zeromq.ZMQ.Context;
import org.zeromq.ZMQ.Socket;

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
	 * On login.
	 *
	 * @param event the event
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@FXML
	private void onLogin(MouseEvent event) throws IOException {

		this.serverSideLogin();
		
		
		

	}
	
	/**
	 * On create account clicked.
	 *
	 * @param event the event
	 * @throws IOException 
	 */
	@FXML
	private void onCreateAccountClicked(MouseEvent event) throws IOException {
		
		this.serverSideCreateAccount();
		
		
	}

	private void serverSideLogin() throws IOException {
		Context context = ZMQ.context(1);
		String user = this.usernameField.textProperty().get();
		String pass = this.passwordField.textProperty().get();

        //  Socket to talk to server
        System.out.println("Connecting to hello world server");

        try (@SuppressWarnings("deprecation")
		Socket socket = context.socket(ZMQ.REQ)) {
			socket.connect("tcp://127.0.0.1:5555");
			
			String request = "{\"requestType\" : \"login\", \"username\": \""+user+"\", \"password\":\""+pass+"\"}";
			System.out.println("Client - Sending Login Request");
			socket.send(request.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Successful request send.");
			
			String help = socket.recvStr();
			
			
			JSONObject checker = new JSONObject(help);
			
			int success = checker.getInt("successCode");
			
			if(success == 1) {
				System.out.println("Account Login Successfull" );
				this.loginManager.login(user, pass);
				this.setToMainPage();
			}
			else {
				System.out.println("Account Login Failed" );
				this.setErrorText(LoginManager.INCORRECT_LOGIN_INFORMATION);
			}
			System.out.println("the received string for server: " + help);
			
		}
	}
	
	private void serverSideCreateAccount() throws IOException {
		Context createAccountContext = ZMQ.context(1);
		String user = this.usernameField.textProperty().get();
		String pass = this.passwordField.textProperty().get();
		User createdAccount = new User(user, pass);

        //  Socket to talk to server
        System.out.println("Connecting to hello world server");

        try (@SuppressWarnings("deprecation")
		Socket createAccountsocket = createAccountContext.socket(ZMQ.REQ)) {
        	createAccountsocket.connect("tcp://127.0.0.1:5555");
			
			String createAccountRequest = "{\"requestType\" : \"createAccount\", \"username\": \""+user+"\", \"password\":\""+pass+"\"}";
			System.out.println("Client - Sending create Account Request");
			createAccountsocket.send(createAccountRequest.getBytes(ZMQ.CHARSET), 0);
			System.out.println("Successful request send.");
			
			String help = createAccountsocket.recvStr();
			
			
			JSONObject checker = new JSONObject(help);
			
			int success = checker.getInt("successCode");
			
			if(success == 1) {
				System.out.println("Account Creation Successfull" );
				this.loginManager.addUser(createdAccount);
			}
			else {
				System.out.println("Account Creation Failed" );
				this.setErrorText(LoginManager.DUPLICATE_USERNAME);
			}
			System.out.println("the received string for server: " + help);
			
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
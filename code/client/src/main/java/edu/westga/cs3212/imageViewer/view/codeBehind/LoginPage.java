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

public class LoginPage {

    @FXML
    private TextField usernameField;
    @FXML
    private TextField passwordField;

    @FXML
    private Text errorText;

    @FXML
    private Button loginButton;

    @FXML
    private Button createAccountButton;

    private LoginManager loginManager;

    @FXML
    private void OnCreateAccountClicked(MouseEvent event) {
      
    	String password = this.passwordField.getText();
        String username = this.usernameField.getText();
        User user = new User(username, password);
        
        if (!this.loginManager.addUser(user)) {
            this.setErrorText(LoginManager.Duplicate_Username);
        }
    }

    @FXML
    private void OnLogin(MouseEvent event) throws IOException {
        String password = this.passwordField.getText();
        String username = this.usernameField.getText();

        if (!this.loginManager.login(username, password)){
            this.setErrorText(LoginManager.INCORRECT_LOGIN_INFORMATION);
        } else {
            this.setToMainPage();
        }

    }

    private void setErrorText(String text) {
        this.errorText.setText(text);
        this.errorText.disableProperty().setValue(false);
        this.errorText.setVisible(true);
        this.passwordField.setText("");
    }

    public void setToMainPage() throws IOException{
    	Stage loginStage = (Stage)this.createAccountButton.getScene().getWindow();
    	
    	loginStage.close();
        Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_IMAGE));
		Scene scene = new Scene(parent);
        Stage mainPage = new Stage();
        mainPage.setScene(scene);
        mainPage.setTitle(Main.WINDOW_TITLE);
        mainPage.show();
    
    }

    /**
    * Initializes the login page
    *
    */
    @FXML
    public void initialize() {
        this.errorText.disableProperty().setValue(true);;
        this.errorText.setVisible(false);
        loginManager = new LoginManager();
        
    }

}
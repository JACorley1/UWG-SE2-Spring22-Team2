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
        //this.createUser(this.usernameField.Text, this.passwordField.Text);
    	String password = this.passwordField.getText();
        String username = this.usernameField.getText();
        
        User user = new User(username, password);
        
        this.loginManager.addUser(user);
    }

    @FXML
    private void OnLogin(MouseEvent event) throws IOException {
        String password = this.passwordField.getText();
        String username = this.usernameField.getText();

        if (!this.loginManager.login(username, password)){
            this.errorText.setText("Invalid user name or password");
            this.errorText.disableProperty().setValue(false);
            this.errorText.setVisible(true);
            this.passwordField.setText("");
        } else {
            this.setToMainPage();
        }

    }

    public void setToMainPage() throws IOException{
    	Stage loginStage = (Stage)this.createAccountButton.getScene().getWindow();
    	
    	loginStage.close();
        Parent parent = FXMLLoader.load(Main.class.getResource(Main.ADD_IMAGE));
		Scene scene = new Scene(parent);
        Stage mainPage = new Stage();
        mainPage.setScene(scene);
        mainPage.show();
        
        
    }

    public void initialize() {
        this.errorText.disableProperty().setValue(true);;
        this.errorText.setVisible(false);
        loginManager = new LoginManager();
        
    }

}
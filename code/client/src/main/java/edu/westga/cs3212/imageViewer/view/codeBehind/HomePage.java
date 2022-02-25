package edu.westga.cs3212.imageViewer.view.codeBehind;

import java.io.IOException;

import edu.westga.cs3212.imageViewer.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Modality;
import javafx.stage.Stage;
public class HomePage {

    @FXML
    private ListView<?> userImages;

    @FXML
    private Button addImageButton;

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


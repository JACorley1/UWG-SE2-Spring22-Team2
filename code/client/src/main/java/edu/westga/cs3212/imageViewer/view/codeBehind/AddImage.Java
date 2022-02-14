package edu.westga.cs3212.imageViewer.view.codeBehind;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.ImageView;

public class AddImage {

    @FXML
    private ImageView imageView;

    @FXML
    private Button uploadButton;

    @FXML
    private Button clearButton;

    @FXML
    private Button cancelButton;

    @FXML
    private RadioButton privateRadioButton;

    @FXML
    private RadioButton shareableRadioButton;

    @FXML
    private ComboBox<?> categoriesComboBox;

    @FXML
    private Label categoryLabel;

    @FXML
    void OnClearClicked(MouseEvent event) {
        //this.imageView.setValue = null;
        //this.categoriesComboBox.selectedItemProperty = null;
        //
    }

    @FXML
    void OnUploadClicked(MouseEvent event) {
        //TODO
    }

    @FXML
    void onCancelClicked(MouseEvent event) {
        //TODO
    }

    public void initialize() {

    }

}

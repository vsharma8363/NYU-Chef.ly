package Applet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;

public class SceneController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    // Create Tab
    @FXML
    private ChoiceBox<String> difficultyChoiceBox;

    private String[] difficultyChoices = { "easy", "medium", "hard" };

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // difficultyChoiceBox.getItems().addAll(difficultyChoices);
    }

    public void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBrowseScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BrowseScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreateScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        // difficultyChoiceBox.getItems().addAll(difficultyChoices);
    }

    public void switchToDiscoverScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DiscoverScene.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void hello(ActionEvent event) throws IOException {
        System.out.println("hello");
    }
    
}

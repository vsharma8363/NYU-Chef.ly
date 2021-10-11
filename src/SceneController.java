import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;


    public void switchToHomeScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToBrowseScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("BrowseScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToCreateScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("CreateScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // Button btn = new Button();
        // btn.setText("Recipe 1");

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToDiscoverScene(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("DiscoverScene.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void hello(ActionEvent event) throws IOException {
        System.out.println("hello");
    }
    
}

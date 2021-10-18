package Applet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import Recipes.Recipe;
import Recipes.RecipeManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.TextAlignment;

import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class SceneController implements Initializable {

    private RecipeManager recipeManager;

    @FXML
    private Pane viewPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        recipeManager = new RecipeManager("recipes");
    }

    public void switchToBrowseScene(ActionEvent event) throws IOException {
        dislayRecipeButtons(recipeManager.getRecipeList());
    }

    public void switchToCreateScene(ActionEvent event) throws IOException {
        viewPane.getChildren().clear();
        VBox vbox = new VBox(10.0);
        TextField searchBox = new TextField("Search for recipe by name.");
    }

    public void switchToDiscoverScene(ActionEvent event) throws IOException {
        viewPane.getChildren().clear();
        HBox hbox = new HBox(10.0);
        TextField searchBox = new TextField("Search for recipe by name.");
        Button searchButton = new Button("SEARCH");
        hbox.getChildren().addAll(searchBox, searchButton);
        viewPane.getChildren().addAll(hbox);
        searchButton.setOnAction(e->{
            if(recipeManager.getRecipeBySearch(searchBox.getText()).size() <= 0) {
                searchBox.setStyle("-fx-text-fill: red;");
                searchBox.setText("No Results Found!");
            }
            else{
                dislayRecipeButtons(recipeManager.getRecipeBySearch(searchBox.getText()));
            }
         });

    }

    private void dislayRecipeButtons(ArrayList<Recipe> recipes) {
        viewPane.getChildren().clear();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(20,20,20,20));
        gridPane.setHgap(20); //horizontal gap in pixels => that's what you are asking for
        gridPane.setVgap(20);
        int x = 0;
        int y = 0;
        for(Recipe recipe:recipes) {
            gridPane.add(generateRecipeButton(recipe), x, y);
            x++;
            if (x % 2 == 0) {
                x = 0;
                y++;
            }
        }
        viewPane.getChildren().add(gridPane);
    }
    
    private Button generateRecipeButton(Recipe recipe) {
        Button button;
        try {
            FileInputStream input = new FileInputStream("recipes/" + recipe.getImageURI());
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(100.0);
            imageView.setFitHeight(100.0);
            button = new Button(recipe.getName(), imageView);
        }
        catch(FileNotFoundException e) {
            button = new Button(recipe.getName());
        }
        button.setOnAction(e->{
            displayRecipe(recipe);
         });
        button.setPrefWidth(400.0);
        button.setPrefHeight(110.0);
        return button;
    }

    private void displayRecipe(Recipe recipe) {
        viewPane.getChildren().clear();
        Label recipeData = new Label(recipe.toString());
        recipeData.setFont(new Font(15.0));
        recipeData.setTextAlignment(TextAlignment.CENTER);
        viewPane.getChildren().add(recipeData);
    }

}

package Applet;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.ArrayList;

import Recipes.Recipe;
import Recipes.RecipeManager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.TextAlignment;

import javafx.fxml.Initializable;
import javafx.stage.FileChooser;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Node;
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
import javafx.geometry.Pos;


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
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        viewPane.getChildren().clear();
        VBox vbox = new VBox(10.0);

        TextField recipeName = new TextField("");
        recipeName.setPromptText("Recipe Name");
        recipeName.getStyleClass().add("title");
        recipeName.setAlignment(Pos.CENTER);

        TextField recipeAuthor = new TextField("");
        recipeAuthor.setPromptText("Author of the Recipe");
        recipeAuthor.getStyleClass().add("title");
        recipeAuthor.setAlignment(Pos.CENTER);

        TextField recipeDesc = new TextField("");
        recipeDesc.setPromptText("Describe your recipe here");

        TextField recipeDifficulty = new TextField("");
        recipeDifficulty.setPromptText("How difficult is your recipe? (Easy, Medium, Hard)");

        HBox imageBox = new HBox(10.0);
        FileChooser fileChooser = new FileChooser();
        Button selectImageButton = new Button("Select Recipe Image");
        Label imageLabel = new Label("");
        selectImageButton.setOnAction(e -> {
            String imagePath = fileChooser.showOpenDialog(stage).getAbsolutePath();
            imageLabel.setText(imagePath);
        });
        imageBox.getChildren().addAll(selectImageButton, imageLabel);

        VBox ingredientsBox = new VBox(10.0);
        ingredientsBox.setPadding(new Insets(20, 0, 10, 0));
        ArrayList<TextField> ingredientFields = new ArrayList<TextField>();
        Button ingredients = new Button("Add Ingredient");
        ingredientsBox.getChildren().add(ingredients);
        ingredients.setOnAction(e -> {
            TextField ingredientsField = new TextField("");
            ingredientsField.setPromptText("Add an ingredient here.");
            ingredientsBox.getChildren().add(ingredientsField);
            ingredientFields.add(ingredientsField);
        });

        VBox stepsBox = new VBox(10.0);
        stepsBox.setPadding(new Insets(20, 0, 10, 0));
        ArrayList<TextField> stepFields = new ArrayList<TextField>();
        Button steps = new Button("Add Step");
        stepsBox.getChildren().add(steps);
        steps.setOnAction(e -> {
            TextField stepsField = new TextField("");
            stepsField.setPromptText("Add step #" + (stepFields.size() + 1) + " here.");
            stepsBox.getChildren().add(stepsField);
            stepFields.add(stepsField);
        });

        Button doneButton = new Button("Submit");
        doneButton.setOnAction(e -> {
            Recipe newRecipe = new Recipe(recipeName.getText(), recipeDesc.getText(), recipeDifficulty.getText(), recipeAuthor.getText(), imageLabel.getText());

            for(TextField ingredientField:ingredientFields){
                newRecipe.addIngredient(ingredientField.getText());
            }
            int i = 1;
            for(TextField stepField:stepFields){
                newRecipe.addStep(i, stepField.getText());
                i++;
            }
            recipeManager.writeRecipesToDirectory(newRecipe, "recipes/" + Long.toString(System.currentTimeMillis()) + ".xml");
            viewPane.getChildren().clear();
        });

        vbox.setPadding(new Insets(80, 100, 20, 100));
        vbox.setPrefWidth(800);
        vbox.setAlignment(Pos.CENTER);;
        vbox.getChildren().addAll(recipeName, recipeAuthor, recipeDesc, recipeDifficulty, imageBox, ingredientsBox, stepsBox, doneButton);
        viewPane.getChildren().add(vbox);
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
            FileInputStream input = new FileInputStream(recipe.getImageURI());
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
        VBox recipeBox = new VBox(10);
        recipeBox.setAlignment(Pos.CENTER_LEFT);
        Label recipeData = new Label("\n" + recipe.toString() + "\n");
        recipeData.setFont(new Font(15.0));
        recipeData.setTextAlignment(TextAlignment.LEFT);
        try {
            FileInputStream input = new FileInputStream(recipe.getImageURI());
            Image image = new Image(input);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(512.0);
            imageView.setFitHeight(512.0);
            recipeBox.getChildren().add(imageView);
        }
        catch(FileNotFoundException e) {}
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setPrefWidth(viewPane.getWidth());
        scrollPane.setPrefHeight(viewPane.getHeight());
        recipeBox.getChildren().add(recipeData);
        scrollPane.setContent(recipeBox);
        scrollPane.setPadding(new Insets(20,20,20,20));
        viewPane.getChildren().add(scrollPane);
    }

}

package Applet;
//JavaFX Imports
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ChoiceBox;


import java.util.TreeMap;
import Recipes.Recipe;
import Recipes.RecipeManager;

public class AppletMain extends Application{
    //GUI start method
	@Override
    public void start(Stage primaryStage){
        try{
            Parent root = FXMLLoader.load(getClass().getResource("HomeScene.fxml"));
            ChoiceBox<String> difficultyChoiceBox = new ChoiceBox<String>();
            String[] difficultyChoices = { "easy", "medium", "hard" };
            difficultyChoiceBox.getItems().addAll(difficultyChoices);

            Scene scene = new Scene(root);
            primaryStage.setResizable(false);
            String css = this.getClass().getResource("application.css").toExternalForm();
            scene.getStylesheets().add(css);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) throws Exception{
        RecipeManager recipeManager = new RecipeManager("recipes");
		for(Recipe recipe:recipeManager.getRecipeList()) {
			System.out.println("\nName:\n" + recipe.getName());
			System.out.println("Description:\n" + recipe.getDescription());
			System.out.println("Author:\n" + recipe.getAuthor());
			System.out.println("Difficulty:\n" + recipe.getDifficulty());
			System.out.println("Recipe Image:\n" + recipe.getImageURI());
			TreeMap<Integer, String> recipeSteps = recipe.getSteps();
			for(int i = 0; i < recipeSteps.size(); i++) {
				System.out.println("Step " + (i + 1));
				System.out.println(recipeSteps.get(i+1));
			}
		}

		launch(args);
        
    }
}
    
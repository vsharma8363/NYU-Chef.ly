package Recipes;

import java.util.ArrayList;
import java.util.Random;

public class RecipeManager {

	
	public static ArrayList<Recipe> loadRecipesFromDirectory(String directory) {
		// TODO: Must be replaced with actual recipe data.
		// Read recipe data in some format from directory.
		return getFakeRecipeData();
	}
	
	public void writeRecipesToDirectory(Recipe recipe, String directory) {
		//Do something here to write the recipes.
	}
	
	// Returns a list of randomly generated fake recipes.
	private static ArrayList<Recipe> getFakeRecipeData() {
		ArrayList<Recipe> recipes = new ArrayList<Recipe>();
		int numFakeRecipes = 10;
		Random random = new Random();
		for(int i = 0; i < numFakeRecipes; i++) {
			Recipe r = new Recipe(
					"Fake Recipe #" + i, // Recipe Name.
					"test.png", // Recipe Image URI. 
					"This is where the description of the recipe will go"); // Recipe Description.
			for(int x = 0; x < random.nextInt(5) + 2; x++) {
				RecipeStep step = new RecipeStep("This is the instruciton set for step #" + x);
				r.addRecipeStep(x+1, step);
			}
		}
		
		return recipes;
	}
	
}

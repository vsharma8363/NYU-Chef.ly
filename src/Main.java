import java.util.ArrayList;

import Recipes.Recipe;
import Recipes.RecipeManager;

public class Main {
	
	public static void main(String[] args) {
		RecipeManager rm = new RecipeManager("Some directory");
		ArrayList<Recipe> recipes = rm.getRecipeList();
		for(Recipe r:recipes) {
			System.out.println(r.getRecipeName());
		}
	}

}

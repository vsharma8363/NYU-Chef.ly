import java.util.TreeMap;
import Recipes.Recipe;
import Recipes.RecipeManager;

public class Main {
	
	public static void main(String[] args) {
		RecipeManager recipeManager = new RecipeManager("recipes");
		for(Recipe recipe:recipeManager.getRecipeList()) {
			System.out.println("\nName:\n" + recipe.getRecipeName());
			System.out.println("Description:\n" + recipe.getRecipeDescription());
			System.out.println("Recipe Image:\n" + recipe.getRecipeImageURI());
			TreeMap<Integer, String> recipeSteps = recipe.getRecipeSteps();
			for(int i = 0; i < recipeSteps.size(); i++) {
				System.out.println("Step " + (i + 1));
				System.out.println(recipeSteps.get(i+1));
			}
		}
	}

}

import java.util.TreeMap;
import Recipes.Recipe;
import Recipes.RecipeManager;

public class Main {
	
	public static void main(String[] args) {
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
	}

}

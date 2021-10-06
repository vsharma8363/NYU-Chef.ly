import java.util.TreeMap;
import Recipes.Recipe;
import Recipes.RecipeManager;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to chef.ly!");
		RecipeManager recipeManager = new RecipeManager("recipes");
		while(true) { // Start permanent loop
			System.out.println("What would you like to do? (0 - Search a recipe, 1 - View all recipes, 2 - Add a recipe, 3 - Exit)");
			int choice = scan.nextInt();
			// Waiting for getRecipeBySearch to be implemented before testing
			if (choice == 0) {/*
				System.out.println("Please enter the name of the recipe");
				String recipename = scan.nextLine();
				ArrayList<Recipe> results = new ArrayList<Recipe>();
				results = getRecipeBySearch(recipename);
				for (int i = 0; i < results.size(); i++) {
					System.out.println("Recipe: " + i);
					Recipe recipe = results.get(i);
					recipe.printAll();
					}
				
				System.out.println("Please enter the number of the recipe you would like");
				int recipChoice = scan.nextInt();
				System.out.println("Here is your recipe");
				results.get(recipChoice).printAll();
			*/	
			}
			else if (choice == 1) {
				for(Recipe recipe:recipeManager.getRecipeList()) {
					recipe.printAll();
				}
			}
			else if (choice == 2) {
				System.out.println("Please enter the recipe name");
				scan.nextLine();
				String name = scan.nextLine();
				
				System.out.println("Please enter the recipe image URI");
				String URI = scan.nextLine();
				
				System.out.println("Please enter the recipe description");
				String description = scan.nextLine();
				Recipe newRecip = new Recipe(name,URI,description);
				System.out.println("How many steps is your recipe?");
				int x = scan.nextInt();
				scan.nextLine();
				String step;
				for (int i = 0; i < x; i++) {
					
					System.out.println("Please enter step: " + i);
					step = scan.nextLine();
					newRecip.addRecipeStep(i,step);
					
				}
				String outputpath = "recipes/" + Long.toString(System.currentTimeMillis()) + ".xml";
				recipeManager.writeRecipesToDirectory(newRecip, outputpath);
				
				System.out.println("Your recipe has been added!");
			}
			else if (choice == 3) {
				System.out.println("Thanks for visiting!");
				System.exit(0);
			}
			else {
				System.out.println("Error: " + choice + " is not an option");
				System.out.println("Returning to beginning");
			}
			
		}
		/* RecipeManager recipeManager = new RecipeManager("recipes");
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
		} */
	}
	

}

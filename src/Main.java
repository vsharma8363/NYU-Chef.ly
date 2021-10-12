import java.util.TreeMap;
import Recipes.Recipe;
import Recipes.RecipeManager;
import java.util.Scanner;
import java.util.ArrayList;

public class Main{
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Welcome to chef.ly!");
		RecipeManager recipeManager = new RecipeManager("recipes");
		while(true) { // Start permanent loop
			System.out.println("What would you like to do? (0 - Search a recipe, 1 - View all recipes, 2 - Add a recipe, 3 - Exit)");
			int choice = scan.nextInt();
			if (choice == 0) {
				System.out.println("Please enter the name of the recipe");
				String recipename = scan.nextLine();
				recipename = scan.nextLine();
				ArrayList<Recipe> results = new ArrayList<Recipe>();
				results = recipeManager.getRecipeBySearch(recipename);
				for (int i = 0; i < results.size(); i++) {
					System.out.println("Recipe: " + i);
					Recipe recipe = results.get(i);
					System.out.println(recipe.getName() + ", by " + recipe.getAuthor() + "\n");
				}
				System.out.println("Please enter the number of the recipe you would like");
				int recipChoice = scan.nextInt();
				System.out.println("Here is your recipe...");
				System.out.println(results.get(recipChoice).toString());
			}
			else if (choice == 1) {
				ArrayList<Recipe> recipes = recipeManager.getRecipeList();
				for (int i = 0; i < recipes.size(); i++) {
					System.out.println("Recipe: " + i);
					Recipe recipe = recipes.get(i);
					System.out.println(recipe.getName() + ", by " + recipe.getAuthor() + "\n");
				}
				System.out.println("Please enter the number of the recipe you would like");
				int recipChoice = scan.nextInt();
				System.out.println("\nHere is your recipe:\n");
				System.out.println(recipes.get(recipChoice));
			}
			else if (choice == 2) {
				System.out.println("What is the name of your new recipe?");
				scan.nextLine();
				String name = scan.nextLine();
				System.out.println("Please describe your recipe:");
				String description = scan.nextLine();
				System.out.println("How difficult is your recipe? (e.g. easy, medium, hard)");
				String difficulty = scan.nextLine();
				System.out.println("Who is the author of this recipe?");
				String author = scan.nextLine();
				System.out.println("Please enter a filepath to an image of your recipe: (type 'N/A' if no image will be used)");
				String URI = scan.nextLine();
				if(URI.equals("N/A")) {
					URI = "recipes/images/default.png";
				}
				Recipe newRecip = new Recipe(name, description, difficulty, author, URI);
				System.out.println("How many ingredients does your recipe have?");
				int numIngredients = scan.nextInt();
				scan.nextLine();
				for (int i = 0; i < numIngredients; i++) {
					
					System.out.println("Please enter an ingredient and quantity:");
					String ingredient = scan.nextLine();
					newRecip.addIngredient(ingredient);
					
				}
				System.out.println("How many steps is your recipe?");
				int x = scan.nextInt();
				scan.nextLine();
				String step;
				for (int i = 0; i < x; i++) {
					
					System.out.println("Please enter step: " + i);
					step = scan.nextLine();
					newRecip.addStep(i,step);
					
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

	}
	

}

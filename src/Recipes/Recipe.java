package Recipes;

import java.util.ArrayList;
import java.util.TreeMap;

public class Recipe {
	
	private String recipeName;
	private String recipeImageURI;
	private String recipeDescription;
	private String recipeDifficulty;
	private String recipeAuthor;
	private ArrayList<String> ingredients;
	private TreeMap<Integer, String> steps;
	
	public Recipe(String recipeName, String recipeDesc, String recipeDifficulty, String recipeAuthor, String recipeImage) {
		this.recipeName = recipeName;
		this.recipeImageURI = recipeImage;
		this.recipeDescription = recipeDesc;
		this.recipeDifficulty = recipeDifficulty;
		this.recipeAuthor = recipeAuthor;
		this.ingredients = new ArrayList<String>();
		this.steps = new TreeMap<Integer, String>();
	}

	public Recipe(String recipeName, String recipeDesc, String recipeDifficulty, String recipeAuthor) {
		this.recipeName = recipeName;
		this.recipeImageURI = "recipes/images/default.png";
		this.recipeDescription = recipeDesc;
		this.recipeDifficulty = recipeDifficulty;
		this.recipeAuthor = recipeAuthor;
		this.ingredients = new ArrayList<String>();
		this.steps = new TreeMap<Integer, String>();
	}

	public void addStep(int stepNumber, String step) {
		this.steps.put(stepNumber, step);
	}
	
	public void addIngredient(String ingredient) {
		this.ingredients.add(ingredient);
	}

	public void setImageURI(String imageURI) {
		this.recipeImageURI = imageURI;
	}
	
	public String getName() {
		return this.recipeName;
	}
	
	public String getAuthor() {
		return this.recipeAuthor;
	}
	
	public String getDifficulty() {
		return this.recipeDifficulty;
	}
	
	public String getImageURI() {
		return this.recipeImageURI;
	}
	
	public String getDescription() {
		return this.recipeDescription;
	}
	
	public ArrayList<String> getIngredients() {
		return this.ingredients;
	}
	
	public String toString() {
		int max_line_len = 80;
		String output = String.format("Name: %s\nAuthor: %s\nDifficulty: %s\n\n", this.recipeName, this.recipeAuthor, this.recipeDifficulty);
		output += (this.recipeDescription + "\n\nYou will need the following ingredients:\n");
		for(String ingredient:this.ingredients) {
			output += ingredient + "\n";
		}
		output += "\n";
		for(int i = 0; i < this.steps.size(); i++) {
			output += ("Step " + (i + 1) + ": ");
			for(int w = 0; w < this.steps.get(i+1).length(); w++) {
				if(w % max_line_len == 0) {
					output += "\n";
				}
				output += this.steps.get(i+1).charAt(w);
			}
			output += ("\n");
		}
		return output;
	}
	
	public TreeMap<Integer, String> getSteps() {
		return this.steps;
	}
}


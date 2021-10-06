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

	public void addStep(int stepNumber, String step) {
		this.steps.put(stepNumber, step);
	}
	
	public void addIngredient(String ingredient) {
		this.ingredients.add(ingredient);
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
	
	public TreeMap<Integer, String> getSteps() {
		return this.steps;
	}
}


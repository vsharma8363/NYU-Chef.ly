package Recipes;

import java.util.TreeMap;

public class Recipe {
	
	private String recipeName;
	private String recipeImageURI;
	private String recipeDescription;
	private TreeMap<Integer, String> steps;
	
	public Recipe(String recipeName, String recipeImageURI, String recipeDescription) {
		this.recipeName = recipeName;
		this.recipeImageURI = recipeImageURI;
		this.recipeDescription = recipeDescription;
		this.steps = new TreeMap<Integer, String>();
	}
	
	public void addRecipeStep(int stepNumber, String step) {
		this.steps.put(stepNumber, step);
	}
	
	public String getRecipeName() {
		return this.recipeName;
	}
	
	public String getRecipeImageURI() {
		return this.recipeImageURI;
	}
	
	public String getRecipeDescription() {
		return this.recipeDescription;
	}
	
	public TreeMap<Integer, String> getRecipeSteps() {
		return this.steps;
	}
	
	public void printAll() {
		System.out.println("\nName:\n" + this.recipeName);
		System.out.println("Description:\n" + this.recipeDescription);
		System.out.println("Recipe Image:\n" + this.recipeImageURI);
		for(int i = 0; i < this.steps.size(); i++) {
			System.out.println("Step " + (i + 1));
			System.out.println(this.steps.get(i+1));
		}
	}
	

}


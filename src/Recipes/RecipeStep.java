package Recipes;


public class RecipeStep {

	private String instructions;
	private String stepImageURI;
	
	public RecipeStep(String instructions, String stepImageURI)
	{
		this.instructions = instructions;
		this.stepImageURI = stepImageURI;
	}
	
	public RecipeStep(String instructions)
	{
		this.instructions = instructions;
		this.stepImageURI = null;
	}
	
	public boolean stepHasImage() {
		return !(this.stepImageURI == null);
	}
	
	public String getStepImageURI() {
		return this.stepImageURI;
	}
	
	public String getInstructions() {
		return this.instructions;
	}
	
}

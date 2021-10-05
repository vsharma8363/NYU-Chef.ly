package Recipes;

import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class RecipeManager {
	
	ArrayList<Recipe> recipeList;
	String directory;
	
	public RecipeManager(String directory) {
		this.recipeList = new ArrayList<Recipe>();
		this.directory = directory;
		this.refreshRecipeList();
	}
	
	public ArrayList<Recipe> getRecipeList() {
		return recipeList;
	}
	
	public void writeRecipesToDirectory(Recipe recipe, String filepath) {
		DocumentBuilder documentBuilder;
		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
			documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();
	        //Define root element of document as 'recipe'.
			Element root = document.createElement("recipe");
	        document.appendChild(root);
	        // Add recipe name to XML file.
	        Element recipeName = document.createElement("name");
	        recipeName.appendChild(document.createTextNode(recipe.getRecipeName()));
            root.appendChild(recipeName);
            // Add recipe description to XML file.
	        Element recipeDesc = document.createElement("description");
	        recipeDesc.appendChild(document.createTextNode(recipe.getRecipeDescription()));
            root.appendChild(recipeDesc);
            // Add recipe URI or filepath to XML file.
	        Element recipeImageURI = document.createElement("image_filepath");
	        recipeImageURI.appendChild(document.createTextNode(recipe.getRecipeImageURI()));
            root.appendChild(recipeImageURI);
            // Add all recipe steps to XML file.
            Element recipeSteps = document.createElement("steps");
            root.appendChild(recipeSteps);
            for(String instruction:recipe.getRecipeSteps().values()) {
            	Element recipeStep = document.createElement("step");
            	recipeStep.appendChild(document.createTextNode(instruction));
            	recipeSteps.appendChild(recipeStep);
            }
            // Write XML output to file.
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File(filepath));
            StreamResult result = new StreamResult(writer);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
            transformer.transform(source, result);
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		refreshRecipeList();
	}
	
	//TODO: public Recipe getRecipeBySearch(String searchKey) {}
	
	private void refreshRecipeList() {
		this.recipeList.clear();
		File dir = new File(this.directory);
		File [] files = dir.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.toString().endsWith(".xml");
			}
		});
		for (File xmlfile : files) {
			Recipe recipe;
		    DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		    DocumentBuilder db;
			try {
				db = dbf.newDocumentBuilder();
				Document document = db.parse(xmlfile);
				document.getDocumentElement().normalize();
				Element element = document.getDocumentElement();
				String recipeName = element.getElementsByTagName("name").item(0).getTextContent();
				String recipeDesc = element.getElementsByTagName("description").item(0).getTextContent();
				String recipeImage = element.getElementsByTagName("image_filepath").item(0).getTextContent();
				recipe = new Recipe(recipeName, recipeImage, recipeDesc);
				NodeList recipeSteps = element.getElementsByTagName("step");
				for(int i = 0; i < recipeSteps.getLength(); i++) {
					recipe.addRecipeStep(i + 1, recipeSteps.item(i).getTextContent());
				}
				this.recipeList.add(recipe);
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

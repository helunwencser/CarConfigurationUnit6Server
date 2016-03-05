package org.cmu.edu.adapter;

import org.cmu.edu.model.OptionSet;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
public interface UpdateAuto {
	
	/*
	 * set the name of model
	 * 
	 * @param	modelName	
	 * 			old model name
	 * 
	 * @param	newName	
	 * 			new name of model
	 * */
	public void updateModelName(String modelName, String newName);
	
	/*
	 * update model's base price
	 * 
	 * @param	modelName
	 * 			the name of model
	 * 
	 * @param	change
	 * 			the change in base price
	 * */
	public void updateBasePrice(String modelName, int change);
	
	/*
	 * update model's make
	 * 
	 * @param	modelName
	 * 			the name of model
	 * 
	 * @param	make
	 * 			new make
	 * */
	public void updateMake(String modelName, String make);
	
	/*
	 * delete specified option set from model
	 * 
	 * @param	modelName
	 * 			the name of model
	 * 
	 * @param	optionSetName
	 * 			the name of option set
	 * */
	public void deleteOptionSet(String modelName, String optionSetName);
	
	/*
	 * add option set into model
	 * 
	 * @param	modelName
	 * 			the name of model
	 * 
	 * @param	optionSet
	 * 			option set
	 * */
	public void addOptionSet(String modelName, OptionSet optionSet);
	
	/*
	 * update the option set choice
	 * 
	 * @param	modelName	
	 * 			the name of model
	 * 
	 * @param	optionSetName
	 * 			the name of option set
	 * 
	 * @param	optionSetChoice
	 * 			the new choice of option set
	 * */
	public void updateOptionSetChoice(String modelName, String optionSetName, String optionSetChoice);
	
	/*
	 * This function searches the Model for a given 
	 * OptionSet and sets the name of OptionSet to newName.
	 * 
	 * @param	modelName	
	 * 			model name
	 * 
	 * @param	optionSetName	
	 * 			option set name
	 * 
	 * @param	newName	
	 * 			the new name of optionSet
	 * */
	public void updateOptionSetName(String modelName, String optionSetName, String newName);
	
	/*
	 * This function searches the Model for a given OptionSet 
	 * and Option name, and sets the price to newPrice.
	 * @param	modelName	
	 * 			model name
	 * 
	 * @param	optionSetName	
	 * 			option set name
	 * 
	 * @param	option	
	 * 			option name
	 * 
	 * @param	newPrice	
	 * 			new price of option 
	 * */
	public void updateOptionPrice(String modelName, String optionSetName, String option, int change);
}

package org.cmu.edu.adapter;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */

public interface CreateAuto {
	
	/*
	 * Given a text file name this method can be 
	 * written to build an instance of Automobile
	 * @param	fileName
	 * 			file path
	 * 
	 * @param	fileType
	 * 			the type of file, possible values are: txt, properties
	 * */
	public void buildAuto(String fileName, String fileType);
	
	/*
	 * delete automobile
	 * @param	autoName
	 * 			the name of automobile
	 * */
	public void deleteAuto(String autoName);
	
	/*
	 * This function searches and prints the 
	 * properties of a given Automobile.
	 * */
	public void printAuto(String modelName);
}

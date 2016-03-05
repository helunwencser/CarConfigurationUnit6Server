package org.cmu.edu.adapter;


/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.cmu.edu.model.Automobile;
import org.cmu.edu.model.AutomobileCollection;
import org.cmu.edu.model.OptionSet;
import org.cmu.edu.util.Util;

/*
 * This class will contain all the implementation 
 * of any method declared in the interface
 * */
public abstract class ProxyAutomobile {
	/*
	 * collection contains all automobiles
	 * */
	private static AutomobileCollection automobiles;
	
	public ProxyAutomobile(){
		ProxyAutomobile.automobiles = new AutomobileCollection();
	}
	
	/*
	 * add automobile to automobile collection
	 * @param	automobile
	 * 			the automobile to be added
	 * */
	public void addAutomobile(Automobile automobile){
		this.automobiles.addAutomobile(automobile.getName(), automobile);
	}
	
	public HashSet<String> getModelNames(){
		return ProxyAutomobile.automobiles.getModelNames();
	}
	
	public void updateOptionSetName(String modelName, String optionSetName,
			String newName) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).updateOptionSetName(optionSetName, newName);
	}

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, int change) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).updateOptionPrice(optionSetName, optionName, change);
	}

	public void buildAuto(String fileName, String fileType) {
		// TODO Auto-generated method stub
		Util util = new Util(fileName);
		if(fileType.equals("txt")){
			Automobile automobile = util.buildAutoObject();
			ProxyAutomobile.automobiles.addAutomobile(automobile.getName(), automobile);
		}else{
			this.buildAutoFromProperties(util.getPropertiesObject());
		}
	}

	public void printAuto(String modelName) {
		// TODO Auto-generated method stub
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		System.out.println(automobile.print());
	}
	
	public Automobile getAutomobile(String modelName){
		return ProxyAutomobile.automobiles.getAutomobile(modelName);
	}
	
	public void updateModelName(String modelName, String newName) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).setName(newName);
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		ProxyAutomobile.automobiles.deleteAutomobile(modelName);
		ProxyAutomobile.automobiles.addAutomobile(newName, automobile);
	}

	public void updateBasePrice(String modelName, int change) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).setBasePrice(change);
	}

	public void updateMake(String modelName, String make) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).setMake(make);
	}

	public void deleteOptionSet(String modelName, String optionSetName) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).deleteOptionSet(optionSetName);
	}
	
	public void addOptionSet(String modelName, OptionSet optionSet) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).addOptionSet(optionSet);
	}

	public void updateOptionSetChoice(String modelName, String optionSetName,
			String optionSetChoice) {
		// TODO Auto-generated method stub
		ProxyAutomobile.automobiles.getAutomobile(modelName).setOptionChoice(optionSetName, optionSetChoice);
	}
	
	/*
	 * create Automobile and add it into AutomobileCollection
	 * @param	properties
	 * 			the properties object
	 * */
	public void buildAutoFromProperties(Properties properties){
		if(properties == null){
			return;
		}
		String model = properties.getProperty("model");
		String make = properties.getProperty("make");
		String baseprice = properties.getProperty("baseprice");
		Automobile automobile = new Automobile(model, Integer.parseInt(baseprice), make);
		
		Set<Object> keys = properties.keySet();
		
		/* parse option set 1 */
		String optionSet1 = properties.getProperty("option1");
		Map<String, Integer> options1 = new HashMap<String, Integer>();
		for(Object key : keys){
			String optionName = (String) key;
			if(optionName.startsWith("option1_")){
				options1.put(optionName.substring(optionName.indexOf("_") + 1), 
						Integer.parseInt(properties.getProperty(optionName)));
			}
		}
		automobile.addOptionSet(optionSet1, options1);

		/* parse option set 2 */
		String optionSet2 = properties.getProperty("option2");
		Map<String, Integer> options2 = new HashMap<String, Integer>();
		for(Object key : keys){
			String optionName = (String) key;
			if(optionName.startsWith("option2_")){
				options2.put(optionName.substring(optionName.indexOf("_") + 1), 
						Integer.parseInt(properties.getProperty(optionName)));
			}
		}
		automobile.addOptionSet(optionSet2, options2);
		
		/* parse option set 3 */
		String optionSet3 = properties.getProperty("option3");
		Map<String, Integer> options3 = new HashMap<String, Integer>();
		for(Object key : keys){
			String optionName = (String) key;
			if(optionName.startsWith("option3_")){
				options3.put(optionName.substring(optionName.indexOf("_") + 1), 
						Integer.parseInt(properties.getProperty(optionName)));
			}
		}
		automobile.addOptionSet(optionSet3, options3);
		
		/* parse option set 4 */
		String optionSet4 = properties.getProperty("option4");
		Map<String, Integer> options4 = new HashMap<String, Integer>();
		for(Object key : keys){
			String optionName = (String) key;
			if(optionName.startsWith("option4_")){
				options4.put(optionName.substring(optionName.indexOf("_") + 1), 
						Integer.parseInt(properties.getProperty(optionName)));
			}
		}
		automobile.addOptionSet(optionSet4, options4);
		
		/* parse option set 5 */
		String optionSet5 = properties.getProperty("option5");
		Map<String, Integer> options5 = new HashMap<String, Integer>();
		for(Object key : keys){
			String optionName = (String) key;
			if(optionName.startsWith("option5_")){
				options5.put(optionName.substring(optionName.indexOf("_") + 1), 
						Integer.parseInt(properties.getProperty(optionName)));
			}
		}
		automobile.addOptionSet(optionSet5, options5);
		this.automobiles.addAutomobile(model, automobile);
	}
}

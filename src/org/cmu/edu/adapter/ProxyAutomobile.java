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

import org.cmu.edu.db.CreateDB;
import org.cmu.edu.db.CreateTable;
import org.cmu.edu.db.ManageAutoTable;
import org.cmu.edu.db.ManageMappingTable;
import org.cmu.edu.db.ManageOptionSetTable;
import org.cmu.edu.db.ManageOptionTable;
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
	
	private ManageAutoTable autoTable;
	
	private ManageMappingTable mappingTable;
	
	private ManageOptionSetTable optionSetTable;
	
	private ManageOptionTable optionTable;
	
	public ProxyAutomobile(){
		ProxyAutomobile.automobiles = new AutomobileCollection();
		
		/* create database */
		new CreateDB();
		
		/* create tables */
		new CreateTable();
		
		this.autoTable = new ManageAutoTable();
		
		this.mappingTable = new ManageMappingTable();
		
		this.optionSetTable = new ManageOptionSetTable();
		
		this.optionTable = new ManageOptionTable();
	}
	
	/*
	 * add automobile to automobile collection
	 * @param	automobile
	 * 			the automobile to be added
	 * */
	public void addAutomobile(Automobile automobile){
		ProxyAutomobile.automobiles.addAutomobile(automobile.getName(), automobile);
		
		/* add automobile to database */
		this.autoTable.addAuto(automobile.getName(), automobile.getMake(), automobile.getBasePrice());
		
		int auto_id = this.autoTable.selectAuto(automobile.getName(), automobile.getMake(), automobile.getBasePrice());
		
		/* add optionSet and option to database */
		Set<String> optionSets = automobile.getOptionSets();
		for(String optionSet : optionSets){
			this.optionSetTable.addOptionSet(optionSet);
			int optionSet_id = this.optionSetTable.selectOptionSet(optionSet);
			Set<String> options = automobile.getOptions(optionSet);
			for(String option : options){
				this.optionTable.addOption(option, automobile.getOptionValue(optionSet, option));
				int option_id = this.optionTable.selectOption(option, automobile.getOptionValue(optionSet, option));
				this.mappingTable.addMapping(auto_id, optionSet_id, option_id);
			}
		}
	}
	
	public void deleteAuto(String autoName){
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(autoName);
		ProxyAutomobile.automobiles.deleteAutomobile(autoName);
		/* delete from auto_table */
		int auto_id = this.autoTable.selectAuto(automobile.getName(), automobile.getMake(), automobile.getBasePrice());
		this.autoTable.deleteOption(automobile.getName(), automobile.getMake(), automobile.getBasePrice());
		
		/* delete from mapping_table */
		this.mappingTable.deleteMapping(auto_id);
		
		/* delete from optionSet_table and option_table */
		Set<String> optionSets = automobile.getOptionSets();
		for(String optionSet : optionSets){
			int optionSet_id = this.optionSetTable.selectOptionSet(optionSet);
			if(!this.mappingTable.isOptioinSetContained(optionSet_id)){
				this.optionSetTable.deleteOptionSet(optionSet);
			}
			Set<String> options = automobile.getOptions(optionSet);
			for(String option : options){
				int option_id = this.optionTable.selectOption(option, automobile.getOptionValue(optionSet, option));
				if(!this.mappingTable.isOptioinContained(option_id)){
					this.optionTable.deleteOption(option, automobile.getOptionValue(optionSet, option));
				}
			}
		}
	}
	
	public HashSet<String> getModelNames(){
		return ProxyAutomobile.automobiles.getModelNames();
	}
	
	public void updateOptionSetName(String modelName, String optionSetName,
			String newName) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).updateOptionSetName(optionSetName, newName);
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		this.optionSetTable.addOptionSet(newName);
		int newOptionID = this.optionSetTable.selectOptionSet(newName);
		int oldOptionID = this.optionSetTable.selectOptionSet(optionSetName);
		this.mappingTable.updateOptionSetID(
				this.autoTable.selectAuto(modelName, automobile.getMake(), automobile.getBasePrice()), 
				this.optionSetTable.selectOptionSet(optionSetName), 
				newOptionID);
		if(!this.mappingTable.isOptioinSetContained(oldOptionID)){
			this.optionSetTable.deleteOptionSet(optionSetName);
		}
	}

	public void updateOptionPrice(String modelName, String optionSetName,
			String optionName, int change) {
		int oldPrice = ProxyAutomobile.automobiles.getAutomobile(modelName).getOptionValue(optionSetName, optionName);
		int newPrice = oldPrice + change;
		ProxyAutomobile.automobiles.getAutomobile(modelName).updateOptionPrice(optionSetName, optionName, change);
		this.optionTable.addOption(optionName, newPrice);
		int newOption_id = this.optionTable.selectOption(optionName, newPrice);
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		this.mappingTable.updateOptionID(
				this.autoTable.selectAuto(modelName, automobile.getMake(), automobile.getBasePrice()), 
				this.optionSetTable.selectOptionSet(optionSetName), 
				this.optionTable.selectOption(optionName, oldPrice), 
				newOption_id);
		if(!this.mappingTable.isOptioinContained(this.optionTable.selectOption(optionName, oldPrice))){
			this.optionTable.deleteOption(optionName, oldPrice);
		}
	}

	public void buildAuto(String fileName, String fileType) {
		Util util = new Util(fileName);
		if(fileType.equals("txt")){
			Automobile automobile = util.buildAutoObject();
			//ProxyAutomobile.automobiles.addAutomobile(automobile.getName(), automobile);
			this.addAutomobile(automobile);
		}else{
			this.buildAutoFromProperties(util.getPropertiesObject());
		}
	}

	public void printAuto(String modelName) {
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		System.out.println(automobile.print());
	}
	
	public Automobile getAutomobile(String modelName){
		return ProxyAutomobile.automobiles.getAutomobile(modelName);
	}
	
	public void updateModelName(String modelName, String newName) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).setName(newName);
		Automobile automobile = ProxyAutomobile.automobiles.getAutomobile(modelName);
		ProxyAutomobile.automobiles.deleteAutomobile(modelName);
		ProxyAutomobile.automobiles.addAutomobile(newName, automobile);
		this.autoTable.updateAutoName(modelName, newName);
	}

	public void updateBasePrice(String modelName, int change) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).setBasePrice(change);
		int baseprice = ProxyAutomobile.automobiles.getAutomobile(modelName).getBasePrice() + change;
		this.autoTable.updateAutoBasePrice(modelName, baseprice);
	}

	public void updateMake(String modelName, String make) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).setMake(make);
		this.autoTable.updateAutoMake(modelName, make);
	}

	public void deleteOptionSet(String modelName, String optionSetName) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).deleteOptionSet(optionSetName);
	}
	
	public void addOptionSet(String modelName, OptionSet optionSet) {
		ProxyAutomobile.automobiles.getAutomobile(modelName).addOptionSet(optionSet);
	}

	public void updateOptionSetChoice(String modelName, String optionSetName,
			String optionSetChoice) {
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
		
		/* parse option set */
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
		//ProxyAutomobile.automobiles.addAutomobile(model, automobile);
		this.addAutomobile(automobile);
	}
	
	/*
	 * close resources
	 * */
	public void closeResources(){
		this.autoTable.closeResources();
		this.mappingTable.closeResources();
		this.optionSetTable.closeResources();
		this.optionTable.closeResources();
	}
}

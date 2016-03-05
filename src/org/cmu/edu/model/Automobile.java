package org.cmu.edu.model;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 01/31/2016
 * */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Automobile implements Serializable{
	/* serial version id */
	private static final long serialVersionUID = -4984127716354278171L;
	
	/* automotive name */
	private String name;
	
	/* base price */
	private int basePrice;
	
	/* make */
	private String make;
	
	/* option sets */
	private List<OptionSet> optionSets = new ArrayList<OptionSet>();
	
	/*
	 * @param	name	automotive name
	 * @param	basePrice	base price
	 * @param	make	the name of make
	 * @return	automobile
	 * */
	public Automobile(String name, int basePrice, String make){
		this.name = name;
		this.basePrice = basePrice;
		this.make = make;
	}
	
	public Automobile() {

	}

	public String getName() {
		return name;
	}
	
	public synchronized void setName(String name) {
		this.name = name;
	}
	
	public int getBasePrice() {
		return basePrice;
	}
	
	public synchronized void setBasePrice(int change) {
		this.basePrice += change;
	}
	
	
	public String getMake() {
		return make;
	}

	public synchronized void setMake(String make) {
		this.make = make;
	}

	/*
	 * return the specified OptionSet
	 * @param	setName	the name of option set name
	 * @return	the specified OptionSet
	 * */
	public OptionSet getOptionSet(String setName){
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(setName))
				return optionSet;
		}
		return null;
	}
	
	/*
	 * If there is an OptionSet with name name, return the OptionSet;
	 * otherwise, return null
	 * @param	name	the name of OptionSet
	 * @return	option set
	 * */
	public OptionSet findOptionSetWithName(String name){
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(name))
				return optionSet;
		}
		return null;
	}
	
	/*
	 * set the value of optionSets
	 * @param	optionSets	the value of optionSets in String
	 * */
	public synchronized void setValuesOfOptionSet(String optionSets){
		String[] optionSetArray = optionSets.split("_");
		for(int i = 0; i < optionSetArray.length; i++){
			String[] elements = optionSetArray[i].split(":");
			OptionSet optionSet = new OptionSet(elements[0]);
			optionSet.setOptions(elements[1]);
			this.optionSets.add(optionSet);
		}
	}
	
	/*
	 * delete the specified OptionSet
	 * @param	optionSetName	the name of option set 
	 * */
	public synchronized void deleteOptionSet(String optionSetName){
		int i = 0;
		for(; i < this.optionSets.size(); i++){
			if(this.optionSets.get(i).getName().equals(optionSetName))
				break;
		}
		if(i < this.optionSets.size())
			this.optionSets.remove(i);
	}
	
	/*
	 * add option set
	 * @param	optionSet	option set
	 * */
	public synchronized void addOptionSet(OptionSet optionSet){
		if(optionSet == null)
			return;
		for(OptionSet e : this.optionSets){
			if(e.getName().equals(optionSet.getName()))
				return;
		}
		this.optionSets.add(optionSet);
	}
	
	/*
	 * @return	the	string representation of this automotive
	 * */
	public String print(){
		StringBuffer sb = new StringBuffer();
		sb.append("name:" + this.name + "\n");
		sb.append("make:" + this.make + "\n");
		sb.append("baseprice:" + this.basePrice + "\n");
		sb.append("properties:");
		for(OptionSet optionSet : this.optionSets)
			sb.append(optionSet.print() + "_");
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	/*
	 * get option choice
	 * @param	setName	the name of option set
	 * @return	the name of selected option
	 * */
	public String getOptionChoice(String setName){
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(setName)){
				return optionSet.getOptioinChoice().getName();
			}
		}
		return null;
	}
	
	/*
	 * get the price of option in option set whose name is setName
	 * @param	setName	the name of option set
	 * @return	the price of option
	 * */
	public int getOptionChoicePrice(String setName){
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(setName))
				return optionSet.getOptioinChoice().getValue();
		}
		return 0;
	}
		
	/*
	 * set the selected option to optionName in option set whose
	 * name is setName
	 * @param	setName	the name of option set
	 * @param	optionName	the name of selected option
	 * */
	public synchronized void setOptionChoice(String setName, String optionName){
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(setName))
				optionSet.setOptionChoice(optionName);
		}
	}
	
	/*
	 * get the total price of this automobile
	 * @return the total price of this automobile 
	 * */
	public int getTotalPrice(){
		int price = this.basePrice;
		for(OptionSet optionSet : this.optionSets)
			price += this.getOptionChoicePrice(optionSet.getName());
		return price;
	}
	
	/*
	 * update option set name
	 * @param	optionSetName	the name of option set
	 * @param	newName	the new name of option set
	 * */
	public synchronized void updateOptionSetName(String optionSetName, String newName) {
		// TODO Auto-generated method stub
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(optionSetName)){
				optionSet.setName(newName);
				break;
			}
		}
	}
	
	/*
	 * update the price of given option
	 * @param	optionSetName	the name of option set
	 * @param	optionName	the name of option
	 * @param	newPrice	the new price
	 * */
	public synchronized void updateOptionPrice(String optionSetName,String optionName, int change) {
		// TODO Auto-generated method stub
		for(OptionSet optionSet : this.optionSets){
			if(optionSet.getName().equals(optionSetName)){
				optionSet.setValueOfOption(optionName, change);
				break;
			}
		}
	}
	
	/*
	 * add option set
	 * @param	optionSetName
	 * 			the name of option set
	 * 
	 * @param	options
	 * 			the options, stored as <String, Integer>
	 * */
	public void addOptionSet(String optionSetName, Map<String, Integer> options){
		OptionSet optionSet = new OptionSet(optionSetName, options);
		this.optionSets.add(optionSet);
	}
	
	/*
	 * get the name of all optionSets
	 * */
	public Set<String> getOptionSets(){
		Set<String> res = new HashSet<String>();
		for(OptionSet optionSet : this.optionSets){
			res.add(optionSet.getName());
		}
		return res;
	}
	
	/*
	 * get all options in optionSet
	 * @param	optionSetName
	 * 			the name of optionSet
	 * 
	 * @return	the collection of all options
	 * */
	public Set<String> getOptions(String optionSetName){
		return this.getOptionSet(optionSetName).getOptions();
	}
	
	/*
	 * get the price of specific option
	 * @param	optionSet
	 * 			the name of optionSet
	 * 
	 * @param	option
	 * 			the name of option
	 * 
	 * @return	the value of option
	 * */
	public int getOptionValue(String optionSet, String option){
		return this.getOptionSet(optionSet).getOptionValue(option);
	}
}
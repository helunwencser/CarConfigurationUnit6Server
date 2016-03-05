package org.cmu.edu.model;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 01/31/2016
 * */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * this class define one option set 
 * */
public class OptionSet implements Serializable{

	private static final long serialVersionUID = -6513331677107460693L;

	/*
	 * this class define one option
	 * */
	protected class Option implements Serializable{

		private static final long serialVersionUID = 153998096588698112L;
		/* option name */
		private String name;
		/* option value */
		private int value;
		
		protected Option(String name, int value){
			this.name = name;
			this.value = value;
		}
		
		protected String getName() {
			return name;
		}
		
		protected void setName(String name) {
			this.name = name;
		}
		
		protected int getValue() {
			return value;
		}
		
		protected void setValue(int value) {
			this.value = value;
		}
		
		/*
		 * @return	the string representation of this option
		 * */
		protected String print(){
			StringBuilder sb = new StringBuilder();
			sb.append(name);
			sb.append("|");
			sb.append(value);
			return sb.toString();
		}
	}
	
	/* option set name */
	private String name;
	
	/* options */
	private List<Option> options;
	
	/* selected option name */
	private String selectedOption;
	
	protected OptionSet(String name){
		this.name = name;
		this.options = new ArrayList<Option>();
		this.selectedOption = null;
	}
	
	/*
	 * @param	optionSetName
	 * 			the name of option set
	 * 
	 * @param	options
	 * 			options, stored as <String, Integer>
	 * */
	protected OptionSet(String optionSetName, Map<String, Integer> options) {
		this.name = optionSetName;
		this.options = new ArrayList<Option>();
		this.selectedOption = null;
		Set<String> keys = options.keySet();
		for(String key : keys){
			this.addOption(new Option(key, options.get(key)));
		}
	}
	
	/*
	 * add option
	 * @param	option
	 * 			the option to be added
	 * */
	protected void addOption(Option option){
		this.options.add(option);
	}

	protected String getName() {
		return name;
	}
	
	protected void setName(String name) {
		this.name = name;
	}
	
	/*
	 * @param	options	the string representation of options in this option set
	 * */
	protected void setOptions(String options){
		String[] optionArray = options.split(",");
		for(int i = 0; i < optionArray.length; i++)			
			this.options.add(new Option(optionArray[i].substring(0, optionArray[i].indexOf("|")), 
					Integer.parseInt(optionArray[i].substring(optionArray[i].indexOf("|") + 1))));
	}
	
	/*
	 * @param	name	option name
	 * @return	if there is an option with name name, return the option;
	 * 			otherwise, return null
	 * */
	protected Option findOptionWithName(String name){
		for(Option option : this.options){
			if(option.getName().equals(name))
				return option;
		}
		return null;
	}
	
	/*
	 * set the specified options value
	 * @param	index	the index of option
	 * @param	value	the new value of option
	 * */
	protected void setValueOfOption(String optionName, int change){
		for(Option e: this.options){
			if(e.getName().equals(optionName)){
				e.setValue(e.getValue() + change);
				break;
			}
		}
	}
	
	/*
	 * delete the specified option
	 * @param	index	the index of option
	 * */
	protected void deleteOption(String optionName){
		int i = 0;
		for(; i < this.options.size(); i++){
			if(this.options.get(i).getName().equals(optionName))
				break;
		}
		if(i < this.options.size())
			this.options.remove(i);
	}
	
	/*
	 * @return	the string representation of this option set
	 * */
	protected String print(){
		StringBuilder sb = new StringBuilder();
		sb.append(this.name);
		sb.append(":");
		for(Option option : this.options){
			if(option != null){
				sb.append(option.print());
				sb.append(",");
			}
		}
		sb.setLength(sb.length() - 1);
		return sb.toString();
	}
	
	/*
	 * get the selected option
	 * @return selected option
	 * */
	protected Option getOptioinChoice(){
		if(this.selectedOption != null){
			for(Option option : this.options){
				if(option.getName().equals(this.selectedOption))
					return option;
			}
		}
		return null;
	}
	
	
	/*
	 * set the selected option
	 * @param	optionName	option name
	 * */
	protected void setOptionChoice(String optionName){
		this.selectedOption = optionName;
	}
	
	
}

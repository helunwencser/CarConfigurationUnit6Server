package org.cmu.edu.model;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class AutomobileCollection {
	/* collection saved all automobiles */
	private Map<String, Automobile> automobiles;
	
	public AutomobileCollection(){
		this.automobiles = new LinkedHashMap<String, Automobile>();
	}
	
	/*
	 * add automobile to automobile collection
	 * @param	modelName	the name of automobile
	 * @param	automobile	the automobile
	 * */
	public void addAutomobile(String modelName, Automobile automobile){
		if(!this.automobiles.containsKey(modelName))
			this.automobiles.put(modelName, automobile);
	}
	
	/*
	 * delete automobile from automobile collection
	 * @param	modelName	the name of automobile
	 * */
	public void deleteAutomobile(String modelName){
		if(this.automobiles.containsKey(modelName))
			this.automobiles.remove(modelName);
	}
	
	/*
	 * get automobile from automobile collection if the 
	 * automobile is contained in automobile collection;
	 * otherwise, return null
	 * @param	modelName	the name of automobile
	 * @return	automobile
	 * */
	public Automobile getAutomobile(String modelName){
		Iterator<String> iterator = this.automobiles.keySet().iterator();
		while(iterator.hasNext()){
			if(iterator.next().equals(modelName))
				return this.automobiles.get(modelName);
		}
		return null;
	}
	
	/*
	 * get all automobile names
	 * @return	all the names of automobile
	 * */
	public HashSet<String> getModelNames(){
		HashSet<String> names = new HashSet<String>();
		names.addAll(this.automobiles.keySet());
		return names;
	}
}

package org.cmu.edu.scale;

import org.cmu.edu.adapter.BuildAuto;
/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/10/2016
 * */
import org.cmu.edu.model.OptionSet;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 01/31/2016
 * */

/*
 * edit options for a given model in its own thread
 * */
public class EditOptions implements Runnable{

	private BuildAuto buildAuto;
	private String modelName;
	
	public EditOptions(BuildAuto buildAuto, String modelName){
		this.buildAuto = buildAuto;
		this.modelName = modelName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		 * update model name
		 * */
		String updatedModelName = "updated model name";
		if(this.buildAuto.getAutomobile(this.modelName) != null){
			this.buildAuto.updateModelName(this.modelName, updatedModelName);
		}
		this.modelName = updatedModelName;
		
		/*
		 * update base price 10 times
		 * */
		int change = 10;
		for(int i = 0; i < 10; i++){
			this.buildAuto.updateBasePrice(this.modelName, change);
		}
		
		/*
		 * update make
		 * */
		String newMake = "updated make name";
		this.buildAuto.updateMake(this.modelName, newMake);
		
		/*
		 * update option set
		 * */
		String optionSetName = "powerMoonroof";
		OptionSet optionSet = this.buildAuto.getAutomobile(this.modelName).getOptionSet(optionSetName);
		this.buildAuto.deleteOptionSet(this.modelName, optionSetName);
		this.buildAuto.addOptionSet(this.modelName, optionSet);
		
		/*
		 * update option set choice
		 * */
		String newOptionSetChoice = "none";
		this.buildAuto.updateOptionSetChoice(this.modelName, optionSetName, newOptionSetChoice);
		
		/*
		 * update option set name
		 * */
		String updatedOptionSetName = "updated option set name";
		this.buildAuto.updateOptionSetName(this.modelName, optionSetName, updatedOptionSetName);
		
		/*
		 * update option price
		 * */
		for(int i = 0; i < 10; i++){
			this.buildAuto.updateOptionPrice(this.modelName, "brakes", "Standard", 10);
		}
	}
}

package org.cmu.edu.driver;

import org.cmu.edu.adapter.BuildAuto;
import org.cmu.edu.util.Util;


/*
 * this is the driver for testing db function
 * */
public class DBDriver {
	public static void main(String[] args){
		/* test for creating database and table */
		BuildAuto buildAuto = new BuildAuto();
		
		/* test for creating automobile */
		buildAuto.buildAuto("config1.txt", "txt");
		
		buildAuto.buildAuto("config2.txt", "txt");
		
		buildAuto.buildAutoFromProperties(new Util("properties1").getPropertiesObject());
		
		buildAuto.buildAutoFromProperties(new Util("properties2").getPropertiesObject());
		
		/* test for updating automobile */
		buildAuto.updateModelName("model1", "model1_updated");
		
		buildAuto.updateMake("model2", "model2_Make2_updated");
		
		buildAuto.updateBasePrice("model2", 1000);
		
		/* test for updating option set */
		buildAuto.updateOptionSetName("model2", "color", "color_updated_model2");
		
		/* test for updating option */
		buildAuto.updateOptionPrice("model3", "brakes", "ABS", 500);
		
		/* test for deleting automobile*/
		buildAuto.deleteAuto("model1_updated");
		buildAuto.deleteAuto("model3");
		
		buildAuto.closeResources();
	}
}

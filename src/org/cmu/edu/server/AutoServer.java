package org.cmu.edu.server;

import java.util.Properties;

import org.cmu.edu.model.Automobile;


/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/18/2016
 * */

/*
 * AutoServer interface
 * */
public interface AutoServer {
	
	/*
	 * create Automobile and add it into AutomobileCollection
	 * @param	properties
	 * 			the properties object
	 * */
	public void buildAutoFromProperties(Properties properties);
}

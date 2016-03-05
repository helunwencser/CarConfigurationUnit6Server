package org.cmu.edu.adapter;

import org.cmu.edu.server.AutoServer;


/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */

/*
 * Whenever a new interface has to be added, we need
 * to update BuildAuto declaration and write all 
 * methods in Abstract class called ProxyAutomobile
 * */
public class BuildAuto 
		extends ProxyAutomobile implements CreateAuto, UpdateAuto, AutoServer{

	
}

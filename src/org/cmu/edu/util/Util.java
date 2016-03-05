package org.cmu.edu.util;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 01/31/2016
 * */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.cmu.edu.exception.AutoException;
import org.cmu.edu.exception.ErrorMessage;
import org.cmu.edu.exception.ErrorNo;
import org.cmu.edu.exception.Log;
import org.cmu.edu.model.Automobile;

/*
 * this class read configuration of model from filename and generate the automotive object
 * */
public class Util {
	/* the path of configuration file */
	private String filename;
	
	/*
	 * @param	filename	
	 * 			the path of configuration file
	 * */
	public Util(String filename){
		this.filename = filename;
	}

	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	/*
	 * @return	generated automotive object 
	 * */
	public Automobile buildAutoObject(){
		Automobile automobile = new Automobile();
		boolean hasNotFixed = true;
		do{
			try {
				BufferedReader reader = new BufferedReader(new FileReader(this.filename));
				String line = null;
				while((line = reader.readLine()) != null){
					String prefix = line.substring(0, line.indexOf(":"));
					switch(prefix){
					case "name" :
						String name = line.substring(line.indexOf(":") + 1);
						while(name == null || name.trim().length() == 0){
							try {
								throw new AutoException(ErrorNo.Two.getErrorNo(), ErrorMessage.Two.getErrorMessage());
							} catch (AutoException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getErrorMessage());
								Log.writeLog(e.getErrorMessage());
								name = e.fix(e.getErrorNo());
							}
						}
						automobile.setName(name);
						break;
					case "baseprice" :
						String baseprice = line.substring(line.indexOf(":") + 1);
						while(baseprice == null || !baseprice.matches("[0-9]+")){
							try {
								throw new AutoException(ErrorNo.Four.getErrorNo(), ErrorMessage.Four.getErrorMessage());
							} catch (AutoException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getErrorMessage());
								Log.writeLog(e.getErrorMessage());
								baseprice = e.fix(e.getErrorNo());							
							}
						}
							
						automobile.setBasePrice(Integer.parseInt(baseprice));
						break;
					case "make" :
						String makeName = line.substring(line.indexOf(":") + 1);
						while(makeName == null || makeName.trim().length() == 0){
							try {
								throw new AutoException(ErrorNo.Three.getErrorNo(), ErrorMessage.Three.getErrorMessage());
							} catch (AutoException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getErrorMessage());
								Log.writeLog(e.getErrorMessage());
								makeName = e.fix(e.getErrorNo());
							}
						}
							
						automobile.setMake(makeName);
						break;
					default:
						String properties = line.substring(line.indexOf(":") + 1);
						while(properties == null || properties.trim().length() == 0){
							try {
								throw new AutoException(ErrorNo.Five.getErrorNo(), ErrorMessage.Five.getErrorMessage());
							} catch (AutoException e) {
								// TODO Auto-generated catch block
								System.out.println(e.getErrorMessage());
								Log.writeLog(e.getErrorMessage());
								properties = e.fix(e.getErrorNo());
							}
						}
						automobile.setValuesOfOptionSet(properties);
						break;
					}
				}
				reader.close();
				hasNotFixed = false;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println(ErrorMessage.One.getErrorMessage());
				Log.writeLog(ErrorMessage.One.getErrorMessage());
				AutoException autoException =  new AutoException(ErrorNo.One.getErrorNo(), ErrorMessage.One.getErrorMessage());
				this.setFilename(autoException.fix(ErrorNo.One.getErrorNo()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(hasNotFixed);
		return automobile;
	}
	
	/*
	 * get the properties object from properties file
	 * @return	the properties object
	 * */
	public Properties getPropertiesObject(){
		Properties properties = new Properties();
		try {
			FileInputStream inputStream = new FileInputStream(this.filename);
			properties.load(inputStream);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return properties;
	}
}

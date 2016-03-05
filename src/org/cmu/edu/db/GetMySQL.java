package org.cmu.edu.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetMySQL {
	/*
	 * get the MySQL in the sql file specified by sql title
	 * @param	title
	 * 			the title of the sql
	 * 
	 * @return	the MySQL in file
	 * */
	public static String getMySQL(String title){
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(Config.SQL));
			String line = null;
			while((line = reader.readLine()) != null){
				if(line.equals(title)){
					return reader.readLine();
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}	
}

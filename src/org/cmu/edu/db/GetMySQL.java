package org.cmu.edu.db;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GetMySQL {
	/*
	 * get the MySQL in the file specified in filepath
	 * @param	filepath
	 * 			the path of the file
	 * 
	 * @return	the MySQL in file
	 * */
	public static String getMySQL(String filepath){
		StringBuilder sb = new StringBuilder();
		try {
			@SuppressWarnings("resource")
			BufferedReader reader = new BufferedReader(new FileReader(filepath));
			String line = null;
			while((line = reader.readLine()) != null){
				sb.append(line);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return sb.toString();
	}	
}

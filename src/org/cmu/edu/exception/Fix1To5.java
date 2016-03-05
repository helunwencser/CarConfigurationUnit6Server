package org.cmu.edu.exception;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
import java.util.Scanner;

public class Fix1To5 {
	
	public static String fix1(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("please input valid file name");
		String filename = reader.nextLine();
		while(filename == null || filename.trim().length() == 0){
			System.out.println("please input valid file name");
			filename = reader.nextLine();			
		}
		return filename;
	}

	public static String fix2(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("please input valid model name");
		String modelname = reader.nextLine();
		while(modelname == null || modelname.trim().length() == 0){
			System.out.println("please input the valid model name");
			modelname = reader.nextLine();			
		}
		return modelname;
	}

	public static String fix3(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("please input valid make name");
		String makename = reader.nextLine();
		while(makename == null || makename.trim().length() == 0){
			System.out.println("please input the valid make name");
			makename = reader.nextLine();			
		}
		return makename;
	}
	
	public static String fix4(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("please input valid base price");
		String baseprice = reader.nextLine();
		while(baseprice == null || baseprice.trim().length() == 0 || !baseprice.matches("[0-9]+")){
			System.out.println("please input the valid base price");
			baseprice = reader.nextLine();			
		}
		return baseprice;
	}
	
	public static String fix5(){
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(System.in);
		System.out.println("please input valid properties");
		String properties = reader.nextLine();
		while(properties == null || properties.trim().length() == 0){
			System.out.println("please input the valid properties");
			properties = reader.nextLine();			
		}
		return properties;
	}	
}

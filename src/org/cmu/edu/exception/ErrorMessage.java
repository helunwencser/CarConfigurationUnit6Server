package org.cmu.edu.exception;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
public enum ErrorMessage {
	One("missing filename or wrong filename"),
	Two("missing model name"),
	Three("missing make name"),
	Four("missing base price"),
	Five("missing properties");
	
	private String errorMessage;
	
	ErrorMessage(String errorMessage){
		this.errorMessage = errorMessage;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
}

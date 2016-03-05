package org.cmu.edu.exception;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
public enum ErrorNo {
	One(1),Two(2),Three(3),Four(4),Five(5);
	
	private int errorNo;
	
	ErrorNo(int errorNo){
		this.errorNo = errorNo;
	}
	
	public int getErrorNo(){
		return this.errorNo;
	}
}

package org.cmu.edu.exception;

/*
 * Author: Lunwen He
 * Andrew ID: lunwenh
 * Date: 02/04/2016
 * */
public class AutoException extends Exception implements FixAuto{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8067682678839452359L;
	/* error number */
	private int errorNo;
	
	/* error message */
	private String errorMessage;
	
	public AutoException(int errorNo, String errorMessage){
		this.errorNo = errorNo;
		this.errorMessage = errorMessage;
	}
	
	public int getErrorNo() {
		return errorNo;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorNo(int errorNo) {
		this.errorNo = errorNo;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String fix(int errorNo) {
		String res = null;
		switch (errorNo){
		case 1:
			res = Fix1To5.fix1();
			break;
		case 2:
			res = Fix1To5.fix2();
			break;
		case 3:
			res = Fix1To5.fix3();
			break;
		case 4:
			res = Fix1To5.fix4();
			break;
		default:
			res = Fix1To5.fix5();
			break;
		}
		return res;
	}
	

}

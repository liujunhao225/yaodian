package com.oscar.oscar.exception;

public class InputException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InputException(String msg){
		new Throwable("【进货】错误"+msg);
	}
	
	public InputException(){
		new Throwable("【进货】错误");
	}
}

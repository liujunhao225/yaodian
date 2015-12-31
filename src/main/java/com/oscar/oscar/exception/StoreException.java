package com.oscar.oscar.exception;

public class StoreException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StoreException() {
		new Throwable("【入库】异常");
	}

	public StoreException(String msg) {
		new Throwable("【入库】异常" + msg);
	}
}

package com.oscar.oscar.service;


import com.oscar.oscar.bean.InputLogBean;
import com.oscar.oscar.exception.InputException;


public interface InputLogMapper {
	
	public  int addInputLog(InputLogBean bean) throws InputException;
	
	

}

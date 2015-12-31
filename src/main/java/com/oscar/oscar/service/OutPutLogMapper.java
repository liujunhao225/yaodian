package com.oscar.oscar.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.oscar.oscar.bean.OutPutLogBean;


public interface OutPutLogMapper {
	
	public int addLog(OutPutLogBean  bean);
	
	public List<OutPutLogBean> getlist(RowBounds rowbounds);
	
	public int getlistCount();
}

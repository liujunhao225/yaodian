package com.oscar.oscar.service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.oscar.oscar.bean.MedicineInfoBean;

public interface MedicineInfoMapper {

	public List<MedicineInfoBean> getList(MedicineInfoBean bean,
			RowBounds bounds);

	public int getListCount(MedicineInfoBean bean);

	public int addMedicine(MedicineInfoBean bean);

	public int updateMedicineInfo(MedicineInfoBean bean);

	public int deleteMedicine(int id);


}

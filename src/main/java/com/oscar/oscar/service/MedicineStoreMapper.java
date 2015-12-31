package com.oscar.oscar.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.oscar.oscar.bean.MedicineStoreBean;
import com.oscar.oscar.exception.StoreException;


public interface MedicineStoreMapper {
	
	public List<MedicineStoreBean> getList(String barcode);
	
	public int inputChange(@Param(value="medicineId")int medicineId,@Param(value="leftNum") int number) throws StoreException;
	
	public int outputChange(@Param(value="medicineId")int medicineId,@Param(value="leftNum") int number)throws StoreException;
	
	public int deleteStore(int medicineId);
	
	public int addToStore(@Param(value="medicineId")int medicineId,@Param(value="leftNum") int number)throws StoreException;

	public MedicineStoreBean getMedicineInfoByBarcode(String barcode);
}

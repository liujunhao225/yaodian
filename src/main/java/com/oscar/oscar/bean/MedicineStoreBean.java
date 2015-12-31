package com.oscar.oscar.bean;

/**
 * 库存信息
 * 
 * @author mayibo
 *
 */
public class MedicineStoreBean {

	private int id;
	
	private String medicineId;
	/**
	 * 数量必须大于等于0
	 */
	private String leftNum;

	private MedicineInfoBean medicineInfoBean;

	public String getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(String medicineId) {
		this.medicineId = medicineId;
	}

	public String getLeftNum() {
		return leftNum;
	}

	public void setLeftNum(String leftNum) {
		this.leftNum = leftNum;
	}

	public MedicineInfoBean getMedicineInfoBean() {
		return medicineInfoBean;
	}

	public void setMedicineInfoBean(MedicineInfoBean medicineInfoBean) {
		this.medicineInfoBean = medicineInfoBean;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}

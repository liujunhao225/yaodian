package com.oscar.oscar.bean;

/**
 * 进货记录实体类
 * @author mayibo
 *
 */
public class InputLogBean {
	
	private int logId;
	
	private int medicineId;
	
	private long ctime;
	
	private int count;
	
	private float inputPrice;

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public long getCtime() {
		return ctime;
	}

	public void setCtime(long ctime) {
		this.ctime = ctime;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public float getInputPrice() {
		return inputPrice;
	}

	public void setInputPrice(float inputPrice) {
		this.inputPrice = inputPrice;
	}
	
	

}

package com.oscar.oscar.bean;

public class OutPutLogBean {

	private int logId;

	private int medicineId;

	private long ctime;

	private float sellPrice;
	
	private int sellsCount;
	
	private String sellsDay;
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

	public float getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(float sellPrice) {
		this.sellPrice = sellPrice;
	}

	public int getSellsCount() {
		return sellsCount;
	}

	public void setSellsCount(int sellsCount) {
		this.sellsCount = sellsCount;
	}

	public String getSellsDay() {
		return sellsDay;
	}

	public void setSellsDay(String sellsDay) {
		this.sellsDay = sellsDay;
	}

}

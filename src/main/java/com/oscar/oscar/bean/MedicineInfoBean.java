package com.oscar.oscar.bean;

/**
 * 药品详细信息
 * @author mayibo
 *
 */
public class MedicineInfoBean {

	private int medicineId;
	private String medicineName;
	private String medicineBarCode;
	private float medicineBasePrice;
	private float medicineSellsPrice;

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getMedicineBarCode() {
		return medicineBarCode;
	}

	public void setMedicineBarCode(String medicineBarCode) {
		this.medicineBarCode = medicineBarCode;
	}

	public float getMedicineBasePrice() {
		return medicineBasePrice;
	}

	public void setMedicineBasePrice(float medicineBasePrice) {
		this.medicineBasePrice = medicineBasePrice;
	}

	public float getMedicineSellsPrice() {
		return medicineSellsPrice;
	}

	public void setMedicineSellsPrice(float medicineSellsPrice) {
		this.medicineSellsPrice = medicineSellsPrice;
	}

}

package com.oscar.oscar.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.oscar.bean.MedicineInfoBean;
import com.oscar.oscar.service.MedicineInfoMapper;

@Component
@Controller
@RequestMapping("/info")
public class MedicineInfoAction {
	private Logger log = Logger.getLogger(LoginAction.class);
	@Autowired
	private MedicineInfoMapper medicineInfoMapper;

	/**
	 * 跳转到药品详细信息页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/index.do")
	public String index() {
		return "/info/info";
	}

	@RequestMapping(value = "/list.do")
	@ResponseBody
	public String getList(HttpServletRequest reqeust) {
		String medicineName = reqeust.getParameter("medicineName");
		String medicineBarCode = reqeust.getParameter("medicineBarCode");
		String start = reqeust.getParameter("page");
		String limit = reqeust.getParameter("limit");
		int startNum = 0;
		int limitNum = 15;
		try {
			limitNum = Integer.parseInt(limit) + startNum;
			startNum = (Integer.parseInt(start) - 1) * limitNum;
		} catch (NumberFormatException e) {
			startNum = 0;
			limitNum = 15;
		}
		MedicineInfoBean bean = new MedicineInfoBean();
		bean.setMedicineBarCode(medicineBarCode);
		bean.setMedicineName(medicineName);
		JSONObject json = new JSONObject();
		List<MedicineInfoBean> list = medicineInfoMapper.getList(bean,
				new RowBounds(startNum, limitNum));
		int count = medicineInfoMapper.getListCount(bean);
		json.put("datalist", list);
		json.put("totalRecords", count);
		return json.toString();
	}

	@RequestMapping("/edit.do")
	@ResponseBody
	public String edit(HttpServletRequest request) {
		String medicineName = request.getParameter("medicineName");
		String basePrice = request.getParameter("basePrice");
		String sellsPrice = request.getParameter("sellsPrice");
		String medicineId = request.getParameter("medicineId");
		System.out.println("basePrice:"+basePrice);
		MedicineInfoBean bean = new MedicineInfoBean();
		try {
			bean.setMedicineBasePrice(Float.valueOf(basePrice));
		} catch (NumberFormatException e) {

		}
		try {
			bean.setMedicineSellsPrice(Float.valueOf(sellsPrice));
		} catch (NumberFormatException e) {

		}
		bean.setMedicineName(medicineName);
		bean.setMedicineId(Integer.valueOf(medicineId));
		medicineInfoMapper.updateMedicineInfo(bean);
		JSONObject job = new JSONObject();
		job.put("success", true);
		job.put("desc", "提交申请成功!");
		return job.toString();
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public String add(HttpServletRequest request) {
		String medicineName = request.getParameter("medicineName");
		String basePrice = request.getParameter("basePrice");
		String sellsPrice = request.getParameter("sellsPrice");
		String medicineBarCode = request.getParameter("medicineBarCode");
		MedicineInfoBean bean = new MedicineInfoBean();
		try {
			bean.setMedicineBasePrice(Float.valueOf(basePrice));
		} catch (NumberFormatException e) {
		}
		try {
			bean.setMedicineSellsPrice(Float.valueOf(sellsPrice));
		} catch (NumberFormatException e) {
		}
		bean.setMedicineName(medicineName);
		bean.setMedicineBarCode(medicineBarCode);
		medicineInfoMapper.addMedicine(bean);
		JSONObject job = new JSONObject();
		job.put("success", true);
		job.put("desc", "提交申请成功!");
		return job.toString();
	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		String medicineId = request.getParameter("medicineId");
		medicineInfoMapper.deleteMedicine(Integer.valueOf(medicineId));
		JSONObject job = new JSONObject();
		job.put("success", true);
		job.put("desc", "提交申请成功!");
		return job.toString();
	}
}

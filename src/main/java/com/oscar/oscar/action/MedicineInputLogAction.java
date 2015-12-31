package com.oscar.oscar.action;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.oscar.bean.MedicineInfoBean;
import com.oscar.oscar.bean.MedicineStoreBean;
import com.oscar.oscar.exception.StoreException;
import com.oscar.oscar.service.InputLogMapper;
import com.oscar.oscar.service.MedicineInfoMapper;
import com.oscar.oscar.service.MedicineStoreMapper;

@Component
@Controller
@RequestMapping("/input")
public class MedicineInputLogAction {

	@Autowired
	private InputLogMapper inputLogMapper;

	@Autowired
	private MedicineStoreMapper medicineStoreMapper;

	@Autowired
	private MedicineInfoMapper medicineInfoMapper;

	private static Logger logger = Logger
			.getLogger(MedicineInputLogAction.class);

	@RequestMapping("/index.do")
	public String index(HttpServletRequest request) {
		return "/input/input";
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public String add(HttpServletRequest request) {
		try {
//			// 新增商品信息 start
			String medicineId = request.getParameter("medicineId");
			String strCount = request.getParameter("strCount");
		
//			MedicineInfoBean bean = new MedicineInfoBean();
//			bean.setMedicineBarCode(barCode);
//			bean.setMedicineBasePrice(Integer.valueOf(basePrice));
//			bean.setMedicineSellsPrice(Integer.valueOf(sellsPrice));
//			bean.setMedicineName(medicineName);
//			int medicineId = medicineInfoMapper.addMedicine(bean);
//			// 新增商品信息 end

			// 新增商品数量
			medicineStoreMapper.addToStore(Integer.parseInt(medicineId),
					Integer.valueOf(strCount));
		} catch (StoreException e) {
			logger.info("【进货】增加商品数量出错" + e.getMessage());
		} catch (Exception e) {
			logger.error("【进货】增加商品错误:" + e.getMessage());
		}
		return "";
	}

	@RequestMapping("/update.do")
	@ResponseBody
	public String updateCount(HttpServletRequest request) {
		try {
			String medicineId = request.getParameter("medicineId");
			String strCount = request.getParameter("strCount");
			int result = medicineStoreMapper.inputChange(
					Integer.valueOf(medicineId), Integer.valueOf(strCount));
			String msg = result > 0 ? "成功" : "失败";
			logger.info("【进货】更新结果:" + msg);
		} catch (StoreException e) {
			logger.error("【进货】更新失败：" + e.getMessage());
		}
		return "";
	}

	@RequestMapping("/findone.do")
	@ResponseBody
	public String getOne(HttpServletRequest request) {
		String barcode = request.getParameter("barCode");
		MedicineStoreBean bean = medicineStoreMapper
				.getMedicineInfoByBarcode(barcode);
		MedicineInfoBean medicinebean = bean.getMedicineInfoBean();
		Method[] methods = MedicineInfoBean.class.getDeclaredMethods();
		JSONObject job = new JSONObject();
		for (Method me : methods) {
			if (me.getName().startsWith("get")) {
				try {
					String value = (String) me.invoke(medicinebean);
					job.put(me.getName().substring(3, 4).toLowerCase() 
							+ me.getName().substring(4), value);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					try {
						Float value = (Float) me.invoke(medicinebean);
						job.put(me.getName().substring(3, 4).toLowerCase()
								  + me.getName().substring(4), value);
					} catch (Exception e2) {
						try {
							Integer value = (Integer) me.invoke(medicinebean);
							job.put(me.getName().substring(3, 4).toLowerCase()
									 + me.getName().substring(4), value);
						} catch (Exception e3) {

						}
					}

				}
			}
		}
		job.put("medicineId", bean.getMedicineId());
		job.put("id", bean.getId());
		job.put("leftNum", bean.getLeftNum());
		// job.put("record", bean);
		return job.toString();

	}

	@RequestMapping("/delete.do")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		String medicineId = request.getParameter("medicineid");
		medicineStoreMapper.deleteStore(Integer.valueOf(medicineId));
		return "";
	}

}

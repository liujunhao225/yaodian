package com.oscar.oscar.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.oscar.bean.OutPutLogBean;
import com.oscar.oscar.exception.StoreException;
import com.oscar.oscar.service.MedicineStoreMapper;
import com.oscar.oscar.service.OutPutLogMapper;
@Component
@Controller
@RequestMapping("/sells")
public class SellsAction {
	
	@Autowired
	private MedicineStoreMapper medicineStoreMapper;
	@Autowired
	private OutPutLogMapper outPutLogMapper;
	@RequestMapping("/index.do")
	public String index(){
		
		return "/output/output";
	}

	@RequestMapping("/add.do")
	@ResponseBody
	public String addLog(HttpServletRequest request){
		String allrecord=request.getParameter("allRecord");
		JSONArray array = new JSONArray(allrecord);
		
		for(int i =0;i<array.length();i++){
			
			JSONObject job = array.getJSONObject(i);
			int medicineId =job.getInt("medicineId");
			int sellsCount = job.getInt("tempcount");
			float medicineSellsPrice =(float)job.getDouble("medicineSellsPrice");
			long ctime = System.currentTimeMillis();
			System.out.println("ctime"+ctime);
			//减少数量
			try {
				medicineStoreMapper.outputChange(medicineId, sellsCount);
			} catch (StoreException e) {
				e.printStackTrace();
			}
			//增加日志
			OutPutLogBean bean = new OutPutLogBean();
			bean.setCtime(ctime);
			bean.setMedicineId(medicineId);
			bean.setSellPrice(medicineSellsPrice);
			bean.setSellsCount(sellsCount);
			SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd");
			String inputday = format.format(new Date());
			bean.setSellsDay(inputday);
			outPutLogMapper.addLog(bean);
		}
		return "";
	}
	
	
}

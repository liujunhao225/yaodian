package com.oscar.oscar.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.RowBounds;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.oscar.bean.OutPutLogBean;
import com.oscar.oscar.service.OutPutLogMapper;

@Component
@Controller
@RequestMapping("/summary")
public class SummaryAction {

	@Autowired
	private OutPutLogMapper outPutLogMapper;
	@RequestMapping("/index.do")
	public String index(){
		return "/summary/summary";
	}
	@RequestMapping("/list.do")
	@ResponseBody
	public String list(HttpServletRequest request){
		String start = request.getParameter("page");
		String limit = request.getParameter("limit");
		int startNum = 0;
		int limitNum = 15;
		try {
			limitNum = Integer.parseInt(limit) + startNum;
			startNum = (Integer.parseInt(start)-1)*limitNum;
		} catch (NumberFormatException e) {
			startNum = 0;
			limitNum = 15;
		}
		int totalRecords = outPutLogMapper.getlistCount();
		List<OutPutLogBean> list = outPutLogMapper.getlist(new RowBounds(startNum,limitNum));
		List<JSONObject> job = new ArrayList<JSONObject>();
		for (OutPutLogBean bean : list) {
			JSONObject tempjob = new JSONObject();
			tempjob.put("summoney", bean.getSellPrice());
			tempjob.put("sellsDay",bean.getSellsDay() );
			job.add(tempjob);
		}
		JSONObject returnJob = new JSONObject();
		returnJob.put("totalRecords", totalRecords);
		returnJob.put("datalist", job);
		System.out.println(returnJob.toString());
		return returnJob.toString();
	}
}

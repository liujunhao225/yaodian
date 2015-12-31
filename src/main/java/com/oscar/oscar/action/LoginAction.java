/**
 * 登录操作
 */
package com.oscar.oscar.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.oscar.oscar.util.MD5Utils;

@Component
@Controller
@RequestMapping("/login")
public class LoginAction {
	private Logger log = Logger.getLogger(LoginAction.class);

	
	@RequestMapping(value = "/index")
	@ResponseBody
	public String index(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping(value = "/login", produces = "application/json;charset=utf-8")
	@ResponseBody
	public String login(HttpServletRequest request) {
		String username = request.getParameter("userName");
		String password = request.getParameter("password");

		JSONObject job = new JSONObject();
		if (username == null||password==null ||username.length()==0|| password.length()==0) {
			job.put("errorType", 2);
		} else if (!checkPassword(password, "123456")) {
			job.put("errorType", 3);
		} else {
			job.put("success", true);
			request.getSession().setAttribute("username", username);
		}
		return job.toString();
	}

	@RequestMapping(value = "/toLogin")
	public String todoLogin(HttpServletRequest request) {
		
		return "/login/login";

	}

	@RequestMapping("/toIndex")
	public String main(HttpServletRequest request,ModelMap map) {
		Object  userNameObj = request.getSession().getAttribute("username");
		if(userNameObj ==null){
			return "/login/login";
		}
		String userName = userNameObj.toString();
		if("admin".equals(userName)){
			return "index_admin";
		}
		// 处理操作权限
		return "index";
		
	}
	@RequestMapping("/toAdminIndex")
	public String adminmain(HttpServletRequest request) {
		return "index_admin";
	}

	@RequestMapping(value = "/logout")
	public String logOut(HttpServletRequest request) {
		request.getSession().invalidate();
		return "/login/login";
	}

	private boolean checkPassword(String password1, String password2) {
		System.out.println(MD5Utils.MD5(password1));
//		if (MD5Utils.MD5(password1).equals(password2)) {
//			return true;
//		}
		if(password1.equals("123456")){
			return true;
		}
		return false;
	}
	


}

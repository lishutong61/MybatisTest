package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.Service.AdminService;

/**
* @author lishutong
* @version 创建时间:2019年7月31日 上午11:07:37
* 类说明
*/
@Controller
public class adminController {
	
	@Autowired
	private AdminService adminService;
	@GetMapping("/admin")
	public @ResponseBody String admintest() {
		
		return adminService.getAll().toString();
	}
}

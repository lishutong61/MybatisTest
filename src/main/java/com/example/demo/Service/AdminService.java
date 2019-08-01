package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.AdminDao;
import com.example.demo.entity.User;

/**
* @author lishutong
* @version 创建时间:2019年7月31日 下午2:35:32
* 类说明
*/
@Service
public class AdminService {
	@Autowired
	private AdminDao adminDao;
	
	public User getAll() {
		return adminDao.selectall();
	}
}

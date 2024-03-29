package com.example.demo.Service;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
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
	
	public List<User> getAll(int pageNumber,int pageSize) {
		int a=(pageNumber-1)*pageSize;
		
		//return adminDao.selectall(new RowBounds(a,pageSize));
		return adminDao.selectall();
	}
}

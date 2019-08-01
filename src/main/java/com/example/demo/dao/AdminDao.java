package com.example.demo.dao;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/**
* @author lishutong
* @version 创建时间:2019年7月31日 下午2:43:55
* 类说明
*/
@Repository
public interface AdminDao {
	User selectall();
}

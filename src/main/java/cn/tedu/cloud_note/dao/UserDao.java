package cn.tedu.cloud_note.dao;

import java.util.Map;

import cn.tedu.cloud_note.entity.User;

public interface UserDao {
	//登录方法
	public User findByName(String name);
	//注册方法
	public void save(User user);
	//根据userId查询user信息
	public User findByUserId(String userId);
	//修改密码
	public int changepassword(Map param);
}

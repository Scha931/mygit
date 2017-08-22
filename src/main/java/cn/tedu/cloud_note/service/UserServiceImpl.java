package cn.tedu.cloud_note.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("userService")//扫描的spring容器
@Transactional
public class UserServiceImpl implements UserService{

	@Resource
	private UserDao userDao;
	
	public NoteResult<User> checkLogin(String name, String password) {
		//接收结果数据
		NoteResult<User> result = new NoteResult<User>();
		//按参数name查询数据库
		User user = userDao.findByName(name);
		if(user == null){
			result.setStatus(1);
			result.setMsg("用户名不存在");
			return result;
		}
		//检测密码
		String md5Password = NoteUtil.md5(password);
		if(!user.getCn_user_password().equals(md5Password)){
			result.setStatus(2);
			result.setMsg("密码错误");
			return result;
		}
		//用户名和密码都正确
		result.setStatus(0);
		result.setMsg("登录成功");
		result.setData(user);
		return result;
	}

	public NoteResult<Object> addUser(String name, String password, String nickname) {
		NoteResult<Object> result = new NoteResult<Object>();
		//用户检测
		User hasUser = userDao.findByName(name);
		if(hasUser!=null){ //用户名存在
			result.setStatus(1);
			result.setMsg("用户已被占用");
			return result;
		}
		//添加新用户
		User user = new User();
		//设置用户id
		String id = NoteUtil.createId();
		user.setCn_user_id(id);
		//设置用户名
		user.setCn_user_name(name);
		//设置加密后的密码
		String md5password = NoteUtil.md5(password); 
		user.setCn_user_password(md5password);
		//设置昵称
		user.setCn_user_nick(nickname);
		userDao.save(user);
		result.setStatus(0);
		result.setMsg("注册成功");
		return result;
	}
	//修改密码
	public NoteResult<Object> changepassword(String userId, String oldpassword, String password) {
		User user = userDao.findByUserId(userId);
		NoteResult<Object> result = new NoteResult<Object>();
		String md5password = NoteUtil.md5(password);
		String md5oldpassword = NoteUtil.md5(oldpassword);
		if(user.getCn_user_password().equals(md5oldpassword)){
			Map<Object,Object> param = new HashMap<Object, Object>();
			param.put("userId", userId);
			param.put("password", md5password);
			int n = userDao.changepassword(param);
			if(n==1){
				result.setStatus(0);
				result.setMsg("密码修改成功");
				return result;
			}else{
				result.setStatus(1);
				result.setMsg("密码修改失败");
				return result;
			}
		}else{
			result.setStatus(3);
			result.setMsg("密码错误");
			return result;
		}
	}
}




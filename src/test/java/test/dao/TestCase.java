package test.dao;


import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.UserDao;
import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.util.NoteUtil;

public class TestCase {
	@Test
	public void test1(){
		ApplicationContext  ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao = ac.getBean("userDao",UserDao.class);
		User user = dao.findByName("yht");
		System.out.println(user);
	}
	@Test
	public void save(){
		String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		UserDao dao = ac.getBean("userDao",UserDao.class);
		User user = new User();
		user.setCn_user_id("123456789");
		user.setCn_user_name("jack");
		user.setCn_user_password("123456");
		user.setCn_user_nick("杰克");
		dao.save(user);
	}
	@Test //用例3. 预测结果： 修改密码 123123
	public void test3(){
		ApplicationContext  ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao = ac.getBean("userDao",UserDao.class);
		Map<Object,Object> map = new HashMap<Object, Object>();
		map.put("userId","48595f52-b22c-4485-9244-f4004255b972");
		String password = NoteUtil.md5("123123");
		map.put("password",password);
		int n = dao.changepassword(map);
		System.out.println(n);
	}
	@Test //用例4. 预测结果， 根据userId 查询user信息
	public void test4(){
		ApplicationContext  ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		UserDao dao = ac.getBean("userDao",UserDao.class);
		User user = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(user);
	}
}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
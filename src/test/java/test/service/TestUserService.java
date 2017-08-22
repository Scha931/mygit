package test.service;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.entity.User;
import cn.tedu.cloud_note.service.UserService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestUserService {
	UserService service;
	@Before
	public void init(){
		String[] conf = {"conf/spring-mvc.xml","conf/spring-mybatis.xml","conf/spring-transaction.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		service = ac.getBean("userService",UserService.class);	
	}
	@Test //����-1. Ԥ�ڽ�����û���������
	public void test1(){
		NoteResult<User> result = service.checkLogin("���ʦ��", "111");
		System.out.println(service.getClass().getName());
		//System.out.println(result.getStatus());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
	}
	@Test //����-1. Ԥ�ڽ�����������
	public void test2(){
		NoteResult<User> result = service.checkLogin("Tom", "1234");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	@Test //����-1. Ԥ�ڽ������¼�ɹ�
	public void test3(){
		NoteResult<User> result = service.checkLogin("Tom", "123456");
		System.out.println(result.getStatus());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
	}
	
	@Test
	//����-4. Ԥ�ڽ����ע��ɹ�
	public void test4(){
		NoteResult<Object> obj = service.addUser("zhangsan", "123456", "����");
		System.out.println(obj);
	}
	@Test
	//����-5. Ԥ�ڽ�����޸�����ɹ� ���� 0
	public void test5(){
		NoteResult<Object> obj = service.changepassword("48595f52-b22c-4485-9244-f4004255b972","123456", "123333");
		System.out.println(obj);
	}
}

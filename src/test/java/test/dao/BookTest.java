package test.dao;

import java.sql.Timestamp;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteUtil;

public class BookTest {
	@Test //����1.����Ԥ�⣺����userId �鵽���ʼ�
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		BookDao dao = ac.getBean("bookDao",BookDao.class);
		List<Book>  list = dao.findByUserId("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(list);
	}
	
	@Test // ����2. ����Ԥ�⣺���book �ɹ�����
	public void test2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		BookDao dao = ac.getBean("bookDao",BookDao.class);
		Timestamp time = new Timestamp(System.currentTimeMillis());
		System.out.println(time);
		Book book = new Book();
		book.setCn_user_id("39295a3d-cc9b-42b4-b206-a2e7fab7e77c");
		book.setCn_notebook_id(NoteUtil.createId());
		book.setCn_notebook_name("������ӱʼ�");
		book.setCn_notebook_createtime(time);
		int n = dao.addBook(book);
		System.out.println(n);
	}
}

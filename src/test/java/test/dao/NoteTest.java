package test.dao;

import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.NoteDao;
import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteUtil;

public class NoteTest {
	@Test// 用例1. 查询note到结果
	public void test1(){
		String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		List<Map> list= dao.findByBookId("0cd94778-4d52-486d-a35d-263b3cfe6de9");
		for(Map map:list){
			System.out.println(map);
		}
	}
	@Test// 用例2. 未查到note结果
	public void test2(){
		String[] conf = {"conf/spring-mybatis.xml","conf/spring-mvc.xml"};
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		List<Map> list= dao.findByBookId("111");
		System.out.println(list);
	}
	@Test //用例3. 根据noteId 查询笔记内容 和笔记标题 
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		Note list = dao.findByNoteId("3febebb3-a1b7-45ac-83ba-50cdb41e5fc1");
		System.out.println(list);
	}
	
	@Test //用例4. 预测结果：修改成功
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		String cn_note_id = "7e87de1e-0963-4fe0-b996-d0b13481786e";
		String cn_note_title ="周佳";
		String cn_note_body = "啊水井坊绿卡圣诞节法拉盛简单了咖啡机卡拉斯的了发生发了束带结发阿斯蒂芬,单了咖啡机卡拉斯的了发生发了束带结发阿斯蒂芬.....";
		Note note = new Note();
		note.setCn_note_id(cn_note_id);
		note.setCn_note_title(cn_note_title);
		note.setCn_note_body(cn_note_body);
		Long time = System.currentTimeMillis();
		note.setCn_note_last_modify_time(time);
		int n = dao.updataNote(note);
		System.out.println(n);
	}
	@Test //用例5. 预测结果：添加成功
	public void test5(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		String userId = "48595f52-b22c-4485-9244-f4004255b972";
		String bookId = "1454cd9b8fee4b62808e68e5fd8f9c6c";
		String noteId = NoteUtil.createId();
		String title = "测试笔记添加";
		Note note = new Note();
		note.setCn_user_id(userId);
		note.setCn_notebook_id(bookId);
		note.setCn_note_title(title);
		note.setCn_note_id(noteId);
		note.setCn_note_status_id("1");
		int n = dao.insertNote(note);
		System.out.println(n);
	}
	@Test //用例6. 修改笔记状态 ， 结果预测：修改成功
	public void test6(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		NoteDao dao = ac.getBean("noteDao",NoteDao.class);
		String noteId = "7a6454c3aa214981bb62c7ebdda47303";
		String status = "1";
		int n  = dao.changeNoteStatus(noteId,status);
		System.out.println(n);
	}
}















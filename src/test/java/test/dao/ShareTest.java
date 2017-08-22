package test.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.tedu.cloud_note.dao.ShareDao;
import cn.tedu.cloud_note.entity.Share;
import cn.tedu.cloud_note.util.NoteUtil;

public class ShareTest {
	@Test //����1. ����Ԥ�ڣ���ӷ���ɹ�
	public void test1(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		ShareDao dao  = ac.getBean("shareDao",ShareDao.class);
		String shareId = NoteUtil.createId();
		String title = "asdf asdfds";
		String body = "����ҷ������յ��˸�";
		String noteId = "7a6454c3aa214981bb62c7ebdda47303";
		Share share = new Share();
		share.setCn_share_id(shareId);
		share.setCn_share_title(title);
		share.setCn_share_body(body);
		share.setCn_note_id(noteId);
		int n = dao.save(share);
		System.out.println(n);
	}
	@Test //����2. ����Ԥ�ڣ�����noteId ����shareId
	public void test2(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		ShareDao dao  = ac.getBean("shareDao",ShareDao.class);
		List<Map> data = dao.findShareIdByNoteId("7a6454c3aa214981bb62c7ebdda47303");
		System.out.println(data);
	}
	@Test //����3. ����Ԥ�ڣ�������  3�� ���� �йصıʼ�  
	public void test3(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		ShareDao dao  = ac.getBean("shareDao",ShareDao.class);
		Map<Object,Object> param = new HashMap<Object,Object>();
		param.put("page", 1);
		param.put("title", "%����%");
		List<Share> list = dao.findShareBysearch(param);
		for(Share s:list){
			System.out.println(s);
		}
	}	
	@Test //����4. ����Ԥ�ڣ�������������ıʼ�
	public void test4(){
		ApplicationContext ac = new ClassPathXmlApplicationContext("conf/spring-mybatis.xml");
		ShareDao dao  = ac.getBean("shareDao",ShareDao.class);
		Map<Object,Object> param = new HashMap<Object,Object>();
		param.put("page", 1);
		param.put("title", "%��ʦ��%");
		List<Share> list = dao.findShareBysearch(param);
		for(Share s:list){
			System.out.println(s);
		}
	}
}
	
	
	
	
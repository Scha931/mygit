package test.service;


import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;

public class TestBookService extends TestBase{
	BookService bookService;
	@Before
	public void init(){
		bookService = super.getContext().getBean("bookService",BookService.class);
	}
	
	@Test //���Ը���uerId ���ұʼ�
	public void test1(){
		NoteResult<List<Book>> result= bookService.loadBooks("48595f52-b22c-4485-9244-f4004255b972");
		System.out.println(result);
	}
	
	@Test //����2�� ����Ԥ�ƣ����� ������service �ʼǳɹ���
	public void test2(){
		String cn_user_id="39295a3d-cc9b-42b4-b206-a2e7fab7e77c";
		String bookName = "����service�ʼ�";
		NoteResult<Object> result = bookService.addBook(cn_user_id, bookName);
		System.out.println(result);
	}
}

package cn.tedu.cloud_note.service;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.cloud_note.dao.BookDao;
import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;
import cn.tedu.cloud_note.util.NoteUtil;

@Service("bookService") //扫描spring容器
public class BookServiceImpl implements BookService{

	@Resource
	private BookDao bookDao;
	
	public NoteResult<List<Book>> loadBooks(String userId) {
		List<Book> list = bookDao.findByUserId(userId);
		NoteResult<List<Book>> result = new NoteResult<List<Book>>();
		if(list == null){
			result.setStatus(1);
			result.setMsg("查找失败");
			return result;
		}
		result.setStatus(0);
		result.setData(list);
		result.setMsg("查询成功");
		return result;
	}

	public NoteResult<Object> addBook(String cn_user_id,String bookName) {
		//创建bookid 和 当前时间
		String bookId = NoteUtil.createId();
		Timestamp createTime = new Timestamp(System.currentTimeMillis());
		Book book = new Book();
		book.setCn_notebook_id(bookId);
		book.setCn_user_id(cn_user_id);
		book.setCn_notebook_name(bookName);
		book.setCn_notebook_createtime(createTime);
		int n = bookDao.addBook(book);
		NoteResult<Object> result = new NoteResult<Object>();
		if(n==1){
			result.setStatus(0);
			result.setData(book);
			result.setMsg("添加笔记成功");
			System.out.println(book);
			return result;
		}else{
			result.setStatus(1);
			result.setMsg("添加笔记失败");
			return result;
		}
	}
}












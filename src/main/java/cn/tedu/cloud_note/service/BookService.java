package cn.tedu.cloud_note.service;

import java.util.List;

import cn.tedu.cloud_note.entity.Book;
import cn.tedu.cloud_note.util.NoteResult;

public interface BookService {
	public NoteResult<List<Book>> loadBooks(String userId);
	public NoteResult<Object> addBook(String cn_user_id,String bookName);
}

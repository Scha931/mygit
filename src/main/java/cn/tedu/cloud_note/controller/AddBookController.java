package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.BookService;
import cn.tedu.cloud_note.util.NoteResult;
@RequestMapping("/book")
@Controller
public class AddBookController {
	@Resource
	private BookService service;
	@RequestMapping("/addbook.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId,String bookName){
		System.out.println(userId+","+bookName);
		NoteResult<Object> result = service.addBook(userId, bookName);
		System.out.println(result);
		return result;
	}
	
}

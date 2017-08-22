package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/note")
public class AddNoteController {
	@Resource
	private NoteService service;
	@RequestMapping("/addNote.do")
	@ResponseBody
	public NoteResult<Object> execute(String userId,String bookId,String title){
		NoteResult<Object> result = service.addNote(userId, bookId, title);

		return result;
	}
}

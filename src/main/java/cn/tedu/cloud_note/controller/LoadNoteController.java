package cn.tedu.cloud_note.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/note")
public class LoadNoteController {
	@Resource
	private NoteService service;
	//根据bookid查找笔记
	@RequestMapping("/loadnotes.do")
	@ResponseBody
	public NoteResult<List<Map>> execute(String bookId){
		NoteResult<List<Map>> result = service.loadNote(bookId);
		System.out.println(result);
		return result;
	}
	
}

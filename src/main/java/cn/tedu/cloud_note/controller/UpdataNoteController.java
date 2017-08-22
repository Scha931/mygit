package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;

@Controller
@RequestMapping("/note")
public class UpdataNoteController {
	@Resource
	private NoteService service;
	@ResponseBody
	@RequestMapping("/updataNote.do")
	public NoteResult<Object> execute(String noteId,String note_title,String note_body){
		
		NoteResult<Object> result = service.updataNote(noteId, note_title, note_body);
		return result;
	}
}

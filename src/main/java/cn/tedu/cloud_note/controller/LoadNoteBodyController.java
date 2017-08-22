package cn.tedu.cloud_note.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.service.NoteService;
import cn.tedu.cloud_note.util.NoteResult;
@Controller
@RequestMapping("/note")
public class LoadNoteBodyController {
	@Resource
	private NoteService service;
	@RequestMapping("/load.do")
	@ResponseBody
	public NoteResult<Note> execute(String noteId){
		NoteResult<Note> result = service.loadNoteBody(noteId);
		return result;
	}
}

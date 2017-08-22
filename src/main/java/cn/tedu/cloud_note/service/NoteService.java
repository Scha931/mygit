package cn.tedu.cloud_note.service;

import java.util.List;
import java.util.Map;

import cn.tedu.cloud_note.entity.Note;
import cn.tedu.cloud_note.util.NoteResult;

public interface NoteService {
	public NoteResult<List<Map>> loadNote(String bookId);  
	public NoteResult<Note> loadNoteBody(String noteId);
	public NoteResult<Object> updataNote(String noteId,String note_title,String note_body);
	public NoteResult<Object> addNote(String userId,String bookId,String title);
	public NoteResult<Object> changeNoteStatus(String noteId);
}
